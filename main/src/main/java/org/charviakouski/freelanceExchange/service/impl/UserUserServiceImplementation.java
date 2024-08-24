package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.annotation.Autowired;
import org.charviakouski.freelanceExchange.annotation.Component;
import org.charviakouski.freelanceExchange.dao.UserDao;
import org.charviakouski.freelanceExchange.service.UserService;

@Component
public class UserUserServiceImplementation implements UserService {

    private UserDao userDao;

    @Autowired
    public void setDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void execute() {
        this.userDao.execute();
    }
}
