package br.com.fiap.clyvo_java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class SwaggerConfiguration {
	
	final String TIPO_AUTENTICACAO = "bearerAuth";
	
	@Bean
	OpenAPI configurarSwagger() {
		return new OpenAPI()
				
				.addSecurityItem(new SecurityRequirement().addList(TIPO_AUTENTICACAO))
				
				.components(new Components()
						.addSecuritySchemes(TIPO_AUTENTICACAO, 
								new SecurityScheme()
								.name(TIPO_AUTENTICACAO)
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")))
				
				
				.info(new Info()
				.title("Projeto API REST para Clyvo - FIAP 2026")
				.description("Este projeto tem com objetivo realizar a gestão de dados com o intuito de auxiliar o cuidado de animais domésticos")
				.summary("Projeto API REST Clyvo")
				.termsOfService("Texto de Termos de Serviço")
				.version("1.0.0")
				.license(new License().url("/licenses")
						.name("Tela de Aquisição de Planos de Uso"))
				);
	}

}
