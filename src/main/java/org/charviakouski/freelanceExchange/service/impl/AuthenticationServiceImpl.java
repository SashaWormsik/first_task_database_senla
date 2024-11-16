package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.dto.authentication.AuthenticationRequestDto;
import org.charviakouski.freelanceExchange.model.dto.authentication.RegistrationRequestDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.charviakouski.freelanceExchange.repository.RoleRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.security.JwtTokenProvider;
import org.charviakouski.freelanceExchange.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager manager;
    private final JwtTokenProvider provider;
    private final RoleRepository roleRepository;
    private final CredentialRepository credentialRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityMapper entityMapper;

    @Override
    public Map<Object, Object> login(AuthenticationRequestDto authDto) {
        try {
            Optional<Credential> optionalCredential = credentialRepository.findCredentialByEmailAndActiveTrue(authDto.getEmail());
            Credential credential = optionalCredential.orElseThrow(() -> new BadCredentialsException("Bad credential. Or Your profile is blocked"));
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));
            String token = provider.createToken(credential.getEmail(), credential.getRole().getName());
            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("email", credential.getEmail());
            return response;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public UserInfoDto createNewUser(RegistrationRequestDto requestDto) {
        if (credentialRepository.existsCredentialByEmail(requestDto.getEmail())) {
            throw new BadCredentialsException("This email address already exists");
        }
        if (!requestDto.getPassword().equals(requestDto.getConfirmPassword())) {
            throw new BadCredentialsException("Invalid confirm password");
        }
        UserInfo userInfo = new UserInfo();
        Credential credential = entityMapper.fromDtoToEntity(requestDto, Credential.class);
        Role role = roleRepository.findByName(credential.getRole().getName());
        credential.setRole(role);
        credential.setUserInfo(userInfo);
        credential.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userInfo.setCredential(credential);
        return entityMapper.fromEntityToDto(userInfoRepository.save(userInfo), UserInfoDto.class);
    }
}
