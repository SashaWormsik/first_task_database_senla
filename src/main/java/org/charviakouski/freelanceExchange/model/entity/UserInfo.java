package org.charviakouski.freelanceExchange.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String name;
    private String surname;
    private String profession;
    private Integer workExperience;
    private String description;
}
