package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ResponseDto>> getAll() {
        List<ResponseDto> responseDtos = responseService.getAll();
        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable(name = "id") long id) {
        ResponseDto responseDto = responseService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> insert(@RequestBody ResponseDto responseDto) {
        ResponseDto newResponseDto = responseService.insert(responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newResponseDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable(name = "id") long id, @RequestBody ResponseDto responseDto) {
        responseDto.setId(id);
        ResponseDto updatedResponseDto = responseService.update(responseDto);
        return ResponseEntity.ok().body(updatedResponseDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id) {
        responseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/executor")
    public ResponseEntity<List<ResponseDto>> getAllResponsesByExecutor(@RequestParam(name = "executorId") Long executorId) {
        List<ResponseDto> responseDtoList = responseService.getAllResponsesByExecutor(executorId);
        return ResponseEntity.ok().body(responseDtoList);

    }
}
