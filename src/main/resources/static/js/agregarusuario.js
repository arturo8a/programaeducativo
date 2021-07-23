$(document).ready(function(){
	
	$("#btncancelRegistro").on("click",function(){
		$("#modalAlianzaEstrategica").modal('hide');
		$("#btnaceptarRegistro").prop("disabled",false);
	});
	
	$("#btncerrarmensajeerror").on("click",function(){
		$("#modalerror").modal('hide');
	});
	
	$("#perfilregusuComiteEvaluador").on("click",function(){
		if($('#perfilregusuComiteEvaluador').is(':checked') ) {
	    	$("#section-datos-autoridad").show();
		}else{
			$("#section-datos-autoridad").hide();
		}
	});
	
	$("#perfilregusuAuspiciador").on("click",function(){
		if($('#perfilregusuAuspiciador').is(':checked') ) {
	    	$("#section-datos-auspicio").show();
	    	$(".section-datos-auspicio").show();
		}else{
			$("#section-datos-auspicio").hide();
			$(".section-datos-auspicio").hide();
		}
	});
	
	$("#btnAgregarAuspiciador").on("click",function(evt){
		evt.preventDefault();
		htmlAuspicicador();
	});
	
	$("#btnQuitararchivo").on("click",function(){
		$("#fileautoridad").prop("disabled",false);
		$("#fileautoridad").show();
	});
	
	$("#tipocodcontactoresgusu").on("change",function(){
		$("#numdoccontactoregusu").val('');
	});
	
	$(document).on("keyup","[id*=montounitarioauspiciador]",function(){
		var monto = 0;
		$.each($("[id*=montototalauspiciador]"), function( i, value ) {
			if($("#cantidadauspiciador"+(i+1)).val() != '' && $("#montounitarioauspiciador"+(i+1)).val() != '' ){
				$("#montototalauspiciador"+(i+1)).val(parseFloat($("#cantidadauspiciador"+(i+1)).val())*parseFloat($("#montounitarioauspiciador"+(i+1)).val()));
			}

			if(!isNaN(parseFloat($("#montototalauspiciador"+(i+1)).val()))){
				monto += parseFloat($("#montototalauspiciador"+(i+1)).val());
			}else{
				monto = 0;
			}
		});
		$("#montototalform").val(monto);
	});
	$(document).on("keyup","[id*=cantidadauspiciador]",function(){
		var monto = 0;
		$.each($("[id*=montototalauspiciador]"), function( i, value ) {
			if($("#cantidadauspiciador"+(i+1)).val() != '' && $("#montounitarioauspiciador"+(i+1)).val() != '' ){
				$("#montototalauspiciador"+(i+1)).val(parseFloat($("#cantidadauspiciador"+(i+1)).val())*parseFloat($("#montounitarioauspiciador"+(i+1)).val()));
			}

			if(!isNaN(parseFloat($("#montototalauspiciador"+(i+1)).val()))){
				monto += parseFloat($("#montototalauspiciador"+(i+1)).val());
			}else{
				monto = 0;
			}
		});
		$("#montototalform").val(monto);
	});
	
	getUsuario($("#idAlianzaEstrategica").val());
	comboTipoAuspicio();
	$("#fecha_oficio").datepicker({
		locale: 'es-es',
	    format: 'dd/mm/yyyy',
	    uiLibrary: 'bootstrap4'
	});

});

function htmlAuspicicador(){
	var cont = $("[id*=tipocodauspiciador]").length;
	cont = cont+1; 
	html = '';
	html += '   <input type="hidden" name="idauspiciador'+cont+'" id="idauspiciador'+cont+'" value="0"/>';
	html += '	<div class="col-xs-12 col-sm-4 text-left pt-1">';
	html += '		<select class="form-control" id="tipocodauspiciador'+cont+'" name="tipocodauspiciador'+cont+'">';
	html += '			<option value="0">Tipo de documento</option>';
	$.each(htmlTipoAuspicio, function( index, value ) {
		html += '<option value="'+value.id+'">'+value.descripcion+'</option>';
	});
	html += '		</select>';
	html += '	</div>';
	html += '	<div class="col-xs-12 col-sm-4 text-left pt-1">';
	html += '		<input type="text" name="cantidadauspiciador'+cont+'" id="cantidadauspiciador'+cont+'" class="form-control" placeholder="Cantidad" onKeyPress="return filterIntInput(event,this)" maxlength="10"/>';
	html += '	</div>';
	html += '	<div class="col-xs-12 col-sm-4 text-left pt-1">';
	html += '		<input type="text" name="descripcionauspiciador'+cont+'" id="descripcionauspiciador'+cont+'" class="form-control" placeholder="Descripcion" maxlength="60" />';
	html += '	</div>';
	html += '	<div class="col-xs-12 col-sm-4 text-left pt-1">';
	html += '		<input type="text" name="montounitarioauspiciador'+cont+'" id="montounitarioauspiciador'+cont+'" class="form-control" placeholder="Monto Unitario" onKeyPress="return filterFloat(event,this)" maxlength="10"/>';
	html += '	</div>';
	html += '	<div class="col-xs-12 col-sm-4 text-left pt-1">';
	html += '		<input type="text" name="montototalauspiciador'+cont+'" id="montototalauspiciador'+cont+'" class="form-control" placeholder="Monto Total" disabled value="0" onKeyPress="return filterFloat(event,this)" maxlength="10"/>';
	html += '	</div>';
	html += '	<div class="col-xs-12 col-sm-4 text-left">';
	html += '	</div>';
	
	$("#section-datos-auspicio").append(html);
}

