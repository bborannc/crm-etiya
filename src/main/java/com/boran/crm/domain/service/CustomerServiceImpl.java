package com.boran.crm.domain.service;


import com.boran.crm.domain.entity.Customer;
import com.boran.crm.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public void delete(UUID id){
        customerRepository.deleteById(id);
    }


}
