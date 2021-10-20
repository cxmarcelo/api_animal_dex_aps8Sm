package br.com.aps8s.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aps8s.domain.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{

}
