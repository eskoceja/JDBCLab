package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        main.connect();
        main.selectAllBooks();
    }

    public Connection connect() {
        String url = "jdbc:sqlite:/Users/emilykoceja/Desktop/CTAC_Program/full-stack-104/SQL-Bookstore/Bookstore.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAllBooks() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = this.connect();
            String sql = "SELECT * FROM Books";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("Title") + "\t" +
                        rs.getString("Author") + "\t" +
                        rs.getDouble("Price"));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}