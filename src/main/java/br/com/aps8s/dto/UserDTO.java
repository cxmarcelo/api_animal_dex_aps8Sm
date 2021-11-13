package br.com.aps8s.dto;

import java.util.List;

import br.com.aps8s.domain.Animal;
import br.com.aps8s.domain.User;

public class UserDTO {

	private Integer id;
	private String login;
	private String password;
	private String name;
	private String email;
	
	private List<Animal> favoriteAnimals;
	
	public UserDTO() {
	}
	
	public UserDTO(User user) {
		this.email = user.getEmail();
		this.login = user.getLogin();
		this.id = user.getId();
		this.favoriteAnimals = user.getFavoriteAnimals();
		this.name = user.getName();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Animal> getFavoriteAnimals() {
		return favoriteAnimals;
	}
	public void setFavoriteAnimals(List<Animal> favoriteAnimals) {
		this.favoriteAnimals = favoriteAnimals;
	}
}
