package org.charviakouski.freelanceExchange.dao.impl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.annotation.Autowired;
import org.charviakouski.freelanceExchange.annotation.Component;
import org.charviakouski.freelanceExchange.dao.UserDao;
import org.charviakouski.freelanceExchange.util.ParametersHolder;

@Component
public class UserUserDaoImplementation implements UserDao {

    @Autowired
    private ParametersHolder parametersHolder;

    @Override
    @SneakyThrows
    public void execute() {
        this.parametersHolder.getSomeText();
    }
}
