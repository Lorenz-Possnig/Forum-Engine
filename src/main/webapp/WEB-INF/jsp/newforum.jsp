<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:page-container title="Employees" activePage="listEmployees">
    <h1 style="padding-left: 0px">Create a new forum</h1>
    <c:choose>
        <c:when test="${forum != null}">
            <form method="post" action="createforum?forumId=${forum.forumId}">
            <br />
        </c:when>
        <c:otherwise>
            <form method="post" action="createforum">
            <br />
        </c:otherwise>
    </c:choose>
        <label for="name"><p style="padding-left: 0px">Name:</p></label><br>
        <input type="text" id = "name" name = "name" value="${forum.name}"><br>
        <label for="description"><p style="padding-left: 0px; padding-top: 15px;">Description:</p></label><br>
        <textarea rows="15" cols="100" placeholder="Put the description of your forum here" id="description" name="description">${forum.description}</textarea>
    <br>
    <br>
        <input type="submit" value="Create Forum" style="font-family: Bahnschrift">
    </form>
</layout:page-container>