var url_base = location.origin+"/";
var data;
var appaternodocenteeditar="",apmaternodocenteeditar="",nombredocenteeditar="",tipodocumentodocenteeditar="",nrodocumentodocenteeditar="";
var generodocenteeditar="";
var nrotelefonodocenteeditar="",correoelectronicodocenteeditar="";
var mensajeValidacion=false;
var bandera = false;

$(document).ready(function(){

	$("#tipodocumentodocenteeditar").change(function(){
		$("#nrodocumentodocenteeditar").focus();
	});

	$("#btncancelardocenteeditar").click(function(){
		limpiarControlesEditar();
		$("#modalEditarDocente").modal('hide');
	});	

	$("#btncerrarmensajeexitoeditar").click(function(){			
		$("#modalexitoeditar").modal('hide');
		limpiarControlesEditar();
		$("#modalEditarDocente").modal('hide');
	});
	
	$("#btncerrarmensajeinformacioneditar").click(function(){
		$("#modalinformacioneditar").modal('hide');	
	});
	
	$("#tipodocumentodocenteeditar").on("change",function(){
		
		if($("#tipodocumentodocenteeditar").val()!=""){
			$("#nrodocumentodocenteeditar").prop('disabled', false);
			$("#nrodocumentodocenteeditar").val("");
			$("#nrodocumentodocenteeditar").focus();
		}
		else{
			$("#nrodocumentodocenteeditar").val("");
			$("#nrodocumentodocenteeditar").prop('disabled', true);
		}
	});
	
	$("#tipodocumentodocenteeditar").on("change",function(){
		if($("#tipodocumentodocenteeditar").val()!=""){
			$("#nrodocumentodocenteeditar").prop('disabled', false);
			$("#nrodocumentodocenteeditar").val("");
			$("#nrodocumentodocenteeditar").focus();
		}
		else{
			$("#nrodocumentodocenteeditar").val("");
			$("#nrodocumentodocenteeditar").prop('disabled', true);
		}
	});
	
	$("#btnactualizardocenteeditar").click(function(){
		
		if(validarCamposEditar()){
		
			$("#modalimagencargando").modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
			
			data = {
				id : $("#iddocenteeditar").val() ,
				appaterno : appaternodocenteeditar,
				apmaterno : apmaternodocenteeditar,
				nombre : nombredocenteeditar,
				tipodocumento : {
					id : tipodocumentodocenteeditar
				},
				nrodocumento : nrodocumentodocenteeditar,
				genero : {
					id : generodocenteeditar
				},
				nrotelefono : nrotelefonodocenteeditar,
				correoelectronico : correoelectronicodocenteeditar,
				estado : 1
			};
			
			$.ajax({
				type : "POST",
			    contentType : "application/json",
			    url : url_base + "pedesa/actualizardocente",
			    data : JSON.stringify(data),
			    dataType : 'json',
				success: function(respuesta) {
					console.log("respuesta :"+ JSON.stringify(respuesta));
					if(respuesta!=undefined){				 
			      		$("#modalimagencargando").modal('hide');
			            table_lista_docentes.cell(celdaseleccionada,1).data(respuesta.appaterno).draw();
						table_lista_docentes.cell(celdaseleccionada,2).data(respuesta.apmaterno).draw();
						table_lista_docentes.cell(celdaseleccionada,3).data(respuesta.nombre).draw();
						table_lista_docentes.cell(celdaseleccionada,4).data($("#tipodocumentodocenteeditar option:selected").html()).draw();
						table_lista_docentes.cell(celdaseleccionada,5).data(respuesta.nrodocumento).draw();
						table_lista_docentes.cell(celdaseleccionada,6).data(respuesta.nrotelefono).draw();
						table_lista_docentes.cell(celdaseleccionada,7).data(respuesta.correoelectronico).draw();
						
						$("#textoexitoeditar").html("Usted modificó exitosamente los datos del docente");
						$('#modalexitoeditar').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}
					else{
						$("#modalimagencargando").modal('hide');
						$("#textoerroreditar").html("IE no se ha registrado actualmente en el Programa Educativo");
						$('#modalerroreditar').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}					
				},
				error: function() {
					$("#modalimagencargando").modal('hide');
					alert("Error al actualizar Docente. Comunicarse con la Oficina de Tecnología de Información de Sunass");
			    }
			});		
		}
		else{
			$("#textoinformacioneditar").html("<div style='color:red' class='text-left'>"+mensajeValidacionEditar+"</div>");
			$('#modalinformacioneditar').modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
		}
	});

});

