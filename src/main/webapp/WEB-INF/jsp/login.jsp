<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <jsp:include page="common.jsp"></jsp:include>

</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="container mt-3">
    <form:form method="POST" action="/login">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <h2 class="form-signin-heading">Sign in:</h2>

    <div class="form-group row">
        <spring:bind path="login">
            <label for="login" class="col-sm-2 col-form-label">Login:</label>
            <div class="col-sm-10">
                <form:input type="text" path="login" placeholder="Login"
                            autofocus="true" class="form-control"></form:input>
                <form:errors path="login"></form:errors>
<%--                    ${loginError}--%>
            </div>
        </spring:bind>

    </div>
    <div class="form-group row">
        <spring:bind path="password">
            <label for="password" class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-10">
                <form:input type="password" path="password" placeholder="Password" class="form-control"></form:input>
            </div>
        </spring:bind>
    </div>
</div>
<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
<a href="/registration">
    <button class="btn btn-lg btn-primary btn-block" type="button">Create an account</button>
</a>
</form:form>
</div>

</body>
</html>