package br.com.fiap.clyvo_java.service.caching;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.clyvo_java.model.pet.saude.Doenca;
import br.com.fiap.clyvo_java.repository.pet.saude.DoencaRepository;

@Service
public class DoencaCachingService {

	@Autowired
	private DoencaRepository repD;

	@Cacheable(value = "todasDoencas")
	public List<Doenca> findAll() {
		return repD.findAll();
	}

	@Cacheable(value = "doencasID", key = "#id")
	public Optional<Doenca> findById(Long id) {
		return repD.findById(id);
	}

	@Cacheable(value = "doencasPaginadas", key = "#pr")
	public Page<Doenca> findAll(PageRequest pr) {
		return repD.findAll(pr);
	}
	
	@Cacheable(value = "doencasPorNome", key = "#parteNome")
	public List<Doenca> retornarDoencasPorNome(String parteNome) {
		return repD.retornarDoencaPorNome(parteNome);
	}

	@CacheEvict(value = { "todasDoencas", "doencasID", "doencasPaginadas", "doencasPorNome"}, allEntries = true)
	public void removerCache() {
		System.out.println("Removendo arquivos de cache");
	}
}
