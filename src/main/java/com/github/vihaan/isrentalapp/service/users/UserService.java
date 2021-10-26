package com.github.vihaan.isrentalapp.service.users;

import com.github.vihaan.isrentalapp.users.entities.UserEntity;
import com.github.vihaan.isrentalapp.users.entities.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id){
        return userRepository.findById(id);
    }

    Optional<UserEntity> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
