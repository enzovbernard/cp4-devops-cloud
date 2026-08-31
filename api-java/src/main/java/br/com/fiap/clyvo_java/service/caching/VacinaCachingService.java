package br.com.fiap.clyvo_java.service.caching;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.clyvo_java.model.pet.saude.Vacina;
import br.com.fiap.clyvo_java.repository.pet.saude.VacinaRepository;

@Service
public class VacinaCachingService {

	@Autowired
	private VacinaRepository repV;

	@Cacheable(value = "todasVacinas")
	public List<Vacina> findAll() {
		return repV.findAll();
	}

	@Cacheable(value = "VacinasID", key = "#id")
	public Optional<Vacina> findById(Long id) {
		return repV.findById(id);
	}

	@Cacheable(value = "VacinasPaginadas", key = "#pr")
	public Page<Vacina> findAll(PageRequest pr) {
		return repV.findAll(pr);
	}
	
	@Cacheable(value = "vacinasPorNome", key = "#parteNome")
	public List<Vacina> retornarVacinasPorNome(String parteNome) {
		return repV.retornarVacinaPorNome(parteNome);
	}

	@CacheEvict(value = { "todasVacinas", "VacinasID", "VacinasPaginadas", "vacinasPorNome"}, allEntries = true)
	public void removerCache() {
		System.out.println("Removendo arquivos de cache");
	}
}
