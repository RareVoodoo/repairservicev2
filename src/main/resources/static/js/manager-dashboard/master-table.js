	$("form").submit(function(event){
		event.preventDefault();
		values = $(this).serializeArray();

		fullName = values[0].value;
		username = values[1].value;
		phoneNumber = values[2].value;
		devices = values.filter(item => item.name == "device-control").map(item => item.value);
		oldUsername = $(this)[0][0].getAttribute("data-initial-value");
		
		var formData = JSON.stringify({
			fullName: fullName,
			username: username,
			phoneNumber: phoneNumber,
			oldUsername: oldUsername,
			devices:devices
		  });

		$.ajax({
			type: "PUT",
			url: "http://localhost:8080/api/user/",
			data: formData,
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			  },
		  }).done(function (data) {
			console.log(data);
		  });

		  $('#exampleModal').modal('hide')
	})


function deleteUser(username){
		var data = JSON.stringify({
			username: username 
		  });

		$.ajax({
			type: "DELETE",
			url: "http://localhost:8080/api/user/",
			data: data,
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			  },
		  }).done(function (data) {
			console.log(data);
		  });
}