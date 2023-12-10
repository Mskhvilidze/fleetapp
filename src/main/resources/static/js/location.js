$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (location, status){
            $('#loc_idEdit').val(location.id);
            $('#loc_addressEdit').val(location.address);
            $('#loc_codeEdit').val(location.city);
            $('#loc_descriptionEdit').val(location.description);
            $('#loc_detailsEdit').val(location.details);
            $('#loc_ClEdit').val(location.country.id);
            $('#loc_SlEdit').val(location.state.id);
            $('#loc_ddlCountryEdit').val(location.country.id);
            $('#loc_ddlStateEdit').val(location.state.id);
        });
        $('.tooltip-r').tooltip();
        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (location, status){
            $('#local_idDetails').val(location.id);
            $('#loc_addressDeteils').val(location.address);
            $('#loc_cityDeteils').val(location.city);
            $('#loc_codeDeteils').val(location.countryid);
            $('#loc_descriptionDeteils').val(location.description);
            $('#loc_detailsDetails').val(location.details);
            $('#loc_stateIdDetails').val(location.stateid);
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

    $("#loc_ddlCountryEdit").on("change", function () {
        var selectedOption = $(this).find("option:selected");
        var countryId = selectedOption.data("countryid");
        $("#loc_ClEdit").val(countryId);
    });

    $("#loc_ddlStateEdit").on("change", function () {
        var selectedOption = $(this).find("option:selected");
        var stateId = selectedOption.data("stateid");
        $("#loc_SlEdit").val(stateId);
    });
})