package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        return entityMapper.fromDtoToJson(feedbackService.getAll());
    }

    public String getById(String jsonFeedbackId) {
        FeedBackDto feedBackDto = feedbackService.getById(entityMapper.fromJsonToDto(jsonFeedbackId, FeedBackDto.class));
        return entityMapper.fromDtoToJson(feedBackDto);
    }

    public String insert(String jsonFeedback) {
        FeedBackDto feedBackDto = feedbackService.insert(entityMapper.fromJsonToDto(jsonFeedback, FeedBackDto.class));
        return entityMapper.fromDtoToJson(feedBackDto);
    }

    public String update(String jsonFeedback) {
        FeedBackDto feedBackDto = feedbackService.update(entityMapper.fromJsonToDto(jsonFeedback, FeedBackDto.class));
        return entityMapper.fromDtoToJson(feedBackDto);
    }

    public boolean delete(String jsonFeedback) {
        return feedbackService.delete(entityMapper.fromJsonToDto(jsonFeedback, FeedBackDto.class));
    }

    public String getAllFeedbackByAddressee(String jsonFeedbackAddressee) {
        UserInfoDto userInfoDto = entityMapper.fromJsonToDto(jsonFeedbackAddressee, UserInfoDto.class);
        return entityMapper.fromDtoToJson(feedbackService.getAllFeedbackByAddressee(userInfoDto));
    }
}
