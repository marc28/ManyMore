/**
 * 
 */
/*
 * $(document).ready(function() {
 * document.getElementById('sendCookieInfo').style.visibility = 'hidden'; });
 */

function validate() {

	var fup = document.getElementById("uploadFile");
	var fileName = fup.value;
	var ext = fileName.substring(fileName.lastIndexOf('.') + 1);

	if (ext == "xml") {
		return true;
	} else {
		alert("File must be of type xml");
		return false;
	}

}

$('#sendCookieInfo').click(function() {
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
	if (validate()) {
		$.ajax({
			type : 'POST',
			url : "rest/files/import?useremail=" + userEmail,
			contentType : "application/xml",
			dataType : "xml",
			success : function() {
				alert("Sent");
				window.location.href = "viewtracks.html";
			},
			error : function() {
				alert("Error");
				clearAll();
			}

		});
	}
});

function test() {
	alert("Hi");
}