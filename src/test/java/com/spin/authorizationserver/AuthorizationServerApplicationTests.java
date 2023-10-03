package com.spin.authorizationserver;

import com.spin.authorizationserver.entities.Client;
import com.spin.authorizationserver.entities.User;
import com.spin.authorizationserver.model.UserPayload;
import com.spin.authorizationserver.repositories.ClientRepository;
import com.spin.authorizationserver.repositories.UserRepository;
import com.spin.authorizationserver.services.UserAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest
class AuthorizationServerApplicationTests {


	@Autowired
	UserRepository userRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserAccountService userAccountService;

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
		Client c1 = new Client("client",bCryptPasswordEncoder.encode("secret"),"http://localhost:3000/authorized","openid","client_secret_basic","authorization_code");
		clientRepository.save(c1);
		Client c2 = new Client("spin-client",bCryptPasswordEncoder.encode("secret"),"http://localhost:9090/authorized","openid","client_secret_basic","client_credentials");
		clientRepository.save(c2);
		Client c = new Client("account",bCryptPasswordEncoder.encode("#Big2120"),"http://localhost:7070/authorized","openid","client_secret_basic","client_credentials");
		clientRepository.save(c);
	}

	@Test
	void createAccount(){
		userAccountService.createAccount("darshanbehere@gmail.com");
	}

	@Test
	void getRequest(){
		byte[] b = Base64.getEncoder().encode("auth-server:Henry2023!".getBytes(StandardCharsets.UTF_8));
		String basicAuth = new String(b);
		//System.out.println(new String(b));
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Basic "+basicAuth);
		HttpEntity<String> entity = new HttpEntity(headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:7070/hello", HttpMethod.GET, entity, String.class);
		System.out.println(response);
		HttpHeaders httpResponse = response.getHeaders();
		String csrfToken = httpResponse.get("CSRF-TOKEN-VALUE").get(0);
		String jsession = httpResponse.get("Set-Cookie").get(0).split(" ")[0];
		System.out.println(jsession.substring(0,jsession.length()-1));
		System.out.println(csrfToken);
		//postRequest(basicAuth,csrfToken,jsession);
		//postRequest(basicAuth,csrfToken,jsession);
	}

	@Test
	void postRequest(String basicAuth, String csrfToken, String jsession){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.set("Authorization","Basic "+basicAuth);
		headers2.set("X-CSRF-TOKEN",csrfToken);
		headers2.set("Cookie",jsession.substring(0,jsession.length()-1));
		HttpEntity<String> entity2 = new HttpEntity(new UserPayload("user","email","user") ,headers2);

		ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:7070/account/create", HttpMethod.POST, entity2, String.class);

		System.out.println(response2);
	}
}