function armarAuspicio(){
	var array = [];
	if($("#section-datos-auspicio").is(":visible")){
		$.each($("[id*=tipocodauspiciador]"), function( index, value ) {
			var i = index + 1;
		  	var tipoDoc = $('#tipocodauspiciador'+i).val();
		  	var tipodocumento = {id : tipoDoc};
		  	var cantidad = $('#cantidadauspiciador'+i).val();
		  	var descripcion = $('#descripcionauspiciador'+i).val();
		  	var montounitario = $('#montounitarioauspiciador'+i).val();
		  	var montototal = $('#montototalauspiciador'+i).val();
		  	var id = $('#idauspiciador'+i).val();
		  	
		  	array.push({id,tipodocumento,cantidad,descripcion,montounitario,montototal});
		});
	}else{
		//array = null;
	}
	return array;
}

function armarData(){
	var idUsuario = $("#idAlianzaEstrategica").val();
	var odsresgusu = $("#odsresgusu").val();
	var anioregusu = $("#anioregusu").val();
	var categoriaregusu = $("#categoriaregusu").val();
	var entidadregusu = $("#entidadregusu").val();
	var direccionregusu = $("#direccionregusu").val();
	var comitetecnico = "0";
	if($('#perfilregusuComiteTecnico').is(':checked') ) {
	    comitetecnico = "1";
	}
	var comiteevaluador = "0";
	if($('#perfilregusuComiteEvaluador').is(':checked') ) {
	    comiteevaluador = "1";
	}
	var auspiciador = "0";
	if($('#perfilregusuAuspiciador').is(':checked') ) {
	    auspiciador = "1";
	}
	var aliado = "0";
	if($('#perfilregusuAliado').is(':checked') ) {
	    aliado = "1";
	}
	var numcontactoresgusu = $('#numcontactoresgusu').val();
	var apepatcontactoregusu = $("#apepatcontactoregusu").val();
	var apematcontactoregusu = $("#apematcontactoregusu").val();
	var nombrecontactoregusu = $("#nombrecontactoregusu").val();
	var tipocodcontactoresgusu = $("#tipocodcontactoresgusu").val();
	var numdoccontactoregusu = $("#numdoccontactoregusu").val();
	var numtelcontactoregusu = $("#numtelcontactoregusu").val();
	var numtel2contactoregusu = $("#numtel2contactoregusu").val();
	var correocontactoregusu = $("#correocontactoregusu").val();
	var cargocontactoregusu = $("#cargocontactoregusu").val();
	var apepatautoridad = $("#apepatautoridad").val();
	var apematautoridad = $("#apematautoridad").val();
	var nombreautoridad = $("#nombreautoridad").val();
	var correoautoridad = $("#correoautoridad").val();
	var cargoautoridad = $("#cargoautoridad").val();
	var usuarioautoridad = $("#usuarioautoridad").val();
	var passwordautoridad = $("#passwordautoridad").val();
	var sendOficio = "";
	if($('#sendOficioSi').is(':checked') ) {
	    sendOficio = $('#sendOficioSi').val();
	}else if($('#sendOficioSi').is(':checked')){
		sendOficio = $('#sendOficioNo').val();
	}
	var numoficioautoridad = $('#numoficioautoridad').val();
	var fecha_oficio = formatoFecha($('#fecha_oficio').val());
	
	var filenamedocaut = "";
	
	if(fileautoridad.files.length > 0){
		filenamedocaut = fileautoridad.files[0].name;
	}
	
	//var arrayAuspicio = armarAuspicio();
	
	var data = {
		id : idUsuario,
		ods : {
				id : odsresgusu
			},
		anio : anioregusu,
		categoria : {
				id : categoriaregusu
			},
		entidad : entidadregusu,
		direccion : direccionregusu,
		comitetecnico : comitetecnico,
		comiteevaluador : comiteevaluador,
		auspiciador : auspiciador,
		aliado : aliado,
		numcontaccto : numcontactoresgusu,
		apepatcontacto : apepatcontactoregusu,
		apematcontacto : apematcontactoregusu,
		nombrecontacto : nombrecontactoregusu,
		tipodocumento : {
			id : tipocodcontactoresgusu
		},
		numdocumento : numdoccontactoregusu,
		telefonouno : numtelcontactoregusu,
		telefonodos : numtel2contactoregusu,
		correocontato : correocontactoregusu,
		cargocontacto : cargocontactoregusu,
		apepatautoridad : apepatautoridad,
		apematautoridad : apematautoridad,
		nombresautoridad : nombreautoridad,
		correoautoridad : correoautoridad,
		cargoautoridad : cargoautoridad,
		usuarioautoridad : usuarioautoridad,
		passwordautoridad : passwordautoridad,
		enviaroficio : sendOficio,
		numoficio : numoficioautoridad,
		fecha_oficio : fecha_oficio,
		docoficio : filenamedocaut,
		auspicios : armarAuspicio()

	}
	console.log(data);
	return data;
}


