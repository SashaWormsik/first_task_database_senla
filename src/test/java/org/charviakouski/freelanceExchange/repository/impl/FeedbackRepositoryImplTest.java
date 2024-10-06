package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.config.TestConfig;
import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


@SpringJUnitConfig({TestConfig.class, FeedbackRepositoryImpl.class})
public class FeedbackRepositoryImplTest {

    private final UserInfo ADDRESSEE = UserInfo.builder()
            .name("ADDRESSEE")
            .surname("COMPANY")
            .profession("COM")
            .workExperience(5)
            .description("Normal ONE")
            .build();

    private final UserInfo SENDER = UserInfo.builder()
            .name("Nik")
            .surname("Eliot")
            .profession("teacher")
            .workExperience(5)
            .description("Normal TWO")
            .build();

    private final Feedback FEEDBACK_ONE = Feedback.builder()
            .addressee(ADDRESSEE)
            .sender(SENDER)
            .createDate(new Date())
            .content("my angry review")
            .build();

    private final Feedback FEEDBACK_TWO = Feedback.builder()
            .sender(SENDER)
            .addressee(ADDRESSEE)
            .createDate(new Date())
            .content("so-so review")
            .build();

    @Autowired
    private FeedbackRepository feedbackRepository;

    @BeforeEach
    public void data() {
        feedbackRepository.create(FEEDBACK_ONE);
        feedbackRepository.create(FEEDBACK_TWO);
    }

    @Test
    @Transactional
    public void getAllFeedbackByAddresseeTest() {
        List<Feedback> expectedList = new ArrayList<>(List.of(FEEDBACK_ONE, FEEDBACK_TWO));
        List<Feedback> actualList = feedbackRepository.getAllFeedbackByAddressee(ADDRESSEE);
        actualList.sort(Comparator.comparing(Feedback::getId));
        expectedList.sort(Comparator.comparing(Feedback::getId));
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    @Transactional
    public void createFeedbackWithoutDate() {
        Feedback newFeedback = Feedback.builder()
                .addressee(ADDRESSEE)
                .sender(SENDER)
                .content("NULL")
                .build();
        Assertions.assertThrows(PropertyValueException.class,
                () -> feedbackRepository.create(newFeedback));
    }

    @AfterEach
    public void clean() {
        feedbackRepository.delete(FEEDBACK_ONE.getId());
        feedbackRepository.delete(FEEDBACK_TWO.getId());
    }
}
