package br.com.fiap.clyvo_java.control.swagger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.clyvo_java.model.swagger.SwaggerUserModel;
import br.com.fiap.clyvo_java.repository.swagger.SwaggerUserRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuarios")
public class UsuarioSwaggerController {

	@Autowired
	private SwaggerUserRepository repU;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Operation(description = "Este endpoint retorna todos os usuários com acesso a API",
			summary = "Visualizar usuários",
			tags = "Autenticação CRUD")
	@GetMapping("/todos")
	public List<SwaggerUserModel> listarTodos(){
		return repU.findAll();
	}
	
	@Operation(description = "Este endpoint usuários com acesso a API pelo ID",
			summary = "Visualizar usuários por ID",
			tags = "Autenticação CRUD")
	@GetMapping("/{id}")
	public SwaggerUserModel buscarPorId(@PathVariable Long id) {
		SwaggerUserModel usuario = repU.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
		
		return usuario;
	}
	
	@Operation(description = "Este endpoint permite a criação de novos usuários com acesso a API",
			summary = "Criar usuários",
			tags = "Autenticação CRUD")
	@PostMapping("/novo")
	public SwaggerUserModel inserirUsuario(@RequestBody SwaggerUserModel usuario) {
		
		usuario.setSenha( encoder.encode(usuario.getSenha()));
		repU.save(usuario);
		return usuario;	
		
	}
	
	@Operation(description = "Este endpoint exclui usuários com acesso a API",
			summary = "Remover usuários",
			tags = "Autenticação CRUD")
	@DeleteMapping("/remover/{id}")
	public SwaggerUserModel removerUsuario(@PathVariable Long id) {
		SwaggerUserModel usuario = repU.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
		
		repU.deleteById(id);
		return usuario;
	}
	
}
