package org.charviakouski.freelanceExchange.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "profession", length = 100)
    private String profession;

    @Column(name = "work_experience", precision = 2)
    private Integer workExperience;

    @Column(name = "description", length = 500)
    private String description;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userInfo", cascade = CascadeType.ALL)
    private Credential credential;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "executor", cascade = CascadeType.ALL)
    private List<Response> responses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addressee", cascade = CascadeType.ALL)
    private List<Feedback> addresseeFeedback;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Feedback> senderFeedback;
}
