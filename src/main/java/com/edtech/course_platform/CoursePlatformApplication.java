package com.edtech.course_platform;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Base64;

@SpringBootApplication
public class CoursePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursePlatformApplication.class, args);
	}

}
