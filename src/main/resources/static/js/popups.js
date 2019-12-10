function userCreated() {
    $.alert({
        title: 'User has been created',
        autoClose: 'confirm|1000',
    });
}

function delet(id) {
    $.confirm({
        containerFluid: true,
        title: 'Are you sure you want to delete?',
        content: null,
        buttons: {
            cancel: function() {
                //close
            },
            confirm: function() {
                var str = "#delet" + id;
                $(str).click();
            }
        }
    });
}

function updat(id) {
    var str = "#updat" + id;
    $(str).click();
}
