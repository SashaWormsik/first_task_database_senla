package org.charviakouski.freelanceExchange.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credential")
public class Credential {
    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private UserInfo userInfo;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 250, nullable = false)
    private String password;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "token", length = 250)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;
}
