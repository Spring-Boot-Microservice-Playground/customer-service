package com.ilyas.customer.controller;

import com.ilyas.customer.model.Customer;
import com.ilyas.customer.repository.CustomerRepository;
import com.ilyas.customer.service.CustomerService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    
    private CustomerService customerService;
    private CustomerRepository customerRepository;
    
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> findAllCustomersByNameLike(@RequestParam(defaultValue = "") String name){
        List<Customer> result = Arrays.asList();
        try {
            if(name.length() == 0){
                result = customerRepository.findAll();
            } else {
                result = customerRepository.findByNameLike(name);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        try {
            Customer result = customerService.creatCustomer(customer);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
