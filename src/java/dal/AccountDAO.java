/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Dell
 */
public class AccountDAO extends DBContext {

    Connection cnn;

    PreparedStatement pstm;
    ResultSet rs;

    private void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn != null) {
                System.out.println("Connect success");
            } else {
                System.out.println("Connect fail");
            }

        } catch (Exception e) {

        }
    }

      public static void main(String[] args) {
        AccountDAO manager = new AccountDAO();
        String user = "long@gmail.com"; // Thay thế bằng số điện thoại hợp lệ trong database của bạn
        String pass="123";
        Account account = manager.loginUser(user,pass);

        if (account != null) {
            System.out.println("Account ID: " + account.getAccountID());
            
        } else {
            System.out.println("No account found for mobile number: ");
        }
    }
    private boolean checkBan(String account) {
        if (account.equals("Active")) {
            return true;
        } else {
            return false;
        }
    }

    public Account loginUser(String user, String password) {
        String query = "  SELECT * FROM Account\n"
                + "  where Email =? and [passwordHash] = ?";
        try {
            cnn = new DBContext().connection;
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, user);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                if (checkBan(rs.getString(10))) {
                    return new Account(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getInt(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11),
                            rs.getString(12),
                            rs.getString(13));
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println("Login: " + e.getMessage());
        }
        return null;
    }

   

    public String convertDateTimeFormat(String inputDateTime) {
        if (inputDateTime == null) {
            return null;
        } else {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat outputFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            try {
                java.util.Date date = inputFormat.parse(inputDateTime);
                return outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

}
