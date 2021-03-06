/**
 * 
 */
$(document).ready(function() {
	getAllPlayListNames();
});

function getAllPlayListNames() {
	var userEmail = "";
	var cookies = document.cookie; // gets the cookie
	var cookieArray = cookies.split(';');
	for (var i = 0; i < cookieArray.length; i++) {
		var key = cookieArray[i].split('=')[0];
		key = key.trim();
		var value = cookieArray[i].split('=')[1];
		var valuesArray = value.split("---");
		userEmail = decodeURI(valuesArray[0]);
	}

	$.ajax({
		type : 'GET',
		url : 'rest/playlist/namesdropdown?libid=' + userEmail,
		success : handleResponseForDropDown,
		contentType : 'application/json'
	});
}

function handleResponseForDropDown(myData) {
	var select = document.getElementById('mySelect');
	for (var i = 0; i < myData.length; i++) {
		var opt = myData[i];
		var el = document.createElement("option");
		el.textContent = opt;
		el.value = opt;
		select.appendChild(el);
	}
}

function insert() {
	var userEmail = "";
	var cookies = document.cookie; // gets the cookie
	var cookieArray = cookies.split(';');
	for (var i = 0; i < cookieArray.length; i++) {
		var key = cookieArray[i].split('=')[0];
		key = key.trim();
		var value = cookieArray[i].split('=')[1];
		var valuesArray = value.split("---");
		userEmail = decodeURI(valuesArray[0]);
	}
	
	box1Value = document.getElementById("searchTrack").value;
	var x = document.getElementById("mySelect").selectedIndex;
	var playlistFromBox = document.getElementsByTagName("option")[x].value;
	if (box1Value != "" && playlistFromBox != "") {
		$.ajax({
			type : 'GET',
			url : 'rest/playlist/insertingTrackToPlaylist?libid=' + userEmail+'&trackname='+ box1Value + "&playlist=" + playlistFromBox,
			success : function() {
				alert(box1Value + " has been moved to the Playlist "
						+ playlistFromBox);
			},
			contentType : 'application/json'
		});
	} else {
		alert("Please choose both a Track and PlayList");
	}

}

function removeMe() {
	var userEmail = "";
	var cookies = document.cookie; // gets the cookie
	var cookieArray = cookies.split(';');
	for (var i = 0; i < cookieArray.length; i++) {
		var key = cookieArray[i].split('=')[0];
		key = key.trim();
		var value = cookieArray[i].split('=')[1];
		var valuesArray = value.split("---");
		userEmail = decodeURI(valuesArray[0]);
	}
	
	box1Value = document.getElementById("searchTrack").value;
	var x = document.getElementById("mySelect").selectedIndex;
	var playlistFromBox = document.getElementsByTagName("option")[x].value;
	if (box1Value != "" && playlistFromBox != "") {
		$.ajax({
			type : 'GET',
			url : 'rest/playlist/removeTrackFromPlaylist?libid=' + userEmail+'&trackname='+ box1Value + "&playlist=" + playlistFromBox,
			success : function() {
				alert(box1Value + " has been removed to the Playlist "
						+ playlistFromBox);
			},
			contentType : 'application/json'
		});
	} else {
		alert("Please choose both a Track and PlayList");
	}
}

// 'rest/playlist/tracknamesdropdown'
$(function() {
	var userEmail = "";
	var cookies = document.cookie; // gets the cookie
	var cookieArray = cookies.split(';');
	for (var i = 0; i < cookieArray.length; i++) {
		var key = cookieArray[i].split('=')[0];
		key = key.trim();
		var value = cookieArray[i].split('=')[1];
		var valuesArray = value.split("---");
		userEmail = decodeURI(valuesArray[0]);
	}
	$("#searchTrack").autocomplete(
			{
				source : function(request, response) {
					var title = document.getElementById('searchTrack').value;
					$.ajax({
						url : "rest/playlist/tracknamesdropdown?libid="
								+ userEmail + "&title=" + title,
						dataType : "json",
						success : function(data) {
							response($.map(data, function(v, i) {
								return {
									label : v.name
								};
							}));
						} // end of success
					});
				}
			});
});



