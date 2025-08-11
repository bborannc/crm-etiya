package com.boran.crm.domain.web.dto;

import lombok.Data;

@Data
public class CustomerRequest {
    private String username;
    private String email;
    private String phone;
}
