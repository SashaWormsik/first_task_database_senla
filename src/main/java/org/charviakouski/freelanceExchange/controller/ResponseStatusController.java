package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.service.ResponseStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responseStatuses")
@RequiredArgsConstructor
public class ResponseStatusController {

    private final ResponseStatusService responseStatusService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ResponseStatusDto> getAll() {
        return responseStatusService.getAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseStatusDto getById(@PathVariable long id) {
        return responseStatusService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseStatusDto insert(@RequestBody ResponseStatusDto responseStatusDto) {
        return responseStatusService.insert(responseStatusDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseStatusDto update(@PathVariable long id, @RequestBody ResponseStatusDto responseStatusDto) {
        return responseStatusService.update(id, responseStatusDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        responseStatusService.delete(id);
    }
}
