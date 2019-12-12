function userExists() {
    $.alert({
        title: 'User already exists',
        content: null
    });
}

function adPosted() {
    $.alert({
        title: 'Advert posted',
        content: 'Your advert is now live!',
        containerFluid: true,
        backgroundDismiss: true
    });
}

function adDrafted() {
    $.alert({
        title: 'Advert saved as draft',
        content: null,
        containerFluid: true,
        backgroundDismiss: true
    });
}

function adEdited() {
    $.alert({
        title: 'Advert edited',
        content: null,
        containerFluid: true,
        backgroundDismiss: true
    });
}

function adProblem(){
    $.alert({
        title: 'Error!',
        content: 'There was an issue with your action',
        containerFluid: true,
        backgroundDismiss: true
    });
}

function appSent() {
    $.alert({
        title: 'Application sent',
        content: null,
        containerFluid: true,
        backgroundDismiss: true
    });
}

function infoSent() {
    $.alert({
        title: 'Information sent',
        content: 'The administrator has been contacted',
        containerFluid: true,
        backgroundDismiss: true
    });
}

//---functional functions---

function delet(id) {
    $.confirm({
        containerFluid: true,
        title: 'Are you sure you want to delete?',
        content: 'This action cannot be undone',
        buttons: {
            no: function() {
                //close
            },
            yes: function() {
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

function log_out() {
    $.confirm({
        containerFluid: true,
        title: 'Are you sure you want to logout?',
        content: null,
        buttons: {
            no: function() {
                //close
            },
            yes: function() {
                $("#logout").click();
            }
        }
    });
}
