$(document).ready(function() {
    var $rows = $('.table>tbody>tr');
    $('#search').keyup(function () {
        var val = '^(?=.*\\b' + $.trim($(this).val()).split(/\s+/).join('\\b)(?=.*\\b') + ').*$',
            reg = RegExp(val, 'i'),
            text;

        $rows.show().filter(function () {
            text = $(this).text().replace(/\s+/g, ' ');
            return !reg.test(text);
        }).hide();

        console.log('done searching');

        if ($rows.find(':visible').length === 0) {
            var string = document.getElementById("tableInfo").innerHTML;
            var replacement = string.replace("Click on a row to view an advert!", "No results");
            document.getElementById("tableInfo").innerHTML = replacement;
        } else
            document.getElementById("tableInfo").innerHTML = "Click on a row to view an advert!";
    });
});
