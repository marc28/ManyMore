/**
 * 
 */

$(document).ready(function(){
	getTrackListInfo();
});

function getTrackListInfo() {

	$.ajax({
		type : 'GET',
		url : 'rest/playlist/playlistnames',
		success : getBackPlaylistInfo,
		contentType : 'application/json'
	});
}

function getBackPlaylistInfo(myData) {

	for (var i = 0; i < myData.length; i++) {

		$('#playlistTableNoTracks').append(
				"<tr>" + "<td>" + myData[i].playlistId + "</td>" 
						+ "<td>"+ myData[i].name + "</td>" + "<td>"
						+ "<td><button onclick='edit(this)'class='btn btn-warning'>Edit</button></td>"
						+ "<td><button onclick='saveme(this)'class='btn btn-success'>Save</button></td>"
						+ "<td><button onclick='deletMe(this)' class='btn btn-danger'>Delete</button></td>"
						+ "</tr>");
	}
	;
}

function edit(me){
	var parent = me.parentNode.parentNode;
	var tdPlaylistNameNode = parent.children[1]; //get the user name node
	tdPlaylistNameNode.setAttribute("contentEditable", true);
	
}

function saveme(me){
	var parent = me.parentNode.parentNode;
	var tdnameNode = parent.children[1]; //get the playlist name node
	var name = tdnameNode.innerHTML; //playlist name value
	tdnameNode.setAttribute("contentEditable", false);
	var playlistid = me.parentNode.parentNode.cells[0].textContent;
	
	$.ajax({
		  type: 'GET',
		  url: 'rest/playlist/editandsave?pid='+ playlistid+"&name=" + name,
		  success:function(){
			  alert(playlistid + " has been changed");
			  location.reload();
		  },
		  contentType: 'application/json'
	});
}

function deletMe(el) {
	   var playlistid = el.parentNode.parentNode.cells[0].textContent;
	  $.ajax({
			  type: 'GET',
			  url: 'rest/playlist/pid?pid='+ playlistid,
			  success:function(){
				  alert(playlistid + " has been deleted");
				  location.reload();
			  },
			  contentType: 'application/json'
		});
	}