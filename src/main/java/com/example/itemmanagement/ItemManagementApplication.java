package com.example.itemmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // start and configure the application
//In spring boot, the components are -all classes whichever classes annotated with @serive, @RestController, @component
//when springboot app runs, it starts scans all the main class subclass pkg's which are annotated with above annotations and register them as beans(objects) into spring container
public class ItemManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemManagementApplication.class, args);
    }

}
