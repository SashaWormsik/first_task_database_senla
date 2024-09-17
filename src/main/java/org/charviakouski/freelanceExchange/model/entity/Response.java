package org.charviakouski.freelanceExchange.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "response")
public class Response {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(precision = 10, scale = 2)
    private BigDecimal suggestedPrice;

    @Column
    private Date suggestedDate;

    @Column(nullable = false)
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_id")
    private UserInfo executor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_status_id")
    private ResponseStatus responseStatus;
}
