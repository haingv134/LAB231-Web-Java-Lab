/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Untils.SessionUnilt;
import dao.DepartmentDAO;
import dao.RequestDAO;
import dao.StudentDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RequestModel;

/**
 *
 * @author haiva
 */
@WebServlet(name = "SolveRequest", urlPatterns = {"/solveRequest"})
public class SolveRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUnilt su = new SessionUnilt();
        String username = su.getValue(request, "username");
        String strRid = request.getParameter("rid");
        if (!username.equalsIgnoreCase("null")) {
            if (strRid == null) {
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                int rid = Integer.parseInt(strRid);
                RequestModel requestModel = new RequestDAO().getRequest(rid);
                request.setAttribute("requestModel", requestModel);
                request.setAttribute("departmentModel", new DepartmentDAO().getDepartment(requestModel.getDid()));
                request.setAttribute("studentModel", new StudentDAO().getStudent(requestModel.getSid()));
                request.setAttribute("username", username);
                request.setAttribute("currentDay", java.time.LocalDate.now().toString());
                SimpleDateFormat sfdm = new SimpleDateFormat("EEEEE dd/MM/yyyy");
                request.setAttribute("welcomeMsg", sfdm.format(new java.util.Date()));

                request.getRequestDispatcher("solveRequest.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        try {
            int rid = Integer.parseInt(request.getParameter("rid"));
            String solution = request.getParameter("solution");
            String strState = request.getParameterValues("rState")[0];
            boolean state = false;
            if (strState.equalsIgnoreCase("approved")) {
                state = true;
            }
            RequestDAO rDao = new RequestDAO();
            RequestModel rModel = rDao.getRequest(rid);
            rModel.setState(state);
            rModel.setSolution(solution.trim());
            rModel.setIsSolved(true);
            rModel.setUsername(new SessionUnilt().getValue(request, "username"));
            rDao.updaterequest(rModel);

            response.sendRedirect(request.getContextPath() + "/home");
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
