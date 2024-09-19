package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.springframework.stereotype.Component;

@Component
public class FeedbackRepositoryImpl extends AbstractRepository<Long, Feedback> implements FeedbackRepository {

    @Override
    protected Class<Feedback> getEntityClass() {
        return Feedback.class;
    }
}
