package com.pismo.corebankingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CoreBankingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreBankingApiApplication.class, args);
    }

}
