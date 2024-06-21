package com.example.demo;

import com.example.demo.entity.Customer;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface CustomerRepository extends ListCrudRepository<Customer,Integer> {

    String CUSTOMER_CACHE_KEY = "customers";

    @Override
    @Cacheable(CUSTOMER_CACHE_KEY)
    Optional<Customer> findById(Integer id);

    Collection<Customer> findByName(String name);


    @Override
    @CacheEvict(value = CUSTOMER_CACHE_KEY, key = "#result.id")
    <S extends Customer> S save (S entity);

}
