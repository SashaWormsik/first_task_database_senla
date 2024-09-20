package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.RoleDto;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.RoleRepository;
import org.charviakouski.freelanceExchange.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public RoleDto insert(RoleDto roleDto) {
        Role role = entityMapper.fromDtoToEntity(roleDto, Role.class);
        return entityMapper.fromEntityToDto(roleRepository.create(role), RoleDto.class);
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        Role role = entityMapper.fromDtoToEntity(roleDto, Role.class);
        return entityMapper.fromEntityToDto(roleRepository.update(role), RoleDto.class);
    }

    @Override
    public RoleDto getById(RoleDto roleDto) {
        Optional<Role> optionalRole = roleRepository.getById(roleDto.getId());
        if (optionalRole.isEmpty()) {
            throw new ServiceException("Role not found");
        }
        return entityMapper.fromEntityToDto(optionalRole.get(), RoleDto.class);
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.getAll().stream()
                .map(role -> entityMapper.fromEntityToDto(role, RoleDto.class))
                .toList();
    }

    @Override
    public boolean delete(RoleDto roleDto) {
        roleRepository.delete(roleDto.getId());
        return roleRepository.getById(roleDto.getId()).isEmpty();
    }
}
