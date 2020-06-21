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
        <div card="card-header" style="height: 6rem; background-color: darkslategray">
            <h1 style="text-align: center">${forum.name}</h1>
        </div>
        <div class="card">
            <div class="card-header" style="height: 4rem; background-color: #4e73ff;">
                <h2 style="text-align: center">${question.title}</h2>
            </div>
            <p style="color: white">${question.text}</p>

                <c:if test="${question.getUsername() == 'unknown' ||
                              question.getUsername() == pageContext.request.remoteUser ||
                              pageContext.request.remoteUser == 'koarl'}">
                    <div class="card-footer" style="height: 4rem; background-color: #4e73ff">
                        <div class="row">
                            <div class="col-sm">
                                <a href="newquestion?forumId=${forum.forumId}&questId=${question.questId}">
                                    <p style="text-align: right;">Edit</p>
                                </a>
                            </div>
                            <div class="col-sm">
                                <a href="deletequestion?questId=${question.questId}">
                                    <p style="text-align: right;">Delete</p>
                                </a>
                            </div>
                            <c:if test="${question.getIsClosed() == false}">
                                <div class="col-sm">
                                    <a href="close?questId=${question.questId}">
                                        <p style="text-align: right;">Close this question</p>
                                    </a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:if>

        </div>
    </div>
    <div class="card my-card">
        <c:forEach items="${answers}" var="answer">
            <div class="card">
                <div class="card-header" style="height: 3rem; background-color: #4e73ff">
                    <p>Answer from: ${answer.getUsername()}</p>
                </div>
            <br>
            <p>${answer.text}</p>

            <div class="card-footer text-muted">
                <div class="row">
                    <div class="col-sm">
                        Posted on: ${answer.createdOn}
                    </div>
                    <div class="col-sm">
                        <c:if test="${answer.getUsername() == 'unknown' ||
                            answer.getUsername() == pageContext.request.remoteUser ||
                            pageContext.request.remoteUser == 'koarl'}">
                            <a href="deleteanswer?forumId=${forum.forumId}&questId=${question.questId}&answId=${answer.answId}" >
                                Delete
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
            </div>
            <hr>
        </c:forEach>
    </div>
    <c:if test="${question.getIsClosed() == false &&
                  pageContext.request.remoteUser != null}">
        <div class="card my-card">
            <form method="post" action="createanswer?forumId=${forum.forumId}&questId=${question.questId}">
                <label for="text"><p style="color: black; padding-top: 5px; padding-left: 10px;">Answer this question:</p></label><br>
                <textarea style="box-sizing: border-box; width: 100%" placeholder="Answer" id="text" name="text"></textarea>
                <input type="submit" value="Post">
            </form>
        </div>
    </c:if>
    <br>
    <br>
    <br>
    <br>
</layout:page-container>