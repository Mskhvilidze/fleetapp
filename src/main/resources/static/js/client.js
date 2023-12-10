$('document').ready(function () {
    $('#add').on('click', function (event) {
        event.preventDefault();
        // var form_data = cleanFormData($("form :input").serializeArray());
        var form = $('#addModal form')[0]; // Zugriff auf das Formular
        var formData = new FormData(form);

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8086/fricke/client/addNew',
            data: formData,
            processData: false, // Damit die Daten nicht in einen Query-String umgewandelt werden
            contentType: false, // Damit der richtige Content-Type für Datei-Upload verwendet wird
            success: function (data, textStatus, jqXHR) {
                if (jqXHR.status === 201) {
                    window.location.href = 'http://localhost:8086/fricke/client';
                    alert(jqXHR.responseText)
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 404) {
                    $('#Tdanger').text(jqXHR.responseText);
                    $('.flash').attr('class', 'alert alert-danger');
                } else {
                    $('#Tdanger').text("Ein Fehler ist aufgetreten:");
                    $('.flash').attr('class', 'alert alert-danger');
                }
            }
        });
    });

    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (clients, status) {
            $('#ClientIDEdit').val(clients.id);
            $('#ClientCountryEdit').val(clients.country.id);
            $('#ClientStateEdit').val(clients.stateid);
            $('#ClientNameEdit').val(clients.name);
            $('#ClientDetailsEdit').val(clients.details);
            $('#ClientWebsiteEdit').val(clients.website);
            $('#ClientAddressEdit').val(clients.address);
            $('#ClientCityEdit').val(clients.city);
            $('#ClientPhoneEdit').val(clients.phone);
            $('#ClientMobileEdit').val(clients.mobile);
            $('#ClientEmailEdit').val(clients.email);
        })
            .done(function () {
                // Erfolgreicher Abschluss
                $('.tooltip-r').tooltip();
                $('#editModal').modal();
            })

            .fail(function (jqXHR, textStatus, errorThrown) {
                // Fehlerbehandlung
                if (jqXHR.status === 404) {
                    $('#flash').attr('class', 'alert alert-danger');
                    $('#Tt').text(jqXHR.responseText);
                } else {
                    alert("Ein Fehler ist aufgetreten: " + textStatus);
                }
            });
    });
    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (clients, status) {
            $('#ClientIDDetails').val(clients.id);
            $('#ClientCountryDetails').val(clients.country.id);
            $('#ClientStateDetails').val(clients.stateid);
            $('#ClientNameDetails').val(clients.name);
            $('#ClientDetailsDetails').val(clients.details);
            $('#ClientWebsiteDetails').val(clients.website);
            $('#ClientAddressDetails').val(clients.address);
            $('#ClientCityDetails').val(clients.city);
            $('#ClientPhoneDetails').val(clients.phone);
            $('#ClientMobileDetails').val(clients.mobile);
            $('#ClientEmailDetails').val(clients.email);
        })
            .done(function () {
                // Erfolgreicher Abschluss
                $('.tooltip-r').tooltip();
                $('#detailsModal').modal();
            })

            .fail(function (jqXHR, textStatus, errorThrown) {
                // Fehlerbehandlung
                if (jqXHR.status === 404) {
                    $('#flash').attr('class', 'alert alert-danger');
                    $('#Tt').text(jqXHR.responseText);
                } else {
                    alert("Ein Fehler ist aufgetreten: " + textStatus);
                }
            });
    });

    $('table #deleteButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal({
            backdrop: 'static',
            keyboard: false
        });
    });
});
