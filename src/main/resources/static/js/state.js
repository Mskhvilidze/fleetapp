$('document').ready(function () {
    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        // /state/findById/?id=2
        var href = $(this).attr('href');
        $.get(href, function (state, status) {
            $('#idEdit').val(state.id);
            $('#descriptionEdit').val(state.name);
            $('#detailsEdit').val(state.details);
            $('#codeEdit').val(state.countryid);
        })
            .done(function () {
                // Erfolgreicher Abschluss
                $('.tooltip-r').tooltip();
                $('#editModal').modal();
            })
            .fail(function (jqXHR, textStatus, errorThrown) {
                // Fehlerbehandlung
                if (jqXHR.status === 404) {
                    alert("Error: " + jqXHR.responseText);
                } else {
                    alert("Ein Fehler ist aufgetreten: " + textStatus);
                }
            });
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (state, status) {
            $('#StateCountryDetails').val(state.countryid);
            $('#continentDetails').val(state.country.continent);
            $('#capitalDetails').val(state.country.capital);
            $('#nationalityDetails').val(state.country.nationality);
            $('#idDetails').val(state.id);
            $('#descriptionDetails').val(state.name);
            $('#detailsDetails').val(state.details);
            $('#codeDetails').val(state.countryid);
        })
            .done(function () {
                // Erfolgreicher Abschluss
                $('.tooltip-r').tooltip();
                $('#detailsModal').modal();
            })
            .fail(function (jqXHR, textStatus, errorThrown) {
                // Fehlerbehandlung
                if (jqXHR.status === 404) {
                    alert("Error: " + jqXHR.responseText);
                } else {
                    alert("Ein Fehler ist aufgetreten: " + textStatus);
                }
            });

        itemIDs = ["5", "3", "8"]
        itemIDs1 = ["10", "13", "18"]
        var requestData = {
            data1: itemIDs,
            data2: itemIDs1
        }
        $.ajax({
            url: "http://localhost:8086/fricke/states/details/100",
            type: "POST",
            data: JSON.stringify(requestData),
            cache: false,
            dataType: "text",
            contentType: "application/json",
            success: function (response) {
            },
            error: function (xhr, ajaxOption, thrownError) {
                alert(xhr.status);
            },
            statusCode: {
                404: function () {
                    alert("page not found");
                }
            }
        });
    });

    $('table #deleteButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal({
            backdrop: 'static',
            keyboard: false
        });
    });
});