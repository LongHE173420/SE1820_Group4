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
import java.util.List;

/**
 *
 * @author Dell
 */
public class NewServlet extends HttpServlet {
   
    private static final long serialVersionUID = 1L;
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
                showEditForm(request, response);
                break;
            case "delete":
                deleteNews(request, response);
                break;
            case "view":
                showNewsDetail(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/manageNews.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        News existingNews = newsManager.getNewsById(newsID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/manageNews.jsp");
        request.setAttribute("news", existingNews);
        dispatcher.forward(request, response);
    }

    private void showNewsDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        News news = newsManager.getNewsById(newsID);
        request.setAttribute("news", news);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewNews.jsp");
        dispatcher.forward(request, response);
    }

    private void addNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String img = request.getParameter("img");
        String author = request.getParameter("author");
        String date = request.getParameter("date");

        newsManager.addNews(accountID, title, description, img, author, date);
        response.sendRedirect(request.getContextPath() + "/news");
    }

    private void updateNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String img = request.getParameter("img");
        String author = request.getParameter("author");
        String date = request.getParameter("date");

        News news = new News(newsID, accountID, title, description, img, author, date);
        newsManager.updateNews(newsID, accountID, title, description, img, author, date);
        response.sendRedirect(request.getContextPath() + "/news");
    }

    private void deleteNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsID"));
        newsManager.deleteNews(newsID);
        response.sendRedirect(request.getContextPath() + "/news");
    }
}
