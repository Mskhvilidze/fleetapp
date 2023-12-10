$('document').ready(function () {
    $('#add').on('click', function (event) {
        event.preventDefault();
        // var form_data = cleanFormData($("form :input").serializeArray());
        var form = $('#addModal form')[0]; // Zugriff auf das Formular
        var formData = new FormData(form);

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8086/fricke/supplier/addNew',
            data: formData,
            processData: false, // Damit die Daten nicht in einen Query-String umgewandelt werden
            contentType: false, // Damit der richtige Content-Type für Datei-Upload verwendet wird
            success: function (data, textStatus, jqXHR) {
                if (jqXHR.status === 201) {
                    window.location.href = 'http://localhost:8086/fricke/supplier';
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
        $.get(href, function (supplier, status) {
            $('#SupplierIDEdit').val(supplier.id);
            $('#SupplierCountryEdit').val(supplier.country.id);
            $('#SupplierStateEdit').val(supplier.stateid);
            $('#SupplierNameEdit').val(supplier.name);
            $('#SupplierDetailsEdit').val(supplier.details);
            $('#SupplierWebsiteEdit').val(supplier.website);
            $('#SupplierAddressEdit').val(supplier.address);
            $('#SupplierCityEdit').val(supplier.city);
            $('#SupplierPhoneEdit').val(supplier.phone);
            $('#SupplierMobileEdit').val(supplier.mobile);
            $('#SupplierEmailEdit').val(supplier.email);
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
        $.get(href, function (supplier, status) {
            $('#SupplierIDDetails').val(supplier.id);
            $('#SupplierCountryDetails').val(supplier.country.id);
            $('#SupplierStateDetails').val(supplier.stateid);
            $('#SupplierNameDetails').val(supplier.name);
            $('#SupplierDetailsDetails').val(supplier.details);
            $('#SupplierWebsiteDetails').val(supplier.website);
            $('#SupplierAddressDetails').val(supplier.address);
            $('#SupplierCityDetails').val(supplier.city);
            $('#SupplierPhoneDetails').val(supplier.phone);
            $('#SupplierMobileDetails').val(supplier.mobile);
            $('#SupplierEmailDetails').val(supplier.email);
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
