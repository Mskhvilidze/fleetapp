$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleStatus, status){
            $('#loc_idEdit').val(vehicleStatus.id);
            $('#loc_descriptionEdit').val(vehicleStatus.description);
            $('#loc_detailsEdit').val(vehicleStatus.details);
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleStatus, status){
            $('#local_idDetails').val(vehicleStatus.id);
            $('#loc_codeDeteils').val(vehicleStatus.countryid);
            $('#loc_descriptionDeteils').val(vehicleStatus.description);
        });
        $('.tooltip-r').tooltip();
        $('#detailsModal').modal();
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