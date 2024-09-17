package org.charviakouski.freelanceExchange.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<Credential> credentials;
}
