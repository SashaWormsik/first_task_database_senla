package org.charviakouski.freelanceExchange.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class UserInfoDto {
    private Long id;
    private String name;
    private String surname;
    private String profession;
    private Integer workExperience;
    private String description;
    private CredentialDto credential;
}
