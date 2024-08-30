package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.RoleDto;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.RoleRepository;
import org.charviakouski.freelanceExchange.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<RoleDto> getAll() {
        return null;
    }

    @Override
    public RoleDto getById(RoleDto roleDto) {
        return null;
    }

    @Override
    public boolean insert(RoleDto roleDto) {
        return false;
    }

    @Override
    public boolean update(RoleDto roleDto) {
        return false;
    }

    @Override
    public boolean delete(RoleDto roleDto) {
        return false;
    }
}
