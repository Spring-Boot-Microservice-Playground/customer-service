package com.ilyas.customer.service;

import com.ilyas.customer.model.Customer;
import com.ilyas.customer.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    private CustomerRepository customerRepository;
    // private RestTemplate restTemplate;
    
    @Autowired
    // public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        // this.restTemplate = restTemplate;
    }

    public Slice<Customer> findAllCustomersByNameLike(String name, int pageSize) throws Exception {
        Pageable pageable = PageRequest.ofSize(pageSize);
        Slice<Customer> result = customerRepository.findByNameLike(name, pageable);
        return result;
    }

    public Customer creatCustomer(Customer customer) throws Exception {
        Customer result = customerRepository.save(customer);
        return result;
    }
}
