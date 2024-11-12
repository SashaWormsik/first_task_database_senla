package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.entity.Feedback;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.entity.security.CredentialUserDetails;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.FeedbackRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.service.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserInfoRepository userInfoRepository;
    private final EntityMapper entityMapper;

    @Override
    public FeedBackDto insert(FeedBackDto feedBackDto) {
        log.info("insert new Feedback to {}", feedBackDto.getAddressee().getId());
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(credentialUserDetails.getId());
        if (optionalUserInfo.isEmpty()) {
            throw new ServiceException("Something has happened! User not found");
        }
        Feedback feedback = entityMapper.fromDtoToEntity(feedBackDto, Feedback.class);
        feedback.setSender(optionalUserInfo.get());
        feedback.setCreateDate(new Date());
        return entityMapper.fromEntityToDto(feedbackRepository.save(feedback), FeedBackDto.class);
    }

    @Override
    public FeedBackDto update(FeedBackDto feedBackDto) {
        log.info("update new Feedback from {} to {}", feedBackDto.getSender().getName(), feedBackDto.getAddressee().getName());
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        if (!credentialUserDetails.getId().equals(feedBackDto.getSender().getId())) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        Feedback feedback = entityMapper.fromDtoToEntity(feedBackDto, Feedback.class);
        return entityMapper.fromEntityToDto(feedbackRepository.save(feedback), FeedBackDto.class);
    }

    @Override
    public FeedBackDto getById(Long id) {
        log.info("get feedback with ID {}", id);
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
        if (optionalFeedback.isEmpty()) {
            log.info("feedback with ID {} not found", id);
            throw new ServiceException("Feedback not found");
        }
        return entityMapper.fromEntityToDto(optionalFeedback.get(), FeedBackDto.class);
    }

    @Override
    public Page<FeedBackDto> getAll(int page, int size, String sort) {
        log.info("get ALL feedbacks");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return feedbackRepository
                .findAll(pageable)
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class));
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete feedback with id {}", id);
        Feedback feedback = feedbackRepository.getReferenceById(id);
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        if (!credentialUserDetails.getId().equals(feedback.getSender().getId())) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        feedbackRepository.deleteById(id);
        return !feedbackRepository.existsById(id);
    }

    @Override
    public Page<FeedBackDto> getAllGivenFeedbacks(int page, int size) {
        log.info("get ALL given feedbacks");
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        Pageable pageable = PageRequest.of(page - 1, size);
        return feedbackRepository.findAllFeedbackBySender_Id(credentialUserDetails.getId(), pageable)
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class));
    }

    @Override
    public Page<FeedBackDto> getAllGotFeedbacks(int page, int size) {
        log.info("get ALL got feedbacks");
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        Pageable pageable = PageRequest.of(page - 1, size);
        return feedbackRepository.findAllFeedbackByAddressee_Id(credentialUserDetails.getId(), pageable)
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class));
    }

    @Override
    public Page<FeedBackDto> getAllFeedbacksByAddresseeId(long id, int page, int size) {
        log.info("get ALL feedbacks for Users with id {} ", id);
        Pageable pageable = PageRequest.of(page - 1, size);
        return feedbackRepository.findAllFeedbackByAddressee_Id(id, pageable)
                .map(feedback -> entityMapper.fromEntityToDto(feedback, FeedBackDto.class));
    }
}
