package com.farhantanvir.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/hello")
public class Hello {
    @GetMapping(path = "user")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping(path = "admin")
    public String helloAdmin() {
        return "Hello Admin";
    }
}
