package br.com.fiap.clyvo_java.service.paginacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.clyvo_java.dto.individuos.VeterinarioDTO;
import br.com.fiap.clyvo_java.model.individuos.Veterinario;
import br.com.fiap.clyvo_java.service.caching.VeterinarioCachingService;

@Service
public class VeterinarioPageService {

	@Autowired
	private VeterinarioCachingService cacheV;

	@Transactional(readOnly = true)
	public Page<VeterinarioDTO> paginar(PageRequest req) {

		Page<Veterinario> veterinarios_paginados = cacheV.findAll(req);

		Page<VeterinarioDTO> veterinariosdto_paginados = veterinarios_paginados.map(veterinario -> new VeterinarioDTO(veterinario));

		return veterinariosdto_paginados;
	}
}
