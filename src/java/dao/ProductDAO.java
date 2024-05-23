/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Product;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBContext;

/**
 *
 * @author ASUS
 */
public class ProductDAO extends DBContext {

    public Vector<Product> getAll() {
        String sql = "select * from Product";
        Vector<Product> vector = new Vector<>();
        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                int productId = rs.getInt(1);
                String name = rs.getNString(2);
                int brandId = rs.getInt(3);
                int catrgoryId = rs.getInt(4);
                String description = rs.getNString(5);
                double price = rs.getDouble(6);
                int quantity = rs.getInt(7);
                Timestamp publicationDate = rs.getTimestamp(8);
                String status = rs.getString(9);

                vector.add(new Product(productId, name, brandId, catrgoryId,
                        description, price, quantity, publicationDate, status));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Product> getBySql(String sql) {
        Vector<Product> vector = new Vector<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int productId = rs.getInt(1);
                String name = rs.getNString(2);
                int brandId = rs.getInt(3);
                int catrgoryId = rs.getInt(4);
                String description = rs.getNString(5);
                double price = rs.getDouble(6);
                int quantity = rs.getInt(7);
                Timestamp publicationDate = rs.getTimestamp(8);
                String status = rs.getString(9);

                vector.add(new Product(productId, name, brandId, catrgoryId,
                        description, price, quantity, publicationDate, status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Vector<Product> list = dao.getAll();
        for (Product product : list) {
            System.out.println(product);
        }
    }
}
