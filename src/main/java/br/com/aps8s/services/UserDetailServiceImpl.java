package br.com.aps8s.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.aps8s.domain.User;
import br.com.aps8s.repositories.UserRepository;
import br.com.aps8s.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = repo.findByLogin(login);
		if(user == null) {
			throw new UsernameNotFoundException(login);
		}
		return new UserSS(user.getId(), user.getLogin(), user.getPassword(), user.getProfiles());
	}

}
