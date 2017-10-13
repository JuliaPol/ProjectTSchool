<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/customer.css" rel="stylesheet">
    <link href="../css/basket.css" rel="stylesheet">
</head>
<body>
<%@include file="hat.jsp" %>
<div class="panel panel-default back">
    <div class="panel-body" id="contractPanel">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#">Tariffs</a></li>
            <li><a href="#">Options</a></li>
        </ul>
        <div class="panel-body back1">
            <div class="row">
                <div class="well well-sm col-md-3">
                    <div class="row">
                        <p class="col-md-10">Option1</p>
                        <input type="submit" class="btn btn-danger delete-button col-md-3" value="">
                    </div>
                    <p>Cost</p>
                </div>
            </div>
            <div class="row">
                <div class="well well-sm col-md-3">
                    <div class="row">
                        <p class="col-md-10">Option1</p>
                        <input type="submit" class="btn btn-danger delete-button col-md-3" value="">
                    </div>
                    <p>Cost</p>
                </div>
            </div>
            <input type="submit" class="btn btn-info button-one button-act col-md-2 button-number" value="Save">
        </div>
    </div>
</div>
</body>
</html>