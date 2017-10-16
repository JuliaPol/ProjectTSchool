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
        <div class="panel-body back1">
            <div class="row">
                <div class="well well-sm col-md-3">
                    <div class="row">
                        <p class="col-md-10">${basket.getRateName()}</p>
                        <input type="submit" onclick="deleteTariff(${basket.getRateId()})"
                               class="btn btn-danger delete-button col-md-3" value="">
                    </div>
                    <p>basket.getRateCost()</p>
                </div>
            </div>
            <c:forEach var="prod" items="${basket.getOptionList()}">
                <div class="row">
                    <div class="well well-sm col-md-3">
                        <div class="row">
                            <p class="col-md-10">${prod.getName()}</p>
                            <input type="submit" onclick="deleteOption(${prod.getId()})"
                                   class="btn btn-danger delete-button col-md-3" value="">
                        </div>
                        <p>${prod.getCost()}</p>
                    </div>
                </div>
            </c:forEach>
            <input type="submit" class="btn btn-info button-one button-act col-md-2 button-number" value="Save">
        </div>
    </div>
</div>
<script type="application/javascript"
        src="../js/jquery-3.2.1.min.js"></script>
<script type="application/javascript" src="../js/customer.js"></script>
</body>
</html>