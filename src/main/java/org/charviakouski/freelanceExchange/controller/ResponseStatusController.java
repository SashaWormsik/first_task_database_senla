package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.service.ResponseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responseStatuses")
public class ResponseStatusController {
    @Autowired
    private ResponseStatusService responseStatusService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseStatusDto> getAll() {
        return responseStatusService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseStatusDto getById(@PathVariable(name = "id") long id) {
        return responseStatusService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseStatusDto insert(@RequestBody ResponseStatusDto responseStatusDto) {
        return responseStatusService.insert(responseStatusDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseStatusDto update(@PathVariable(name = "id") long id, @RequestBody ResponseStatusDto responseStatusDto) {
        responseStatusDto.setId(id);
        return responseStatusService.update(responseStatusDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id) {
        responseStatusService.delete(id);
    }
}
