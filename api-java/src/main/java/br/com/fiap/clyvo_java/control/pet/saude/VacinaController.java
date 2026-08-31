package br.com.fiap.clyvo_java.control.pet.saude;

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

import br.com.fiap.clyvo_java.dto.pet.saude.VacinaDTO;
import br.com.fiap.clyvo_java.model.pet.saude.Vacina;
import br.com.fiap.clyvo_java.repository.pet.saude.VacinaRepository;
import br.com.fiap.clyvo_java.service.caching.VacinaCachingService;
import br.com.fiap.clyvo_java.service.paginacao.VacinaPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vacinas")
public class VacinaController {

    @Autowired
    private VacinaCachingService vacinaCachingService;

    @Autowired
    private VacinaPageService vacinaPageService;

    @Autowired
    private VacinaRepository vacinaRepository;

    @Operation(description = "Realiza a busca de todas as vacinas, utilizando caching",
            summary = "Retornar todas as vacinas",
            tags = "Retorno de Informações de Vacinas")
    @GetMapping("/todos")
    public ResponseEntity<List<Vacina>> listarTodos() {
        return ResponseEntity.ok(vacinaCachingService.findAll());
    }

    @Operation(description = "Realiza a busca de vacinas por ID, utilizando caching",
            summary = "Retornar vacinas por ID",
            tags = "Retorno de Informações de Vacinas")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vacina>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vacinaCachingService.findById(id));
    }

    @Operation(description = "Realiza a busca de vacinas por nome, utilizando caching",
            summary = "Retornar vacinas por nome",
            tags = "Retorno de Informações de Vacinas")
    @GetMapping("/buscarNome")
    public ResponseEntity<List<Vacina>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(vacinaCachingService.retornarVacinasPorNome(nome));
    }

    @Operation(description = "Realiza a busca de vacinas paginadas",
            summary = "Retornar vacinas paginadas",
            tags = "Retorno de Informações por Paginação")
    @GetMapping("/paginado")
    public ResponseEntity<Page<VacinaDTO>> listarPaginado(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {

        PageRequest pr = PageRequest.of(page, size);
        return ResponseEntity.ok(vacinaPageService.paginar(pr));
    }

    @Operation(description = "Este endpoint tem como objetivo inserir novas vacinas",
            summary = "Inserir vacina",
            tags = "Vacina CRUD")
    @PostMapping("/inserir")
    public ResponseEntity<Vacina> inserirVacina(@RequestBody @Valid Vacina vacina) {
        vacinaRepository.save(vacina);
        vacinaCachingService.removerCache();
        return ResponseEntity.status(HttpStatus.CREATED).body(vacina);
    }

    @Operation(description = "Este endpoint realiza a remoção de vacinas",
            summary = "Remover vacina",
            tags = "Vacina CRUD")
    @DeleteMapping("/{id}")
    public ResponseEntity<Vacina> removerVacina(@PathVariable Long id) {

        Optional<Vacina> op = vacinaRepository.findById(id);

        if (op.isPresent()) {
            vacinaRepository.delete(op.get());
            vacinaCachingService.removerCache();
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(description = "Este endpoint realiza a atualização de vacinas",
            summary = "Atualizar vacina",
            tags = "Vacina CRUD")
    @PutMapping("/{id}")
    public ResponseEntity<Vacina> atualizarVacina(@PathVariable Long id,
                                  @RequestBody @Valid Vacina vacina) {

        Optional<Vacina> op = vacinaCachingService.findById(id);

        if (op.isPresent()) {

            Vacina vacinaBanco = op.get();
            vacinaBanco.transferirVacina(vacina);

            vacinaRepository.save(vacinaBanco);
            vacinaCachingService.removerCache();

            return ResponseEntity.ok(vacinaBanco);
        }

        return ResponseEntity.notFound().build();
    }
}