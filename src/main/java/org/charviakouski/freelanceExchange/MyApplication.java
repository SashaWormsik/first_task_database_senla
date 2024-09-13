package org.charviakouski.freelanceExchange;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.config.JavaConfig;
import org.charviakouski.freelanceExchange.controller.TaskController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApplication {

    private static final String NEW_TASK = "{\n" +
            "  \"title\" : \"\",\n" +
            "  \"description\" : \"\",\n" +
            "  \"price\" : 100.00,\n" +
            "  \"deadline\" : 1725829200000,\n" +
            "  \"createDate\" : 1725829200000,\n" +
            "  \"customer\" : {\n" +
            "    \"name\" : \"Иван\",\n" +
            "    \"surname\" : \"Чехов\",\n" +
            "    \"profession\" : \"плотник\",\n" +
            "    \"workExperience\" : 2,\n" +
            "    \"description\" : \"охуенный плотник\"\n" +
            "  },\n" +
            "  \"status\" : {\n" +
            "    \"status\" : \"NEW STATUS\"\n" +
            "  },\n" +
            "  \"categories\" : [ {\n" +
            "    \"id\" : 22,\n" +
            "    \"name\" : \"ABU\"\n" +
            "  } ]\n" +
            "}";

    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        TaskController taskController = context.getBean(TaskController.class);
        taskController.insert(NEW_TASK);
        String find = taskController.getById("{\"id\":24}");
        System.out.println(find);
        context.close();
    }
}










