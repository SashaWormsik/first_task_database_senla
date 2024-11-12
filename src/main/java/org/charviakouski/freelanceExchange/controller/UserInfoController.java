package org.charviakouski.freelanceExchange.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'EXECUTOR')")
    public Page<UserInfoDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "size", defaultValue = "2") int size,
                                    @RequestParam(name = "sort", defaultValue = "name") String sort) {
        return userInfoService.getAll(page, size, sort);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'EXECUTOR')")
    public UserInfoDto getById(@PathVariable long id) {
        return userInfoService.getById(id);
    }

    @PostMapping("/new_admin")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole({'ADMIN'})")
    public UserInfoDto insert(@Valid @RequestBody UserInfoDto userInfoDto) {
        return userInfoService.insert(userInfoDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public UserInfoDto update(@PathVariable long id, @RequestBody UserInfoDto userInfoDto) {
        return userInfoService.update(id, userInfoDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public void delete(@PathVariable long id) {
        userInfoService.delete(id);
    }

    @GetMapping(value = "/name")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<UserInfoDto> getAllUserInfoByName(@RequestParam(defaultValue = "", required = false) String username,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "2") int size,
                                                  @RequestParam(name = "sort", defaultValue = "name") String sort) {
        return userInfoService.getAllUserInfoByName(username, page, size, sort);
    }

    @GetMapping(value = "/email")
    @PreAuthorize("hasRole('ADMIN')")
    public UserInfoDto getUserInfoByEmail(@RequestParam String email) {
        return userInfoService.getUserInfoByEmail(email);
    }

    @GetMapping("/search_company")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<UserInfoDto> searchCompany(@RequestParam(defaultValue = "", required = false) String companyName,
                                           @RequestParam(name = "page", defaultValue = "1") int page,
                                           @RequestParam(name = "size", defaultValue = "2") int size) {
        return userInfoService.getAllCompanyByLikeName(companyName, page, size);
    }
}
