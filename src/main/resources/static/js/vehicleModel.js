$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleModel, status){
            $('#loc_idEdit').val(vehicleModel.id);
            $('#loc_descriptionEdit').val(vehicleModel.description);
            $('#loc_detailsEdit').val(vehicleModel.details);
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleModel, status){
            $('#local_idDetails').val(vehicleModel.id);
            $('#loc_descriptionDeteils').val(vehicleModel.description);
            $('#loc_detailsDetails').val(vehicleModel.details);
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