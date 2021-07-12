<%-- 
    Document   : takeQuiz
    Created on : 10-Jun-2021, 13:23:47
    Author     : haiva
--%>
<%@page import="java.util.Timer"%>
<%@page import="java.util.TimerTask"%>
<%@page import="Untils.SessionUnilt"%>
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
        <title>Manage Quiz</title>

    <p id="timeLength" hidden >${timeLength}</p>
    <script>
        // setting time countdown runner
        var currentTime = new Date().getTime();
        var totalSeconds = document.getElementById("timeLength").innerHTML;

        var timeRemain = setInterval(function () {
            var secondsFromStart = Math.floor((new Date().getTime() - currentTime) / 1000);
            var secondsLeft = totalSeconds - secondsFromStart;
            if (secondsLeft > 0) {
                let minutes = Math.floor(secondsLeft / 60);
                var seconds = secondsLeft % 60;
                // format  minutes and seconds
                minutes = ((minutes.toString().length == 1) ? ("0" + minutes) : minutes);
                seconds = ((seconds.toString().length == 1) ? ("0" + seconds) : seconds);
                document.getElementById("timer").innerHTML = minutes + ":" + seconds;
            } else {
                document.getElementById("status").value = "endQuiz";
                document.getElementById("myForm").submit();
            }
        }, 1000);
        // end of time countdown
    </script>

</head>
<body onkeydown="return (event.keyCode != 116)">
    <div id="main-area">
        <div id="nav-bar">
            <ul>
                <li><a href='javascript:void(0)'>Home</a></li>
                <li><a href='javascript:void(0)'>Take Quiz</a></li>
                    <c:if test="${usertype == 'Teacher' || empty usertype}"> 
                    <li><a href='javascript:void(0)'>Make Quiz</a></li>
                    <li><a href='javascript:void(0)'>Manage Quiz</a></li>
                    </c:if>
                    <c:if test="${not empty username}">
                    <li><a href='javascript:void(0)'>Logout</a></li>
                    </c:if>
            </ul>
        </div>
        <div id="main-content">

            <div id="standard-title"> Welcome <span style="font-weight: bold; color: blue"> ${username} </span></div>
            <div id="timer-area"> Time remainting <span id="timer" style="color: red">  </span> </div>

            <form action="takeQuiz" method="POST" id="myForm">
                <!--M-->
                <input type="text" value="testing" id="status" hidden>

                <input type="text" value="${question.questionid}" name="questionid" hidden>

                <!--M-->
                <div>
                    <div class="question-title"> ${question.getQuestion()}</div>  
                    <!--value in here is = answerindex --> 
                    <input type="checkbox" class="answer-checkbox" name="answer" value="1"> ${question.getAnswer1()} <br>
                    <!--answer 2 -->
                    <input type="checkbox" class="answer-checkbox" name="answer" value="2"> ${question.getAnswer2()} <br>
                    <!--answer 3 -->
                    <input type="checkbox" class="answer-checkbox" name="answer" value="3"> ${question.getAnswer3()} <br>
                    <!--answer 4 --> 
                    <input type="checkbox" class="answer-checkbox" name="answer" value="4"> ${question.getAnswer4()} <br>
                </div>

                <div id="next-button">
                    <input type="submit" value="Next" name="Next"/>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
