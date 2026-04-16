package com.example.esport_clash;

import org.springframework.boot.SpringApplication;

public class TestEsportClashApplication {

	public static void main(String[] args) {
		SpringApplication.from(EsportClashApplication::main).with(PostgreSQLTestConfiguration.class).run(args);
	}

}
