package org.charviakouski.freelanceExchange.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class CredentialDto {
    private Long id;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "This must be your email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, max = 20)
    private String password;

    private Date createDate;
    private boolean active;
    private RoleDto role;
}
