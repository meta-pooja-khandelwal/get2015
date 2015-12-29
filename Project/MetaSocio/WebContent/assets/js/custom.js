$("[data-toggle=popover]").popover({html:true})
								
								
function buttonPress(postId){
	$(".editPost"+postId).show();
}



function editPost(postId){
	$("#post"+postId).hide();
	
	$("#editDiv"+postId).show();
	var postContent=$("#post"+postId).text();
		
		document.getElementById(postId).value;
	$("#editContent"+postId).val(postContent);
	document.getElementById("postId"+postId).value=postId;
	
	
}


function updatePost(postId){

	var postContent=$("#editContent"+postId).val();
	
	
	
	if (window.XMLHttpRequest) {
		
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	var url = "PostHelper?action=editPost&postId=" + postId+"&postContent="+postContent;
	req.open("POST", url, true);
	req.send();
	
	req.onreadystatechange = function() {

		if (req.readyState == 4 && req.status == 200) {
			
			var postDetails=req.responseText;
			document.getElementById("post"+postId).innerHTML=postDetails;
			}
		}	
	
	$("#editDiv"+postId).hide();
	$("#post"+postId).show();
}




function commentButtonPress(commentId){
	$(".editComment"+commentId).show();
}


function editComment(commentId){
	
	$("#comment"+commentId).hide();
	
	$("#editCommentDiv"+commentId).show();
	
	var commentContent=$("#comment"+commentId).text();
		
		/*document.getElementById("commentId"+commentId).value;*/
	
	$("#editCommentContent"+commentId).val(commentContent);
	
	
	
	
}



function updateComment(commentId){
	
	
	var commentContent=$("#editCommentContent"+commentId).val();
	
	
	
	
	if (window.XMLHttpRequest) {
		
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "CommentHelper?action=editComment&commentId=" + commentId+"&commentContent="+commentContent;
	
	req.open("POST", url, true);
	req.send();
	
	req.onreadystatechange = function() {

		if (req.readyState == 4 && req.status == 200) {
			
			var commentDetails=req.responseText;
			document.getElementById("comment"+commentId).innerHTML=commentDetails;
			}
		}	

	$("#editCommentDiv"+commentId).hide();
	$("#comment"+commentId).show();
	
	

}


$('#sandbox-container input').datepicker({
    autoclose: true
});







