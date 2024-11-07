package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> getAllFeedbackByAddressee(UserInfo userInfo);
}
