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
<div class="logo">
    <a id="logo" href="#">Satellite</a>
</div>
<nav class="navbar navbar-default head" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#" id="a1">Tariffs</a></li>
                <li><a href="#" id="a2">Options</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" id="a3">${customerInfo.getFirstName()} ${customerInfo.getLastName()}</a></li>
                <li id="selectContract">
                    <select class="form-control" id="numberSelect">
                        <c:forEach var="number" items="${customerInfo.getContactNumbers()}" >
                            <option>${number}</option>
                        </c:forEach>
                    </select>
                </li>
            </ul>
        </div>
    </div>
</nav>
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
                <input type="submit" id="statusButton" class="col-md-2 btn btn-info button-one button-act" value="">
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
            <div class="panel-body back1" id="tariff">
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
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="application/javascript" src="../js/customer.js"></script>
</body>
</html>