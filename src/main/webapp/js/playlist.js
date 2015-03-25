/**
 * 
 */
$(document).ready(function(){
	getAllPlayListTrackInfo();
});

function getAllPlayListTrackInfo() {

	$.ajax({
		type : 'GET',
		url : 'rest/playlist',
		success : handleResponseJQuery3,
		contentType : 'application/json'
	});
}

function handleResponseJQuery3(myData) {

	for (var i = 0; i < myData.length; i++) {
		$('#table-body').append("<tr>");
		for(var j = 0;j<5;j++){
			$('#table-body').append(
					"<td>" + myData[i][j] + "</td>");
		}
		$('#table-body').append("</tr>");
	}
}

/*
 * $('#showPlaylists').click(function(){ getTheInfo(); });
 */
/*
 * $( document ).ready(function() { getTheInfo(); });
 * 
 * function getTheInfo(){ var playId = $('#playID').val();
 * 
 * $.ajax({ type: 'GET', url: 'rest/playlist/pid?PID='+ playId, success:
 * handleResponseJQuery, contentType: 'application/json' }); }
 * 
 * function handleResponseJQuery(myData) {
 * 
 * for (var i = 0; i < myData.length; i++) { $('#table-body').append( "<tr>" + "<td>" +
 * myData[i].playlistId + "</td>" + "<td>" + myData[i].name + "</td>" + "<td>" + "</tr>"); }
 *  }
 */