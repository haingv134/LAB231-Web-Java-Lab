<%-- 
    Document   : search
    Created on : 26-May-2021, 21:24:56
    Author     : haiva
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.ArticleModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <%
            String searchKeyword = String.valueOf(request.getAttribute("searchKeyword"));
            ArrayList<ArticleModel> searchResult = (ArrayList<ArticleModel>) request.getAttribute("searchResult");
            String highlight = "<span class='highlight'>" + searchKeyword + "</span>";
            int index = 0;

        %>
        <div class="left" id="leftPanel">
            <div> Result for keyword: ${searchKeyword}</div>
            <c:forEach var="item" items="${searchResult}">
                <div class="resultList">
                    <%
                        String title = searchResult.get(index).getTitle().replaceAll(searchKeyword, highlight);
                        index++;
                    %>
                    <h4 class="titleResult" > <a href="home?id=${item.id}"> <%= title %> </a> </h4>
                    <img src="materials/images/${item.image}" class="image"> </p>
                    <p class="content"> ${item.content}</p>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
