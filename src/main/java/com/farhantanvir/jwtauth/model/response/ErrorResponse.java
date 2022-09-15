package com.farhantanvir.jwtauth.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    HttpStatus httpStatus;
    String message;
}
