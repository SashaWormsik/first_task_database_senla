package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Feedback;

import java.util.List;

public interface FeedbackRepository {
    List<Feedback> getAll();

    Feedback getById(Feedback feedback);

    boolean insert(Feedback feedback);

    boolean update(Feedback feedback);

    boolean delete(Feedback feedback);
}
