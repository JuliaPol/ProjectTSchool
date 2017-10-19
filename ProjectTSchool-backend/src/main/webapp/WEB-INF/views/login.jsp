<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/login.css" rel="stylesheet">
</head>

<body>
<c:if test="${param.error != null}">
    <div class="alert alert-danger" id="invalidUsername">
        <p>Invalid username or password.</p>
    </div>
</c:if>
<h2 id="labelH2"> Sign in to <a>Satellite</a></h2>
<div class="form">
    <form action="/check" class="form-horizontal UP" role="form" method="POST">
        <div class="form-group ">
            <label for="inputEmail"><p>Username</p></label>
            <input type="text" name="username" class="form-control input-UP" id="inputEmail" placeholder="Username">
        </div>
        <div class="form-group ">
            <label for="inputPassword"><p>Password</p></label>
            <%--<label for="inputPassword" id="forgotPass"><a>Forgot password?</a></label>--%>
            <input type="password" name="password" class="form-control input-UP" id="inputPassword"
                   placeholder="Password">
        </div>
        <div class="form-group ">
            <input type="submit" id="signIN" class="btn btn-default button-one sign-in" value="Sign in">
        </div>
    </form>
</div>
<%--<div class="form" id="miniForm">--%>
    <%--<label id="signUp">New to Satellite?<a> Create an account.</a></label>--%>
<%--</div>--%>

</body>
</html>