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
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public List<FeedBackDto> getAll() {
        return feedbackService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public FeedBackDto getById(@PathVariable(name = "id") long id) {
        return feedbackService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    public FeedBackDto insert(@RequestBody FeedBackDto feedBackDto) {
        return feedbackService.insert(feedBackDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public FeedBackDto update(@PathVariable(name = "id") long id, @RequestBody FeedBackDto feedBackDto) {
        feedBackDto.setId(id);
        return feedbackService.update(feedBackDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('USER')")
    public void delete(@PathVariable(name = "id") long id) {
        feedbackService.delete(id);
    }

    @GetMapping(value = "/addressee")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public List<FeedBackDto> getAllFeedbackByAddressee(@RequestParam(name = "addresseeId") long addresseeId) {
        return feedbackService.getAllFeedbackByAddressee(addresseeId);
    }
}
