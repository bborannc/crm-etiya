package com.boran.crm.domain.service;

import com.boran.crm.domain.entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Customer save(Customer customer);
    Optional<Customer> findById(UUID id);
    List<Customer> findAll();
    Customer update(Customer customer);
    void delete(UUID id);

}
