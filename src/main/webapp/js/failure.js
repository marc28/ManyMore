/**
 * 
 */

$( document ).ready(function() {
	getAllFailures();
});



function getAllFailures() {
	$.ajax({
		type : 'GET',
		url : 'rest/tracks/',
		success : handleResponseJQuery,
		contentType : 'application/json'
	});

}

function handleResponseJQuery(myData) {

	for (var i = 0; i < myData.length; i++) {

		$('#table-body').append(
				"<tr>" + "<td>" + myData[i].trackID + "</td>" + "<td>"
						+ myData[i].name + "</td>" + "<td>"
						+ myData[i].artist + "</td>" + "<td>"
						+ myData[i].album + "</td>" + "<td>"
						+ myData[i].year + "</td>" + "<td>"
						+ "</tr>");
	}
	;
}