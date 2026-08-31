package br.com.fiap.clyvo_java.dto.individuos;

import br.com.fiap.clyvo_java.model.individuos.Responsavel;

public class ResponsavelDTO {

    private String nm_responsavel;
    private String email;
    private String num_celular;

    public ResponsavelDTO() {}
    
    public ResponsavelDTO(String nm_responsavel, String email, String num_celular) {
		super();
		this.nm_responsavel = nm_responsavel;
		this.email = email;
		this.num_celular = num_celular;
	}
    
    public ResponsavelDTO(Responsavel responsavel) {
    	this.nm_responsavel = responsavel.getNm_responsavel();
		this.email = responsavel.getEmail();
		this.num_celular = responsavel.getNum_celular();
	}

	public String getResponsavel() {
        return nm_responsavel;
    }

    public void setNm_responsavel(String nm_responsavel) {
        this.nm_responsavel = nm_responsavel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum_celular() {
        return num_celular;
    }

    public void setNum_celular(String num_celular) {
        this.num_celular = num_celular;
    }
}