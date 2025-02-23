package com.patton.pkg.chms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.patton.pkg.chms.auth.YAMLConfig;

@SpringBootApplication
@EnableScheduling
public class ChmsApplication implements CommandLineRunner {

	@Autowired
	YAMLConfig config;

	public static void main(String[] args) {
		SpringApplication.run(ChmsApplication.class, args);
	}

	public void run(String... args) throws Exception {
		System.out.println("using environment: " + config.getJwtsecret());
	}
}
