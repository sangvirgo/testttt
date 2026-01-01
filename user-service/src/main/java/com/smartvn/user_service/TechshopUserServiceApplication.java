package com.smartvn.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;

@EntityListeners(AuditingEntityListener.class)
@SpringBootApplication
@EnableDiscoveryClient
public class TechshopUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechshopUserServiceApplication.class, args);
	}

}
