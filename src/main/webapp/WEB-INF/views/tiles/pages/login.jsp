<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>

<div class="container">
    <style>
        <%@include file='/css/login.css' %>
    </style>
    <div class="errorMessage">
        <p>${requestScope.get("error")}</p>
    </div>
    <div class="login">
        <h1>Login</h1>
        <form method="post">
            <input type="text" name="login" placeholder="Login" required="required" />
            <input type="password" name="password" placeholder="Password" required="required" />
            <button type="submit" class="btn btn-primary btn-block btn-large">Let me in.</button>
            <a href="${base}/registration" class="btn btn-primary btn-block btn-large">Sign up</a>
        </form>
    </div>
</div>

