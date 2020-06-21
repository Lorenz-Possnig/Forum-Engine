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
    <div class="card my-card">
        <h1 style="padding-left: 0px; color: black;">${user.username}</h1>
        <h3>Forums Created: ${user.forums.size()}</h3>
        <h3>Questions Asked: ${user.questions.size()}</h3>
        <h3>Questions Answered: ${user.answers.size()}</h3>
    </div>
    <div class="card my-card">
        <div class="card">
            <div class="card-header" style="height: 3rem; background-color: #4e73ff">
                <h5>Created Forums:</h5>
            </div>
            <c:forEach items="${forums}" var="forum">
                <a href="forum?id=${forum.forumId}"><p>${forum.name}</p></a>
            </c:forEach>
        </div>
        <hr style="background-color: darkslategray">
        <div class="card">
            <div class="card-header" style="height: 3rem; background-color: #4e73ff">
                <h5>Answered Questions:</h5>
            </div>
            <c:forEach items="${questions}" var="question">
                <a href="question?forumId=${question.forum.forumId}&questId=${question.questId}"><p>${question.title}</p></a>
                <hr style="color: white; background-color: white">
            </c:forEach>
        </div>
    </div>
</layout:page-container>
