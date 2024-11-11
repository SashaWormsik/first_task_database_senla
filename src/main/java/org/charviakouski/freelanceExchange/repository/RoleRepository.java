package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
