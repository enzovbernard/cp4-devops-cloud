package br.com.fiap.clyvo_java.repository.swagger;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.clyvo_java.model.swagger.SwaggerUserModel;

public interface SwaggerUserRepository extends JpaRepository<SwaggerUserModel, Long> {
	
	Optional<SwaggerUserModel> findByUsername(String username);

}