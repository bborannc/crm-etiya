package com.boran.crm.domain.service.abstracts;

import com.boran.crm.domain.entity.Customer;
import com.boran.crm.domain.web.dto.request.CustomerRequest;
import com.boran.crm.domain.web.dto.response.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerResponse create(CustomerRequest request);
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    Customer update(Customer customer);
    CustomerResponse update(Long id, CustomerRequest request);
    CustomerResponse getById(Long id);
    List<CustomerResponse> getAll();
    void delete(Long id);

}
