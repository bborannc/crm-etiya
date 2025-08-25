package com.boran.crm.domain.web.dto.request;

import com.boran.crm.domain.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String email;
    private String password;
    private String username;
    private Role role;
}
