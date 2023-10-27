$('document').ready(function () {
    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleMake, status) {
            $('#VM_IDEdit').val(vehicleMake.id);
            $('#VM_descriptionEditEdit').val(vehicleMake.description);
            $('#VM_detailsedit').val(vehicleMake.details);
            $('#VM_last_modified_dateedit').val(vehicleMake.lastModifiedDate.substring(0, 19).replace("T", " "));
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
        $.get(href, function (vehicleMake, status) {
            $('#VM_IDDetails').val(vehicleMake.id);
            $('#VM_descriptionEditDetails').val(vehicleMake.description);
            $('#VM_detailsDetails').val(vehicleMake.details);
            $('#VM_last_modified_dateDetails').val(vehicleMake.lastModifiedDate.substring(0, 19).replace("T", " "));
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

    $('table #deleteButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal({
            backdrop: 'static',
            keyboard: false
        });
    })
})