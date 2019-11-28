$(document).ready(function() {
    var $rows = $('#table tr');
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
            $("#noResults").show();
        } else {
            $("#noResults").hide();
        }
    });
});
