package com.farhantanvir.jwtauth.repository;

import com.farhantanvir.jwtauth.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public User findUserByEmail(String email){
        return new User(email,"123456","Farhan","Tanvir");
    }
}
