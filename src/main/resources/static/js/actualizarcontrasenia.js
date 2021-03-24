var url_base = location.origin + "/";

$(document).ready(function(){
	
	history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
    };
	
	$("#btnactualizarcontrasenia").click(function(){		
		enviarMensajeCorreo();
	});	
	
	$("#contrasenia").keypress(function( event ) {
		if ( event.which == 13 ) {
		    enviarMensajeCorreo();
	    }
	});
	
	$("#contrasenia1").keypress(function( event ) {
		if ( event.which == 13 ) {
		    enviarMensajeCorreo();
	    }
	});
	
	$("#btncerrarmensajeexito").click(function(){
		$("#modalexito").modal('hide');
	});
	
	$("#btncerrarmensajeinformacion").click(function(){
		$("#modalinformacion").modal('hide');
	});
	
	$("#btncerrarmensajeerror").click(function(){
		$("#modalerror").modal('hide');
	});
});

function enviarMensajeCorreo(){
	
	var contrasenia = $("#contrasenia").val().trim();
	var contrasenia1 = $("#contrasenia1").val().trim();
	
	if(contrasenia=="" || contrasenia1==""){
		$("#textoerror").html("Los Campos contraseñas deben tener texto");
		$('#modalerror').modal({
			show : true,
			backdrop : 'static',
			keyboard:false
		});
	}
	else{
		if(contrasenia!=contrasenia1){
			$("#textoerror").html("Los Campos contraseñas deben ser iguales");
			$('#modalerror').modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
		}
		else{
			
			$.ajax({
				type: "POST",
				url: url_base + "programaeducativo/actualizarcontraseniacorreo/" ,
				data: {
					contrasenia : $("#contrasenia").val().trim(),
					tipo: $("#hd_tipo").val(),
					id : $("#hd_id").val()
				},
				success: function(respuesta) {
					if(respuesta == 1){
						$("#textoexito").html("Se actualizo la contraseña con éxito");
						$('#modalexito').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}
					else{
						$("#textoerror").html("Ocurrieron problemas al actualizar contraseña");
						$('#modalerror').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}
				},
				error: function() {
			        alert("Ocurrio error en el web service");
			    }
			});
		}
	}
	
}