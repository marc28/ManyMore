/**
 * 
 */

$( document ).ready(function() {
	getAllFailures();
});



function getAllFailures() {
	var prodId = getParameterByName('email');
	if(prodId==""){
		var cookies = document.cookie; // gets the cookie
		var cookieArray = cookies.split(';');
		for (var i = 0; i < cookieArray.length; i++) {
			var key = cookieArray[i].split('=')[0];
			key = key.trim();
			var value = cookieArray[i].split('=')[1];
			var valuesArray = value.split("---");
			prodId = decodeURI(valuesArray[0]);
		}
	}
	
	$.ajax({
		type : 'GET',
		url : 'rest/tracks?libid='+prodId,
		success : handleResponseJQuery,
		contentType : 'application/json'
	});

}

function handleResponseJQuery(myData) {

	for (var i = 0; i < myData.length; i++) {

		$('#table-body').append(
				"<tr>" + "<td>" + myData[i].trackID + "</td>" 
						+ "<td>"
						+ myData[i].name + "</td>" + "<td>"
						+ myData[i].artist + "</td>" + "<td>"
						+ myData[i].album + "</td>" + "<td>"
						+ myData[i].year + "</td>" + "<td>"
						+ "<td><button onclick='edit(this)'class='btn btn-warning'>Edit</button></td>"
						+ "<td><button onclick='saveme(this)'class='btn btn-success'>Save</button></td>"
						+ "<td><button onclick='deletMe(this)' class='btn btn-danger'>Delete</button></td>"
						+ "</tr>");
	}
	;
}

function edit(me){
	var parent = me.parentNode.parentNode;
	var tdnameNode = parent.children[1]; //get the user name node
	var tdArtistNode = parent.children[2];
	var tdAlbumNode = parent.children[3];
	tdnameNode.setAttribute("contentEditable", true);
	tdArtistNode.setAttribute("contentEditable", true);
	tdAlbumNode.setAttribute("contentEditable", true);
}


function saveme(me){
	var parent = me.parentNode.parentNode;
	var tdnameNode = parent.children[1]; //get the user name node
	var name = tdnameNode.innerHTML; //name value
	var tdArtistNode = parent.children[2];
	var artist = tdArtistNode.innerHTML; //artist value
	var tdAlbumNode = parent.children[3];
	var album = tdAlbumNode.innerHTML; //album value
	
	tdnameNode.setAttribute("contentEditable", false);
	tdArtistNode.setAttribute("contentEditable", false);
	tdAlbumNode.setAttribute("contentEditable", false);
	var trackid = me.parentNode.parentNode.cells[0].textContent;
	alert(trackid + ", " + name + ", " + artist + ", " + album);
	$.ajax({
		  type: 'GET',
		  url: 'rest/tracks/editandsave?tid='+ trackid+"&name=" + name + "&artist="+artist+"&album="+album,
		  success:function(){
			  alert(trackid + " has been changed");
			  location.reload();
		  },
		  contentType: 'application/json'
	});
}


function deletMe(el) {
	   var trackid = el.parentNode.parentNode.cells[0].textContent;
	   $.ajax({
			  type: 'GET',
			  url: 'rest/tracks/tid?tid='+ trackid,
			  success:function(){
				  alert(trackid + " has been deleted");
				  location.reload();
			  },
			  contentType: 'application/json'
		});
	}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


