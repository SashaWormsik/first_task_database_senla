package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class TaskRepositoryImpl implements TaskRepository {
    private final List<Task> tasks = new ArrayList<Task>();

    {
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        task1.setDescription("Task 1");
        task1.setPrice(new BigDecimal(100));
        task1.setCreateDate(new Date());
        task1.setDeadline(new Date());
        task1.setStatus(new TaskStatus(1L, "Выполнено"));
        task1.setCategories(Arrays.asList(new Category(1L, "Ux/Ui"), new Category(2L, "IT")));
        task1.setCustomer(new UserInfo(1L, "ИВан", "Попов", "developer", 12, null));
        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setDescription("Task 2");
        task2.setPrice(new BigDecimal(500));
        task2.setCreateDate(new Date());
        task2.setDeadline(new Date());
        task2.setStatus(new TaskStatus(2L, "Просрочено"));
        task2.setCategories(Arrays.asList(new Category(3L, "architecture"), new Category(4L, "construction")));
        task2.setCustomer(new UserInfo(1L, "POMA", "РОМАНОВ", "constructor", 5, null));
        tasks.add(task1);
        tasks.add(task2);
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public Optional<Task> getById(Task task) {
        return tasks.stream()
                .filter(cat -> cat.getId().equals(task.getId()))
                .findAny();
    }

    @Override
    public Task insert(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public Task update(Task newTask, Task oldTask) {
        Collections.replaceAll(tasks, oldTask, newTask);
        return newTask;
    }

    @Override
    public boolean delete(Task task) {
        return tasks.removeIf(task1 -> task1.getId().equals(task.getId()));
    }
}
