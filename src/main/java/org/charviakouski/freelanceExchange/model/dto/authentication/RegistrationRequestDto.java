package org.charviakouski.freelanceExchange.model.dto.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "This must be your email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, max = 20)
    private String password;

    @NotBlank(message = "Confirm password cannot be empty")
    @Size(min = 5, max = 20)
    private String confirmPassword;

    @NotBlank(message = "Role cannot be empty")
    @Pattern(regexp = "CUSTOMER|EXECUTOR")
    private String roleName;
}
