//DOM loaded 

$(document).ready(function() {
	$('#start').click(function() {
		$('#div').animate({
			'marginLeft' : '1180px'
		}, 8000);
	});

	$('#stop').click(function() {
		$('#div').stop();
	});

	$('#back').click(function() {
		$('#div').animate({
			'marginLeft' : '0px'
		}, 8000);
	});

	$('#left').click(function() {
		$('#div').animate({
			'marginLeft' : '-=50px'
		}, 8000);
	});

	$('#right').click(function() {
		$('#div').animate({
			'marginLeft' : '+=50px'
		}, 8000);
	});

	$('#top').click(function() {
		$('#div').animate({
			'marginTop' : '-=50px'
		}, 8000);
	});

	$('#bottom').click(function() {
		$('#div').animate({
			'marginTop' : '+=50px'
		}, 8000);
	});
});
