/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Date;
import java.sql.*;
import dal.DBContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class News {

    private int id, accountID, groupId;
    private String title, heading, author, image;
    private int view, stt;
    private String link;
    private String createAt, updateAt;
    private String content, groupName, accountName;
    private Connection cnn;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;

    private void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn == null) {
                System.out.println("Connect News Fail!");
            }

        } catch (Exception e) {

        }
    }

    public News() {
    }

    public News(int id) {
        this.id = id;
    }

    public News(int id, int accountID, int groupId, String title, String heading, String author, String image, int view, int stt, String link, String createAt, String updateAt, String content, String groupName, String accountName) {
        this.id = id;
        this.accountID = accountID;
        this.groupId = groupId;
        this.title = title;
        this.heading = heading;
        this.author = author;
        this.image = image;
        this.view = view;
        this.stt = stt;
        this.link = link;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.content = content;
        this.groupName = groupName;
        this.accountName = accountName;
    }

    public News(int id, int accountID, int groupId, String title, String heading, String author, String image, int view, String createAt, String updateAt, String content, String groupName, String accountName, int STT) {
        this.id = id;
        this.accountID = accountID;
        this.groupId = groupId;
        this.title = title;
        this.heading = heading;
        this.author = author;
        this.image = image;
        this.view = view;
        this.stt = STT;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.content = content;
        this.groupName = groupName;
        this.accountName = accountName;
    }

    public News(int id, int accountID, int groupId, String title, String heading, String author, String image, int view, String createAt, String content, String groupName, String accountName) {
        this.id = id;
        this.accountID = accountID;
        this.groupId = groupId;
        this.title = title;
        this.heading = heading;
        this.author = author;
        this.image = image;
        this.view = view;
        this.createAt = createAt;
        this.content = content;
        this.groupName = groupName;
        this.accountName = accountName;
    }

    public News(int id, int accountID, int groupId, String title, String image, int stt, String link, String createAt, String updateAt, String content) {
        this.id = id;
        this.accountID = accountID;
        this.groupId = groupId;
        this.title = title;
        this.image = image;
        this.stt = stt;
        this.link = link;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<News> getListNews() {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.NewsID, n.accountID, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from News n join [Account] a on a.AccountID = n.accountID\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                int id = rs.getInt(1);
                int accountId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                int view = rs.getInt(8);
                String createAt = convertDateTimeFormat(rs.getString(9));
                String updateAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String accountName = rs.getString(13);
                data.add(new News(id, accountId, groupId, title, heading, author, image, view, createAt, content, groupName, accountName));
            }

            cnn.close();

        } catch (SQLException e) {
            System.out.println("getListNews" + e.getMessage());
        }
        return data;
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
