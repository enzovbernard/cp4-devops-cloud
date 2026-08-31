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
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.clyvo_java.dto.pet.saude.VacinaAnimalDTO;
import br.com.fiap.clyvo_java.model.pet.saude.VacinaAnimal;
import br.com.fiap.clyvo_java.repository.pet.saude.VacinaAnimalRepository;
import br.com.fiap.clyvo_java.service.caching.VacinaAnimalCachingService;
import br.com.fiap.clyvo_java.service.paginacao.VacinaAnimalPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vacinaAnimal")
public class VacinaAnimalController {

    @Autowired
    private VacinaAnimalCachingService vacinaAnimalCachingService;

    @Autowired
    private VacinaAnimalPageService vacinaAnimalPageService;

    @Autowired
    private VacinaAnimalRepository vacinaAnimalRepository;

    @Operation(description = "Este endpoint tem como objetivo retornar todas relações de vacinas e animais, utilizando caching",
            summary = "Retornar todas as relação de vacina e animal",
            tags = "Retorno de Informações de Vacina e Animal")
    @GetMapping("/todos")
    public ResponseEntity<List<VacinaAnimal>> listarTodos() {
        return ResponseEntity.ok(vacinaAnimalCachingService.findAll());
    }

    @Operation(description = "Este endpoint tem como objetivo retornar relações de vacinas e animais pelo ID, utilizando caching",
            summary = "Retornar relação vacina e animal por ID",
            tags = "Retorno de Informações de Vacina e Animal")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<VacinaAnimal>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vacinaAnimalCachingService.findById(id));
    }

    @Operation(description = "Realiza a busca da relação de vacinas e animais paginadas",
    summary = "Retornar relação vacina e animal paginadas",
    tags = "Retorno de Informações por Paginação")
    @GetMapping("/paginado")
    public ResponseEntity<Page<VacinaAnimalDTO>> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pr = PageRequest.of(page, size);

        return ResponseEntity.ok(vacinaAnimalPageService.paginar(pr));
    }
    
    /*POST, PUT, DELETE*/

    @Operation(description = "Este endpoint tem como objetivo inserir novas relações de vacinas e animais",
            summary = "Adicionar relação vacina e animal",
            tags = "Vacina e Animal CRUD")
    @PostMapping("/inserir")
    public ResponseEntity<VacinaAnimal> inserir(@RequestBody @Valid VacinaAnimal vacinaAnimal) {

        vacinaAnimalRepository.save(vacinaAnimal);
        vacinaAnimalCachingService.removerCache();

        return ResponseEntity.status(HttpStatus.CREATED).body(vacinaAnimal);
    }

    @Operation(description = "Este endpoint tem como objetivo deletar relações de vacinas e animais",
            summary = "Deletar relação vacina e animal",
            tags = "Vacina e Animal CRUD")
    @DeleteMapping("/{id}")
    public ResponseEntity<VacinaAnimal> remover(@PathVariable Long id) {

        Optional<VacinaAnimal> op = vacinaAnimalRepository.findById(id);

        if(op.isPresent()) {

            vacinaAnimalRepository.delete(op.get());
            vacinaAnimalCachingService.removerCache();

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(description = "Este endpoint tem como objetivo atualizar relações de vacinas e animais",
            summary = "Atualizar relação vacina e animal",
            tags = "Vacina e Animal CRUD")
    @PutMapping("/{id}")
    public ResponseEntity<VacinaAnimal> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid VacinaAnimal vacinaAnimal) {

        Optional<VacinaAnimal> op = vacinaAnimalCachingService.findById(id);

        if(op.isPresent()) {

            VacinaAnimal vacinaAnimalBanco = op.get();

            vacinaAnimalBanco.transferirVacinaAnimal(vacinaAnimal);

            vacinaAnimalRepository.save(vacinaAnimalBanco);

            vacinaAnimalCachingService.removerCache();

            return ResponseEntity.ok(vacinaAnimalBanco);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}