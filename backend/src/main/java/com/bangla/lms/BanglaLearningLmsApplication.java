package com.bangla.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bangla.lms.repository")
@EntityScan(basePackages = "com.bangla.lms.entity")
public class BanglaLearningLmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanglaLearningLmsApplication.class, args);
    }
}
