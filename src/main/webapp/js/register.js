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
	    },
	    success: function() {
	    	alert("User Added");
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
	    },
	    success: function(data) {
	    	if(data == null){
	    		alert("Problem");
	    	}else{
	    		alert("Logged In");
	    	}
	    }
	});
});