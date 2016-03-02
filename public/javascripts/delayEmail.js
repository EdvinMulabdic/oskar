/**
 * Created by User on 2/15/2016.
 */
/** calls a function every day to check if there is any person with certificate that expires **/


var now = new Date();
var millisTill10 = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 18, 01, 0, 0);

$(document).ready(setInterval(function() {

    $.ajax({
        type: "POST",
        url: "/"
    }).success(function(response) {

    });
}, millisTill10.getTime()));

function sendManualy() {
    $.ajax({
        type: "POST",
        url: "/"
    }).success(function(response) {
        location.reload();
    });

}