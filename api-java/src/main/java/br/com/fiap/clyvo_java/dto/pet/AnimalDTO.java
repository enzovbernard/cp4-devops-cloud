package br.com.fiap.clyvo_java.dto.pet;

import java.time.LocalDate;

import br.com.fiap.clyvo_java.model.pet.Animal;

public class AnimalDTO {
    private String nm_animal;
    private Integer idade;
    private String especie;
    private String raca;
    private String sexo;
    private LocalDate dt_nascimento;
    private Double peso;

    public AnimalDTO() {}

	public AnimalDTO(String nm_animal, Integer idade, String especie, String raca, String sexo, LocalDate dt_nascimento,
			Double peso, Long tutor_id) {
		super();
		this.nm_animal = nm_animal;
		this.idade = idade;
		this.especie = especie;
		this.raca = raca;
		this.sexo = sexo;
		this.dt_nascimento = dt_nascimento;
		this.peso = peso;
	}
	
	public AnimalDTO(Animal animal) {
	    this.nm_animal = animal.getNm_animal();
	    this.idade = animal.getIdade();
	    this.especie = animal.getEspecie();
	    this.raca = animal.getRaca();
	    this.sexo = animal.getSexo();
	    this.dt_nascimento = animal.getDt_nascimento();
	    this.peso = animal.getPeso();
	}

	public String getNm_animal() {
		return nm_animal;
	}

	public void setNm_animal(String nm_animal) {
		this.nm_animal = nm_animal;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
}
