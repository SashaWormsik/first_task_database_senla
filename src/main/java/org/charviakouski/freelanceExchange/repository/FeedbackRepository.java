package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Page<Feedback> findAllFeedbackBySender_Id(long id, Pageable pageable);

    Page<Feedback> findAllFeedbackByAddressee_Id(long id, Pageable pageable);
}
