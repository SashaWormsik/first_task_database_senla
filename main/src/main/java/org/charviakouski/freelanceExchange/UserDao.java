package org.charviakouski.freelanceExchange;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.annotation.Autowired;
import org.charviakouski.freelanceExchange.annotation.Component;

@Component
public class UserDao {

    @Autowired
    private ParametersHolder parametersHolder;

    @SneakyThrows
    public void execute() {
        this.parametersHolder.getSomeText();
    }
}
