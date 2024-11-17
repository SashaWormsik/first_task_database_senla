package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.config.TestConfig;
import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringJUnitConfig({TestConfig.class})
public class TaskRepositoryImplTest {

    private final TaskStatus TASK_STATUS_ACTUAL = TaskStatus.builder()
            .status("ACTUAL")
            .build();

    private final TaskStatus TASK_STATUS_NOT_ACTUAL = TaskStatus.builder()
            .status("NOT ACTUAL")
            .build();

    private final TaskStatus TASK_STATUS_EXECUTED = TaskStatus.builder()
            .status("EXECUTED")
            .build();

    private final Category CATEGORY_IT = Category.builder()
            .name("IT")
            .build();

    private final Category CATEGORY_DEV = Category.builder()
            .name("DEV")
            .build();

    private final UserInfo CUSTOMER = UserInfo.builder()
            .name("CUSTOMER")
            .surname("SURNAME CUSTOMER")
            .profession("PROFESSION")
            .workExperience(10)
            .description("DESCRIPTION")
            .build();

    private final Task TASK_ONE = Task.builder()
            .title("TASK ONE")
            .description("This is a new task 11111111")
            .price(BigDecimal.valueOf(1000.00))
            .deadline(new Date())
            .createDate(new Date())
            .build();

    private final Task TASK_TWO = Task.builder()
            .title("TASK TWO")
            .description("This is a new task 2222222")
            .price(BigDecimal.valueOf(1000.00))
            .deadline(new Date())
            .createDate(new Date())
            .build();

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TaskStatusRepository taskStatusRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @BeforeEach
    public void date() {
        categoryRepository.saveAll(List.of(CATEGORY_IT, CATEGORY_DEV));
        taskStatusRepository.saveAll(List.of(TASK_STATUS_NOT_ACTUAL, TASK_STATUS_ACTUAL, TASK_STATUS_EXECUTED));
        userInfoRepository.save(CUSTOMER);
        TASK_ONE.setStatus(TASK_STATUS_ACTUAL);
        TASK_ONE.setCategories(List.of(CATEGORY_IT));
        TASK_ONE.setCustomer(CUSTOMER);
        TASK_TWO.setStatus(TASK_STATUS_ACTUAL);
        TASK_TWO.setCategories(List.of(CATEGORY_DEV));
        TASK_TWO.setCustomer(CUSTOMER);
        taskRepository.saveAll(List.of(TASK_ONE, TASK_TWO));
    }

    @Test
    @Transactional
    public void findAllByCustomerIdAndStatusStatusInTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Task actualTask = taskRepository
                .findAllByCustomerIdAndStatusStatusIn(TASK_ONE.getId(), List.of("ACTUAL"), pageable)
                .getContent()
                .getFirst();
        Assertions.assertEquals(TASK_ONE, actualTask);
    }

    @Test
    @Transactional
    public void findAllTasksByTitleTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Task actualTask = taskRepository
                .findAllTasksByTitle("one", pageable)
                .getContent()
                .getFirst();
        Assertions.assertEquals(TASK_ONE, actualTask);
    }

    @Test
    @Transactional
    public void findAllTasksByTitle_NoResultTest() {
        Pageable pageable = PageRequest.of(0, 10);
        long totalElementsActual = taskRepository
                .findAllTasksByTitle("NOY", pageable)
                .getTotalElements();
        Assertions.assertEquals(0, totalElementsActual);
    }

    @Test
    @Transactional
    public void findAllByTitleAndCategory_WithoutTitleAndCategoryTest() {
        Pageable pageable = PageRequest.of(0, 10);
        long actualTotalElement= taskRepository
                .findAllByTitleAndCategory(null, null, pageable)
                .getTotalElements();
        Assertions.assertEquals(2, actualTotalElement);
    }

    @Test
    @Transactional
    public void findAllByTitleAndCategory_WithTitle_WithoutCategoryTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Task actualTask = taskRepository
                .findAllByTitleAndCategory("n", null, pageable)
                .getContent()
                .getFirst();
        Assertions.assertEquals(TASK_ONE, actualTask);
    }

    @Test
    @Transactional
    public void findAllByTitleAndCategory_WithoutTitle_WithCategoryTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Task actualTask = taskRepository
                .findAllByTitleAndCategory(null, List.of(CATEGORY_DEV.getName()), pageable)
                .getContent()
                .getFirst();
        Assertions.assertEquals(TASK_TWO, actualTask);
    }

    @AfterEach
    public void clean() {
        taskRepository.deleteAll();
        userInfoRepository.deleteAll();
        categoryRepository.deleteAll();
        taskStatusRepository.deleteAll();
    }
}