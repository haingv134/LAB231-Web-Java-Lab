<%-- 
    Document   : makeQuiz
    Created on : 12-Jun-2021, 13:38:22
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
        <title>Make Quiz</title>
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
                <form action="makeQuiz" method="POST">
                    <table>
                        <tr>
                            <td> Question: </td>
                            <td> <textarea name="question"> </textarea> </td>
                        </tr>
                        <tr>
                            <td> Option1: </td>
                            <td> <textarea name="answer-1" >  </textarea> </td>
                        </tr>
                        <tr>
                            <td> Option2: </td>
                            <td> <textarea name="answer-2" > </textarea> </td>
                        </tr>
                        <tr>
                            <td> Option3: </td>
                            <td> <textarea name="answer-3"> </textarea> </td>
                        </tr>
                        <tr>
                            <td> Option4: </td>
                            <td> <textarea name="answer-4"> </textarea> </td>
                        </tr>
                        <tr>
                            <td> Answer(s): </td>
                            <td> 
                                <input type="checkbox" name="indexAnswer" value="1"> Option 1
                                <input type="checkbox" name="indexAnswer" value="2"> Option 2
                                <input type="checkbox" name="indexAnswer" value="3"> Option 3
                                <input type="checkbox" name="indexAnswer" value="4"> Option 4
                            </td>
                        </tr>
                        <tr>
                            <td>  </td>
                            <td> <input type="submit" value="Save"> </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
