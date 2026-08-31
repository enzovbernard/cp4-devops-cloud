package br.com.fiap.clyvo_java.dto.pet.saude;

import java.time.LocalDate;

import br.com.fiap.clyvo_java.model.pet.saude.VacinaAnimal;

public class VacinaAnimalDTO {

    private Long id_vacina_animal;
    private Integer num_doses;
    private Integer frequencia_aplicacao;
    private LocalDate dt_receita;
    private String nomeVacina;
    private String nomeAnimal;

    public VacinaAnimalDTO() {}

    public VacinaAnimalDTO(VacinaAnimal vacinaAnimal) {
        this.id_vacina_animal = vacinaAnimal.getId_vacina_animal();
        this.num_doses = vacinaAnimal.getNum_doses();
        this.frequencia_aplicacao = vacinaAnimal.getFrequencia_aplicacao();
        this.dt_receita = vacinaAnimal.getDt_receita();
        this.nomeVacina = vacinaAnimal.getVacina().getNm_vacina();
        this.nomeAnimal = vacinaAnimal.getAnimal().getNm_animal();
    }

    public Long getId_vacina_animal() {
        return id_vacina_animal;
    }

    public void setId_vacina_animal(Long id_vacina_animal) {
        this.id_vacina_animal = id_vacina_animal;
    }

    public Integer getNum_doses() {
        return num_doses;
    }

    public void setNum_doses(Integer num_doses) {
        this.num_doses = num_doses;
    }

    public Integer getFrequencia_aplicacao() {
        return frequencia_aplicacao;
    }

    public void setFrequencia_aplicacao(Integer frequencia_aplicacao) {
        this.frequencia_aplicacao = frequencia_aplicacao;
    }

    public LocalDate getDt_receita() {
        return dt_receita;
    }

    public void setDt_receita(LocalDate dt_receita) {
        this.dt_receita = dt_receita;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }
}