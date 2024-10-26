package com.example.petshop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.petshop.config.MailerService;
import com.example.petshop.entity.Authority;
import com.example.petshop.entity.Role;
import com.example.petshop.entity.User;
import com.example.petshop.entity.DTO.UserDTO;
import com.example.petshop.entity.DTO.updateUserDTO;
import com.example.petshop.service.AuthorityService;
import com.example.petshop.service.RoleService;
import com.example.petshop.service.UserService;

import jakarta.transaction.Transactional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class RestUserController {
    @Autowired
    UserService service;
    @Autowired
    RoleService roleService;
    @Autowired
    AuthorityService authorityService;
    MailerService mailerService;

    //Tìm danh sách người dùng thông qua dto
    @GetMapping("/getall")
    public List<updateUserDTO> getAll() {
        List<User> users = service.findAll();
        List<updateUserDTO> dtos = users.stream().map(user -> {
            updateUserDTO dto = new updateUserDTO();
            dto.setUserName(user.getUsername());
            dto.setFullName(user.getFullName());
            dto.setPhoneNumber(user.getPhoneNumber());
            dto.setActiveToken(user.getActiveToken());
            dto.setOldPassword(user.getUserPassword());
            dto.setEmail(user.getEmail());
            dto.setUserAddress(user.getUserAddress());
//	        dto.setEnable(user.getEnable());
//	        dto.setDateCreated(user.getDateCreated());
//
//	        // Chuyển Set<Authority> thành một chuỗi các authority
//	        Set<Authority> authorities = user.getAuthorities();
//	        if (authorities != null && !authorities.isEmpty()) {
//	            String authIDs = authorities.stream()
//	                                        .map(authority -> authority.getAuthority())  // Lấy tên quyền từ role
//	                                        .collect(Collectors.joining(", "));  // Nối các quyền bằng dấu phẩy
//	            dto.setAuthID(authIDs);  // Cập nhật danh sách quyền vào DTO
//	        }

            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

    //Hàm tìm người dùng thông qua active token
    @GetMapping("/getByToken/{token}")
    public UserDTO getByToken(@PathVariable String token) {
        // Tìm user theo token
        User user = service.findByToken(token);

        // Chuyển đổi đối tượng User sang UserDTO
        UserDTO dto = new UserDTO();
        dto.setUserName(user.getUsername());
        dto.setActiveToken(user.getActiveToken());
        return dto;
    }

    //Đăng ký
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        try {
            // Kiểm tra xem tên đăng nhập đã tồn tại chưa
            if (service.existedByUsername(user.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("{\"success\": false, \"message\": \"Tên đăng nhập đã tồn tại\"}");
            }

            // Mã hóa mật khẩu người dùng
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            mailerService = new MailerService();

            // Tạo mã thông báo và thiết lập thông tin người dùng
            UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString();
            user.setActiveToken(uuidString);
            user.setUserPassword(encodedPassword);
            user.setDateCreated(Instant.now());

            // Gán vai trò cho người dùng
            Role role = roleService.findById("USER");
            if (role == null) {
                throw new RuntimeException("Role not found!");
            }

            // Tạo quyền và gán cho người dùng
            Authority authority = new Authority();
            authority.setRole(role);
            authority.setUserName(user);
            user.getAuthorities().add(authority);

            // Lưu người dùng và quyền
            User newUser = service.create(user);
            Authority newAuthority = authorityService.create(authority);

            // Chuyển đổi người dùng mới thành UserDTO
            UserDTO newUserDto = new UserDTO(newUser);

            // Gửi email xác nhận
            mailerService.sendEmail(
                    user.getEmail(),
                    "Pet Shop",
                    "Confirm your email",
                    user.getFullName(),
                    user.getActiveToken()
            );

            // Trả về người dùng mới với mã trạng thái HTTP 201 (Created)
            return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);

        } catch (Exception e) {
            // Ghi lại lỗi và trả về trạng thái 400 Bad Request
            System.err.println("Error during registration: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //Đường dẫn kích hoạt tài khoản
    @PutMapping("/confirmation")
    public ResponseEntity<?> Confirmation(@RequestParam("confirmation_token") String confirmation_token) {
        // Tìm user theo token
        User user = service.findByToken(confirmation_token);

        // Nếu không tìm thấy user, trả về lỗi
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"success\": false, \"message\": \"User không tồn tại hoặc token không hợp lệ\"}");
        }

        // Kiểm tra xem tài khoản đã được kích hoạt chưa
        if (user.getEnable()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"success\": false, \"message\": \"Tài khoản đã được kích hoạt trước đó\"}");
        }

        // Kích hoạt tài khoản
        user.setEnable(true);
        service.update(user);

        // Chuyển đổi đối tượng User sang UserDTO để trả về thông tin người dùng
        UserDTO dto = new UserDTO();
        dto.setEnable(user.getEnable());
        dto.setActiveToken(user.getActiveToken());

        // Trả về ResponseEntity với mã trạng thái OK
        return ResponseEntity.ok(dto);
    }

    //Gửi mail đường dẫn đổi mật khẩu
    @GetMapping("/forgot-password/{username}")
    public ResponseEntity<Object> sendConfirmPassword(@PathVariable String username) {
        if (!service.existedByUsername(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"success\": false, \"message\": \"Tài khoản không tồn tại\"}");
        }

        User user = service.findByUsername(username);
        System.out.println(user.getActiveToken());
        mailerService = new MailerService();
        mailerService.confirmChangePassword(user.getEmail(), "Ninja Pet", "Thư xác nhận đổi mật khẩu", username, user.getActiveToken());
        return ResponseEntity.ok("{\"success\": true, \"message\": \"Thư xác nhận đã được gửi đến email của bạn.\"}");
    }

    //Đổi mật khẩu mới
    @PutMapping("/change-password/{username}")
    public ResponseEntity<Object> changePassword(@RequestBody updateUserDTO dto, @PathVariable String username){
        if(!service.existedByUsername(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"success\": false, \"message\": \"Tài khoản không tồn tại\"}");
        }
        User user = service.findByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = bCryptPasswordEncoder.encode(dto.getNewPassword());
        user.setUserPassword(passwordEncode);
        service.update(user);
        return ResponseEntity.ok("{\"success\": true, \"message\": \"Đổi mật khẩu thành công\"}");

    }

    //new pass dto
    @GetMapping("/new-password/{userName}")
    public ResponseEntity<Object> forgotPassword(@PathVariable String userName, @RequestParam("token") String token){
        if(!service.existedByUsername(userName) || service.findByToken(token) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"success\": false, \"message\": \"Tài khoản không tồn tại\"}");
        }
        User user  = service.findByUsername(userName);
        updateUserDTO dto = new updateUserDTO();
        dto.setUserName(userName);
        dto.setOldPassword(user.getUserPassword());
        dto.setActiveToken(token);
        return ResponseEntity.ok(dto);
    }

    //Cập nhật thông tin cá nhân
    @PutMapping("/update/{username}")
    public ResponseEntity<Object> update(@RequestBody updateUserDTO userDTO, @PathVariable String username) {
        if (!service.existedByUsername(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"success\": false, \"message\": \"Tài khoản không tồn tại\"}");
        }

        // Lấy thông tin người dùng hiện tại
        User existingUser = service.findByUsername(username);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"success\": false, \"message\": \"Tài khoản không tồn tại\"}");
        }

        // Cập nhật thông tin
        existingUser.setFullName(userDTO.getFullName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setUserAddress(userDTO.getUserAddress());

        // Lưu lại thông tin đã cập nhật
        service.update(existingUser);

        return ResponseEntity.ok("{\"success\": true, \"message\": \"Cập nhật thành công\"}");
    }
}