
window.setInterval(function(){
	fetch('http://127.0.0.1:8099/getString')
	.then(function(response) {
	  return response.json();
	})
	.then(function(myJson) {
	  
	  console.log(JSON.stringify(myJson));
	  
	  var distance = document.getElementById("distanceText");
	  distance.innerHTML  = "Distance: " + JSON.parse(JSON.stringify(myJson)).value + " cm";
	});
	
}, 1000);	// 1s em 1s


