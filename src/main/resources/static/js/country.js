$('document').ready(function(){
    $('table #editButton').on('click', function(event){
        event.preventDefault();
        // /countries/findById/?id=2
        var href = $(this).attr('href');
        $.get(href, function(country, status){
            $('#idEdit').val(country.id);
            $('#descriptionEdit').val(country.description);
            $('#capitalEdit').val(country.capital);
            $('#codeEdit').val(country.code);
            $('#continentEdit').val(country.continent);
            $('#nationalityEdit').val(country.nationality);
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

    $('table #deleteButton').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal({
            backdrop: 'static',
            keyboard: false
        });
    });

    $('table #detailsButton').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (country, status) {
            $('#C_iddetails').val(country.id);
            $('#C_descriptiondetails').val(country.description);
            $('#C_capitaldetails').val(country.capital);
            $('#C_codedetails').val(country.code);
            $('#C_continentdetails').val(country.continent);
            $('#C_nationalitydetails').val(country.nationality);
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
});