<%-- 
    Document   : editQuiz
    Created on : 12-Jun-2021, 14:06:47
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
        <title>Edit Quiz</title>
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
                <form action="editQuiz" method="POST">         
                    <table>
                        <input type="text" hidden="" name="questionid" value="${currentQuestion.questionid}">  </p>
                        <tr>
                            <td> Question: </td>
                            <td> <textarea name="question" rows="7"> ${currentQuestion.question} </textarea> </td>
                        </tr>
                        <tr>
                            <td> Option1: </td>
                            <td> <textarea name="answer-1" rows="3"> ${currentQuestion.answer1} </textarea> </td>
                        </tr>
                        <tr>
                            <td> Option2: </td>
                            <td> <textarea name="answer-2" rows="3"> ${currentQuestion.answer2} </textarea> </td>
                        </tr>
                        <tr>
                            <td> Option3: </td>
                            <td> <textarea name="answer-3" rows="3"> ${currentQuestion.answer3} </textarea> </td>
                        </tr>
                        <tr>
                            <td> Option4: </td>
                            <td> <textarea name="answer-4" rows="3"> ${currentQuestion.answer4} </textarea> </td>
                        </tr>
                        <tr>
                            <td> Answer(s): </td>
                            <td> 
                                <c:set var="answerIndex1" scope="session" value="" > </c:set>
                                <c:set var="answerIndex2" scope="session" value="" > </c:set>
                                <c:set var="answerIndex3" scope="session" value="" > </c:set>
                                <c:set var="answerIndex4" scope="session" value="" > </c:set>

                                <c:forEach var="item" items="${currentAnswerIndex}">
                                    <c:choose>
                                        <c:when test="${item.answerindex == 1}">
                                            <c:set var="answerIndex1" scope="session" value="YES" > </c:set>
                                        </c:when>
                                        <c:when test="${item.answerindex == 2}">
                                            <c:set var="answerIndex2" scope="session" value="YES" > </c:set>
                                        </c:when>
                                        <c:when test="${item.answerindex == 3}">
                                            <c:set var="answerIndex3" scope="session" value="YES" > </c:set>
                                        </c:when>
                                        <c:when test="${item.answerindex == 4}">
                                            <c:set var="answerIndex4" scope="session" value="YES" > </c:set>
                                        </c:when>
                                    </c:choose>                         
                                </c:forEach>     
                                <input type="checkbox" name="indexAnswer" value="1"  ${answerIndex1 == 'YES' ? "checked" : ""} > Option 1
                                <input type="checkbox" name="indexAnswer" value="2"  ${answerIndex2 == 'YES' ? "checked" : ""} > Option 2
                                <input type="checkbox" name="indexAnswer" value="3"  ${answerIndex3 == 'YES' ? "checked" : ""} > Option 3
                                <input type="checkbox" name="indexAnswer" value="4"  ${answerIndex4 == 'YES' ? "checked" : ""} > Option 4
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td> 
                                <input type="submit" name="update" value="Update"> 
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
