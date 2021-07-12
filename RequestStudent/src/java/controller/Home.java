/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Untils.SessionUnilt;
import dao.DepartmentDAO;
import dao.RequestDAO;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DepartmentModel;
import model.RequestModel;

/**
 *
 * @author haiva
 */
@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUnilt su = new SessionUnilt();
        if (!su.getValue(request, "username").equalsIgnoreCase("null")) {
            Date currentDate = Date.valueOf(LocalDate.now());
            Date last2daysDate = Date.valueOf(LocalDate.now().minusDays(2));
            request.setAttribute("currentDate", currentDate);
            List<DepartmentModel> departmentList = new DepartmentDAO().getDepartment();
            
            for (int index = 0; index < departmentList.size(); index++) {
                int today = 0, last2days = 0;
                List<RequestModel> listRequest = new RequestDAO().getRequestbyDID(departmentList.get(index).getDid());
                for (int subindex = 0; subindex < listRequest.size(); subindex++) {
                    Date date = listRequest.get(subindex).getDateCreated();
                    if (date.equals(currentDate)) {
                        today++;
                    } else if (date.compareTo(last2daysDate) >= 0 && date.compareTo(currentDate) != 0) {
                        last2days++;
                    }
                }
                departmentList.get(index).setRequestToday(today);
                departmentList.get(index).setRequestLast2days(last2days);
            }
            request.setAttribute("departmentList", departmentList);
            SimpleDateFormat sfdm = new SimpleDateFormat("EEEEE dd/MM/yyyy");
            request.setAttribute("welcomeMsg", sfdm.format(new java.util.Date()));
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
