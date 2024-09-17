package org.charviakouski.freelanceExchange.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credential")
public class Credential {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserInfo userInfo;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 250, nullable = false)
    private String password;

    @Column(nullable = false)
    private Date createDate;

    @Column(nullable = false)
    private boolean active;

    @Column(length = 250)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
}
