/**
 * 
 */

$('#addUserBut').click(function() {
	var $email = $('#email').val();
	var $password = $('#pwd').val();

	$.ajax({
		type : 'POST',
		url : "rest/user/addUser",
		contentType : "application/json",
		data : JSON.stringify({
			"email" : $email,
			"password" : $password,
		}),
		error : function() {
			alert("Error");
			clearAll();
		},
		success : function() {
			alert("User Added");
			clearAll();
			window.location.href = "insert.html";
		}
	});
});

$('#loginBtn').click(function() {
	var email = $('#emailLogin').val();
	var password = $('#pwdLogin').val();
	$.ajax({
		type : 'POST',
		url : "rest/user/auth",
		contentType : "application/json",
		data : JSON.stringify({
			"email" : email,
			"password" : password,
		}),
		error : function() {
			alert("Problem");
			clearAll();
		},
		success : function(data) {
			if (data == null) {
				alert("Problem");
				clearAll();
			} else {
				alert("Logged In");
				window.location.href = "viewtracks.html";
			}
		}
	});
});

function clearAll() {
	$('#emailLogin').val('');
	$('#pwdLogin').val('');
}

// This is called when you click the registration button
function createCookieReg(expiry) {

	var d = new Date();
	d.setTime(d.getTime() + (expiry * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	var userEmail = document.getElementById("email").value;
	var password = document.getElementById("pwd").value;

	var seperator = "---"; // neede to break up the string array
	var valuePairString = userEmail + seperator + password + ";" + expires
			+ ";";
	valuePairString = encodeURI(valuePairString);
	// cookie should be eg: {marc=1---2---3---4---5}
	document.cookie = "" + userEmail + "=" + valuePairString + ";";
	alert("Thanks for registering: " + getCookieLogin());

}

// Getting the cookie for login
function getCookieLogin() {
	var cookies = document.cookie; // gets the cookie
	var user = $('#emailLogin').val();
	var passw = $('#pwdLogin').val();
	if (user != "" && passw != "") {
		if (cookies.indexOf(user) >= 0) {
			var cookieArray = cookies.split(';');
			for (var i = 0; i < cookieArray.length; i++) {
				var key = cookieArray[i].split('=')[0];
				key = key.trim();
				var value = cookieArray[i].split('=')[1];
				var valuesArray = value.split("---");
				var userEmail = decodeURI(valuesArray[0]);
				return userEmail;
			}

		} else {
			alert("Sorry, not registered");
		}

	} else {
		alert("You must enter details for User name and Password to login");
	}
}
