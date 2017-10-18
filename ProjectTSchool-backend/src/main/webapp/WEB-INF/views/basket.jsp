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
    <link rel="stylesheet" href="../css/sweetalert.min.css">
</head>
<body>
<%@include file="hat.jsp" %>
<div class="panel panel-default back">
    <input id="back" type="submit" class="btn btn-info button-one col-md-2 button-number"
           value="Back">
    <div class="panel-body" id="contractPanel">
        <div class="panel-body back1">
            <div id="warning">${basket.getWarning()}</div>
            <div class="row">
            <c:if test="${not empty basket.getRateId()}">
                <div id="newTariff" >
                    <div class="well well-sm col-md-3">
                        <div class="row">
                            <p class="col-md-10">${basket.getRateName()}</p>
                            <input type="submit" onclick="deleteTariff(${basket.getRateId()})"
                                   class="btn btn-danger delete-button col-md-3" value="">
                        </div>
                        <p>${basket.getRateCost()}</p>
                    </div>
                </div>
            </c:if>
            <c:forEach var="prod" items="${basket.getOptionList()}">
                <div class="newOptions">
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
            </div>
            <c:if test="${((empty basket.getOptionList()) || (basket.getOptionList().size() == 0)) && (empty basket.getRateId())}">
                <div class="row">
                    <div class="well well-sm col-md-5">
                        <p>Your basket is empty.</p>
                    </div>
                </div>
            </c:if>
            <c:if test="${(basket.getOptionList().size() > 0) || (not empty basket.getRateId())}">
                <input id="saveBasket" type="submit" class="btn btn-info button-one button-act col-md-2 button-number"
                       value="Save">
            </c:if>
        </div>
    </div>
</div>
<script type="application/javascript"
        src="../js/jquery-3.2.1.min.js">
</script>
<!-- sweetalert2 -->
<script type="application/javascript" src="../js/sweetalert.min.js"></script>
<script type="application/javascript" src="../js/zone.js"></script>
<script type="application/javascript" src="../js/Reflect.js"></script>
<script type="application/javascript" src="../js/system.js"></script>
<script type="application/javascript" src="../js/typescript.js"></script>
<%--WARNING!!!!!--%>
<%--<link rel="stylesheet" href="https://npmcdn.com/sweetalert2@6.10.3/dist/sweetalert2.min.css">--%>
<%--<script src="https://npmcdn.com/sweetalert2@6.10.3/dist/sweetalert2.min.js"></script>--%>

<%--<script src="https://npmcdn.com/zone.js@0.8.18/dist/zone.js"></script>--%>
<%--<script src="https://npmcdn.com/reflect-metadata@0.1.3/Reflect.js"></script>--%>
<%--<script src="https://npmcdn.com/systemjs@0.20.19/dist/system.js"></script>--%>
<%--<script src="https://unpkg.com/typescript@2.6.0-rc/lib/typescript.js"></script>--%>
<script type="application/javascript" src="../js/basket.js"></script>
</body>
</html>