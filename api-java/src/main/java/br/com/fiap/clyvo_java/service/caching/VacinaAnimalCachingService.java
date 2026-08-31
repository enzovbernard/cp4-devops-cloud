package br.com.fiap.clyvo_java.service.caching;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.clyvo_java.model.pet.saude.VacinaAnimal;
import br.com.fiap.clyvo_java.repository.pet.saude.VacinaAnimalRepository;

@Service
public class VacinaAnimalCachingService {

    @Autowired
    private VacinaAnimalRepository repVA;

    @Cacheable(value = "todasVacinasAnimal")
    public List<VacinaAnimal> findAll() {
        return repVA.findAll();
    }

    @Cacheable(value = "vacinasAnimalID", key = "#id")
    public Optional<VacinaAnimal> findById(Long id) {
        return repVA.findById(id);
    }

    @Cacheable(value = "vacinasAnimalPaginadas", key = "#pr")
    public Page<VacinaAnimal> findAll(PageRequest pr) {
        return repVA.findAll(pr);
    }

    @CacheEvict(value = {"todasVacinasAnimal", "vacinasAnimalID", "vacinasAnimalPaginadas", "vacinasAnimalPorNome"}, allEntries = true)
    public void removerCache() {
        System.out.println("Removendo cache de VacinaAnimal");
    }
}