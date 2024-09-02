package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FeedbackRepositoryImpl implements FeedbackRepository {
    @Override
    public List<Feedback> getAll() {
        return null;
    }

    @Override
    public Optional<Feedback> getById(Feedback feedback) {
        return null;
    }

    @Override
    public Feedback insert(Feedback feedback) {
        return null;
    }

    @Override
    public Feedback update(Feedback newFeedback, Feedback oldFeedback) {
        return null;
    }

    @Override
    public boolean delete(Feedback feedback) {
        return false;
    }
}
