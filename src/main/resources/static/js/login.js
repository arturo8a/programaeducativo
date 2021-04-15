var url_base = location.origin + "/";

$(document).ready(function(){
	
	history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
    };
	
	$("#btnIngresar").click(function(){		
		validarUsuario();
	});	
	$("#usuario").keypress(function( event ) {
		if ( event.which == 13 ) {
		    validarUsuario();
	    }
	});
	$("#password").keypress(function( event ) {
		  if ( event.which == 13 ) {
			  validarUsuario();
		  }
	});
});

function validarUsuario(){
	var usuario  = $("#usuario").val();
	var password = $("#password").val();		
	if(usuario.trim()=="" && password.trim()==""){
		$("#msj").html("<br><br><strong><label class='text-danger'>Ingrese usuario y contraseña</label></strong>");
	}
	else if(usuario.trim()==""){
		$("#msj").html("<br><br><strong><label class='text-danger'>Ingrese usuario</label></strong>");
	}
	else if(password.trim()==""){
		$("#msj").html("<br><br><strong><label class='text-danger'>Ingrese contraseña</label></strong>");
	}
	else{
		var data_json = { usuario : usuario, password:password};
		$.ajax({
			type: "POST",
			url: url_base + "pedesa/login",
			data: data_json,
			success: function(respuesta) {
				if(respuesta=="-1"){
					$("#msj").html("<br><br><strong><label class='text-danger'>Usuario y/o contraseña incorrectos</label></strong>");	
				}
				if(respuesta=="-2"){
					$("#msj").html("<br><br><strong><label class='text-danger'>Usuario existe en el Sistema pero no se ha registrado en el Programa Educativo</label></strong>");	
				}
				else{
					window.location.replace(url_base+"pedesa/menu");
				}
			},
			error: function() {
		        console.log("No se ha podido obtener la información");
		    }
		});
	}		
}