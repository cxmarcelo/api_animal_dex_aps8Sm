package br.com.aps8s.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.aps8s.domain.User;
import br.com.aps8s.dto.UserDTO;
import br.com.aps8s.repositories.UserRepository;
import br.com.aps8s.services.exceptions.DataIntegrityException;
import br.com.aps8s.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public User find(Integer id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}

	public User insert(User obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	
	public UserDTO register(UserDTO userDTO) {
		User userExist = repo.findByLogin(userDTO.getLogin());
		if(userExist != null) {
			throw new DataIntegrityException("Já existe um usuário registrado com esse login.");
		}
		User user = new User(null, userDTO.getLogin(), pe.encode(userDTO.getPassword()), userDTO.getName(), userDTO.getEmail(), userDTO.getFavoriteAnimals());
		return new UserDTO(repo.save(user));
	}

}
