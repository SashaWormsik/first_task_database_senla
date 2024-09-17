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
@Table(name = "user_info")
public class UserInfo {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String surname;

    @Column(length = 100)
    private String profession;

    @Column(precision = 2)
    private Integer workExperience;

    @Column(length = 500)
    private String description;

    @OneToOne(mappedBy = "userInfo")
    private Credential credential;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "executor")
    private List<Response> responses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addressee")
    private List<Feedback> addresseeFeedback;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender")
    private List<Feedback> senderFeedback;
}
