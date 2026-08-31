package br.com.fiap.clyvo_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@ComponentScan
@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class ClyvoJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClyvoJavaApplication.class, args);
	}
}
