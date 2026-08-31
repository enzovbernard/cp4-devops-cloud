package br.com.fiap.clyvo_java.service.caching;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.clyvo_java.model.individuos.Veterinario;
import br.com.fiap.clyvo_java.repository.individuos.VeterinarioRepository;

@Service
public class VeterinarioCachingService {

	@Autowired
	private VeterinarioRepository repV;

	@Cacheable(value = "todosVeterinarios")
	public List<Veterinario> findAll() {
		return repV.findAll();
	}

	@Cacheable(value = "VeterinariosID", key = "#id")
	public Optional<Veterinario> findById(Long id) {
		return repV.findById(id);
	}

	@Cacheable(value = "VeterinariosPaginados", key = "#pr")
	public Page<Veterinario> findAll(PageRequest pr) {
		return repV.findAll(pr);
	}
	
	@Cacheable(value = "VeterinariosPorNome", key = "#parteNome")
	public List<Veterinario> retornarVeterinariosPorNome(String parteNome) {
		return repV.retornarVeterinarioPorNome(parteNome);
	}

	@CacheEvict(value = { "VeterinariosPorNome", "VeterinariosPaginados", "VeterinariosID", "todosVeterinarios"}, allEntries = true)
	public void removerCache() {
		System.out.println("Removendo arquivos de cache");
	}
}
