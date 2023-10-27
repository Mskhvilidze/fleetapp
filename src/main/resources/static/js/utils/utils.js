function loadFlashAttribute() {
    const text = document.getElementById('flash').innerText;
    const notExist = text.substring(0, 4);
    if (notExist === "Nope") {
        document.getElementById('flash').className = 'alert alert-danger';
    }
    if (text.substring(0, 3) === "Yup") {
        document.getElementById('flash').className = 'alert alert-success';
    }
}


function updateDetailsFromSelect(x) {
    var selectedOption = x.options[x.selectedIndex];
    var continent = selectedOption.getAttribute('data-continent');
    var capital = selectedOption.getAttribute("data-capital");
    var nationality = selectedOption.getAttribute("data-nationality");

    var inputContinent = document.getElementById("continentDetails");
    var inputNationality = document.getElementById("nationalityDetails");
    var inputCapital = document.getElementById("capitalDetails");

    inputContinent.value = continent;
    inputNationality.value = nationality;
    inputCapital.value = capital;
}