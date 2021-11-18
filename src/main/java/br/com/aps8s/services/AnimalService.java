package br.com.aps8s.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.aps8s.domain.Animal;
import br.com.aps8s.domain.User;
import br.com.aps8s.repositories.AnimalRepository;
import br.com.aps8s.repositories.UserRepository;
import br.com.aps8s.services.exceptions.ObjectNotFoundException;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
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
	
	public List<Animal> findAllFavoritesByUser(Integer id) {
		Optional<User> optional = userRepo.findById(id);
		User user = optional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
		return user.getFavoriteAnimals();
	}
	
	public void deleteAnimalByUserId(Integer userId, Integer animalId) {
		Optional<User> optional = userRepo.findById(userId);
		User user = optional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + userId + ", Tipo: " + User.class.getName()));
		
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.addAll(user.getFavoriteAnimals());
		for (Animal animal : user.getFavoriteAnimals()) {
			if(animal.getId().equals(animalId)) {
				animals.remove(animal);
			}
		}
		user.setFavoriteAnimals(animals);
		userRepo.save(user);
	}
	
	public void addAnimalFavorite(Integer userId, Integer animalId) {
		Optional<User> optional = userRepo.findById(userId);
		User user = optional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + userId + ", Tipo: " + User.class.getName()));
		
		Optional<Animal> animalOptional = repo.findById(animalId);
		Animal animal = animalOptional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + animalId + ", Tipo: " + Animal.class.getName()));
		
		user.getFavoriteAnimals().add(animal);
		userRepo.save(user);
	}
	
	public Page<Animal> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}
