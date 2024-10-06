package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserInfoServiceImplTest {

    @Mock
    private UserInfoRepository userInfoRepository;

    @Spy
    private EntityMapper entityMapper;

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
                            .name("WORKER")
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
                            .name("WORKER")
                            .build())
                    .build())
            .build();


    @Test
    public void whenGetById_thenReturnUserInfo() {
        Mockito.when(userInfoRepository.getById(2L)).thenReturn(Optional.ofNullable(TWO));
        UserInfoDto userInfoDto = userInfoServiceImpl.getById(2L);
        UserInfoDto actualUserInfoDto = entityMapper.fromEntityToDto(TWO, UserInfoDto.class);
        Assertions.assertEquals(userInfoDto, actualUserInfoDto);
        Mockito.verify(userInfoRepository, Mockito.times(1)).getById(2L);
    }

    @Test
    public void whenGetAll_thenReturnUserInfoList() {
        Mockito.when(userInfoRepository.getAll()).thenReturn(List.of(ONE, TWO));
        List<UserInfoDto> userInfoDtoList = userInfoServiceImpl.getAll();
        Assertions.assertEquals(userInfoDtoList.size(), 2);
        Mockito.verify(userInfoRepository, Mockito.times(1)).getAll();
        Mockito.verify(entityMapper, Mockito.times(2)).fromEntityToDto(Mockito.any(), Mockito.any());
    }

    @Test
    public void whenGetAllUserInfoByName_thenReturnUserInfoList() {
        Mockito.when(userInfoRepository.getAllUserInfoByName(Mockito.anyString())).thenReturn(List.of(ONE));
        List<UserInfoDto> userInfoDtoList = userInfoServiceImpl.getAllUserInfoByName("Alex");
        Assertions.assertEquals(userInfoDtoList.size(), 1);
        Mockito.verify(userInfoRepository, Mockito.times(1)).getAllUserInfoByName("Alex");
    }

    @Test
    public void whenDelete_thenReturnTrue(){
        Mockito.when(userInfoRepository.delete(1L)).thenReturn(true);
        Assertions.assertTrue(userInfoServiceImpl.delete(1L));
        Mockito.verify(userInfoRepository, Mockito.times(1)).delete(1L);
    }

    @Test
    public void whenGetUserinfoByEmail_thenReturnUserINfo(){
        Mockito.when(userInfoRepository.getUserInfoByEmail("emailAlex@gmail.com")).thenReturn(Optional.ofNullable(ONE));
        UserInfoDto userInfoDto = userInfoServiceImpl.getUserInfoByEmail("emailAlex@gmail.com");
        UserInfoDto actualUserInfoDto = entityMapper.fromEntityToDto(ONE, UserInfoDto.class);
        Assertions.assertEquals(userInfoDto, actualUserInfoDto);
        Mockito.verify(userInfoRepository).getUserInfoByEmail("emailAlex@gmail.com");
    }

    @Test
    public void whenInsertUserInfo_thenReturnUserInfo(){
        Mockito.when(userInfoRepository.create(ONE)).thenReturn(ONE);
        UserInfoDto userInfoDto = entityMapper.fromEntityToDto(ONE, UserInfoDto.class);
        UserInfoDto actualdUserInfoDto = userInfoServiceImpl.insert(userInfoDto);
        Assertions.assertEquals(userInfoDto, actualdUserInfoDto);
        Mockito.verify(userInfoRepository).create(ONE);
    }

    @Test
    public void whenUpdateUserInfo_thenReturnUserInfo(){
        Mockito.when(userInfoRepository.update(ONE)).thenReturn(ONE);
        UserInfoDto userInfoDto = entityMapper.fromEntityToDto(ONE, UserInfoDto.class);
        UserInfoDto actualdUserInfoDto = userInfoServiceImpl.update(userInfoDto);
        Assertions.assertNotNull(actualdUserInfoDto);
        Mockito.verify(userInfoRepository, Mockito.times(1)).update(ONE);
    }

}
