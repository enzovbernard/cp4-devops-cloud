package br.com.fiap.clyvo_java.service.caching;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.clyvo_java.model.pet.consultas.Historico;
import br.com.fiap.clyvo_java.repository.pet.consultas.HistoricoRepository;

@Service
public class HistoricoCachingService {

    @Autowired
    private HistoricoRepository repH;

    @Cacheable(value = "todosHistoricos")
    public List<Historico> findAll() {
        return repH.findAll();
    }

    @Cacheable(value = "historicosID", key = "#id")
    public Optional<Historico> findById(Long id) {
        return repH.findById(id);
    }

    @Cacheable(value = "historicosPaginados", key = "#pr")
    public Page<Historico> findAll(PageRequest pr) {
        return repH.findAll(pr);
    }
    

    @CacheEvict(value = { "todosHistoricos", "historicosID", "historicosPaginados" }, allEntries = true)
    public void removerCache() {

        System.out.println("Removendo cache de históricos");

    }

}