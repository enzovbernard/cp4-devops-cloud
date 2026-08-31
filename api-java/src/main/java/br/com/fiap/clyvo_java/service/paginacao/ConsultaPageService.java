package br.com.fiap.clyvo_java.service.paginacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.clyvo_java.dto.pet.consultas.ConsultaDTO;
import br.com.fiap.clyvo_java.model.pet.consultas.Consulta;
import br.com.fiap.clyvo_java.service.caching.ConsultaCachingService;

@Service
public class ConsultaPageService {

    @Autowired
    private ConsultaCachingService cacheC;

    @Transactional(readOnly = true)
    public Page<ConsultaDTO> paginar(PageRequest req) {

        Page<Consulta> consultasPaginadas = cacheC.findAll(req);

        Page<ConsultaDTO> consultasDtoPaginadas =
                consultasPaginadas.map(consulta -> new ConsultaDTO(consulta));

        return consultasDtoPaginadas;
    }
}