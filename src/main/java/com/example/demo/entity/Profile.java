package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("customer_profiles")
public record Profile(@Id Integer id, String username, String password) implements Serializable {
}
