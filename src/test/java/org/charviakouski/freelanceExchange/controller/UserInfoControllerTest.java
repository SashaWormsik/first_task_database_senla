package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.config.TestConfig;
import org.charviakouski.freelanceExchange.config.TestControllerConfig;
import org.charviakouski.freelanceExchange.config.TestSecurityConfig;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.RoleRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@SpringJUnitWebConfig({TestConfig.class, TestControllerConfig.class, TestSecurityConfig.class})
@Transactional
public class UserInfoControllerTest {

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

    private MockMvc mockMvc;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private UserInfoController userInfoController;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp(@Autowired WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        roleRepository.save(ROLE_ADMIN);
        USER.setCredential(CREDENTIAL);
        userInfoRepository.save(USER);
    }

    @Test
    @WithMockUser(username = "user1", authorities = {"ROLE_ADMIN"})
    public void getAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users?page=1&size=10&sort=name")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user1", authorities = {"ROLE_ADMIN"})
    public void getByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", USER.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();
        UserInfoDto userInfoDtoFromResponse = entityMapper.fromJsonToDto(resultJson, UserInfoDto.class);
        Assertions.assertNotNull(userInfoDtoFromResponse);
        Assertions.assertEquals(userInfoDtoFromResponse.getId(), USER.getId());
        Assertions.assertEquals(userInfoDtoFromResponse.getSurname(), USER.getSurname());
    }

    @Test
    @WithMockUser(username = "user1", authorities = {"ROLE_ADMIN"})
    public void getUserInfoByEmail() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/email?email={email}", USER.getCredential().getEmail())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();
        UserInfoDto userInfoDtoFromResponse = entityMapper.fromJsonToDto(resultJson, UserInfoDto.class);
        Assertions.assertNotNull(userInfoDtoFromResponse);
        Assertions.assertEquals(userInfoDtoFromResponse.getId(), USER.getId());
        Assertions.assertEquals(userInfoDtoFromResponse.getSurname(), USER.getSurname());
    }

    @Test
    @WithMockUser(username = "user1", authorities = {"ROLE_USER"})
    public void getUserInfoByEmailForbiddenTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/email?email={email}", USER.getCredential().getEmail())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user1", authorities = {"ROLE_ADMIN"})
    public void getUserInfoByName() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/search_executor")
                        .param("username", "NOT_EXIST_NAME")
                        .param("page", "1")
                        .param("size", "10")
                        .param("sort", "name")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String resultJson = result.getResponse().getContentAsString();
        Assertions.assertNotNull(resultJson);
        Assertions.assertTrue(resultJson.contains("\"content\":[]"));
    }

    @Test
    @WithAnonymousUser
    public void deleteTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", USER.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}
