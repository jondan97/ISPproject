/* Function for the animation */
function custAlert(text) {
    var topVar = 0;
    var increment = 1/10;

    /* animation function */
    $('.notifier').html(text);
    $('.notifierBox').fadeIn();
    var id = setInterval(frame, 50);
    function frame() {
        if (topVar === 0.8 || topVar > 0.8) {
            clearInterval(id);
            setTimeout(function() {
                $('.notifierBox').fadeOut(250);
            }, 2000)
        } else {
            topVar += increment;
            $('.notifierBox').css('top', (topVar + 'vh'));
        }
    }
}