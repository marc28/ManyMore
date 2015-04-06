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

	var t = $('#table').DataTable();
	var i;
	for(i =0;i<500;i++){
		for(var j=0 ;j<4; j++){
		t.row.add([ myData[i][j],myData[i][++j],myData[i][++j],myData[i][++j]]).draw();
		}
	}
}
function getMyValue(){
	var x = document.getElementById("mySelect").selectedIndex;
	var playlistFromBox = document.getElementsByTagName("option")[x].value;
	return playlistFromBox;
}

function test(){
	alert(getMyValue());
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
	var select2 = document.getElementById('mySelect2');
	for (var j = 0; j < myData.length; j++) {
		var opt2 = myData[j];
		var el2 = document.createElement("option");
		el2.textContent = opt2;
		el2.value = opt2;
		select2.appendChild(el2);
	}
	
}
