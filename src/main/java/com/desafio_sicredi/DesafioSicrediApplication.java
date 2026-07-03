package com.desafio_sicredi;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients
@SpringBootApplication
public class DesafioSicrediApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSicrediApplication.class, args);
	}

}
