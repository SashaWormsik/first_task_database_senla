package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.charviakouski.freelanceExchange.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<FeedBackDto> getAll() {
        return null;
    }

    @Override
    public FeedBackDto getById(FeedBackDto feedBackDto) {
        return null;
    }

    @Override
    public FeedBackDto insert(FeedBackDto feedBackDto) {
        return null;
    }

    @Override
    public FeedBackDto update(FeedBackDto feedBackDto) {
        return null;
    }

    @Override
    public boolean delete(FeedBackDto feedBackDto) {
        return false;
    }
}
