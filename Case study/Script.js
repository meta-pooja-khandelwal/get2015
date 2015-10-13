var person;
var AC = false;
var powerSteering = false;
var step = 0;
var image = new Array();

image[0] = "download15.png";

image[1] = "download13.png";

image[2] = "download14.jpg";
image[3] = "download8.jpg";
image[4] = "download10.jpg";

function setPerson(personName) {
	person = personName;

	localStorage.setItem("person", person);

}

function validate() {
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	if (email == "poojak@gmail.com" && password == "1234") {
		document.getElementById("loginForm").action = "VehicleHome.html";
		return true;
	} else {
		document.getElementById("error").innerHTML = "email or password in invalid";
		return false;
	}
}

function validateSelect() {
	var company = document.getElementById("company");
	var model = document.getElementById("model");
	var companySelectedValue = company.options[company.selectedIndex].value;
	var modelSelectedValue = model.options[model.selectedIndex].value;
	if (companySelectedValue == "selectCompany") {
		alert("Please select a company name");
		return false;
	} else if (modelSelectedValue == "selectModel") {
		alert("Please select a model name");
		return false;
	} else {
		return true;
	}
}

function showEditAndLogOut() {

	person = localStorage.getItem("person");
	var logOut = document.getElementById("logOut");
	if (person == "admin") {
		document.getElementById("edit1").innerHTML = "Edit";
		document.getElementById("edit2").innerHTML = "Edit";
		document.getElementById("edit3").innerHTML = "Edit";
	} else if (person == "user") {
		document.getElementById("edit1").innerHTML = "";
		document.getElementById("edit2").innerHTML = "";
		document.getElementById("edit3").innerHTML = "";
		logOut.parentNode.removeChild(logOut);
	}
}

function activeAllButtons() {

	var create = document.getElementById("create");
	var logOut = document.getElementById("logOut");
	person = localStorage.getItem("person");

	if (person == "admin") {
		create.disabled = false;

	} else {

		create.disabled = true;
		logOut.parentNode.removeChild(logOut);
	}
}

function setRoadTax() {

	document.getElementById("roadTax").value = (document
			.getElementById("showRoomPrice").value * 10) / 100;

}

function setOnRoadPrice() {
	var result = Number(document.getElementById("showRoomPrice").value)
			+ Number(document.getElementById("roadTax").value);
	document.getElementById("onRoadPrice").value = result;

}

function setAC(AC_Value) {
	AC = AC_Value;

}

function setPowerSteering(powerSteering_Value) {
	powerSteering = powerSteering_Value;

}

function validateCreateInput() {
	var onRoadPrice = document.getElementById("onRoadPrice").value;
	var roadTax = document.getElementById("roadTax").value;
	var result = Number(document.getElementById("showRoomPrice").value)
			+ Number(document.getElementById("roadTax").value);
	if (onRoadPrice == "" || Number(onRoadPrice) != result) {

		document.getElementById("onRoadPrice").value = result;
	}
	if (roadTax == "") {
		roadTax = (document.getElementById("showRoomPrice").value * 10) / 100;
		document.getElementById("roadTax").value = roadTax;
	}

	saveCreatedData();
}

function saveCreatedData() {
	var make = document.getElementById("make").value;
	var model = document.getElementById("model").value;
	var EngineInCC = document.getElementById("engineInCc").value;
	var FuelCapacity = document.getElementById("fuelCapacity").value;
	var Mileage = document.getElementById("mileage").value;
	// var AC=document.getElementById("ac").value;
	var AccessoryKit = document.getElementById("accessorykit").value;
	// var Power Steering=document.getElementById("powerSteering").value;
	var ShowRoomPrice = document.getElementById("showRoomPrice").value;
	var RoadTax = document.getElementById("roadTax").value;
	var onRoadPrice = document.getElementById("onRoadPrice").value;
	var CreatedTime = document.getElementById("createdTime").value;
	var CreatedBy = document.getElementById("createdBy").value;

	localStorage.setItem("make", make);
	localStorage.setItem("model", model);
	localStorage.setItem("EngineInCC", EngineInCC);
	localStorage.setItem("FuelCapacity", FuelCapacity);
	localStorage.setItem("Mileage", Mileage);
	localStorage.setItem("AC", AC);
	localStorage.setItem("AccessoryKit", AccessoryKit);
	localStorage.setItem("PowerSteering", powerSteering);
	localStorage.setItem("ShowRoomPrice", ShowRoomPrice);
	localStorage.setItem("RoadTax", RoadTax);
	localStorage.setItem("onRoadPrice", onRoadPrice);
	localStorage.setItem("CreatedTime", CreatedTime);
	localStorage.setItem("CreatedBy", CreatedBy);

}

