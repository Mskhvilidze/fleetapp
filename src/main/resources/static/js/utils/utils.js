function loadFlashAttribute(){
    const text = document.getElementById('flash').innerText;
    const notExist = text.substring(0, 4);
    if(notExist === "Nope"){
        document.getElementById('flash').className = 'alert alert-danger';
    }
    if(text.substring(0,3) === "Yup"){
        document.getElementById('flash').className = 'alert alert-success';
    }
}