package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class TaskRepositoryImpl implements TaskRepository {
    private final List<Task> tasks = new ArrayList<Task>();

    {
        Task task1 = new Task();
        task1.setId(1);
        task1.setTitle("Task 1");
        task1.setDescription("Task 1");
        task1.setPrice(new BigDecimal(100));
        task1.setCreateDate(new Date());
        task1.setDeadline(new Date());
        task1.setStatusId(new TaskStatus(1, "Выполнено"));
        task1.setCategories(Arrays.asList(new Category(1, "Ux/Ui"), new Category(2, "IT")));
        task1.setCustomerId(new UserInfo(1, "ИВан", "Попов", "developer", 12, null));
        Task task2 = new Task();
        task2.setId(2);
        task2.setTitle("Task 2");
        task2.setDescription("Task 2");
        task2.setPrice(new BigDecimal(500));
        task2.setCreateDate(new Date());
        task2.setDeadline(new Date());
        task2.setStatusId(new TaskStatus(2, "Просрочено"));
        task2.setCategories(Arrays.asList(new Category(3, "architecture"), new Category(4, "construction")));
        task2.setCustomerId(new UserInfo(1, "POMA", "РОМАНОВ", "constructor", 5, null));
        tasks.add(task1);
        tasks.add(task2);
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public Task getById(Task task) {
        return tasks.stream().filter(cat -> cat.getId().equals(task.getId())).findAny().orElse(null);
    }

    @Override
    public boolean insert(Task task) {
        return tasks.add(task);
    }

    @Override
    public boolean update(Task task) {
        tasks.removeIf(task1 -> task1.getId().equals(task.getId()));
        return tasks.add(task);
    }

    @Override
    public boolean delete(Task task) {
        return tasks.removeIf(task1 -> task1.getId().equals(task.getId()));
    }
}
