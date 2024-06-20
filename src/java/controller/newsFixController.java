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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import dal.*;
/**
 *
 * @author Dell
 */
public class newsFixController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession s = req.getSession();
    String submitType = req.getParameter("submit");

    String author = req.getParameter("author");
    String cateId = req.getParameter("cateId");
    String title = req.getParameter("title");
    String heading = req.getParameter("heading");
    String content = req.getParameter("content");
    String image = req.getParameter("image");

    // Trim the inputs to remove any leading or trailing spaces
    author = (author != null) ? author.trim() : "";
    title = (title != null) ? title.trim() : "";
    heading = (heading != null) ? heading.trim() : "";
    content = (content != null) ? content.trim() : "";

    NewsDAO n = new NewsDAO();
    NewsGroupDAO ng = new NewsGroupDAO();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String formatImage = extractImageSrc(image);
    if (formatImage == null) {
        formatImage = "";
    }

    try {
        if (submitType.equals("1")) {
            String newsId = req.getParameter("newsId");
            if (newsId == null || cateId == null) {
                throw new IllegalArgumentException("News ID or Category ID is missing.");
            }

            String cateName = ng.getNewsGroupById(Integer.parseInt(cateId)).getName();
            String updateAt = dtf.format(now);
            if (!heading.isEmpty() && !author.isEmpty() && !title.isEmpty() && !content.isEmpty()) {
                if (cateName.equalsIgnoreCase("Policy")) {
                    n.updatePolicy(title, updateAt, content, Integer.parseInt(newsId));
                } else {
                    n.updateNews(Integer.parseInt(cateId), title, formatImage, heading, author, updateAt, content, Integer.parseInt(newsId));
                }
                s.setAttribute("functionToast", "showToast('success','Update news successfully!')");
            } else {
                s.setAttribute("functionToast", "showToast('info','Some input(s) are blank!')");
                req.setAttribute("selectNews", n.getNewsById(Integer.parseInt(newsId)));
                req.setAttribute("groups", ng.getListNewsGroupWithoutPolicy());
                String imageFormat = "<p><img src=\"" + n.getNewsById(Integer.parseInt(newsId)).getImage() + "\" width=\"572\" height=\"322\" /></p>";
                req.setAttribute("imageFormat", imageFormat);
                req.getRequestDispatcher("newsDetailManagement.jsp").forward(req, resp);
                return;
            }
        } else {
            if (cateId == null) {
                throw new IllegalArgumentException("Category ID is missing.");
            }

            if (!heading.isEmpty() && !author.isEmpty() && !title.isEmpty() && !content.isEmpty()) {
                String createAt = dtf.format(now);
                Account a = (Account) s.getAttribute("acc");
                n.addNews(a.getAccountID(), Integer.parseInt(cateId), title, formatImage, heading, author, createAt, content);
                s.setAttribute("functionToast", "showToast('success','Add news successfully!')");
            } else {
                s.setAttribute("functionToast", "showToast('info','Some input(s) are blank!')");
                req.setAttribute("heading", heading);
                req.setAttribute("title", title);
                req.setAttribute("content", content);
                req.setAttribute("author", author);
                req.setAttribute("heading", heading);
                String imageFormat = "<p><img src=\"" + formatImage + "\" width=\"572\" height=\"322\" /></p>";
                req.setAttribute("imageFormat", imageFormat);
                req.setAttribute("cateId", cateId);
                req.setAttribute("groups", ng.getListNewsGroupWithoutPolicy());
                req.getRequestDispatcher("newsDetailManagement.jsp").forward(req, resp);
                return;
            }
        }
        resp.sendRedirect("news");
    } catch (NumberFormatException e) {
        s.setAttribute("functionToast", "showToast('error','Invalid input: " + e.getMessage() + "')");
        req.getRequestDispatcher("newsDetailManagement.jsp").forward(req, resp);
    } catch (IllegalArgumentException e) {
        s.setAttribute("functionToast", "showToast('error','" + e.getMessage() + "')");
        req.getRequestDispatcher("newsDetailManagement.jsp").forward(req, resp);
    }
}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public static String extractImageSrc(String html) {
        String src = null;
        if (html == null) {
            return "";
        }
        int startIndex = html.indexOf("src=\"");
        if (startIndex != -1) {
            startIndex += "src=\"".length();
            int endIndex = html.indexOf("\"", startIndex);
            if (endIndex != -1) {
                src = html.substring(startIndex, endIndex);
            }
        }
        return src;
    }

}
