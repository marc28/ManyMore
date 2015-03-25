/**
 * 
 */

$( document ).ready(function() {
	getAllFailures();
});



function getAllFailures() {
	$.ajax({
		type : 'GET',
		url : 'rest/tracks/',
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
	var tdname = parent.children[1]; //get the user name node
	tdname.setAttribute("contentEditable", true);
}


function saveme(me){
	var parent = me.parentNode.parentNode;
	var tdname = parent.children[1]; //get the user name node
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


