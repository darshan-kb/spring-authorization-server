package com.spin.authorizationserver.repositories;

import com.spin.authorizationserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Transactional
    @Query("UPDATE User u SET isEnable=true WHERE u.email =:email")
    int enableUser(String email);
}
