package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<UserInfoDto> getAll() {
        return userInfoService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserInfoDto getById(@PathVariable(name = "id") long id) {
        return userInfoService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'USER'})")
    public UserInfoDto insert(@RequestBody UserInfoDto userInfoDto) {
        return userInfoService.insert(userInfoDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'USER'})")
    public UserInfoDto update(@PathVariable(name = "id") long id, @RequestBody UserInfoDto userInfoDto) {
        userInfoDto.setId(id);
        return userInfoService.update(userInfoDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'USER'})")
    public void delete(@PathVariable(name = "id") long id) {
        userInfoService.delete(id);
    }

    @GetMapping(value = "/name")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'USER'})")
    public List<UserInfoDto> getAllUserInfoByName(@RequestParam(name = "username") String username) {
        return userInfoService.getAllUserInfoByName(username);
    }

    @GetMapping(value = "/email")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserInfoDto getUserInfoByEmail(@RequestParam(name = "email") String email) {
        return userInfoService.getUserInfoByEmail(email);
    }
}
