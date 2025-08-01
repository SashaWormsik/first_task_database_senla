package org.charviakouski.freelanceExchange.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_id_seq")
    @SequenceGenerator(name = "feedback_id_seq", sequenceName = "feedback_id_seq")
    private Long id;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressee_id")
    private UserInfo addressee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private UserInfo sender;
}
