var startIndex = 2;
function loadPosts(groupId,pageTitle) {
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "PostLoader?startIndex="+startIndex+"&groupId="+groupId+"&pageTitle="+pageTitle;
	req.open("GET", url, true);
	req.send();
	req.onreadystatechange = function() {
		if (req.readyState == 4 && req.status == 200) {
			var postContentDiv = document.createElement('div');
			if( req.responseText==""){
				postContentDiv.innerHTML="<div class='col-md-12 text-center' style='margin-top:10px;'>No more post to show.</div>";
				$("#loadMore").hide();
				
			}else{
				postContentDiv.innerHTML = req.responseText;
			}
			
			document.getElementById("postLoader").appendChild(postContentDiv);
			startIndex += 2;
		}
	}
}