/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.News;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsManager extends DBContext {

     public void addNews(int accountID, String title, String description, String img, String author, String date) {
        String query = "INSERT INTO News (AccountID, Title, Description, Img, Author, Date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, accountID);
            pst.setString(2, title);
            pst.setString(3, description);
            pst.setString(4, img);
            pst.setString(5, author);
            pst.setString(6, date);
            pst.executeUpdate();
            System.out.println("News added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNews(int newsID, int accountID, String title, String description, String img, String author, String date) {
        String query = "UPDATE News SET AccountID = ?, Title = ?, Description = ?, Img = ?, Author = ?, Date = ? WHERE NewsID = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, accountID);
            pst.setString(2, title);
            pst.setString(3, description);
            pst.setString(4, img);
            pst.setString(5, author);
            pst.setString(6, date);
            pst.setInt(7, newsID);
            pst.executeUpdate();
            System.out.println("News updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNews(int newsID) {
        String query = "DELETE FROM News WHERE NewsID = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, newsID);
            pst.executeUpdate();
            System.out.println("News deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<News> getAllNews() {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT * FROM News";
        try (PreparedStatement pst = connection.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                News news = new News();
                news.setNewsID(rs.getInt("NewsID"));
                news.setAccountID(rs.getInt("AccountID"));
                news.setTitle(rs.getString("Title"));
                news.setDescription(rs.getString("Description"));
                news.setImg(rs.getString("Img"));
                news.setAuthor(rs.getString("Author"));
                news.setDate(rs.getString("Date"));
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public News getNewsById(int newsID) {
        News news = null;
        String query = "SELECT * FROM News WHERE NewsID = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, newsID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    news = new News();
                    news.setNewsID(rs.getInt("NewsID"));
                    news.setAccountID(rs.getInt("AccountID"));
                    news.setTitle(rs.getString("Title"));
                    news.setDescription(rs.getString("Description"));
                    news.setImg(rs.getString("Img"));
                    news.setAuthor(rs.getString("Author"));
                    news.setDate(rs.getString("Date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }
}

