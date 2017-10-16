var numberSelected = $('#numberSelect');

$("#hatHref").attr("href","http://localhost:8080/basket?number=" + number);

numberSelected.change(function () {
    var number = $('#numberSelect option:selected').text();
    location.href="http://localhost:8080/basket?number="+number;
});

function deleteTariff(id) {
    var number = $('#numberSelect option:selected').text();
    var dataUrl = "/delete?number=" + number + "&tariff=" + id+"&option="+"";
    add(dataUrl);
    location.reload();
}

function deleteOption(id) {
    var number = $('#numberSelect option:selected').text();
    var dataUrl = "/delete?number=" + number + "&option=" + id+"&tariff="+"";
    add(dataUrl);
    location.reload();
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