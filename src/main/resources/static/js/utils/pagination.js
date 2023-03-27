$('document').ready(function(){
    //select Topics
    let tbody = document.querySelector("#tbody");
    let tr = tbody.getElementsByTagName("tr");
    let arrayTr = [];
    for (let i = 0; i < tr.length; i++) {
        arrayTr.push(tr[i]);
    }
    let liPage;
    let aPage;
    let nextPage = 1;
    let value = 1;
    if(arrayTr.length >= 2){
        value = test();
        alert(value);
        let neil = document.getElementsByClassName("page-item");
        for (let i = 0; i < neil.length; i++) {
            neil[i].remove();
        }
        for (let i = 0; i < arrayTr.length; i++) {
            tbody.appendChild(arrayTr[i]);
        }
        if (arrayTr.length >= 2) {
            const nofPage = Math.ceil(arrayTr.length / value);
            //pagination nach links, daher nextPage --
            prev().onclick = ev => {
                if (nextPage > 1){
                    nextPage--;
                    paginateTable(nextPage, value, arrayTr, tbody);
                }
            }
            //Pagination wird erstellt
            for (let i = 1; i <= nofPage; i++) {
                liPage = document.createElement("li");
                liPage.className = "page-item";
                aPage = document.createElement("a");
                aPage.href = "#";
                aPage.setAttribute("data-page", "" + i);
                liPage.appendChild(aPage);
                aPage.innerHTML = "" + i;
                $(".pagination").append(liPage);
                aPage.onclick = ev => {
                    let $target = $(ev.target);
                    let x = $target.attr("data-page");
                    nextPage = x;
                    paginateTable(x, value, arrayTr, tbody);
                }
            }
            next().onclick = ev => {
                if (nextPage < nofPage){
                    nextPage++;
                    paginateTable(nextPage, value, arrayTr, tbody);
                }
            }
            paginateTable(1, value, arrayTr, tbody);
        }
    }
});

function paginateTable(x, value, arrayTr, tbody){
    tbody.innerHTML = "";
    x--;
    let start = value * x;
    let end = start + parseInt(value);
    let page = arrayTr.slice(start, end);
    for (let j = 0; j < page.length; j++) {
        tbody.appendChild(page[j]);
    }
}

function prev(){
            let prevPage = document.createElement("li");
            let aPrev = document.createElement("a");
            $(".pagination").append(prevPage);
            aPrev.innerHTML = "&#139";
            prevPage.appendChild(aPrev);
            return aPrev;
}

function next(){
           let prevPage = document.createElement("li");
           let aNext = document.createElement("a");
           $(".pagination").append(prevPage);
           aNext.innerHTML = "&#155";
           prevPage.appendChild(aNext);
           return aNext;
}


function test(){
            let value = $("#selectTopic option:selected").val();
            return value;
}