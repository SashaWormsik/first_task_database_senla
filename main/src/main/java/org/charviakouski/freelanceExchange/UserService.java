package org.charviakouski.freelanceExchange;

import org.charviakouski.freelanceExchange.annotation.Autowired;
import org.charviakouski.freelanceExchange.annotation.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public void execute() {
        this.userDao.execute();
    }
}
