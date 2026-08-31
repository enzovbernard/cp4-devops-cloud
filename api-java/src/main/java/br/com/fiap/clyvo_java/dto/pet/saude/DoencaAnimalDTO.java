package br.com.fiap.clyvo_java.dto.pet.saude;

import java.time.LocalDate;

import br.com.fiap.clyvo_java.model.pet.saude.DoencaAnimal;

public class DoencaAnimalDTO {

    private LocalDate dt_diagnostico;
    private String gravidade;
    private String status;
    private String observacoes;
    private String nomeAnimal;
    private String nomeDoenca;

    public DoencaAnimalDTO() {}

    public DoencaAnimalDTO(DoencaAnimal doencaAnimal) {
        this.dt_diagnostico = doencaAnimal.getDt_diagnostico();
        this.gravidade = doencaAnimal.getGravidade();
        this.status = doencaAnimal.getStatus();
        this.observacoes = doencaAnimal.getObservacoes();
        this.nomeAnimal = doencaAnimal.getAnimal().getNm_animal();
        this.nomeDoenca = doencaAnimal.getDoenca().getNm_doenca();
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

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getNomeDoenca() {
        return nomeDoenca;
    }

    public void setNomeDoenca(String nomeDoenca) {
        this.nomeDoenca = nomeDoenca;
    }
}