package com.hardforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hardforum.models.User;

@EnableAutoConfiguration
@SpringBootApplication
public class HardForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardForumApplication.class, args);
		
	}

}
