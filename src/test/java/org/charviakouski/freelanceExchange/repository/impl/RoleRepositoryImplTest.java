package org.charviakouski.freelanceExchange.repository.impl;


import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.config.TestConfig;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.repository.RoleRepository;
import org.charviakouski.freelanceExchange.util.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@SpringJUnitConfig({TestConfig.class, RoleRepositoryImpl.class})
public class RoleRepositoryImplTest {

    @Autowired
    private RoleRepository repo;

    @Test
    public void createRole() {
        Role expecteRole = TestData.createRole();
        repo.create(expecteRole);
        Role actual = repo.getById(expecteRole.getId()).get();
        Assertions.assertEquals(expecteRole, actual);
    }

    @Test
    public void getByIdTest() {
        Role expecteRole = TestData.createRole();
        repo.create(expecteRole);
        Role actual = repo.getById(expecteRole.getId()).get();
        Assertions.assertEquals(expecteRole, actual);
    }

    @Test
    public void getAllTest() {
        Role expecteRole = TestData.createRole();
        repo.create(expecteRole);
        List<Role> expectedRoleList = List.of(expecteRole);
        List<Role> actualRoleList = repo.getAll();
        Assertions.assertArrayEquals(expectedRoleList.toArray(), actualRoleList.toArray());
    }
}
