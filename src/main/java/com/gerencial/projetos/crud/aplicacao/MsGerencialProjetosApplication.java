package com.gerencial.projetos.crud.aplicacao;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
public class MsGerencialProjetosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGerencialProjetosApplication.class, args);
	}

}
