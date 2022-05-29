package br.com.felipemaxplay.pdcommerce.pdGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PdGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdGatewayApplication.class, args);
	}

}
