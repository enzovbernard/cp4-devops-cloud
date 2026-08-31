package br.com.fiap.clyvo_java.model.swagger;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "T_CLYVO_SWAGGER_USER")
public class SwaggerUserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_pessoa")
	@Schema(description = "Este atributo a pessoa associada a conta para utilizar a API")
	private SwaggerPessoa pessoa;
	
	@Schema(description = "Este atributo representa o nome de usuário da conta para utilizar a API")
	private String username;
	
	@Schema(description = "Este atributo representa a senha para realizar o login na conta para utilizar a API")
	private String senha;
	
	@Schema(description = "Este atributo representa a permissão de acesso da conta para utilizar a API")
	private String permissao;
	
	@PastOrPresent
	@Schema(description = "Este atributo representa quando a conta foi criada")
	private LocalDate dataCriacao;


	public SwaggerUserModel() {

	}

	public Long getId() {
		return id;
	}

	public SwaggerUserModel(Long id, SwaggerPessoa pessoa, String username, String senha, String permissao, LocalDate dataCriacao) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.username = username;
		this.senha = senha;
		this.permissao = permissao;
		this.dataCriacao = dataCriacao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SwaggerPessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(SwaggerPessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}