<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<h1>Forums:</h1>
<c:forEach items="${forums}" var="forum">
    <h2>Name: ${forum.name} Description: ${forum.description}</h2>
    <h3>Questions:</h3>
    <c:forEach items="${forum.questions}" var="question">
        <h4>&nbsp;&nbsp;&nbsp;Title: ${question.title}</h4>
        <p>&nbsp;&nbsp;&nbsp;Text: ${question.text}</p>
        <p>&nbsp;&nbsp;&nbsp;Hochwählis: ${question.hochwaehlis} Runterwaehlis: ${question.runterwaehlis}</p>
        <h3>&nbsp;&nbsp;&nbsp;Answers:</h3>
        <c:forEach items="${question.answers}" var="answer">
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Text: ${answer.text}</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hochwaehlis: ${asnwer.hochwaehlis} Runterwaehlis: ${answer.runterwaehlis}</p>
        </c:forEach>
    </c:forEach>
</c:forEach>