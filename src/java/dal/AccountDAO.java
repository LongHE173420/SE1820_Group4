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

    public Account getAccountByMobile(String mob) {
        Account account=new Account();
        try {
            connect();
            String strSelect = " select [Account].AccountID, [role], firstName, lastName, mobile, email, passwordHash, lastLogin from [Account] \n"
                    + "inner join [Role] on [Account].roleId = [Role].id where mobile = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, mob);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt("AccountID");
                String role = rs.getString("role");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("mobile");
                String email = rs.getString("email");
                String passwordHash = rs.getString("passwordHash");
                String lastLogin = convertDateTimeFormat(rs.getString("lastLogin"));
                account= new Account(accountId, firstName, lastName, phone, email, passwordHash, lastLogin, role);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return account;

    }
    
//      public static void main(String[] args) {
//        AccountDAO manager = new AccountDAO();
//        String mobileNumber = "123456"; // Thay thế bằng số điện thoại hợp lệ trong database của bạn
//        Account account = manager.getAccountByMobile(mobileNumber);
//
//        if (account != null) {
//            System.out.println("Account ID: " + account.getAccountId());
//            System.out.println("Role: " + account.getRole());
//            System.out.println("First Name: " + account.getFirstName());
//            System.out.println("Last Name: " + account.getLastName());
//            System.out.println("Mobile: " + account.getPhone());
//            System.out.println("Email: " + account.getEmail());
//            System.out.println("Password Hash: " + account.getPasswordHash());
//            System.out.println("Last Login: " + account.getLastLogin());
//        } else {
//            System.out.println("No account found for mobile number: " + mobileNumber);
//        }
//    }

    public boolean checkExistMobile(String mobile) {
        try {
            connect();
            String strSelect = " select [Account].AccountID, [Role], firstName, lastName, mobile, email, passwordHash, lastLogin from [Account] \n"
                    + "join [Role] on [Account].roleID = [role].id where mobile = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, mobile);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return false;

    }

    public List<Account> getListRole() {
        List<Account> lrole = new ArrayList<>();
        try {
            connect();
            String select = "select id, [Role] from [Role] where role != 'Admin'";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int roleId = rs.getInt(1);
                String role = rs.getString(2);
                lrole.add(new Account(roleId, role));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStatus: " + e.getMessage());
        }
        return lrole;
    }
      
    public Role getRoleById(int roleId) {
        Role r=null;
        try {
            connect();
            String select = "select [role] from [Role] where id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, roleId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                r=new Role();
                r.setName(rs.getString("role"));
                
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStatus: " + e.getMessage());
        }
        return r;
    }

    public int getEndPage(String filterBy) {
        try {
            connect();
            if (filterBy.equals("all")) {
                String select = "select count(*) from [Account] join [Role] on [Account].roleId = [Role].id "
                        + "where [Role].[role] != 'Admin'";
                pstm = cnn.prepareStatement(select);
            } else {
                String select = "select count(*) from [Account] join [Role] on [Account].roleId = [Role].id "
                        + "where [Role].[role] = ?";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, filterBy);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                int endPage = rs.getInt(1) / 4;
                if (rs.getInt(1) % 4 != 0) {
                    endPage++;
                }
                return endPage;
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getEndPagesAdmin: " + e.getMessage());
        }
        return 0;
    }

    public int getEndPageSearch(String input) {
        try {
            connect();
            String select = "select count(*) from [Account]\n"
                    + "join [Role] on [Account].roleId = [Role].id \n"
                    + "WHERE [Account].firstName like '%" + input + "%' OR [Account].lastName like '%" + input + "%'";
            pstm = cnn.prepareStatement(select);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int endPage = rs.getInt(1) / 4;
                if (rs.getInt(1) % 4 != 0) {
                    endPage++;
                }
                return endPage;
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getEndPagesSearch: " + e.getMessage());
        }
        return 0;
    }

    public List<Account> getListStaff(int index, String filterBy) {
        List<Account> aList = new ArrayList<>();
        try {
            connect();
            String select;
            if (filterBy.equals("all")) {
                select = "select [Account].AccountID, [Role], firstName, lastName, mobile, email, passwordHash, lastLogin "
                        + "from [Account] \n"
                        + "join [Role] on [Account].roleId = [role].id where role != 'Admin' ORDER BY [Account].AccountID\n"
                        + "OFFSET ? ROWS FETCH NEXT 4 ROWS only";
                pstm = cnn.prepareStatement(select);
                pstm.setInt(1, (index - 1) * 4);
            } else {
                select = "select [Account].AccountID, [role], firstName, lastName, mobile, email, passwordHash, lastLogin from [Account] \n"
                        + "join [Role] on [Account].roleId = [role].id \n"
                        + "WHERE [role] = ?\n"
                        + "ORDER BY [Account].AccountID\n"
                        + "OFFSET ? ROWS FETCH NEXT 4 ROWS only";
                pstm = cnn.prepareStatement(select);
                pstm.setString(1, filterBy);
                pstm.setInt(2, (index - 1) * 4);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt(1);
                String role = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String phone = rs.getString(5);
                String email = rs.getString(6);
                String passwordHash = rs.getString(7);
                String lastLogin = convertDateTimeFormat(rs.getString(8));
                aList.add(new Account(accountId, role, firstName, lastName, phone, email, passwordHash, lastLogin));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStaff: " + e.getMessage());
        }
        return aList;
    }
    
    private boolean checkBan(String account) {
        if (account.equals("Active")) {
            return true;
        } else {
            return false;
        }
    }
  
    public Account loginUser(String user, String password) {
        Account account=new Account();
        
        try {
            connect();
           String query = "  select [Account].AccountID, [role], firstName, lastName, mobile, email, passwordHash, lastLogin from [Account] \n"
                + " inner join [Role] on [Account].roleId = [Role].id  where email =? and [passwordHash] = ?";
            pstm = cnn.prepareStatement(query);
            pstm.setString(1, user);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                
                int accountId = rs.getInt("AccountID");
                String role = rs.getString("role");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("mobile");
                String email = rs.getString("email");
                String passwordHash = rs.getString("passwordHash");
                String lastLogin = convertDateTimeFormat(rs.getString("lastLogin"));
                account= new Account(accountId, firstName, lastName, phone, email, passwordHash, lastLogin, role);
                
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("Login: " + e.getMessage());
        }
        return account;
    }
public static void main(String[] args) {
        // Thông tin đăng nhập cần kiểm tra
        String username = "long@gmail.com";
        String password = "123";

        // Tạo đối tượng của lớp chứa phương thức loginUser
        AccountDAO yourClass = new AccountDAO();

        // Gọi phương thức loginUser và nhận kết quả
        Account account = yourClass.loginUser(username, password);

        // Kiểm tra kết quả và in ra thông tin
        if (account != null) {
            System.out.println("Đăng nhập thành công!");
            System.out.println("AccountID: " + account.getAccountId());
            System.out.println("First Name: " + account.getFirstName());
            System.out.println("Last Name: " + account.getLastName());
            System.out.println("Phone: " + account.getPhone());
            System.out.println("Email: " + account.getEmail());
            System.out.println("Role: " + account.getRole());
            System.out.println("Last Login: " + account.getLastLogin());
        } else {
            System.out.println("Đăng nhập thất bại hoặc tài khoản bị khóa.");
        }
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
