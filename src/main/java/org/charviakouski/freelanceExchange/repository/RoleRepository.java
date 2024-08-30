package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.charviakouski.freelanceExchange.model.entity.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> getAll();

    Role getById(Role role);

    boolean insert(Role role);

    boolean update(Role role);

    boolean delete(Role role);
}
