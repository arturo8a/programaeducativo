var url_base = location.origin+"/";

$(document).ready(function(){

	$("#btnguardarficha").click(function(){
		guardarFicha();
	});
	
	$("#codmod").focusout(function(){
		
		if($("#codmod").val().trim()!=""){
			var codmod = $("#codmod").val();
			if(codmod != codigo_seleccionado){
				buscarCodmod(codmod);
			}
		}
		else{
			limpiarControles();
		}
	});
	
	
});

function guardarFicha(){

	

}

function filterInt(evt,input){
    var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if(key >= 48 && key <= 57){    
        return true;
    }
    else{
        return false;
    }
}