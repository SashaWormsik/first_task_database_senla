package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Optional<Role> getById(Role role) {
        return null;
    }

    @Override
    public Role insert(Role role) {
        return null;
    }

    @Override
    public Role update(Role newRole, Role oldRole) {
        return null;
    }

    @Override
    public boolean delete(Role role) {
        return false;
    }
}
