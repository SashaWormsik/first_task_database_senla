package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.service.CredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credentials")
@RequiredArgsConstructor
public class CredentialController {

    private final CredentialService credentialService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<CredentialDto> getAll() {
        return credentialService.getAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CredentialDto getById(@PathVariable long id) {
        return credentialService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
    public CredentialDto insert(@RequestBody CredentialDto credentialDto) {
        return credentialService.insert(credentialDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
    public CredentialDto update(@PathVariable long id, @RequestBody CredentialDto credentialDto) {
        credentialDto.setId(id);
        return credentialService.update(credentialDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        credentialService.delete(id);
    }
}
