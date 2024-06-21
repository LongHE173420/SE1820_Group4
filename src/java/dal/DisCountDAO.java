/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Discount;
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

/**
 *
 * @author Dell
 */
public class DisCountDAO {
    Connection cnn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;

    private void connect() {
        try {
            cnn = (new DBContext().conn);
            if (cnn == null) {
                System.out.println("Connect Discount fail!");
            }
        } catch (Exception e) {
        }
    }
    public List<Discount> getListDiscount() {
        List<Discount> data = new ArrayList<>();
        try {
            connect();
            String strSelect = "SELECT [Code], [Name], [Amount], [Description], [Type] FROM [dbo].[Discount]";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                data.add(new Discount(code, name, amount, description, type));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListDiscount" + e.getMessage());
        }
        return data;
    }

    public List<Discount> getListDiscountByType(String t) {
        List<Discount> data = new ArrayList<Discount>();
        try {
            connect();
            String strSelect = "SELECT d.[Code], d.[Name], d.[Amount], d.[Description], d.[Type] FROM [dbo].[Discount] d where d.[Type] = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, t);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String decription = rs.getString(4);
                String type = rs.getString(5);
                data.add(new Discount(code, name, amount, decription, type));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListDiscountByType:" + e.getMessage());
        }
        return data;
    }

