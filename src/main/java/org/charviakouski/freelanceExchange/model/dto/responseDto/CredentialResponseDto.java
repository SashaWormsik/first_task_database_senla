package org.charviakouski.freelanceExchange.model.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.charviakouski.freelanceExchange.model.dto.RoleDto;

import java.util.Date;

public class CredentialResponseDto {
    private Long id;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private boolean active;
    private RoleDto role;
}