function setDataAndShowLogOut() {
	document.getElementById("make").innerHTML = localStorage.getItem("make");
	document.getElementById("model").innerHTML = localStorage.getItem("model");
	document.getElementById("engineInCc").innerHTML = localStorage
			.getItem("EngineInCC");
	document.getElementById("fuelCapacity").innerHTML = localStorage
			.getItem("FuelCapacity");
	document.getElementById("mileage").innerHTML = localStorage
			.getItem("Mileage");
	document.getElementById("ac").innerHTML = localStorage.getItem("AC");
	document.getElementById("accessorykit").innerHTML = localStorage
			.getItem("AccessoryKit");
	document.getElementById("powerSteering").innerHTML = localStorage
			.getItem("PowerSteering");
	document.getElementById("showRoomPrice").innerHTML = localStorage
			.getItem("ShowRoomPrice");
	document.getElementById("roadTax").innerHTML = localStorage
			.getItem("RoadTax");
	document.getElementById("onRoadPrice").innerHTML = localStorage
			.getItem("onRoadPrice");
	document.getElementById("createdTime").innerHTML = localStorage
			.getItem("CreatedTime");
	document.getElementById("createdBy").innerHTML = localStorage
			.getItem("CreatedBy");
	person = localStorage.getItem("person");
	var logOut = document.getElementById("logOut");
	if (person == "user") {
		logOut.parentNode.removeChild(logOut);
	}
}

function enableOrDisableCreateCarAndLogOutLink() {
	person = localStorage.getItem("person");

	var createCarlink = document.getElementById("createCarLink");
	var logOut = document.getElementById("logOut");
	if (person == "user") {
		createCarlink.parentNode.removeChild(createCarlink);
		logOut.parentNode.removeChild(logOut);
	}
}

function imageSlider() {
	if (step < 4) {
		step++;

	} else {
		step = 0;

	}
	document.getElementById("asideImage").src = image[step];
	setTimeout("imageSlider()", 2000);
}

function validateMakeAndModelField() {
	var company = document.getElementById("company");
	var model = document.getElementById("model");
	if (company.value == "selectCompany") {
		alert("please select company name");
		return false;
	} else if (model.value == "selectModel") {
		alert("please select Model");
		return false;
	} else {
		return true;
	}
}

function setValues() {
	document.getElementById("make").value = localStorage.getItem("make");
	document.getElementById("model").value = localStorage.getItem("model");
	document.getElementById("engineInCc").value = localStorage
			.getItem("EngineInCC");
	document.getElementById("fuelCapacity").value = localStorage
			.getItem("FuelCapacity");
	document.getElementById("mileage").value = localStorage.getItem("Mileage");
	document.getElementById("showRoomPrice").value = localStorage
			.getItem("ShowRoomPrice");
	document.getElementById("roadTax").value = localStorage.getItem("RoadTax");
	document.getElementById("onRoadPrice").value = localStorage
			.getItem("onRoadPrice");
}
function updateData() {
	var make = document.getElementById("make").value;
	var model = document.getElementById("model").value;
	var EngineInCC = document.getElementById("engineInCc").value;
	var FuelCapacity = document.getElementById("fuelCapacity").value;
	var Mileage = document.getElementById("mileage").value;
	var ShowRoomPrice = document.getElementById("showRoomPrice").value;
	var RoadTax = document.getElementById("roadTax").value;
	var onRoadPrice = document.getElementById("onRoadPrice").value;

	localStorage.setItem("make", make);
	localStorage.setItem("model", model);
	localStorage.setItem("EngineInCC", EngineInCC);
	localStorage.setItem("FuelCapacity", FuelCapacity);
	localStorage.setItem("Mileage", Mileage);
	localStorage.setItem("ShowRoomPrice", ShowRoomPrice);
	localStorage.setItem("RoadTax", RoadTax);
	localStorage.setItem("onRoadPrice", onRoadPrice);

}