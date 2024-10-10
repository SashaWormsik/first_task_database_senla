package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<UserInfoDto> getAll() {
        return userInfoService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto getById(@PathVariable(name = "id") long id) {
        return userInfoService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDto insert(@RequestBody UserInfoDto userInfoDto) {
        return userInfoService.insert(userInfoDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto update(@PathVariable(name = "id") long id, @RequestBody UserInfoDto userInfoDto) {
        userInfoDto.setId(id);
        return userInfoService.update(userInfoDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id) {
        userInfoService.delete(id);
    }

    @GetMapping(value = "/name")
    @ResponseStatus(HttpStatus.OK)
    public List<UserInfoDto> getAllUserInfoByName(@RequestParam(name = "username") String username) {
        return userInfoService.getAllUserInfoByName(username);
    }

    @GetMapping(value = "/email")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto getUserInfoByEmail(@RequestParam(name = "email") String email) {
        return userInfoService.getUserInfoByEmail(email);
    }
}
