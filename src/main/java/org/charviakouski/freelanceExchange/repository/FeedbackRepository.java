package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Long, Feedback> {
    List<Feedback> getAllFeedbackByAddressee(UserInfo userInfo);
}
