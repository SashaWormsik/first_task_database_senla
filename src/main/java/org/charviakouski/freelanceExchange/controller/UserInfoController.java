package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private EntityMapper entityMapper;

    @GetMapping
    public ResponseEntity<List<UserInfoDto>> getAll() {
        List<UserInfoDto> userInfoDtoList = userInfoService.getAll();
        return ResponseEntity.ok().body(userInfoDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserInfoDto> getById(@PathVariable(name = "id") long id) {
        UserInfoDto userInfoDto = userInfoService.getById(id);
        return ResponseEntity.ok().body(userInfoDto);
    }

    @PostMapping
    public ResponseEntity<UserInfoDto> insert(@RequestBody UserInfoDto userInfoDto) {
        UserInfoDto newUserInfoDto = userInfoService.insert(userInfoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUserInfoDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserInfoDto> update(@PathVariable(name = "id") long id, @RequestBody UserInfoDto userInfoDto) {
        userInfoDto.setId(id);
        UserInfoDto updatedUserInfoDto = userInfoService.update(userInfoDto);
        return ResponseEntity.ok().body(updatedUserInfoDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id) {
        userInfoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/name")
    public ResponseEntity<List<UserInfoDto>> getAllUserInfoByName(@RequestParam(name = "username") String username) {
        List<UserInfoDto> userInfoDtoList = userInfoService.getAllUserInfoByName(username);
        return ResponseEntity.ok().body(userInfoDtoList);
    }

    @GetMapping(value = "/email")
    public ResponseEntity<UserInfoDto> getUserInfoByEmail(@RequestParam(name = "email") String email) {
        UserInfoDto userInfoDto = userInfoService.getUserInfoByEmail(email);
        return ResponseEntity.ok().body(userInfoDto);
    }
}
