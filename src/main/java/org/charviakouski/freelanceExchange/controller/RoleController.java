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
        return entityMapper.fromDtoToJson(roleService.getAll());
    }

    public String getById(String jsonRoleId) {
        RoleDto roleDto = roleService.getById(entityMapper.fromJsonToDto(jsonRoleId, RoleDto.class));
        return entityMapper.fromDtoToJson(roleDto);
    }

    public String insert(String jsonRole) {
        RoleDto roleDto = roleService.insert(entityMapper.fromJsonToDto(jsonRole, RoleDto.class));
        return entityMapper.fromDtoToJson(roleDto);
    }

    public String update(String jsonRole) {
        RoleDto roleDto = roleService.update(entityMapper.fromJsonToDto(jsonRole, RoleDto.class));
        return entityMapper.fromDtoToJson(roleDto);
    }

    public boolean delete(String jsonRole) {
        return roleService.delete(entityMapper.fromJsonToDto(jsonRole, RoleDto.class));
    }
}
