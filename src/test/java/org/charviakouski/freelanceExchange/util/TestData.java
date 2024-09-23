package org.charviakouski.freelanceExchange.util;

import lombok.experimental.UtilityClass;
import org.charviakouski.freelanceExchange.model.entity.Role;

@UtilityClass
public class TestData {

    public Role createRole(){
        return Role.builder()
                .name("USER")
                .build();
    }
}
