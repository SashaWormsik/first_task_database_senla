package org.charviakouski.freelanceExchange.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.dto.authentication.AuthenticationRequestDto;
import org.charviakouski.freelanceExchange.model.dto.authentication.RegistrationRequestDto;
import org.charviakouski.freelanceExchange.service.AuthenticationService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public Map<Object, Object> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }

    @PostMapping("/sign-up")
    public UserInfoDto registration(@Valid @RequestBody RegistrationRequestDto requestDto) {
        return authenticationService.createNewUser(requestDto);
    }
}
