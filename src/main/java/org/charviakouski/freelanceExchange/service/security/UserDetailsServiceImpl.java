package org.charviakouski.freelanceExchange.service.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.entity.security.CredentialUserDetails;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Credential> optionalCredential = credentialRepository.findCredentialByEmail(email);
        Credential credential = optionalCredential.orElseThrow(() -> new UsernameNotFoundException("Credential with EMAIL :" + email + "not found"));
        Role role = Role.builder()
                .id(credential.getRole().getId())
                .name(credential.getRole().getName())
                .build();
        return CredentialUserDetails.builder()
                .id(credential.getId())
                .email(credential.getEmail())
                .password(credential.getPassword())
                .active(credential.isActive())
                .role(role)
                .build();
    }
}
