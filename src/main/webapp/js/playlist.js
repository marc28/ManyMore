/**
 * 
 */
$(document).ready(function(){
	getAllPlayListTrackInfo();
});

function getAllPlayListTrackInfo() {
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
		url : 'rest/playlist?libid='+userEmail,
		success : handleResponseJQuery3,
		contentType : 'application/json'
	});
}

function handleResponseJQuery3(myData) {

	/*
	 * for (var i = 0; i < 200; i++) { $('#table-body').append("<tr>");
	 * for(var j = 0;j<4;j++){ $('#table-body').append( "<td>" + myData[i][j] + "</td>"); }
	 * $('#table-body').append("<td><button onclick='edit(this)'class='btn
	 * btn-warning'>Edit</button></td>"); $('#table-body').append("<td><button
	 * onclick='saveme(this)'class='btn btn-success'>Save</button></td>");
	 * $('#table-body').append("<td><button onclick='deletMe(this)' class='btn
	 * btn-danger'>Delete</button></td>"); $('#table-body').append("</tr>"); }
	 */
	var t = $('#table').DataTable();
	var i;
	for(i =0;i<500;i++){
		for(var j=0 ;j<4; j++){
		t.row.add([ myData[i][j],myData[i][++j],myData[i][++j],myData[i][++j]]).draw();
		}
	}
}

function edit(me){
	var parent = me.parentNode.parentNode;
	var tdname = parent.children[1]; // get the user name node
	tdname.setAttribute("contentEditable", true);
}


function saveme(me){
	var parent = me.parentNode.parentNode;
	var tdname = parent.children[1]; // get the user name node
	tdname.setAttribute("contentEditable", false);
	alert(tdname.innerHTML);
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
 * myData[i].playlistId + "</td>" + "<td>" + myData[i].name + "</td>" + "<td>" + "</tr>"); } }
 */