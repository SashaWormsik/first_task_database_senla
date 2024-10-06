package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
public class ResponseController {
    @Autowired
    private ResponseService responseService;
    @Autowired
    private EntityMapper entityMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseDto> getAll() {
        return responseService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto getById(@PathVariable(name = "id") long id) {
        return responseService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto insert(@RequestBody ResponseDto responseDto) {
        return responseService.insert(responseDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto update(@PathVariable(name = "id") long id, @RequestBody ResponseDto responseDto) {
        responseDto.setId(id);
        return responseService.update(responseDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id) {
        responseService.delete(id);
    }

    @GetMapping(value = "/executor")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseDto> getAllResponsesByExecutor(@RequestParam(name = "executorId") Long executorId) {
        return responseService.getAllResponsesByExecutor(executorId);

    }
}
