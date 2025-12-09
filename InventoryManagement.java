package com.industry.management;

import java.sql.*;
import java.util.Scanner;

public class InventoryManagement {
    Scanner sc = new Scanner(System.in);

    // Add new item
    public void addInventory() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Item Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Stock Quantity: ");
            int stock = sc.nextInt();
            System.out.print("Enter Threshold Level: ");
            int threshold = sc.nextInt();
            sc.nextLine();

            String sql = "INSERT INTO inventory (item_name, stock, threshold) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, stock);
            ps.setInt(3, threshold);
            ps.executeUpdate();
            System.out.println("✅ Item added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Update stock (+incoming, -outgoing)
    public void updateStock() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter Item ID to update: ");
            int id = sc.nextInt();
            System.out.print("Enter stock change (+incoming / -outgoing): ");
            int change = sc.nextInt();
            sc.nextLine();

            String sqlGet = "SELECT stock, threshold, item_name FROM inventory WHERE item_id = ?";
            PreparedStatement ps1 = con.prepareStatement(sqlGet);
            ps1.setInt(1, id);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                int currentStock = rs.getInt("stock");
                int threshold = rs.getInt("threshold");
                String name = rs.getString("item_name");
                int newStock = currentStock + change;

                if (newStock < 0) {
                    System.out.println("❌ Cannot have negative stock!");
                    return;
                }

                String sqlUpdate = "UPDATE inventory SET stock = ? WHERE item_id = ?";
                PreparedStatement ps2 = con.prepareStatement(sqlUpdate);
                ps2.setInt(1, newStock);
                ps2.setInt(2, id);
                ps2.executeUpdate();

                System.out.println("✅ Stock updated for " + name + ". Current stock: " + newStock);
                if (newStock <= threshold)
                    System.out.println("⚠️ Warning: Stock for " + name + " is below threshold!");
            } else {
                System.out.println("❌ Item not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // View inventory
    public void viewInventory() {
        try (Connection con = DBConnection.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM inventory");

            System.out.println("\n--- INVENTORY LIST ---");
            System.out.printf("%-5s %-20s %-10s %-10s\n", "ID", "Item", "Stock", "Threshold");
            System.out.println("-------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-10d %-10d\n",
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getInt("stock"),
                        rs.getInt("threshold"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
