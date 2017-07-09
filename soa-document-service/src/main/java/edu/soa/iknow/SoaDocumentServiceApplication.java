package edu.soa.iknow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SoaDocumentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoaDocumentServiceApplication.class, args);
	}
}
