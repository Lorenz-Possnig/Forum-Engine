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
        <div class="card">
            <div class="card-header" style="background-color: #4e73ff">
                <div class="col-sm">
                    <div class="container">
                        <img src="${image.toStreamingURI()}" class="img-responsive" width="200" height="200" alt="The user has not uploaded a profile picture yet">
                    </div>
                    <h1 style="padding-left: 0px; color: black;">${user.username}</h1>
                </div>
                <c:if test="${pageContext.request.remoteUser == user.username}">
                    <div class="col-sm">
                        <form method="post" action="/fileupload" enctype="multipart/form-data">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <button class="btn btn-outline-secondary" type="submit">Upload</button>
                                </div>
                                <input type="file" class="form-control" id="image"
                                       aria-describedby="basic-addon1" name="image" accept="image/jpeg, image/png, image/gif">
                                <div class="input-group-append">
                                    <label class="custom-file-label" for="image">Choose File</label>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:if>
            </div>
            <h2>Forums Created: ${user.forums.size()}</h2>
            <h2>Questions Asked: ${user.questions.size()}</h2>
            <h2>Questions Answered: ${user.answers.size()}</h2>
            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <div class="card-footer text-muted">
                    <c:choose>
                        <c:when test="${user.getIsBanned() == false}">
                            <a href="/ban?userId=${user.userId}">Ban User</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/unban?userId=${user.userId}">Unban User</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </sec:authorize>
        </div>
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