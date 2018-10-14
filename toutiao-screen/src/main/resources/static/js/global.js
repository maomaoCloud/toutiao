var _sign_t = 0;
var _sign_i = 0;
var _t = 0;

var __matchId = null;
var __token = null;

function getMatchId() {
    return __matchId;
}

function getToken() {
    return __token;
}

function updateMatchId(_matchId) {
    __matchId = _matchId;
}

function updateToken(_token) {
    __token = _token;
}

function _destory() {
    clearInterval(_t);
    clearInterval(_sign_i);
    clearTimeout(_sign_t);
}

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

function getClientHeight() {
    var clientHeight = 0;
    if (document.body.clientHeight && document.documentElement.clientHeight) {
        var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;
    }
    else {
        var clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;
    }
    return clientHeight;
}

function requestFullScreen() {
    var element = document.getElementsByTagName("html")[0];
    var requestMethod = element.requestFullScreen || element.webkitRequestFullScreen || element.mozRequestFullScreen || element.msRequestFullScreen;
    if (requestMethod) {
        requestMethod.call(element);
    } else if (typeof window.ActiveXObject !== "undefined") {
        var wscript = new ActiveXObject("WScript.Shell");
        if (wscript !== null) {
            wscript.SendKeys("{F11}");
        }
    }
}

function hideNav() {
    $("#navbar").addClass("hide");
    $("#content").addClass("full-screen");
    $("#body").addClass("body_full");
}

function showNav() {
    $("#navbar").removeClass("hide");
    $("#content").removeClass("full-screen");
    $("#body").removeClass("body_full");
}

function _full_screen() {
    hideNav();
    requestFullScreen();
}

