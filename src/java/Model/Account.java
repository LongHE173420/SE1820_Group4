/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import dal.DBContext;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class Account {
    private int accountId;
    private int roleId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String passwordHash;
    private String lastLogin;
    
    private String role;

    public Account() {
    }
   

    public Account(int accountId, String firstName, String lastName, String phone, String email, String passwordHash, String lastLogin, String role) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.passwordHash = passwordHash;
        this.lastLogin = lastLogin;
        this.role = role;
    }

    public Account(int roleId, String firstName, String lastName, String phone, String email, String passwordHash) {
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Account(int roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    Connection cnn;
    Statement sttm;
    PreparedStatement pstm;
    ResultSet rs;
    
    private void connect(){
        try{
            cnn=(new DBContext().connection);
            if(cnn!=null){
                System.out.println("Connect success");
            }else{
                System.out.println("Connect fail");
            }
            
        }catch(Exception e){
            
        }
    }
    
    public Account getAccountByMobile(String mob) {
        try {
            connect();
            String strSelect = " select [Account].AccountID, [role], firstName, lastName, mobile, email, passwordHash, lastLogin from [Account] \n"
                    + "join [Role] on [Account].roleId = [Role].id where mobile = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, mob);
            rs = pstm.executeQuery();
            while (rs.next()) {
                accountId = rs.getInt(1);
                role = rs.getString(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                phone = rs.getString(5);
                email = rs.getString(6);
                passwordHash = rs.getString(7);
                lastLogin = convertDateTimeFormat(rs.getString(8));
                return new Account(accountId, role, firstName, lastName, phone, email, passwordHash, lastLogin);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return null;

    }
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
                roleId = rs.getInt(1);
                role = rs.getString(2);
                lrole.add(new Account(roleId, role));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStatus: " + e.getMessage());
        }
        return lrole;
    }
        public String getRoleById(int r) {
        try {
            connect();
            String select = "select [role] from [Role] where id = ?";
            pstm = cnn.prepareStatement(select);
            pstm.setInt(1, r);
            rs = pstm.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStatus: " + e.getMessage());
        }
        return role;
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
                accountId = rs.getInt(1);
                role = rs.getString(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                phone = rs.getString(5);
                email = rs.getString(6);
                passwordHash = rs.getString(7);
                lastLogin = convertDateTimeFormat(rs.getString(8));
                aList.add(new Account(accountId, role, firstName, lastName, phone, email, passwordHash, lastLogin));
            }
            cnn.close();
        } catch (SQLException e) {
            System.err.println("getListStaff: " + e.getMessage());
        }
        return aList;
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
