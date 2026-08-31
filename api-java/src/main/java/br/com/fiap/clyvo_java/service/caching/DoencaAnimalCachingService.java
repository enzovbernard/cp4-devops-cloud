package br.com.fiap.clyvo_java.service.caching;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.clyvo_java.model.pet.saude.DoencaAnimal;
import br.com.fiap.clyvo_java.repository.pet.saude.DoencaAnimalRepository;

@Service
public class DoencaAnimalCachingService {

    @Autowired
    private DoencaAnimalRepository repDA;

    @Cacheable(value = "todasDoencasAnimais")
    public List<DoencaAnimal> findAll() {
        return repDA.findAll();
    }

    @Cacheable(value = "doencasAnimaisID", key = "#id")
    public Optional<DoencaAnimal> findById(Long id) {
        return repDA.findById(id);
    }

    @Cacheable(value = "doencasAnimaisPaginadas", key = "#pr")
    public Page<DoencaAnimal> findAll(PageRequest pr) {
        return repDA.findAll(pr);
    }

    @CacheEvict(value = {"todasDoencasAnimais", "doencasAnimaisID", "doencasAnimaisPaginadas"}, allEntries = true)
    public void removerCache() {
        System.out.println("Removendo cache de doenças dos animais");
    }

}