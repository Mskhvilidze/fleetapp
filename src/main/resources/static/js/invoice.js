$('document').ready(function () {
    $('#invoiceAdd').on('click', function (event) {
        event.preventDefault();
        var form = $('#addModal form')[0]; // Zugriff auf das Formular
        var formData = new FormData(form);
        // Schleife durch die Einträge des FormData-Objekts
        /*formData.forEach(function(value, key) {
            alert(key + ": " + value);
        });*/
        let isValid = validateForm();
        if (isValid === false) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8086/fricke/invoice/addNew',
            data: formData,
            processData: false, // Damit die Daten nicht in einen Query-String umgewandelt werden
            contentType: false, // Damit der richtige Content-Type für Datei-Upload verwendet wird
            success: function (data, textStatus, jqXHR) {
                if (jqXHR.status === 201) {
                    window.location.href = 'http://localhost:8086/fricke/invoice';
                    $('#succ').text(jqXHR.responseText);
                    $('#flash').attr('class', 'alert alert-success');
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 404) {
                    $('#Tdanger').text(jqXHR.responseText);
                    $('.flash').attr('class', 'alert alert-danger');
                } else {
                    alert(jqXHR.responseText);
                    $('#Tdanger').text("Ein Fehler ist aufgetreten: Prüfen Sie bitte, ob die Felder korrekt gefüllt sind!");
                    $('.flash').attr('class', 'alert alert-danger');
                }
            }
        });
    });

    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (invoice, status) {
            $('#InvoiceIdEdit').val(invoice.id);
            $('#InvoiceClientEdit').val(invoice.client.id);
            $('#InvoiceInvoiceStatusEdit').val(invoice.invoiceStatus.id);

            var invoiceDate = invoice.invoiceDate.substring(0, 10);
            $('#InvoiceInvoiceDateEdit').val(invoiceDate);
            $('#InvoiceRemarksEdit').val(invoice.remarks);
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
        $.get(href, function (invoice, status) {
            $('#InvoiceIdDetails').val(invoice.id);
            $('#InvoiceClientDetails').val(invoice.client.id);
            $('#InvoiceInvoiceStatusDetails').val(invoice.invoiceStatus.id);

            var invoiceDate = invoice.invoiceDate.substring(0, 10);
            $('#InvoiceInvoiceDateDetails').val(invoiceDate);
            $('#InvoiceRemarksDetails').val(invoice.remarks);
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

    function validateForm() {
        var clientSelect = document.getElementById('InvoiceClientAdd');
        var invoiceStatusSelect = document.getElementById('InvoiceInvoiceStatusAdd');

        var clientSelectedValue = clientSelect.options[clientSelect.selectedIndex].value;
        var invoiceStatusSelectedValue = invoiceStatusSelect.options[invoiceStatusSelect.selectedIndex].value;

        if (clientSelectedValue === '-SELECT-' || invoiceStatusSelectedValue === '-SELECT-') {
            alert('Bitte wählen Sie einen Client oder InvoiceStatus aus.');
            return false;
        }
        return true;
    }
});
