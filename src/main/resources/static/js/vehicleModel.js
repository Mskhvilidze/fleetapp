$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleModel, status){
            $('#VMO_IDEdit').val(vehicleModel.id);
            $('#VMO_descriptionEditEdit').val(vehicleModel.description);
            $('#VMO_detailsedit').val(vehicleModel.details);
            $('#VMO_last_modified_dateedit').val(vehicleModel.lastModifiedDate.substring(0, 19).replace("T", " "));

        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleModel, status){
            $('#VMO_IDEDetails').val(vehicleModel.id);
            $('#VMO_descriptionDetails').val(vehicleModel.description);
            $('#VMO_detailsDetails').val(vehicleModel.details);
            $('#VMO_last_modified_dateDetails').val(vehicleModel.lastModifiedDate.substring(0, 19).replace("T", " "));
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