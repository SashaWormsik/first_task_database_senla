package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedbackRepositoryImpl implements FeedbackRepository {
    @Override
    public List<Feedback> getAll() {
        return null;
    }

    @Override
    public Feedback getById(Feedback feedback) {
        return null;
    }

    @Override
    public boolean insert(Feedback feedback) {
        return false;
    }

    @Override
    public boolean update(Feedback feedback) {
        return false;
    }

    @Override
    public boolean delete(Feedback feedback) {
        return false;
    }
}
