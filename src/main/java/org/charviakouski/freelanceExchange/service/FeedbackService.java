package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.springframework.data.domain.Page;

public interface FeedbackService {
    Page<FeedBackDto> getAll(int page, int size, String sort);

    FeedBackDto getById(Long id);

    FeedBackDto insert(FeedBackDto feedBackDto);

    FeedBackDto update(long id, FeedBackDto feedBackDto);

    boolean delete(Long id);

    Page<FeedBackDto> getAllGivenFeedbacks(int page, int size);

    Page<FeedBackDto> getAllGotFeedbacks(int page, int size);

    Page<FeedBackDto> getAllFeedbacksByAddresseeId(long id, int page, int size);
}
