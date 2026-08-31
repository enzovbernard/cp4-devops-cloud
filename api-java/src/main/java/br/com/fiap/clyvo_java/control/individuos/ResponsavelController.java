package br.com.fiap.clyvo_java.control.individuos;

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

import br.com.fiap.clyvo_java.dto.individuos.ResponsavelDTO;
import br.com.fiap.clyvo_java.model.individuos.Responsavel;
import br.com.fiap.clyvo_java.repository.individuos.ResponsavelRepository;
import br.com.fiap.clyvo_java.service.caching.ResponsavelCachingService;
import br.com.fiap.clyvo_java.service.paginacao.ResponsavelPageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {
	
    @Autowired
    private ResponsavelCachingService responsavelCachingService;
    
    @Autowired
    private ResponsavelPageService responsavelPageService;
    
    @Autowired
    private ResponsavelRepository responsavelRepository;


    @Operation(description = "Realiza a busca de todos os responsáveis, utilizando caching",
			summary = "Retornar todos os responsáveis",
			tags = "Retorno de Informações de Responsáveis")
    @GetMapping("/todos")
    public ResponseEntity<List<Responsavel>> listarTodos() {
        return ResponseEntity.ok(responsavelCachingService.findAll());
    }

    @Operation(description = "Realiza a busca de responsáveis por ID, utilizando caching",
			summary = "Retornar dados de responsáveis por ID",
			tags = "Retorno de Informações de Responsáveis")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Responsavel>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(responsavelCachingService.findById(id));
    }

    @Operation(description = "Realiza a busca de responsáveis por nome, utilizando caching",
			summary = "Retornar responsáveis por nome",
			tags = "Retorno de Informações de Responsáveis")
    @GetMapping("/buscarNome")
    public ResponseEntity<List<Responsavel>> buscarPorNome(@RequestParam String nome) {
    	return ResponseEntity.ok(responsavelCachingService.retornarResponsaveisPorNome(nome));
    }

    @Operation(description = "Realiza a busca de responsáveis por nome, utilizando caching",
			summary = "Retornar responsáveis páginados",
			tags = "Retorno de Informações por Paginação")
    @GetMapping("/paginado")
    public ResponseEntity<Page<ResponsavelDTO>> listarPaginado(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    	PageRequest pr = PageRequest.of(page,size);
        return ResponseEntity.ok(responsavelPageService.paginar(pr));
    }
    
    /*POST, PUT AND DELETE*/
    
	@Operation(description = "Este endpoint tem como objetivo inserir novos responsáveis",
			summary = "Inserir novo responsável",
			tags = "Responsavel CRUD")
	@PostMapping(value = "/inserir")
	public ResponseEntity<Responsavel> inserirResponsavel(@RequestBody @Valid Responsavel responsavel) {
		responsavelRepository.save(responsavel);
		responsavelCachingService.removerCache();
		return ResponseEntity.status(HttpStatus.CREATED).body(responsavel);
	}

	@Operation(description = "Este endpoint realiza a remoção de responsáveis do sistema",
			summary = "Remover responsáveis",
			tags = "Responsavel CRUD")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Responsavel> removerResponsavel(@PathVariable Long id) {
		Optional<Responsavel> op = responsavelRepository.findById(id);

		if (op.isPresent()) {
			responsavelRepository.delete(op.get());
			responsavelCachingService.removerCache();
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(description = "Este endpoint realiza a atualização de dados dos responsáveis",
			summary = "Atualizar responsáveis",
			tags = "Responsavel CRUD")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Responsavel> atualizarResponsavel(@PathVariable Long id, @RequestBody @Valid Responsavel responsavel) {
		Optional<Responsavel> op = responsavelCachingService.findById(id);

		if (op.isPresent()) {
			Responsavel respBanco = op.get();
			respBanco.transferirResponsavel(responsavel);
			responsavelRepository.save(respBanco);
			responsavelCachingService.removerCache();
			return ResponseEntity.ok( respBanco);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
