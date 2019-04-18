/**
 * 
 */
 function checkPost(){
	 var file = document.getElementById("file");
	 var fileType = file.value.substr(file.value.lastIndexOf(".")).toLowerCase();
	 if(file.value == null || file.value == ""){
		 alert("Please choose a file to upload!");
		 return false;
	 }
	 else if( fileType != '.zip' ){
		 alert("Only \".ZIP\" file accepted!");
		 return false;
	 }
	 else{
		 return true;
	 }
 }
