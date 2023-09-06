$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleType, status){
            $('#loc_idEdit').val(vehicleType.id);
            $('#loc_descriptionEdit').val(vehicleType.description);
            $('#loc_detailsEdit').val(vehicleType.details);
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleType, status){
            $('#local_idDetails').val(vehicleType.id);
            $('#loc_codeDeteils').val(vehicleType.countryid);
            $('#loc_descriptionDeteils').val(vehicleType.description);
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