package org.charviakouski.freelanceExchange.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class CredentialDto {
    private Long id;
    private String email;
    private String password;
    private Date createDate;
    private boolean active;
    private String token;
    private RoleDto role;
    private UserInfoDto userInfo;
}
