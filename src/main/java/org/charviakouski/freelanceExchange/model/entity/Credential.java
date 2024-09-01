package org.charviakouski.freelanceExchange.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credential {
    private Integer id;
    private String email;
    private String password;
    private Date createDate;
    private boolean active;
    private String token;
    private Role role;
}
