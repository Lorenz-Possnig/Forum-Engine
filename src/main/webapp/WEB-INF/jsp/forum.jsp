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

    <div class="container align-items-center">
        <div class="card my-card">
            <div class="card">
            <div class="card-header" style="background-color: #4e73ff">
                <h1 style="color: white; text-align: center">${forum.name}</h1>
            </div>
                <br>
                <h5>${forum.description}</h5>
            <div class="card-footer text-muted" style="height: 4rem">
                <div class="row">
                    <div class="col-sm">
                        Created by: ${forum.getUsername()}
                    </div>
                    <div class="col-sm">
                        Created on: ${forum.createdOn}
                    </div>
                    <c:if test="${forum.getUsername() == pageContext.request.remoteUser ||
                              pageContext.request.remoteUser == 'koarl'}">
                        <div class="col-sm">
                            <a href="newforum?forumId=${forum.forumId}" style="font-size:75%"><p style="color: #6c757d">Edit Forum</p></a>
                        </div>
                        <div class="col-sm">
                            <a href="deleteforum?forumId=${forum.forumId}" style="font-size:75%"><p style="color: #6c757d">Delete Forum</p></a>
                        </div>
                    </c:if>
                    <c:if test="${pageContext.request.remoteUser != null}">
                        <div class="col-sm">
                            <a href="newquestion?forumId=${forum.forumId}" style="font-size:75%"><p>Ask a question</p></a>
                        </div>
                    </c:if>
                </div>
            </div>
            </div>

            </div>
        <div class="row text-white">
            <div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
                <div class="px-2">
                    <form action="/forum" class="justify-content-center" method="get">
                        <input type="hidden" id="id" name="id" value="${forum.forumId}">
                        <div class="form-group">
                            <input type="text" id="search" name="search" class="form-control" placeholder="search">
                        </div>
                        <button type="submit" class="btn btn-primary btn-lg">Search this forum</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="card my-card">
        <c:forEach items="${questions}" var="question">
            <div class="card">
                <div class="card-header" style="height: 6rem; background-color: #4e73ff">
                    <a href="question?forumId=${forum.forumId}&questId=${question.questId}"><h2>${question.title}</h2></a>
                </div>
                <br>
                <h5>${question.text}</h5>
                <a href="/user?userId=${question.createdBy.userId}">
                    <h5 style="font-size:75%">Created by: ${question.getUsername()}</h5>
                </a>
                <div class="card-footer text-muted">
                    Created on: ${question.createdOn}
                </div>
            </div>
        </c:forEach>
    </div>
</layout:page-container>