package com.boran.crm.domain.service.abstracts;

import com.boran.crm.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    Customer update(Customer customer);
    void delete(Long id);

}
