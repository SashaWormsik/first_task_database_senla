package org.charviakouski.freelanceExchange.model.dto;

import lombok.*;

@Data
@Builder
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
