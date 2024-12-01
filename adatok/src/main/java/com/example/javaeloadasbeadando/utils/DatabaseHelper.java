package com.example.javaeloadasbeadando.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseHelper {
    private static final String CONFIG_FILE = "config.properties";
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream input = DatabaseHelper.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Config file not found: " + CONFIG_FILE);
            }

            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
        } catch (Exception e) {
            System.err.println("Failed to load database configuration: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
