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
    public ResponseEntity<List<CredentialDto>> getAll() {
        List<CredentialDto> credentials = credentialService.getAll();
        return ResponseEntity.ok().body(credentials);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CredentialDto> getById(@PathVariable(name = "id") long id) {
        CredentialDto credentialDto = credentialService.getById(id);
        return ResponseEntity.ok().body(credentialDto);
    }

    @PostMapping
    public ResponseEntity<CredentialDto> insert(@RequestBody CredentialDto credentialDto) {
        CredentialDto newCredentialDto = credentialService.insert(credentialDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCredentialDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CredentialDto> update(@PathVariable(name = "id") long id, @RequestBody CredentialDto credentialDto) {
        credentialDto.setId(id);
        CredentialDto updatedCredentialDto = credentialService.update(credentialDto);
        return ResponseEntity.ok().body(updatedCredentialDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id) {
        credentialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
