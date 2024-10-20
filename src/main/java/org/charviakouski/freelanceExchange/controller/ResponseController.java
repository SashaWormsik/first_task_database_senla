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
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public List<ResponseDto> getAll() {
        return responseService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseDto getById(@PathVariable(name = "id") long id) {
        return responseService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseDto insert(@RequestBody ResponseDto responseDto) {
        return responseService.insert(responseDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseDto update(@PathVariable(name = "id") long id, @RequestBody ResponseDto responseDto) {
        responseDto.setId(id);
        return responseService.update(responseDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id) {
        responseService.delete(id);
    }

    @GetMapping(value = "/executor")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public List<ResponseDto> getAllResponsesByExecutor(@RequestParam(name = "executorId") Long executorId) {
        return responseService.getAllResponsesByExecutor(executorId);

    }
}
