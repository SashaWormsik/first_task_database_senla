package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.RoleDto;
import org.charviakouski.freelanceExchange.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Component
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAll() {
        List<RoleDto> roles = roleService.getAll();
        return ResponseEntity.ok().body(roles);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDto> getById(@PathVariable(name = "id") long id) {
        RoleDto roleDto = roleService.getById(id);
        return ResponseEntity.ok().body(roleDto);
    }

    @PostMapping
    public ResponseEntity<RoleDto> insert(@RequestBody RoleDto roleDto) {
        RoleDto newRoleDto = roleService.insert(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRoleDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleDto> update(@PathVariable(name = "id") long id, @RequestBody RoleDto roleDto) {
        roleDto.setId(id);
        RoleDto updatedRoleDto = roleService.update(roleDto);
        return ResponseEntity.ok().body(updatedRoleDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
