/**
 * Created by User on 4/25/2016.
 */
function displayVals() {
    var multipleValues = $("#multiple").val() || [];
    $("#personsId").val(multipleValues.join( "," ));
}

$( "select" ).change( displayVals );