/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import entity.Product;
import entity.ProductCart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
//cart
public class CartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        ProductDAO productDao = new ProductDAO();
        Enumeration<String> emm = session.getAttributeNames();
        if (service == null) {
            service = "showCart";
        }
        if (service.equals("addToCart")) {
            String id = (String) request.getParameter("id");
            ProductCart productCart = (ProductCart) session.getAttribute(id);
            if (productCart == null) {
                productCart = new ProductCart();
                int productId = Integer.parseInt(id);
                Vector<Product> vec = productDao.getBySql("select * from product where productID = '" + productId + "'");
                Product product = vec.get(0);
                productCart.setProductId(product.getProductId());
                productCart.setName(product.getName());
                productCart.setPrice(product.getPrice());
                productCart.setQuantity(1);
                session.setAttribute(id, productCart);
            } else {
                productCart.setQuantity(productCart.getQuantity() + 1);
                session.setAttribute(id, productCart);
            }
            response.sendRedirect("home");
        }
        if (service.equals("showCart")) {
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public Vector<ProductCart> getSessionCart(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Vector<ProductCart> listBook = new Vector<>();
        Enumeration<String> em = session.getAttributeNames();
        //BookDAO bookDao = new BookDAO();
        while (em.hasMoreElements()) {
            String key = em.nextElement().toString(); //get key
            if (key.equals("user") || key.equals("vecKey")) {
                continue;
            } else {
                listBook.add((ProductCart) session.getAttribute(key));
            }

        }
        return listBook;
    }

}
