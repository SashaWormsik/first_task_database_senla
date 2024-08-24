package org.charviakouski.freelanceExchange.util;

import org.charviakouski.freelanceExchange.annotation.Component;
import org.charviakouski.freelanceExchange.annotation.Value;


@Component
public class ParametersHolder {

    @Value("my.param.db")
    private String someText;

    public void getSomeText() {
        System.out.println(someText);
    }
}
