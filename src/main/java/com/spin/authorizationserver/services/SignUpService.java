package com.spin.authorizationserver.services;

import com.spin.authorizationserver.entities.ConfirmationToken;
import com.spin.authorizationserver.entities.User;
import com.spin.authorizationserver.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SignUpService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    ConfirmationTokenService confirmationTokenService;


    public String signUpUser(User user){
        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
        if(dbUser.isPresent()) {
            if(!dbUser.get().isEnabled()){
                System.out.println("User activate the user");
                return createTokenAndSave(dbUser.get());
            }
            throw new IllegalStateException("email already taken");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return createTokenAndSave(user);
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    public String createTokenAndSave(User user){
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
}
