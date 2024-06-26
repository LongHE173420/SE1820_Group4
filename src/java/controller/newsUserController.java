/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Model.News;
import Model.NewsGroup;
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
import dal.NewsGroupDAO;
import dal.CategoryDAO;

/**
 *
 * @author Dell
 */
public class newsUserController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String groupName = req.getParameter("groupName");
        if (page == null) {
            page = "1";
        }
        NewsGroupDAO ng1 = new NewsGroupDAO();
        List<NewsGroup> groups = ng1.getListNewsGroup();
        int gid = -1;
        NewsDAO n = new NewsDAO();
        CategoryDAO c = new CategoryDAO();
        req.setAttribute("cList", c.getListCategory());
        
        if (groupName == null || groupName.isEmpty()) {
            req.setAttribute("news", n.getListByPages(Integer.parseInt(page)));
            req.setAttribute("groups", groups);
            req.setAttribute("count", calThePage(5, gid));
        } else {
            for (NewsGroup group : groups) {
                if (groupName.equals(toSlug(group.getName()))) {
                    gid = group.getId();
                }
            }
            if (gid == -1) {
                req.setAttribute("news", n.getListByPages(Integer.parseInt(page)));
            } else {
                req.setAttribute("news", n.getListByPagesAndGroup(Integer.parseInt(page), gid));
            }
            req.setAttribute("cList", c.getListCategory());
            req.setAttribute("groups", groups);
            req.setAttribute("count", calThePage(5, gid));

            NewsGroup selectedGroup = ng1.getNewsGroupById(gid);
            if (selectedGroup != null) {
                req.setAttribute("selectGroup", selectedGroup);
                req.setAttribute("pageGroup", toSlug(selectedGroup.getName()));
            } else {
                req.setAttribute("selectGroup", null);
                req.setAttribute("pageGroup", null);
            }
        }
        req.setAttribute("page", page);
        req.getRequestDispatcher("newsUser.jsp").forward(req, resp);
    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static int calThePage(int sizePage, int gid) {
        NewsDAO n = new NewsDAO();
        int pages = 0;
        int countNews = n.getListNews().size();

        if (gid != -1) {
            countNews = n.getNewsByGid(gid).size();
        }
        if (countNews % sizePage == 0) {
            pages = countNews / sizePage;
        } else {
            pages = countNews / sizePage + 1;
        }
        return pages;
    }

}
