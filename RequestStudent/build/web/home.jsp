<%-- 
    Document   : home
    Created on : 19-Jun-2021, 15:55:27
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
        <link rel="stylesheet" href="resources/css/home.css" type="text/css">
        <title>Home</title>
    </head>
    <body>
        <div id="main-area">
            <div id="header-area"> 
                <h1 id="title"> FPT Education System</h1>
                <h3> Teacher Control Panel </h3>
            </div>
            <%@include file="nav-bar.jsp" %>
            <div id="main-content">
                <form method="POST" action="home">
                    <h2 id="title"> Request received by today / ${currentDate} </h2>
                    <table>
                        <tr>
                            <th> Department name </th>
                            <th> Number of request </th>
                            <th> View detail </th>
                        </tr>
                        <c:forEach var="item" items="${departmentList}"> 
                            <tr>
                                <td> ${item.name} </td>
                                <td> ${item.requestToday} </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.requestToday > 0}">
                                            <a href="viewRequest?did=${item.did}&type=today">View</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascrip:void(0)">View</a>
                                        </c:otherwise>
                                    </c:choose>  
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <h2 id="title"> Request received by last 2 days </h2>
                    <table>
                        <tr>
                            <th> Department name </th>
                            <th> Number of request </th>
                            <th> View detail </th>
                        </tr>
                        <c:forEach var="item" items="${departmentList}"> 
                            <tr>
                                <td> ${item.name} </td>
                                <td> ${item.requestLast2days} </td>
                                <td> 
                                    <c:choose>
                                        <c:when test="${item.requestLast2days > 0}">
                                            <a href="viewRequest?did=${item.did}&type=last2days">View</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascrip:void(0)">View</a>
                                        </c:otherwise>
                                    </c:choose>              
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>   
        </div>
    </body>
</html>
