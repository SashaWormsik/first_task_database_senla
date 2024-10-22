package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.authentication.AuthenticationRequestDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.charviakouski.freelanceExchange.security.JwtTokenProvider;
import org.charviakouski.freelanceExchange.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager manager;
    private final JwtTokenProvider provider;
    private final CredentialRepository credentialRepository;

    @Override
    public Map<Object, Object> login(AuthenticationRequestDto authDto) {
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));
            Optional<Credential> optionalCredential = credentialRepository.getCredentialByEmail(authDto.getEmail());
            Credential credential = optionalCredential.orElseThrow(() -> new BadCredentialsException("Bad credentials"));
            String token = provider.createToken(credential.getEmail(), credential.getRole().getName());
            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("email", credential.getEmail());
            return response;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
