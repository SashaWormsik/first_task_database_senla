package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        feedbackService.getAll();
        return null;
    }

    public String getById(String jsonFeedbackId) {
        feedbackService.getById(entityMapper.fromJsonToDto(jsonFeedbackId, FeedBackDto.class));
        return null;
    }

    public boolean insert(String jsonFeedback) {
        feedbackService.insert(entityMapper.fromJsonToDto(jsonFeedback, FeedBackDto.class));
        return false;
    }

    public boolean update(String jsonFeedback) {
        feedbackService.update(entityMapper.fromJsonToDto(jsonFeedback, FeedBackDto.class));
        return false;
    }

    public boolean delete(String jsonFeedback) {
        feedbackService.delete(entityMapper.fromJsonToDto(jsonFeedback, FeedBackDto.class));
        return false;
    }
}
