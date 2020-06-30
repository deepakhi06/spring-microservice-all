package com.ds.springconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConsumerApplication.class, args);
	}

}
