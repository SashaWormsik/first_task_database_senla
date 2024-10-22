package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.service.ResponseService;
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
    @PreAuthorize("hasRole('USER')")
    public List<ResponseDto> getAll() {
        return responseService.getAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseDto getById(@PathVariable long id) {
        return responseService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public ResponseDto insert(@RequestBody ResponseDto responseDto) {
        return responseService.insert(responseDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseDto update(@PathVariable long id, @RequestBody ResponseDto responseDto) {
        responseDto.setId(id);
        return responseService.update(responseDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        responseService.delete(id);
    }

    @GetMapping(value = "/executor")
    @PreAuthorize("hasRole('USER')")
    public List<ResponseDto> getAllResponsesByExecutor(@RequestParam(name = "executorId") Long executorId) {
        return responseService.getAllResponsesByExecutor(executorId);

    }
}
