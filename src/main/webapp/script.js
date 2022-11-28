function validateQuantity(){
    var quantity = document.getElementById("pQuantity").value;
    var isValid=false;
    if(quantity.trim()==""){
        return false;
    }
    var regx = /^[0-9]*$/;
    if(regx.test(quantity)){
        isValid = true;
    }
    else{
        isValid = false;
    }

    return isValid;
}

function validateTitle(){
	var title = document.getElementById("pTitle").value;
    var isValid=false;
    if(title.trim()==""){
        return false;
    }
    return true;
}

function validateSize(){
	 var size = document.getElementById("pSize").value;
	    var isValid=false;
	    if(size.trim()==""){
	        return false;
	    }
	    var regx = /^[0-9]*$/;
	    if(regx.test(size)){
	        isValid = true;
	    }
	    else{
	        isValid = false;
	    }

	    return isValid;
}

function validate(form){
    var isValid;
    var isValidTitle;
    var isValidSize;
    var isValidImageLink;

    if(validateQuantity()==true){
        document.getElementById("quantityError").style.visibility = "hidden";
        document.getElementById("pQuantity").style.border = "0.5px solid green";
        isValid = true;
    }
    else{
        document.getElementById("quantityError").style.visibility = "visible";
        document.getElementById("pQuantity").style.border = "0.5px solid red";
        isValid = false;
    }
    
    if(validateTitle()==true){
        document.getElementById("titleError").style.visibility = "hidden";
        document.getElementById("pTitle").style.border = "0.5px solid green";
        isValidTitle = true;
    }
    else{
        document.getElementById("titleError").style.visibility = "visible";
        document.getElementById("pTitle").style.border = "0.5px solid red";
        isValidTitle = false;
    }
    isValid = isValid && isValidTitle;
   
    if(validateSize()==true){
        document.getElementById("sizeError").style.visibility = "hidden";
        document.getElementById("pSize").style.border = "0.5px solid green";
        isValidSize = true;
    }
    else{
        document.getElementById("sizeError").style.visibility = "visible";
        document.getElementById("pSize").style.border = "0.5px solid red";
        isValidSize = false;
    }
    isValid = isValid && isValidSize;

    return isValid;
}