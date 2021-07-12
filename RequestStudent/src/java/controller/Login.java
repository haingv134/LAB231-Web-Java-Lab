/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Untils.SessionUnilt;
import dao.AdminDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haiva
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUnilt su = new SessionUnilt();
        SimpleDateFormat sfdm = new SimpleDateFormat("EEEEE dd/MM/yyyy");
        request.setAttribute("welcomeMsg", sfdm.format(new Date()));
        if (!su.getValue(request, "username").equalsIgnoreCase("null")) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            su.pushValue(request, "name", "");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (new AdminDAO().getAdmin(username, password).size() > 0) {
            SessionUnilt su = new SessionUnilt();
            su.pushValue(request, "username", username);
            su.pushValue(request, "password", password);
            su.pushValue(request, "name", su.getValue(request, "username"));
            response.sendRedirect(request.getContextPath() + "/home");
        } else {         
            request.setAttribute("msg", "Invalid username/password!");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
