/**
 * Created by User on 4/24/2016.
 */
function displayVals() {
    var multipleValues = $("#multiple").val() || [];
    $("#personsId").val(multipleValues.join( "," ));
}

$( "select" ).change( displayVals );