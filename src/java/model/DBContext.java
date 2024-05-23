/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class DBContext {

    public Connection conn = null;

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            //create statement
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            //run and get
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rs;
    }

    public DBContext(String URL, String userName, String pass) {
        try {
            //drive
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connection
            conn = DriverManager.getConnection(URL, userName, pass);
//            System.out.println("connected");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public DBContext() {
        this("jdbc:sqlserver://localhost:1433;databaseName=ShoesStore", "sa", "123456");

    }

    public static void main(String[] args) {
        DBContext db = new DBContext("jdbc:sqlserver://localhost:1433;databaseName=ShoesStore", "sa", "123456");
    }
}
