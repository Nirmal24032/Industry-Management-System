package com.industry.management;

import java.sql.*;
import java.util.Scanner;

public class TaskSchedule {
    Scanner sc = new Scanner(System.in);

    public void addTask() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Task Description: ");
            String desc = sc.nextLine();
            System.out.print("Enter Assigned Employee ID: ");
            int empId = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Deadline (YYYY-MM-DD): ");
            String deadline = sc.nextLine();

            String sql = "INSERT INTO tasks (task_desc, employee_id, deadline) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, desc);
            ps.setInt(2, empId);
            ps.setString(3, deadline);
            ps.executeUpdate();
            System.out.println("✅ Task assigned successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateTask() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Task ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new deadline (YYYY-MM-DD): ");
            String deadline = sc.nextLine();
            System.out.print("Enter new status (Pending/In Progress/Completed): ");
            String status = sc.nextLine();

            String sql = "UPDATE tasks SET deadline = ?, status = ? WHERE task_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, deadline);
            ps.setString(2, status);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Task updated successfully!");
            else
                System.out.println("❌ Task not found!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteTask() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Task ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM tasks WHERE task_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("🗑️ Task deleted successfully!");
            else
                System.out.println("❌ Task not found!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewTasksForEmployee(int empId) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tasks WHERE employee_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- TASKS ASSIGNED TO YOU ---");
            while (rs.next()) {
                System.out.println("Task ID: " + rs.getInt("task_id") +
                        " | Description: " + rs.getString("task_desc") +
                        " | Deadline: " + rs.getDate("deadline") +
                        " | Status: " + rs.getString("status"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewAllTasks() {
        try (Connection con = DBConnection.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tasks");

            System.out.println("\n--- ALL TASKS ---");
            while (rs.next()) {
                System.out.println("Task ID: " + rs.getInt("task_id") +
                        " | Employee ID: " + rs.getInt("employee_id") +
                        " | Description: " + rs.getString("task_desc") +
                        " | Deadline: " + rs.getDate("deadline") +
                        " | Status: " + rs.getString("status"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
