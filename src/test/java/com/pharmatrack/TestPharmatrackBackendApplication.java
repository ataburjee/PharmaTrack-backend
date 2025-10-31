package com.pharmatrack;

import org.springframework.boot.SpringApplication;

public class TestPharmatrackBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(PharmatrackBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
