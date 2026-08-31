package br.com.fiap.clyvo_java.control.pet;

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

import br.com.fiap.clyvo_java.dto.pet.AnimalDTO;
import br.com.fiap.clyvo_java.model.pet.Animal;
import br.com.fiap.clyvo_java.repository.pet.AnimalRepository;
import br.com.fiap.clyvo_java.service.caching.AnimalCachingService;
import br.com.fiap.clyvo_java.service.paginacao.AnimalPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalCachingService animalCachingService;
    
    @Autowired
    private AnimalPageService animalPageService;
    
    @Autowired
    private AnimalRepository animalRepository;


    @Operation(description = "Realiza a busca de todos os animais, utilizando caching",
			summary = "Retornar todos animais",
			tags = "Retorno de Informações de Animais")
    @GetMapping("/todos")
    public ResponseEntity<List<Animal>> listarTodos() {
        return ResponseEntity.ok(animalCachingService.findAll());
    }

    @Operation(description = "Realiza a busca de animais por ID, utilizando caching",
			summary = "Retornar dados de animais por ID",
			tags = "Retorno de Informações de Animais")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Animal>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(animalCachingService.findById(id));
    }

    @Operation(description = "Realiza a busca de animais por nome, utilizando caching",
			summary = "Retornar animais por nome",
			tags = "Retorno de Informações de Animais")
    @GetMapping("/buscarNome")
    public ResponseEntity<List<Animal>> buscarPorNome(@RequestParam String nome) {
    	return ResponseEntity.ok(animalCachingService.retornarAnimaisPorNome(nome));
    }

    @Operation(description = "Realiza a busca de animais por nome, utilizando caching",
			summary = "Retornar animais páginados",
			tags = "Retorno de Informações por Paginação")
    @GetMapping("/paginado")
    public ResponseEntity<Page<AnimalDTO>> listarPaginado(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    	PageRequest pr = PageRequest.of(page,size);
        return ResponseEntity.ok(animalPageService.paginar(pr));
    }
    
    /*POST, PUT AND DELETE*/
    
	@Operation(description = "Este endpoint tem como objetivo inserir novos animais",
			summary = "Inserir novo animal",
			tags = "Animal CRUD")
	@PostMapping(value = "/inserir")
	public ResponseEntity<Animal> inserirAnimal(@RequestBody @Valid Animal animal) {
		animalRepository.save(animal);
		animalCachingService.removerCache();
		return ResponseEntity.status(HttpStatus.CREATED).body(animal);
	}

	@Operation(description = "Este endpoint realiza a remoção de animais do sistema",
			summary = "Remover animais",
			tags = "Animal CRUD")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Animal> removerAnimal(@PathVariable Long id) {
		Optional<Animal> op = animalRepository.findById(id);

		if (op.isPresent()) {
			animalRepository.delete(op.get());
			animalCachingService.removerCache();
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(description = "Este endpoint realiza a atualização de animais",
			summary = "Atualizar animais",
			tags = "Animal CRUD")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Animal> atualizarAnimal(@PathVariable Long id, @RequestBody @Valid Animal animal) {
		Optional<Animal> op = animalCachingService.findById(id);

		if (op.isPresent()) {
			Animal animalBanco = op.get();
			animalBanco.transferirAnimal(animal);
			animalRepository.save(animalBanco);
			animalCachingService.removerCache();
			return ResponseEntity.ok(animalBanco);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}