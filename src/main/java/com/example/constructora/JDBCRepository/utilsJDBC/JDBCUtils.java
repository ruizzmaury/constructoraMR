package com.example.constructora.JDBCRepository.utilsJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/constructorabd";
    public static final String DB_URL_HOST = "jdbc:postgresql://localhost:5432/";
    public static final String USER = "postgres";
    public static final String PASS = "postgres";
    public static Connection connection;

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        return connection;
    }
}
