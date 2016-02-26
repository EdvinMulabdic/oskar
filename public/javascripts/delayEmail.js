/**
 * Created by User on 2/15/2016.
 */
/** calls a function every day to check if there is any person with certificate that expires **/
$(document).ready(setInterval(function(){
    $.ajax({
        type: "POST",
        url: "/"
    }).success(function(response) {

    });
}, 100000000));
