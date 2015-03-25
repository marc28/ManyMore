/**
 * 
 */
$('#addUserBut').click(function(){
	var $email = $('#email').val();
	var $password = $('#pwd').val();
	

	
	$.ajax({
		type:'POST',
	    url: "rest/user/addUser",
	    contentType: "application/json",
	    data:JSON.stringify({
            "email": $email,
            "password": $password,
        }),
	    error:function(){
	    	alert("Error");
	    	clearAll();
	    },
	    success: function() {
	    	alert("User Added");
	    	clearAll();
	    	window.location.href="login.html";
	    }
	});
});

$('#loginBtn').click(function(){
	var email =$('#emailLogin').val();
	var password = $('#pwdLogin').val();
	$.ajax({
		type:'POST',
	    url: "rest/user/auth",
	    contentType: "application/json",
	    data:JSON.stringify({
            "email": email,
            "password": password,
        }),
	    error:function(){
	    	alert("Problem");
	    	clearAll();
	    },
	    success: function(data) {
	    	if(data == null){
	    		alert("Problem");
	    		clearAll();
	    	}else{
	    		alert("Logged In");
	    		window.location.href ="viewtracks.html";
	    	}
	    }
	});
});

function clearAll(){
	$('#emailLogin').val('');
	$('#pwdLogin').val('');
}
