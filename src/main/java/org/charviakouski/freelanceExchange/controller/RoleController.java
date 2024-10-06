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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RoleDto>> getAll() {
        List<RoleDto> roles = roleService.getAll();
        return ResponseEntity.ok().body(roles);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto getById(@PathVariable(name = "id") long id) {
        return roleService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto insert(@RequestBody RoleDto roleDto) {
        return roleService.insert(roleDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto update(@PathVariable(name = "id") long id, @RequestBody RoleDto roleDto) {
        roleDto.setId(id);
        return roleService.update(roleDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id) {
        roleService.delete(id);
    }
}
