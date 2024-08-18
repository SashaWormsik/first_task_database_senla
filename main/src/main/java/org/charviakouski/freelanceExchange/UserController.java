package org.charviakouski.freelanceExchange;

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
