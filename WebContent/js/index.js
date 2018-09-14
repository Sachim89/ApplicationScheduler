/** Considering User1 is PAtient and User2 is Doctor **/
$(document).ready(function() {
	//Login validation
    $("#loginButton").click(function() {
        validate();
    });
    //Date Picker function
    $("#datepicker").datepicker({
    	minDate: 0
    });  
    //Converting time to 24 hour format
    $(function(){     
        var d = new Date(),        
            h = d.getHours(),
            m = d.getMinutes();
	        if(h < 10) h = '0' + h; 
	        if(m < 10) m = '0' + m; 
	        $('input[type="time"][value="now"]').each(function(){ 
	          $(this).attr({'value': h + ':' + m});
	        });
    });
    //Appointment Validation
    $("#submit").click(function(){
       apptValidate();
    });
    //Viewing Appointments
    $('#aptBook').click(function(){
    	docAppt();
    });
    //Logout
	$("#logout").click(function() {
		logout();
	});
});
/** Login Validate **/ 
function validate() {
	var name = $("#name").val();
	var password = $("#password").val();
	localStorage.setItem("name",name);
	
	// Check the Password and User ID
	if (name == "" && password == "" || password == "" && name != "" || password != "" && name == "") 
	{
		alert("Enter the required values!");
	}
	else
	{
		$.ajax({
			url : "http://localhost:8080/ApplicationScheduler/application/user/login?name="
					+ name + "&password=" + password,
			type : "GET",
			contentType : "application/json",
			success : function(resultData) {
				var data = JSON.parse(resultData);
				localStorage.setItem("role",data.role);
				var role = data.role;
				if(role == 'doctor'){
					window.location.href = "http://localhost:8080/ApplicationScheduler/appointments.html"  ;
				}
				if(role == 'patient'){
					window.location.href = "http://localhost:8080/ApplicationScheduler/welcome.html"
				}
			},
			error : function(data) {
				alert(data.responseText);
			}
		});
	}
}
/** Appointment validation **/
function apptValidate(){
    var dateValue = $("#datepicker").val();
    var x = document.getElementById("datetimepicker");
    var defaultVal = x.defaultValue;
    var timeValue = x.value;
    var roleValue = localStorage.getItem("role");
    var j = {date:dateValue,time:timeValue,role:roleValue};
   
    // Check the date and time
	if (dateValue == "" && timeValue == "" || timeValue == "" && dateValue != "" || timeValue != "" && dateValue == "") 
    {
        alert("Enter the required values!");
    }
	else{
		if(roleValue == 'patient'){
			$.ajax({
				url : "http://localhost:8080/ApplicationScheduler/application/appointment/book",
				type: "POST",
				data: JSON.stringify(j),
				contentType : "application/json",
				success : function(resultData) {
					console.log(resultData);
					alert("Confirmed Appointment");
					var tr;
		   			tr = $('<tr/>');
		   			tr.append("<td>" + resultData.date + "</td>");
		   			tr.append("<td>" + resultData.time + "</td>");
		   			$('table').append(tr);
				},
				error : function(data) {
					alert(data.responseText);
				}
				});
			}
	}
  }
/**Viewing Appointment **/
function docAppt(){
	var role = localStorage.getItem("role");
	$.ajax({
		url : "http://localhost:8080/ApplicationScheduler/application/doctor/apts?role="+role,
		type: "GET",
		contentType : "application/json",
		success : function(resultData) {
			var data = JSON.parse(resultData);
			var tr;
			$('#apptsTable tbody').remove();
			tr = $('<tr/>');
			for(i in data) {
				tr = document.createElement('tr');
			    td = document.createElement('td');
			    tn = document.createTextNode(i);
			    td.appendChild(tn);
			    tr.appendChild(td);
			    for(var v = 0; v < data[i].length ; v++){
			    	td = document.createElement('td');
			    	tn = document.createTextNode(data[i][v]);
			    	td.appendChild(tn);	
			    	tr.appendChild(td);
			    }
			    $('table').append(tr);
			    	 
			//    console.log (i, data[i])
			}
		},
		error : function(data) {
			console.log(data);
		}
	});
}
//Logout functionality
function logout() {
		var id = localStorage.getItem("name");
		$.ajax({
			url : "http://localhost:8080/ApplicationScheduler/application/user/logout?name="
					+ id,
			type : "GET",
			contentType : "application/json",
			success : function(resultData) {
				window.location.href = "http://localhost:8080/ApplicationScheduler/index.html"
			},
			error : function(data) {
				if (data == 400) {
					alert("Oops!! Somethings went wrong.. Please try after sometime");
				}
				if (data == 500) {
					alert("Oops!! Somethings went wrong.. Please try after sometime");
				}
			}
		});
	localStorage.removeItem("studentId");
}