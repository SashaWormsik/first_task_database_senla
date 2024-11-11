package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.dto.authentication.AuthenticationRequestDto;
import org.charviakouski.freelanceExchange.model.dto.authentication.RegistrationRequestDto;

import java.util.Map;

public interface AuthenticationService {

    Map<Object, Object> login(AuthenticationRequestDto authenticationRequestDto);

    UserInfoDto createNewUser(RegistrationRequestDto requestDto);
}
