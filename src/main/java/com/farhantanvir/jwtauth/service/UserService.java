package com.farhantanvir.jwtauth.service;

import com.farhantanvir.jwtauth.model.User;
import com.farhantanvir.jwtauth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
