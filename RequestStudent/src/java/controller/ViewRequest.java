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
import java.util.ArrayList;
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
@WebServlet(name = "ViewRequest", urlPatterns = {"/viewRequest"})
public class ViewRequest extends HttpServlet {

    private List<RequestModel> getRequestBy(HttpServletRequest request) {
        return new RequestDAO().getAllRequest();
    }

    private List<RequestModel> getRequestBy(HttpServletRequest request, String type, String strDid) {
        int did = Integer.parseInt(strDid);
        List<RequestModel> requestListByDid = new RequestDAO().getRequestbyDID(did);
        // this will forward to jsp 
        List<RequestModel> requestList = new ArrayList<>();
        Date currentDate = Date.valueOf(LocalDate.now());
        Date last2days = Date.valueOf(LocalDate.now().minusDays(2));
        // get correspond request list base on did and type (today/last2days)
        if (type.equalsIgnoreCase("today")) {
            for (int index = 0; index < requestListByDid.size(); index++) {
                Date date = requestListByDid.get(index).getDateCreated();
                if (date.equals(currentDate)) {
                    requestList.add(requestListByDid.get(index));
                }
            }
        } else if (type.equalsIgnoreCase("last2days")) {
            for (int index = 0; index < requestListByDid.size(); index++) {
                Date date = requestListByDid.get(index).getDateCreated();
                if (date.compareTo(last2days) >= 0 && date.compareTo(currentDate) != 0) {
                    requestList.add(requestListByDid.get(index));
                }
            }
        }
        return requestList;
    }

    private List<RequestModel> getRequestBy(HttpServletRequest request, String type, String strDid, String searchKeyword) {
        List<RequestModel> requestList = new ArrayList<>();
        //  show all, no filter
        if (searchKeyword.isEmpty() && strDid.isEmpty() && type.isEmpty()) {
            requestList = getRequestBy(request);
            // show all filter by did
        } else if (searchKeyword.isEmpty() && !strDid.isEmpty() && type.isEmpty()) {
            requestList = new RequestDAO().getRequestbyDID(Integer.parseInt(strDid));
            request.setAttribute("type", "");
            // show all filter by did & type
        } else if (searchKeyword.isEmpty() && !strDid.isEmpty() && !type.isEmpty()) {
            requestList = getRequestBy(request, type, strDid);
            request.setAttribute("type", type);
            // show all filter by did & type & search
        } else if (!searchKeyword.isEmpty() && !strDid.isEmpty() && !type.isEmpty()) {
            List<RequestModel> requestListByTypeAndDID = getRequestBy(request, type, strDid);
            for (int index = 0; index < requestListByTypeAndDID.size(); index++) {
                if (requestListByTypeAndDID.get(index).getTitle().contains(searchKeyword)) {
                    requestList.add(requestListByTypeAndDID.get(index));
                }
            }
            // show all filter by did & search
        } else if (!searchKeyword.isEmpty() && !strDid.isEmpty() && type.isEmpty()) {
            int did = Integer.parseInt(strDid);
            List<RequestModel> requestListByDID = new RequestDAO().getRequestbyDID(did);
            for (int index = 0; index < requestListByDID.size(); index++) {
                if (requestListByDID.get(index).getTitle().contains(searchKeyword)) {
                    requestList.add(requestListByDID.get(index));
                }
            }
        } else if (!searchKeyword.isEmpty() && strDid.isEmpty() && type.isEmpty()) {
            List<RequestModel> requestListByDID = new RequestDAO().getAllRequest();
            for (int index = 0; index < requestListByDID.size(); index++) {
                if (requestListByDID.get(index).getTitle().contains(searchKeyword)) {
                    requestList.add(requestListByDID.get(index));
                }
            }
        }
        return requestList;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SessionUnilt su = new SessionUnilt();
            String username = su.getValue(request, "username");
            String type = request.getParameter("type");
            String strDid = request.getParameter("did");
            String searchKeyword = request.getParameter("searchKeyword");
            // requestList: forward to jsp
            List<RequestModel> requestList = new ArrayList<>();
            // not login
            if (username.equalsIgnoreCase("null")) {
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                String selectedDepartName = "";
                //case 1: no parameter 
                if (type == null && strDid == null) {
                    requestList = getRequestBy(request);
                    request.setAttribute("type", "");
                }
                //case 2: type and strDid 
                if (type != null && strDid != null) {
                    if (!type.isEmpty() && !strDid.isEmpty()) {
                        requestList = getRequestBy(request, type, strDid);
                        request.setAttribute("type", type);
                    }
                }

                //case 3: searchkeyword 
                if (searchKeyword != null) {
                    requestList = getRequestBy(request, type, strDid, searchKeyword);
                    request.setAttribute("searchKeyword", searchKeyword);
                }
                DepartmentDAO dDao = new DepartmentDAO();
                List<DepartmentModel> departmentList = dDao.getDepartment();
                if (strDid != null) {
                    // get selected department name
                    if (!strDid.isEmpty()) {
                        int did = Integer.parseInt(strDid);
                        selectedDepartName = dDao.getDepartment(did).getName();
                    } else {
                        selectedDepartName = "";
                    }
                }
                for (int index = 0; index < requestList.size(); index++) {
                    requestList.get(index).setDepartmentName(dDao.getDepartment(requestList.get(index).getDid()).getName());
                    String strState = "Reject";
                    if (requestList.get(index).isState()) {
                        strState = "Approved";
                    }
                    requestList.get(index).setStrState(strState);
//requestList.get(index).setStrState();
                }
                //---

                request.setAttribute("departmentList", departmentList);
                request.setAttribute("selectedDepartName", selectedDepartName);
                request.setAttribute("requestList", requestList);
                SimpleDateFormat sfdm = new SimpleDateFormat("EEEEE dd/MM/yyyy");
                request.setAttribute("welcomeMsg", sfdm.format(new java.util.Date()));
                request.getRequestDispatcher("viewRequest.jsp").forward(request, response);
            }
        } catch (IOException | NumberFormatException | ServletException e) {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
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
