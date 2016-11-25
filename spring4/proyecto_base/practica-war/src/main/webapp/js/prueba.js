function validateDate(el){
	if (Date.parse(el) < Date.now()) {
	    el.value = '';
    	alert("La fecha debe ser posterior a la actual");
    }
}


function validateDate(el){
	// Se busca el elemento con id "campoFecha"
	var el = document.getElementById("campoFecha"); 
	if (Date.parse(el) < Date.now()) {
	    el.value = '';
    	alert("La fecha debe ser posterior a la actual");
    }
}

/*
function <nombre_función>(argumento1, argumento2, ..., argumento n) { 
	<código de la función> 
} 


function <nombre de función>(argumento1, argumento2, ..., argumento n) { 
	<código de la función> 
	return valor;
}

*/