$(document).on("click", ".btn-exchange", function() {
	var fCoinName = $(this).data('id');
	var userId = $(this).data('user-id');
	console.log("this is the user ID: " + userId);
	$("#current-user-Id").val(userId);
	console.log(fCoinName);
	$(".fromCoinName").html(fCoinName);


	// $(".modal-body #bookId").val( myBookId );
	// As pointed out in comments, 
	// it is unnecessary to have to manually call the modal.
	// $('#addBookDialog').modal('show');
});

function exchanger(from, to) {

	$("#fCoin").val(from);
	$("#aCoins").val(to);


}

$("#save-exchange").click(function() {
	
	var userId = $("#current-user-Id").val();
	
	var formData = {
		fromCoin: $("#fCoin").val(),
		toCoin: $("#tCoin").val(),
		coinAmount: $("#coinAmt").val()

	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/usercoins/"+userId+"/exchange",
		data: JSON.stringify(formData),
		dataType: 'json',
		success: function(result) {

			if (result.status == "success") {

				$("#result-msg").html("<h6>" + result.message + "</h6>");

				setTimeout(function() {
					window.location = "/usercoins/1";
				}, 1000);

			} else {
				$("#result-msg").html("<h6>" + result.message + "</h6>");
			}
		},
		error: function(e) {
			alert("Please enter the amount!")
			console.log("ERROR: ", e);
		}
	});


});

$(".reset-exchange").click(function() {
	$("#coinAmt").val('');
	$("#tCoin").val(1);
	$("#result-msg").html("");
});

