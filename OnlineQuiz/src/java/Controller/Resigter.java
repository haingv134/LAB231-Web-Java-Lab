/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import Model.UserModel;
import Untils.SessionUnilt;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haiva
 */
@WebServlet(name = "Resigter", urlPatterns = {"/register"})
public class Resigter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUnilt su = new SessionUnilt();
        String username = su.getValue(request, "username");
        if (username.equalsIgnoreCase("null")) {
            response.sendRedirect(request.getContextPath() + "/register.jsp");
        } else {
            request.setAttribute("usertype", su.getValue(request, "usertype"));
            request.setAttribute("username", username);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUnilt su = new SessionUnilt();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String usertype = request.getParameterValues("usertype")[0];
        String email = request.getParameter("email");
        su.pushValue(request, "username", username);
        su.pushValue(request, "password", password);
        su.pushValue(request, "usertype", usertype);
        try {
            new UserDAO().addUser(new UserModel(0, username, password, usertype, email));
            response.sendRedirect(request.getContextPath() + "/quizSetup");
        } catch (Exception e) {
            request.setAttribute("msg", "Registration error: " + e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
