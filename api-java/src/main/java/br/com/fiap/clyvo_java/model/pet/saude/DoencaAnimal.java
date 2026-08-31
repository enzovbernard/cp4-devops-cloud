package br.com.fiap.clyvo_java.model.pet.saude;

import java.time.LocalDate;

import br.com.fiap.clyvo_java.model.pet.Animal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_CLYVO_DOENCA_ANIMAL")
@Schema(description = "Entidade que representa doenças de um animal")
public class DoencaAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doenca_animal")
    private Long id_doenca_animal;

    @PastOrPresent
    @Column(name = "dt_diagnostico")
    @Schema(description = "Este atributo representa quando um animal foi diagnosticado com uma doença")
    private LocalDate dt_diagnostico;

    @Size(max = 50)
    @Column(name = "gravidade")
    @Schema(description = "Representa o quão grave é a doença")
    private String gravidade;

    @Size(max = 50)
    @Column(name = "status")
    @Schema(description = "Representa se a doença já foi tratada ou não")
    private String status;

    @Lob
    @Column(name = "observacoes")
    @Schema(description = "Este atributo armazena observações relacionadas a resposta do animal a doença")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "fk_id_animal")
    @Schema(description = "Este atributo representa qual animal está com a doença")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "fk_id_doenca")
    @Schema(description = "Este atributo representa com qual doença o animal está contaminado")
    private Doenca doenca;

    public DoencaAnimal() {}

	public DoencaAnimal(Long id_doenca_animal, LocalDate dt_diagnostico, @Size(max = 50) String gravidade,
			@Size(max = 50) String status, String observacoes, Animal animal, Doenca doenca) {
		super();
		this.id_doenca_animal = id_doenca_animal;
		this.dt_diagnostico = dt_diagnostico;
		this.gravidade = gravidade;
		this.status = status;
		this.observacoes = observacoes;
		this.animal = animal;
		this.doenca = doenca;
	}
	
	public void transferirDoencaAnimal(DoencaAnimal doencaAnimal) {
		this.dt_diagnostico = doencaAnimal.getDt_diagnostico();
		this.gravidade = doencaAnimal.getGravidade();
		this.status = doencaAnimal.getStatus();
		this.observacoes = doencaAnimal.getObservacoes();
		this.animal = doencaAnimal.getAnimal();
		this.doenca = doencaAnimal.getDoenca();
	}

	public Long getId_doenca_animal() {
		return id_doenca_animal;
	}

	public void setId_doenca_animal(Long id_doenca_animal) {
		this.id_doenca_animal = id_doenca_animal;
	}

	public LocalDate getDt_diagnostico() {
		return dt_diagnostico;
	}

	public void setDt_diagnostico(LocalDate dt_diagnostico) {
		this.dt_diagnostico = dt_diagnostico;
	}

	public String getGravidade() {
		return gravidade;
	}

	public void setGravidade(String gravidade) {
		this.gravidade = gravidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Doenca getDoenca() {
		return doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}

    
}