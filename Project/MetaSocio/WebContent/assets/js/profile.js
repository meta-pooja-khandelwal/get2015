$(document).ready(function () {
			
			if($("#imagePathId").val()==""){
				$("#uploadButton").hide();
			}
		});
		function checkImage(){
			if($("#imagePathId").val()!=""){
				$("#uploadButton").show();
			}
		}