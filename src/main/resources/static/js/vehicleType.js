$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleType, status){
            $('#VT_IDEdit').val(vehicleType.id);
            $('#VT_descriptionEdit').val(vehicleType.description);
            $('#VT_detailsedit').val(vehicleType.details);
            $('#VT_last_modified_dateedit').val(vehicleType.lastModifiedDate.substring(0, 19).replace("T", " "));
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleType, status){
            $('#VT_IDDetails').val(vehicleType.id);
            $('#VT_descriptionDetails').val(vehicleType.description);
            $('#VT_detailsDetails').val(vehicleType.details);
            $('#VT_last_modified_dateDetails').val(vehicleType.lastModifiedDate.substring(0, 19).replace("T", " "));
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