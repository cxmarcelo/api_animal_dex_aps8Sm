package br.com.aps8s.resources;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.aps8s.domain.AnimalImage;
import br.com.aps8s.services.ImageService;

@RestController
@RequestMapping(value="/images")
public class ImagesResource {

	@Autowired
	private ImageService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Integer id) {
		AnimalImage image = service.getImageById(id);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getImagem());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> handleImagePost(@RequestParam("image") MultipartFile file) throws Exception {
		try {
			AnimalImage img = new AnimalImage();
			img.setImagem(file.getBytes());
			service.insertImage(img);
			return ResponseEntity.ok().body(null);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Erro pocas");
		}
	}

}