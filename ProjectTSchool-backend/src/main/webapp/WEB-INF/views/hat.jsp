<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/customer" id="a3">${customerInfo.getFirstName()} ${customerInfo.getLastName()}</a></li>
                <li id="selectContract">
                    <select class="form-control" id="numberSelect">
                        <c:forEach var="number" items="${customerInfo.getContactNumbers()}" >
                            <option>${number}</option>
                        </c:forEach>
                    </select>
                </li>
                <li><input id="signOut" type="submit" class="btn btn-info button-one col-md-2 button-number" value="Sign out"></li>
                <li><a id="hatHref" href="/basket"><img id="basketImg" src="../img/bask1.png"></a></li>
                <li><p id="countProd"></p></li>
            </ul>
        </div>
    </div>
</nav>
