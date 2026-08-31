package br.com.fiap.clyvo_java.dto.pet.saude;

import br.com.fiap.clyvo_java.model.pet.saude.Doenca;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO responsável por representar os dados da doença")
public class DoencaDTO {

    @Schema(description = "Nome da doença", example = "Otite")
    private String nm_doenca;

    @Schema(description = "Tipo da doença", example = "Infecção")
    private String tipo;

    @Schema(description = "Descrição da doença", example = "Infecção no ouvido")
    private String descricao;

    @Schema(description = "Indica se a doença é contagiosa", example = "N")
    private String contagiosidade;

    public DoencaDTO() {
    }

    public DoencaDTO(String nm_doenca, String tipo, String descricao, String contagiosidade) {
        this.nm_doenca = nm_doenca;
        this.tipo = tipo;
        this.descricao = descricao;
        this.contagiosidade = contagiosidade;
    }
    
    public DoencaDTO(Doenca doenca) {
        this.nm_doenca = doenca.getNm_doenca();
        this.tipo = doenca.getTipo();
        this.descricao = doenca.getDescricao();
        this.contagiosidade = doenca.getContagiosidade();
    }

    public String getNm_doenca() {
        return nm_doenca;
    }

    public void setNm_doenca(String nm_doenca) {
        this.nm_doenca = nm_doenca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContagiosidade() {
        return contagiosidade;
    }

    public void setContagiosidade(String contagiosidade) {
        this.contagiosidade = contagiosidade;
    }
}