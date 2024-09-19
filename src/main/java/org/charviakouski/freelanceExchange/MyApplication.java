package org.charviakouski.freelanceExchange;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.config.JavaConfig;
import org.charviakouski.freelanceExchange.controller.TaskController;
import org.charviakouski.freelanceExchange.controller.UserInfoController;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MyApplication {


    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserInfoController controller = context.getBean(UserInfoController.class);
        EntityMapper mapper = context.getBean(EntityMapper.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setName("BDFY");
        String name =mapper.fromDtoToJson(mapper.fromEntityToDto(userInfo, UserInfoDto.class));
        System.out.println(controller.getByName(name));

    }
}










