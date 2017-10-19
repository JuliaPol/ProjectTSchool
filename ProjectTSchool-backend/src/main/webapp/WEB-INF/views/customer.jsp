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
</head>
<body>
<%@include file="hat.jsp" %>
<div class="row">
    <div class="btn-group-vertical button-group col-md-4">
        <div class="btn-group">
            <input type="submit" id="contactButton" class="active btn btn-info button-one button-one1" value="My contract">
        </div>
        <div class="btn-group">
            <input type="submit" id="tariffsButton" class="btn btn-info button-one button-one1" value="Tariffs">
        </div>
        <div class="btn-group">
            <input type="submit" id="optionsButton" class="btn btn-info button-one button-one1" value="Options">
        </div>
    </div>
    <div class="panel panel-default back col-md-9">
        <div class="panel-body" id="contractPanel">
            <div class="row">
                <div class="col-md-2">Your number:</div>
                <div class="col-md-2" id="yourNumber"></div>
                <input type="submit" id="statusButton" class="col-md-2 btn btn-info button-one" value="">
            </div>
            <div class="row">
                <div class="col-md-2">Status:</div>
                <div class="col-md-2" id="numberStatus"></div>
            </div>
        </div>
        <div class="panel-body" id="optionTariffPanel">
            <ul class="nav nav-tabs">
                <li class="active" id="myLi"><a id="myHref" href="#">My</a></li>
                <li id="allLi"><a id="allHref" href="#">All</a></li>
            </ul>
            <div class="panel-body back1 optionPanel scrollbar-cyan bordered-cyan card example-1 square" id="tariff">
            </div>
            <%--<div class="text-center">--%>
                <%--<ul class="pagination" id="pag">--%>
                    <%--<li><a href="#">&laquo;</a></li>--%>
                    <%--<li><a href="#">1</a></li>--%>
                    <%--<li><a href="#">2</a></li>--%>
                       <%--<li><a href="#">3</a></li>--%>
            <%--<li><a href="#">4</a></li>--%>
            <%--<li><a href="#">5</a></li>--%>
            <%--<li><a href="#">&raquo;</a></li>--%>
            <%--</ul>--%>
            <%--</div>--%>
        </div>
    </div>
</div>
<script type="application/javascript"
            src="../js/jquery-3.2.1.min.js"></script>
    <script type="application/javascript" src="../js/customer.js"></script>
</body>
</html>