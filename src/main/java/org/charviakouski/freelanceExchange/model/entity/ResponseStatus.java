package org.charviakouski.freelanceExchange.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "response_status")
public class ResponseStatus {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", length = 100, unique = true, nullable = false)
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "responseStatus")
    private List<Response> responses;
}
