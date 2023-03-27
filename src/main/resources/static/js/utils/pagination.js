$(document).ready(function(){
    let tbody = document.querySelector("#tbody");
    let tr = tbody.getElementsByTagName("tr");
    let arrayTr = [];
    for (let i = 0; i < tr.length; i++) {
        arrayTr.push(tr[i]);
    }
    pagination(tbody, tr, arrayTr);

    $("#selectTopic").change(function () {
        let neil = document.getElementsByClassName("page-item");
        Array.from(neil).forEach((el) => el.remove());
        pagination(tbody, tr, arrayTr);
    });
});

function pagination(tbody, tr, arrayTr) {
    //select Topics
    let liPage;
    let aPage;
    let nextPage = 1;
    let value = getSelectedValue();
    let pageSize = parseInt(value);
    // entferne alte Pagination-Elemente
    //$(".pagination .page-item, .pagination .prev, .pagination .next").remove();
    Array.from(document.getElementsByClassName("page-item")).forEach((el) =>
        el.remove()
    );
    if (arrayTr.length >= 2) {
        let neil = document.getElementsByClassName("page-item");
        for (let i = 0; i < neil.length; i++) {
            neil[i].remove();
        }
        for (let i = 0; i < arrayTr.length; i++) {
            tbody.appendChild(arrayTr[i]);
        }
        if (arrayTr.length >= pageSize) {
            const nofPage = Math.ceil(arrayTr.length / pageSize);
            //pagination nach links, daher nextPage --
            prev().onclick = ev => {
                if (nextPage > 1){
                    nextPage--;
                    paginateTable(nextPage, pageSize, arrayTr, tbody);
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
                    paginateTable(x, pageSize, arrayTr, tbody);
                }
            }
            next().onclick = ev => {
                if (nextPage < nofPage){
                    nextPage++;
                    paginateTable(nextPage, pageSize, arrayTr, tbody);
                }
            }
            paginateTable(1, pageSize, arrayTr, tbody);
        }
    }
}

function paginateTable(x, value, arrayTr, tbody){
    nextPage = x;
    tbody.innerHTML = "";
    let start = (x - 1) * value;
    let end = Math.min(start + value, arrayTr.length);
    let page = arrayTr.slice(start, end);
    for (let j = 0; j < page.length; j++) {
        tbody.appendChild(page[j]);
    }
}

function prev(){
    let prevPage = document.createElement("li");
    prevPage.className = "page-item";
    let aPrev = document.createElement("a");
    aPrev.innerHTML = "&#139";
    prevPage.appendChild(aPrev);
    $(".pagination").append(prevPage);
    return aPrev;
}

function next(){
    let aNextPage = document.createElement("li");
    let aNext = document.createElement("a");
    aNextPage.className = "page-item";
    aNext.innerHTML = "&#155";
    aNextPage.appendChild(aNext);
    $(".pagination").append(aNextPage);
    return aNext;
}

function getSelectedValue(){
    let value = $("#selectTopic option:selected").val();
    return value;
}