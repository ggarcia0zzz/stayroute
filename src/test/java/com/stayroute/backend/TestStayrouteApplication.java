package com.stayroute.backend;

import org.springframework.boot.SpringApplication;

public class TestStayrouteApplication {

	public static void main(String[] args) {
		SpringApplication.from(StayrouteApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
