package br.com.aps8s.security;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(Integer id, String login) {
		HashMap<String, Object> claims = new HashMap<String, Object>();
		claims.put("login", login);
		claims.put("id", id);
		
		return Jwts.builder()
				.setSubject(login)
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}	
}
