package com.elaundry.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {
    public static Connection initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/elaundry",
                    "root",
                    "Rabi@Work4");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
