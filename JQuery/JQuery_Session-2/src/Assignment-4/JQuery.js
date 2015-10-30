//DOM loaded 
$(document).ready(function() {

	$('input[type="text"]').change(function() {
		var label = $('#name').text();
		var value = $('input[type="text"]').val();

		var content = $('.div').text() + "\n" + label + value;
		$('.div').text(content);

		$('input[type="text"]').addClass('highlighted');
		setTimeout(function() {
			$('input[type="text"]').removeClass('highlighted');
		}, 2000);
	});

	$('input[type="number"]').change(function() {
		var label = $('#age').text();
		var value = $('input[type="number"]').val();
		var content = $('.div').text() + "\n" + label + value;
		$('.div').text(content);

		$('input[type="number"]').addClass('highlighted');
		setTimeout(function() {
			$('input[type="number"]').removeClass('highlighted');
		}, 2000);
	});

	$('textarea').change(function() {
		var label = $('#address').text();
		var value = $('textarea').val();
		var content = $('.div').text() + "\n" + label + value;
		$('.div').text(content);

		$('textarea').addClass('highlighted');
		setTimeout(function() {
			$('textarea').removeClass('highlighted');
		}, 2000);
	});

});
