package com.project.opportufind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.project.repositories")

@SpringBootApplication
@EntityScan(basePackages = "com.project.entities")
@ComponentScan(basePackages = "com.project")
public class OpportufindApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpportufindApplication.class, args);
	}

}
