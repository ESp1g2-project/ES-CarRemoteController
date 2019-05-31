var c = document.getElementById("canvas");
var ctx = c.getContext("2d");

//Let's assume your rgb data is in `var rgbdata`
var r,g,b;
 



window.setInterval(function(){
	fetch('http://127.0.0.1:8099/getFrames')
	.then(function(response) {
	  return response.json();
	})
	.then(function(myJson) {
	  
	  ///console.log(JSON.stringify(myJson));
	  
	  imageStrBytes = JSON.parse(JSON.stringify(myJson)).frame;
	  
	  if (imageStrBytes !== "")
	  {
		  var partsOfStr = imageStrBytes.split(',');
		  
		  //console.log("!!!!!!!: " + partsOfStr[0]);
		  
		  //var frameArray = new Array(80);
		  
		  
		  for(var i=0; i<(3*128*80); i+=3){ //Notice i+=3
				//Extract RGB components
				r = partsOfStr[i + 0];
				g = partsOfStr[i + 1];	
				b = partsOfStr[i + 2];
				
				//Put pixels!
				ctx.fillStyle = "rgba("+r+","+g+","+b+", 1)";
				//Tricky part ;)
				//We divide i by 3 because remember i does 0,3,6,9...
				//Then we take mod with for column numbers, and integer division for row numbers
				ctx.fillRect( (i/3)%128, Math.floor((i/3)/128), 1, 1 );
			}
	  }
	  /*
	  var countPixels = 0;
	  for(var i=0; i < 80; i++)
	  {
		  frameArray[i] = new Array(128);
		  for (var j=0; j < 128; j++)
		  {
			  frameArray[i][j] = new Array(3);
			  frameArray[i][j][0] = countPixels*3 + 0;
			  frameArray[i][j][1] = countPixels*3 + 1;
			  frameArray[i][j][2] = countPixels*3 + 2;
		      countPixels += 1
		  }
	  }
	  
	  for(var i=0; i< frameArray.length; i++){     // rows
			for(var j=0; j< frameArray[0].length; j++){		// pixels of row 
				r = frameArray[i][j][0];
				g = frameArray[i][j][1];	
				b = frameArray[i][j][2];		
				ctx.fillStyle = "rgba("+r+","+g+","+b+", 1)"; 
				ctx.fillRect( j, i, 1, 1 );
			}
		}
	  */
	  
	  
	  //console.log(imageStrBytes);
	  
	  //console.log(image.length);
	  //console.log(image[1].length);
	  //console.log(image[1] + image[2] + image[3] + image[4] + image[5]);
	  //var distance = document.getElementById("distanceText");
	  //distance.innerHTML  = "Distance: " + JSON.parse(JSON.stringify(myJson)).value + " cm";
	  
	  //document.getElementById("camera").src = "data:image/png;base64," + image;
	});
	
}, 1000);	// 1s em 1s


