<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/layout.css" rel="stylesheet" type="text/css"> 
        <link href="css/head.css" rel="stylesheet" type="text/css">
        <link href="css/left.css" rel="stylesheet" type="text/css">
        <link href="css/right.css" rel="stylesheet" type="text/css">
        <link href="css/footer.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="css/search.css" type="text/css">

        <script>
            function resize() {
                var leftPanelHeightElement = document.getElementById("leftPanel").offsetHeight;
                var rightPanelHeightElement = document.getElementById("rightPanel").offsetHeight;
                if (window.innerWidth < 1250) {
                    //window.alert("<1250");
                    document.getElementById("containerID").style.height = leftPanelHeightElement + rightPanelHeightElement + 70 + "px";
                } else {
                    //window.alert(">1250");
                    document.getElementById("containerID").style.height = leftPanelHeightElement + 70 + "px";
                }
            }
        </script>

        <title>Digital News</title>
    </head>

    <body onresize="resize()" onload="resize()">
        <%@include file="head.jsp" %>
        <div class="container" id="containerID">    
            <c:choose>
                <c:when test="${not empty searchKeyword}">
                    <%@include file="search.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@include file="left.jsp" %>
                </c:otherwise>
            </c:choose>
            <%@include file="right.jsp" %>
        </div>  
    </body>
    <footer>
    </footer>
</html>