package br.com.aps8s.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aps8s.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByLogin(String login);

}
