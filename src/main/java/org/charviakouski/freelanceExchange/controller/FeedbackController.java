package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private EntityMapper entityMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FeedBackDto> getAll() {
        return feedbackService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FeedBackDto getById(@PathVariable(name = "id") long id) {
        return feedbackService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedBackDto insert(@RequestBody FeedBackDto feedBackDto) {
        return feedbackService.insert(feedBackDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FeedBackDto update(@PathVariable(name = "id") long id, @RequestBody FeedBackDto feedBackDto) {
        feedBackDto.setId(id);
        return feedbackService.update(feedBackDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id) {
        feedbackService.delete(id);
    }

    @GetMapping(value = "/addressee")
    @ResponseStatus(HttpStatus.OK)
    public List<FeedBackDto> getAllFeedbackByAddressee(@RequestParam(name = "addresseeId") long addresseeId) {
        return feedbackService.getAllFeedbackByAddressee(addresseeId);
    }
}
