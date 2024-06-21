/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import Model.Account;
import Model.Discount;
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
public class discountController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeId = req.getParameter("groupBy");
        String search = req.getParameter("search");
        String page = req.getParameter("page");

        NewsDAO n = new NewsDAO();
        DisCountDAO d = new DisCountDAO();
        if (search.isEmpty()) {
            search = "";
        }
        if (page == null || page.equals("0")) {
            page = "1";
        }
        String type;
        if (typeId.equals("0")) {
            typeId = "-1";
            type = null;
        } else {
            type = n.getContentById(Integer.parseInt(typeId)).getContent();
        }

        if (Integer.parseInt(page) > calThePage(5, Integer.parseInt(typeId), search)) {
            page = calThePage(5, Integer.parseInt(typeId), search) + "";
        }

        req.setAttribute("groupBy", Integer.parseInt(req.getParameter("groupBy")));
        req.setAttribute("search", req.getParameter("search"));
        req.setAttribute("count", calThePage(5, Integer.parseInt(typeId), search));
        req.setAttribute("page", page);
        req.setAttribute("discounts", d.getListDiscountByTypeAndSearchAndPage(Integer.parseInt(page), type, search));
       

        req.getRequestDispatcher("discount.jsp").forward(req, resp);
    }

    public static void main(String[] args) {
        DisCountDAO d = new DisCountDAO();
        NewsDAO n = new NewsDAO();
        String type = n.getContentById(50).getContent();
        String se = "a";
//        System.out.println(type);
//        System.out.println(se);
//        System.out.println(d.getListDiscountByTypeAndSearch(type, se).size());
//      
        System.out.println(d.getListDiscountByTypeAndSearchAndPage(1, null, null));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("acc") == null) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        Account ch = (Account) session.getAttribute("acc");
        if (!(ch.getRoleID()==1 || ch.getRoleID()== 3)) {
            req.getRequestDispatcher("403.jsp").forward(req, resp);
        }
        DisCountDAO d = new DisCountDAO();
        NewsDAO n = new NewsDAO();

        req.setAttribute("types", n.getListContentsByName("discountFilter"));
        req.setAttribute("groupBy", "0");
        req.setAttribute("search", null);
        req.setAttribute("count", calThePage(5, -1, ""));
        req.setAttribute("page", "1");
        //req.setAttribute("discounts", d.getListDiscount());
        req.setAttribute("discounts", d.getListDiscountByTypeAndSearchAndPage(1, null, null));

        req.getRequestDispatcher("discount.jsp").forward(req, resp);
    }

    public int calThePage(int sizePage, int typeId, String search) {
        DisCountDAO d = new DisCountDAO();
        NewsDAO n = new NewsDAO();

        int pages = 0;
        int countDiscounts = 0;
        if (typeId != -1) {
            countDiscounts = d.getListDiscountByTypeAndSearch(n.getContentById(typeId).getContent(), search).size();
        } else {
            countDiscounts = d.getListDiscountBySearch(search).size();
        }
        if (countDiscounts % sizePage == 0) {
            pages = countDiscounts / sizePage;
        } else {
            pages = countDiscounts / sizePage + 1;
        }
        return pages;
    }
}
