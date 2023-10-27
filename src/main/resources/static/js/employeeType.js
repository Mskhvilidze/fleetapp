$('document').ready(function () {
    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (emplpyeType, status) {
            $('#ET_IDEdit').val("DSG");
            $('#ET_descriptionEdit').val(emplpyeType.description);
            $('#ET_detailsEdit').val(emplpyeType.details);
            $('#ET_last_modified_dateEdit').val(emplpyeType.lastModifiedDate.substring(0, 19).replace("T", " "));
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
        $.get(href, function (emplpyeType, status) {
            $('#ET_IDDetails').val(emplpyeType.id);
            $('#ET_descriptionDetails').val(emplpyeType.description);
            $('#ET_detailsDetails').val(emplpyeType.details);
            $('#ET_last_modified_dateDetails').val(emplpyeType.lastModifiedDate.substring(0, 19).replace("T", " "));
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