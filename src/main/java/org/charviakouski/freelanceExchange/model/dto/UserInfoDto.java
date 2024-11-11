package org.charviakouski.freelanceExchange.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
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

    @Positive(message = "Work experience cannot be negative")
    @Max(value = 90, message = "Work experience cannot be more than 90 years")
    @Min(value = 0, message = "Work experience cannot be less than 90 years")
    private Integer workExperience;
    private String description;
    private CredentialDto credential;
}
