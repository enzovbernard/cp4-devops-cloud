package br.com.fiap.clyvo_java.dto.pet.saude;

import br.com.fiap.clyvo_java.model.pet.saude.Vacina;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO responsável por representar os dados da vacina")
public class VacinaDTO {

    @Schema(description = "Nome da vacina", example = "V10")
    private String nm_vacina;

    @Schema(description = "Tipo da vacina", example = "Canina")
    private String tipo;

    @Schema(description = "Descrição da vacina", example = "Vacina múltipla canina")
    private String descricao;

    public VacinaDTO() {
    }

    public VacinaDTO(String nm_vacina, String tipo, String descricao) {
        this.nm_vacina = nm_vacina;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public VacinaDTO(Vacina vacina) {
        this.nm_vacina = vacina.getNm_vacina();
        this.tipo = vacina.getTipo();
        this.descricao = vacina.getDescricao();
    }

    public String getNm_vacina() {
        return nm_vacina;
    }

    public void setNm_vacina(String nm_vacina) {
        this.nm_vacina = nm_vacina;
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
}