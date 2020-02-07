$(document).ready(function() {
    var $rows = $('.table>tbody>tr');
    $('#search').keyup(function () {
        /*var val = '^(?=.*\\b' + $.trim($(this).val()).split(/\s+/).join('\\b)(?=.*\\b') + ').*$',
            reg = RegExp(val, 'i'),
            text;

        $rows.show().filter(function () {
            text = $(this).text().replace(/\s+/g, ' ');
            return !reg.test(text);
        }).hide();*/

        $rows.show();

        var searchFor = $(this).val().trim().split(/\s+/).map(function (text) {
            return new RegExp(text, "i");
        });

        if (searchFor.length) {
            if (searchFor) $rows.filter(function () {
                var self = this;
                return searchFor.some(function (regex) {
                    return ![].some.call(self.children, function (cell) {
                        return regex.test(cell.textContent);
                    });
                });
            }).hide();
        }

        console.log('done searching');

        if ($rows.find(':visible').length === 0) {
            var string = document.querySelectorAll("#tableInfo").innerHTML;
            var replacement = string.replace("Click on a row to view an advert!", "No results");
            document.querySelectorAll("#tableInfo").innerHTML = replacement;
        } else
            document.querySelectorAll("#tableInfo").innerHTML = "Click on a row to view an advert!";
    });
});
