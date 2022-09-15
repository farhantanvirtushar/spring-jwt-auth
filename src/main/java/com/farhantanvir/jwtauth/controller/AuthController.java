package com.farhantanvir.jwtauth.controller;

import com.farhantanvir.jwtauth.auth.JwtUtil;
import com.farhantanvir.jwtauth.model.User;
import com.farhantanvir.jwtauth.model.request.LoginReq;
import com.farhantanvir.jwtauth.model.response.ErrorResponse;
import com.farhantanvir.jwtauth.model.response.LoginRes;
import com.farhantanvir.jwtauth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private JwtUtil jwtUtil;
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;

    }

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {


        LoginRes loginRes = new LoginRes();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            User user = userService.findByEmail(loginReq.getEmail());
            String token = jwtUtil.createToken(user);
            loginRes.setEmail(user.getEmail());
            loginRes.setToken(token);

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
