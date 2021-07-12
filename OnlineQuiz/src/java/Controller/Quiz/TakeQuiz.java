/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Quiz;

import DAO.AnswerSheetDAO;
import DAO.QuestionDAO;
import Model.AnswerSheetModel;
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
@WebServlet(name = "TakeQuiz", urlPatterns = {"/takeQuiz"})
public class TakeQuiz extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SessionUnilt su = new SessionUnilt();

            String username = su.getValue(request, "username");
            String currentQuestionId = su.getValue(request, "currentQuestionId");
            
            System.out.println("CURRENT QUESTION (GET): " + currentQuestionId);
            if (username.equalsIgnoreCase("null")) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
                request.setAttribute("usertype", su.getValue(request, "usertype"));
                request.setAttribute("username", username);

                if (!su.getValue(request, "quizNumber").equalsIgnoreCase("null")) {
                    int questionId = Integer.parseInt(currentQuestionId);
                    request.setAttribute("question", new QuestionDAO().getQuestion(questionId, username));
                    request.setAttribute("timeLength", Long.parseLong(su.getValue(request, "timeLength")) - ((System.currentTimeMillis() - Long.parseLong(su.getValue(request, "startTime"))) / 1000));
                    request.getRequestDispatcher("/quiz/takeQuiz.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/quizSetup");
                }

            }
        } catch (Exception e) {
            request.setAttribute("msg", "Information: " + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUnilt su = new SessionUnilt();
        // UPDATE
        try {

            int questionid = Integer.parseInt(su.getValue(request, "currentQuestionId"));
            int questionidPage = Integer.parseInt(request.getParameter("questionid"));

            System.out.println("DO POST PART WITH ID: " + questionid);
            System.out.println("Current QUESTION ID present in PAGE: " + questionidPage);

            // if user not refresh page
            if (questionid == questionidPage) {
                // get answersheet index from takeQuiz page corresponding with this id
                String[] answerIndexPage = request.getParameterValues("answer");
                // get answersheet index from database corresponding with id
                List<AnswerSheetModel> answersheetList = new AnswerSheetDAO().getAnswerSheet(questionid);

                if (answerIndexPage != null) {
                    int[] answerIndexDB = new int[answersheetList.size()];
                    for (int i = 0; i < answersheetList.size(); i++) {
                        answerIndexDB[i] = answersheetList.get(i).getAnswerindex();
                    }
                    //
                    int correctAnswer = 0;
                    int wrongAnswer = 0;
                    //
                    for (String answerPage : answerIndexPage) {
                        int match = 0;
                        for (int answerDB : answerIndexDB) {
                            if (answerDB == Integer.parseInt(answerPage)) {
                                match = 1;
                                break;
                            }
                        }
                        if (match == 1) {
                            ++correctAnswer;
                        } else {
                            ++wrongAnswer;
                        }
                    }
                    double score = (((correctAnswer > wrongAnswer) ? (correctAnswer - wrongAnswer) : 0.0) * (1.0 / answerIndexDB.length));
                    su.pushValue(request, "score", String.valueOf(Double.parseDouble(su.getValue(request, "score")) + score));
                }

                String questionIdList = su.getValue(request, "questionIdList");
                
                // get questionid from session
                // remove question has been used
                // if questionid in session is empty -> caculate score
                int nextQuestionId;
                if (!questionIdList.isEmpty()) {
                    if (questionIdList.indexOf(" ") >= 0) {
                        String currentQuestionId = questionIdList.split(" ")[0].trim();
                        su.pushValue(request, "questionIdList", questionIdList.substring(questionIdList.indexOf(" ")).trim());
                        nextQuestionId = Integer.parseInt(currentQuestionId);
                    } else {
                        su.pushValue(request, "questionIdList", "");
                        nextQuestionId = Integer.parseInt(questionIdList);
                    }
                    su.pushValue(request, "currentQuestionId", String.valueOf(nextQuestionId));

                    request.setAttribute("timeLength", Long.parseLong(su.getValue(request, "timeLength")) - ((System.currentTimeMillis() - Long.parseLong(su.getValue(request, "startTime"))) / 1000));
                    request.setAttribute("question", new QuestionDAO().getQuestion(nextQuestionId, su.getValue(request, "username")));
                    request.getRequestDispatcher("/quiz/takeQuiz.jsp").forward(request, response);
                } else {
                    // redirect to result page
                    double score = ((Double.parseDouble(su.getValue(request, "score"))) / (Integer.parseInt(su.getValue(request, "quizNumber"))) * 10);
                    request.setAttribute("usertype", su.getValue(request, "usertype"));
                    request.setAttribute("username", su.getValue(request, "username"));
                    String status = "Failed";
                    if (score >= Double.parseDouble("5")) {
                        status = "Passed";
                    }
                    request.setAttribute("score", String.format("%.1f", score));
                    request.setAttribute("status", status);
                    // remove all unneccessry information in session
                    su.remove(request, "currentQuestionId");
                    su.remove(request, "questionIdList");
                    su.remove(request, "score");
                    su.remove(request, "timeLength");
                    su.remove(request, "quizNumber");
                    su.remove(request, "startTime");
                    //
                    request.getRequestDispatcher("quiz/quizResult.jsp").forward(request, response);
                }
            } else {
                // if user refresh page
                request.setAttribute("timeLength", Long.parseLong(su.getValue(request, "timeLength")) - ((System.currentTimeMillis() - Long.parseLong(su.getValue(request, "startTime"))) / 1000));
                request.setAttribute("question", new QuestionDAO().getQuestion(questionid, su.getValue(request, "username")));
                request.getRequestDispatcher("/quiz/takeQuiz.jsp").forward(request, response);
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
