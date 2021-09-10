var url_base = location.origin+"/";
var data;
var appaternoestudiante="",apmaternoestudiante="",nombreestudiante="",tipodocumentoestudiante="",nrodocumentoestudiante="",fechanacimientoestudiante="";
var generoestudiante="",nivelestudiante="",gradoestudiante="",seccionestudiante="";
var categoriacuento=0, categoriapoesia=0,categoriadibujopintura=0,categoriacomposicionmusical=0,categoriaahorroagua=0,modalidadpostulacionindividual=0,modalidadpostulaciongrupal=0;
var appaternopmt="",apmaternopmt="",nombrepmt="",parentesco="",tipodocumentopmt="",nrodocumentopmt="",nrotelefonopmt="",correoelectronicopmt="";
var mensajeValidacion=false;

$(document).ready(function(){

	$("#nrodocumentoestudiante").prop("disabled",true);
	$("#nrodocumentopmt").prop("disabled",true);

	$("#tipodocumentoestudiante").change(function(){
		$("#nrodocumentoestudiante").focus();
	});
	
	$("#gradoestudiante").change(function(){
		$("#seccionestudiante").focus();
	});
	
	$("#tipodocumentopmt").change(function(){
		$("#nrodocumentopmt").focus();
	});

	$("#fechanacimientoestudiante").datepicker({
		locale: 'es-es',
	    format: 'dd/mm/yyyy',
	    uiLibrary: 'bootstrap4'
	});
	
	$("#btncerrarmensajeexito").click(function(){			
		$("#modalexito").modal('hide');
		limpiarControles();	
		$("#modalagregarParticipante").modal('hide');
	});
		
	$("#btncerrarmensajeinformacion").click(function(){
		$("#modalinformacion").modal('hide');
	});
	
	$("#btncerrarmensajeerror").click(function(){
		$("#modalerror").modal('hide');
	});
	
	$("#btncerrarmensajeconfirmacion").click(function(){
		$("#modalconfirmacion").modal('hide');
	});	
	
	$("#btncancelarparticipanteagregar").click(function(){
		limpiarControles();	
		$("#modalagregarParticipante").modal('hide');
	});	
	
	$("#tipodocumentoestudiante").on("change",function(){
		
		if($("#tipodocumentoestudiante").val()!=""){
			$("#nrodocumentoestudiante").prop('disabled', false);
			$("#nrodocumentoestudiante").val("");
			$("#nrodocumentoestudiante").focus();
		}
		else{
			$("#nrodocumentoestudiante").val("");
			$("#nrodocumentoestudiante").prop('disabled', true);
		}
	});
	
	$("#tipodocumentopmt").on("change",function(){
		if($("#tipodocumentopmt").val()!=""){
			$("#nrodocumentopmt").prop('disabled', false);
			$("#nrodocumentopmt").val("");
			$("#nrodocumentopmt").focus();
		}
		else{
			$("#nrodocumentopmt").val("");
			$("#nrodocumentopmt").prop('disabled', true);
		}
	});
	
	$("#nivelestudiante").click(function(){	
		nivelestudiante = $("#nivelestudiante").val();
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
					$("#gradoestudiante").html(contenido);
				},
				error: function() {
					alert("Error al buscar grado por nivel");
			    }
			});
		 
	});
	
	$("#btnagregarparticipante").click(function(){
		
		if(validarCampos()){
		
			var contenido_categoria = "";
			var contenido_modalidad = "";
		
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
			
			if($("#categoriacuento").is(':checked')){
				categoriacuento = 1;
				contenido_categoria = "Cuento /";
			}					
			if($("#categoriapoesia").is(':checked')){
				categoriapoesia = 1;
				contenido_categoria += "Poesía /";
			}					
			if($("#categoriadibujopintura").is(':checked')){
				categoriadibujopintura = 1;
				contenido_categoria += "Díbujo o Pintura /";
			}					
			if($("#categoriacomposicionmusical").is(':checked')){
				categoriacomposicionmusical = 1;
				contenido_categoria += "Composición musical /";
			}				
			if($("#categoriaahorroagua").is(':checked')){
				categoriaahorroagua = 1;
				contenido_categoria += "Ahorro agua/";
			}					
			if($("#modalidadpostulacionindividual").is(':checked')){
				modalidadpostulacionindividual = 1;
				contenido_modalidad += "Individual /";
			}					
			if($("#modalidadpostulaciongrupal").is(':checked')){
				modalidadpostulaciongrupal = 1;
				contenido_modalidad += "Grupal/";
			}
			
			contenido_categoria = contenido_categoria.slice(0, -1);
			contenido_modalidad = contenido_modalidad.slice(0, -1);
			
			data = {
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
			
			$.ajax({
				type : "POST",
			    contentType : "application/json",
			    url : url_base + "pedesa/registrarparticipante",
			    data : JSON.stringify(data),
			    dataType : 'json',
				success: function(respuesta) {
					if(respuesta>0){
					
						var data = new FormData();
						data.append('file',fichaparticipante.files[0]);
						data.append('id',respuesta);						
						$.ajax({
					        type: "POST",
					        enctype: 'multipart/form-data',
					        url: url_base + "pedesa/subirarchivoparticipante",
					        data: data,
					        processData: false, 
					        contentType: false,
					        cache: false,
					        timeout: 600000,
					        success: function (data) {					 
					            $("#modalimagencargando").modal('hide');
					            table_lista_participantes.row.add({
								        "id": respuesta,
								        "appaterno": appaternoestudiante,
								        "apmaterno": apmaternoestudiante,
								        "nombre": nombreestudiante,
								        "tipodocumento": $("#tipodocumentoestudiante option:selected").html(),
								        "nrodocumento": nrodocumentoestudiante,
								        "categoria" : contenido_categoria,
								        "modalidad" : contenido_modalidad,
								        "defaultContent" : '<img src="./images/svg/file-pdf-regular.svg" class="archivo" style="width:20px; cursor:pointer"/>',
								        "defaultContent" : '<img src="./images/svg/file-pdf-regular.svg" class="ver" style="width:20px; cursor:pointer"/>',
								        "defaultContent" : '<img src="./images/svg/edit-regular.svg" class="editar" style="width:20px; cursor:pointer" />',
								        "defaultContent" : '<img src="./images/svg/eliminar-alt-regular.svg" class="eliminar" style="width:20px; cursor:pointer" />'
								    }).draw();	
								$("#textoexito").html("Usted agregó exitosamente a un nuevo participante");
								$('#modalexito').modal({
									show : true,
									backdrop : 'static',
									keyboard:false
								});
								
								limpiarControles();
					 
					        },
					        error: function (e) {
					            console.log("ERROR : ", e);
					        }
					    });						
					}
					else if(respuesta == -100){
						 window.location = url_base + "pedesa";
					}
					else if(respuesta==0){
						$("#modalimagencargando").modal('hide');
						$("#textoerror").html("Error al registrar participante");
						$('#modalerror').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}
					else if(respuesta==-1){
						$("#modalimagencargando").modal('hide');
						$("#textoerror").html("IE no se ha registrado actualmente en el Programa Educativo");
						$('#modalerror').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}
					else if(respuesta==-2){
						$("#modalimagencargando").modal('hide');
						$("#textoerror").html("Ya existe un participante registrado en el sistema con el mismo Tipo y número de documento");
						$('#modalerror').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}
				},
				error: function() {
					$("#modalimagencargando").modal('hide');
					alert("Error al Registrarse en el Programa Educativo. Comunicarse con la Oficina de Tecnología de Información de Sunass");
			    }
			});		
		}
		else{
			$("#textoinformacion").html("<div style='color:red' class='text-left'>"+mensajeValidacion+"</div>");
			$('#modalinformacion').modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
		}
	});

});

