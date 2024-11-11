package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.service.ResponseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;

    @GetMapping
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<ResponseDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "size", defaultValue = "2") int size,
                                    @RequestParam(name = "sort", defaultValue = "create_date") String sort) {
        return responseService.getAll(page, size, sort);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public ResponseDto getById(@PathVariable long id) {
        return responseService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('EXECUTOR')")
    public ResponseDto insert(@RequestBody ResponseDto responseDto) {
        return responseService.insert(responseDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('EXECUTOR')")
    public ResponseDto update(@PathVariable long id, @RequestBody ResponseDto responseDto) {
        responseDto.setId(id);
        return responseService.update(responseDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('EXECUTOR')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        responseService.delete(id);
    }

    @GetMapping(value = "/executor")
    @PreAuthorize("hasRole('EXECUTOR')")
    public List<ResponseDto> getAllResponsesByExecutor(@RequestParam(name = "executorId") Long executorId) {
        return responseService.getAllResponsesByExecutor(executorId);
    }
}
