package br.com.fiap.clyvo_java.service.paginacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.clyvo_java.dto.pet.saude.VacinaDTO;
import br.com.fiap.clyvo_java.model.pet.saude.Vacina;
import br.com.fiap.clyvo_java.service.caching.VacinaCachingService;

@Service
public class VacinaPageService {	
	@Autowired
	private VacinaCachingService cacheV;

	@Transactional(readOnly = true)
	public Page<VacinaDTO> paginar(PageRequest req) {

		Page<Vacina> vacinas_paginadas = cacheV.findAll(req);

		Page<VacinaDTO> vacinasdto_paginadas = vacinas_paginadas.map(vacina -> new VacinaDTO(vacina));

		return vacinasdto_paginadas;
	}
}
