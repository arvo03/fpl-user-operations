package com.fpl.operations.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class DemoJarApplication {
//public class DemoJarApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(DemoJarApplication.class, args);
	}	
}
