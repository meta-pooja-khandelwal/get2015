alert("hi");
function showContentAndHideOnBtn() {
	var textContent = document.getElementById("textContent");
	textContent.style.display = '';
	var btnShowOrHide = document.getElementById("btnShowOrHide");
	btnShowOrHide.innerHTML = "HIDE Content";
}

function hideContentAndShowOnBtn() {
	var textContent = document.getElementById("textContent");
	textContent.style.display = 'none';
	var btnShowOrHide = document.getElementById("btnShowOrHide");
	btnShowOrHide.innerHTML = "SHOW Content";
}
function showOrHideContent() {
	var btnShowOrHide = document.getElementById("btnShowOrHide");

	if ((btnShowOrHide.innerHTML) == "HIDE Content") {
		hideContentAndShowOnBtn();
	}

	else if ((btnShowOrHide.innerHTML) == "SHOW Content") {
		showContentAndHideOnBtn();
	}

}