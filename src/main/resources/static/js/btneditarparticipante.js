var url_base = location.origin+"/";
var data;
var idparticipante=0, appaternoestudiante="",apmaternoestudiante="",nombreestudiante="",tipodocumentoestudiante="",nrodocumentoestudiante="",fechanacimientoestudiante="";
var generoestudiante="",nivelestudiante="",gradoestudiante="",seccionestudiante="";
var categoriacuento=0, categoriapoesia=0,categoriadibujopintura=0,categoriacomposicionmusical=0,categoriaahorroagua=0,modalidadpostulacionindividual=0,modalidadpostulaciongrupal=0;
var appaternopmt="",apmaternopmt="",nombrepmt="",parentesco="",tipodocumentopmt="",nrodocumentopmt="",nrotelefonopmt="",correoelectronicopmt="";
var mensajeValidacionEditar="";
var bandera = false;

$(document).ready(function(){

	$("#tipodocumentoestudianteeditar").change(function(){
		$("#nrodocumentoestudianteeditar").focus();
	});
	$("#tipodocumentopmteditar").change(function(){
		$("#nrodocumentopmteditar").focus();
	});

	$("#fechanacimientoestudianteeditar").datepicker({
			locale: 'es-es',
		    format: 'dd/mm/yyyy',
		    uiLibrary: 'bootstrap4'
		});
		
	$("#quitararchivoeditar").click(function(){
		console.log("quitar");
		$("#fichaparticipanteeditar").prop("disabled",false);
		bandera = true;
	});
	
	$("#btncancelarparticipanteeditar").click(function(){
		limpiarControlesEditar();
		$("#modaleditarParticipante").modal('hide');
	});	

	$("#tipodocumentoestudianteeditar").change(function(){
		$("#nrodocumentoestudianteeditar").focus();
	});
	
	$("#gradoestudianteeditar").change(function(){
		$("#seccionestudianteeditar").focus();
	});
	
	$("#tipodocumentopmteditar").change(function(){
		$("#nrodocumentopmteditar").focus();
	});

	$("#fechanacimientoestudianteeditar").datepicker({
		locale: 'es-es',
	    format: 'dd/mm/yyyy',
	    uiLibrary: 'bootstrap4'
	});
	
	$("#btncerrarmensajeexitoeditar").click(function(){			
		$("#modalexitoeditar").modal('hide');
		limpiarControlesEditar();
		$("#modaleditarParticipante").modal('hide');
	});
		
	$("#btncerrarmensajeinformacioneditar").click(function(){
		$("#modalinformacioneditar").modal('hide');
	});
	
	$("#btncerrarmensajeerroreditar").click(function(){
		$("#modalerroreditar").modal('hide');
	});
	
	$("#btncerrarmensajeconfirmacioneditar").click(function(){
		$("#modalconfirmacioneditar").modal('hide');
	});
	
	$("#tipodocumentoestudianteeditar").on("change",function(){
		
		if($("#tipodocumentoestudianteeditar").val()!=""){
			$("#nrodocumentoestudianteeditar").prop('disabled', false);
			$("#nrodocumentoestudianteeditar").val("");
			$("#nrodocumentoestudianteeditar").focus();
		}
		else{
			$("#nrodocumentoestudianteeditar").val("");
			$("#nrodocumentoestudianteeditar").prop('disabled', true);
		}
	});
	
	$("#tipodocumentopmteditar").on("change",function(){
		if($("#tipodocumentopmteditar").val()!=""){
			$("#nrodocumentopmteditar").prop('disabled', false);
			$("#nrodocumentopmteditar").val("");
			$("#nrodocumentopmteditar").focus();
		}
		else{
			$("#nrodocumentopmteditar").val("");
			$("#nrodocumentopmteditar").prop('disabled', true);
		}
	});
	
	$("#nivelestudianteeditar").click(function(){	
		nivelestudiante = $("#nivelestudianteeditar").val();
		data = {
			nivelestudiante : nivelestudiante
		};
		$.ajax({
				url : url_base + "pedesa/listargradopornivel/"+nivelestudiante,
			    success: function(respuesta) {
					var contenido = "<option value='0'>Seleccione</option>";
					for(var i=0;i<respuesta.length;i++){
						contenido = contenido + "<option value="+respuesta[i].id+">"+respuesta[i].descripcion+"</option>";
					}
					$("#gradoestudianteeditar").html(contenido);
				},
				error: function() {
					alert("Error al buscar grado por nivel");
			    }
			});
		 
	});
	
	$("#btnactualizarparticipanteeditar").click(function(){
		
		console.log("btnactualizarparticipante");
		
		if(validarCamposEditar()){
		
			var contenido_categoria = "";
			var contenido_modalidad = "";
			
			console.log("entro validarCamposEditar()");
			
			$("#modalimagencargando").modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
			
			fechanacimientoestudiante = fechanacimientoestudiante.split("/");
			fechanacimientoestudiante = fechanacimientoestudiante[2] + "-" + fechanacimientoestudiante[1] + "-" + fechanacimientoestudiante[0];
			
			categoriacuento = 0;
			categoriapoesia = 0;
			categoriadibujopintura = 0;
			categoriacomposicionmusical = 0;
			categoriaahorroagua = 0;
			modalidadpostulacionindividual = 0;
			modalidadpostulaciongrupal = 0;
			
			if($("#categoriacuentoeditar").is(':checked')){
				categoriacuento = 1;
				contenido_categoria = "Cuento /";
			}					
			if($("#categoriapoesiaeditar").is(':checked')){
				categoriapoesia = 1;
				contenido_categoria += "Poesía /";
			}					
			if($("#categoriadibujopinturaeditar").is(':checked')){
				categoriadibujopintura = 1;
				contenido_categoria += "Díbujo o Pintura /";
			}					
			if($("#categoriacomposicionmusicaleditar").is(':checked')){
				categoriacomposicionmusical = 1;
				contenido_categoria += "Composición musical /";
			}				
			if($("#categoriaahorroaguaeditar").is(':checked')){
				categoriaahorroagua = 1;
				contenido_categoria += "Ahorro agua/";
			}					
			if($("#modalidadpostulacionindividualeditar").is(':checked')){
				modalidadpostulacionindividual = 1;
				contenido_modalidad += "Individual /";
			}					
			if($("#modalidadpostulaciongrupaleditar").is(':checked')){
				modalidadpostulaciongrupal = 1;
				contenido_modalidad += "Grupal/";
			}
			
			contenido_categoria = contenido_categoria.slice(0, -1);
			contenido_modalidad = contenido_modalidad.slice(0, -1);
			
			data = {
				id : $("#idparticipanteeditar").val() ,
				appaternoestudiante : appaternoestudiante,
				apmaternoestudiante : apmaternoestudiante,
				nombreestudiante : nombreestudiante,
				tipodocumentoestudiante : {
					id : tipodocumentoestudiante
				},
				nrodocumentoestudiante : nrodocumentoestudiante,
				fechanacimientoestudiante: fechanacimientoestudiante ,
				generoestudiante : {
					id : generoestudiante 
				},
				nivelestudiante : nivelestudiante ,
				gradoestudiante : {
					id :gradoestudiante 
				},
				seccion : seccionestudiante,
				categoriacuento : categoriacuento,
				categoriapoesia : categoriapoesia,
				categoriadibujopintura : categoriadibujopintura,
				categoriacomposicionmusical :categoriacomposicionmusical,
				categoriaahorroagua : categoriaahorroagua,
				modalidadpostulacionindividual : modalidadpostulacionindividual,
				modalidadpostulaciongrupal : modalidadpostulaciongrupal,
				appaternopmt : appaternopmt,
				apmaternopmt : apmaternopmt,
				nombrepmt : nombrepmt,
				parentesco : {
					id :parentesco
				},
				tipodocumentopmt : {
					id :tipodocumentopmt
				},
				nrodocumentopmt : nrodocumentopmt,
				nrotelefonopmt : nrotelefonopmt,
				correoelectronicopmt : correoelectronicopmt,
				estado : 1
			};
			
			console.log("id ..." + $("#idparticipanteeditar").val());
			console.log("data ..." + data);
			
			$.ajax({
				type : "POST",
			    contentType : "application/json",
			    url : url_base + "pedesa/registrarparticipante",
			    data : JSON.stringify(data),
			    dataType : 'json',
				success: function(respuesta) {
					if(respuesta>0){
						if(bandera){						
							var data = new FormData();
							data.append('file',fichaparticipanteeditar.files[0]);
							data.append('id',respuesta);						
							$.ajax({
						        type: "POST",
						        enctype: 'multipart/form-data',
						        url: url_base + "pedesa/actualizararchivoparticipante",
						        data: data,
						        processData: false, 
						        contentType: false,
						        cache: false,
						        timeout: 600000,
						        success: function (data) {					 
						            $("#modalimagencargando").modal('hide');
						            
						            console.log("celdaseleccionada ..." + celdaseleccionada);
									table_lista_participantes.cell(celdaseleccionada,1).data(appaternoestudiante).draw();
									table_lista_participantes.cell(celdaseleccionada,2).data(apmaternoestudiante).draw();
									table_lista_participantes.cell(celdaseleccionada,3).data(nombreestudiante).draw();
									table_lista_participantes.cell(celdaseleccionada,4).data($("#tipodocumentoestudianteeditar option:selected").html()).draw();
									table_lista_participantes.cell(celdaseleccionada,5).data(nrodocumentoestudiante).draw();
									table_lista_participantes.cell(celdaseleccionada,6).data(contenido_categoria).draw();
									table_lista_participantes.cell(celdaseleccionada,7).data(contenido_modalidad).draw();
									
									$("#textoexitoeditar").html("Usted modificó exitosamente los datos del participante");
									$('#modalexitoeditar').modal({
										show : true,
										backdrop : 'static',
										keyboard:false
									});
												
									limpiarControlesEditar();
						 
						        },
						        error: function (e) {
						            console.log("ERROR : ", e);
						        }
						    });							
						}
						else{
							$("#modalimagencargando").modal('hide');
							
							console.log("celdaseleccionada ..." + celdaseleccionada);
							
							table_lista_participantes.cell(celdaseleccionada,1).data(appaternoestudiante).draw();
							table_lista_participantes.cell(celdaseleccionada,2).data(apmaternoestudiante).draw();
							table_lista_participantes.cell(celdaseleccionada,3).data(nombreestudiante).draw();
							table_lista_participantes.cell(celdaseleccionada,4).data($("#tipodocumentoestudianteeditar option:selected").html()).draw();
							table_lista_participantes.cell(celdaseleccionada,5).data(nrodocumentoestudiante).draw();
							table_lista_participantes.cell(celdaseleccionada,6).data(contenido_categoria).draw();
							table_lista_participantes.cell(celdaseleccionada,7).data(contenido_modalidad).draw();
							
							$("#textoexitoeditar").html("Usted modificó exitosamente los datos del participante");
							$('#modalexitoeditar').modal({
								show : true,
								backdrop : 'static',
								keyboard:false
							});						
						}	
											
					}
					else if(respuesta==0){
						$("#modalimagencargando").modal('hide');
						$("#textoerror").html("Error al registrar participante");
						$('#modalerroreditar').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}
					else if(respuesta==-1){
						$("#modalimagencargando").modal('hide');
						$("#textoerror").html("IE no se ha registrado actualmente en el Programa Educativo");
						$('#modalerroreditar').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}					
				},
				error: function() {
					$("#modalimagencargando").modal('hide');
					alert("Error al actualizar Participante. Comunicarse con la Oficina de Tecnología de Información de Sunass");
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
	appaternoestudiante = $("#appaternoestudianteeditar").val();
	apmaternoestudiante = $("#apmaternoestudianteeditar").val();
	nombreestudiante = $("#nombreestudianteeditar").val();
	tipodocumentoestudiante = $("#tipodocumentoestudianteeditar").val();
	nrodocumentoestudiante = $("#nrodocumentoestudianteeditar").val();
	
	fechanacimientoestudiante = $("#fechanacimientoestudianteeditar").val();		
	
	generoestudiante = $("#generoestudianteeditar").val();
	nivelestudiante = $("#nivelestudianteeditar").val();
	gradoestudiante = $("#gradoestudianteeditar").val();
	seccionestudiante = $("#seccionestudianteeditar").val();
	appaternopmt = $("#appaternopmteditar").val();
	apmaternopmt = $("#apmaternopmteditar").val();
	nombrepmt = $("#nombrepmteditar").val();
	parentesco = $("#parentescoeditar").val();
	tipodocumentopmt = $("#tipodocumentopmteditar").val();
	nrodocumentopmt = $("#nrodocumentopmteditar").val();
	nrotelefonopmt = $("#nrotelefonopmteditar").val();
	correoelectronicopmt  = $("#correoelectronicopmteditar").val();
	
	if(appaternoestudiante.trim()==""){
		mensajeValidacionEditar = "Debe ingresar Apellido Paterno de Estudiante"+"<br>";
	}
	if(apmaternoestudiante.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Apellido Materno de Estudiante"+"<br>";
	}
	if(nombreestudiante.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Nombre de Estudiante"+"<br>";
	}
	if(generoestudiante==0){
		mensajeValidacionEditar += "Debe Seleccionar Genero de Estudiante"+"<br>";
	}
	if(tipodocumentoestudiante==0){
		mensajeValidacionEditar += "Debe Seleccionar Tipo de Documento de Estudiante"+"<br>";
	}
	if(nrodocumentoestudiante.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Número de documento de Estudiante"+"<br>";
	}
	if(fechanacimientoestudiante.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Fecha nacimiento de Estudiante"+"<br>";
	}
	if(nivelestudiante==0){
		mensajeValidacionEditar += "Debe Seleccionar Nivel  de Estudiante"+"<br>";
	}
	if(gradoestudiante==0){
		mensajeValidacionEditar += "Debe Seleccionar Grado de Estudiante"+"<br>";
	}
	if(seccionestudiante.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Sección de Estudiante"+"<br>";
	}
	if(!($("#categoriacuentoeditar").is(':checked') || $("#categoriapoesiaeditar").is(':checked') || $("#categoriadibujopinturaeditar").is(':checked') || $("#categoriacomposicionmusicaleditar").is(':checked') || $("#categoriaahorroaguaeditar").is(':checked'))){		
		mensajeValidacionEditar += "Debe Seleccionar una Categoria de Estudiante"+"<br>";
	}
	if(!($("#modalidadpostulacionindividualeditar").is(':checked') || $("#modalidadpostulaciongrupaleditar").is(':checked'))){		
		mensajeValidacionEditar += "Debe Seleccionar Modalidad de Postulación de Estudiante"+"<br>";
	}
	if(appaternopmt.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Apellido Paterno del Padre,Madre o Tutor"+"<br>";
	}
	if(apmaternopmt.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Apellido Materno del Padre,Madre o Tutor"+"<br>";
	}
	if(nombrepmt.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Nombre del Padre , Madre o Tutor"+"<br>";
	}
	if(parentesco==0){
		mensajeValidacionEditar += "Debe Seleccionar Parentesco del Padre , Madre o Tutor"+"<br>";
	}
	if(tipodocumentopmt==0){
		mensajeValidacionEditar += "Debe Seleccionar Tipo de documento del Padre , Madre o Tutor"+"<br>";
	}
	if(nrodocumentopmt.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Número de Documento del Padre , Madre o Tutor"+"<br>";
	}
	if(nrotelefonopmt.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Número de Télefono del Padre , Madre o Tutor"+"<br>";
	}
	if(correoelectronicopmt.trim()==""){
		mensajeValidacionEditar += "Debe ingresar Correo Electrónico del Padre , Madre o Tutor"+"<br>";
	}
	if(!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test(correoelectronicopmt))){		
		mensajeValidacionEditar += "Debe ingresar Correctamente el Correo Electrónico del Padre , Madre o Tutor"+"<br>";
	}
	
	if(bandera){
		var fichaparticipanteeditar_name = (fichaparticipanteeditar.files[0]).name;
		var fichaparticipanteeditar_size = (fichaparticipanteeditar.files[0]).size;
		if(fichaparticipanteeditar_size >5000000){
			mensajeValidacionEditar += "El archivo no debe superar los 5MB"+"<br>";
		}
		var ext = fichaparticipanteeditar_name.split('.').pop();
		ext = ext.toLowerCase();
		switch(ext){
			case 'jpg': break;
			case 'png': break;
			case 'jpeg': break;
			case 'pdf': break;
			case 'docx': break;
			default: mensajeValidacionEditar += "El archivo debe tener extensión jpg , png, pdf, word"+"<br>";	
		}	
	}
	console.log("mensajeValidacionEditar .. "+ mensajeValidacionEditar);
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
    if((key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key ==32)){    
        return true;
    }
    else{
        return false;
    }	
}

function filterIntNroDocIdentidadEstudianteEditar(evt,input){
	
	if($("#tipodocumentoestudianteeditar").val()==1){
	    var key = window.Event ? evt.which : evt.keyCode;    
	    var chark = String.fromCharCode(key);
	    var tempValue = input.value+chark;
	    if(key >= 48 && key <= 57){
	    	if($("#tipodocumentoestudianteeditar").val() == 1){
				if($("#nrodocumentoestudianteeditar").val().trim().length<8)
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
	else if($("#tipodocumentoestudianteeditar").val()==2){
		if($("#nrodocumentoestudianteeditar").val().trim().length<12)
			return true;
		return false;
	}
}

function filterIntNroDocIdentidadPmtEditar(evt,input){
	
	if($("#tipodocumentopmteditar").val()==1){
	    var key = window.Event ? evt.which : evt.keyCode;    
	    var chark = String.fromCharCode(key);
	    var tempValue = input.value+chark;
	    if(key >= 48 && key <= 57){
	    	if($("#tipodocumentopmteditar").val() == 1){
				if($("#nrodocumentopmteditar").val().trim().length<8)
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
	else if($("#tipodocumentopmteditar").val()==2){
		if($("#nrodocumentopmteditar").val().trim().length<12)
			return true;
		return false;
	}
}


function limpiarControlesEditar(){
	
	$("#appaternoestudianteeditar").val("");
	$("#apmaternoestudianteeditar").val("");
	$("#nombreestudianteeditar").val("");
	$("#tipodocumentoestudianteeditar").val("0");
	$("#nrodocumentoestudianteeditar").val("");
	$("#fechanacimientoestudianteeditar").val("");
	$("#generoestudianteeditar").val("0");
	$("#nivelestudianteeditar").val("0");
	$("#gradoestudianteeditar").val("0");
	$("#seccionestudianteeditar").val("");
	$("#categoriacuentoeditar").prop("checked", false);
	$("#categoriapoesiaeditar").prop("checked", false);
	$("#categoriadibujopinturaeditar").prop("checked", false);
	$("#categoriacomposicionmusicaleditar").prop("checked", false);
	$("#categoriaahorroaguaeditar").prop("checked", false);
	$("#modalidadpostulacionindividualeditar").prop("checked", false);
	$("#modalidadpostulaciongrupaleditar").prop("checked", false);
	$("#appaternopmteditar").val("");
	$("#apmaternopmteditar").val("");
	$("#nombrepmteditar").val("");
	$("#parentescoeditar").val("");
	$("#tipodocumentopmteditar").val("");
	$("#nrodocumentopmteditar").val("");
	$("#nrotelefonopmteditar").val("");
	$("#correoelectronicopmteditar").val("");
	$("#fichaparticipanteeditareditar").val("");

}



