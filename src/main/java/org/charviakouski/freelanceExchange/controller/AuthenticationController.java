package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.authentication.AuthenticationRequestDto;
import org.charviakouski.freelanceExchange.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public Map<Object, Object> login(@RequestBody AuthenticationRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }
}
