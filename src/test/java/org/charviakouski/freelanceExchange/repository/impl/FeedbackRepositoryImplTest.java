package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.config.TestConfig;
import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


@SpringJUnitWebConfig({TestConfig.class})
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

            .createDate(new Date())
            .content("my angry review")
            .build();

    private final Feedback FEEDBACK_TWO = Feedback.builder()
            .createDate(new Date())
            .content("so-so review")
            .build();

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @BeforeEach
    public void data() {
        userInfoRepository.saveAll(List.of(SENDER, ADDRESSEE));
        FEEDBACK_ONE.setSender(SENDER);
        FEEDBACK_ONE.setAddressee(ADDRESSEE);
        FEEDBACK_TWO.setSender(SENDER);
        FEEDBACK_TWO.setAddressee(ADDRESSEE);
        feedbackRepository.saveAll(List.of(FEEDBACK_ONE, FEEDBACK_TWO));
    }

    @Test
    @Transactional
    public void findAllFeedbackBySender_IdTest() {
        List<Feedback> expectedList = new ArrayList<>(List.of(FEEDBACK_ONE, FEEDBACK_TWO));
        Pageable pageable = PageRequest.of(0, 10);
        List<Feedback> actualList = feedbackRepository.findAllFeedbackBySender_Id(SENDER.getId(), pageable)
                .getContent()
                .stream()
                .sorted(Comparator.comparing(Feedback::getId))
                .toList();
        expectedList.sort(Comparator.comparing(Feedback::getId));
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    @Transactional
    public void findAllFeedbackByAddressee_IdTest() {
        List<Feedback> expectedList = new ArrayList<>(List.of(FEEDBACK_ONE, FEEDBACK_TWO));
        Pageable pageable = PageRequest.of(0, 10);
        List<Feedback> actualList = feedbackRepository.findAllFeedbackByAddressee_Id(ADDRESSEE.getId(), pageable)
                .getContent()
                .stream()
                .sorted(Comparator.comparing(Feedback::getId))
                .toList();
        expectedList.sort(Comparator.comparing(Feedback::getId));
        Assertions.assertEquals(expectedList, actualList);
    }

    @AfterEach
    public void clean() {
        feedbackRepository.deleteAll();
        userInfoRepository.deleteAll();
    }
}