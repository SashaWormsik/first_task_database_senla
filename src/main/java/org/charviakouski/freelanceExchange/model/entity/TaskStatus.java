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
@Table(name = "task_status")
public class TaskStatus {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taskStatus")
    private List<Task> task;
}
