package com.boran.crm.domain.service.concretes;


import com.boran.crm.domain.entity.Customer;
import com.boran.crm.domain.repository.CustomerRepository;
import com.boran.crm.domain.service.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
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
    public void delete(Long id){
        customerRepository.deleteById(id);
    }


}
