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
    public ResponseEntity<List<ResponseStatusDto>> getAll() {
        List<ResponseStatusDto> responseStatusDtoList = responseStatusService.getAll();
        return ResponseEntity.ok().body(responseStatusDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseStatusDto> getById(@PathVariable(name = "id") long id) {
        ResponseStatusDto responseStatusDto = responseStatusService.getById(id);
        return ResponseEntity.ok().body(responseStatusDto);
    }

    @PostMapping
    public ResponseEntity<ResponseStatusDto> insert(@RequestBody ResponseStatusDto responseStatusDto) {
        ResponseStatusDto newResponseStatusDto = responseStatusService.insert(responseStatusDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newResponseStatusDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseStatusDto> update(@PathVariable(name = "id") long id, @RequestBody ResponseStatusDto responseStatusDto) {
        responseStatusDto.setId(id);
        ResponseStatusDto updatedResponseStatusDto = responseStatusService.update(responseStatusDto);
        return ResponseEntity.ok().body(updatedResponseStatusDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id) {
        responseStatusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
