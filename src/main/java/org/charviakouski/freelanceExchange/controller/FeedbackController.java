package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.service.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<FeedBackDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "size", defaultValue = "2") int size,
                                    @RequestParam(name = "sort", defaultValue = "create_date") String sort) {
        return feedbackService.getAll(page, size, sort);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public FeedBackDto getById(@PathVariable long id) {
        return feedbackService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole({'CUSTOMER', 'EXECUTOR'})")
    public FeedBackDto insert(@RequestBody FeedBackDto feedBackDto) {
        return feedbackService.insert(feedBackDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'CUSTOMER', 'EXECUTOR'})")
    public FeedBackDto update(@PathVariable long id, @RequestBody FeedBackDto feedBackDto) {
        feedBackDto.setId(id);
        return feedbackService.update(feedBackDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        feedbackService.delete(id);
    }

    @GetMapping(value = "/left feedback")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<FeedBackDto> getAllUserFeedback(@RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(name = "size", defaultValue = "2") int size) {
        return feedbackService.getAllFeedbackByAddressee(page, size);
    }

    @GetMapping(value = "/written_feedback")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public List<FeedBackDto> getAllUserFeedback() {
        return null; // feedbackService.getAllFeedbackBySender();
    }
}
