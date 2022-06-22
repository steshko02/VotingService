package com.senla.steshko.controllers;


import com.senla.steshko.api.AuthenticationService;
import com.senla.steshko.dto.entities.UserAuthDto;
import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.exception.IncorrectPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserAuthDto dto) throws IncorrectPasswordException {
            return ResponseEntity.ok(authenticationService.login(dto));
    }

    @SneakyThrows
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto)  {
        return ResponseEntity.ok(authenticationService.register(userDto));
    }

}
