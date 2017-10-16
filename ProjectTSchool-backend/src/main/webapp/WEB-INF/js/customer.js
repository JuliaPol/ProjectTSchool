var contact = $("#contactButton");
var tariff = $("#tariffsButton");
var option = $("#optionsButton");
var allHref = $('#allHref');
var myHref = $('#myHref');
var numberSelected = $('#numberSelect');
var statusNumb = $('#statusButton');
var count = 0;

refreshContract();
var number = $('#numberSelect option:selected').text();
$("#hatHref").attr("href","http://localhost:8080/basket?number=" + number);
add("/getCount?number="+number);


function checkStatus() {
    var number = $('#numberSelect option:selected').text();
    var dataUrlContract = "http://localhost:8080/contract/" + number;
    $.ajax({
        url: dataUrlContract
    }).done(function (result) {
            if (result.status === 'AVAILABLE') {
                $('.button-act').removeClass('disabled');
            } else if (result.status === 'BLOCKED_BY_THE_CUSTOMER') {
                $('.button-act').addClass('disabled');
                statusNumb.removeClass('disabled');
            } else {
                $('.button-act').addClass('disabled');
            }
        }
    )
}

function refreshContract() {
    var number = $('#numberSelect option:selected').text();
    var dataUrlContract = "http://localhost:8080/contract/" + number;
    $.ajax({
        url: dataUrlContract
    }).done(function (result) {
        $('#yourNumber').text(result.number);
        $('#numberStatus').text(result.status);
        if (result.status === 'AVAILABLE') {
            statusNumb.removeClass('disabled');
            statusNumb.val('Block');
        } else if (result.status === 'BLOCKED_BY_THE_CUSTOMER') {
            statusNumb.removeClass('disabled');
            statusNumb.val('Unblock');
        } else {
            statusNumb.val('Unblock');
            statusNumb.addClass('disabled');
        }
    })
}

numberSelected.change(function () {
    if (tariff.hasClass('active')) {
        if ($('#myLi').hasClass('active')) {
            refreshItem("/tariffs/active?number=");
        } else {
            refreshList("/tariffs/?number=","Activate");
        }
    } else if (option.hasClass('active')) {
        if ($('#myLi').hasClass('active')) {
            refreshList("/options/active?number=","Deactivate");
        } else {
            refreshList("/options/?number=","Activate");
        }
    } else {
        refreshContract();
    }
    checkStatus();
    var number = $('#numberSelect option:selected').text();
    $("#hatHref").attr("href","http://localhost:8080/basket?number=" + number);
    add("/getCount?number="+number);
});

function tabsActive(n) {
    if (n === 0) {
        $('#myLi').removeClass('active');
        $('#allLi').addClass('active');
    } else {
        $('#myLi').addClass('active');
        $('#allLi').removeClass('active');
    }
}

option.click(function () {
    $('#contractPanel').css("display", "none");
    $('#optionTariffPanel').css("display", "block");
    tariff.removeClass('active');
    contact.removeClass('active');
    option.addClass('active');
    tabsActive(1);
    refreshList("/options/active?number=","Deactivate");
    checkStatus();
});

contact.click(function () {
    $('#optionTariffPanel').css("display", "none");
    $('#contractPanel').css("display", "block");
    tariff.removeClass('active');
    contact.addClass('active');
    option.removeClass('active');
    refreshContract();
});

tariff.click(function () {
    $('#contractPanel').css("display", "none");
    $('#optionTariffPanel').css("display", "block");
    tariff.addClass('active');
    contact.removeClass('active');
    option.removeClass('active');
    tabsActive(1);
    refreshItem("/tariffs/active?number=");
    checkStatus();
});

statusNumb.click(function () {
    var number = $('#numberSelect option:selected').text();
    var dataUrl = "http://localhost:8080/contract/customer/"+number;
    $.post( dataUrl ).done( function() {
        refreshContract();
    });
});

function addItemWithName(name, status) {
    var List = $("#tariff");
    $("<div class=\"row\">").appendTo(List);
    $("<div class=\"well well-sm col-md-9\"></div>").text(name.name).appendTo(List);
    $("<input onclick='addTariff("+name.id+")' type=\"submit\" class=\"btn btn-info button-one button-act col-md-2\" " +
        "value=''>").val(status).appendTo(List);
    $("</div>").appendTo(List);
}

