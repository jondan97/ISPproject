$(document).ready(function(){
    $("input[type=file]").click(function(){
        $(this).val("");
    });

    $("input[type=file]").change(function(){
        var string = document.getElementById("uploadCV").innerHTML;
        var replacement = string.replace("Upload CV ", "CV Uploaded! ");
        document.getElementById("uploadCV").innerHTML = replacement;
    });
});