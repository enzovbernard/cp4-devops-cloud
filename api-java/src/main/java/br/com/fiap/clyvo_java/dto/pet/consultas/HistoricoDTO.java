package br.com.fiap.clyvo_java.dto.pet.consultas;

import java.time.LocalDate;

import br.com.fiap.clyvo_java.model.pet.consultas.Historico;

public class HistoricoDTO {

    private LocalDate dt_criacao_historico;
    private Double num_consultas_clyvo;
    private String link_historico;
    private String nomeAnimal;
    private Long idConsulta;

    public HistoricoDTO() {}

    public HistoricoDTO(Historico historico) {
        this.dt_criacao_historico = historico.getDt_criacao_historico();
        this.num_consultas_clyvo = historico.getNum_consultas_clyvo();
        this.link_historico = historico.getLink_historico();
        this.nomeAnimal = historico.getAnimal().getNm_animal();
        this.idConsulta = historico.getConsulta().getId_consulta();
    }

    public LocalDate getDt_criacao_historico() {
        return dt_criacao_historico;
    }

    public void setDt_criacao_historico(LocalDate dt_criacao_historico) {
        this.dt_criacao_historico = dt_criacao_historico;
    }

    public Double getNum_consultas_clyvo() {
        return num_consultas_clyvo;
    }

    public void setNum_consultas_clyvo(Double num_consultas_clyvo) {
        this.num_consultas_clyvo = num_consultas_clyvo;
    }

    public String getLink_historico() {
        return link_historico;
    }

    public void setLink_historico(String link_historico) {
        this.link_historico = link_historico;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

}