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
import java.util.ArrayList;
import java.util.Collections;
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
@WebServlet(name = "QuizSetup", urlPatterns = {"/quizSetup"})
public class QuizSetup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // check login status
        // if haven't not loggined: redirect to login page
        SessionUnilt su = new SessionUnilt();
        String username = su.getValue(request, "username");
        if (username.equalsIgnoreCase("null")) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            request.setAttribute("usertype", su.getValue(request, "usertype"));
            request.setAttribute("username", username);
            request.getRequestDispatcher("/quiz/quizSetup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUnilt su = new SessionUnilt();
        String username = su.getValue(request, "username");
        int quizNumber = 0;
        // validation integer number that user enter
        try {
            String strQuizNumber = request.getParameter("setNumberQuiz");
            quizNumber = Integer.parseInt(strQuizNumber);
            List<QuestionModel> allQuestion = new QuestionDAO().getQuestion("*", username);
            if (quizNumber > allQuestion.size()) {
                request.setAttribute("msg", "Number of quiz amount must not over " + allQuestion.size());
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            } else {
                Collections.shuffle(allQuestion);
                String questionIdList = "";
                for (int index = 1; index < quizNumber; index++) {
                    questionIdList += (allQuestion.get(index).getQuestionid() + " ");
                }

                su.pushValue(request, "currentQuestionId", String.valueOf(allQuestion.get(0).getQuestionid()));
                su.pushValue(request, "questionIdList", questionIdList.trim());
                su.pushValue(request, "score", "0");
                su.pushValue(request, "timeLength", String.valueOf(quizNumber * 60));
                su.pushValue(request, "quizNumber", String.valueOf(quizNumber));
                su.pushValue(request, "startTime", String.valueOf(System.currentTimeMillis()));
                
                response.sendRedirect(request.getContextPath() + "/takeQuiz");
            }
        } catch (Exception e) {
            request.setAttribute("msg", "Please enter right POSITIVE integer number format. \n" + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
