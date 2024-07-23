package com.parth;

import com.parth.service.TaskService;
import com.parth.util.InputService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        InputService inputService = new InputService();
        TaskService taskService = new TaskService();

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Update Task");
            System.out.println("4. Filter Tasks by Status");
            System.out.println("5. Sort Tasks by Priority");
            System.out.println("6. All Tasks");
            System.out.println("0. Exit");
            System.out.println();
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println(inputService.addTaskInput());
                    break;
                case 2:
                    System.out.println(inputService.deleteTaskInput());
                    break;
                case 3:
                    System.out.println(inputService.updateTaskInput());
                    break;
                case 4:
                    System.out.println(inputService.filterTaskByStatusInput());
                    break;
                case 5:
                    System.out.println(taskService.sortTaskByPriority());
                    break;
                case 6:
                    System.out.println(taskService.allTasks());
                    break;
                case 0:
                    scanner.close();
                    return;
            }
        }
    }
}
