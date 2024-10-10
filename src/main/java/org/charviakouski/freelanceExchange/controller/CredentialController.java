package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credentials")
public class CredentialController {
    @Autowired
    private CredentialService credentialService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CredentialDto> getAll() {
        return credentialService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CredentialDto getById(@PathVariable(name = "id") long id) {
        return credentialService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CredentialDto insert(@RequestBody CredentialDto credentialDto) {
        return credentialService.insert(credentialDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CredentialDto update(@PathVariable(name = "id") long id, @RequestBody CredentialDto credentialDto) {
        credentialDto.setId(id);
        return credentialService.update(credentialDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id) {
        credentialService.delete(id);
    }
}
