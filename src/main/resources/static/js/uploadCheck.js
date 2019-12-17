$(document).ready(function(){
    $("input[type=file]").on("change", function(){
        var string = document.getElementById("uploadCV").innerHTML;
        var replacement = string.replace("Upload CV", "Success! ");
        document.getElementById("uploadCV").innerHTML = replacement;
    });

    /*var label = $("#uploadCV");
    var input = $("#cv");
    var form = $("form");

    var otherInput = document.createElement("input");
    otherInput.type = "file";
    otherInput.accept = "application/pdf";
    otherInput.multiple = false;
    otherInput.addEventListener("change", function () {
        input.files = otherInput.files;
    });

    label.on("click",function (e) {
        otherInput.click();
        e.preventDefault();
    });

    form.on("submit", function () {
        if (!form.checkValidity()) {
            setTimeout(function () {
                input.files = otherInput.files;
            }, 1500);
        }
    });*/
});