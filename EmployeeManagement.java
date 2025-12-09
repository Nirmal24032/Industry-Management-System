package com.industry.management;

import java.sql.*;
import java.util.Scanner;

public class EmployeeManagement {
    Scanner sc = new Scanner(System.in);

    // Add Employee
    public void addEmployee() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Role: ");
            String role = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            String sql = "INSERT INTO employees (emp_name, emp_role, emp_salary) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, role);
            ps.setDouble(3, salary);
            ps.executeUpdate();

            System.out.println("✅ Employee Added Successfully!\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // View Employees
    public void viewEmployees() {
        try (Connection con = DBConnection.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees");

            System.out.println("\n--- Employee List ---");
            System.out.printf("%-5s %-20s %-15s %-10s\n", "ID", "Name", "Role", "Salary");
            System.out.println("---------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-15s %-10.2f\n",
                        rs.getInt("emp_id"),
                        rs.getString("emp_name"),
                        rs.getString("emp_role"),
                        rs.getDouble("emp_salary"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Update Employee
    public void updateEmployee() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Employee ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter New Role: ");
            String role = sc.nextLine();
            System.out.print("Enter New Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            String sql = "UPDATE employees SET emp_role = ?, emp_salary = ? WHERE emp_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, role);
            ps.setDouble(2, salary);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Employee Updated Successfully!\n");
            else
                System.out.println("❌ Employee Not Found.\n");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Delete Employee
    public void deleteEmployee() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Employee ID to Delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM employees WHERE emp_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("🗑️ Employee Deleted Successfully!\n");
            else
                System.out.println("❌ Employee Not Found.\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

