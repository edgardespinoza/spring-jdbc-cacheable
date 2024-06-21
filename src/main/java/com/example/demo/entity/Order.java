package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("customer_orders")
public record Order(@Id Integer id, String name) implements Serializable {
}
