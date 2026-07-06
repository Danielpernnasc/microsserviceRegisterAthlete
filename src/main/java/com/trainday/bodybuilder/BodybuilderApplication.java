package com.trainday.bodybuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BodybuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BodybuilderApplication.class, args);
	}

}
