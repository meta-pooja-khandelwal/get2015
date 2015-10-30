//DOM loaded 
$(document).ready(function() {

	//attach click event to buttons
	$('.button-show').click(function() {

		/**
		 * when show button is clicked we call the show plugin
		 * which scales the box to default size
		 * You can try other effects from here: http://jqueryui.com/effect/
		 */
		$("#textContent").show();
		$('.toggleBtn').html("HIDE Content");

	});

	$('.button-hide').click(function() {

		//same thing happens except in this case we hide the element
		$("#textContent").hide();
		$('.toggleBtn').html("SHOW Content");

	});

	$('.toggleBtn').click(function() {
		var toggleBtnValue = $('.toggleBtn').html();
		if (toggleBtnValue == "HIDE Content") {
			$('.button-hide').click();
		} else if (toggleBtnValue == "SHOW Content") {
			$('.button-show').click();
		}
	});

});
