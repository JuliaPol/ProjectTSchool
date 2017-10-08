var contact = $("#contactButton");
var tariff = $("#tariffsButton");
var option = $("#optionsButton");
var tariffAll = $('#allHref');
var tariffMy = $('#myHref');
var numberSelected = $('#numberSelect');


numberSelected.change(function () {
    if ($('myLi').hasClass('active')) {
        refreshTariff();
    } else {
        refreshTariffs();
    }
});

function tabsActive(n) {
    if (n === 0) {
        $('myLi').removeClass('active');
        $('allLi').addClass('active');
    } else {
        $('myLi').addClass('active');
        $('allLi').removeClass('active');
    }
}
option.click(function () {
    tariff.removeClass('active');
    contact.removeClass('active');
    option.addClass('active');
});

contact.click(function () {
    tariff.removeClass('active');
    contact.addClass('active');
    option.removeClass('active');
});

tariff.click(function () {
    tariff.addClass('active');
    contact.removeClass('active');
    option.removeClass('active');
    tabsActive(1);
    refreshTariff();
});

var dataResult;

function addItemWithName(name) {
    var List = $("#tariff");
    $("<div class=\"row\">").appendTo(List);
    $("<div class=\"well well-sm col-md-9\"></div>").text(name).appendTo(List);
    $(" <input type=\"submit\" class=\"btn btn-info button-one buttonAct col-md-2\" " +
        "value=\"Deactivate\">" +
        "</div>").appendTo(List);
}

function addItem(tariff) {
    var List = $("#tariff");
    $("<div class=\"row\">").appendTo(List);
    $("<div class=\"well well-sm col-md-2\"></div>").text(tariff.name).appendTo(List);
    $("<div class=\"well well-sm col-md-2\"></div>").text(tariff.cost).appendTo(List);
    $("<div class=\"well well-sm col-md-2\"></div>").text(tariff.sms).appendTo(List);
    $("<div class=\"well well-sm col-md-2\"></div>").text(tariff.internet).appendTo(List);
    $(" <input type=\"submit\" class=\"btn btn-info button-one buttonAct col-md-2\" " +
        "value=\"Activate\">" +
        "</div>").appendTo(List);
}

function refreshTariffs() {
    var number = $('#numberSelect option:selected').text();
    var dataUrl = "http://localhost:8080/tariffs/?number=" + number;
    var list = $("#tariff");
    list.empty();
    $.ajax({
        url: dataUrl
    }).done(function (result) {
        dataResult = result;
        for (var i = 0; i < result.length; i++) {
            var tariff = result[i];
            addItemWithName(tariff.name);
        }
    })
}

function refreshTariff() {
    var number = $('#numberSelect option:selected').text();
    var dataUrl = "http://localhost:8080/tariffs/active?number=" + number;
    var list = $("#tariff");
    list.empty();
    $.ajax({
        url: dataUrl
    }).done(function (result) {
        dataResult = result;
            addItem(result);
    })
}

tariffAll.click(function () {
    tabsActive(0);
    refreshTariffs();
});

tariffMy.click(function () {
    tabsActive(1);
    refreshTariff();
})
