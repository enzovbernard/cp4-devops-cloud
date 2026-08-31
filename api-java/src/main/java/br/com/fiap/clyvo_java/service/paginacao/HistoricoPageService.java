package br.com.fiap.clyvo_java.service.paginacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.clyvo_java.dto.pet.consultas.HistoricoDTO;
import br.com.fiap.clyvo_java.model.pet.consultas.Historico;
import br.com.fiap.clyvo_java.service.caching.HistoricoCachingService;

@Service
public class HistoricoPageService {

    @Autowired
    private HistoricoCachingService cacheH;

    @Transactional(readOnly = true)
    public Page<HistoricoDTO> paginar(PageRequest req) {

        Page<Historico> historicos_paginados = cacheH.findAll(req);

        Page<HistoricoDTO> historicosdto_paginados =
                historicos_paginados.map(historico -> new HistoricoDTO(historico));

        return historicosdto_paginados;
    }
}