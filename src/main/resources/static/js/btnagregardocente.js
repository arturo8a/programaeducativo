var url_base = location.origin+"/";
var data;
var appaternodocente="",apmaternodocente="",nombredocente="",tipodocumentodocente="",nrodocumentodocente="";
var generodocente="";
var nrotelefonodocente="",correoelectronicodocente="";
var mensajeValidacion=false;

$(document).ready(function(){

	$("#tipodocumentodocente").change(function(){
		$("#nrodocumentodocente").focus();
	});
	
	$("#btnCerrarMensajeExitoDocente").click(function(){			
		$("#modalExitoDocente").modal('hide');
		limpiarControles();	
		$("#modalAgregarDocente").modal('hide');
	});
		
	$("#btnCerrarMensajeInformacionDocente").click(function(){
		$("#modalInformacionDocente").modal('hide');
	});
	
	$("#btnCerrarMensajeErrorDocente").click(function(){
		$("#modalErrorDocente").modal('hide');
	});
	
	$("#btnCancelarDocente").click(function(){
		limpiarControles();	
		$("#modalAgregarDocente").modal('hide');
	});	
	
	$("#tipodocumentodocente").on("change",function(){
		
		if($("#tipodocumentodocente").val()!=""){
			$("#nrodocumentodocente").prop('disabled', false);
			$("#nrodocumentodocente").val("");
			$("#nrodocumentodocente").focus();
		}
		else{
			$("#nrodocumentodocente").val("");
			$("#nrodocumentodocente").prop('disabled', true);
		}
	});
	
	$("#btnAgregarDocente").click(function(){
		if(validarCampos()){
		
			$("#modalimagencargando").modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
			
			data = {
				appaterno : appaternodocente,
				apmaterno : apmaternodocente,
				nombre : nombredocente,
				tipodocumento: {
					id : tipodocumentodocente
				},
				nrodocumento : nrodocumentodocente,
				genero : {
					id : generodocente 
				},
				nrotelefono : nrotelefonodocente,
				correoelectronico : correoelectronicodocente,
				estado : 1
			};
			
			var hoy = new Date();
			var dia = parseInt(hoy.getDate());
			if(dia<10){
				dia = "0" + dia.toString();
			}
			var mes = parseInt(hoy.getMonth()+1);
			if(mes<9){
				mes = "0" + mes.toString();
			}
			var anio = hoy.getFullYear();
			var fecha = anio + "/"  + mes + "/" + dia;		
			var hora = " " + hoy.getHours() + ':' + hoy.getMinutes();
			
			$.ajax({
				type : "POST",
			    contentType : "application/json",
			    url : url_base + "pedesa/registrardocente",
			    data : JSON.stringify(data),
			    dataType : 'json',
				success: function(respuesta) {
					console.log("respuesta :" + respuesta);
					if(respuesta.id==-1){					
						$("#modalimagencargando").modal('hide');
						$("#textoErrorDocente").html("IE no se ha registrado en este año al Programa Educativo");
						$('#modalErrorDocente').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});	
					}
					else if(respuesta.id == -100){
						 window.location = url_base + "pedesa";
					}
					else if(respuesta.id==0){
						$("#modalimagencargando").modal('hide');
						$("#textoErrorDocente").html("Error al registrar Docente");
						$('#modalErrorDocente').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}
					else {						
						$("#modalimagencargando").modal('hide');	
						table_lista_docentes.row.add({
							"id" : respuesta.id,
					        "appaterno": appaternodocente,
					        "apmaterno": apmaternodocente,
					        "nombre": nombredocente,
					        "tipodocumento": $("#tipodocumentodocente option:selected").html(),
					        "nrodocumento": nrodocumentodocente,
					        "nrotelefono" : nrotelefonodocente,
					        "correoelectronico" : correoelectronicodocente,
					        "fecha_registro" : fecha + hora,
					        "nomie" : respuesta.valor,
					        "defaultContent" : '<img src="./images/svg/edit-regular.svg" class="editar" style="width:20px; cursor:pointer" />',
					        "defaultContent" : '<img src="./images/svg/eliminar-alt-regular.svg" class="eliminar" style="width:20px; cursor:pointer" />'
					    }).draw();
					    table_lista_docentes.ajax.reload(null, false);
					    $("#textoExitoDocente").html("Usted agregó exitosamente a un nuevo docente");
						$('#modalExitoDocente').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
						limpiarControles();	
					}
				},
				error: function() {
					$("#modalimagencargando").modal('hide');
					alert("Error al Registrarse en el Programa Educativo. Comunicarse con la Oficina de Tecnología de Información de Sunass");
			    }
			});		
		}
		else{
			$("#textoInformacionDocente").html("<div style='color:red' class='text-left'>"+mensajeValidacion+"</div>");
			$('#modalInformacionDocente').modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
		}
	});

});

