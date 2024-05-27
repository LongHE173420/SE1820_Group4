/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;




import entity.News;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO extends DBContext {

    public List<News> listAllNews() {
        List<News> listNews = new ArrayList<>();
        String sql = "SELECT * FROM News";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                news.setNewsID(resultSet.getInt("newsID"));
                news.setAccountID(resultSet.getInt("accountID"));
                news.setTitle(resultSet.getString("title"));
                news.setDescription(resultSet.getString("description"));
                news.setImageUrl(resultSet.getString("imageUrl"));
                news.setAuthor(resultSet.getString("author"));
                news.setDate(resultSet.getString("date"));
                listNews.add(news);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNews;
    }

    public void addNews(News news) {
        String sql = "INSERT INTO News (accountID, title, description, imageUrl, author, date) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, news.getAccountID());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getDescription());
            statement.setString(4, news.getImageUrl());
            statement.setString(5, news.getAuthor());
            statement.setString(6, news.getDate());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNews(News news) {
        String sql = "UPDATE News SET accountID = ?, title = ?, description = ?, imageUrl = ?, author = ?, date = ? WHERE newsID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, news.getAccountID());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getDescription());
            statement.setString(4, news.getImageUrl());
            statement.setString(5, news.getAuthor());
            statement.setString(6, news.getDate());
            statement.setInt(7, news.getNewsID());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNews(int newsID) {
        String sql = "DELETE FROM News WHERE newsID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newsID);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

