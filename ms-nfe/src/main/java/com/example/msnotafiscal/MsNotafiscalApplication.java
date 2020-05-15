package com.example.msnotafiscal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsNotafiscalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNotafiscalApplication.class, args);
	}

}
