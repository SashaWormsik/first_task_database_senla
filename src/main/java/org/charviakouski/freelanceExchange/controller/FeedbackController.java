package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.service.FeedbackService;
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
    @PreAuthorize("hasRole('USER')")
    public List<FeedBackDto> getAll() {
        return feedbackService.getAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public FeedBackDto getById(@PathVariable long id) {
        return feedbackService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public FeedBackDto insert(@RequestBody FeedBackDto feedBackDto) {
        return feedbackService.insert(feedBackDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public FeedBackDto update(@PathVariable long id, @RequestBody FeedBackDto feedBackDto) {
        feedBackDto.setId(id);
        return feedbackService.update(feedBackDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void delete(@PathVariable long id) {
        feedbackService.delete(id);
    }

    @GetMapping(value = "/addressee")
    @PreAuthorize("hasRole('USER')")
    public List<FeedBackDto> getAllFeedbackByAddressee(@RequestParam long addresseeId) {
        return feedbackService.getAllFeedbackByAddressee(addresseeId);
    }
}
