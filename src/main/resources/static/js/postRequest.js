$(document).ready(
	function() {

	$("#exchange-form").submit(function(event) {
		var form=this;
		console.log(form);
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

		function ajaxPost() {
			console.log("Hi Arya");
			
			console.log("Hi Ashim");
			// PREPARE FORM DATA
			var formData = {
				fromCoin: $("#fCoin").val(),
				toCoin: $("#tCoin").val(),
				coinAmount: $("#coinAmt").val()
			}

			console.log(formData);

			// DO POST
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/usercoins/1/exchange",
				data: JSON.stringify(formData),
				dataType: 'json',
				success: function(result) {

					if (result.status == "success") {

						$("#demo").html("<strong>"+result.message +"</strong>");
						
				

					} else {
						$("#demo").html("<strong>"+result.message +"</strong>");
					}
				},
				error: function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
			});

		}

	})