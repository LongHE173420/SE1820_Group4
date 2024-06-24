/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Model.News;
import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import dal.NewsDAO;

/**
 *
 * @author Dell
 */
public class newsUserDetailController extends HttpServlet {

     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nid = req.getParameter("nid");
        req.getRequestDispatcher("newsUserDetail").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        NewsDAO newsDao = new NewsDAO();
        
        List<News> newsList = newsDao.getListNews();
        int selectId = -1;

        for (News aNew : newsList) {
            if (title.equalsIgnoreCase(toSlug(aNew.getTitle()))) {
                selectId = aNew.getId();
                break;
            }
        }
        
        News selectedNews = newsDao.getNewsById(selectId);
        CategoryDAO categoryDao = new CategoryDAO();
        
        req.setAttribute("cList", categoryDao.getListCategory());
        req.setAttribute("news", newsList); // This can be used to show related news
        req.setAttribute("selectNew", selectedNews);
    
        req.getRequestDispatcher("newsUserDetail.jsp").forward(req, resp);
    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}
