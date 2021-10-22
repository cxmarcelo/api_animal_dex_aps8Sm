package br.com.aps8s.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aps8s.domain.AnimalImage;

public interface ImageRepository extends JpaRepository<AnimalImage, Integer>{

}
