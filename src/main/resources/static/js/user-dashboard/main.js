
$(document).ready(function(){
	$("form").submit(function(event){
		event.preventDefault();

		var formData = JSON.stringify({
			summary: $("#summary").val(),
			description: $("#descriptionArea").val(),
			deviceType: $("#deviceType").val()
		  });

		$.ajax({
			type: "POST",
			url: "http://localhost:8080/api/repair-request/",
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
})


