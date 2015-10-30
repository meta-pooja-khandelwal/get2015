//DOM loaded 
$(document).ready(function() {

	//attach click event to buttons
	$('#setColorbtn').click(function() {

		$('tr:even').css({
			'color' : 'red',
			'font-size' : '150%',
                          'background':'lime'        
		});
		$('tr:odd').css({
			'color' : 'yellow',
			'font-size' : '150%',
                         'background':'gray'     

		});
	});



});