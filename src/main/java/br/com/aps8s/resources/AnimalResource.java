package br.com.aps8s.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.aps8s.domain.Animal;
import br.com.aps8s.services.AnimalService;

@RestController
@RequestMapping(value="/animals")
public class AnimalResource {
	
	@Autowired
	private AnimalService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> find() {
		return ResponseEntity.ok().body("FUNCIONANDO");
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Animal> find(@PathVariable Integer id) {
		Animal obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Animal>> findAll() {
		List<Animal> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/favorites/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Animal>> findAllFavoritesByUserId(@PathVariable Integer id) {
		List<Animal> list = service.findAllFavoritesByUser(id);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/deleteByUser/{userId}/{animalId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteByUserIdAndAnimalId(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "animalId") Integer animalId) {
		service.deleteAnimalByUserId(userId, animalId);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/addAnimalFavorite/{userId}/{animalId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addByUserIdAndAnimalId(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "animalId") Integer animalId) {
		service.addAnimalFavorite(userId, animalId);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Validated @RequestBody Animal obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Animal>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Animal> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}
