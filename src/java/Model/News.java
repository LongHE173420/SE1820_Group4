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

    public News(int id, int accountID, int groupId, String title, String heading, String author, String image, int view, String createAt, String updateAt, String content, String groupName, String accountName) {
        this.id = id;
        this.accountID = accountID;
        this.groupId = groupId;
        this.title = title;
        this.heading = heading;
        this.author = author;
        this.image = image;
        this.view = view;
        this.createAt = createAt;
        this.updateAt = updateAt;
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

}
