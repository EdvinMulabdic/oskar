/**
 * Created by User on 2/11/2016.
 */
function displayVals() {
    var multipleValues = $("#multiple").val() || [];
    $("#certificateId").val(multipleValues.join( "," ));
}

$( "select" ).change( displayVals );
