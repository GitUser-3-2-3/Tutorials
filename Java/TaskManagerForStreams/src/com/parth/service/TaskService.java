package com.parth.service;

import com.parth.model.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskService {
    List<Task> tasks = new ArrayList<>();
    int nextId = 0;

    public Task addTask(String title, String description, int priority, String deadline) {
        Task task = new Task.Builder(++nextId, title)
            .description(description).priority(priority)
            .deadline(deadline).status(false)
            .build();

        tasks.add(task);
        return task;
    }

    public Task updateTask(
        int id, String title, String description, int priority, String deadline, boolean status
    ) throws ClassNotFoundException {

        Optional<Task> optionalTask = tasks.stream()
            .filter(task -> task.getId() == id)
            .findFirst();

        if (optionalTask.isPresent()) {
            Task oldTask = optionalTask.get();
            Task updatedTask = new Task.Builder(oldTask.getId(), title)
                .description(description)
                .priority(priority)
                .deadline(deadline)
                .status(status)
                .build();

            tasks.set(tasks.indexOf(oldTask), updatedTask);
            return updatedTask;
        } else {
            throw new ClassNotFoundException("Task not found");
        }
    }

    public String deleteTask(int id) {
        tasks = tasks.stream()
            .filter(task -> task.getId() != id)
            .collect(Collectors.toList());

        return "Task deleted";
    }

    public List<Task> filterTaskByStatus(boolean status) {
        return tasks.stream()
            .filter(task -> task.isStatus() == status)
            .collect(Collectors.toList());
    }

    public List<Task> sortTaskByPriority() {
        return tasks.stream()
            .sorted(Comparator.comparingInt(Task::getPriority))
            .collect(Collectors.toList());
    }

    public List<Task> allTasks() {
        return tasks;
    }
}
