package com.ilyas.customer.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@Document
public class Customer {
    @Id
    private String id;
    private String name;
    private String address;
    private LocalDateTime created_at;
}
