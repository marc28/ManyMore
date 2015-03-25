/**
 * 
 */

/*$('#showPlaylists').click(function(){
	getTheInfo();
});*/
$( document ).ready(function() {
	getTheInfo();
});

function getTheInfo(){
	var playId = $('#playID').val();
	
	$.ajax({
		  type: 'GET',
		  url: 'rest/playlist/pid?PID='+ playId,
		  success: handleResponseJQuery,
		  contentType: 'application/json'
	});
}

function handleResponseJQuery(myData) {
	
	for (var i = 0; i < myData.length; i++) {
	 $('#table-body').append(
			 "<tr>" + "<td>" + myData[i].playlistId + "</td>" + "<td>"
				+ myData[i].name + "</td>" + "<td>"
				+ "</tr>");
	}

}