package com.ilyas.customer.repository;

import java.util.List;

import com.ilyas.customer.model.Customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    @Query(value = "{name: {$regex: /?0/, $options: 'i'}}")
    List<Customer> findByNameLike(String name);
    List<Customer> findByName(String name);
}
