    <%@ attribute name="title" required="true" %>
        <%@ attribute name="activePage" required="true" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
        <%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
        <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
        <bootstrap:bootstrap-metadata/>
        <title>Forum Engine</title>
        <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sticky-footer-navbar/">
        <bootstrap:bootstrap-css/>
        <link rel="stylesheet" href="/css/custom.css" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        </head>
        <body>
            <div class="header">
                <h3 style="color: white; text-align: center;">You ask, we google!</h3>
                <p style="color: white; text-align: center;">forumengine.org</p>
            </div>
            <nav class="navbar navbar-expand-lg my-navbar sticky-top">
            <a class="navbar-brand" href="/forums">Forum Engine</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
            <sec:authorize access="hasAuthority('ROLE_USER')">
                <li class="nav-item active">
                <a class="nav-link" href="/newforum">New Forum<span class="sr-only"></span></a>
                </li>
            </sec:authorize>
            <c:if test="${pageContext.request.remoteUser == null}">
                <li class="nav-item">
                <a class="nav-link" href="/register">Register</a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item">
                <a class="nav-link" href="/askUsername">I forgot my Password</a>
                </li>
            </c:if>
            <c:if test="${pageContext.request.remoteUser != null}">
                <li class="nav-item">
                <a class="nav-link" href="/logout">Logout</a>
                </li>
            </c:if>
            </div>
            </li>
            </ul>
            <form class="form-inline my-2 my-lg-0 ml-auto" action="/forums" method="get">
            <input class="form-control mr-sm-2" name="search" type="search" placeholder="Enter Searchterm" aria-label="Search">
            <button class="btn" type="submit">Search</button>
            </form>
            </div>
            </nav>

        <div class="container" > <!-- role="main" --!>


        <!-- Messages ----------------------------------------------------------- -->

        <!-- Error message ----------------------------------------------------------- -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">${errorMessage}</div>
        </c:if>
        <!-- Error message ----------------------------------------------------------- -->

        <!-- Warning message ----------------------------------------------------------- -->
        <c:if test="${not empty warningMessage}">
            <div class="alert alert-warning" role="warning">${warningMessage}</div>
        </c:if>
        <!-- Warning message ----------------------------------------------------------- -->

        <!-- successful message ----------------------------------------------------------- -->
        <c:if test="${not empty message}">
            <div class="alert alert-success" role="warning">${message}</div>
        </c:if>
        <!-- successful message ----------------------------------------------------------- -->

        <!-- Messages ----------------------------------------------------------- -->
        <jsp:doBody/>
        </div>
        <style>
        .footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: #4e73ff;
        color: white;
        text-align: center;}
        .fa {
        padding: 20px;
        font-size: 30px;
        height: 30px;
        width: 30px;
        margin: 5px 2px;
        text-align: center;
        text-decoration: none;
        border-radius: 50%;
        }
        .fa:hover {
        opacity: 0.7;
        }
        .fa-facebook {
        background: #3B5998;
        color: white;
        }
        .fa-instagram {
            background: #125688;
            color: white;
        }
        </style>
        <div class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-sm">
                        &#169; 2020 Copyright:
                        Forumengine.org
                    </div>
                    <div class="col-sm">
                        <a href="http://www.facebook.com/forum-engine-109767487448741" class="fa fa-facebook"></a>
                        <a href="http://www.instagram.com/real_forum_engine" class="fa fa-instagram"></a>
                    </div>
                </div>
            </div>
        </div>
        <bootstrap:bootstrap-js/>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.iframe-transport/1.0.1/jquery.iframe-transport.min.js"></script>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/blueimp-file-upload/10.28.0/js/jquery.fileupload.min.js"></script>
        <script type="text/javascript" src="<c:url value="/js/custom.js"/>"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        </body>
        </html>
