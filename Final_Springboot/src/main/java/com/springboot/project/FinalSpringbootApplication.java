package com.springboot.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class FinalSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalSpringbootApplication.class, args);
	}
}
