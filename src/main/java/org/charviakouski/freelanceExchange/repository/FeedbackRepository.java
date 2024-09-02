package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository {
    List<Feedback> getAll();

    Optional<Feedback> getById(Feedback feedback);

    Feedback insert(Feedback feedback);

    Feedback update(Feedback oldFeedback, Feedback newFeedback);

    boolean delete(Feedback feedback);
}
