package org.charviakouski.freelanceExchange.model.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.charviakouski.freelanceExchange.model.dto.RoleDto;

import java.util.Date;

public class CredentialResponseDto {
    private Long id;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private boolean active;
    private RoleDto role;
}
