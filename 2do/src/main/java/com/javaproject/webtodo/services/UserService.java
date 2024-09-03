package com.javaproject.webtodo.services;

import com.javaproject.webtodo.models.User;
import com.javaproject.webtodo.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    
    
    public User saveUser(User user) throws Exception {

        if (user.getUsername() != null && user.getPassword() != null) {
            return userRepository.save(user);
        } else if ((emailExists(user.getEmail()))){
            throw new IllegalArgumentException("Email already exists");
        }
        throw new Exception("Username and password are required");

    }

    public Optional<User> findUserById(Long id) throws Exception {
        if(userRepository.findById(id).isPresent()){
            return userRepository.findById(id);
        }
        throw new Exception("User ID not found!");
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) throws Exception {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
        throw new Exception("User ID not found!");
    }

    public User updateUser(Long id, User user) throws Exception{
        if (userRepository.findById(id).isPresent() && user.getUsername() != null && user.getPassword() != null) {
            user.setId(id);
            return userRepository.save(user);

        }
        throw new Exception("User ID not found!");
    }


    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}
