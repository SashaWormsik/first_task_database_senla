package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.charviakouski.freelanceExchange.service.FeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final EntityMapper entityMapper;

    @Override
    public FeedBackDto insert(FeedBackDto feedBackDto) {
        log.info("insert new Feedback from {} to {}", feedBackDto.getSender().getName(), feedBackDto.getAddressee().getName());
        Feedback feedback = entityMapper.fromDtoToEntity(feedBackDto, Feedback.class);
        return entityMapper.fromEntityToDto(feedbackRepository.save(feedback), FeedBackDto.class);
    }

    @Override
    public FeedBackDto update(FeedBackDto feedBackDto) {
        log.info("update new Feedback from {} to {}", feedBackDto.getSender().getName(), feedBackDto.getAddressee().getName());
        Feedback feedback = entityMapper.fromDtoToEntity(feedBackDto, Feedback.class);
        return entityMapper.fromEntityToDto(feedbackRepository.save(feedback), FeedBackDto.class);
    }

    @Override
    public FeedBackDto getById(Long id) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
        if (optionalFeedback.isEmpty()) {
            log.info("feedback with ID {} not found", id);
            throw new ServiceException("Feedback not found");
        }
        return entityMapper.fromEntityToDto(optionalFeedback.get(), FeedBackDto.class);
    }

    @Override
    public List<FeedBackDto> getAll() {
        log.info("get ALL feedbacks");
        return feedbackRepository.findAll().stream()
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete feedback with id {}", id);
        feedbackRepository.deleteById(id);
        return !feedbackRepository.existsById(id);
    }

    @Override
    public List<FeedBackDto> getAllFeedbackByAddressee(Long id) {
        log.info("get ALL feedbacks for ID {}", id);
        UserInfo userInfo = UserInfo.builder().id(id).build();
        return feedbackRepository.getAllFeedbackByAddressee(userInfo).stream()
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class))
                .toList();
    }
}
