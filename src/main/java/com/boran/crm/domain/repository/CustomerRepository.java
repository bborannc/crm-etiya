package com.boran.crm.domain.repository;

import com.boran.crm.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //List<Customer> findByid(Long id);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUsername(String username);
}
