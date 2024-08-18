package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.annotation.Autowired;
import org.charviakouski.freelanceExchange.annotation.Component;
import org.charviakouski.freelanceExchange.dao.UserDao;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public void execute() {
        this.userDao.execute();
    }
}
