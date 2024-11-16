package org.charviakouski.freelanceExchange.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.service.CredentialService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credentials")
@RequiredArgsConstructor
public class CredentialController {

    private final CredentialService credentialService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<CredentialDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                      @RequestParam(name = "size", defaultValue = "2") int size,
                                      @RequestParam(name = "sort", defaultValue = "email")
                                      @Pattern(regexp = "active|create_date|email") String sort) {
        return credentialService.getAll(page, size, sort);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public CredentialDto getById(@PathVariable long id) {
        return credentialService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CredentialDto insert(@Valid @RequestBody CredentialDto credentialDto) {
        return credentialService.insert(credentialDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public CredentialDto update(@PathVariable long id, @Valid @RequestBody CredentialDto credentialDto) {
        return credentialService.update(id, credentialDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        credentialService.delete(id);
    }
}
