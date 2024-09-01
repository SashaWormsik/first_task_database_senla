package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.RoleDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        roleService.getAll();
        return null;
    }

    public String getById(String jsonRoleId) {
        roleService.getById(entityMapper.fromJsonToDto(jsonRoleId, RoleDto.class));
        return null;
    }

    public String insert(String jsonRole) {
        roleService.insert(entityMapper.fromJsonToDto(jsonRole, RoleDto.class));
        return null;
    }

    public String update(String jsonRole) {
        roleService.update(entityMapper.fromJsonToDto(jsonRole, RoleDto.class));
        return null;
    }

    public boolean delete(String jsonRole) {
        roleService.delete(entityMapper.fromJsonToDto(jsonRole, RoleDto.class));
        return false;
    }
}