$("#btnaceptarRegistro").on("click",function(){ console.log(armarData());
    
    $("#btnaceptarRegistro").prop("disabled",true);
    
    $("#modalimagencargando").modal({
		show : true,
		backdrop : 'static',
		keyboard:false
	});
	
	if(!validarCampos()){
		$("#textoerror").html("<div style='color:red' class='text-left'>"+contentMensajeError+"</div>");
		$("#modalerror").modal();
		$("#modalimagencargando").modal('hide');
		$("#btnaceptarRegistro").prop("disabled",false);
		return true;
	}
    
    $.ajax({
		type : "POST",
	    contentType : "application/json",
	    url : url_base + "pedesa/saveusuarioalianza",
	    data : JSON.stringify(armarData()),
	    dataType : 'json',
		success: function(respuesta) {
			if(respuesta>0){
				
				if(fileautoridad.files.length > 0){
					subirDocumento(respuesta);
				}else{
					$("#btnaceptarRegistro").prop("disabled",false);
					$("#modalimagencargando").modal('hide');
					$("#modalAlianzaEstrategica").modal('hide');
				}
				//listarAlianzaEstrategica();
			}else{
				$("#btnaceptarRegistro").prop("disabled",false);
				$("#modalimagencargando").modal('hide');
				alert("Exception al registrar");
			}
		},
		error: function() {
				$("#modalimagencargando").modal('hide');
				$("#btnaceptarRegistro").prop("disabled",false);
				alert("Exception al registrar");
		    }
		});
   
    
});

var htmlTipoAuspicio;

function comboTipoAuspicio(){
	$.ajax({
		type : "GET",
	    contentType : "application/json",
	    url : url_base + "pedesa/getTipoAuspicio",
	    dataType : 'json',
		success: function(respuesta) {
			 /*htmlTipoAuspicio = '<opcion value="0">Tipo de documento</option>';
			$.each(respuesta, function( index, value ) {
				htmlTipoAuspicio += '<opcion value="'+value.id+'">'+value.descripcion+'</option>';
			});*/
			htmlTipoAuspicio = respuesta;
		},
		error: function() {
			alert("Exception al cargar combo auspicio");
		}
	});
}


function formatoFecha(fecha){
	if(fecha == "") fecha = "01/01/2020";
	var date = fecha.split("/");
	return date[2] +'-'+ date[1] +'-'+ date[0];
}

