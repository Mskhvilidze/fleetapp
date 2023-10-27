$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (invoiceStatus, status) {
            $('#ISV_IDEdit').val(invoiceStatus.id);
            $('#ISV_descriptionEdit').val(invoiceStatus.description);
            $('#ISV_detailsedit').val(invoiceStatus.details);
            $('#ISV_last_modified_dateedit').val(invoiceStatus.lastModifiedDate.substring(0, 19).replace("T", " "));
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
        let href = $(this).attr('href');
        $.get(href, function (invoiceStatus, status) {
            $('#ISV_IDDetails').val(invoiceStatus.id);
            $('#ISV_descriptionDetails').val(invoiceStatus.description);
            $('#ISV_detailsDetails').val(invoiceStatus.details);
            $('#ISV_last_modified_dateDetails').val(invoiceStatus.lastModifiedDate.substring(0, 19).replace("T", " "));
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

    $('table #deleteButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href',href);
        $('#deleteModal').modal({
            backdrop: 'static',
            keyboard: false
        });
    })
})