/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Product;
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
public class ProductDAO {
    Connection cnn; //dùng để kết nối
    Statement stm; //Thực thi các câu lệnh sql
    PreparedStatement pstm;
    ResultSet rs; //Lưu trữ và xử lý dữ liệu

    private void connect() {
        try {
            cnn = (new DBContext()).connection;
            if (cnn != null) {
            } else {
                System.out.println("Connect Product fail");
            }
        } catch (Exception e) {

        }
    }
    
    public List<Product> getListProduct() {
        List<Product> data = new ArrayList<Product>();
        try {
            connect();
            String strSelect = "select [ProductID], [Name], [Categoryid], [BrandID], [Description], [model], "
                    + "[SizeID], [ColorID], [Quantity], [sold], [view], [PublicationDate],[createdAt],[updatedAt],Price from Product";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                int pid = rs.getInt(1);
                String name = rs.getString(2);
                int categoryid = rs.getInt(3);
                int brandId = rs.getInt(4);
                String description = rs.getString(5);
                String model = rs.getString(6);
                int sizeId = rs.getInt(7);
                int colorId = rs.getInt(8);
                int quantity = rs.getInt(9);
                int sold=rs.getInt(10);
                int view=rs.getInt(11);
                String PublicationDate=rs.getString(12);
                String createdAt=rs.getString(13);
                String updatedAt=rs.getString(14);
                double price=rs.getDouble(15);
                Product p = new Product();
               
                data.add(new Product(pid, name, model, brandId, sizeId, colorId, sizeId, description, price, quantity, sold, view, PublicationDate, createdAt, updatedAt));
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListProduct: " + e.getMessage());
        }
        return data;
    }
    
    
}
