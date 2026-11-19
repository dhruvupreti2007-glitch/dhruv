package com.community.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    private DBConnection() { }

    public static Connection getConnection() throws SQLException {
        String url = System.getenv().getOrDefault(
                "DB_URL", "jdbc:mysql://localhost:3306/community_complaint_db?useSSL=false&serverTimezone=UTC");
        String username = System.getenv().getOrDefault("DB_USER", "root");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "");
        return DriverManager.getConnection(url, username, password);
    }
}
