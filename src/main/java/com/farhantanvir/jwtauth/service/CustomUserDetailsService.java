package com.farhantanvir.jwtauth.service;

import com.farhantanvir.jwtauth.model.CustomUserDetails;
import com.farhantanvir.jwtauth.model.User;
import com.farhantanvir.jwtauth.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        return new CustomUserDetails(email,user.getPassword(),new ArrayList<>());
    }
}
