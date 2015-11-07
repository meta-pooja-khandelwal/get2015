$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
	loadData();
	$('#myTable').dataTable({
		"iDisplayLength" : 5,
		"bPaginate" : true,
		"bInfo" : false,
		"bFilter" : true,
		"bLengthChange" : false
	});
});
