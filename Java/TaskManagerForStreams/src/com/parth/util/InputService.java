package com.parth.util;

import com.parth.model.Task;
import com.parth.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class InputService {
    Scanner sc = new Scanner(System.in);
    TaskService taskService = new TaskService();

    public Task addTaskInput() {
        System.out.println("Enter title: ");
        String title = sc.nextLine();

        System.out.println("Enter Description");
        String description = sc.nextLine();

        System.out.println("Enter priority");
        int priority = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter deadline");
        String deadline = sc.nextLine();

        return taskService.addTask(title, description, priority, deadline);
    }

    public String deleteTaskInput() {
        System.out.println("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();

        return taskService.deleteTask(id);
    }

    public Task updateTaskInput() throws ClassNotFoundException {
        System.out.println("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter title: ");
        String title = sc.nextLine();

        System.out.println("Enter Description");
        String description = sc.nextLine();

        System.out.println("Enter priority");
        int priority = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter deadline");
        String deadline = sc.nextLine();

        System.out.println("Enter status");
        boolean status = sc.nextBoolean();
        sc.nextLine();

        return taskService.updateTask(id, title, description, priority, deadline, status);
    }

    public List<Task> filterTaskByStatusInput() {
        System.out.println("Enter status: ");
        boolean status = sc.nextBoolean();
        sc.nextLine();

        return taskService.filterTaskByStatus(status);
    }
}
