package com.example;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EnterpriseCrudApplication {
    public static void main(String[] a) {
        SpringApplication.run(EnterpriseCrudApplication.class, a);
    }
}