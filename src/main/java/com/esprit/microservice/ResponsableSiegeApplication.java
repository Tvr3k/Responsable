package com.esprit.microservice;

import java.util.stream.Stream;

import com.esprit.microservice.Entity.ERole;
import com.esprit.microservice.Entity.Role;
import com.esprit.microservice.Repository.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.esprit.microservice.Entity.ResponsableSiege;
import com.esprit.microservice.Repository.ResponsableSiegeRepository;

@SpringBootApplication
@EnableEurekaClient
public class ResponsableSiegeApplication {

	public static void main(String[] args) {


		ConfigurableApplicationContext context = SpringApplication.run(ResponsableSiegeApplication.class, args);

		RoleRepository roleRepository = context.getBean(RoleRepository.class);



		for (ERole d : ERole.values()) {


			if (roleRepository.findByName(d).isEmpty()) {
				Role role = new Role();
				role.setName(d);
				roleRepository.save(role);


			}


		}
	}


}
