$('document').ready(function () {
    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (jobTitle, status) {
            $('#JB_IDEdit').val(jobTitle.id);
            $('#JB_descriptionEdit').val(jobTitle.description);
            $('#JB_detailsEdit').val(jobTitle.details);
            $('#JB_last_modified_dateEdit').val(jobTitle.lastModifiedDate.substring(0, 19).replace("T", " "));
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
        $.get(href, function (jobTitle, status) {
            $('#JB_IDDetails').val(jobTitle.id);
            $('#JB_descriptionDetails').val(jobTitle.description);
            $('#JB_detailsDetails').val(jobTitle.details);
            $('#JB_last_modified_dateDetails').val(jobTitle.lastModifiedDate.substring(0, 19).replace("T", " "));
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
    });
});