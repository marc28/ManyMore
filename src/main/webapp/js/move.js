/**
 * 
 */
$(document).ready(function() {
	getAllPlayListNames();
	//getAllTrackNamesOnly();
});

function getAllPlayListNames() {

	$.ajax({
		type : 'GET',
		url : 'rest/playlist/namesdropdown',
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

function insert(){
	box1Value = document.getElementById("searchTrack").value;
	//box2.value = document.getElementById('insertBtn').value;
	var x = document.getElementById("mySelect").selectedIndex;
	alert(document.getElementsByTagName("option")[x].value);
	//alert(x);
}
//'rest/playlist/tracknamesdropdown'
$(function(){
	  $("#searchTrack").autocomplete({
		  source: function (request, response) {
		       // var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
		        $.ajax({
		            url: "rest/playlist/tracknamesdropdown",
		            dataType: "json",
		            success: function (data) {
		                response($.map(data, function(v,i){
		                  //  var text = v.name;
		                    //if ( text && ( !request.term || matcher.test(text) ) ) {
		                        return {
		                                label: v.name
		                                
		                               };
		                    //}
		                }));
		            }
		        });
		    }
	});
});







