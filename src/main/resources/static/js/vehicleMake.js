$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleMake, status){
            $('#loc_idEdit').val(vehicleMake.id);
            $('#loc_descriptionEdit').val(vehicleMake.description);
            $('#loc_detailsEdit').val(vehicleMake.details);
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleMake, status){
            $('#local_idDetails').val(vehicleMake.id);
            $('#loc_descriptionDeteils').val(vehicleMake.description);
            $('#loc_detailsDetails').val(vehicleMake.details);
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