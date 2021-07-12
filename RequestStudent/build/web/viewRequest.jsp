<%-- 
    Document   : viewRequest
    Created on : 19-Jun-2021, 15:55:56
    Author     : haiva
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="login.jpg" type="image/icon">
        <link rel="stylesheet" href="resources/css/layout.css" type="text/css">
        <link rel="stylesheet" href="resources/css/viewRequest.css" type="text/css">
        <title>View Request</title>
    </head>
    <body>
        <div id="main-area">
            <div id="header-area"> 
                <h1 id="title"> FPT Education System</h1>
                <h3> Teacher Control Panel </h3>
            </div>
            <%@include file="nav-bar.jsp" %>
            <div id="main-content">
                <h2 id="title"> View requests </h2>
                <table>
                    <tr>
                        <th colspan="6">
                            <form method="GET" action="viewRequest">
                                
                                <input type="text" hidden value="${type}" name="type">
                                
                                Select department 
                                <select name="did">
                                    <c:if test="${empty type}">
                                        <option value="" selected> All </option>
                                    </c:if>
                                    <c:forEach var="item" items="${departmentList}">
                                        <option value="${item.did}" ${item.name == selectedDepartName ? "selected" : ""}>  ${item.name}  </option>
                                    </c:forEach>
                                </select>
                                <br> <br>
                                or Enter request title <input type="text" value="${searchKeyword}" name="searchKeyword"> 
                                <input type="submit" value="View">
                                <br> <br> 
                            </form>
                        </th>           
                    </tr>
                    <tr>
                        <th> Request title </th>
                        <th> Date created </th>
                        <th> Closed created </th>
                        <th> Status </th>
                        <th> Report to </th>
                        <th> Detail </th>
                    </tr>
                    <c:forEach var="item" items="${requestList}">
                        <tr>
                            <td> ${item.title} </td>
                            <td> ${item.dateCreated} </td>
                            <td> ${item.isSolved == false ? 'N/A' : item.closeDate} </td>
                            <td> ${item.isSolved == false ? 'in progress' : item.strState} </td>
                            <td> ${item.departmentName} </td>
                            <td> 
                                <c:choose>
                                    <c:when test="${item.isSolved == false}">
                                        <a href="solveRequest?rid=${item.rid}"> Solve </a> 
                                    </c:when>
                                    <c:otherwise>
                                        <a href="solveRequest?rid=${item.rid}"> View </a> 
                                    </c:otherwise>
                                </c:choose>  
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
