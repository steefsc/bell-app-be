package com.sheensoft.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.sheensoft"})
@EntityScan(basePackages = {"com.sheensoft"})
@ComponentScan(basePackages = {"com.sheensoft"})
public class MainApp {
    public static void main(String[] args){
        SpringApplication.run(MainApp.class);
    }
}
