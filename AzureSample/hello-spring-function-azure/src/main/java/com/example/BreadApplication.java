package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Derived from these instructions
// https://docs.microsoft.com/en-us/samples/azure-samples/hello-spring-function-azure/hello-spring-function-azure/
@SpringBootApplication
public class BreadApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BreadApplication.class, args);
    }
}
