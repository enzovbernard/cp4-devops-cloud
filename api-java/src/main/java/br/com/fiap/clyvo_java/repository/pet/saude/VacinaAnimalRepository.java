package br.com.fiap.clyvo_java.repository.pet.saude;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.clyvo_java.model.pet.saude.VacinaAnimal;

public interface VacinaAnimalRepository extends JpaRepository<VacinaAnimal, Long> {

}
