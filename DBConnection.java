package com.industry.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/industry_db";
    private static final String USER = "root";          
    private static final String PASSWORD = "Nimi@2005"; 

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL Driver not found.");
        } catch (SQLException e) {
            System.out.println("❌ Database Connection Error: " + e.getMessage());
        }
        return con;
    }
}
