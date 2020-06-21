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

<layout:page-container title="Create an Account" activePage="register">
    <form:form modelAttribute="user" method="post" action="createuser" class="needs-validation"
               oninput='passwordconfirm.setCustomValidity(passwordconfirm.value != password.value ? "Passwords do not match." : "")'>
        <label for="username"><p style="padding-left: 0px">Username:</p></label><br>
        <form:input type="text" id = "username" name = "username" required="required" max="26" cssClass="form-control" path="username"></form:input><br>
        <label for="firstname"><p style="padding-left: 0px">Firstname:</p></label><br>
        <form:input type="text" id = "firstname" name = "firstname" required="required" max="255" cssClass="form-control" path="firstname"></form:input><br>
        <label for="lastname"><p style="padding-left: 0px">Lastname:</p></label><br>
        <form:input path="lastname" type="text" id = "lastname" name = "lastname" required="required" max="255" cssClass="form-control"></form:input><br>
        <label for="mail"><p style="padding-left: 0px">E-Mail:</p></label><br>
        <form:input path="mail" type="text" id = "mail" name = "mail" required="required" cssClass="form-control"></form:input><br>
        <label for="password"><p style="padding-left: 0px">Password:</p></label><br>
        <form:input path="password" type="password" id="password" name="password" required="required" cssClass="form-control"></form:input><br>
        <label for="passwordconfirm"><p style="padding-left: 0px">Confirm Password:</p></label><br>
        <input class="form-control" type="password" id="passwordconfirm" name="passwordconfirm" required="required"/>
        <label for="securityquestion"><p style="padding-left: 0px">Security Question</p></label><br>
        <form:input path="securityQuestion" type="text" id="securityquestion" name="securityquestion" required="required" cssClass="form-control"></form:input>
        <label for="securityquestion-answer"><p style="padding-left: 0px">Security Question Answer</p> </label><br>
        <form:input path="securityQuestionAnswer" type="text" id="securityquestion-answer" name="securityquestion-answer" required="required" cssClass="form-control"></form:input><br>
        <br>
        <br>
        <input type="submit" value="Create Account">
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
    </form:form>
</layout:page-container>