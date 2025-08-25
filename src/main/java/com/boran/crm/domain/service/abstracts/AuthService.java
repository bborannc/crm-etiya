package com.boran.crm.domain.service.abstracts;


import com.boran.crm.domain.entity.Role;
import com.boran.crm.domain.entity.User;
import com.boran.crm.domain.repository.UserRepository;
import com.boran.crm.domain.web.dto.response.AuthResponse;
import com.boran.crm.domain.web.dto.request.LoginRequest;
import com.boran.crm.domain.web.dto.request.RegisterRequest;
import com.boran.crm.exception.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Bu e-posta adresi zaten kullanılıyor."+request.getEmail());
        }



        User user = new User();
        user.setUsername(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);


        return "kullanıcı başarıyla oluşturuldu";
    }

    public AuthResponse login(LoginRequest request) {
        // Kullanıcı kimlik doğrulama işlemi
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        // Spring Security User nesnesini doğru şekilde alıyoruz
        UserDetails springUser = (UserDetails) auth.getPrincipal();
        // Eğer kendi User nesnenize ihtiyacınız varsa, veritabanından çekebilirsiniz
        // User user = userRepository.findByEmail(springUser.getUsername());
        // JWT token oluşturma
        String token = jwtService.generateToken(springUser);
        // Yanıt oluşturma ve dönme
        return new AuthResponse(token);
    }
}


