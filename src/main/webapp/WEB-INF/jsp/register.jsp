<%@ page import="at.fhj.ima.forumengine.forumengine.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<form method="post" action="createuser">
    <label for="username">Username:</label><br>
    <input type="text" id = "username" name = "username"><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password">
    <input type="submit" value="Create Account">
</form>