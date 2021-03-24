var url_base = location.origin + "/";

$(document).ready(function(){
	
	history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
    };
	
	$("#btnrecuperarcontrasenia").click(function(){		
		enviarMensajeCorreo();
	});	
	
	$("#correo").keypress(function( event ) {
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
	
	if($("#correo").val().trim()!=""){
		
	    var obj =  {
			usuario : $("#correo").val().trim()
		};
	    
		$.ajax({	
			type : "POST",
		    contentType : "application/json",
		    url : url_base +  "programaeducativo/recuperarcorreo",
		    data : JSON.stringify(obj),
		    dataType : 'json',
			success: function(respuesta) {
				switch(respuesta){			
					case 0 :  
						$("#textoerror").html("Usted no se encuentra registrado en el PE en este año, para registrarte haga clic <a href='http://prometeo.sunass.gob.pe/programaeducativo/inicio'>Aquí</a>");
						$('#modalerror').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
						break;
					case 1 : 						
						$("#textoexito").html("Revise su bandeja de correo");
						$('#modalexito').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
						break;
					default :  
						$("#textoerror").html("Ocurrio error al enviar correo");
						$('#modalerror').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
						break;
				}
			},
			error: function() {
		        alert("Ocurrio error en el web service");
		    }
		});
	}
	else{
		$("#textoerror").html("Debe ingresar Código de II.EE");
		$('#modalerror').modal({
			show : true,
			backdrop : 'static',
			keyboard:false
		});
	}
	
}