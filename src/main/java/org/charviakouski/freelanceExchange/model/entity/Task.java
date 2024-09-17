package org.charviakouski.freelanceExchange.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column
    private Date deadline;

    @Column(nullable = false)
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private UserInfo customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private TaskStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    private List<Response> responses;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_category",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;
}
