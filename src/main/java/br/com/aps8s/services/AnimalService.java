package br.com.aps8s.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.aps8s.domain.Animal;
import br.com.aps8s.repositories.AnimalRepository;
import br.com.aps8s.services.exceptions.ObjectNotFoundException;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository repo;
	
	public Animal find(Integer id) {
		Optional<Animal> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Animal.class.getName()));
	}

	public Animal insert(Animal obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public List<Animal> findAll() {
		return repo.findAll();
	}
	
	public Page<Animal> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}
