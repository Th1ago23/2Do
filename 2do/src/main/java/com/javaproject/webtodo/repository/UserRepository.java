package com.javaproject.webtodo.repository;

import com.javaproject.webtodo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    boolean existsByEmail(String username);
    boolean existsByUsernameAndPassword(String username, String password);
}
