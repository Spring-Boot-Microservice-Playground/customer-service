package com.ilyas.customer.controller;

import com.ilyas.customer.model.Customer;
import com.ilyas.customer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    private CustomerService customerService;
    
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/search")
    public ResponseEntity<Slice<Customer>> findAllCustomersByNameLike(
        @RequestParam String name,
        @RequestParam(defaultValue = "5") int pageSize
    ){
        try {
            Slice<Customer> result = customerService.findAllCustomersByNameLike(name, pageSize);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> createNewCustomer(@RequestBody Customer customer){
        try {
            Customer result = customerService.creatCustomer(customer);
            if(result != null){
                return ResponseEntity.ok("Customer Created");
            }
            return ResponseEntity.badRequest().body("Customer Already Exist");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
}
