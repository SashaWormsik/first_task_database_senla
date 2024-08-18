package org.charviakouski.freelanceExchange.dao;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.annotation.Autowired;
import org.charviakouski.freelanceExchange.annotation.Component;
import org.charviakouski.freelanceExchange.util.ParametersHolder;

@Component
public class UserDao {

    @Autowired
    private ParametersHolder parametersHolder;

    @SneakyThrows
    public void execute() {
        this.parametersHolder.getSomeText();
    }
}
