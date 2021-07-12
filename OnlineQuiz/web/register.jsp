<%-- 
    Document   : register
    Created on : 10-Jun-2021, 13:04:48
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
        <title>Registration</title>
    </head>
    <body>
        <div id="main-area">
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
                <div id="user-title"> Registration Form </div>
                <div id="register-area">
                    <form action="register" method="POST">
                        <table>
                            <tr>
                                <td> User Name: </td>
                                <td> <input type="text" name="username" value="" required> </td>
                            </tr>
                            <tr>
                                <td> Password: </td>
                                <td> <input type="password" name="password" value="" required> </td>
                            </tr>  
                            <tr>
                                <td> User Type: </td>
                                <td> 
                                    <select id="usertype" name="usertype">
                                        <option value="Teacher"> Teacher </option>
                                        <option value="Student"> Student </option>
                                    </select> 
                                </td>
                            </tr>
                            <tr>
                                <td> Email: </td>
                                <td> <input type="text" name="email" value="" required> </td>
                            </tr>
                            <tr>
                                <td> </td>
                                <td> <input type="submit" name="btnRegister" value="Register"> </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
