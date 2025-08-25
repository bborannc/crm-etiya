package com.boran.crm.domain.web.mapper;

import com.boran.crm.domain.entity.Customer;
import com.boran.crm.domain.web.dto.request.CustomerRequest;
import com.boran.crm.domain.web.dto.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {


    Customer toEntity(CustomerRequest request);


    CustomerResponse toResponse(Customer customer);

    List<CustomerResponse> toResponseList(List<Customer> customers);


    void update(@MappingTarget Customer entity, CustomerRequest request);
}
