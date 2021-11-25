
function deleteRequest(requestId){
			var data = JSON.stringify({
				id: requestId
			  });
	
			$.ajax({
				type: "DELETE",
				url: "http://localhost:8080/api/repair-request/delete",
				data: data,
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				  },
			  }).done(function (data) {
				console.log(data);
			  });
}


function assignMaster(requestId, username){
	var data = JSON.stringify({
		requestId: requestId,
		assigneeUsername: username
	  });

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/api/repair-request/assign-master",
		data: data,
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		  },
	  }).done(function (data) {
		console.log(data);
	  });
}