function getUsuario(idUsuario){
	$("#modalimagencargando").modal({
		show : true,
		backdrop : 'static',
		keyboard:false
	});
	
	$.ajax({
		type : "GET",
	    contentType : "application/json",
	    url : url_base + "pedesa/formactualizarusuario/"+idUsuario,
	    dataType : 'json',
		success: function(respuesta) {console.log(respuesta);
			
			if(respuesta.id != null){
				$("#idAlianzaEstrategica").val(respuesta.id);
				$("#odsresgusu").val(respuesta.ods.id);
				$("#anioregusu").val(respuesta.anio);
				$("#categoriaregusu").val(respuesta.categoria.id);
				$("#entidadregusu").val(respuesta.entidad);
				$("#direccionregusu").val(respuesta.direccion);
				
				if(respuesta.comitetecnico == "1"){
					$('#perfilregusuComiteTecnico').prop("checked",true);
				}
				
				if(respuesta.comiteevaluador == "1"){
					$('#perfilregusuComiteEvaluador').prop("checked",true);
					$("#section-datos-autoridad").show();
					$("#apepatautoridad").val(respuesta.apepatautoridad);
					$("#apematautoridad").val(respuesta.apematautoridad);
					$("#nombreautoridad").val(respuesta.nombresautoridad);
					$("#correoautoridad").val(respuesta.correoautoridad);
					$("#cargoautoridad").val(respuesta.cargoautoridad);
					$("#usuarioautoridad").val(respuesta.usuarioautoridad);
					$("#passwordautoridad").val(respuesta.passwordautoridad);
					if(respuesta.enviaroficio == "0"){
						$('#sendOficioSi').prop("checked",false);
						$('#sendOficioNo').prop("checked",true);
					}
					$("#numoficioautoridad").val(respuesta.numoficio);
					$("#fecha_oficio").val(respuesta.fechadocformat);
					$("#fileautoridad").prop("disabled",true);
					$("#textArchivoFile").text(respuesta.docoficio);
				}
				
				if(respuesta.auspiciador == "1"){
					$('#perfilregusuAuspiciador').prop("checked",true);
					$("#section-datos-auspicio").show();
					$(".section-datos-auspicio").show();
					$.each(respuesta.auspicios, function( index, value ) {
						if(index > 0){
							htmlAuspicicador();
						}
						var i = index+1; 
						$('#tipocodauspiciador'+i).val(value.tipodocumento.id);
		  				$('#cantidadauspiciador'+i).val(value.cantidad);
		  				$('#descripcionauspiciador'+i).val(value.descripcion);
		  				$('#montounitarioauspiciador'+i).val(value.montounitario);
		  				$('#montototalauspiciador'+i).val(value.montototal);
		  				$('#idauspiciador'+i).val(value.id);
					});
					
				}
				
				if(respuesta.aliado == "1"){
					$('#perfilregusuAliado').prop("checked",true);
				}
				
				$('#numcontactoresgusu').val(respuesta.numcontaccto);
				$("#apepatcontactoregusu").val(respuesta.apepatcontacto);
				$("#apematcontactoregusu").val(respuesta.apematcontacto);
				$("#nombrecontactoregusu").val(respuesta.nombrecontacto);
				$("#tipocodcontactoresgusu").val(respuesta.tipodocumento.id);
				$("#numdoccontactoregusu").val(respuesta.numdocumento);
				$("#numtelcontactoregusu").val(respuesta.telefonouno);
				$("#numtel2contactoregusu").val(respuesta.telefonodos);
				$("#correocontactoregusu").val(respuesta.correocontato);
				$("#cargocontactoregusu").val(respuesta.cargocontacto);
				var monto = 0;
				$.each($("[id*=montototalauspiciador]"), function( i, value ) {
					if($("#cantidadauspiciador"+(i+1)) != '' && $("#montounitarioauspiciador"+(i+1)) != '' ){
						$("#montototalauspiciador"+(i+1)).val(parseFloat($("#cantidadauspiciador"+(i+1)).val())*parseFloat($("#montounitarioauspiciador"+(i+1)).val()));
					}
		
					if(!isNaN(parseFloat($("#montototalauspiciador"+(i+1)).val()))){
						monto += parseFloat($("#montototalauspiciador"+(i+1)).val());
					}else{
						monto = 0;
					}
				});
				$("#montototalform").val(monto);
			}else{
				$('.alert').alert('close');
				$("#fileautoridad").show();
			}
			
			
			
			
			
			$("#modalimagencargando").modal('hide');
		},
		error: function() {
				$("#modalimagencargando").modal('hide');
				alert("Exception al registrar");
	    }
	});
}

var contentMensajeError ="";

