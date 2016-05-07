/**
 * Created by User on 2/15/2016.
 */
/** calls a function every day to check if there is any person with certificate that expires **/


var now = new Date();
console.log("NOW " + now)
//var millisTill10 = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 18,01, 0, 0);
var millisTill10 = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 10, 0, 0, 0) - now;
if (millisTill10 < 0) {
    millisTill10 += 86400000; // it's after 10am, try 10am tomorrow.
}
$(document).ready(setInterval(function(){

    $.ajax({
        type: "POST",
        url: "/"
    }).success(function(response) {

    });
    console.log("NOW " + now)
}, millisTill10));



function sendManualy(){
    $.ajax({
        type: "POST",
        url: "/"
    }).success(function(response) {
        location.reload();
    });

}