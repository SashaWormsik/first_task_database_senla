package org.charviakouski.freelanceExchange.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.charviakouski.freelanceExchange.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public FeedBackDto insert(FeedBackDto feedBackDto) {
        log.info("insert new Feedback from {} to {}", feedBackDto.getSender().getName(), feedBackDto.getAddressee().getName());
        Feedback feedback = entityMapper.fromDtoToEntity(feedBackDto, Feedback.class);
        return entityMapper.fromEntityToDto(feedbackRepository.create(feedback), FeedBackDto.class);
    }

    @Override
    public FeedBackDto update(FeedBackDto feedBackDto) {
        log.info("update new Feedback from {} to {}", feedBackDto.getSender().getName(), feedBackDto.getAddressee().getName());
        Feedback feedback = entityMapper.fromDtoToEntity(feedBackDto, Feedback.class);
        return entityMapper.fromEntityToDto(feedbackRepository.update(feedback), FeedBackDto.class);
    }

    @Override
    public FeedBackDto getById(FeedBackDto feedBackDto) {
        Optional<Feedback> optionalFeedback = feedbackRepository.getById(feedBackDto.getId());
        if (optionalFeedback.isEmpty()) {
            log.info("feedback with ID {} not found", feedBackDto.getId());
            throw new ServiceException("Feedback not found");
        }
        return entityMapper.fromEntityToDto(optionalFeedback.get(), FeedBackDto.class);
    }

    @Override
    public List<FeedBackDto> getAll() {
        log.info("get ALL feedbacks");
        return feedbackRepository.getAll().stream()
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class))
                .toList();
    }

    @Override
    public boolean delete(FeedBackDto feedBackDto) {
        log.info("delete feedback with id {}", feedBackDto.getId());
        feedbackRepository.delete(feedBackDto.getId());
        return feedbackRepository.getById(feedBackDto.getId()).isEmpty();
    }

    @Override
    public List<FeedBackDto> getAllFeedbackByAddressee(UserInfoDto userInfoDto) {
        log.info("get ALL feedbacks for  {}", userInfoDto.getName());
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        return feedbackRepository.getAllFeedbackByAddressee(userInfo).stream()
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class))
                .toList();
    }
}
