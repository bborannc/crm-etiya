package com.boran.crm.controller;

import com.boran.crm.domain.entity.User;
import com.boran.crm.domain.service.abstracts.UserService;
import com.boran.crm.domain.web.dto.request.UserRequest;
import com.boran.crm.domain.web.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    public UserResponse createUser(@RequestBody UserRequest request){
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .role(request.getRole())
                .build();

        User savedUser = userService.save(user);
        return toResponse(savedUser);
    }
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id,@RequestBody UserRequest request){
        User user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());

        return toResponse(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(@AuthenticationPrincipal UserDetails principal) {
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }
        return userService.findByEmail(principal.getUsername())
                .map(user -> ResponseEntity.ok(toResponse(user)))
                .orElse(ResponseEntity.status(404).build());
    }
    private UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

}
