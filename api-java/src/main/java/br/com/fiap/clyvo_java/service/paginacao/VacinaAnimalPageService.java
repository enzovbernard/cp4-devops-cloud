package br.com.fiap.clyvo_java.service.paginacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.clyvo_java.dto.pet.saude.VacinaAnimalDTO;
import br.com.fiap.clyvo_java.model.pet.saude.VacinaAnimal;
import br.com.fiap.clyvo_java.service.caching.VacinaAnimalCachingService;

@Service
public class VacinaAnimalPageService {

    @Autowired
    private VacinaAnimalCachingService cacheVA;

    @Transactional(readOnly = true)
    public Page<VacinaAnimalDTO> paginar(PageRequest req){
        Page<VacinaAnimal> vacinaAnimalPaginado = cacheVA.findAll(req);
        Page<VacinaAnimalDTO> vacinaAnimalDTO = vacinaAnimalPaginado.map(v -> new VacinaAnimalDTO(v));
        
        return vacinaAnimalDTO;
    }
}