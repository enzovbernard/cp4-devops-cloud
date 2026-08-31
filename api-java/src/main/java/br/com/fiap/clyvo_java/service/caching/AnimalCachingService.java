package br.com.fiap.clyvo_java.service.caching;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.clyvo_java.model.pet.Animal;
import br.com.fiap.clyvo_java.repository.pet.AnimalRepository;

@Service
public class AnimalCachingService {

	@Autowired
	private AnimalRepository repA;

	@Cacheable(value = "todosAnimais")
	public List<Animal> findAll() {
		return repA.findAll();
	}

	@Cacheable(value = "animaisID", key = "#id")
	public Optional<Animal> findById(Long id) {
		return repA.findById(id);
	}

	@Cacheable(value = "animaisPaginados", key = "#pr")
	public Page<Animal> findAll(PageRequest pr) {
		return repA.findAll(pr);
	}
	
	@Cacheable(value = "animaisPorNome", key = "#parteNome")
	public List<Animal> retornarAnimaisPorNome(String parteNome) {
		return repA.retornarAnimalPorNome(parteNome);
	}

	@CacheEvict(value = { "animaisPorNome", "animaisPaginados", "animaisID", "todosAnimais"}, allEntries = true)
	public void removerCache() {
		System.out.println("Removendo arquivos de cache");
	}
	
	

}
