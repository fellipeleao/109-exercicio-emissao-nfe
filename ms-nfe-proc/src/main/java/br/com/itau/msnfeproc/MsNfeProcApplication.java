package br.com.itau.msnfeproc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsNfeProcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNfeProcApplication.class, args);
	}

}
