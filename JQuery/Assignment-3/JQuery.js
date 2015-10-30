//DOM loaded 
$(document).ready(function() {

	//attach click event to buttons
	$('#Q1').click(function() {

		$('span.message').css({
			'color' : 'red',
			'font-size' : '150%'
		});
	});

	$('#Q2').click(function() {

		$('div.box:first').css({
			'color' : 'green',
			'font-size' : '150%'
		});
	});

	$('#Q3').click(function() {

		$('button').css({
			'background' : 'gray'
		});
	});

	$('#selectFirstDiv').click(function() {
		$('div:first').css({
			'color' : 'silver',
			'font-size' : '150%'
		});
	});

	$('#Q4').click(function() {
		$("img[alt='hello']").css({
			'height' : '100px',
			'width' : '100px'
		});

	});

	$('#Q5').click(function() {
		$("div#myDiv :input[type='text']").css({
			'background' : 'yellow'
		});
	});

	$('#Q6').click(function() {
		$('input[name*="txt"]').css({
			'background' : 'brown'
		});
	});

	$('#Q7').click(function() {
		$("p").not($(".box")).css({
			'color' : 'pink'
		});
	});

	$('#Q8').click(function() {
		$("div.box ,.error").css({
			'color' : 'lime'
		});
	});

	$('#Q9').click(function() {
		$("div.box.error").css({
			'color' : 'aqua'
		});
	});

	$('#Q10').click(function() {
		$("div#myDiv span.info").css({
			'background' : 'blue'
		});
	});

});