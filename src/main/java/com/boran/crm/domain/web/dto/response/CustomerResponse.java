package com.boran.crm.domain.web.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomerResponse {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private boolean isActive;
    private LocalDateTime createdAt;
}
