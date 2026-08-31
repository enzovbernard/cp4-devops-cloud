package br.com.fiap.clyvo_java.service.paginacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.clyvo_java.dto.pet.saude.DoencaAnimalDTO;
import br.com.fiap.clyvo_java.model.pet.saude.DoencaAnimal;
import br.com.fiap.clyvo_java.service.caching.DoencaAnimalCachingService;

@Service
public class DoencaAnimalPageService {

    @Autowired
    private DoencaAnimalCachingService cacheDA;

    @Transactional(readOnly = true)
    public Page<DoencaAnimalDTO> paginar(PageRequest req) {
        Page<DoencaAnimal> doencasAnimaisPaginadas =cacheDA.findAll(req);
        Page<DoencaAnimalDTO> doencasAnimaisDTO = doencasAnimaisPaginadas.map(doencaAnimal -> new DoencaAnimalDTO(doencaAnimal));

        return doencasAnimaisDTO;
    }
}