function validarCampos(){
	
	mensajeValidacion = "";
	appaternoestudiante = $("#appaternoestudiante").val();
	apmaternoestudiante = $("#apmaternoestudiante").val();
	nombreestudiante = $("#nombreestudiante").val();
	tipodocumentoestudiante = $("#tipodocumentoestudiante").val();
	nrodocumentoestudiante = $("#nrodocumentoestudiante").val();
	
	fechanacimientoestudiante = $("#fechanacimientoestudiante").val();		
	
	generoestudiante = $("#generoestudiante").val();
	nivelestudiante = $("#nivelestudiante").val();
	gradoestudiante = $("#gradoestudiante").val();
	seccionestudiante = $("#seccionestudiante").val();
	appaternopmt = $("#appaternopmt").val();
	apmaternopmt = $("#apmaternopmt").val();
	nombrepmt = $("#nombrepmt").val();
	parentesco = $("#parentesco").val();
	tipodocumentopmt = $("#tipodocumentopmt").val();
	nrodocumentopmt = $("#nrodocumentopmt").val();
	nrotelefonopmt = $("#nrotelefonopmt").val();
	correoelectronicopmt  = $("#correoelectronicopmt").val();
	
	if(appaternoestudiante.trim()==""){
		mensajeValidacion = "Debe ingresar Apellido Paterno de Estudiante"+"<br>";
	}
	if(apmaternoestudiante.trim()==""){
		mensajeValidacion += "Debe ingresar Apellido Materno de Estudiante"+"<br>";
	}
	if(nombreestudiante.trim()==""){
		mensajeValidacion += "Debe ingresar Nombre de Estudiante"+"<br>";
	}
	if(generoestudiante==0){
		mensajeValidacion += "Debe Seleccionar Genero de Estudiante"+"<br>";
	}
	if(tipodocumentoestudiante==0){
		mensajeValidacion += "Debe Seleccionar Tipo de Documento de Estudiante"+"<br>";
	}
	if(nrodocumentoestudiante.trim()==""){
		mensajeValidacion += "Debe ingresar Número de documento de Estudiante"+"<br>";
	}
	if(fechanacimientoestudiante.trim()==""){
		mensajeValidacion += "Debe ingresar Fecha nacimiento de Estudiante"+"<br>";
	}
	if(nivelestudiante==0){
		mensajeValidacion += "Debe Seleccionar Nivel  de Estudiante"+"<br>";
	}
	if(gradoestudiante==0){
		mensajeValidacion += "Debe Seleccionar Grado de Estudiante"+"<br>";
	}
	if(seccionestudiante.trim()==""){
		mensajeValidacion += "Debe ingresar Sección de Estudiante"+"<br>";
	}
	if(!($("#categoriacuento").is(':checked') || $("#categoriapoesia").is(':checked') || $("#categoriadibujopintura").is(':checked') || $("#categoriacomposicionmusical").is(':checked') || $("#categoriaahorroagua").is(':checked'))){		
		mensajeValidacion += "Debe Seleccionar una Categoria de Estudiante"+"<br>";
	}
	if(!($("#modalidadpostulacionindividual").is(':checked') || $("#modalidadpostulaciongrupal").is(':checked'))){		
		mensajeValidacion += "Debe Seleccionar Modalidad de Postulación de Estudiante"+"<br>";
	}
	if(appaternopmt.trim()==""){
		mensajeValidacion += "Debe ingresar Apellido Paterno del Padre,Madre o Tutor"+"<br>";
	}
	if(apmaternopmt.trim()==""){
		mensajeValidacion += "Debe ingresar Apellido Materno del Padre,Madre o Tutor"+"<br>";
	}
	if(nombrepmt.trim()==""){
		mensajeValidacion += "Debe ingresar Nombre del Padre , Madre o Tutor"+"<br>";
	}
	if(parentesco==0){
		mensajeValidacion += "Debe Seleccionar Parentesco del Padre , Madre o Tutor"+"<br>";
	}
	if(tipodocumentopmt==0){
		mensajeValidacion += "Debe Seleccionar Tipo de documento del Padre , Madre o Tutor"+"<br>";
	}
	if(nrodocumentopmt.trim()==""){
		mensajeValidacion += "Debe ingresar Número de Documento del Padre , Madre o Tutor"+"<br>";
	}
	if(nrotelefonopmt.trim()==""){
		mensajeValidacion += "Debe ingresar Número de Télefono del Padre , Madre o Tutor"+"<br>";
	}
	if(correoelectronicopmt.trim()==""){
		mensajeValidacion += "Debe ingresar Correo Electrónico del Padre , Madre o Tutor"+"<br>";
	}
	/*if(!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test(correoelectronicopmt))){		
		mensajeValidacion += "Debe ingresar Correctamente el Correo Electrónico del Padre , Madre o Tutor"+"<br>";
	}*/
	if(correoelectronicopmt.indexOf('@', 0) == -1 || correoelectronicopmt.indexOf('.', 0) == -1) {
		mensajeValidacion += "Debe ingresar Correctamente el Correo Electrónico del Padre , Madre o Tutor"+"<br>";
	}
	
	console.log("fichaparticipante.files[0] : " + fichaparticipante.files[0]);
	
	if(fichaparticipante.files[0] === undefined){
		mensajeValidacion += "Debe adjuntar un archivo"+"<br>";	
	}
	else{
		var fichaparticipante_name = (fichaparticipante.files[0]).name;
		var fichaparticipante_size = (fichaparticipante.files[0]).size;
		if(fichaparticipante_size >5000000){
			mensajeValidacion += "El archivo no debe superar los 5MB"+"<br>";
		}
		var ext = fichaparticipante_name.split('.').pop();
		ext = ext.toLowerCase();
		switch(ext){
			case 'jpg': break;
			case 'png': break;
			case 'jpeg': break;
			case 'pdf': break;
			case 'docx': break;
			default: mensajeValidacion += "El archivo debe tener extensión jpg , png, pdf, word"+"<br>";	
		}
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

function filterIntNroDocIdentidadEstudiante(evt,input){
	
	if($("#tipodocumentoestudiante").val()==1){
	    var key = window.Event ? evt.which : evt.keyCode;    
	    var chark = String.fromCharCode(key);
	    var tempValue = input.value+chark;
	    if(key >= 48 && key <= 57){
	    	if($("#tipodocumentoestudiante").val() == 1){
				if($("#nrodocumentoestudiante").val().trim().length<8)
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
	else if($("#tipodocumentoestudiante").val()==2){
		if($("#nrodocumentoestudiante").val().trim().length<12)
			return true;
		return false;
	}
}

function filterIntNroDocIdentidadPmt(evt,input){
	
	if($("#tipodocumentopmt").val()==1){
	    var key = window.Event ? evt.which : evt.keyCode;    
	    var chark = String.fromCharCode(key);
	    var tempValue = input.value+chark;
	    if(key >= 48 && key <= 57){
	    	if($("#tipodocumentopmt").val() == 1){
				if($("#nrodocumentopmt").val().trim().length<8)
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
	else if($("#tipodocumentopmt").val()==2){
		if($("#nrodocumentopmt").val().trim().length<12)
			return true;
		return false;
	}
}


function filterCadena(evt,input){
	
	var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if((key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key ==32)   || (key ==241) || (key ==209) || (key ==225) || (key ==233) || (key ==237) || (key ==243) || (key ==250)  || (key ==193) || (key ==201) || (key ==205) || (key ==211) || (key ==218)    ){    
        return true;
    }
    else{
        return false;
    }	
}

function limpiarControles(){
	
	$("#appaternoestudiante").val("");
	$("#apmaternoestudiante").val("");
	$("#nombreestudiante").val("");
	$("#tipodocumentoestudiante").val("0");
	$("#nrodocumentoestudiante").val("");
	$("#fechanacimientoestudiante").val("");
	$("#generoestudiante").val("0");
	$("#nivelestudiante").val("0");
	$("#gradoestudiante").val("0");
	$("#seccionestudiante").val("");
	$("#categoriacuento").prop("checked", false);
	$("#categoriapoesia").prop("checked", false);
	$("#categoriadibujopintura").prop("checked", false);
	$("#categoriacomposicionmusical").prop("checked", false);
	$("#categoriaahorroagua").prop("checked", false);
	$("#modalidadpostulacionindividual").prop("checked", false);
	$("#modalidadpostulaciongrupal").prop("checked", false);
	$("#appaternopmt").val("");
	$("#apmaternopmt").val("");
	$("#nombrepmt").val("");
	$("#parentesco").val("");
	$("#tipodocumentopmt").val("");
	$("#nrodocumentopmt").val("");
	$("#nrotelefonopmt").val("");
	$("#correoelectronicopmt").val("");
	$("#fichaparticipante").val("");
}



