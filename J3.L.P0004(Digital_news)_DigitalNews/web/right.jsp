<%-- 
    Document   : right
    Created on : 22-May-2021, 20:01:11
    Author     : haiva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>right</title>
    </head>
    <body>
        <div class="right" id="rightPanel">
                <div class="mostRecentNews">
                    <h3>Digital News</h3>
                    <p class="newRcontent">${shortContent}</p>
                </div>
                <div class="search">
                    <h3>Search</h3>
                    <form action="home" method="POST">
                        <input class="txtSearch" type="text" name="searchKeyword" value="${searchKeyword}" id="">
                        <input class="btnSearch" type="submit" value="Go">
                    </form>    
                </div>
                <div class="top5RecentNews">
                    <h3>Last Articles</h3>
                    <c:forEach var="item" items="${recentArticle}">
                        <a href="?id=${item.id}"> ${item.title}</a>
                    </c:forEach>
                </div>
            </div>
    </body>
</html>
