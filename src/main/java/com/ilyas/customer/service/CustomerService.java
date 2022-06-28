package com.ilyas.customer.service;

import com.ilyas.customer.model.Customer;
import com.ilyas.customer.repository.CustomerRepository;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    private CustomerRepository customerRepository;
    // private RestTemplate restTemplate;
    
    // public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        // this.restTemplate = restTemplate;
    }

    public Customer creatCustomer(Customer customer) throws Exception {
        List<Customer> customerExist = customerRepository.findByName(customer.getName());
        if (customerExist.size() > 0) {
            throw new Exception("customer already exist!");
        }
        Customer result = customerRepository.save(customer);
        return result;
    }
}
