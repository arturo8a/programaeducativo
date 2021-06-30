$(document).ready(function(){
	
	$("#btncancelRegistro").on("click",function(){
		$("#modalAlianzaEstrategica").modal('hide');
		 $("#btnaceptarRegistro").prop("disabled",false);
	});
	
	$("#btnAgregarAuspiciador").on("click",function(){
		var cont = $("[id*=tipocodauspiciador]").length;
		cont = cont+1; 
		html = '';
		html += '	<div class="col-xs-12 col-sm-4 text-left pt-1">';
		html += '		<select class="form-control" id="tipocodauspiciador'+cont+'" name="tipocodauspiciador'+cont+'">';
		html += '			<option value="0">Tipo de documento</option>';
		html += '		    <option value="1">DNI</option>';
		html += '		    <option value="2">Pasaporte</option>';
		html += '		    <option value="3">Carnet de Extranjería</option>';
		html += '		</select>';
		html += '	</div>';
		html += '	<div class="col-xs-12 col-sm-4 text-left pt-1">';
		html += '		<input type="text" name="cantidadauspiciador'+cont+'" id="cantidadauspiciador'+cont+'" class="form-control" placeholder="Cantidad"/>';
		html += '	</div>';
		html += '	<div class="col-xs-12 col-sm-4 text-left pt-1">';
		html += '		<input type="text" name="descripcionauspiciador'+cont+'" id="descripcionauspiciador'+cont+'" class="form-control" placeholder="Descripcion"/>';
		html += '	</div>';
		html += '	<div class="col-xs-12 col-sm-4 text-left pt-1">';
		html += '		<input type="text" name="montounitarioauspiciador'+cont+'" id="montounitarioauspiciador'+cont+'" class="form-control" placeholder="Monto Unitario"/>';
		html += '	</div>';
		html += '	<div class="col-xs-12 col-sm-4 text-left pt-1">';
		html += '		<input type="text" name="montototalauspiciador'+cont+'" id="montototalauspiciador'+cont+'" class="form-control" placeholder="Monto Total"/>';
		html += '	</div>';
		html += '	<div class="col-xs-12 col-sm-4 text-left">';
		html += '	</div>';
		
		$("#section-datos-auspicio").append(html);
	});
	
	$("#fecha_oficio").datepicker({
		locale: 'es-es',
	    format: 'dd/mm/yyyy',
	    uiLibrary: 'bootstrap4'
	});

});

function armarAuspicio(){
	var array = [];
	$.each($("[id*=tipocodauspiciador]"), function( index, value ) {
		var i = index + 1;
	  	var tipoDoc = $('#tipocodauspiciador'+i).val();
	  	var cantidad = $('#tipocodauspiciador'+i).val();
	  	var descripcion = $('#descripcionauspiciador'+i).val();
	  	var montounitario = $('#montounitarioauspiciador'+i).val();
	  	var montototal = $('#montototalauspiciador'+i).val();
	  	
	  	array.push({tipoDoc,cantidad,descripcion,montounitario,montototal});
	});
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
	
	var arrayAuspicio = armarAuspicio();
	
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
		docoficio : null,
		auspicioid : {
			lista : arrayAuspicio
		}

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
    
    $.ajax({
		type : "POST",
	    contentType : "application/json",
	    url : url_base + "pedesa/saveusuarioalianza",
	    data : JSON.stringify(armarData()),
	    dataType : 'json',
		success: function(respuesta) {
			if(respuesta>0){
				
			}
			
			$("#btnaceptarRegistro").prop("disabled",false);
			$("#modalimagencargando").modal('hide');
		},
		error: function() {
				$("#modalimagencargando").modal('hide');
				alert("Exception al registrar");
		    }
		});
   
    
});


function formatoFecha(fecha){
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
	    url : url_base + "pedesa/saveusuarioalianza/"+idUsuario,
	    dataType : 'json',
		success: function(respuesta) {console.log(respuesta);
			
			$("#idAlianzaEstrategica").val(respuesta.id);
			$("#odsresgusu").val(respuesta.ods.id);
			$("#anioregusu").val(respuesta.anio);
			$("#categoriaregusu").val(respuesta.categoria.id);
			$("#entidadregusu").val(respuesta.entidad);
			$("#direccionregusu").val(respuesta.direccion);
			/*var comitetecnico = "0";
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
			}*/
			

			$("#modalimagencargando").modal('hide');
		},
		error: function() {
				$("#modalimagencargando").modal('hide');
				alert("Exception al registrar");
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

function filtraNombre(nombre,campo){
	if(nombre==="" || nombre === undefined)
		return true;
	var posicion = (campo.toLowerCase()).indexOf(nombre.toLowerCase()); 
	if(posicion != -1)
		return  true;
	return false;
}

function filtraSelect(dato,campo){
	if(dato==="Todos")
		return true;
	return dato===campo;
}

var obtener_data_form = function(tbody,table){
	$(tbody).on("click","button.registrarEvaliacion",function(){
		registarEvaliacionTrabajosPendientes($(this).attr('data-id'));
	});
};

var obtener_fichatrabajo = function(tbody,table){
	$(tbody).on("click","img.fichatrabajo",function(){
		verfichatrabajo($(this).attr('data-id'));
	});
};

var obtener_evidencia = function(tbody,table){
	$(tbody).on("click","img.evidencia",function(){
		verevidencia($(this).attr('data-id'));
	});
};

var obtener_trabajos = function(tbody,table){
	$(tbody).on("click","img.trabajo",function(){
		vertrabajo($(this).attr('data-id'));
	});
};

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