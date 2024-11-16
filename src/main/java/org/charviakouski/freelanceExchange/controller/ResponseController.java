package org.charviakouski.freelanceExchange.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.service.ResponseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
@Validated
public class ResponseController {

    private final ResponseService responseService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ResponseDto> getAll(@RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                    @RequestParam(name = "size", defaultValue = "2") @Min(1) int size,
                                    @RequestParam(name = "sort", defaultValue = "create_date")
                                    @Pattern(regexp = "suggested_price|suggested_date|create_date") String sort) {
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
    public ResponseDto insert(@Valid @RequestBody ResponseDto responseDto) {
        return responseService.insert(responseDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('EXECUTOR')")
    public ResponseDto update(@PathVariable long id, @Valid @RequestBody ResponseDto responseDto) {
        responseDto.setId(id);
        return responseService.update(responseDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('EXECUTOR')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        responseService.delete(id);
    }

    @GetMapping(value = "/my")
    @PreAuthorize("hasRole('EXECUTOR')")
    public List<ResponseDto> getAllResponsesByExecutor() {
        return responseService.getAllResponsesByExecutor();
    }

    @GetMapping(value = "/task/{id}")
    @PreAuthorize("hasAnyRole({'EXECUTOR', 'ADMIN', 'CUSTOMER'})")
    public Page<ResponseDto> getAllResponsesByTask(@PathVariable Long id,
                                                   @RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                                   @RequestParam(name = "size", defaultValue = "2") @Min(1) int size) {
        return responseService.getAllResponsesByTaskId(id, page, size);
    }

    @PutMapping(value = "/change_response_status/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseDto changeResponseStatus(@PathVariable Long id, @RequestBody ResponseStatusDto responseStatusDto) {
        return responseService.changeResponseStatus(id, responseStatusDto);
    }
}
