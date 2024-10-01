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
    public ResponseEntity<List<FeedBackDto>> getAll() {
        List<FeedBackDto> feedBackDtoList = feedbackService.getAll();
        return ResponseEntity.ok().body(feedBackDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FeedBackDto> getById(@PathVariable(name = "id") long id) {
        FeedBackDto feedBackDto = feedbackService.getById(id);
        return ResponseEntity.ok().body(feedBackDto);
    }

    @PostMapping
    public ResponseEntity<FeedBackDto> insert(@RequestBody FeedBackDto feedBackDto) {
        FeedBackDto newFeedBackDto = feedbackService.insert(feedBackDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFeedBackDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FeedBackDto> update(@PathVariable(name = "id") long id, @RequestBody FeedBackDto feedBackDto) {
        feedBackDto.setId(id);
        FeedBackDto updatedFeedBackDto = feedbackService.update(feedBackDto);
        return ResponseEntity.ok().body(updatedFeedBackDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/addressee")
    public ResponseEntity<List<FeedBackDto>> getAllFeedbackByAddressee(@RequestParam(name = "addresseeId") long addresseeId) {
        List<FeedBackDto> feedBackDtoList = feedbackService.getAllFeedbackByAddressee(addresseeId);
        return ResponseEntity.ok().body(feedBackDtoList);
    }
}
