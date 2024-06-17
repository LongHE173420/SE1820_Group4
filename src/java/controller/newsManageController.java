/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import Model.Account;
import Model.News;
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
public class newsManageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession();
        String act = req.getParameter("act");
        NewsDAO n = new NewsDAO();
        News news= new News();
        String nid = req.getParameter("newsId");
        News deletedNews = n.getNewsById(Integer.parseInt(nid));
        String gr = deletedNews.getGroupName();
        news = n.getContentById(Integer.parseInt(nid));
        if (act.equals("delete")) {
            //Delete news here by id   
            s.removeAttribute("updateNewsId");

            if (gr.equalsIgnoreCase("Policy")) {
                s.setAttribute("functionToast", "showToast('warning','You can not delete the policy!')");
            } else {
                if (news.getStt() == 1) {
                    s.setAttribute("functionToast", "showToast('warning','You can not delete the news currently in slideshare!')");
                } else {
                    n.DeleteNews(nid);
                    s.setAttribute("functionToast", "showToast('success','Delete news successfully!')");
                }
            }
        } else if (act.equals("isSlide")) {
            if (gr.equalsIgnoreCase("Policy")) {
                s.setAttribute("functionToast", "showToast('warning','You can not show policy on slideshare!')");
            } else {
                n.isSlideBanner(nid);
                s.setAttribute("functionToast", "showToast('success','Edit slideshare successfully!')");
            }
        } else {
            if (news.getLink() == null) {
                n.notSlideBanner(nid);
                s.setAttribute("functionToast", "showToast('success','Edit slideshare successfully!')");
            }
        }
        resp.sendRedirect("newsManage");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession s = req.getSession();
//        if (s.getAttribute("acc") == null) {
//            req.getRequestDispatcher("403.jsp").forward(req, resp);
//        }
//        Account ch = (Account) s.getAttribute("acc");
//        if (!(ch.getRole().equals("Admin") || ch.getRole().equals("NewsManage"))) {
//            req.getRequestDispatcher("403.jsp").forward(req, resp);
//        }
        NewsDAO n = new NewsDAO();
        req.setAttribute("news", n.getListNews());
        resp.sendRedirect("news");
    }

}
