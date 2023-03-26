$('document').ready(function(){
    $('table #editButton').on('click', function(event){
        event.preventDefault();
        // /countries/findById/?id=2
        var href = $(this).attr('href');
        $.get(href, function(country, status){
            $('#idEdit').val(country.id);
            $('#descriptionEdit').val(country.description);
            $('#capitalEdit').val(country.capital);
            $('#codeEdit').val(country.code);
            $('#continentEdit').val(country.continent);
            $('#nationalityEdit').val(country.nationality);
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #deleteButton').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();
    });
});