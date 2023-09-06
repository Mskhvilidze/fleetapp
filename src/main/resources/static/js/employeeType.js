$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (employeeType, status){
            $('#loc_idEdit').val(employeeType.id);
            $('#loc_descriptionEdit').val(employeeType.description);
            $('#loc_detailsEdit').val(employeeType.details);
            alert(states.name);
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (employeeType, status){
            $('#local_idDetails').val(employeeType.id);
            $('#loc_descriptionDeteils').val(employeeType.description);
            $('#loc_detailsDetails').val(employeeType.details);
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