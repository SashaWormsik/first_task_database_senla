package org.charviakouski.freelanceExchange.model.dto.authentication;

import lombok.Builder;
import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;

    public AuthenticationRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthenticationRequestDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}