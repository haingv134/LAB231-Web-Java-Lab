/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Quiz;

import DAO.AnswerSheetDAO;
import DAO.QuestionDAO;
import Model.AnswerSheetModel;
import Model.QuestionModel;
import Untils.SessionUnilt;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haiva
 */
@WebServlet(name = "EditQuiz", urlPatterns = {"/editQuiz"})
public class EditQuiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SessionUnilt su = new SessionUnilt();
        String username = su.getValue(request, "username");
        if (username.equalsIgnoreCase("null")) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            try {
                int questionid = Integer.parseInt(request.getParameter("questionid"));
                QuestionModel qModel = new QuestionDAO().getQuestion(questionid, new SessionUnilt().getValue(request, "username"));
                // get answer index for this question
                List<AnswerSheetModel> list = new AnswerSheetDAO().getAnswerSheet(questionid);
                //
                request.setAttribute("usertype", su.getValue(request, "usertype"));
                request.setAttribute("username", username);
                request.setAttribute("currentQuestion", qModel);
                request.setAttribute("currentAnswerIndex", list);
                request.getRequestDispatcher("quiz/editQuiz.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("msg", "Error: " + e.getMessage());
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int questionid = Integer.parseInt(request.getParameter("questionid"));
            String[] strIndexAnswer = request.getParameterValues("indexAnswer");
            SessionUnilt su = new SessionUnilt();
            QuestionModel qModel = new QuestionModel(questionid, request.getParameter("question").trim(), request.getParameter("answer-1").trim(), request.getParameter("answer-2").trim(), request.getParameter("answer-3").trim(), request.getParameter("answer-4").trim(), su.getValue(request, "username"), "");
            if (strIndexAnswer != null) {
                int[] indexAnswer = new int[strIndexAnswer.length];
                for (int index = 0; index < indexAnswer.length; index++) {
                    indexAnswer[index] = Integer.parseInt(strIndexAnswer[index].trim());
                }
                new QuestionDAO().updateQuestion(qModel, indexAnswer);
                
                response.sendRedirect(request.getContextPath() + "/manageQuiz");
            } else {
                throw new Exception("Please select options");
            }

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
