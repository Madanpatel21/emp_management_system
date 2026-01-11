package com.employ_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("entity")
@ComponentScan({
    "Controller",
    "Dto",
    "Service",
    "Repository",
    "Entity",
    "Exception",
})
@EnableJpaRepositories(basePackages = "Repository")
public class EmployManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployManagementApplication.class, args);
	}

}
