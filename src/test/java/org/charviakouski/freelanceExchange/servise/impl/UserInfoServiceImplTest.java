package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.util.PrincipalUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserInfoServiceImplTest {

    @Mock
    private UserInfoRepository userInfoRepository;
    @Mock
    private CredentialRepository credentialRepository;

    @Mock
    private PrincipalUtil principalUtil;


    @Spy
    private EntityMapper entityMapper;

    @Spy
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserInfoServiceImpl userInfoServiceImpl;

    private static final UserInfo ONE = UserInfo.builder()
            .id(1L)
            .name("Alex")
            .surname("Worms")
            .profession("constructor")
            .workExperience(10)
            .description("cool")
            .credential(Credential.builder()
                    .id(1L)
                    .email("emailAlex@gmail.com")
                    .password("passwordAlex")
                    .active(true)
                    .createDate(new Date())
                    .role(Role.builder()
                            .id(1L)
                            .name("ADMIN")
                            .build())
                    .build())
            .build();

    private static final UserInfo TWO = UserInfo.builder()
            .id(2L)
            .name("Sasha")
            .surname("Le Wan Dovsky")
            .profession("football players")
            .workExperience(30)
            .description("The golden ball")
            .credential(Credential.builder()
                    .id(1L)
                    .email("SASHA@gmail.com")
                    .password("passWORD")
                    .active(true)
                    .createDate(new Date())
                    .role(Role.builder()
                            .id(1L)
                            .name("EXECUTOR")
                            .build())
                    .build())
            .build();


    @Test
    public void whenGetById_thenReturnUserInfo() {
        Mockito.when(userInfoRepository.findById(2L)).thenReturn(Optional.ofNullable(TWO));
        UserInfoDto userInfoDto = userInfoServiceImpl.getById(2L);
        UserInfoDto actualUserInfoDto = entityMapper.fromEntityToDto(TWO, UserInfoDto.class);
        Assertions.assertEquals(userInfoDto, actualUserInfoDto);
        Mockito.verify(userInfoRepository, Mockito.times(1)).findById(2L);
    }

    @Test
    public void whenGetAll_thenReturnUserInfoList() {
        Page<UserInfo> page = new PageImpl<>(List.of(ONE, TWO));
        Mockito.when(userInfoRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        List<UserInfoDto> userInfoDtoList = userInfoServiceImpl.getAll(1, 2, "name").getContent();
        Assertions.assertEquals(userInfoDtoList.size(), 2);
        Mockito.verify(userInfoRepository, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
        Mockito.verify(entityMapper, Mockito.times(2)).fromEntityToDto(Mockito.any(), Mockito.any());
    }

    @Test
    public void whenGetAllExecutorByLikeName_thenReturnUserInfoList() {
        Page<UserInfo> page = new PageImpl<>(List.of(ONE, TWO));
        Mockito.when(userInfoRepository
                        .findAllUserInfoByNameContainingIgnoreCaseAndCredential_Role_NameIn(Mockito.anyString(), Mockito.anyList(), Mockito.any(Pageable.class)))
                .thenReturn(page);
        List<UserInfoDto> userInfoDtoList = userInfoServiceImpl
                .getAllExecutorByLikeName("Alex", 1, 2, "name").getContent();
        Assertions.assertEquals(userInfoDtoList.size(), 2);
        Mockito.verify(userInfoRepository, Mockito.times(1))
                .findAllUserInfoByNameContainingIgnoreCaseAndCredential_Role_NameIn(Mockito.anyString(), Mockito.anyList(), Mockito.any(Pageable.class));
        ;
    }

    @Test
    public void whenDelete_thenReturnTrue() {
        Mockito.doNothing().when(userInfoRepository).deleteById(Mockito.anyLong());
        Mockito.when(principalUtil.checkId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(userInfoRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Assertions.assertTrue(userInfoServiceImpl.delete(1L));
        Mockito.verify(userInfoRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    public void whenGetUserinfoByEmail_thenReturnUserINfo() {
        Mockito.when(userInfoRepository.findUserInfoByCredential_Email(Mockito.anyString())).thenReturn(Optional.ofNullable(ONE));
        UserInfoDto userInfoDto = userInfoServiceImpl.getUserInfoByEmail("emailAlex@gmail.com");
        UserInfoDto actualUserInfoDto = entityMapper.fromEntityToDto(ONE, UserInfoDto.class);
        Assertions.assertEquals(userInfoDto, actualUserInfoDto);
        Mockito.verify(userInfoRepository).findUserInfoByCredential_Email(Mockito.anyString());
    }

    @Test
    public void whenInsertUserInfo_thenReturnUserInfo() {
        Mockito.when(userInfoRepository.save(Mockito.any(UserInfo.class))).thenReturn(ONE);
        Mockito.when(credentialRepository.existsCredentialByEmail(Mockito.anyString())).thenReturn(false);
        UserInfoDto userInfoDto = entityMapper.fromEntityToDto(ONE, UserInfoDto.class);
        CredentialDto credentialDto = entityMapper.fromEntityToDto(ONE.getCredential(), CredentialDto.class);
        UserInfoDto actualdUserInfoDto = userInfoServiceImpl.insert(credentialDto);
        Assertions.assertEquals(userInfoDto, actualdUserInfoDto);
        Mockito.verify(userInfoRepository).save(Mockito.any(UserInfo.class));
    }

    @Test
    public void whenUpdateUserInfo_thenReturnUserInfo() {
        Mockito.when(userInfoRepository.save(Mockito.any(UserInfo.class))).thenReturn(ONE);
        Mockito.when(principalUtil.checkId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(userInfoRepository.existsById(Mockito.anyLong())).thenReturn(true);
        UserInfoDto userInfoDto = entityMapper.fromEntityToDto(ONE, UserInfoDto.class);
        UserInfoDto actualdUserInfoDto = userInfoServiceImpl.update(1L, userInfoDto);
        Assertions.assertNotNull(actualdUserInfoDto);
        Mockito.verify(userInfoRepository, Mockito.times(1)).save(Mockito.any(UserInfo.class));
    }
}