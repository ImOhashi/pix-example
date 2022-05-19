package com.ohashi.pixexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class PixExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PixExampleApplication.class, args);
	}

}
