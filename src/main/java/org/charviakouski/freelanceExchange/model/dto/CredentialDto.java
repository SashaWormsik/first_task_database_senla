package org.charviakouski.freelanceExchange.model.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
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
}
