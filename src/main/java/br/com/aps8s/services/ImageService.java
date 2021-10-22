package br.com.aps8s.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aps8s.domain.AnimalImage;
import br.com.aps8s.repositories.ImageRepository;
import br.com.aps8s.services.exceptions.ObjectNotFoundException;

@Service
public class ImageService {

	@Autowired
	private ImageRepository repo;
	
	public AnimalImage getImageById(Integer id) {
		Optional<AnimalImage> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + AnimalImage.class.getName()));
	}

	public AnimalImage insertImage(AnimalImage obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}
