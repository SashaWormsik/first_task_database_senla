package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.config.TestConfig;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringJUnitConfig({TestConfig.class, UserInfoRepositoryImpl.class})
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

    @BeforeEach
    public void data() {
        USER.setCredential(CREDENTIAL);
        userInfoRepository.create(USER);
    }

    @Test
    @Transactional
    public void createCredentialTest() {
        NEW_USER.setCredential(NEW_CREDENTIAL);
        userInfoRepository.create(NEW_USER);
        UserInfo actualUserInfo = userInfoRepository.getById(NEW_USER.getId()).orElse(null);
        Assertions.assertEquals(NEW_USER, actualUserInfo);
    }

    @Test
    @Transactional
    public void getAllUserInfoByNameTest() {
        UserInfo actualUserInfo = userInfoRepository.getAllUserInfoByName(USER.getName()).getFirst();
        Assertions.assertEquals(USER, actualUserInfo);
    }

    @Test
    @Transactional
    public void getUserInfoByEmailTest() {
        UserInfo actualUserInfo = userInfoRepository.getUserInfoByEmail(CREDENTIAL.getEmail()).orElse(null);
        Assertions.assertEquals(USER, actualUserInfo);
    }

    @Test
    @Transactional
    public void getUserInfoByEmailNotExistTest() {
        UserInfo actualUserInfo = userInfoRepository.getUserInfoByEmail("email@not.exist").orElse(null);
        Assertions.assertNull(actualUserInfo);
    }

    @AfterEach
    public void clean() {
        userInfoRepository.delete(USER.getId());
    }
}
