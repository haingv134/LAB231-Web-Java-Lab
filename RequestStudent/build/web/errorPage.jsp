<%-- 
    Document   : errorPage
    Created on : 19-Jun-2021, 16:01:53
    Author     : haiva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="login.jpg" type="image/icon">
        <link rel="stylesheet" href="resources/css/layout.css" type="text/css">
        <link rel="stylesheet" href="resources/css/login.css" type="text/css">
        <title>Error</title>
    </head>
    <body>
        <div id="main-area">
            <div id="header-area"> 
                <h1 id="title"> FPT Education System</h1>
                <h3> Teacher Control Panel </h3>
            </div>
            <%@include file="nav-bar.jsp" %>
            <div id="main-content">
                <h1 id="title"> ${msg} </h1>           
            </div>
        </div>
    </body>
</html>
