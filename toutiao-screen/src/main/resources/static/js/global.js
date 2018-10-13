var isStop = false;

/**显示信息*/
function showNotification(title, msg) {
    $.jGrowl(msg, {header: title});
}

function showLoading() {
    $("#loader").fadeIn(300);
    $(".mask").fadeIn(300);
}

function closeLoading() {
    $("#loader").delay(1000).fadeOut(300);
    $(".mask").delay(1000).fadeOut(300);
}