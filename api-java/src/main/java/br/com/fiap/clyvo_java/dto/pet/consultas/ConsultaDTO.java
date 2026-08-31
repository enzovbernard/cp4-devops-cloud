package br.com.fiap.clyvo_java.dto.pet.consultas;

import java.time.LocalDate;

import br.com.fiap.clyvo_java.model.individuos.Veterinario;
import br.com.fiap.clyvo_java.model.pet.Animal;
import br.com.fiap.clyvo_java.model.pet.consultas.Consulta;

public class ConsultaDTO {

    private LocalDate dtAgendamento;
    private LocalDate dtConsulta;

    private String nomeAnimal;
    private String nomeVeterinario;

    public ConsultaDTO() {

    }

    public ConsultaDTO(Consulta consulta) {

        this.dtAgendamento = consulta.getDt_agendamento();
        this.dtConsulta = consulta.getDt_consulta();

        Animal animal = consulta.getAnimal();
        Veterinario veterinario = consulta.getVeterinario();

        this.nomeAnimal = animal != null ? animal.getNm_animal() : null;
        this.nomeVeterinario = veterinario != null ? veterinario.getNm_veterinario() : null;
    }

    public LocalDate getDtAgendamento() {
        return dtAgendamento;
    }

    public void setDtAgendamento(LocalDate dtAgendamento) {
        this.dtAgendamento = dtAgendamento;
    }

    public LocalDate getDtConsulta() {
        return dtConsulta;
    }

    public void setDtConsulta(LocalDate dtConsulta) {
        this.dtConsulta = dtConsulta;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getNomeVeterinario() {
        return nomeVeterinario;
    }

    public void setNomeVeterinario(String nomeVeterinario) {
        this.nomeVeterinario = nomeVeterinario;
    }
}