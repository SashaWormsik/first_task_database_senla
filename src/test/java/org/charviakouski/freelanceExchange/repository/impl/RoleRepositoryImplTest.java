package org.charviakouski.freelanceExchange.repository.impl;


import org.charviakouski.freelanceExchange.config.TestConfig;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.repository.RoleRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@SpringJUnitWebConfig({TestConfig.class, RoleRepositoryImpl.class})
public class RoleRepositoryImplTest {

    private final Role ROLE_WORKER = Role.builder()
            .name("WORKER")
            .build();
    private final Role ROLE_COMPANY = Role.builder()
            .name("COMPANY")
            .build();

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void createData() {
        roleRepository.create(ROLE_WORKER);
        roleRepository.create(ROLE_COMPANY);
    }


    @Test
    @Transactional
    public void createRole() {
        Role expecteRole = Role.builder().name("ADMIN").build();
        roleRepository.create(expecteRole);
        Role actual = roleRepository.getById(expecteRole.getId()).orElse(null);
        Assertions.assertEquals(expecteRole, actual);
    }

    @Test
    @Transactional
    public void createExistRole() {  // FIXME
        Role role = Role.builder().name("WORKER").build();
        try {
            roleRepository.create(role);
            Assertions.fail("The ROLE with name 'WORKER' already exists");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Transactional
    public void createExistRoleWithException() {
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> roleRepository.create(Role.builder().name("WORKER").build()));
    }

    @Test
    @Transactional
    public void getByIdTest() {
        Role expecteRole = ROLE_WORKER;
        Role actual = roleRepository.getById(expecteRole.getId()).orElse(null);
        Assertions.assertEquals(expecteRole, actual);
    }

    @Test
    @Transactional
    public void getByIdNotExist() {
        Optional<Role> actual = roleRepository.getById(999L);
        Assertions.assertNull(actual.orElse(null));
    }

    @Test
    @Transactional
    public void getAllTest() {
        List<Role> expectedRoleList = new ArrayList<>(List.of(ROLE_WORKER, ROLE_COMPANY));
        List<Role> actualRoleList = roleRepository.getAll();
        expectedRoleList.sort(Comparator.comparing(Role::getId));
        actualRoleList.sort(Comparator.comparing(Role::getId));
        Assertions.assertIterableEquals(expectedRoleList, actualRoleList);
    }

    @AfterEach
    public void clean() {
        roleRepository.delete(ROLE_WORKER.getId());
        roleRepository.delete(ROLE_COMPANY.getId());
    }
}
