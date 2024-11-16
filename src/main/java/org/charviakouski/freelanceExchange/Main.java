package org.charviakouski.freelanceExchange;

import org.charviakouski.freelanceExchange.model.dto.authentication.RegistrationRequestDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;

public class Main {
    public static void main(String[] args) {
        EntityMapper entityMapper = new EntityMapper();
        RegistrationRequestDto requestDto = new RegistrationRequestDto();
        requestDto.setPassword("asdasd");
        requestDto.setEmail("asdasd");
        requestDto.setConfirmPassword("asdasd");
        requestDto.setRoleName("ADMIN");
        Credential credential = entityMapper.fromDtoToEntity(requestDto, Credential.class);
        System.out.println(credential);
    }
}
