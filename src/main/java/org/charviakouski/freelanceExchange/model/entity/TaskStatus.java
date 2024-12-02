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
@Table(name = "task_status")
public class TaskStatus {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_status_id_seq")
    @SequenceGenerator(name = "task_status_id_seq", sequenceName = "task_status_id_seq")
    private Long id;

    @Column(name = "status", length = 100, unique = true, nullable = false)
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
    private List<Task> task;
}
