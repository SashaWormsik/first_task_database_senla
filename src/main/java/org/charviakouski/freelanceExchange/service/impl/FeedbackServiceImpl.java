package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.MyBadRequestException;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.service.FeedbackService;
import org.charviakouski.freelanceExchange.util.PrincipalUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserInfoRepository userInfoRepository;
    private final EntityMapper entityMapper;
    private final PrincipalUtil principalUtil;

    @Override
    public FeedBackDto insert(FeedBackDto feedBackDto) {
        log.info("Insert new Feedback to {}", feedBackDto.getAddressee().getId());
        UserInfo userInfo = userInfoRepository.findById(principalUtil.getCurrentUserId())
                .orElseThrow(() -> new ServiceException("User not found with ID " + principalUtil.getCurrentUserId())); // TODO serex
        Feedback feedback = entityMapper.fromDtoToEntity(feedBackDto, Feedback.class);
        feedback.setSender(userInfo);
        feedback.setCreateDate(new Date());
        return entityMapper.fromEntityToDto(feedbackRepository.save(feedback), FeedBackDto.class);
    }

    @Override
    public FeedBackDto update(long id, FeedBackDto feedBackDto) {
        log.info("Update new Feedback with ID{}", id);
        if (!feedbackRepository.existsById(id)) {
            log.info("FeedBack with ID {} does not exist", id);
            throw new MyBadRequestException("FeedBack with ID " + id + " does not exist");
        }
        if (!principalUtil.checkId(feedBackDto.getSender().getId())) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        Feedback feedback = entityMapper.fromDtoToEntity(feedBackDto, Feedback.class);
        return entityMapper.fromEntityToDto(feedbackRepository.save(feedback), FeedBackDto.class);
    }

    @Override
    public FeedBackDto getById(Long id) {
        log.info("Get feedback with ID {}", id);
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("FeedBack with ID {} not found", id);
                    return new MyBadRequestException("FeedBack with ID " + id + " not found");
                });
        return entityMapper.fromEntityToDto(feedback, FeedBackDto.class);
    }

    @Override
    public Page<FeedBackDto> getAll(int page, int size, String sort) {
        log.info("Get ALL feedbacks");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return feedbackRepository
                .findAll(pageable)
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class));
    }

    @Override
    public boolean delete(Long id) {
        log.info("Delete feedback with id {}", id);
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("FeedBack with ID {} not found", id);
                    return new MyBadRequestException("FeedBack with ID " + id + " not found");
                });
        if (!principalUtil.checkId(feedback.getSender().getId())) {
            throw new AccessDeniedException("You cannot delete other people's data");
        }
        feedbackRepository.deleteById(id);
        return !feedbackRepository.existsById(id);
    }

    @Override
    public Page<FeedBackDto> getAllGivenFeedbacks(int page, int size) {
        log.info("get ALL given feedbacks");
        Pageable pageable = PageRequest.of(page - 1, size);
        return feedbackRepository.findAllFeedbackBySender_Id(principalUtil.getCurrentUserId(), pageable)
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class));
    }

    @Override
    public Page<FeedBackDto> getAllGotFeedbacks(int page, int size) {
        log.info("Get ALL got feedbacks");
        Pageable pageable = PageRequest.of(page - 1, size);
        return feedbackRepository.findAllFeedbackByAddressee_Id(principalUtil.getCurrentUserId(), pageable)
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class));
    }

    @Override
    public Page<FeedBackDto> getAllFeedbacksByAddresseeId(long id, int page, int size) {
        log.info("Get ALL feedbacks for Users with id {} ", id);
        Pageable pageable = PageRequest.of(page - 1, size);
        return feedbackRepository.findAllFeedbackByAddressee_Id(id, pageable)
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class));
    }
}