function validarCampos(){
	var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
	var status = true;
	contentMensajeError = "";
	if($("#odsresgusu").val() == "0"){
		contentMensajeError += "Debe seleccionar ODS"+"</br>";
		status = false;
	}
	if($("#categoriaregusu").val() == "0"){
		contentMensajeError += "Debe seleccionar Categoría"+"</br>";
		status = false;
	}
	if($("#entidadregusu").val() == "0"){
		contentMensajeError += "Debe ingresar Entidad"+"</br>";
		status = false;
	}
	if($("#direccionregusu").val() == "0"){
		contentMensajeError += "Debe ingresar Dirección"+"</br>";
		status = false;
	}
	if($('#numcontactoresgusu').val() == "0"){
		contentMensajeError += "Debe seleccionar la cantidad de contactos"+"</br>";
		status = false;
	}
	if($('#apepatcontactoregusu').val() == ""){
		contentMensajeError += "Debe ingresar Apellido Paterno de Contacto"+"</br>";
		status = false;
	}
	if($('#apematcontactoregusu').val() == ""){
		contentMensajeError += "Debe ingresar Apellido Materno de Contacto"+"</br>";
		status = false;
	}
	if($('#nombrecontactoregusu').val() == ""){
		contentMensajeError += "Debe ingresar Nombres de Contacto"+"</br>";
		status = false;
	}
	if($('#tipocodcontactoresgusu').val() == "0"){
		contentMensajeError += "Debe seleccionar Tipo de Documento de Contacto"+"</br>";
		status = false;
	}
	if($('#numdoccontactoregusu').val() == ""){
		contentMensajeError += "Debe ingresar N° Documento de Contacto"+"</br>";
		status = false;
	}
	if($('#numtelcontactoregusu').val() == ""){
		contentMensajeError += "Debe ingresar N° de teléfono 1 de Contacto"+"</br>";
		status = false;
	}
	if($('#numtel2contactoregusu').val() == ""){
		contentMensajeError += "Debe ingresar N° de teléfono 2 de Contacto"+"</br>";
		status = false;
	}
	if($('#correocontactoregusu').val() == ""){
		contentMensajeError += "Debe ingresar Correo Institucional de Contacto"+"</br>";
		status = false;
	}else if(!pattern.test($('#correocontactoregusu').val())) {
		contentMensajeError += "Debe ingresar Correo Institucional de Autoridad/Representante"+"</br>";
		status = false;
	}
	if($('#cargocontactoregusu').val() == ""){
		contentMensajeError += "Debe ingresar Cargo de Contacto"+"</br>";
		status = false;
	}
	if($("#section-datos-autoridad").is(":visible")){
		if($('#apepatautoridad').val() == ""){
			contentMensajeError += "Debe ingresar Apellido Paterno de Autoridad/Representante"+"</br>";
			status = false;
		}
		if($('#apematautoridad').val() == ""){
			contentMensajeError += "Debe ingresar Apellido Materno de Autoridad/Representante"+"</br>";
			status = false;
		}
		if($('#nombreautoridad').val() == ""){
			contentMensajeError += "Debe ingresar Nombres de Autoridad/Representante"+"</br>";
			status = false;
		}
		if($('#correoautoridad').val() == ""){
			contentMensajeError += "Debe ingresar Correo Institucional de Autoridad/Representante"+"</br>";
			status = false;
		}else if(!pattern.test($('#correoautoridad').val())) {
			contentMensajeError += "Debe ingresar Correo Institucional de Autoridad/Representante"+"</br>";
			status = false;
		}
		if($('#cargoautoridad').val() == ""){
			contentMensajeError += "Debe ingresar Cargo de Autoridad/Representante"+"</br>";
			status = false;
		}
		if($('#usuarioautoridad').val() == ""){
			contentMensajeError += "Debe ingresar Usuario de Autoridad/Representante"+"</br>";
			status = false;
		}
		if($('#passwordautoridad').val() == ""){
			contentMensajeError += "Debe ingresar Contraseña de Autoridad/Representante"+"</br>";
			status = false;
		}
		/*if($('#sendOficioSi').is(':checked') || $('#sendOficioNo').is(':checked')){
			contentMensajeError += "Debe ingresar Cargo de Contacto"+"</br>";
			status = false;
		}*/
		if($('#numoficioautoridad').val() == ""){
			contentMensajeError += "Debe ingresar N° Oficio de Autoridad/Representante"+"</br>";
			status = false;
		}
		if($('#fecha_oficio').val() == ""){
			contentMensajeError += "Debe ingresar Fecha de Oficio de Autoridad/Representante"+"</br>";
			status = false;
		}
		if($('.alert').length == 0 && fileautoridad.files.length == 0){
			contentMensajeError += "Debe ingresar Documento de Autoridad/Representante"+"</br>";
			status = false;
		}else{
			if(fileautoridad.files.length > 0){
				if(fileautoridad.files[0].size >5000000){
					contentMensajeError += "El archivo no debe superar los 5MB"+"<br>";
				}
				var ext = fileautoridad.files[0].name.split('.').pop();
				ext = ext.toLowerCase();
				switch(ext){
					case 'pdf': break;
					default: mensajeValidacion += "El archivo debe tener extensión pdf"+"<br>";	
				}
			}
		}
	}
	
	if($("#section-datos-auspicio").is(":visible")){
		$.each($("[id*=tipocodauspiciador]"), function( index, value ) {
			if($(value).val() == "0"){
				contentMensajeError += "Debe seleccionar Tipo de Documento del Auspiciador"+"</br>";
				status = false;
			}
		});
		$.each($("[id*=cantidadauspiciador]"), function( index, value ) {
			if($(value).val() == ""){
				contentMensajeError += "Debe ingresar Cantidad del Auspiciador"+"</br>";
				status = false;
			}
		});
		$.each($("[id*=descripcionauspiciador]"), function( index, value ) {
			if($(value).val() == ""){
				contentMensajeError += "Debe ingresar Descripcion del Auspiciador"+"</br>";
				status = false;
			}
		});
		$.each($("[id*=montounitarioauspiciador]"), function( index, value ) {
			if($(value).val() == ""){
				contentMensajeError += "Debe ingresar el Monto Unitario del Auspiciador"+"</br>";
				status = false;
			}
		});
		$.each($("[id*=montototalauspiciador]"), function( index, value ) {
			if($(value).val() == ""){
				contentMensajeError += "Debe ingresar Monto Final del Auspiciador"+"</br>";
				status = false;
			}
		});
		
	}
	
	return status;
}


function filterCadena(evt,input){
	
	var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if((key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key ==32)   || (key ==241) || (key ==209) || (key ==225) || (key ==233) || (key ==237) || (key ==243) || (key ==250)  || (key ==193) || (key ==201) || (key ==205) || (key ==211) || (key ==218)   ){    
        return true;
    }
    else{
        return false;
    }	
}

