package org.example.myecommerceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.example.myecommerceapp.model")
public class MyEcommerceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyEcommerceAppApplication.class, args);
    }

}
