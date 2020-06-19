<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<a href="/login">login </a>
<a href="/logout">logout </a>
<a href="/register">Create User</a>
<br>
<c:choose>
    <c:when test="${pageContext.request.remoteUser != null}">
        <a href="newforum">Create new forum</a>
    </c:when>
    <c:otherwise></c:otherwise>
</c:choose>

<form method="get" action="forums">
    <input name="search" type="search" placeholder="Search" aria-label="Search">
    <input type="submit" value="Search">
</form>
<h1>Forums:</h1>
<c:forEach items="${forums}" var="forum">
    <a href="forum?id=${forum.forumId}"><h2>${forum.name}</h2></a>
        <p>${forum.description}</p>
        <P>Created by: ${forum.getUsername()}</P>
</c:forEach>