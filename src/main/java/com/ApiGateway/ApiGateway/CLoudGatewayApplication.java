package com.ApiGateway.ApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CLoudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CLoudGatewayApplication.class, args);
	}

}
