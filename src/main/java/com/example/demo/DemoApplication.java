package com.example.demo;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Profile;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

import java.util.Set;
@EnableCaching
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    ConcurrentMapCacheManager concurrentMapCacheManager (){
        var cache = new ConcurrentMapCacheManager();
        cache.setAllowNullValues(true);
        cache.setStoreByValue(true);
        return cache;
    }

    @Bean
    ApplicationRunner demo(CustomerRepository repository) {


        return args -> {
            var orders = Set.of(new Order(null, "A-order"),
                    new Order(null, "B-order")
            );
            var customer = repository.save(new Customer(null, "A",
                    new Profile(null, "user", "pass"),
                    orders
            ));

            //repository.findAll().forEach(System.out::println);
            System.out.println("getting hopefully cached version (1)");
            repository.findById(customer.id()).get();

            System.out.println("getting hopefully cached version (2)");
            var result = repository.findById(customer.id()).get();

            Assert.state(result != customer, "the two references should not be the same");

        };
    }
}

