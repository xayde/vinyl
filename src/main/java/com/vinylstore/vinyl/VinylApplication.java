package com.vinylstore.vinyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VinylApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinylApplication.class, args);
	}

}
