<%-- 
    Document   : left
    Created on : 22-May-2021, 20:01:02
    Author     : haiva
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.ArticleModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>left</title>
    </head>
    <body>
        <div class="left" id="leftPanel">
            <h3 class="title"> ${article.title} </h3>
            <img src="materials/images/${article.image}" class="image"> </p>
            <p class="content"> ${article.content}</p>
            <div class="description">  
                <img src="materials/images/comment.gif" alt="">
                <img src="materials/images/timeicon.gif" alt="">
                ${article.author} | ${dateCreated}  
            </div>
        </div>
    </body>
</html>
