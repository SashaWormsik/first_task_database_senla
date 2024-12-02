package org.charviakouski.freelanceExchange.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "response")
public class Response {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "response_id_seq")
    @SequenceGenerator(name = "response_id_seq", sequenceName = "response_id_seq")
    private Long id;

    @Column(name = "suggested_price", precision = 10, scale = 2)
    private BigDecimal suggestedPrice;

    @Column(name = "suggested_date")
    @Temporal(TemporalType.DATE)
    private Date suggestedDate;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.DATE)
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
