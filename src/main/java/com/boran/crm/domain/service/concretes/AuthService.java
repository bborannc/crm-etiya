package com.boran.crm.domain.service.concretes;


import com.boran.crm.domain.entity.Role;
import com.boran.crm.domain.entity.User;
import com.boran.crm.domain.repository.UserRepository;
import com.boran.crm.domain.service.abstracts.JwtService;
import com.boran.crm.domain.web.dto.AuthResponse;
import com.boran.crm.domain.web.dto.LoginRequest;
import com.boran.crm.domain.web.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }


}
