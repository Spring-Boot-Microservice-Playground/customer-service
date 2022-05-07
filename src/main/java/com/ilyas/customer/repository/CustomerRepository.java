package com.ilyas.customer.repository;

import java.util.List;

import com.ilyas.customer.model.Customer;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    @Query(value = "{name: {$regex: /?0*/, $options: 'si'}}")
    public Slice<Customer> findByNameLike(String name, Pageable pageable);
    List<Customer> findByName(String name);
}
