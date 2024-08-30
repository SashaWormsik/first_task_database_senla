package org.charviakouski.freelanceExchange;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.config.JavaConfig;
import org.charviakouski.freelanceExchange.controller.TaskController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MyApplication {
    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyApplication.class, JavaConfig.class);
        TaskController taskController = context.getBean(TaskController.class);
        System.out.println("==============ВЫВОД ВСЕХ ОБЪЕКТОВ==============");
        System.out.println(taskController.getAll());
        System.out.println("==============ВЫВОД ОБЪЕКТА ПО ID СУЩЕСТВУЮЩЕГО==============");
        System.out.println(taskController.getById("{\"id\":1}"));
        System.out.println("==============ВЫВОД ОБЪЕКТА ПО ID КОТОРОГО НЕТ==============");
        System.out.println(taskController.getById("{\"id\":3}"));
        String insert1 = "{\"id\":3," +
                "\"title\":\"Task3\"," +
                "\"description\":\"Task3\"," +
                "\"price\":585," +
                "\"deadline\":1724967451473," +
                "\"createDate\":1724967451473," +
                "\"customerId\":{" +
                    "\"id\":3," +
                    "\"name\":\"ИВан\"," +
                    "\"surname\":\"Попов\"," +
                    "\"profession\":\"developer\"," +
                    "\"workExperience\":12," +
                    "\"description\":null}," +
                "\"statusId\":{" +
                    "\"id\":1," +
                    "\"status\":\"В ОЖИДАНИИ\"}," +
                "\"categories\":[" +
                    "{\"id\":1,\"name\":\"Ux/Ui\"}," +
                    "{\"id\":2,\"name\":\"IT\"}]}";
        String insert2 = "{\"id\":4," +
                "\"title\":\"Task4\"," +
                "\"description\":\"Task4\"," +
                "\"price\":59999999," +
                "\"deadline\":1724967451473," +
                "\"createDate\":1724967451473," +
                "\"customerId\":{" +
                    "\"id\":4," +
                    "\"name\":\"Диана\"," +
                    "\"surname\":\"Милощ\"," +
                    "\"profession\":\"developer\"," +
                    "\"workExperience\":12," +
                    "\"description\":null}," +
                "\"statusId\":{" +
                    "\"id\":1," +
                    "\"status\":\"В ОЖИДАНИИ\"}," +
                "\"categories\":[" +
                    "{\"id\":1,\"name\":\"Ux/Ui\"}," +
                    "{\"id\":2,\"name\":\"IT\"}]}";
        taskController.insert(insert1);
        taskController.insert(insert2);
        System.out.println("==============ВЫВОД ВСЕХ ОБЪЕКТОВ==============");
        System.out.println(taskController.getAll());
        System.out.println("==============УДАЛЕНИЕ ОБЪЕКТА СУЩЕСТВУЮЩЕГО==============");
        System.out.println(taskController.delete("{\"id\":1}"));
        System.out.println("==============УДАЛЕНИЕ ОБЪЕКТА КОТОРОГО НЕТ==============");
        System.out.println(taskController.delete("{\"id\":555}"));
        System.out.println("==============ВЫВОД ВСЕХ ОБЪЕКТОВ==============");
        System.out.println(taskController.getAll());
        System.out.println("==============ОБНОВЛЕНИЕ ОБЪЕКТА==============");
        String update2 = "{\"id\":4," +
                "\"title\":\"Task4\"," +
                "\"description\":\"ОБНОВИЛИ ОПИСАНИЕ!!!!!!!!!!!!ОПИСАНИЕ НОВОЕ\"," +
                "\"price\":59999999," +
                "\"deadline\":1724967451473," +
                "\"createDate\":1724967451473," +
                "\"customerId\":{" +
                    "\"id\":4," +
                    "\"name\":\"Диана\"," +
                    "\"surname\":\"Милощ\"," +
                    "\"profession\":\"developer\"," +
                    "\"workExperience\":12," +
                    "\"description\":null}," +
                "\"statusId\":{" +
                    "\"id\":1," +
                    "\"status\":\"В ОЖИДАНИИ\"}," +
                "\"categories\":[" +
                    "{\"id\":1,\"name\":\"Ux/Ui\"}," +
                    "{\"id\":2,\"name\":\"IT\"}]}";
        System.out.println(taskController.update(update2));
        System.out.println("==============ВЫВОД ВСЕХ ОБЪЕКТОВ==============");
        System.out.println(taskController.getAll());
    }
}










