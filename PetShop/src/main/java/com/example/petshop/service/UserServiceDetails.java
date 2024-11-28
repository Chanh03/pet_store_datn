package com.example.petshop.service;

import com.example.petshop.config.CustomUserDetails;
import com.example.petshop.entity.Authority;
import com.example.petshop.entity.Role;
import com.example.petshop.entity.User;

import io.ipinfo.api.IPinfo;
import io.ipinfo.api.model.IPResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceDetails implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userService.findById(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    @Transactional
    // Phương thức để lưu trữ người dùng
    public boolean saveUser(OAuth2User oAuth2User) {
        System.out.println(oAuth2User.getAttributes());
        // Lấy thông tin người dùng từ OAuth2User
        String email = oAuth2User.getAttribute("email");
        String id = oAuth2User.getAttribute("sub");
        String name = oAuth2User.getAttribute("name");
        if (id == null) {
            id = oAuth2User.getAttribute("id");
        }

        // Kiểm tra sự tồn tại của người dùng theo email
        if (userService.existedByEmail(email) && userService.existedByUsername(id)) {
            return true;
        }

        if (userService.existedByUsername(id)) {
            User user = userService.findByUsername(id);
            if (user == null) {
                // Nếu người dùng không tồn tại, trả về false
                return false;
            }

            if (!user.getEnable()) {
                // Nếu tài khoản người dùng không kích hoạt, trả về false
                return false;
            }
        }
        if (!userService.existedByUsername(id)) {

            User user = new User();
            user.setUserName(id); // hoặc có thể sử dụng email
            user.setEmail(email);

            // Mã hóa mật khẩu
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = bCryptPasswordEncoder.encode(id + name);
            user.setFullName(name); // Đặt tên đầy đủ từ thông tin

            // Tạo quyền và gán cho người dùng
            Authority authority = new Authority();
            Role role = roleService.findById("USER");
            authority.setRole(role);
            authority.setUserName(user); // Thiết lập người dùng cho quyền

            user.setUserPassword(encodedPassword);
            user.getAuthorities().add(authority);

            user.setPhoneNumber("None"); // Hoặc có thể để trống nếu không cần
            user.setEnable(true);
            user.setDateCreated(LocalDateTime.now());
            try {
                URL url = new URL("https://api.ipify.org?format=text");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                String inInfoToken = "2f51d24fddf1e1";
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String publicIp = in.readLine();
                in.close();

                IPinfo ipInfo = new IPinfo.Builder()
                        .setToken(inInfoToken)
                        .build();
                IPResponse response = ipInfo.lookupIP(publicIp);
                System.out.println(response.getCity());
                user.setUserAddress(response.getCity() + ", " + response.getRegion());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Tạo token duy nhất cho người dùng
            user.setActiveToken(UUID.randomUUID().toString());
            System.out.println("Lưu thành công user: " + user.getUserAddress());
            System.out.println("Lưu thành công user: " + user.getPhoneNumber());
            try {
                userService.create(user);
                authorityService.create(authority); // Lưu quyền
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Lỗi khi lưu người dùng và quyền vào cơ sở dữ liệu.");
                throw new RuntimeException("Error while saving user and authority data: " + e.getMessage());
            }
            // Lưu người dùng và quyền vào cơ sở dữ liệu

            return true;
        }
        return true;
    }

}