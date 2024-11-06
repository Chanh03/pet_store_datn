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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
            LocalDateTime localDate = user.getDateCreated().atZone(ZoneId.systemDefault()).toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dto.setDateCreate(localDate.format(formatter));
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
  //Hàm tìm người dùng thông qua active token
    @GetMapping("/information/{username}")
    public updateUserDTO getByUsername(@PathVariable String username) {
        // Tìm user theo getUserPrincipal
        User user = service.findByUsername(username);
        LocalDateTime localDate = user.getDateCreated().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Chuyển đổi đối tượng User sang UserDTO
        updateUserDTO dto = new updateUserDTO();
        dto.setUserName(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setDateCreate(localDate.format(formatter));
        dto.setUserAddress(user.getUserAddress());
        dto.setOldPassword(user.getUserPassword());
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
            if (service.existedByEmail(user.getEmail()) && user.getUsername().matches("\\d+")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("{\"success\": false, \"message\": \"Email đã được sử dụng cho một tài khoản khác\"}");
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
            
            user.setDateCreated(LocalDateTime.now());

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
            boolean isEmailSent =
            mailerService.sendEmail(
                    user.getEmail(),
                    "Pet Shop",
                    "Confirm your email",
                    user.getFullName(),
                    user.getActiveToken()
            );
            if(!isEmailSent) {
            	return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("{\"success\": false, \"message\": \"Gửi Mail không thành công vui lòng kiểm tra lại\"}");
            }
            
            
            User newUser = service.create(user);
            Authority newAuthority = authorityService.create(authority);

            // Chuyển đổi người dùng mới thành UserDTO
            UserDTO newUserDto = new UserDTO(newUser);

            // Gửi email xác nhận
            

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

    @PutMapping("/forgot-password/{email}")
    public ResponseEntity<Object> sendConfirmPassword(@PathVariable String email) {
        // Kiểm tra xem email có tồn tại không
        if (!service.existedByEmail(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("{\"success\": false, \"message\": \"Email không tồn tại!\"}");
        }

        // Lấy danh sách người dùng theo email
        List<User> listUser = service.findByEmail(email);
        mailerService = new MailerService(); // Khởi tạo mailerService một lần

        boolean validUserFound = false; // Biến để theo dõi xem có người dùng hợp lệ hay không

        for (User user : listUser) {
            // Kiểm tra xem username có chứa ký tự số hay không
            if (!user.getUsername().matches("\\d+")) { // Kiểm tra nếu username không chỉ chứa ký tự số
                validUserFound = true; // Đánh dấu là có người dùng hợp lệ
                
                // Tạo UUID và thiết lập thời gian hết hạn
                UUID uuid = UUID.randomUUID();
                user.setTemporaryGUID(uuid.toString());
                user.setTempGuidExpir(LocalDateTime.now().plusMinutes(10)); // UUID có hiệu lực trong 10 phút

                // Gửi email
                boolean isSent = mailerService.confirmChangePassword(
                    user.getEmail(), 
                    "Ninja Pet", 
                    "Thư xác nhận đổi mật khẩu", 
                    user.getUsername(), 
                    user.getTemporaryGUID()
                );

                if (!isSent) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                         .body("{\"success\": false, \"message\": \"Gửi mail không thành công.\"}");
                }

                // Cập nhật thông tin người dùng
                service.update(user);
                return ResponseEntity.ok("{\"success\": true, \"message\": \"Thư xác nhận đã được gửi đến email của bạn.\"}");
            }
        }

        if (!validUserFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("{\"success\": false, \"message\": \"Không có người dùng hợp lệ với email này!\"}");
        }

        return ResponseEntity.ok("{\"success\": false, \"message\": \"Không có người dùng hợp lệ với email này!\"}");
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
        if(!bCryptPasswordEncoder.matches(dto.getOldPassword(), user.getUserPassword())) {
        	if(!dto.getOldPassword().equals(user.getUserPassword()) ) {
        		return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"success\": false, \"message\": \"Mật khẩu hiện tại của bạn không chính xác\"}");
        	}
        }
        String passwordEncode = bCryptPasswordEncoder.encode(dto.getNewPassword());
        user.setUserPassword(passwordEncode);
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        user.setTemporaryGUID(uuidString);
        service.update(user);
        return ResponseEntity.ok("{\"success\": true, \"message\": \"Đổi mật khẩu thành công\"}");

    }

    //new pass dto
    @GetMapping("/new-password/{userName}")
    public ResponseEntity<Object> forgotPassword(@PathVariable String userName, @RequestParam("token") String token){
        if(!service.existedByUsername(userName) || !service.existedByTempToken(token)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"success\": false, \"message\": \"Đường dẫn không còn khả dụng với bạn\"}");
        }
        Instant now = Instant.now();
        User user  = service.findByUsername(userName);
        LocalDateTime nowLocal = LocalDateTime.now();
        LocalDateTime dateCreated = user.getTempGuidExpir();
        Instant dateCreatedInstant = dateCreated.atZone(ZoneId.systemDefault()).toInstant();
        LocalDateTime time2Local = dateCreatedInstant.atZone(ZoneId.of("UTC")).toLocalDateTime();
        updateUserDTO dto = new updateUserDTO();
        if (time2Local.isBefore(nowLocal.minusSeconds(600))) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"success\": false, \"message\": \"Đường dẫn của bạn đã hết hạn!\"}");
        }
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