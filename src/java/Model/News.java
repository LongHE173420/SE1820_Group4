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

    public News(int id, int accountID, int groupId, String title, String heading, String author, String image, int view, String createAt,String updateAt, String content, String groupName, String accountName) {
        this.id = id;
        this.accountID = accountID;
        this.groupId = groupId;
        this.title = title;
        this.heading = heading;
        this.author = author;
        this.image = image;
        this.view = view;
        this.createAt = createAt;
        this.updateAt=updateAt;
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
            String strSelect = "select n.id, n.accountID, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
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
    
    public News getNewsById(int nId){
        try{
            connect();
            String selectStr="select n.id, n.accountID, n.groupId,n.title, n.heading, n.author, n.[image],n.createdAt, n.updatedAt, n.content, ng.[name], \n"
                    + "(na.firstName + ' ' + na.lastName) as adminName, n.[view] from news n join NewsGroup ng on n.groupId = ng.id \n"
                    + "join [Account] na on n.accountID = na.AccountID where n.id = ?";
            pstm=cnn.prepareStatement(selectStr);
            pstm.setInt(1, nId);
            rs=pstm.executeQuery();
            if(rs.next()){
                int id=rs.getInt(1);
                int accountId=rs.getInt(2);
                int groupId=rs.getInt(3);
                String title=rs.getString(4);
                String heading=rs.getString(5);
                String author=rs.getString(6);
                String image=rs.getString(7);
                String createAt=convertDateTimeFormat(rs.getString(8));
                String updatedAt=convertDateTimeFormat(rs.getString(9));
                String content=rs.getString(10);
                String groupName=rs.getString(11);
                String accountName=rs.getString(12);
                int view=rs.getInt(13);
                return new News(id, accountId, groupId, title, heading, author, image, view, createAt, updateAt, content, groupName, accountName);
            }
            cnn.close();
        }catch(SQLException e){
            System.out.println("getNewsById: "+e.getMessage());
        }
        return null;
    }
    public List<News> getNewsByGid(int gid){
        List<News> data =new ArrayList<News>();
        try{
            connect();
            String selectStr= "select n.id, n.accountID, n.groupId,n.title, n.heading, n.author, n.[image],n.createdAt, n.updatedAt, n.content, ng.[name], \n"
                    + "(na.firstName + ' ' + na.lastName) as adminName, n.[view] from news n join NewsGroup ng on n.groupId = ng.id \n"
                    + "join [Account] na on n.accountID = na.AccountID where n.groupId = ? order by n.createdAt";
            pstm=cnn.prepareStatement(selectStr);
            pstm.setInt(1, gid);
            rs=pstm.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                int accountId=rs.getInt(2);
                int groupId=rs.getInt(3);
                String title=rs.getString(4);
                String heading=rs.getString(5);
                String auhtor=rs.getString(6);
                String image=rs.getString(7);
                String createAt=convertDateTimeFormat(rs.getString(8));
                String updatedAt=convertDateTimeFormat(rs.getString(9));
                String content=rs.getString(10);
                String groupName=rs.getString(11);
                String accountName=rs.getString(12);
                int view=rs.getInt(13);
                data.add(new News(id, accountId, groupId, title, heading, author, image, view, createAt, updateAt, content, groupName, accountName));
            }
            cnn.close();
        }catch(SQLException e){
            System.out.println("getListNewsByGid: "+e.getMessage());
        }
        return data;
    }
    public List<News> getListNewsAndSearch(String search){
        List<News> data=new ArrayList<News>();
        try{
            connect();
            String strSelect="select n.id, n.accountID, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [Account] a on a.AccountID = n.accountID\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' and (n.title like '%" + search + "%' or n.author like '%" + search + "%')";
            pstm=cnn.prepareStatement(strSelect);
            rs=pstm.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                int accountId=rs.getInt(2);
                int groupId=rs.getInt(3);
                String title=rs.getString(4);
                String heading=rs.getString(5);
                String author=rs.getString(6);
                String image=rs.getString(7);
                int view=rs.getInt(8);
                String createAt=convertDateTimeFormat(rs.getString(9));
                String updateAt=convertDateTimeFormat(rs.getString(10));
                String content=rs.getString(11);
                String groupName=rs.getString(12);
                String accountName=rs.getString(13);
                data.add(new News(id, accountId, groupId, title, heading, author, image, view, createAt, updateAt, content, groupName, accountName));
            }
            cnn.close();
        }catch(SQLException e){
            System.out.println("getListNewsAndSearch 1 param: "+e.getMessage());
        }
        return data;
    }
    public List<News> getNewsByGidAndSearch(int gid,String search){
        List<News> data=new ArrayList<News>();
        try{
            connect();
            String selectStr="select n.id, n.accountID, n.groupId,n.title, n.heading, n.author, n.[image],n.createdAt, n.updatedAt, n.content, ng.[name], \n"
                    + "(na.firstName + ' ' + na.lastName) as adminName, n.[view] from News n join NewsGroup ng on n.groupId = ng.id \n"
                    + "join [Account] na on n.accountID = na.AccountID where n.groupId = ? and (n.title like '%" + search + "%' or n.author like '%" + search + "%')";
            pstm=cnn.prepareStatement(selectStr);
            pstm.setInt(1, gid);
            rs=pstm.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                int accountId=rs.getInt(2);
                int groupId=rs.getInt(3);
                String title=rs.getString(4);
                String heading=rs.getString(5);
                String author=rs.getString(6);
                String image=rs.getString(7);
                String createAt=rs.getString(8);
                String updatedAt=rs.getString(9);
                String content=rs.getString(10);
                String groupName=rs.getString(11);
                String accountName=rs.getString(12);
                int view=rs.getInt(13);
                data.add(new News(id, accountId, groupId, title, heading, author, image, view, createAt, updatedAt, content, groupName, accountName));
            }
            cnn.close();
        }catch(SQLException e){
            System.out.println("getNewsbyGidAndSearch 2 params: "+e.getMessage());
        }
        return data;
    }
    public void addNews(int aid ,int gid,String title,String image,String heading,String author,String createdAt,String content){
        try{
            connect();
            String strAdd="INSERT INTO [News]([accountID], [groupId], [title], [image], [heading], [author], [createdAt], [content])"
                    + " values (?,?,?,?,?,?,?,?)";
            pstm=cnn.prepareStatement(strAdd);
            pstm.setInt(1, aid);
            pstm.setInt(2, gid);
            pstm.setString(3, title);
            pstm.setString(4, image);
            pstm.setString(5, heading);
            pstm.setString(6, author);
            pstm.setString(7, createdAt);
            pstm.setString(8, content);
            pstm.execute();
            cnn.close();
        }catch(SQLException e){
            System.out.println("addNews: "+e.getMessage());
        }
    }
    public void updateNews(int gid,String title,String image,String heading,String author,String updatedAt,String content,int id){
        try{
            connect();
            String strUpdate="UPDATE [dbo].[News]\n"
                    + "   SET [groupId] = ?\n"
                    + "      ,[title] = ?\n"
                    + "      ,[image] = ?\n"
                    + "      ,[heading] = ?\n"
                    + "      ,[author] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + "      ,[content] = ?\n"
                    + " WHERE id = ?";
            pstm=cnn.prepareStatement(strUpdate);
            pstm.setInt(1, gid);
            pstm.setString(2, title);
            pstm.setString(3, image);
            pstm.setString(4, heading);
            pstm.setString(5, author);
            pstm.setString(6, updatedAt);
            pstm.setString(7, content);
            pstm.setInt(8, id);
            pstm.execute();
            cnn.close();
        }catch(Exception e){
            System.out.println("updateNews: "+e.getMessage());
        }
    }
    public void updatePolicy(String title,String updatedAt,String content,int id){
        try{
            connect();
            String strUpdate="UPDATE [dbo].[News]\n"
                    + "   SET [title] = ?\n"                    
                    + "      ,[updatedAt] = ?\n"
                    + "      ,[content] = ?\n"
                    + " WHERE id = ?";
            pstm=cnn.prepareStatement(strUpdate);
            pstm.setString(1, title);
            pstm.setString(2, updatedAt);
            pstm.setString(3, content);
            pstm.setInt(4, id);
            pstm.execute();
            cnn.close();
            
        }
        catch(Exception e){
            System.out.println("updatePolicy: "+e.getMessage());
        }
    }
    public void updateFooterContent(String title,String content,String updatedAt,int id){
        try{
            String strUpdate="UPDATE [dbo].[News]\n"
                    + "   SET [title] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + "      ,[content] = ?\n"
                    + " WHERE id = ?";
            pstm=cnn.prepareStatement(strUpdate);
            pstm.setString(1, title);
            pstm.setString(2, updatedAt);
            pstm.setString(3, content);
            pstm.setInt(4, id);
            pstm.execute();
            cnn.close();
        }catch(Exception e){
            System.out.println("updateFooterContent: "+e.getMessage());
        }
    }
    public void DeleteContents(String sid,String nid){
        connect();
        int x=1;
        try{
            String strSelect="delete from News where id = ?";
            pstm=cnn.prepareStatement(strSelect);
            pstm.setString(1, sid);
            pstm.execute();
        }catch(Exception e){
            System.out.println("DeleteContent: "+e.getMessage());
        }
        List<Integer> data=new ArrayList<Integer>();
        try{
            String strSelect="select News.id from [News] where groupId = ? order by STT asc";
            pstm=cnn.prepareStatement(strSelect);
            pstm.setString(1, nid);
            rs=pstm.executeQuery();
            while(rs.next()){
                id=rs.getInt(1);
                data.add(id);
            }
        }catch(Exception e){
            System.out.println("getListContentByGid: "+e.getMessage());
        }
            try{
                for(Integer n : data){
                    String strUpdate="UPDATE [News]\n"
                        + "   SET [STT] = ?\n"
                        + " WHERE id = ?";
                    pstm=cnn.prepareStatement(strUpdate);
                    pstm.setInt(1, x);
                    pstm.setInt(2, n);
                    pstm.execute();
                    x++;
                    
                }
                cnn.close();
            }catch (Exception e){
                System.out.println("DeleteContent: "+e.getMessage());
            }      
    }
    public void DeleteNews(String sid){
        try{
            connect();
            String strSelect="delete from News where id = ?";
            pstm=cnn.prepareStatement(strSelect);
            pstm.setString(1, sid);
            pstm.execute();
            cnn.close();
        }catch(Exception e){
            System.out.println("DeleteContent: "+e.getMessage());
        }
    }
    public void isSlideBanner(String id){
        try{
            connect();
            String strSelect="update [News] set [STT] = 1 where id= ?";
            pstm=cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.execute();
            cnn.close();
        }catch(Exception e){
            System.out.println("isSlideBanner: "+e.getMessage());
        }
    }
    public void notSlideBanner(String id){
        try{
            connect();
            String strSelect="update [News] set [STT] = NULL where id= ?";
            pstm=cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.execute();
            cnn.close();
        }catch(Exception e){
            System.out.println("notSlideBanner: "+e.getMessage());
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
