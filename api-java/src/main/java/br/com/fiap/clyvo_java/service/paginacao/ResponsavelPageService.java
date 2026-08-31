package br.com.fiap.clyvo_java.service.paginacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.clyvo_java.dto.individuos.ResponsavelDTO;
import br.com.fiap.clyvo_java.model.individuos.Responsavel;
import br.com.fiap.clyvo_java.service.caching.ResponsavelCachingService;

@Service
public class ResponsavelPageService {

	@Autowired
	private ResponsavelCachingService cacheR;

	@Transactional(readOnly = true)
	public Page<ResponsavelDTO> paginar(PageRequest req) {

		Page<Responsavel> responsaveis_paginados = cacheR.findAll(req);

		Page<ResponsavelDTO> responsaveisdto_paginados = responsaveis_paginados.map(responsavel -> new ResponsavelDTO(responsavel));

		return responsaveisdto_paginados;
	}
}
