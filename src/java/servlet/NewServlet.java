package servlet;

import Model.News;
import dal.NewsManager;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;

@MultipartConfig
public class NewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(NewServlet.class.getName());
    private NewsManager newsManager;

    @Override
    public void init() throws ServletException {
        newsManager = new NewsManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "edit":
                showEditNews(request, response);
                break;
            case "delete":
                deleteNews(request, response);
                break;
            case "view":
                showNewsDetail(request, response);
                break;
            case "add":
                showAddNews(request, response);
                break;
            default:
                listNews(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addNews(request, response);
                break;
            case "update":
                updateNews(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/news");
                break;
        }
    }

    private void listNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<News> newsList = newsManager.getAllNews();
        request.setAttribute("newsList", newsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageNews.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        News existingNews = newsManager.getNewsById(newsID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editNews.jsp");
        request.setAttribute("news", existingNews);
        dispatcher.forward(request, response);
    }

    private void showNewsDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        News news = newsManager.getNewsById(newsID);
        request.setAttribute("news", news);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewNews.jsp");
        dispatcher.forward(request, response);
    }

     private void showAddNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addNews.jsp");
        dispatcher.forward(request, response);
    }
    
    private void addNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int accountID = Integer.parseInt(request.getParameter("accountID"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String author = request.getParameter("author");
            String dateString = request.getParameter("date");

            Date date = Date.valueOf(LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            Part filePart = request.getPart("img");
            String fileName = getFileName(filePart);
            String filePath = getServletContext().getRealPath("/") + "images" + File.separator + fileName;

            File uploads = new File(getServletContext().getRealPath("/") + "images");
            if (!uploads.exists()) {
                uploads.mkdirs();
            }

            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            
            newsManager.addNews(accountID, title, description, "images/" + fileName, author, date);
            response.sendRedirect(request.getContextPath() + "/news");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding news", e);
            response.sendRedirect(request.getContextPath() + "addNews.jsp");
        }
    }

    private void updateNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int newsID = Integer.parseInt(request.getParameter("newsID"));
            int accountID = Integer.parseInt(request.getParameter("accountID"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String author = request.getParameter("author");
            String dateString = request.getParameter("date");

            Date date = Date.valueOf(LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            Part filePart = request.getPart("img");
            String fileName = getFileName(filePart);
            String filePath = getServletContext().getRealPath("/") + "images" + File.separator + fileName;
            if (filePart != null && filePart.getSize() > 0) {
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }

            newsManager.updateNews(newsID, accountID, title, description, "images/" + fileName, author, date);
            response.sendRedirect(request.getContextPath() + "/news");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating news", e);
            response.sendRedirect(request.getContextPath() + "/news");
        }
    }

    private void deleteNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        newsManager.deleteNews(newsID);
        response.sendRedirect(request.getContextPath() + "/news");
    }

    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
