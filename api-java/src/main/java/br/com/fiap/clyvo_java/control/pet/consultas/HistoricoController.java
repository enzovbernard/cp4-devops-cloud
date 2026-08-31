package br.com.fiap.clyvo_java.control.pet.consultas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.clyvo_java.dto.pet.consultas.HistoricoDTO;
import br.com.fiap.clyvo_java.model.pet.consultas.Historico;
import br.com.fiap.clyvo_java.repository.pet.consultas.HistoricoRepository;
import br.com.fiap.clyvo_java.service.caching.HistoricoCachingService;
import br.com.fiap.clyvo_java.service.paginacao.HistoricoPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    private HistoricoCachingService historicoCachingService;

    @Autowired
    private HistoricoPageService historicoPageService;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Operation(
        description = "Realiza a busca de todos os históricos",
        summary = "Retornar todos os históricos",
        tags = "Retorno de Informações de Históricos"
    )
    @GetMapping("/todos")
    public ResponseEntity<List<Historico>> listarTodos() {

        return ResponseEntity.ok(historicoCachingService.findAll());

    }

    @Operation(
        description = "Realiza a busca de históricos por ID",
        summary = "Retornar histórico por ID",
        tags = "Retorno de Informações de Históricos"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Historico>> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(historicoCachingService.findById(id));

    }

    @Operation(
        description = "Realiza a busca paginada de históricos",
        summary = "Retornar históricos paginados",
        tags = "Retorno de Informações por Paginação"
    )
    @GetMapping("/paginado")
    public ResponseEntity<Page<HistoricoDTO>> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pr = PageRequest.of(page, size);

        return ResponseEntity.ok(historicoPageService.paginar(pr));
    }

    /* POST, PUT E DELETE */

    @Operation(
        description = "Este endpoint insere novos históricos",
        summary = "Inserir histórico",
        tags = "Historico CRUD"
    )
    @PostMapping("/inserir")
    public ResponseEntity<Historico> inserirHistorico(
            @RequestBody @Valid Historico historico) {

        historicoRepository.save(historico);

        historicoCachingService.removerCache();

        return ResponseEntity.status(HttpStatus.CREATED).body(historico);
    }

    @Operation(
        description = "Este endpoint remove históricos",
        summary = "Remover histórico",
        tags = "Historico CRUD"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Historico> removerHistorico(@PathVariable Long id) {

        Optional<Historico> op = historicoRepository.findById(id);

        if (op.isPresent()) {
            historicoRepository.delete(op.get());
            historicoCachingService.removerCache();
            return ResponseEntity.noContent().build();
        } else {
        	return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        description = "Este endpoint atualiza históricos",
        summary = "Atualizar histórico",
        tags = "Historico CRUD"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Historico> atualizarHistorico(
            @PathVariable Long id,
            @RequestBody @Valid Historico historico) {

        Optional<Historico> op =
                historicoCachingService.findById(id);

        if (op.isPresent()) {

            Historico historicoBanco = op.get();

            historicoBanco.transferirHistorico(historico);

            historicoRepository.save(historicoBanco);

            historicoCachingService.removerCache();

            return ResponseEntity.ok(historicoBanco);

        } else {

        	return ResponseEntity.notFound().build();

        }
    }

}