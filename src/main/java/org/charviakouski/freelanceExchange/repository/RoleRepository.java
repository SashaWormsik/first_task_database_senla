package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    List<Role> getAll();

    Optional<Role> getById(Role role);

    Role insert(Role role);

    Role update(Role newRole, Role oldRole);

    boolean delete(Role role);
}
