package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.service.UserService;
import org.charviakouski.freelanceExchange.annotation.Autowired;
import org.charviakouski.freelanceExchange.annotation.Component;

@Component
public class UserController {

    @Autowired
    private UserService userService;

    public void execute() {
        userService.execute();
    }
}
