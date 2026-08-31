package br.com.fiap.clyvo_java.service.caching;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.clyvo_java.model.individuos.Responsavel;
import br.com.fiap.clyvo_java.repository.individuos.ResponsavelRepository;

@Service
public class ResponsavelCachingService {
	@Autowired
	private ResponsavelRepository repR;

	@Cacheable(value = "todosResponsaveis")
	public List<Responsavel> findAll() {
		return repR.findAll();
	}

	@Cacheable(value = "responsaveisID", key = "#id")
	public Optional<Responsavel> findById(Long id) {
		return repR.findById(id);
	}

	@Cacheable(value = "responsaveisPaginados", key = "#pr")
	public Page<Responsavel> findAll(PageRequest pr) {
		return repR.findAll(pr);
	}
	
	@Cacheable(value = "responsaveisPorNome", key = "#parteNome")
	public List<Responsavel> retornarResponsaveisPorNome(String parteNome) {
		return repR.retornarResponsaveisPorNome(parteNome);
	}

	@CacheEvict(value = { "responsaveisPorNome", "responsaveisPaginados", "responsaveisID", "todosResponsaveis"}, allEntries = true)
	public void removerCache() {
		System.out.println("Removendo arquivos de cache");
	}
}
