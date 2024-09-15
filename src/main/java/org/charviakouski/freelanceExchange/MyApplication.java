package org.charviakouski.freelanceExchange;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.config.JavaConfig;
import org.charviakouski.freelanceExchange.controller.TaskController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyApplication {

    private static final String NEW_TASK_ONE = "{\n" +
            "  \"title\" : \" TASK ONE !!!!!!! OOOOOOOONNNNNNEEEEEEEEEEEEE\",\n" +
            "  \"description\" : \"ОПИСАНИЕ ТАСКА ОДИН\",\n" +
            "  \"price\" : 100.00,\n" +
            "  \"deadline\" : 1725829200000,\n" +
            "  \"createDate\" : 1725829200000,\n" +
            "  \"customer\" : {\n" +
            "    \"name\" : \"Иван\",\n" +
            "    \"surname\" : \"Чехов\",\n" +
            "    \"profession\" : \"плотник\",\n" +
            "    \"workExperience\" : 2,\n" +
            "    \"description\" : \"плотник\"\n" +
            "  },\n" +
            "  \"status\" : {\n" +
            "    \"status\" : \"in waiting\"\n" +
            "  },\n" +
            "  \"categories\" : [ {\n" +
            "    \"id\" : 3,\n" +
            "    \"name\" : \"worker\"\n" +
            "  } ]\n" +
            "}";

    private static final String NEW_TASK_TWO = "{\n" +
            "  \"title\" : \" TASK TWO !!!!!!!!!!!!!!! TWOOOOOOOOO\",\n" +
            "  \"description\" : \"ОПИСАНИЕ ТАСКА ДВА\",\n" +
            "  \"price\" : 100.00,\n" +
            "  \"deadline\" : 1725829200000,\n" +
            "  \"createDate\" : 1725829200000,\n" +
            "  \"customer\" : {\n" +
            "    \"name\" : \"Иван\",\n" +
            "    \"surname\" : \"Чехов\",\n" +
            "    \"profession\" : \"плотник\",\n" +
            "    \"workExperience\" : 2,\n" +
            "    \"description\" : \"плотник\"\n" +
            "  },\n" +
            "  \"status\" : {\n" +
            "    \"status\" : \"in waiting\"\n" +
            "  },\n" +
            "  \"categories\" : [ {\n" +
            "    \"id\" : 3,\n" +
            "    \"name\" : \"worker\"\n" +
            "  } ]\n" +
            "}";

    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        TaskController taskController = context.getBean(TaskController.class);

        Runnable insertOneTASK = () -> taskController.insert(NEW_TASK_ONE);
        Runnable insertTwoTASK = () -> taskController.insert(NEW_TASK_TWO);
        List<Runnable> runnableList = List.of(insertOneTASK, insertTwoTASK);
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            for (Runnable run : runnableList) {
                executorService.execute(run);
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        System.out.println(taskController.getAll());
    }
}










