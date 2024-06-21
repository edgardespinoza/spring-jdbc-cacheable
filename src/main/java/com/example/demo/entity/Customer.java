package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Set;

public record Customer(@Id Integer id, String name, Profile profile, Set<Order> orders) implements Serializable {
}
