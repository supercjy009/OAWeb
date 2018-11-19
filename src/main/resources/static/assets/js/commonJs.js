// ajaxUri = "http://47.99.47.49:8080";
 ajaxUri = "http://localhost:8080";
workPayInit = false;
var partNow = "";
var full = 'full-320';

function getRandomString(len) {
    len = len || 32;
    var $chars = 'abcdefghijklmnopqrstuvwxyz0123456789'; // any letter uppercase / lowercase, any number
    var maxPos = $chars.length;
    var Random = '';
    for (i = 0; i < len; i++) {
        Random += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return Random;
}