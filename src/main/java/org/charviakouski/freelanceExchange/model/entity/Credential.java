package org.charviakouski.freelanceExchange.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "credential")
public class Credential {
    @Id
    private Long id;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 250, nullable = false)
    private String password;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "active", nullable = false)
    private boolean active;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

}
