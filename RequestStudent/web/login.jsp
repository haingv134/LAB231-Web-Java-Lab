<%-- 
    Document   : login
    Created on : 19-Jun-2021, 15:55:11
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
                <h1 id="title"> Member Login </h1>
                <div> Using your username and password .</div>
                <div id="form-area">
                    <form action="login" method="POST">
                        <table>
                            <tr>
                                <td> User Name </td>
                                <td> <input type="text" name="username" value="" required> <span style="color: red"> (*)</span> </td>
                            </tr>
                            <tr>
                                <td> Password </td>
                                <td> <input type="password" name="password" value="" required> <span style="color: red"> (*)</span> </td>
                            </tr>
                        </table>
                        <div>
                            <br>
                            <input type="submit" name="signin" value="Sign in"> 
                            <br>
                            The field <span style="color: red"> (*)</span> is required.
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
