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

import br.com.fiap.clyvo_java.dto.pet.consultas.ConsultaDTO;
import br.com.fiap.clyvo_java.model.pet.consultas.Consulta;
import br.com.fiap.clyvo_java.repository.pet.consultas.ConsultaRepository;
import br.com.fiap.clyvo_java.service.caching.ConsultaCachingService;
import br.com.fiap.clyvo_java.service.paginacao.ConsultaPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaCachingService consultaCachingService;

    @Autowired
    private ConsultaPageService consultaPageService;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Operation(description = "Realiza a busca de todas as consultas, utilizando caching",
        summary = "Retornar todas as consultas",
        tags = "Retorno de Informações de Consultas")
    @GetMapping("/todos")
    public ResponseEntity<List<Consulta>> listarTodos() {
        return ResponseEntity.ok(consultaCachingService.findAll());
    }

    @Operation(description = "Realiza a busca de consultas por ID, utilizando caching",
        summary = "Retornar consulta por ID",
        tags = "Retorno de Informações de Consultas")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Consulta>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaCachingService.findById(id));
    }

    @Operation(
        description = "Realiza a busca de consultas paginadas",
        summary = "Retornar consultas paginadas",
        tags = "Retorno de Informações por Paginação"
    )
    @GetMapping("/paginado")
    public ResponseEntity<Page<ConsultaDTO>> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pr = PageRequest.of(page, size);

        return ResponseEntity.ok(consultaPageService.paginar(pr));
    }

    /* POST, PUT E DELETE */

    @Operation(
        description = "Este endpoint tem como objetivo inserir novas consultas",
        summary = "Inserir nova consulta",
        tags = "Consulta CRUD"
    )
    @PostMapping("/inserir")
    public ResponseEntity<Consulta> inserirConsulta(@RequestBody @Valid Consulta consulta) {

        consultaRepository.save(consulta);
        consultaCachingService.removerCache();

        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
    }

    @Operation(
        description = "Este endpoint realiza a remoção de consultas do sistema",
        summary = "Remover consulta",
        tags = "Consulta CRUD"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Consulta> removerConsulta(@PathVariable Long id) {

        Optional<Consulta> op = consultaRepository.findById(id);

        if (op.isPresent()) {

            consultaRepository.delete(op.get());
            consultaCachingService.removerCache();

            return ResponseEntity.noContent().build();

        } else {
        	return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        description = "Este endpoint realiza a atualização de consultas",
        summary = "Atualizar consulta",
        tags = "Consulta CRUD"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(
            @PathVariable Long id,
            @RequestBody @Valid Consulta consulta) {

        Optional<Consulta> op = consultaCachingService.findById(id);

        if (op.isPresent()) {

            Consulta consultaBanco = op.get();

            consultaBanco.transferirConsulta(consulta);

            consultaRepository.save(consultaBanco);

            consultaCachingService.removerCache();

            return ResponseEntity.ok(consultaBanco);

        } else {
        	return ResponseEntity.notFound().build();
        }
    }
}