function validarCamposEditar(){
	
	mensajeValidacionEditar = "";
	appaternodocenteeditar = $("#appaternodocenteeditar").val();
	apmaternodocenteeditar = $("#apmaternodocenteeditar").val();
	nombredocenteeditar = $("#nombredocenteeditar").val();
	tipodocumentodocenteeditar = $("#tipodocumentodocenteeditar").val();
	nrodocumentodocenteeditar = $("#nrodocumentodocenteeditar").val();
	generodocenteeditar = $("#generodocenteeditar").val();
	nrotelefonodocenteeditar = $("#nrotelefonodocenteeditar").val();
	correoelectronicodocenteeditar  = $("#correoelectronicodocenteeditar").val();
	
	if(appaternodocenteeditar.trim()==""){
		mensajeValidacionEditar = "Debe ingresar Apellido Paterno de Docente"+"<br>";
	}
	if(apmaternodocenteeditar.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Apellido Materno de Docente"+"<br>";
	}
	if(nombredocenteeditar.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Nombre de Docente"+"<br>";
	}	
	if(tipodocumentodocenteeditar==0){
		mensajeValidacionEditar += "Debe Seleccionar Tipo de Documento de Docente"+"<br>";
	}
	if(nrodocumentodocenteeditar.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Número de documento de Docente"+"<br>";
	}
	if(generodocenteeditar==0){
		mensajeValidacionEditar += "Debe Seleccionar Genero de Docente"+"<br>";
	}
	if(nrotelefonodocenteeditar.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Número de Télefono de Docente"+"<br>";
	}
	if(correoelectronicodocenteeditar.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Correo Electrónico de Docente"+"<br>";
	}
	if(!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test(correoelectronicodocenteeditar))){		
		mensajeValidacionEditar += "Debe ingresar Correctamente el Correo Electrónico de Docente"+"<br>";
	}	
	if(mensajeValidacionEditar!=""){
		return false;
	}
	return true;
}

function filterIntEditar(evt,input){
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

function filterIntTelfEditar(evt,input){
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

function filterCadenaEditar(evt,input){
	
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

function filterIntNroDocIdentidadDocenteEditar(evt,input){
	
	if($("#tipodocumentodocenteeditar").val()==1){
	    var key = window.Event ? evt.which : evt.keyCode;    
	    var chark = String.fromCharCode(key);
	    var tempValue = input.value+chark;
	    if(key >= 48 && key <= 57){
	    	if($("#tipodocumentodocenteeditar").val() == 1){
				if($("#nrodocumentodocenteeditar").val().trim().length<8)
					return true;
				return false;
			}
	    	else{
	    		return false;
	    	}
	    }
	    else{
	        return false;
	    }
	}
	else if($("#tipodocumentodocenteeditar").val()==2){
		if($("#nrodocumentodocenteeditar").val().trim().length<12)
			return true;
		return false;
	}
}


function limpiarControlesEditar(){
	
	$("#appaternodocenteeditar").val("");
	$("#apmaternodocenteeditar").val("");
	$("#nombredocenteeditar").val("");
	$("#tipodocumentodocenteeditar").val("0");
	$("#nrodocumentodocenteeditar").val("");
	$("#generodocenteeditar").val("0");
	$("#nrotelefonodocenteeditar").val("");
	$("#correoelectronicodocenteeditar").val("");

}



