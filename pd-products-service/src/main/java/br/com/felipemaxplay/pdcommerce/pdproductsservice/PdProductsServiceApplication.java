package br.com.felipemaxplay.pdcommerce.pdproductsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PdProductsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdProductsServiceApplication.class, args);
	}

}
