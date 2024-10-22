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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<UserInfoDto> getAll() {
        return userInfoService.getAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserInfoDto getById(@PathVariable long id) {
        return userInfoService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
    public UserInfoDto insert(@RequestBody UserInfoDto userInfoDto) {
        return userInfoService.insert(userInfoDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'}) || #id==#authentication.principal.id")
    public UserInfoDto update(@PathVariable long id, @RequestBody UserInfoDto userInfoDto) {
        userInfoDto.setId(id);
        return userInfoService.update(userInfoDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'}) || #id==authentication.principal.id")
    public void delete(@PathVariable long id) {
        userInfoService.delete(id);
    }

    @GetMapping(value = "/name")
    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
    public List<UserInfoDto> getAllUserInfoByName(@RequestParam String username) {
        return userInfoService.getAllUserInfoByName(username);
    }

    @GetMapping(value = "/email")
    @PreAuthorize("hasRole('ADMIN')")
    public UserInfoDto getUserInfoByEmail(@RequestParam String email) {
        return userInfoService.getUserInfoByEmail(email);
    }
}
