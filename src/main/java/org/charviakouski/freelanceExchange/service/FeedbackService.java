package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FeedbackService {
    Page<FeedBackDto> getAll(int page, int size, String sort);

    FeedBackDto getById(Long id);

    FeedBackDto insert(FeedBackDto feedBackDto);

    FeedBackDto update(FeedBackDto feedBackDto);

    boolean delete(Long id);

    Page<FeedBackDto> getAllFeedbackByAddressee(int page, int size);
}
