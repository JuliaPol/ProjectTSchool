var numberSelected = $('#numberSelect');

var url = new URL(window.location.href);
var number = url.searchParams.get("number");

$("#hatHref").attr("href", "http://localhost:8080/basket?number=" + number);
$("#saveBasket").attr("action", "/contract/add/" + number);

numberSelected.val(number);

numberSelected.change(function () {
    var number = $('#numberSelect option:selected').text();
    location.href = "http://localhost:8080/basket?number=" + number;
});

function deleteTariff(id) {
    var number = $('#numberSelect option:selected').text();
    var dataUrl = "/delete?number=" + number + "&tariff=" + id + "&option=" + "";
    add(dataUrl);
}

function deleteOption(id) {
    var number = $('#numberSelect option:selected').text();
    var dataUrl = "/delete?number=" + number + "&option=" + id + "&tariff=" + "";
    add(dataUrl);
}

function add(url) {
    var dataUrl = "http://localhost:8080/basket" + url;
    $.ajax({
        url: dataUrl
    }).done(function (result) {
        count = result;
        if (count !== 0) {
            $('#countProd').text(count);
        }
        location.reload();
    });
}

