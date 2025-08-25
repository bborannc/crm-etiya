package com.boran.crm.domain.service.concretes;

import com.boran.crm.domain.entity.Customer;
import com.boran.crm.domain.repository.CustomerRepository;
import com.boran.crm.domain.service.abstracts.CustomerService;
import com.boran.crm.domain.web.dto.request.CustomerRequest;
import com.boran.crm.domain.web.dto.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = Customer.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .phone(request.getPhone())
                .isActive(true) // default değer
                .build();

        // Veritabanına kaydet
        Customer savedCustomer = customerRepository.save(customer);

        // Entity → Response DTO dönüşümü
        return CustomerResponse.builder()
                .id(savedCustomer.getId())
                .username(savedCustomer.getUsername())
                .email(savedCustomer.getEmail())
                .phone(savedCustomer.getPhone())
                .isActive(savedCustomer.getIsActive())
                .createdAt(savedCustomer.getCreatedAt())
                .build();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer not found"));

        // Güncellenebilir alanları set et
        customer.setUsername(request.getUsername());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());


        Customer  updatedCustomer = customerRepository.save(customer);
        return CustomerResponse.builder()
                .id(updatedCustomer.getId())
                .username(updatedCustomer.getUsername())
                .email(updatedCustomer.getEmail())
                .phone(updatedCustomer.getPhone())
                .createdAt(updatedCustomer.getCreatedAt())
                .isActive(updatedCustomer.getIsActive())
                .build();
    }

    @Override
    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return CustomerResponse.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .createdAt(customer.getCreatedAt())
                .isActive(customer.getIsActive())
                .build();
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customer -> CustomerResponse.builder()
                        .id(customer.getId())
                .username(customer.getUsername())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .isActive(customer.getIsActive())
                .createdAt(customer.getCreatedAt())
                .build()
                )
                .toList();
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
