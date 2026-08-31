package br.com.fiap.clyvo_java.service.paginacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.clyvo_java.dto.pet.saude.DoencaDTO;
import br.com.fiap.clyvo_java.model.pet.saude.Doenca;
import br.com.fiap.clyvo_java.service.caching.DoencaCachingService;

@Service
public class DoencaPageService {
	
	@Autowired
	private DoencaCachingService cacheD;

	@Transactional(readOnly = true)
	public Page<DoencaDTO> paginar(PageRequest req) {

		Page<Doenca> doencas_paginadas = cacheD.findAll(req);

		Page<DoencaDTO> doencasdto_paginadas = doencas_paginadas.map(doenca -> new DoencaDTO(doenca));

		return doencasdto_paginadas;
	}

}
