/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package servlet;

import Model.News;
import dal.NewsManager;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
public class NewServlet extends HttpServlet {
   
     private static final long serialVersionUID = 1L;
    private NewsManager newsManager;

    public void init() {
        newsManager = new NewsManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        switch (action) {
            case "edit":
                editNews(request, response);
                break;
            case "delete":
                deleteNews(request, response);
                break;
            default:
                viewNews(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "addOrUpdate":
                addOrUpdateNews(request, response);
                break;
            case "delete":
                deleteNews(request, response);
                break;
            default:
                viewNews(request, response);
                break;
        }
    }

    private void addOrUpdateNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int newsID = request.getParameter("newsID").isEmpty() ? 0 : Integer.parseInt(request.getParameter("newsID"));
            int accountID = Integer.parseInt(request.getParameter("accountID"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String img = request.getParameter("img");
            String author = request.getParameter("author");
            String date = request.getParameter("date");

            if (newsID == 0) {
                newsManager.addNews(accountID, title, description, img, author, date);
            } else {
                newsManager.updateNews(newsID, accountID, title, description, img, author, date);
            }
            response.sendRedirect("news");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra khi thêm tin tức: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("news.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        newsManager.deleteNews(newsID);
        response.sendRedirect("news");
    }

    private void editNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        News news = newsManager.getNewsById(newsID);
        request.setAttribute("news", news);
        RequestDispatcher dispatcher = request.getRequestDispatcher("news.jsp");
        dispatcher.forward(request, response);
    }

    private void viewNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("newsList", newsManager.getAllNews());
        RequestDispatcher dispatcher = request.getRequestDispatcher("news.jsp");
        dispatcher.forward(request, response);
    }
}
