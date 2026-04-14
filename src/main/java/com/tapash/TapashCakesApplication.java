package com.tapash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TapashCakesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TapashCakesApplication.class, args);
	}

}
