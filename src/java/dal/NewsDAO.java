/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.News;
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
import java.util.Vector;

/**
 *
 * @author Dell
 */
public class NewsDAO {

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
                data.add(new News(id, accountId, groupId, title, heading, author, image, view, createAt, updateAt, content, groupName, accountName));
            }

            cnn.close();

        } catch (SQLException e) {
            System.out.println("getListNews" + e.getMessage());
        }
        return data;
    }
    public static void main(String[] args) {
        NewsDAO dao = new NewsDAO();
        List<News> list = dao.getNewsByGid(1);
        for (News news : list) {
            System.out.println(news);
        }
    }
   
    public News getNewsById(int nId) {
        try {
            connect();
            String selectStr = "select n.id, n.accountID, n.groupId,n.title, n.heading, n.author, n.[image],n.createdAt, n.updatedAt, n.content, ng.[name], \n"
                    + "(na.firstName + ' ' + na.lastName) as adminName, n.[view] from news n join NewsGroup ng on n.groupId = ng.id \n"
                    + "join [Account] na on n.accountID = na.AccountID where n.id = ?";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setInt(1, nId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                int accountId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                String createAt = convertDateTimeFormat(rs.getString(8));
                String updatedAt = convertDateTimeFormat(rs.getString(9));
                String content = rs.getString(10);
                String groupName = rs.getString(11);
                String accountName = rs.getString(12);
                int view = rs.getInt(13);
                return new News(id, accountId, groupId, title, heading, author, image, view, createAt, updatedAt, content, groupName, accountName);
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getNewsById: " + e.getMessage());
        }
        return null;
    }

    public List<News> getNewsByGid(int gid) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String selectStr = "select n.id, n.accountID, n.groupId,n.title, n.heading, n.author, n.[image],n.createdAt, n.updatedAt, n.content, ng.[name], \n"
                    + "(na.firstName + ' ' + na.lastName) as adminName, n.[view] from news n join NewsGroup ng on n.groupId = ng.id \n"
                    + "join [Account] na on n.accountID = na.AccountID where n.groupId = ? order by n.createdAt";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setInt(1, gid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int accountId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                String createAt = convertDateTimeFormat(rs.getString(8));
                String updatedAt = convertDateTimeFormat(rs.getString(9));
                String content = rs.getString(10);
                String groupName = rs.getString(11);
                String accountName = rs.getString(12);
                int view = rs.getInt(13);
                data.add(new News(id, accountId, groupId, title, heading, author, image, view, createAt, updatedAt, content, groupName, accountName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNewsByGid: " + e.getMessage());
        }
        return data;
    }

    public List<News> getListNewsAndSearch(String search) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.accountID, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName from news n join [Account] a on a.AccountID = n.accountID\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' and (n.title like '%" + search + "%' or n.author like '%" + search + "%')";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
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
                data.add(new News(id, accountId, groupId, title, heading, author, image, view, createAt, updateAt, content, groupName, accountName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListNewsAndSearch 1 param: " + e.getMessage());
        }
        return data;
    }

    public List<News> getNewsByGidAndSearch(int gid, String search) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String selectStr = "select n.id, n.accountID, n.groupId,n.title, n.heading, n.author, n.[image],n.createdAt, n.updatedAt, n.content, ng.[name], \n"
                    + "(na.firstName + ' ' + na.lastName) as adminName, n.[view] from News n join NewsGroup ng on n.groupId = ng.id \n"
                    + "join [Account] na on n.accountID = na.AccountID where n.groupId = ? and (n.title like '%" + search + "%' or n.author like '%" + search + "%')";
            pstm = cnn.prepareStatement(selectStr);
            pstm.setInt(1, gid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int accountId = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                String heading = rs.getString(5);
                String author = rs.getString(6);
                String image = rs.getString(7);
                String createAt = rs.getString(8);
                String updatedAt = rs.getString(9);
                String content = rs.getString(10);
                String groupName = rs.getString(11);
                String accountName = rs.getString(12);
                int view = rs.getInt(13);
                data.add(new News(id, accountId, groupId, title, heading, author, image, view, createAt, updatedAt, content, groupName, accountName));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getNewsbyGidAndSearch 2 params: " + e.getMessage());
        }
        return data;
    }

    public void addNews(int aid, int gid, String title, String image, String heading, String author, String createdAt, String content) {
        try {
            connect();
            String strAdd = "INSERT INTO [News]([accountID], [groupId], [title], [image], [heading], [author], [createdAt], [content])"
                    + " values (?,?,?,?,?,?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
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
        } catch (SQLException e) {
            System.out.println("addNews: " + e.getMessage());
        }
    }
   

    public void updateNews(int gid, String title, String image, String heading, String author, String updatedAt, String content, int id) {
        try {
            connect();
            String strUpdate = "UPDATE [dbo].[News]\n"
                    + "   SET [groupId] = ?\n"
                    + "      ,[title] = ?\n"
                    + "      ,[image] = ?\n"
                    + "      ,[heading] = ?\n"
                    + "      ,[author] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + "      ,[content] = ?\n"
                    + " WHERE id = ?";
            pstm = cnn.prepareStatement(strUpdate);
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
        } catch (Exception e) {
            System.out.println("updateNews: " + e.getMessage());
        }
    }

    public void updatePolicy(String title, String updatedAt, String content, int id) {
        try {
            connect();
            String strUpdate = "UPDATE [dbo].[News]\n"
                    + "   SET [title] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + "      ,[content] = ?\n"
                    + " WHERE id = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, title);
            pstm.setString(2, updatedAt);
            pstm.setString(3, content);
            pstm.setInt(4, id);
            pstm.execute();
            cnn.close();

        } catch (Exception e) {
            System.out.println("updatePolicy: " + e.getMessage());
        }
    }

    public void updateFooterContent(String title, String content, String updatedAt, int id) {
        try {
            String strUpdate = "UPDATE [dbo].[News]\n"
                    + "   SET [title] = ?\n"
                    + "      ,[updatedAt] = ?\n"
                    + "      ,[content] = ?\n"
                    + " WHERE id = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, title);
            pstm.setString(2, updatedAt);
            pstm.setString(3, content);
            pstm.setInt(4, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("updateFooterContent: " + e.getMessage());
        }
    }

    public void DeleteContents(String sid, String nid) {
        connect();
        int x = 1;
        try {
            String strSelect = "delete from News where id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sid);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("DeleteContent: " + e.getMessage());
        }
        List<Integer> data = new ArrayList<Integer>();
        try {
            String strSelect = "select News.id from [News] where groupId = ? order by STT asc";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, nid);
            rs = pstm.executeQuery();
            while (rs.next()) {

                int id = rs.getInt(1);
                data.add(id);
            }
        } catch (Exception e) {
            System.out.println("getListContentByGid: " + e.getMessage());
        }
        try {
            for (Integer n : data) {
                String strUpdate = "UPDATE [News]\n"
                        + "   SET [STT] = ?\n"
                        + " WHERE id = ?";
                pstm = cnn.prepareStatement(strUpdate);
                pstm.setInt(1, x);
                pstm.setInt(2, n);
                pstm.execute();
                x++;

            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("DeleteContent: " + e.getMessage());
        }
    }

    public void DeleteNews(String sid) {
        try {
            connect();
            String strSelect = "delete from News where id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, sid);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("DeleteContent: " + e.getMessage());
        }
    }

    public void isSlideBanner(String id) {
        try {
            connect();
            String strSelect = "update [News] set [STT] = 1 where id= ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("isSlideBanner: " + e.getMessage());
        }
    }

    public void notSlideBanner(String id) {
        try {
            connect();
            String strSelect = "update [News] set [STT] = NULL where id= ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("notSlideBanner: " + e.getMessage());
        }
    }

    public List<News> getListByPageAndGroupAndSort(int index, String grouped, String sorted) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.accountID, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName, n.STT from News n join [Account] a on a.AccountID = n.accountID\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' ";
            if (!grouped.equals("-1")) {
                strSelect += "and n.groupId = " + grouped;
            }
            if (!sorted.equals("-1")) {
                strSelect += "order by" + sorted + "desc \n";
            } else {
                strSelect += "order by n.id";
            }
            strSelect += "Offset ? rows fetch first 5 rows only";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (index - 1) * 5);
            rs = pstm.executeQuery();
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
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String accountName = rs.getString(13);
                int STT = rs.getInt(14);
                data.add(new News(id, accountId, groupId, title, heading, author, image, view, createAt, updatedAt, content, groupName, accountName, STT));

            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListByPageAndGroupAndSort: " + e.getMessage());
        }
        return data;
    }

    public List<News> getListContentByGid(int gid) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select News.id, News.accountID, News.groupId, News.title, News.STT, News.[image], News.link, News.createdAt, News.updatedAt, News.content from News\n"
                    + "join NewsGroup on News.groupId = NewsGroup.id\n"
                    + "where NewsGroup.id = ? ORDER BY News.STT ASC ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, gid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int accountID = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                int stt = rs.getInt(5);
                String image = rs.getString(6);
                String link = rs.getString(7);
                String createAt = convertDateTimeFormat(rs.getString(8));
                String updateAt = convertDateTimeFormat(rs.getString(9));
                String content = rs.getString(10);
                data.add(new News(id, accountID, groupId, title, image, stt, link, createAt, updateAt, content));
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListContentByGid: " + e.getMessage());
        }
        return data;
    }

    public List<News> getListByPagesAndGroupAndSortAndSearch(int index, String grouped, String sorted, String search) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = "select n.id, n.accountID, n.groupId, n.title, n.heading, n.author, n.[image], n.[view], n.createdAt, n.updatedAt,\n"
                    + "n.content, ng.[name], (a.firstName + ' ' + a.lastName) as adminName, n.STT from news n join [Account] a on a.AccountID = n.accountID\n"
                    + "join NewsGroup ng on n.groupId = ng.id where ng.[type] = 'news' ";
            if (!grouped.equals("-1")) {
                strSelect += " and n.groupId = " + grouped;
            }
            if (search != null) {
                strSelect += " and (n.title like '%" + search + "%' or n.author like '%" + search + "%') ";
            } else {
                strSelect += " and n.title like '%%' ";
            }
            if (!sorted.equals("-1")) {
                strSelect += " order by " + sorted + " desc \n";
            } else {
                strSelect += "order by n.id ";
            }
            strSelect += " Offset ? rows fetch first 5 rows only";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (index - 1) * 5);
            rs = pstm.executeQuery();
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
                String updatedAt = convertDateTimeFormat(rs.getString(10));
                String content = rs.getString(11);
                String groupName = rs.getString(12);
                String accountName = rs.getString(13);
                int STT = rs.getInt(14);
                data.add(new News(id, accountId, groupId, title, heading, author, image, view, createAt, updatedAt, content, groupName, accountName, STT));
            }
            cnn.close();
        } catch (SQLException e) {
            System.out.println("getListByPagesAndGroupAndSortAndSearch:" + e.getMessage());
        }
        return data;
    }

    public List<News> getListContentsByName(String name) {
        List<News> data = new ArrayList<News>();
        try {
            connect();
            String strSelect = " select News.id, News.adminId, News.groupId, News.title, News.STT, News.[image],"
                    + " News.link, News.createdAt, News.updatedAt, News.content from News \n"
                    + " join NewsGroup on News.groupId = NewsGroup.id\n"
                    + " where NewsGroup.name = ? \n"
                    + " ORDER BY News.STT ASC";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int accountID = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                int stt = rs.getInt(5);
                String image = rs.getString(6);
                String link = rs.getString(7);
                String createAt = convertDateTimeFormat(rs.getString(8));
                String updateAt = convertDateTimeFormat(rs.getString(9));
                String content = rs.getString(10);
                data.add(new News(id, accountID, groupId, title, image, stt, link, createAt, updateAt, content));
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getListContentsByName: " + e.getMessage());
        }
        return data;
    }

    public News getContentById(int id) {
        try {
            connect();
            String strSelect = " select News.id, News.adminId, News.groupId, News.title, News.STT, News.[image], \n"
                    + "News.link, News.createdAt, News.updatedAt, News.content from News\n"
                    + "    join NewsGroup on News.groupId = NewsGroup.id\n"
                    + "    where News.id = ?\n"
                    + "    ORDER BY News.STT ASC";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                int accountID = rs.getInt(2);
                int groupId = rs.getInt(3);
                String title = rs.getString(4);
                int stt = rs.getInt(5);
                String image = rs.getString(6);
                String link = rs.getString(7);
                String createAt = convertDateTimeFormat(rs.getString(8));
                String updateAt = convertDateTimeFormat(rs.getString(9));
                String content = rs.getString(10);
                return new News(id, accountID, groupId, title, image, stt, link, createAt, updateAt, content);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getContentsById: " + e.getMessage());
        }
        return null;
    }

    public void UpdateSTT(int[] order, String nid) {
        List<Integer> data = new ArrayList<Integer>();
        connect();
        int x = 1;
        try {

            for (int i = 0; i < order.length; i++) {
                String strSelect = "select id from [News] where groupId = ? and STT = ? ";
                pstm = cnn.prepareStatement(strSelect);
                pstm.setString(1, nid);
                pstm.setInt(2, order[i]);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    data.add(id);
                }
            }
        } catch (Exception e) {
            System.out.println("UpdateSTT_1: " + e.getMessage());
        }

        try {
            for (Integer n : data) {
                System.out.println(n);
                String strUpdate = "UPDATE [News]\n"
                        + "   SET [STT] = ?\n"
                        + " WHERE id = ?";
                pstm = cnn.prepareStatement(strUpdate);
                pstm.setInt(1, x);
                pstm.setInt(2, n);
                pstm.execute();
                x++;
            }
            System.out.println(x);
            cnn.close();
        } catch (Exception e) {
            System.out.println("UpdateSTT_2: " + e.getMessage());
        }
    }

    public void AddContents(String name, String accountID, int STT, String link, String cr, String gr) {
        try {
            connect();
            String strSelect = " insert into [News](accountID, groupId, title, STT, link, createdAt)\n"
                    + " values\n"
                    + " (?, ?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, accountID);
            pstm.setString(2, gr);
            pstm.setString(3, name);
            pstm.setInt(4, STT);
            pstm.setString(5, link);
            pstm.setString(6, cr);
            pstm.execute();
            cnn.close();
        } catch (Exception e) {
            System.out.println("AddContents: " + e.getMessage());
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

    public News getContentsFootlinkContent() {
        try {
            connect();
            String strSelect = " select News.id, News.adminId, News.groupId, News.title, News.STT, News.[image],\n"
                    + "News.link, News.createdAt, News.updatedAt, News.content from News join NewsGroup on\n"
                    + "News.groupId = NewsGroup.id where NewsGroup.name = 'footerContent' ORDER BY News.STT ASC";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                int adminId = rs.getInt(2);
                int groupID = rs.getInt(3);
                String title = rs.getString(4);
                int stt = rs.getInt(5);
                String image = rs.getString(6);
                String link = rs.getString(7);
                String createAt = convertDateTimeFormat(rs.getString(8));
                String updatedAt = convertDateTimeFormat(rs.getString(9));
                String content = rs.getString(10);
                return new News(id, adminId, groupID, title, image, stt, link, createAt, updatedAt, content);
            }
            cnn.close();
        } catch (Exception e) {
            System.out.println("getContentsFootlinkContent: " + e.getMessage());
        }
        return null;
    }
//     public String toString() {
//        return "News{" + "id=" + id + ", title=" + title + ", stt=" + stt + ", link=" + link + ", content=" + content + '}';
//    }
}
