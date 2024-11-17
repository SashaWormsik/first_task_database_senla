package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.config.TestConfig;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.RoleRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringJUnitWebConfig({TestConfig.class})
public class UserInfoRepositoryImplTest {

    private final Role ROLE_ADMIN = Role.builder()
            .name("ADMIN")
            .build();
    private final UserInfo USER = UserInfo.builder()
            .name("Nik")
            .surname("Eliot")
            .profession("teacher")
            .workExperience(5)
            .description("Normal ONE")
            .build();
    private final Credential CREDENTIAL = Credential.builder()
            .email("myEmail@gmail.com")
            .password("myPassword")
            .createDate(new Date())
            .active(true)
            .userInfo(USER)
            .role(ROLE_ADMIN)
            .build();
    private final UserInfo NEW_USER = UserInfo.builder()
            .name("Ron")
            .surname("Biller")
            .profession("doctor")
            .workExperience(10)
            .description("the best")
            .build();
    private final Credential NEW_CREDENTIAL = Credential.builder()
            .email("ronBiller@gmail.com")
            .password("myPassword")
            .createDate(new Date())
            .active(true)
            .userInfo(NEW_USER)
            .role(ROLE_ADMIN)
            .build();

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void data() {
        roleRepository.save(ROLE_ADMIN);
        USER.setCredential(CREDENTIAL);
        userInfoRepository.save(USER);
        NEW_USER.setCredential(CREDENTIAL);
        userInfoRepository.save(NEW_USER);
    }

    @Test
    @Transactional
    public void findUserInfoByCredential_EmailTest() {
        UserInfo actualUserInfo = userInfoRepository.findUserInfoByCredential_Email(CREDENTIAL.getEmail())
                .orElse(null);
        Assertions.assertEquals(USER, actualUserInfo);
    }

    @Test
    @Transactional
    public void findAllUserInfoByNameContainingIgnoreCaseAndCredential_Role_NameInTest() {
        Pageable pageable = PageRequest.of(0, 10);
        List<String> roles = List.of("ADMIN");
        UserInfo actualUserInfo = userInfoRepository
                .findAllUserInfoByNameContainingIgnoreCaseAndCredential_Role_NameIn("NI", roles, pageable)
                .getContent()
                .getFirst();
        Assertions.assertEquals(USER, actualUserInfo);
    }

    @AfterEach
    public void clean() {
        userInfoRepository.deleteById(USER.getId());
    }
}