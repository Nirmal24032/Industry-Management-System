package com.industry.management;

import java.util.Scanner;

public class IndustryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        EmployeeManagement emp = new EmployeeManagement();
        InventoryManagement inv = new InventoryManagement();
        TaskSchedule ts = new TaskSchedule();

        System.out.println("====================================");
        System.out.println("     INDUSTRY MANAGEMENT SYSTEM     ");
        System.out.println("====================================");

        System.out.print("Enter role (admin/employee): ");
        String role = sc.nextLine().trim().toLowerCase();

        if (role.equals("admin")) {
            adminMenu(emp, inv, ts, sc);
        } else if (role.equals("employee")) {
            System.out.print("Enter your Employee ID: ");
            int empId = sc.nextInt();
            employeeMenu(ts, empId, sc);
        } else {
            System.out.println("❌ Invalid role. Please restart.");
        }
        sc.close();
    }

    private static void adminMenu(EmployeeManagement emp, InventoryManagement inv, TaskSchedule ts, Scanner sc) {
        int choice;
        do {
            System.out.println("\n========= ADMIN MENU =========");
            System.out.println("1. Employee Management");
            System.out.println("2. Inventory Management");
            System.out.println("3. Task Scheduling");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> adminEmployeeMenu(emp, sc);
                case 2 -> adminInventoryMenu(inv, sc);
                case 3 -> adminTaskMenu(ts, sc);
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void adminEmployeeMenu(EmployeeManagement emp, Scanner sc) {
        int choice;
        do {
            System.out.println("\n--- EMPLOYEE MANAGEMENT ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. View All Employees");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> emp.addEmployee();
                case 2 -> emp.updateEmployee();
                case 3 -> emp.deleteEmployee();
                case 4 -> emp.viewEmployees();
                case 0 -> {}
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void adminInventoryMenu(InventoryManagement inv, Scanner sc) {
        int choice;
        do {
            System.out.println("\n--- INVENTORY MANAGEMENT ---");
            System.out.println("1. Add Item");
            System.out.println("2. Update Stock");
            System.out.println("3. View Inventory");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> inv.addInventory();
                case 2 -> inv.updateStock();
                case 3 -> inv.viewInventory();
                case 0 -> {}
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void adminTaskMenu(TaskSchedule ts, Scanner sc) {
        int choice;
        do {
            System.out.println("\n--- TASK SCHEDULING ---");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> ts.addTask();
                case 2 -> ts.updateTask();
                case 3 -> ts.deleteTask();
                case 4 -> ts.viewAllTasks();
                case 0 -> {}
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void employeeMenu(TaskSchedule ts, int empId, Scanner sc) {
        int choice;
        do {
            System.out.println("\n========= EMPLOYEE MENU =========");
            System.out.println("1. View My Tasks");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> ts.viewTasksForEmployee(empId);
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}
