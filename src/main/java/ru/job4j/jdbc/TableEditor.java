package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.io.*;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException {
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password")
        );
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s()", tableName);
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("DROP TABLE %s", tableName);
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s", tableName, columnName, type);
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s RENAME COLUMN %S TO %S", tableName, columnName, newColumnName);
            statement.execute(sql);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String tableName = "test_table";
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/app.properties")) {
            properties.load(fis);
            try (TableEditor tableEditor = new TableEditor(properties)) {
                tableEditor.createTable(tableName);
                tableEditor.addColumn(tableName, "id", "int");
                tableEditor.renameColumn(tableName, "id", "count");
                tableEditor.dropColumn(tableName, "count");
                tableEditor.dropTable(tableName);
            }

        }
    }
}
