/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import Model.Account;
import Model.News;
import Model.NewsGroup;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dal.*;
/**
 *
 * @author Dell
 */
public class newsDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession();
        s.setAttribute("updateNewsId", req.getParameter("updateNewsId"));
        resp.sendRedirect("newsDetail");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // go to update news page
        HttpSession s = req.getSession();
        if (s.getAttribute("acc") == null) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        Account ch = (Account) s.getAttribute("acc");
        if (!(ch.getRoleID()==1 || ch.getRoleID()==3)) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        String nid = (String) s.getAttribute("updateNewsId");
        NewsDAO n = new NewsDAO();
        NewsGroupDAO ng = new NewsGroupDAO();
        News news=new News();
        if (nid == null) {
            n = null;
        } else {
            news = n.getNewsById(Integer.parseInt(nid));
            String imageFormat = "<p><img src=\"" + news.getImage() + "\" width=\"572\" height=\"322\" /></p>";
            req.setAttribute("imageFormat", imageFormat);
        }
        req.setAttribute("selectNews", news);
        req.setAttribute("groups", ng.getListNewsGroup());
        req.getRequestDispatcher("newsDetailManagement.jsp").forward(req, resp);
    }
}
