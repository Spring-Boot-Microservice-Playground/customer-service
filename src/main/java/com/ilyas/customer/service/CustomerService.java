package com.ilyas.customer.service;

import java.util.List;

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
    
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Slice<Customer> findAllCustomersByNameLike(String name, int pageSize) throws Exception {
        Pageable pageable = PageRequest.ofSize(pageSize);
        Slice<Customer> result = customerRepository.findByNameLike(name, pageable);
        return result;
    }

    public Customer creatCustomer(Customer customer) throws Exception {
        List<Customer> existingCustomers = customerRepository.findByName(customer.getName());
        if(existingCustomers.isEmpty()){
            Customer result = customerRepository.save(customer);
            return result;
        } else {
            return null;
        }
    }
}
