$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (vehicleStatus, status){
            $('#VS_IDEdit').val(vehicleStatus.id);
            $('#VS_descriptionEdit').val(vehicleStatus.description);
            $('#VS_detailsedit').val(vehicleStatus.details);
            $('#VS_last_modified_dateedit').val(vehicleStatus.lastModifiedDate.substring(0, 19).replace("T", " "));
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        alert(href);
        $.get(href, function (vehicleStatus, status){
            $('#VS_IDDetails').val(vehicleStatus.id);
            $('#VS_descriptionDetails').val(vehicleStatus.description);
            $('#VS_detailsDetails').val(vehicleStatus.details);
            $('#VS_last_modified_dateDetails').val(vehicleStatus.lastModifiedDate.substring(0, 19).replace("T", " "));
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