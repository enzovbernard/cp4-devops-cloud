package br.com.fiap.clyvo_java.service.paginacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.clyvo_java.dto.pet.AnimalDTO;
import br.com.fiap.clyvo_java.model.pet.Animal;
import br.com.fiap.clyvo_java.service.caching.AnimalCachingService;

@Service
public class AnimalPageService {
	@Autowired
	private AnimalCachingService cacheA;

	@Transactional(readOnly = true)
	public Page<AnimalDTO> paginar(PageRequest req) {

		Page<Animal> animais_paginados = cacheA.findAll(req);

		Page<AnimalDTO> animaisdto_paginados = animais_paginados.map(animal -> new AnimalDTO(animal));

		return animaisdto_paginados;
	}
}