function addItemForOptions(name) {
    var List = $("#tariff");
    $("<div class=\"row\">").appendTo(List);
    $("<div class=\"well well-sm col-md-3\"></div>").text(name.name).appendTo(List);
    if (name.compatibleOption != null) {
        $("<div class=\" col-md-3\"></div>").text("This option must be connected with the option: "+ name.compatibleOption).appendTo(List);
    }
    if (name.incompatibleOption != null) {
        $("<div class=\" col-md-3\"></div>").text("This option can not be connected with the option: "+ name.incompatibleOption).appendTo(List);
    }
    $("<input onclick='addOption("+name.id+")' type=\"submit\" class=\"btn btn-info button-one button-act col-md-2\" " +
        "value=\"Add\">").appendTo(List);
    $("</div>").appendTo(List);
}

function addItem(tariff) {
    var List = $("#tariff");
    $("<div class=\"row\">").appendTo(List);
    $("<div class=\"col-md-2 textTariff\"></div>").text("Your tariff:").appendTo(List);
    $("<div class=\"well well-sm col-md-3\"></div>").text(tariff.name).appendTo(List);
    $("<div class=\"col-md-1 textTariff\"></div>").text("Cost:").appendTo(List);
    $("<div class=\"well well-sm col-md-2\"></div>").text(tariff.cost).appendTo(List);
    $("<input type=\"submit\" class=\"btn btn-info button-one button-act col-md-2\" id=\"deactivateTariff\" " +
        "value=\"Deactivate\">" +
        "</div>").appendTo(List);
    $("<div class=\"row\">").appendTo(List);
    $("<div class=\"col-md-1 textTariff\"></div>").text("SMS:").appendTo(List);
    $("<div class=\"well well-sm col-md-1\"></div>").text(tariff.sms).appendTo(List);
    $("<div class=\"col-md-2 textTariff\"></div>").text("Mobile Internet:").appendTo(List);
    $("<div class=\"well well-sm col-md-1\"></div>").text(tariff.internet).appendTo(List);
    $("<div class=\"col-md-3\"></div>").text("Number of minutes for outgoing calls:").appendTo(List);
    $("<div class=\"well well-sm col-md-1\"></div>").text(tariff.calls).appendTo(List);
    $("</div>").appendTo(List);
    $("<div class=\"row\">").appendTo(List);
    $("<div class=\"well well-sm\"></div>").text(tariff.description).appendTo(List);
    $("</div>").appendTo(List);
    $("<ul class=\"list-group scroll-bar\">").appendTo(List);
    var options = tariff.optionList;
    for (var i = 0; i < options.length; i++) {
        var option = options[i];
        $("<li class=\"list-group-item\"></li>").text(option.name).appendTo(List);
    }
    $("</ul>").appendTo(List);
}

function addTariff(id) {
    var number = $('#numberSelect option:selected').text();
    add("/add?number="+number+"&tariff="+id+"&option="+"");
}

function add(url) {
    var dataUrl = "http://localhost:8080/basket"+url;
    $.ajax({
        url: dataUrl
    }).done(function (result) {
        count = result;
        if (count !== 0) {
            $('#countProd').text(count);
        }
    });
}
function addOption(id) {
    var number = $('#numberSelect option:selected').text();
    add("/add?number="+number+"&option="+id+"&tariff="+"");
}

function refreshList(url, statusButton) {
    var number = $('#numberSelect option:selected').text();
    var dataUrl = "http://localhost:8080" + url + number;
    var list = $("#tariff");
    list.empty();
    $.ajax({
        url: dataUrl
    }).done(function (result) {
        for (var i = 0; i < result.length; i++) {
            var tariff = result[i];
            if(url === "/options/?number=") {
                addItemForOptions(tariff);
            } else {
                addItemWithName(tariff, statusButton);
            }
        }
    })
}

function refreshItem(url) {
    var number = $('#numberSelect option:selected').text();
    var dataUrl = "http://localhost:8080" + url + number;
    var list = $("#tariff");
    list.empty();
    $.ajax({
        url: dataUrl
    }).done(function (result) {
        addItem(result);
    })
}

allHref.click(function () {
    tabsActive(0);
    if (tariff.hasClass('active')) {
        refreshList("/tariffs/?number=","Activate");
    } else {
        refreshList("/options/?number=","Activate");
    }
    checkStatus();
});

myHref.click(function () {
    tabsActive(1);
    if (tariff.hasClass('active')) {
        refreshItem("/tariffs/active?number=");
    } else {
        refreshList("/options/active?number=","Deactivate");
    }
    checkStatus();
});