function validarCampos(){
	
	mensajeValidacion = "";
	appaternodocente = $("#appaternodocente").val();
	apmaternodocente = $("#apmaternodocente").val();
	nombredocente = $("#nombredocente").val();
	tipodocumentodocente = $("#tipodocumentodocente").val();
	nrodocumentodocente = $("#nrodocumentodocente").val();	
	generodocente = $("#generodocente").val();
	nrotelefonodocente = $("#nrotelefonodocente").val();
	correoelectronicodocente  = $("#correoelectronicodocente").val();
	
	if(appaternodocente.trim()==""){
		mensajeValidacion = "Debe ingresar Apellido de Docente"+"<br>";
	}
	if(apmaternodocente.trim()==""){
		mensajeValidacion += "Debe ingresar Apellido de Docente"+"<br>";
	}
	if(nombredocente.trim()==""){
		mensajeValidacion += "Debe ingresar Nombre de Docente"+"<br>";
	}
	if(tipodocumentodocente==0){
		mensajeValidacion += "Debe Seleccionar Tipo de Documento de Docente"+"<br>";
	}
	if(nrodocumentodocente.trim()==""){
		mensajeValidacion += "Debe ingresar Número de documento de Docente"+"<br>";
	}
	
	if(tipodocumentodocente==1){
		if(nrodocumentodocente.trim().length!=8){
			mensajeValidacion += "El número de documento debe tener 8 digitos"+"<br>";
		}
	}
	else if(tipodocumentodocente==2){
		if(nrodocumentodocente.trim().length!=12){
			mensajeValidacion += "El número de documento debe tener 12 digitos"+"<br>";
		}
	}
	
	if(generodocente==0){
		mensajeValidacion += "Debe seleccionar Género de Docente"+"<br>";
	}
	if(nrotelefonodocente.trim()==""){
		mensajeValidacion += "Debe ingresar Número de teléfono de Docente"+"<br>";
	}
	if(correoelectronicodocente.trim()==""){
		mensajeValidacion += "Debe ingresar Correo Electrónico de Docente"+"<br>";
	}
	if(!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test(correoelectronicodocente))){		
		mensajeValidacion += "Debe ingresar Correctamente el Correo Electrónico de Docente"+"<br>";
	}
	
	if(mensajeValidacion!=""){
		return false;
	}
	return true;
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

function filterIntTelf(evt,input){
	var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if((key >= 48 && key <= 57) || key==45){    
        return true;
    }
    else{
        return false;
    }
}

function filterIntNroDocIdentidadDocente(evt,input){
	
	if($("#tipodocumentodocente").val()==1){
	    var key = window.Event ? evt.which : evt.keyCode;    
	    var chark = String.fromCharCode(key);
	    var tempValue = input.value+chark;
	    if(key >= 48 && key <= 57){
	    	if($("#tipodocumentodocente").val() == 1){
				if($("#nrodocumentodocente").val().trim().length<8)
					return true;
				return false;
			}
	    	else{
	    		return true;
	    	}
	    }
	    else{
	        return false;
	    }
	}
	else if($("#tipodocumentodocente").val()==2){
		if($("#nrodocumentodocente").val().trim().length<12)
			return true;
		return false;
	}
}


function filterCadena(evt,input){
	
	var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if((key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key ==32)    || (key ==241) || (key ==209) || (key ==225) || (key ==233) || (key ==237) || (key ==243) || (key ==250)  || (key ==193) || (key ==201) || (key ==205) || (key ==211) || (key ==218)   ){    
        return true;
    }
    else{
        return false;
    }	
}

function limpiarControles(){
	
	$("#appaternodocente").val("");
	$("#apmaternodocente").val("");
	$("#nombredocente").val("");
	$("#tipodocumentodocente").val("0");
	$("#nrodocumentodocente").val("");
	$("#generodocente").val("0");
	$("#nrotelefonodocente").val("");
	$("#correoelectronicodocente").val("");
}



