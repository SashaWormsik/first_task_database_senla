package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll();

    RoleDto getById(Long id);

    RoleDto insert(RoleDto roleDto);

    RoleDto update(long id, RoleDto roleDto);

    boolean delete(Long id);
}
