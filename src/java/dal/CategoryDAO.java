/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class CategoryDAO extends DBContext {
    Connection cnn; //dùng để kết nối
    Statement stm; //Thực thi các câu lệnh sql
    PreparedStatement pstm;
    ResultSet rs; //Lưu trữ và xử lý dữ liệu
    
    private void connect() {
        try {
            cnn = (new DBContext()).conn;
            if (cnn != null) {
                System.out.println("Connect category success");
            } else {
                System.out.println("Connect fail");
            }
        } catch (Exception e) {

        }
    }
    
    public  List<Category> getListCategory() {
        List<Category> data = new ArrayList<Category>();
        try {
            connect();
            String strSelect = "select [CategoryID], [CategoryName] from Category";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {  
                int categoryid = rs.getInt(1);
                String cname = rs.getString(2);
                
                data.add(new Category(categoryid, cname));
            }          
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListCategory: " + e.getMessage());
        }
        return data;
    }
     public static  void main(String[] args) {
         CategoryDAO cat=new CategoryDAO();
        List<Category> categories = cat.getListCategory() ;
        for (Category category : categories) {
            System.out.println(category);
        }
    }
    public Category getCategoryByID(int cid) {
        try {
            connect();
            String strSelect = "select [id], [name] from Category where id=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, cid);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int categoryid = rs.getInt(1);
                String cname = rs.getString(2);
                
                return new Category(categoryid, cname);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListProduct: " + e.getMessage());
        }
        return null;
    }
    
    
    
}
