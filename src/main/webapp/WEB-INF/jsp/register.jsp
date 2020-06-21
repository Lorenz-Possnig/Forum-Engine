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
    <form:form modelAttribute="user" method="post" action="createuser" class="needs-validation"
               oninput='passwordconfirm.setCustomValidity(passwordconfirm.value != password.value ? "Passwords do not match." : "")'>
        <label for="username"><p style="padding-left: 0px">Username:</p></label><br>
        <form:input type="text" id = "username" name = "username" required="required" max="26" cssClass="form-control" path="username"/><br>
        <label for="firstname"><p style="padding-left: 0px">Firstname:</p></label><br>
        <form:input type="text" id = "firstname" name = "firstname" required="required" max="255" cssClass="form-control" path="firstname"/><br>
        <label for="lastname"><p style="padding-left: 0px">Lastname:</p></label><br>
        <form:input path="lastname" type="text" id = "lastname" name = "lastname" required="required" max="255" cssClass="form-control"/>
        <label for="mail"><p style="padding-left: 0px">E-Mail:</p></label><br>
        <form:input path="mail" type="text" id = "mail" name = "mail" required="required" cssClass="form-control"/><br>
        <label for="password"><p style="padding-left: 0px">Password:</p></label><br>
        <form:input path="password" type="password" id="password" name="password" required="required" cssClass="form-control"/><br>
        <label for="passwordconfirm"><p style="padding-left: 0px">Confirm Password:</p></label><br>
        <input class="form-control" type="password" id="passwordconfirm" name="passwordconfirm" required="required"/><br><br>
        <input type="submit" value="Create Account">
    </form:form>
</layout:page-container>