function filterIntInput(evt,input){
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

function filterIntNroDocIdentidad(evt,input){
	console.log($(input).val());
	if($("#tipocodcontactoresgusu").val()=="1"){
	    var key = window.Event ? evt.which : evt.keyCode;    
	    var chark = String.fromCharCode(key);
	    var tempValue = input.value+chark;
	    if(key >= 48 && key <= 57){
	    	if($("#tipocodcontactoresgusu").val() == 1){
				if($(input).val().trim().length<8)
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
	else if($("#tipocodcontactoresgusu").val()=="2"){
		var key = window.Event ? evt.which : evt.keyCode;    
	    var chark = String.fromCharCode(key);
	    var tempValue = input.value+chark;
	    if((key >= 48 && key <= 57) || ((key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key ==32)   || (key ==241) || (key ==209) || (key ==225) || (key ==233) || (key ==237) || (key ==243) || (key ==250)  || (key ==193) || (key ==201) || (key ==205) || (key ==211) || (key ==218))){
	    	if($("#tipocodcontactoresgusu").val() == 2){
				if($(input).val().trim().length<12)
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
}

function filterFloat(evt,input) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    var number = input.value.split('.');
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    //just one dot
    if(number.length>1 && charCode == 46){
         return false;
    }
    //get the carat position
    var caratPos = getSelectionStart(input);
    var dotPos = input.value.indexOf(".");
    if( caratPos > dotPos && dotPos>-1 && (number[1].length > 1)){
        return false;
    }
    return true;
}

function getSelectionStart(o) {
    if (o.createTextRange) {
        var r = document.selection.createRange().duplicate()
        r.moveEnd('character', o.value.length)
        if (r.text == '') return o.value.length
        return o.value.lastIndexOf(r.text)
    } else return o.selectionStart
}

function subirDocumento(id){
	/*Subiendo documento*/
	var data = new FormData();
	data.append('file',fileautoridad.files[0]);
	data.append('id',id);
	$.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: url_base + "pedesa/subirarchivousuarios",
        data: data,
        processData: false, 
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (respuesta) {		console.log(respuesta);
        	console.log("Se cargo el archivo");
        	$("#btnaceptarRegistro").prop("disabled",false);
			$("#modalimagencargando").modal('hide');
			$("#modalAlianzaEstrategica").modal('hide');
        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#modalimagencargando").modal('hide');
        }
    });	
}

/*
var listar = function (){ console.log('listar()');
		
		$("#table_trabajos_pendientes").dataTable().fnDestroy();
		table_lista_aperturar_anio = $('#table_trabajos_pendientes').DataTable({
			dom: 'Bfrtip',
		    "scrollX": true,
		    "searching": true,
		    lengthMenu: [
		        [ 10, 15, 25, -1 ],
		        [ '10 filas', '15 filas', '25 filas', 'Mostrar todas' ]
		    ],
		    "processing":true,
		    language: {
		        "decimal": "",
		        "emptyTable": "No se encontraron registros'",
		        "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
		        "infoEmpty": "Mostrando 0 a 0 de 0 Entradas",
		        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
		        "infoPostFix": "",
		        "thousands": ",",
		        "lengthMenu": "Mostrar _MENU_ Entradas",
		        "loadingRecords": "Cargando Registros...",
		        "processing": "Procesando...",
		        "search": "Buscar:",
		        "zeroRecords": " ",//No existen registros
		        "paginate": {
		            "first": "Primero",
		            "last": "Ultimo",
		            "next": "Siguiente",
		            "previous": "Anterior"
		        }
		    },
		    'ajax' : {
		        "url" : url_base + 'pedesa/listatrabpendientesasignados',
		        "type" : "GET",
		        "dataSrc" : ""
		    },
		    "deferRender": true,
		    'columnDefs': [],
		    'columns' : [
		    	{ 
		    		"data": null, "render": function (data, type, full, meta) { return meta.row + 1; }
		    	},
		    	{ 'data' : 'anio' },
		        { 'data' : 'codigo' },
		        { 'data' : 'ods' },
		        { 'data' : 'iiee' },
		        { 'data' : 'categoria' },
		        { 'data' : 'modalidad' },
		        { 'data' : 'titulotrabajo' },
		       	{ 'data' : 'nivelparticipacion' },
		        { 'data' : 'codigo' ,
	                   render: function(data, type) {
	                   		var x = '<img src="./images/iconos_nd/pdf1.svg" class="fichatrabajo" data-id="'+data+'" style="width:20px; cursor:pointer"/>';
                            return x;
                        }
                },
                { 'data' : 'codigo' ,
	                   render: function(data, type) {
	                   		var x = '<img src="./images/svg/eye-solid.svg" class="evidencia" data-id="'+data+'" style="width:20px; cursor:pointer"/>';
                            return x;
                        }
                },
		        { 'data' : 'codigo' ,
	                   render: function(data, type) {
	                   		var x = '<img src="./images/svg/eye-solid.svg" class="trabajo" data-id="'+data+'" style="width:20px; cursor:pointer"/>';
                            return x;
                        }
                },
		        { 'data' : 'permisos' },
		        { 'data' : 'codigo' ,
	                   render: function(data, type) {
	                   		var x = "<button type='button' data-id='"+data+"' class='registrarEvaliacion btn btn-primary'>Evaluar</button>";

                            return x;
                        }
               }
		    ],
		    buttons: []
		});
		obtener_data_form("#table_trabajos_pendientes tbody",table_lista_aperturar_anio);
		obtener_fichatrabajo("#table_trabajos_pendientes tbody",table_lista_aperturar_anio);
		obtener_evidencia("#table_trabajos_pendientes tbody",table_lista_aperturar_anio);
		obtener_trabajos("#table_trabajos_pendientes tbody",table_lista_aperturar_anio);
}

$('#table_trabajos_pendientes').DataTable().on("draw", function(){
	$("#consulta_menu").prop("disabled",false);
	$("#btnbuscar").prop("disabled",false);
});

$.fn.dataTable.ext.search.push(
	function( settings, data, dataIndex ) {
		console.log("settings.nTable.id :" + settings.nTable.id);
		if ( settings.nTable.id !== 'table_trabajos_pendientes' ) {
	        return true;
	    }
	    
	    var ods = $('#ods').val();
        var anio = $('#anio').val();
        var modalidad = $('#modalidad').val();
        var categoria = $('#categoria').val();
        var nivel = $('#nivel').val();
        var txtnombreIE = $('#txtnombreIE').val();
        
        if(!filtraSelect(ods,data[3]))
			return false;
		if(!filtraSelect(anio,data[1]))
			return false;	
		if(!filtraSelect(modalidad,data[7]))
			return false;
		if(!filtraSelect(categoria,data[6]))
			return false;
		if(!filtraSelect(nivel,data[9]))
			return false;
		if(!filtraNombre(txtnombreIE,data[4]))
			return false;
		return true;
	}
);

function registarEvaliacionTrabajosPendientes(id){ console.log('-->registarEvaliacionTrabajosPendientes');
	
	$("#modalimagencargando").modal({
		show : true,
		backdrop : 'static',
		keyboard:false
	});
	
	$.ajax({
		type : "GET",
	    url : url_base + "pedesa/formregistrarevaluacion/"+ id,
		success: function(respuesta) {
			$("#contenidoevaluartrabajospendientes").html(respuesta);
			$("#modaleEvaluarTrabajoPendientes").modal();
		},
		error: function() {
			$("#modalimagencargando").modal('hide');
			$("#textoerror").html("Excepcion al cargar la evaluacion");
			$('#modalerror').modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
	    }
	});		
}

function verfichatrabajo(id){
	$.ajax({
		type : "GET",
	    contentType : "application/json",
	    url : url_base + "pedesa/descargarfichatrabajoconcursopdf/"+id,
		success: function(respuesta) {
			window.open(respuesta, '_blank');
		},
		error: function() {
			$("#modalimagencargando").modal('hide');
			$("#textoerror").html("Excepcion al descargar PDF");
			$('#modalerror').modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
	    }
	});    	
}

function verevidencia(id){
	$.ajax({
		type : "GET",
	    contentType : "application/json",
	    url : url_base + "pedesa/vertrabajosevidencias/"+id,
		success: function(respuesta) {
			var objeto = respuesta;
			var archivo = objeto.archivo;
			var evidencias = objeto.evidencia;
			var contenido = "<div><table style='width:100%'><tr>";
			for(var i=0;i<evidencias.length;i++){
				var evidencia = evidencias[i];
				var ext = (evidencia.split('.')).pop();
				ext = ext.toLowerCase();
				var mi_evi = JSON.stringify(evidencia);
				var archivo;
				switch(ext){
					case 'jpg': 
						archivo = mi_evi.replace(/['"]+/g, '');
						contenido += "<td>"+archivo+"<input type='image' width='20' src='./images/iconos_nd/descargarevidencia.svg' onclick='verdocumento(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/><br><img width='300' height='240' src='../alfresco_programaeducativo/pedesa/upload_evidencias/" +id +"/" +archivo +"'/></td>";
						break;
					case 'png': 
						archivo = mi_evi.replace(/['"]+/g, '');
						contenido += "<td>"+archivo+"<input type='image' width='20' src='./images/iconos_nd/descargarevidencia.svg' onclick='verdocumento(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/><br><img width='300' height='240' src='../alfresco_programaeducativo/pedesa/upload_evidencias/" +id +"/" +archivo +"'/></td>";
						break;
					case 'jpeg': 
						archivo = mi_evi.replace(/['"]+/g, '');
						contenido += "<td>"+archivo+"<input type='image' width='20' src='./images/iconos_nd/descargarevidencia.svg' onclick='verdocumento(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/><br><img width='300' height='240' src='../alfresco_programaeducativo/pedesa/upload_evidencias/" +id +"/" +archivo +"'/></td>";
						break;
					case 'pdf':
						archivo = mi_evi.replace(/['"]+/g, '');
						contenido += "<td><label>"+archivo+"</label>&nbsp;<input type='image' src='./images/iconos_nd/pdf1.svg' onclick='verdocumento(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/></td>";
						break;
					case 'docx': 
						archivo = mi_evi.replace(/['"]+/g, '');
						contenido += "<td><label>"+archivo+"</label>&nbsp;<input type='image'  src='./images/iconos_nd/word-2019.svg' onclick='verdocumento(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/></td>";
						break;
					case 'mp3': 
						archivo = mi_evi.replace(/['"]+/g, '');
						contenido += "<td><label>"+archivo+"</label><input type='image' width='20'  onclick='verdocumento(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/></td>";
						break;
					case 'mp4': 
						archivo = mi_evi.replace(/['"]+/g, '');
						contenido += "<td>"+archivo+"<input type='image' width='20' src='./images/iconos_nd/descargarevidencia.svg' onclick='verdocumento(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/><br><video width='320' height='240' controls><source src='../alfresco_programaeducativo/pedesa/upload_evidencias/" +'`'+ id +'`'+  "/" +'`'+ archivo +'`'+"'  type='video/mp4'><source src='movie.ogg' type='video/ogg'></td>";
						break;
				}
			}
			
			contenido += "</tr></table></div>";
			$("#contenidoEvidencias").html(contenido);
			$("#modalArchivosEvidencias").modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
		},
		error: function() {
			$("#modalimagencargando").modal('hide');
			$("#textoerror").html("Excepcion al ver evidencia");
			$('#modalerror').modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
	    }
	});    	
}

function vertrabajo(id){
			
	$.ajax({
		type : "GET",
	    contentType : "application/json",
	    url : url_base + "pedesa/vertrabajosevidencias/"+id,
		success: function(respuesta) {
			var objeto = respuesta;
			var archivo = objeto.archivo;
			var contenido = "<div><table style='width:100%'><tr>";
			var ext = (archivo.split('.')).pop();
			ext = ext.toLowerCase();
			var mi_archivo = JSON.stringify(archivo);
			var archivo;
			switch(ext){
				case 'jpg': 
					archivo = mi_archivo.replace(/['"]+/g, '');
					contenido += "<td>"+archivo+"<input type='image' width='20' src='./images/iconos_nd/descargarevidencia.svg' onclick='verdocumentotrabajo(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/><br><img width='300' height='240' src='../alfresco_programaeducativo/pedesa/upload_trabajos/" +id +"/" +archivo +"'/></td>";
					break;
				case 'png': 
					archivo = mi_archivo.replace(/['"]+/g, '');
					contenido += "<td>"+archivo+"<input type='image' width='20' src='./images/iconos_nd/descargarevidencia.svg' onclick='verdocumentotrabajo(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/><br><img width='300' height='240' src='../alfresco_programaeducativo/pedesa/upload_trabajos/" +id +"/" +archivo +"'/></td>";
					break;
				case 'jpeg': 
					archivo = mi_archivo.replace(/['"]+/g, '');
					contenido += "<td>"+archivo+"<input type='image' width='20' src='./images/iconos_nd/descargarevidencia.svg' onclick='verdocumentotrabajo(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/><br><img width='300' height='240' src='../alfresco_programaeducativo/pedesa/upload_trabajos/" +id +"/" +archivo +"'/></td>";
					break;
				case 'pdf':
					archivo = mi_archivo.replace(/['"]+/g, '');
					contenido += "<td><label>"+archivo+"</label>&nbsp;<input type='image' src='./images/iconos_nd/pdf1.svg' onclick='verdocumentotrabajo(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/></td>";
					break;
				case 'docx': 
					archivo = mi_archivo.replace(/['"]+/g, '');
					contenido += "<td><label>"+archivo+"</label>&nbsp;<input type='image' src='./images/iconos_nd/word-2019.svg' onclick='verdocumentotrabajo(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/></td>";
					break;
				case 'mp3': 
					archivo = mi_archivo.replace(/['"]+/g, '');
					contenido += "<td><label>"+archivo+"</label><input type='image' width='20'  onclick='verdocumentotrabajo(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/></td>";
					break;
				case 'mp4': 
					archivo = mi_archivo.replace(/['"]+/g, '');
					contenido += "<td>"+archivo+"<input type='image' width='20' src='./images/iconos_nd/descargarevidencia.svg' onclick='verdocumentotrabajo(" +'`'+ id +'`'+  "," +'`'+ archivo +'`'+")'/><br><video width='320' height='240' controls><source src='../alfresco_programaeducativo/pedesa/upload_evidencias/" +id +"/" +archivo +"'  type='video/mp4'></td>";
					break;
			}
			
			contenido += "</tr></table></div>";
			$("#contenidoTrabajo").html(contenido);
			$("#modalArchivoTrabajo").modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
		},
		error: function() {
			$("#modalimagencargando").modal('hide');
			$("#textoerror").html("Excepcion al ver trabajo");
			$('#modalerror').modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
	    }
	});    	
}

function verdocumento(id,link){
	window.open("../alfresco_programaeducativo/pedesa/upload_evidencias/"+id+"/"+link, '_blank');
}

function verdocumentotrabajo(id,link){
	window.open("../alfresco_programaeducativo/pedesa/upload_trabajos/"+id+"/"+link, '_blank');
}*/