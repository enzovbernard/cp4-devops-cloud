package br.com.fiap.clyvo_java.service.caching;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.clyvo_java.model.pet.consultas.Consulta;
import br.com.fiap.clyvo_java.repository.pet.consultas.ConsultaRepository;

@Service
public class ConsultaCachingService {

    @Autowired
    private ConsultaRepository repC;

    @Cacheable(value = "todasConsultas")
    public List<Consulta> findAll() {
        return repC.findAll();
    }

    @Cacheable(value = "consultasID", key = "#id")
    public Optional<Consulta> findById(Long id) {
        return repC.findById(id);
    }

    @Cacheable(value = "consultasPaginadas", key = "#pr")
    public Page<Consulta> findAll(PageRequest pr) {
        return repC.findAll(pr);
    }


    @CacheEvict(value = { "consultasPaginadas", "consultasID", "todasConsultas" }, allEntries = true)
    public void removerCache() {
        System.out.println("Removendo arquivos de cache");
    }

}