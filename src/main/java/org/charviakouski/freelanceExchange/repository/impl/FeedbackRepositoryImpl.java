package org.charviakouski.freelanceExchange.repository.impl;

import jakarta.persistence.TypedQuery;
import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedbackRepositoryImpl extends AbstractRepository<Long, Feedback> implements FeedbackRepository {

    @Override
    protected Class<Feedback> getEntityClass() {
        return Feedback.class;
    }

    @Override
    public List<Feedback> getAllFeedbackByAddressee(UserInfo userInfo) {
        String jpqlQuery = "SELECT f FROM Feedback  f JOIN FETCH f.addressee JOIN FETCH f.sender WHERE f.addressee.id = :id";
        TypedQuery<Feedback> query = entityManager.createQuery(jpqlQuery, Feedback.class);
        query.setParameter("id", userInfo.getId());
        return query.getResultList();
    }
}
