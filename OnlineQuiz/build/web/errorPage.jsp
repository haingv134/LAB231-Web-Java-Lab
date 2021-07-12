<%-- 
    Document   : errorPage
    Created on : 10-Jun-2021, 13:32:40
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
        <link rel="stylesheet" href="resources/css/style.css" type="text/css">
        <link rel="stylesheet" href="resources/css/nav-bar.css" type="text/css">
        <title>Error</title>
    </head>
    <body>
        <div id="main-area">   
            <!---->
            <div id="nav-bar">
                <ul>
                    <li><a href='login'>Home</a></li>
                    <li><a href='takeQuiz'>Take Quiz</a></li>
                    <c:if test="${usertype == 'Teacher' || empty usertype}"> 
                        <li><a href='makeQuiz'>Make Quiz</a></li>
                        <li><a href='manageQuiz'>Manage Quiz</a></li>
                    </c:if>
                    <c:if test="${not empty username}">
                        <li><a href='logout'>Logout</a></li>
                    </c:if>
                </ul>
            </div>
            <div id="main-content">
                <span style="font-weight: bold; font-size: larger; color: red"> ${msg} </span>
            </div>
            <!---->
        </div>
    </body>
</html>
