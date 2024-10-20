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
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public List<ResponseStatusDto> getAll() {
        return responseStatusService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseStatusDto getById(@PathVariable(name = "id") long id) {
        return responseStatusService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseStatusDto insert(@RequestBody ResponseStatusDto responseStatusDto) {
        return responseStatusService.insert(responseStatusDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseStatusDto update(@PathVariable(name = "id") long id, @RequestBody ResponseStatusDto responseStatusDto) {
        responseStatusDto.setId(id);
        return responseStatusService.update(responseStatusDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('USER')")
    public void delete(@PathVariable(name = "id") long id) {
        responseStatusService.delete(id);
    }
}
