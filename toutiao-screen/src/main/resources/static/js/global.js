var _sign_t = 0;
var _sign_i = 0;
var _t = 0;

var hasGroupName = false;

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

function getHasGroupName() {
    return hasGroupName;
}

function setHasGroupName(_hasGroupName) {
    hasGroupName = _hasGroupName;
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
    } else {
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

function _exit_full_screen() {
    var exitMethod = document.exitFullscreen || //W3C
        document.mozCancelFullScreen || //Chrome等
        document.webkitExitFullscreen || //FireFox
        document.webkitExitFullscreen; //IE11
    if (exitMethod) {
        exitMethod.call(document);
    } else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
        var wscript = new ActiveXObject("WScript.Shell");
        if (wscript !== null) {
            wscript.SendKeys("{F11}");
        }
    }
    showNav();
}

function setCookie(c_name, value, expiredays) {
    var exdate = new Date()
    exdate.setDate(exdate.getDate() + expiredays)
    document.cookie = c_name + "=" + escape(value) +
        ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString()) + ";path=/";
}

function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1) c_end = document.cookie.length
            return unescape(document.cookie.substring(c_start, c_end))
        }
    }
    return ""
}

function is_mobile(phone) {
//	var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
    //var pattern =/^([0-9-]+)$/;   //验证手机号码之前所用
    var pattern = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
    //   var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;  //座机验证规则
    return pattern.test(phone);
}


function is_idCard(idCard) {
    var regIdNo = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    return regIdNo.test(idCard);
}
