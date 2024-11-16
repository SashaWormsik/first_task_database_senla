package org.charviakouski.freelanceExchange.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<UserInfoDto> getAll(@RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                    @RequestParam(name = "size", defaultValue = "2") @Min(1) int size,
                                    @RequestParam(name = "sort", defaultValue = "name")
                                    @Pattern(regexp = "name|surname|profession|work_experience") String sort) {
        return userInfoService.getAll(page, size, sort);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public UserInfoDto getById(@PathVariable long id) {
        return userInfoService.getById(id);
    }

    @PostMapping("/new_admin")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole({'ADMIN'})")
    public UserInfoDto insert(@Valid @RequestBody CredentialDto credentialDto) {
        return userInfoService.insert(credentialDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public UserInfoDto update(@PathVariable long id, @Valid @RequestBody UserInfoDto userInfoDto) {
        return userInfoService.update(id, userInfoDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public void delete(@PathVariable long id) {
        userInfoService.delete(id);
    }

    @GetMapping(value = "/search_executor")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<UserInfoDto> getAllUserInfoByName(@RequestParam(defaultValue = "", required = false) String username,
                                                  @RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                                  @RequestParam(name = "size", defaultValue = "2") @Min(1) int size,
                                                  @RequestParam(name = "sort", defaultValue = "name")
                                                  @Pattern(regexp = "name|surname|profession|work_experience") String sort) {
        return userInfoService.getAllExecutorByLikeName(username, page, size, sort);
    }

    @GetMapping(value = "/email")
    @PreAuthorize("hasRole('ADMIN')")
    public UserInfoDto getUserInfoByEmail(@RequestParam @Email String email) {
        return userInfoService.getUserInfoByEmail(email);
    }

    @GetMapping("/search_company")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<UserInfoDto> searchCompany(@RequestParam(defaultValue = "", required = false) String companyName,
                                           @RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                           @RequestParam(name = "size", defaultValue = "2") @Min(1) int size) {
        return userInfoService.getAllCompanyByLikeName(companyName, page, size);
    }
}
