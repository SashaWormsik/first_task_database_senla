package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;

import java.util.List;

public interface FeedbackService {
    List<FeedBackDto> getAll();

    FeedBackDto getById(Long id);

    FeedBackDto insert(FeedBackDto feedBackDto);

    FeedBackDto update(FeedBackDto feedBackDto);

    boolean delete(Long id);

    List<FeedBackDto> getAllFeedbackByAddressee(Long id);
}
