<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
<head>
    <title>Регистрация</title>
    <jsp:include page="common.jsp"></jsp:include>
</head>

<body>
<div class="container mt-3">

    <form:form method="POST" modelAttribute="userForm" action="/registration">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <h2 class="form-signin-heading">Create your account:</h2>
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Name:</label>
            <div class="col-sm-10">
                <form:input type="text" path="name" placeholder="Name"
                            autofocus="true" class="form-control"></form:input>
                <form:errors path="name"></form:errors>
                    ${nameError}
            </div>
        </div>

        <div class="form-group row">
            <label for="surname" class="col-sm-2 col-form-label">Surname:</label>
            <div class="col-sm-10">
                <form:input type="text" path="surname" placeholder="Surname"
                            autofocus="true" class="form-control"></form:input>
                <form:errors path="surname"></form:errors>
                    ${surnameError}
            </div>
        </div>
        <div class="form-group row">
            <label for="login" class="col-sm-2 col-form-label">Login:</label>
            <div class="col-sm-10">
                <form:input type="text" path="login" placeholder="Login"
                            autofocus="true" class="form-control"></form:input>
                <form:errors path="login"></form:errors>
                    ${loginError}
            </div>
        </div>

        <fieldset class="form-group">
            <div class="row">
                <label class="col-sm-2 col-form-label">
                    Sign up as:
                </label>
<%--                <legend class="col-form-label col-sm-2 pt-0">Sign up as</legend>--%>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="student" id="student1" value="${roleStudent}"
                               checked>
                        <label class="form-check-label" for="student1">
                            Student
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="teacher" id="teacher1" value="${roleTeacher}">
                        <label class="form-check-label" for="teacher1">
                            Teacher
                        </label>
                    </div>
                </div>
            </div>
        </fieldset>

        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-10">
                <form:input type="password" path="password" placeholder="Password" class="form-control"></form:input>
            </div>
        </div>
        <div class="form-group row">
            <label for="passwordConfirm" class="col-sm-2 col-form-label">Confirm password:</label>
            <div class="col-sm-10">
                <form:input type="password" path="passwordConfirm"
                            placeholder="Confirm your password" class="form-control"></form:input>
                <form:errors path="password"></form:errors>
                    ${passwordError}
            </div>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
    </form:form>
    <%--    <a href="/">Главная</a>--%>
</div>
</body>
</html>