package org.charviakouski.freelanceExchange;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.config.JavaConfig;
import org.charviakouski.freelanceExchange.controller.UserInfoController;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class MyApplication {


    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserInfoController controller = context.getBean(UserInfoController.class);
        EntityMapper mapper = context.getBean(EntityMapper.class);
        UserInfoRepository repository = context.getBean(UserInfoRepository.class);
        Role role = Role.builder()
                .id(1L)
                .name("ADMIN")
                .build();
        Credential credential = Credential.builder()
                .email("aasdasd@ccc.ru")
                .active(true)
                .createDate(new Date())
                .password("1234")
                .role(role)
                .build();
        UserInfo userInfo = UserInfo.builder()
                .name("Павед")
                .surname("Иванов")
                .description("АБОТАЮ КАК ХОЧУ")
                .profession("Лентяй")
                .workExperience(25)
                .credential(credential)
                .build();
        Credential credential2 = Credential.builder()
                .email("@@@ ru")
                .active(true)
                .createDate(new Date())
                .password("1234")
                .role(role)
                .build();
        UserInfo userInfo2 = UserInfo.builder()
                .name("ЁД")
                .surname("!!!!!!!!!!!")
                .description("КАК ХОЧУ")
                .profession("Лентяй")
                .workExperience(25)
                .credential(credential2)
                .build();
        credential2.setUserInfo(userInfo2);
       repository.create(userInfo2);
    }
}










