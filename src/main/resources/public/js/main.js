function myFunction() {
    document.getElementById("ddownList").classList.toggle("show");
}

window.onclick = function(e) {
    if (!e.target.matches('.ddown')) {
        var ddownList = document.getElementById("ddownList");
        if (ddownList.classList.contains('show')) {
            ddownList.classList.remove('show');
        }
    }
}