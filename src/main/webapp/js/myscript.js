userDetail = "";

/*******************************************************************************
 * REGISTER
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
		success : function(data) {
			if(data!=null){
			createCookieReg(20);
			alert("User Added");
			clearAll();
			window.location.href = "insert.html";
			}else{
				alert("Sorry Email Taken");
			}
		}
	});
});

// Create the cookie
function createCookieReg(expiry) {
	cookienamedelete = getCookieLogin();
	document.cookie = cookienamedelete
			+ '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
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
	// alert("Thanks for registering: " + getCookieLogin());

}
/*******************************************************************************
 * LOGIN
 */
$('#loginBtn').click(function() {
	var email = $('#email').val();
	var password = $('#pwd').val();
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
			// alert("DATA: " + data[0]);
			if (data == null) {
				alert("Problem");
				clearAll();
			} else {
				// userDetail = getCookieLogin(email,password);
				// alert(userDetail +" is logged In");
				// a = encodeURIComponent(email);
				// window.location.href = "viewtracks.html?email="+a;
				createCookieReg(20);
				window.location = "viewtracks.html";
			}
		}
	});
});

function clearAll() {
	$('#emailLogin').val('');
	$('#pwdLogin').val('');
}

// Getting the cookie for login
function getCookieLogin() {
	var cookies = document.cookie; // gets the cookie
	if (cookies != "") {
		var cookieArray = cookies.split(';');
		for (var i = 0; i < cookieArray.length; i++) {
			var key = cookieArray[i].split('=')[0];
			key = key.trim();
			var value = cookieArray[i].split('=')[1];
			var valuesArray = value.split("---");
			var userEmail = decodeURI(valuesArray[0]);
			return userEmail;

		}
	}
}

/*******************************************************************************
 * TRACKS.HTML - Get all the tracks
 */

$(document).ready(function() {
	getAllFailures();
	    //$('#table').DataTable();
});

function getAllFailures() {
	// var prodId = getParameterByName('email');
	// if(prodId==""){
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
	// }

	$.ajax({
		type : 'GET',
		url : 'rest/tracks?libid=' + userEmail,
		success : handleResponseJQuery,
		contentType : 'application/json'
	});

}

function handleResponseJQuery(myData) {

	/*for (var i = 0; i < myData.length; i++) {

		$('#table-body')
				.append("<tr>"
							+ "<td>"+ myData[i].trackID	+ "</td>"
								+ "<td>"+ myData[i].name + "</td>"
								+ "<td>"+ myData[i].artist + "</td>"
								+ "<td>"+ myData[i].album + "</td>"
								+ "<td>"+ myData[i].year + "</td>"
								+ "<td>"
								+ "<td><button onclick='edit(this)'class='btn btn-warning'>Edit</button></td>"
								+ "<td><button onclick='saveme(this)'class='btn btn-success'>Save</button></td>"
								+ "<td><button onclick='deletMe(this)' class='btn btn-danger'>Delete</button></td>"
								+ "</tr>");
	}*/
	
	var t = $('#table').DataTable();
	var i;
	for(i =0;i<myData.length;i++){
		t.row.add([ myData[i].trackID, myData[i].name,myData[i].artist,myData[i].album,myData[i].year,"<button onclick='edit(this)'class='btn btn-warning'>Edit</button>","<button onclick='saveme(this)'class='btn btn-success'>Save</button>","<button onclick='deletMe(this)' class='btn btn-danger'>Delete</button>"]).draw();
	}
}

function edit(me) {
	var parent = me.parentNode.parentNode;
	var tdnameNode = parent.children[1]; // get the user name node
	var tdArtistNode = parent.children[2];
	var tdAlbumNode = parent.children[3];
	tdnameNode.setAttribute("contentEditable", true);
	tdArtistNode.setAttribute("contentEditable", true);
	tdAlbumNode.setAttribute("contentEditable", true);
}

function saveme(me) {
	var parent = me.parentNode.parentNode;
	var tdnameNode = parent.children[1]; // get the user name node
	var name = tdnameNode.innerHTML; // name value
	name=name.substring(0,name.length-4);
	var tdArtistNode = parent.children[2];
	var artist = tdArtistNode.innerHTML; // artist value
	var tdAlbumNode = parent.children[3];
	var album = tdAlbumNode.innerHTML; // album value

	tdnameNode.setAttribute("contentEditable", false);
	tdArtistNode.setAttribute("contentEditable", false);
	tdAlbumNode.setAttribute("contentEditable", false);
	var trackid = me.parentNode.parentNode.cells[0].textContent;
	var trackNameAlerted = me.parentNode.parentNode.cells[1].textContent;
	//alert(trackid + ", " + name + ", " + artist + ", " + album);
	$.ajax({
		type : 'GET',
		url : 'rest/tracks/editandsave?tid=' + trackid + "&name=" + name
				+ "&artist=" + artist + "&album=" + album,
		success : function() {
			alert(trackNameAlerted + " has been changed");
			location.reload();
		},
		contentType : 'application/json'
	});
}

function deletMe(el) {
	var trackid = el.parentNode.parentNode.cells[0].textContent;
	var trackNameAlerted = el.parentNode.parentNode.cells[1].textContent;
	$.ajax({
		type : 'GET',
		url : 'rest/tracks/tid?tid=' + trackid,
		success : function() {
			alert(trackNameAlerted + " has been deleted");
			location.reload();
		},
		contentType : 'application/json'
	});
}
