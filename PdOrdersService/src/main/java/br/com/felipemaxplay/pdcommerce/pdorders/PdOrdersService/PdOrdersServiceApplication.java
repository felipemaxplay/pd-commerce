package br.com.felipemaxplay.pdcommerce.pdorders.PdOrdersService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PdOrdersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdOrdersServiceApplication.class, args);
	}

}
