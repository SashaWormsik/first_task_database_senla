package org.charviakouski.freelanceExchange.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.service.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<FeedBackDto> getAll(@RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                    @RequestParam(name = "size", defaultValue = "2") @Min(1) int size,
                                    @RequestParam(name = "sort", defaultValue = "create_date")
                                    @Pattern(regexp = "create_date") String sort) {
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
    public FeedBackDto insert(@Valid @RequestBody FeedBackDto feedBackDto) {
        return feedbackService.insert(feedBackDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'CUSTOMER', 'EXECUTOR'})")
    public FeedBackDto update(@PathVariable long id, @Valid @RequestBody FeedBackDto feedBackDto) {
        return feedbackService.update(feedBackDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        feedbackService.delete(id);
    }

    @GetMapping(value = "/given feedbacks")
    @PreAuthorize("hasAnyRole({'CUSTOMER', 'EXECUTOR'})")
    public Page<FeedBackDto> getAllGivenFeedbacks(@RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                                  @RequestParam(name = "size", defaultValue = "2") @Min(1) int size) {
        return feedbackService.getAllGivenFeedbacks(page, size);
    }

    @GetMapping(value = "/got_feedbacks")
    @PreAuthorize("hasAnyRole({'CUSTOMER', 'EXECUTOR'})")
    public Page<FeedBackDto> getAllGotFeedbacks(@RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                                @RequestParam(name = "size", defaultValue = "2") @Min(1) int size) {
        return feedbackService.getAllGotFeedbacks(page, size);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<FeedBackDto> getAllFeedbacksByAddresseeId(@PathVariable long id,
                                                          @RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                                          @RequestParam(name = "size", defaultValue = "2") @Min(1) int size) {
        return feedbackService.getAllFeedbacksByAddresseeId(id, page, size);
    }
}
