package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;

@Data
public class UserInfoMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserInfoDto fromEntityToDto(UserInfo userInfo) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(userInfo.getId());
        userInfoDto.setName(userInfo.getName());
        userInfoDto.setSurname(userInfo.getSurname());
        userInfoDto.setProfession(userInfo.getProfession());
        userInfoDto.setWorkExperience(userInfo.getWorkExperience());
        userInfoDto.setDescription(userInfo.getDescription());
        return userInfoDto;
    }

    public UserInfo fromDtoToEntity(UserInfoDto userInfoDto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userInfoDto.getId());
        userInfo.setName(userInfoDto.getName());
        userInfo.setSurname(userInfoDto.getSurname());
        userInfo.setProfession(userInfoDto.getProfession());
        userInfo.setWorkExperience(userInfoDto.getWorkExperience());
        userInfo.setDescription(userInfoDto.getDescription());
        return userInfo;
    }

    @SneakyThrows
    public String fromDtoToJson(UserInfoDto userInfoDto) {
        return objectMapper.writeValueAsString(userInfoDto);
    }

    @SneakyThrows
    public UserInfoDto fromJsonToDto(String json) {
        return objectMapper.readValue(json, UserInfoDto.class);
    }
}
