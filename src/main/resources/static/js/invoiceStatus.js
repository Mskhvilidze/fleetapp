$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (invoiceStatus, status){
            $('#loc_idEdit').val(invoiceStatus.id);
            $('#loc_descriptionEdit').val(invoiceStatus.description);
            $('#loc_detailsEdit').val(invoiceStatus.details);
            alert(states.name);
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (invoiceStatus, status){
            $('#local_idDetails').val(invoiceStatus.id);
            $('#loc_descriptionDeteils').val(invoiceStatus.description);
            $('#loc_detailsDetails').val(invoiceStatus.details);
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