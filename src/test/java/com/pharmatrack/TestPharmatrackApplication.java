package com.pharmatrack;

import org.springframework.boot.SpringApplication;

public class TestPharmatrackApplication {

	public static void main(String[] args) {
		SpringApplication.from(PharmatrackApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
