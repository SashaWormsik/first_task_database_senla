package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.config.TestConfig;
import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringJUnitConfig({TestConfig.class, TaskRepositoryImpl.class})
public class TaskRepositoryImplTest {

    private final TaskStatus TASK_STATUS = TaskStatus.builder()
            .status("Done")
            .build();

    private final Category CATEGORY_ARH = Category.builder()
            .name("architecture")
            .build();

    private final Category CATEGORY_CON = Category.builder()
            .name("construction")
            .build();

    private final UserInfo CUSTOMER = UserInfo.builder()
            .name("CUSTOMER")
            .surname("SURNAME CUSTOMER")
            .profession("PROFESSION")
            .workExperience(10)
            .description("DESCRIPTION")
            .build();

    private final Task TASK = Task.builder()
            .title("NEW TASK")
            .description("This is a new task")
            .price(BigDecimal.valueOf(1000.00))
            .deadline(new Date())
            .createDate(new Date())
            .customer(CUSTOMER)
            .status(TASK_STATUS)
            .categories(List.of(CATEGORY_ARH, CATEGORY_CON))
            .build();

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void date() {
        taskRepository.create(TASK);
    }

    @Test
    @Transactional
    public void getAllTasksByTitleTest() {
        Task actualTask = taskRepository.getAllTasksByTitle("NEW TASK").getFirst();
        Assertions.assertEquals(TASK, actualTask);
    }

    @Test
    @Transactional
    public void getAllTasksByPriceTest() {
        Task actualTask = taskRepository.getAllTasksByPrice(BigDecimal.valueOf(1000.00)).getFirst();
        Assertions.assertEquals(TASK, actualTask);
    }

    @Test
    @Transactional
    public void getTaskByIdGraphTest() {
        Task actualTask = taskRepository.getTaskByIdGraph(TASK.getId()).orElse(null);
        Assertions.assertEquals(TASK, actualTask);
    }

    @AfterEach
    public void clean() {
        taskRepository.delete(TASK.getId());
    }
}
