package com.mauriciomiranda.projeto_vagas_job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
// Swagger
@OpenAPIDefinition(info = @Info(title = "Gestão de vagas", description = "API responsável pela gestão de vagas", version = "1.0"))
public class ProjetoVagasJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoVagasJobApplication.class, args);
	}
}
