/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Quiz;

import DAO.QuestionDAO;
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
@WebServlet(name = "ManageQuiz", urlPatterns = {"/manageQuiz"})
public class ManageQuiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUnilt su = new SessionUnilt();
        String username = su.getValue(request, "username");
        try {
            if (username.equalsIgnoreCase("null")) {
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            } else {
                request.setAttribute("usertype", su.getValue(request, "usertype"));
                request.setAttribute("username", username);
                request.setAttribute("questionList", new QuestionDAO().getQuestion("*", username));
                request.getRequestDispatcher("quiz/manageQuiz.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("msg", "Error: " + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
