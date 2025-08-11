package com.boran.crm.domain.web.mapper;

import com.boran.crm.domain.entity.Customer;
import com.boran.crm.domain.web.dto.CustomerRequest;
import com.boran.crm.domain.web.dto.CustomerResponse;

public class CustomerMapper {
    //Request -> Entity
    public static Customer toEntity(CustomerRequest request){
        if(request == null){
            return null;
        }
        return Customer.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }
    public static CustomerResponse toResponse(Customer customer){
        if (customer ==null){
            return null;
        }
        return CustomerResponse.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .isActive(customer.isActive())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}
