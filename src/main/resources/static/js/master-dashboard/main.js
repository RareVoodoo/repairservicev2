
function changeStatus(requestId){
			var data = JSON.stringify({
				requestId: requestId,
				requestStatus: $(event.target).text()
			  });
	
			$.ajax({
				type: "POST",
				url: "http://localhost:8080/api/repair-request/change-status",
				data: data,
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				  },
			  }).done(function (data) {
				console.log(data);
			  });
}

