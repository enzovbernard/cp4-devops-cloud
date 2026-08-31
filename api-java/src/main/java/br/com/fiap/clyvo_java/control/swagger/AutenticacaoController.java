package br.com.fiap.clyvo_java.control.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.clyvo_java.security.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Operation(description = "Este endpoint realiza o login e retorna o token para a autorização da utilização dos outros endpoints",
			summary = "Realizar Login",
			tags = "Autenticação")
	@PostMapping(value = "/login")
	public String logar(@RequestParam(value = "username", defaultValue = "RM1") String usuario, 
			            @RequestParam(value = "password", defaultValue = "1234") String senha, 
			            @RequestParam(value = "duracao", defaultValue = "10") Integer duracao) {
		
		try {
			var autenticacao = new UsernamePasswordAuthenticationToken(usuario,senha);
			manager.authenticate(autenticacao);
			return jwtUtil.gerarToken(usuario, duracao);
			
		} catch (Exception e) {
			return "Credenciais inválidas!";
		}
	}
}