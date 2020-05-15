package br.com.itau.mslog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class MsLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsLogApplication.class, args);
	}

}
