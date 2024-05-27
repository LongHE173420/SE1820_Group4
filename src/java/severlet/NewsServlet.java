package severlet;

import dal.NewsDAO;
import entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class NewsServlet extends HttpServlet {
    private NewsDAO newsDAO;

    @Override
    public void init() throws ServletException {
        newsDAO = new NewsDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<News> listNews = newsDAO.listAllNews();
        StringBuilder newsJson = new StringBuilder();
        newsJson.append("[");

        for (int i = 0; i < listNews.size(); i++) {
            News news = listNews.get(i);
            newsJson.append("{");
            newsJson.append("\"newsID\":").append(news.getNewsID()).append(",");
            newsJson.append("\"accountID\":").append(news.getAccountID()).append(",");
            newsJson.append("\"title\":\"").append(news.getTitle()).append("\",");
            newsJson.append("\"description\":\"").append(news.getDescription()).append("\",");
            newsJson.append("\"imageUrl\":\"").append(news.getImageUrl()).append("\",");
            newsJson.append("\"author\":\"").append(news.getAuthor()).append("\",");
            newsJson.append("\"date\":\"").append(news.getDate()).append("\"");
            newsJson.append("}");
            if (i < listNews.size() - 1) {
                newsJson.append(",");
            }
        }

        newsJson.append("]");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(newsJson.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");
        String author = request.getParameter("author");
        String date = request.getParameter("date");

        News news = new News();
        news.setAccountID(accountID);
        news.setTitle(title);
        news.setDescription(description);
        news.setImageUrl(imageUrl);
        news.setAuthor(author);
        news.setDate(date);

        newsDAO.addNews(news);
        response.sendRedirect("news.html");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");
        String author = request.getParameter("author");
        String date = request.getParameter("date");

        News news = new News();
        news.setNewsID(newsID);
        news.setAccountID(accountID);
        news.setTitle(title);
        news.setDescription(description);
        news.setImageUrl(imageUrl);
        news.setAuthor(author);
        news.setDate(date);

        newsDAO.updateNews(news);
        response.sendRedirect("news.html");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        newsDAO.deleteNews(newsID);
        response.sendRedirect("news.html");
    }
}
