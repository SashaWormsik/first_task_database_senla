package org.charviakouski.freelanceExchange.controller.impl;

import org.charviakouski.freelanceExchange.annotation.Autowired;
import org.charviakouski.freelanceExchange.annotation.Component;
import org.charviakouski.freelanceExchange.controller.UserController;
import org.charviakouski.freelanceExchange.service.UserService;

@Component
public class UserUserControllerImplementation implements UserController {

    private UserService userService;

    @Autowired
    public UserUserControllerImplementation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        userService.execute();
    }
}
