<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="nav-bar">
    <ul>
        <c:if test="${empty name}">
            <li> <a href="login"> Login </a></li>
            </c:if>
            <c:if test="${not empty name}">
            <li> <a href="logout"> Welcome ${name}, logout </a></li>
            </c:if>
        <li> <a href="home"> Home </a></li>
        <li> <a href="viewRequest"> View Request</a> </li>
        <li> <a href="solveRequest"> Solve Request </a></li>
        <li> <span style="color:blue; font-weight: bold;"> Today is: ${welcomeMsg} </span> </li>
    </ul>
</div>
