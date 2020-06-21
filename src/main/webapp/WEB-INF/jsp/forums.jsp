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

<layout:page-container title="ForumEngine" activePage="forums">

<div class="container align-items-center">
    <div class="card my-card">
            <c:forEach items="${forums}" var="forum">
                <a href="forum?id=${forum.forumId}" class="my-link-card">
                <div class="card">
                    <div class="card-header" style="height: 6rem; background-color: #4e73ff">
                        <h1 style="text-align: center;">${forum.name}</h1>
                    </div>
                    <p>${forum.description}</p>
                    <div class="card-footer text-muted">
                        Created on: ${forum.createdOn}
                    </div>
                </div>
                </a>
            </c:forEach>
    </div>
</div>
</layout:page-container>
