<%-- 
    Document   : solveRequest
    Created on : 19-Jun-2021, 15:56:24
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
        <link rel="stylesheet" href="resources/css/solveRequest.css" type="text/css">
        <title> Solve Request </title>
    </head>
    <body>
        <div id="main-area">
            <div id="header-area"> 
                <h1 id="title"> FPT Education System</h1>
                <h3> Student Control Panel </h3>
            </div>
            <%@include file="nav-bar.jsp" %>
            <div id="main-content">
                <h1 id="title">Request ID: <span style="font-style: italic"> ${requestModel.rid} </span> </h1>
                <div id="form-area">
                    <form action="solveRequest" method="POST">
                        <table>
                            <tr>
                                <td> Request to department </td>
                                <td> <input type="text" value="${departmentModel.name}" disabled> </td>
                            </tr><tr>
                                <td> Student ID </td>
                                <td> <input type="text" value="${studentModel.studentId}" disabled> </td>
                            </tr><tr>
                                <td> Student name </td>
                                <td> <input type="text" value="${studentModel.name}" disabled> </td>
                            </tr><tr>
                                <td> Dare created </td>
                                <td> <input type="date" value="${requestModel.dateCreated}" disabled> </td>
                            </tr><tr>
                                <td> Request title </td>
                                <td> <input type="text" value="${requestModel.title}" disabled> </td>
                            </tr><tr>
                                <td> Request content </td>
                                <td> <textarea type="text" rows="5" cols="30" disabled> ${requestModel.content} </textarea> </td>
                            </tr><tr>
                                <td> Request state </td>
                                <td> 
                                    <input type="radio" name="rState" value="approved" ${requestModel.state ? 'checked' : ''} ${requestModel.isSolved ? "disabled" : ""}> <span style="color: blue"> Approved </span>
                                    <input type="radio" name="rState" value="rejected" ${requestModel.state ? '' : 'checked'} ${requestModel.isSolved ? "disabled" : ""}> <span style="color: red"> Rejected </span>
                                </td>
                            </tr><tr>
                                <td> Clost date </td>
                                <td> <input type="date" name="closeDate"value="${!requestModel.isSolved ? currentDay : requestModel.closeDate}"  disabled> </td>
                            </tr><tr>
                                <td> Solved by </td>
                                <td> 
                                    <c:if test="${not empty requestModel.username}">
                                        <input type="text" value="${requestModel.username}" name="solveBy" disabled> 
                                    </c:if>
                                    <c:if test="${empty requestModel.username}">
                                        <input type="text" value="${username}" name="solveBy" disabled> 
                                    </c:if>
                                </td>
                            </tr><tr>
                                <td> Attached (if any)</td>
                                <td>
                                    <button name="download" value="Download">  
                                        <c:if test="${empty requestModel.attachedFile}">
                                            <a href="javascript:void(0)" >Download</a> 
                                        </c:if>
                                        <c:if test="${ not empty requestModel.attachedFile}">
                                            <a href="<c:url value="/resources/dbFile/${requestModel.attachedFile}"></c:url>" download>Download</a> 
                                        </c:if>
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td> School's solution </td>
                                <td> <textarea type="text" rows="7" cols="30" name="solution" ${requestModel.isSolved ? "disabled" : ""} > ${requestModel.solution} </textarea> </td>
                            </tr>
                        </table>
                        <input hidden type="text" value="${requestModel.rid}" name="rid">
                        <input type="submit" value="Save" ${requestModel.isSolved  ? 'hidden' : ''}>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
