/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Model.Account;
import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author quyen
 */
public class loginController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("user");
        String password = req.getParameter("password");
        req.setAttribute("user", username);
        AccountDAO accountDAO = new AccountDAO();
        Account acc = accountDAO.loginUser(username, password);
        if (acc == null) {
            req.setAttribute("mess", "Your account is wrong! Please try again!");
            req.getRequestDispatcher("signIn.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("acc", acc);
            resp.sendRedirect("home");
        }
    }

}
