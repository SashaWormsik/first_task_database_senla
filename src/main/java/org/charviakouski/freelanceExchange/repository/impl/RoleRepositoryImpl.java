package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Role getById(Role role) {
        return null;
    }

    @Override
    public boolean insert(Role role) {
        return false;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }

    @Override
    public boolean delete(Role role) {
        return false;
    }
}
