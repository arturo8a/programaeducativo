window.onload = function(){
	console.log("back");
	window.location.hash = "no-back-button";
	window.location.hash = "Again-No-back-button" //chrome	
	window.onhashchange = function(){
		console.log("back1");
		window.location.hash = "no-back-button";
	}
}