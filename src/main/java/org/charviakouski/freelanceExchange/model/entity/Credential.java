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
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private UserInfo userInfo;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 250, nullable = false)
    private String password;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(nullable = false)
    private boolean active;

    @Column(length = 250)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;
}
