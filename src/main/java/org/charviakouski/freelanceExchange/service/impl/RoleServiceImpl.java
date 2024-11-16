package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.MyBadRequestException;
import org.charviakouski.freelanceExchange.model.dto.RoleDto;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.RoleRepository;
import org.charviakouski.freelanceExchange.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final EntityMapper entityMapper;

    @Override
    public RoleDto insert(RoleDto roleDto) {
        log.info("Insert new Role with name {}", roleDto.getName());
        Role role = entityMapper.fromDtoToEntity(roleDto, Role.class);
        return entityMapper.fromEntityToDto(roleRepository.save(role), RoleDto.class);
    }

    @Override
    public RoleDto update(long id, RoleDto roleDto) {
        log.info("Update Role with ID {}", id);
        if (!roleRepository.existsById(id)) {
            log.info("Role with ID {} does not exist", id);
            throw new MyBadRequestException("Role with ID " + id + " does not exist");
        }
        Role role = entityMapper.fromDtoToEntity(roleDto, Role.class);
        return entityMapper.fromEntityToDto(roleRepository.save(role), RoleDto.class);
    }

    @Override
    public RoleDto getById(Long id) {
        log.info("Get Role with ID {}", id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Role with ID {} not found", id);
                    return new MyBadRequestException("Role not found");
                });
        return entityMapper.fromEntityToDto(role, RoleDto.class);
    }

    @Override
    public List<RoleDto> getAll() {
        log.info("Get ALL role");
        return roleRepository.findAll().stream()
                .map(role -> entityMapper.fromEntityToDto(role, RoleDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("Delete role with ID {}", id);
        roleRepository.deleteById(id);
        return roleRepository.existsById(id);
    }
}
