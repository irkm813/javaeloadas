package com.example.javaeloadasbeadando.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://mysql.nethely.hu:3306/cstpdb";
    private static final String USER = "cstpdb";
    private static final String PASSWORD = "BundasKenyer69420";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

