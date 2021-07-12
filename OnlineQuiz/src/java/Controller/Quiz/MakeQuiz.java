/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Quiz;

import DAO.QuestionDAO;
import Model.QuestionModel;
import Untils.SessionUnilt;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haiva
 */
@WebServlet(name = "MakeQuiz", urlPatterns = {"/makeQuiz"})
public class MakeQuiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUnilt su = new SessionUnilt();
        String username = su.getValue(request, "username");
        if (username.equalsIgnoreCase("null")) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            request.setAttribute("usertype", su.getValue(request, "usertype"));
            request.setAttribute("username", username);
            request.getRequestDispatcher("quiz/makeQuiz.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String[] strIndexAnswer = request.getParameterValues("indexAnswer");
            SessionUnilt su = new SessionUnilt();
            QuestionModel qModel = new QuestionModel(0, request.getParameter("question").trim(), request.getParameter("answer-1").trim(), request.getParameter("answer-2").trim(), request.getParameter("answer-3").trim(), request.getParameter("answer-4").trim(), su.getValue(request, "username"), "");
            int[] indexAnswer = new int[strIndexAnswer.length];
            for (int index = 0; index < indexAnswer.length; index++) {
                indexAnswer[index] = Integer.parseInt(strIndexAnswer[index].trim());
            }
            new QuestionDAO().addQuestion(qModel, indexAnswer);
            response.sendRedirect(request.getContextPath()+"/manageQuiz");
        } catch (Exception e) {
            request.setAttribute("msg", "Error: " + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
