 $(document).ready(init);

 function init(){
	$('#name').change(getEmployeeData);
}
 
 function getEmployeeData(){
	
	 var xhttp;
	  if (window.XMLHttpRequest) {
	    // code for modern browsers
	    xhttp = new XMLHttpRequest();
	    } else {
	    // code for IE6, IE5
	    xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  var name=$('#name').val();
	  xhttp.open("GET", "./EmployeeDetailsController?name="+name, true);
	  xhttp.send();
	 
	  xhttp.onreadystatechange = function() {
		
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	     $("#details").html(xhttp.responseText);
	    
	    }
	  }
	 
 }