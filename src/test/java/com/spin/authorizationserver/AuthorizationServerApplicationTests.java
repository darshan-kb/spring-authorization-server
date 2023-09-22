package com.spin.authorizationserver;

import com.spin.authorizationserver.entities.Client;
import com.spin.authorizationserver.entities.User;
import com.spin.authorizationserver.repositories.ClientRepository;
import com.spin.authorizationserver.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AuthorizationServerApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	void contextLoads() {
	}

	@Test
	void addUser(){
		//BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		User u = new User("Prajwal","parjwaltarpe@gmail.com", bCryptPasswordEncoder.encode("Prajwal1212"),"ROLE_USER");
		userRepository.save(u);
	}

	@Test
	void addClient(){
		Client c = new Client("client",bCryptPasswordEncoder.encode("secret"),"https://springone.io/authorized","openid","client_secret_basic","authorization_code");
		clientRepository.save(c);
	}
}
