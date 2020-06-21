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

<layout:page-container title="New Question" activePage="newquestion">
    <h1>Ask a new question</h1>
    <c:choose>
        <c:when test="${question != null}">
            <form method="post" action="createquestion?forumId=${forum.forumId}&questId=${question.questId}">
        </c:when>
        <c:otherwise>
            <form method="post" action="createquestion?forumId=${forum.forumId}">
        </c:otherwise>
    </c:choose>
        <label for="title"><p style="padding-left: 0px">Title:</p></label><br>
        <input type="text" id = "title" name = "title" value="${question.title}"><br>
        <label for="text"><p style="padding-left: 0px">Question text:</p></label><br>
        <textarea rows="15" cols="100" placeholder="Put your question here" id="text" name="text">${question.text}</textarea>
        <input type="hidden" id="isClosed" name="isClosed" value="false">
    <br><br>
        <input type="submit" value="Ask Question">
    </form>
</layout:page-container>