    public List<Discount> getListDiscountByTypeAndSearch(String t, String search) {
        List<Discount> data = new ArrayList<Discount>();
        try {
            connect();
            String strSelect = "SELECT d.[Code], d.[Name], d.[Amount], d.[Description], d.[Type] FROM [dbo].[Discount] d \n"
                    + "where d[type]=? and (d.[Name] like '%" + search + "%' or d.Code like '%" + search + "%')";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, t);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                data.add(new Discount(code, name, amount, description, type));

            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListDiscountByTypeAndSearch" + e.getMessage());
        }
        return data;
    }

    public List<Discount> getListDiscountByTypeAndSearchAndPage(int index, String t, String search) {
        List<Discount> data = new ArrayList<Discount>();
        try {
            connect();
            String strSelect = "SELECT d.[Code], d.[Name], d.[Amount], d.[Description], d.[Type] FROM [dbo].[Discount] d where 1=1";
            if (t != null) {
                strSelect += " and d.[type]= '" + t + "' ";
            }
            if (search != null) {
                strSelect += "and (d.[Name] like '%" + search + "%' or d.Code like '%" + search + "%')";
            } else {
                strSelect += "and d.[Name] like '%%'";
            }
            strSelect += "order by d.Amount Offset ? rows fetch first 5 rows only";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (index - 1) * 5);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                data.add(new Discount(code, name, amount, description, type));

            }
            cnn.close();

        } catch (SQLException e) {
            System.out.println("getListDisCountByTypeAndSearchAndPage" + e.getMessage());
        }
        return data;
    }

    public List<Discount> getListDiscountBySearch(String search) {
        List<Discount> data = new ArrayList<Discount>();
        try {
            connect();
            String strSelect = "SELECT d.[Code], d.[Name], d.[Amount], d.[Description], d.[Type] FROM [dbo].[Discount] d \n"
                    + "where (d.[Name] like '%" + search + "%' or d.Code like '%" + search + "%')";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                data.add(new Discount(code, name, amount, description, type));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListDisCountByTypeAndSearchAndPage" + e.getMessage());
        }
        return data;
    }

    public Discount getDisCountByCode(String c) {
        try {
            connect();
            String selectStr = "SELECT [Code], [Name], [Amount], [Description], [Type] FROM [dbo].[Discount] where Code = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, c);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String code = rs.getString(1);
                String name = rs.getString(2);
                double amount = rs.getDouble(3);
                String description = rs.getString(4);
                String type = rs.getString(5);
                return new Discount(code, name, amount, description, type);
            }
            cnn.close();

        } catch (SQLException e) {
            System.out.println("getDisCountByCode" + e.getMessage());
        }
        return null;
    }

   
    public Discount getProductDiscountByCode(String c) {
        try {
            connect();
            String selectStr = "SELECT [ProductId], [DiscountCode], [FromDate], [ToDate] FROM [dbo].[product_discount] where [DiscountCode] = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, c);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String code = rs.getString(2);
                String fromDate = convertDateTimeFormat(rs.getString(3));
                String toDate = convertDateTimeFormat(rs.getString(5));
                return new Discount(code, fromDate, toDate, id);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getDiscountByCode: " + e.getMessage());
        }
        return null;
    }

    public void addDiscount(String code, String name, double amount, String decription, String type) {
        try {
            connect();
            String strAdd = "INSERT INTO [Discount]([Code], [Name], [Amount], [Description], [Type])\n"
                    + "values (?,?,?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, code);
            pstm.setString(2, name);
            pstm.setDouble(3, amount);
            pstm.setString(4, decription);
            pstm.setString(5, type);
            pstm.execute();

            cnn.close();
        } catch (SQLException e) {
            System.out.println("addDiscount: " + e.getMessage());
        }
    }

    public void deleteDiscount(String cod) {
        try {
            connect();
            String strDelete = "delete from Discount where Code=?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setString(1, cod);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("deleteDiscount: " + e.getMessage());
        }
    }

    public void deleteUserDiscount(String cod) {
        try {
            connect();
            String strSelect = "delete form account_discount where DiscountCode= ? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("deleteUserDiscount: " + e.getMessage());
        }
    }

    public void deleteProductDiscount(String cod) {
        try {
            connect();
            String strSelect = "delete from product_discount where DiscountCode = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cod);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("deleteProductDiscount: " + e.getMessage());
        }
    }

    public void updateDiscount(String name, double amount, String description, String code) {
        try {
            String strUPdate = "UPDATE [dbo].[Discount]\n"
                    + "   SET   [Name] = ?\n"
                    + "      ,[Amount] = ?\n"
                    + "      ,[Description] = ?\n"
                    + " WHERE Code=?";
            pstm = cnn.prepareStatement(strUPdate);
            pstm.setString(1, name);
            pstm.setDouble(2, amount);
            pstm.setString(3, description);
            pstm.setString(4, code);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println("updateDiscount: " + e.getMessage());
        }
    }

    public void updateProductDiscount(int productId, String fromDate, String toDate, String code) {
        try {
            String strUpdate = "UPDATE [dbo].[product_discount]\n"
                    + "   SET [ProductId] = ?\n"
                    + "      ,[FromDate] = ?\n"
                    + "      ,[ToDate] = ?\n"
                    + " WHERE DiscountCode= ? ";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, productId);
            pstm.setString(2, fromDate);
            pstm.setString(3, toDate);
            pstm.setString(4, code);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("updateProductDiscount: " + e.getMessage());
        }
    }

    public void addUserDiscount(int accountId, String discountCode, int num) {
        try {
            connect();
            String strAdd = "insert into account_discount(AccountId,DiscountCode,number)\n"
                    + "values (?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setInt(1, accountId);
            pstm.setString(2, discountCode);
            pstm.setInt(3, num);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("addUserDiscount: " + e.getMessage());
        }
    }

    public void addProductDiscount(int productId, String discountCode, String fromDate, String toDate) {
        try {
            String strAdd = "INSERT INTO [product_discount](ProductId, DiscountCode, FromDate, ToDate) values (?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setInt(1, productId);
            pstm.setString(2, discountCode);
            pstm.setString(3, fromDate);
            pstm.setString(4, toDate);
            pstm.execute();

            cnn.close();
        } catch (SQLException e) {
            System.out.println("addProductDiscount: " + e.getMessage());
        }
    }

    public int getProductIdByModel(String model) {
        try {
            connect();
            String selectStr = "select ProductID where model=? ";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, model);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int proId = rs.getInt(1);
                return proId;
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getProductIdByModel: " + e.getMessage());
        }
        return -1;
    }

    public String getProductModelByProductId(int id) {
        try {
            connect();
            String selectStr = "select model from Product where ProductID= ? ";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String proModel = rs.getString(1);
                return proModel;
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getProductModelByProductId: " + e.getMessage());
        }
        return null;
    }

    public void updateUserDiscountNumber(int AccountId, String discountCode) {
        try {
            connect();
            String strUpdate = "update account_discount set number = number - 1 where AccountId = ? and DiscountCode = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, AccountId);
            pstm.setString(2, discountCode);
            pstm.execute();
            cnn.close();
        } catch (SQLException e) {
            System.out.println("updateUserDiscountNumber: " + e.getMessage());
        }

    }

    public List<String> getListProductModelByProductDiscountCode(String code) {
        List<String> list = new ArrayList<String>();
        try {
            connect();
            String selectStr = "select p.model from product_discount pd join Product p on pd.ProductId = p.ProductID where pd.DiscountCode = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String proModel = rs.getString(1);
                list.add(proModel);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListProductModelByProductDiscountCode: " + e.getMessage());
        }
        return list;
    }

    public List<Integer> getListProductIdByDiscountCode(String discountCode) {
        List<Integer> data = new ArrayList<Integer>();
        try {
            connect();
            String strSelect = "select [ProductId] from product_discount where DiscountCode = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, discountCode);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int proId = rs.getInt(1);
                data.add(proId);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListProductIdByDiscountCode: " + e.getMessage());
        }
        return data;
    }

    public boolean checkAddProductModelOnActivateWithDiscount(String productModel, String fromDate, String toDate) {
        boolean check = false;
        try {
            connect();
            String selectStr = "select p.model from product_discount pd join Product p on pd.ProductId = p.ProductID where p.model = ?\n"
                    + "	and ((FromDate >= ? and  FromDate <= ?) or (ToDate >= ? and  ToDate <= ?))";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, productModel);
            pstm.setString(2, fromDate);
            pstm.setString(3, toDate);
            pstm.setString(4, fromDate);
            pstm.setString(5, toDate);
            rs = pstm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("checkProductModelOnActivateWithDiscount: " + e.getMessage());
        }
        return check;
    }

    public boolean checkUpdateProductModelOnActivateWithDiscount(String productaModel, String formDate, String toDate, String discountCode) {
        boolean check = false;
        try {
            connect();
            String selectStr = "select p.model from product_discount pd join Product p on pd.productId = p.ProductID where p.model = ?\n"
                    + "	and ((FromDate >= ? and  FromDate <= ?) or (ToDate >= ? and  ToDate <= ?)) and pd.DiscountCode != ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setString(1, productaModel);
            pstm.setString(2, formDate);
            pstm.setString(3, toDate);
            pstm.setString(4, formDate);
            pstm.setString(5, toDate);
            pstm.setString(6, discountCode);
            rs = pstm.executeQuery();
            if (rs.next()) {
                check = true;
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("checkProductModelOnActivateWithDiscount: " + e.getMessage());
        }
        return check;

    }

    public String convertDateTimeFormat(String inputDateTime) {
        if (inputDateTime == null) {
            return null;
        } else {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat ouputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                java.util.Date date = inputFormat.parse(inputDateTime);
                return ouputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

    }
}
