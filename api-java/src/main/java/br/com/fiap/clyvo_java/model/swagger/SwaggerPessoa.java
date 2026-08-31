package br.com.fiap.clyvo_java.model.swagger;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_CLYVO_SWAGGER_PESSOA")
public class SwaggerPessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 1, max = 60)
	@Schema(description = "Este atributo representa o nome do utilizador da API")
	private String nome;
	
	@CPF
	@Schema(description = "Este atributo representa o CPF do utilizador da API")
	private String cpf;
	
	@Past
	@Schema(description = "Representa a data de nascimento do utilizador da API")
	private LocalDate dataNascimento;

	@Email
	@Schema(description = "Representa o e-mail de contato do utilizador da API")
	private String emailPessoal;

	public SwaggerPessoa() {

	}

	public SwaggerPessoa(Long id, String nome, String cpf, LocalDate dataNascimento, String emailPessoal) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.emailPessoal = emailPessoal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmailPessoal() {
		return emailPessoal;
	}

	public void setEmailPessoal(String emailPessoal) {
		this.emailPessoal = emailPessoal;
	}
}