$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (jobTitle, status){
            $('#loc_idEdit').val(jobTitle.id);
            $('#loc_descriptionEdit').val(jobTitle.description);
            $('#loc_detailsEdit').val(jobTitle.details);
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (jobTitle, status){
            $('#local_idDetails').val(jobTitle.id);
            $('#loc_descriptionDeteils').val(jobTitle.description);
            $('#loc_detailsDetails').val(jobTitle.details);
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