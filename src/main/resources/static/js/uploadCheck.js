$(document).ready(function(){
    $("input[type=file]").click(function(){
        $(this).val("");
    });

    $("input[type=file]").change(function(){
        var string = document.getElementById("uploadCV").innerHTML;
        var replacement = string.replace("Upload CV", "Success! ");
        document.getElementById("uploadCV").innerHTML = replacement;
    });
});