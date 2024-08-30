package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;

import java.util.List;

public interface FeedbackService {
    List<FeedBackDto> getAll();

    FeedBackDto getById(FeedBackDto feedBackDto);

    boolean insert(FeedBackDto feedBackDto);

    boolean update(FeedBackDto feedBackDto);

    boolean delete(FeedBackDto feedBackDto);
}
