package com.boran.crm.domain.web.dto;

import com.boran.crm.domain.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    private Role role;
}
