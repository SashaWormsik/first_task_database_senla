package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.RoleDto;
import org.charviakouski.freelanceExchange.model.entity.Role;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll();

    RoleDto getById(RoleDto roleDto);

    boolean insert(RoleDto roleDto);

    boolean update(RoleDto roleDto);

    boolean delete(RoleDto roleDto);
}
