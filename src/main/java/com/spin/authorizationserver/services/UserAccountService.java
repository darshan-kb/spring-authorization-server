package com.spin.authorizationserver.services;

import com.spin.authorizationserver.entities.User;
import com.spin.authorizationserver.model.UserPayload;
import com.spin.authorizationserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class UserAccountService {

    @Value("${accountcreateURL}")
    private String accountcreateURL;
    @Value("${clientname}")
    private String clientname;
    @Value(("${clientpassword}"))
    private String clientpassword;

    @Autowired
    private UserRepository userRepository;

    public String createAccount(String email){
        UserPayload user = new UserPayload(userRepository.findByEmail(email).get());
        HttpEntity<UserPayload> request = new HttpEntity<>(user);
        //sendRequest(accountcreateURL,request);
        String basicauthcred = clientname+":"+clientpassword;
        byte[] b = Base64.getEncoder().encode(basicauthcred.getBytes(StandardCharsets.UTF_8));
        String basicAuthEncoded = new String(b);
        String[] csrfAndJsession = getCsrfAndJsession(basicAuthEncoded);
        postRequest(basicAuthEncoded, csrfAndJsession[0], csrfAndJsession[1], user);
        return "success";
    }

    public String[] getCsrfAndJsession(String basicAuthEncoded){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Basic "+basicAuthEncoded);
        HttpEntity<String> entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:7070/hello", HttpMethod.GET, entity, String.class);
        HttpHeaders httpResponse = response.getHeaders();
        String csrfToken = httpResponse.get("CSRF-TOKEN-VALUE").get(0);
        String jsession = httpResponse.get("Set-Cookie").get(0).split(" ")[0];
        return new String[]{csrfToken, jsession.substring(0,jsession.length()-1)};
    }

    public String sendRequest(String URL, HttpEntity request){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(accountcreateURL,request, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String postRequest(String basicAuth, String csrfToken, String jsession, UserPayload user){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.set("Authorization","Basic "+basicAuth);
        headers2.set("X-CSRF-TOKEN",csrfToken);
        headers2.set("Cookie",jsession.substring(0,jsession.length()-1));
        HttpEntity<String> entity2 = new HttpEntity(user ,headers2);

        ResponseEntity<String> response2 = restTemplate.exchange(accountcreateURL, HttpMethod.POST, entity2, String.class);

        return response2.getBody();
    }
}
