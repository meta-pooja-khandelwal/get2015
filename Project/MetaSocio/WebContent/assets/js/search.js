var req;


$(document).ready(function(){
    $("#search").keypress(function(){
        $("#searchBlock").css("display", "list-item");
    });
    $("#search").click(function(){
        $("#searchBlock").css("display", "none");
    });
});


	function searchUsersByKey() {

		var search = document.getElementById("search").value;
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		var url = "NameSearch?search=" + search;
		req.open("GET", url, true);
		req.send();
		
		req.onreadystatechange = function() {
			
			if (req.readyState == 4 && req.status == 200) {
				
				var searchedResult = JSON.parse(req.responseText);
			
				var usersNameArray = [];
				var usersImageArray=[];
				var users_array = [];
				var usersIdArray=[];
				for(var i in searchedResult) {
				  if(searchedResult.hasOwnProperty(i) && !isNaN(+i)) {
				    	
					  usersNameArray.push(searchedResult[i].name);
					 
					  usersImageArray.push(searchedResult[i].picture);
					  usersIdArray.push(searchedResult[i].userId);
				    }
				}
				
				var text="";
				for (i = 0; i < searchedResult.length; i++) { 
				    text += "<div class='row' style='margin-top:10px;'>" +
				    		
				    		"<div class='col-md-2'>" +
				    		"<img src='"+usersImageArray[i]+"' width='50px'>" +
				    		"</div>" +
				    		"<div class='col-md-6'><label>"+usersNameArray[i]+"</label></div>"+
				    		"<div class='col-md-4'>" +
				    		"<a class='btn-sm btn-outline-dark' href='FollowPeople?followingId="+usersIdArray[i]+"'>FOLLOW</a>" +
				    		"</div>" +
				    		
				    		"</div>" +
				    		"<hr >" ;
				}
				
				
				
				
				
				
				
				if(text==""){
					document.getElementById("searchBlock").innerHTML ="<center>No results Found</center>";
				}
				else{
					document.getElementById("searchBlock").innerHTML =text;
				}
				
				
				
			}
			}
	}

		/* req.send(null); */
	