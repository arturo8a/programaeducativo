var categoriatrabajo="", modalidadtrabajo="",titulotrabajo="",linkvideotrabajo="", apellidopaternotrabajo="",apellidomaternotrabajo="",nombretrabajo="",tipodoctrabajo="",nrodoctrabajo="",nrotelefonotrabajo="",correotrabajo="",generotrabajo="";
var mensajeValidacion, celdas_seleccionadas = 0;
var acumulanivel = "", table_lpt_editar;
var conversacion,valoracionagua,valoracionalcantarillado,buenuso,importancia,vinculo,carencias;
var arrayParticipante , array_nivel, arrayNombreParticipanteEditar;	
var rows_selected;
var array_indice = new Array();	
var evidencias_iniciadas = new Array();
var evidencias_agregadas = new Array();
var evidencias_iniciadas_eliminadas = new Array();
var evidencias_agregadas_eliminadas = new Array();
var nroevidencias = parseInt($("#nroevidencias").val());

var mis_evidencias_inicial_edit = new Array();
var mis_evidencias_edit = new Array();
var mis_evidencias_eliminadas = new Array();
	
	console.log("nroevidencias : " + nroevidencias);
	if(nroevidencias<10)
		$("#evidenciastrabajoeditar").prop("disabled",false);
	else
		$("#evidenciastrabajoeditar").prop("disabled",true);
	
	$("#quitartrabajoeditar").click(function(){
		$("#archivotrabajoeditar").prop("disabled",false);
		bandera = true;
	});
	
	var html1 = "<table><tr>";
	for(var i=1;i<=nroevidencias;i++){
		mis_evidencias_inicial_edit.push($("#evidencia" + i).val());		
		var evidencia_file = $("#evidencia" + i).val();
		html1 += "<td><div id='"+evidencia_file+"' class='alert alert-warning alert-dismissible fade show' role='alert' style='padding:10px'><strong><abbr title='"+evidencia_file+"'>"+	(evidencia_file.length > 5 ?	evidencia_file.substr(0,5) : evidencia_file)	+"</abbr></strong><button type='button' class='close' onclick='eliminarEvidenciaInicial("+'`'+evidencia_file+'`'+','+i+")' data-dismiss='alert' aria-label='Close' style='padding:0px; margin:-3px'><span aria-hidden='true'>&times;</span></button></div></td>";
	}
	html1 += "</tr></table>";
	$("#divevidencias").html(html1);
	
	$("#evidenciastrabajoeditar").change(function(){
		if(evidenciastrabajoeditar.files[0].name != undefined){
			
			var msj_eviste_evidencia="";
			var flag_existe;
			for(var k=0;k<evidenciastrabajoeditar.files.length;k++){
				flag_existe = true;
				for(var i=0;i<mis_evidencias_inicial_edit.length;i++){					
					if(mis_evidencias_inicial_edit[i]  == evidenciastrabajoeditar.files[k].name){
						flag_existe = false;
						msj_eviste_evidencia +=  evidenciastrabajoeditar.files[k].name + "/";
					}
				}
				if(flag_existe){
					for(var j=0;j<mis_evidencias_edit.length;j++){					
						if(mis_evidencias_edit[j].name  == evidenciastrabajoeditar.files[k].name){
							flag_existe = false;
							msj_eviste_evidencia +=  evidenciastrabajoeditar.files[k].name + "/";
						}
					}
				}
				if(flag_existe){
					mis_evidencias_edit.push(evidenciastrabajoeditar.files[k]);
				}
			}
			
			var data_archivo_subir_editar = "<table><tr>";
			for(var j=0;j<mis_evidencias_inicial_edit.length;j++){
				data_archivo_subir_editar += "<td><div id='"+(mis_evidencias_inicial_edit[j])+"' class='alert alert-warning alert-dismissible fade show' role='alert' style='padding:10px'><strong><abbr title='"+(mis_evidencias_inicial_edit[j])+"'>"+	((	(mis_evidencias_inicial_edit[j]).length) <5 ? (mis_evidencias_inicial_edit[j]) :	(mis_evidencias_inicial_edit[j]).substr(0,5))	+"<abbr></strong><button type='button' class='close' onclick='eliminarEvidenciaEditar("+'`'+mis_evidencias_inicial_edit[j]+'`'+")' data-dismiss='alert' aria-label='Close' style='padding:0px; margin:-3px'><span aria-hidden='true'>&times;</span></button></div></td>";
			}
			for(var i=0;i<mis_evidencias_edit.length;i++){
				data_archivo_subir_editar += "<td><div id='"+(mis_evidencias_edit[i].name)+"' class='alert alert-primary alert-dismissible fade show' role='alert' style='padding:10px'><strong><abbr title='"+(mis_evidencias_edit[i].name)+"'>"+	 (	(mis_evidencias_edit[i].name).length <5 ? (mis_evidencias_edit[i].name) :	(mis_evidencias_edit[i].name).substr(0,5))	+"<abbr></strong><button type='button' class='close' onclick='eliminarEvidenciaEditar("+'`'+mis_evidencias_edit[i].name+'`'+")' data-dismiss='alert' aria-label='Close' style='padding:0px; margin:-3px'><span aria-hidden='true'>&times;</span></button></div></td>";
			}
			data_archivo_subir_editar += "</tr></table>";
			if(msj_eviste_evidencia.length>0){
				msj_eviste_evidencia = msj_eviste_evidencia.substr(0,msj_eviste_evidencia.length-1);
				alert("La(s) siguientes evidencias no se subieron porque ya existen " + msj_eviste_evidencia);
			}				
			$("#divevidencias").html(data_archivo_subir_editar);
		}
	});
	
	var mistyle = "";
	
	if($("#modalidadpostulaciontrabajoeditar").val() == 1) 
		mistyle = 'single';
	else
		mistyle = 'multi';
	
	$("#tipodocumentodocentetrabajoeditar").on("change",function(){
		
		if($("#tipodocumentodocentetrabajoeditar").val()!="0"){
			$("#nrodocumentodocentetrabajoeditar").prop('disabled', false);
			$("#nrodocumentodocentetrabajoeditar").val("");
			$("#nrodocumentodocentetrabajoeditar").focus();
			
		}
		else{
			$("#nrodocumentodocentetrabajoeditar").val("");
			$("#nrodocumentodocentetrabajoeditar").prop('disabled', true);
		}
	});


	$("#categoriatrabajoeditar").change(function(){
		categoriatrabajo = $("#categoriatrabajoeditar").val();		
		$("#linkvideoeditar").val("");
		array_indice = new Array();		
		table_lpt_editar.rows().deselect();
		table_lpt_editar.draw();
	});
	
	$("#modalidadpostulaciontrabajoeditar").change(function(){
		modalidadtrabajo = $("#modalidadpostulaciontrabajoeditar").val();
		if(modalidadtrabajo == '1')
			table_lpt_editar.select.style('single'); 
		if(modalidadtrabajo == '2')
			table_lpt_editar.select.style('multi');
		array_indice = new Array();		
		table_lpt_editar.rows().deselect();
		table_lpt_editar.draw();
	});
		
	$("#table_lpt_editar").dataTable().fnDestroy();
	table_lpt_editar = $('#table_lpt_editar').DataTable({
		dom: '<B><"rs_seleccione_participante_edit">frtip',
	    "scrollX": true,
	    "scrollY": "250px",
	    "scrollCollapse": true,
	    "paging": false,
	    fixedHeader: {
            header: true
        },
	    lengthMenu: [
	        [ 10, 15, 25, -1 ],
	        [ '10 filas', '15 filas', '25 filas', 'Mostrar todas' ]
	    ],
	    destroy: true,
	    "processing":true,
	    language: {
	        "decimal": "",
	        "emptyTable": "No se han registrado",
	        "info": "",
	        "infoEmpty": "Mostrando 0 a 0 de 0 Entradas",
	        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
	        "infoPostFix": "",
	        "thousands": ",",
	        "lengthMenu": "Mostrar _MENU_ Entradas",
	        "loadingRecords": "Cargando Registros...",
	        "processing": "Procesando...",
	        "search": "Buscar:",
	        "zeroRecords": "Sin datos",//No existen registros
	        "paginate": {
	            "first": "Primero",
	            "last": "Ultimo",
	            "next": "Siguiente",
	            "previous": "Anterior"
	        }
	    },
	    'ajax' : {
	        "url" : url_base + 'pedesa/listaparticipantes_trabajo',
	        "type" : "GET",
	        "dataSrc" : ""
	    },
	    'columnDefs': [
	    	{
                   orderable: false,
                   className: 'select-checkbox',
                   targets:   0
               },
               { "visible": false, "targets": 6 },
  		    	{ "visible": false, "targets": 7 },
  		  		{ "visible": false, "targets": 8 },
  		  		{ "visible": false, "targets": 9 },
	     	{ "visible": false, "targets": 10 },
	  		{ "visible": false, "targets": 11 },
	  		{ "visible": false, "targets": 12 },
	  		{ "visible": false, "targets": 14 }
	    ],
           select: {        	   
        	   style: mistyle,
               selector: 'td:first-child'
           },
	    'columns' : [
	    	{
          		  "data":null, render:function(){return "";}
          		},
	    	{ 'data' : 'appaterno' },
	        { 'data' : 'apmaterno' },
	        { 'data' : 'nombre' },
	        { 'data' : 'tipodocumento' },
	        { 'data' : 'nrodocumento' },
               { 'data' : 'composicionmusical'},
               { 'data' : 'cuento'},
               { 'data' : 'poesia'},
               { 'data' : 'dibujopintura'},
               { 'data' : 'ahorraragua'},
               { 'data' : 'modalidadindividual'},
               { 'data' : 'modalidadgrupal'},
               { 'data' : 'nivel'},
               { 'data' : 'id'}
	    ],
	    buttons: []
	});
	$("div.rs_seleccione_participante_edit").html("<strong>Seleccione participantes</strong>");
	
	$('#table_lpt_editar').DataTable().on("draw", function(row, data){
	
		/*var array_data = table_lpt_editar.data();
		var array_participanteid = $("#participanteid").val();
		array_participanteid = array_participanteid.split(',');
		
		var contador = 0;
		for(var i=0;i<array_indice.length;i++){				
			for(var j=0;j<array_participanteid.length-1;j++){ 
				if(array_indice[i] == array_participanteid[j]){
					table_lpt_editar.row(':eq('+i+')', { page: 'current' }).select();
				}
			}				
		}
		$('#table_lpt_editar').DataTable().columns.adjust();*/
	});
      
	
	$("#btncancelartrabajoeditar").click(function(){
		$("#modaleditartrabajo").modal('hide');
	});
	
	$("#btncerrarmensajeinformaciontrabajoeditar").click(function(){			
		$("#modalInformaciontrabajoeditar").modal('hide');
	});
	
	$("#btncerrarmensajeinformaciontrabajoeditar").click(function(){			
		$("#modalinformaciontrabajoeditar").modal('hide');
	});
	
	$("#btncerrarmensajeexitotrabajoeditar").click(function(){
		$("#modalexitotrabajoeditar").modal('hide');
		$("#modaleditartrabajo").modal('hide');
	}); 
	
	$("#btncerrarmensajeerroreditar").click(function(){
		$("#modalerroreditar").modal('hide');
	});
	
	$("#btnguardartrabajoeditar").click(function(){	
		
		$("#modalimagencargando").modal({
			show : true,
			backdrop : 'static',
			keyboard:false
		});
		
		if(validarCamposTrabajo()){
			
			conversacion = $("#conversacioneditar").is(":checked") ? 1 : 0;
			valoracionagua = $("#valoracionaguaeditar").is(":checked") ? 1 : 0 ;
			valoracionalcantarillado = $("#valoracionalcantarilladoeditar").is(":checked") ? 1 : 0;
			buenuso = $("#buenusoeditar").is(":checked") ? 1 : 0;
			importancia = $("#importanciaeditar").is(":checked") ? 1 : 0;
			vinculo = $("#vinculoeditar").is(":checked") ? 1 : 0;
			carencias = $("#carenciaseditar").is(":checked") ? 1 : 0;
			
			var trabajosfinales = {
				id: $("#trabajooid").val(),
				categoriatrabajo  : {
					id : categoriatrabajo
				},
				modalidadtrabajo : {
					id : modalidadtrabajo
				},
				titulotrabajo  : titulotrabajo,
				linkvideo : linkvideotrabajo,
				conversacion : conversacion,
				valoracionagua : valoracionagua,
				valoracionalcantarillado : valoracionalcantarillado,
				buenuso : buenuso,
				importancia : importancia,
				vinculo : vinculo,
				puesto : 0,
				carencias : carencias,
				appaterno: apellidopaternotrabajo,
				apmaterno : apellidomaternotrabajo,
				nombre : nombretrabajo,
				tipodocumento : {
					id : tipodoctrabajo
				},
				estadotrabajo:{
					id : 1
				},
				nrodocumento: nrodoctrabajo,
				telefono : nrotelefonotrabajo,
				correo  : correotrabajo,
				genero : {
					id : generotrabajo
				},
				estado : 1,
				enviado : 0
			};
			
			var trabajosfinalesparticipantesDto = {
				trabajosfinales : trabajosfinales,
				listaparticipante : arrayParticipante
			}
			
			$.ajax({
				type : "POST",
			    contentType : "application/json",
			    url : url_base + "pedesa/savetrabajosfinalesparticipante",
			    data : JSON.stringify(trabajosfinalesparticipantesDto),
			    dataType : 'json',
				success: function(respuesta) {
					if(respuesta == -50){
						$("#modalimagencargando").modal('hide');
						$("#textoerroreditar").html("No se puede registrar trabajos con la misma categoria, modalidad y participantes");
						$('#modalerroreditar').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}
					else if(respuesta == -100){
						 window.location = url_base + "pedesa";
					}
					else if (respuesta == 0){
						$("#modalimagencargando").modal('hide');
						$("#textoerroreditar").html("Error al registrar trabajo");
						$('#modalerroreditar').modal({
							show : true,
							backdrop : 'static',
							keyboard:false
						});
					}
					else{
						var data = new FormData();
						data.append('file',archivotrabajoeditar.files[0] != undefined ? archivotrabajoeditar.files[0] : null);
						data.append('id',respuesta);
						for(var i=0;i<mis_evidencias_edit.length;i++){
							data.append('files',mis_evidencias_edit[i]);
						}						
						var archivos_eliminados = "";
						for(var i=0;i<mis_evidencias_eliminadas.length;i++){
							archivos_eliminados += mis_evidencias_eliminadas[i] + ",";
						}						
						data.append('array_evidencias_eliminadas' , archivos_eliminados);
						
						/*Subiendo trabajo final y evidencias*/
						$.ajax({
					        type: "POST",
					        enctype: 'multipart/form-data',
					        url: url_base + "pedesa/subiractualizartrabajoarchivoevidencia",
					        data: data,
					        processData: false, 
					        contentType: false,
					        cache: false,
					        timeout: 600000,
					        success: function (data) {		
					        	
					        	$("#modalimagencargando").modal('hide');
					        	var nombreparticipanteeditar = "";
					        	for(var i=0;i<arrayNombreParticipanteEditar.length;i++){
					        		nombreparticipanteeditar += arrayNombreParticipanteEditar[i]  + "/";
					        	}
					        	var nro_edit = evidencias_iniciadas.length + evidencias_agregadas.length;
					        	table_lista_trabajos_finales.cell(celdaseleccionada,1).data($("#categoriatrabajoeditar option:selected").html()).draw();
					        	table_lista_trabajos_finales.cell(celdaseleccionada,2).data(titulotrabajo).draw();
					        	table_lista_trabajos_finales.cell(celdaseleccionada,3).data($("#modalidadpostulaciontrabajoeditar option:selected").html()).draw();
					        	table_lista_trabajos_finales.cell(celdaseleccionada,4).data(nombreparticipanteeditar).draw();
					        	table_lista_trabajos_finales.cell(celdaseleccionada,5).data( (mis_evidencias_edit.length + mis_evidencias_inicial_edit.length) + ((mis_evidencias_edit.length + mis_evidencias_inicial_edit.length) >1 ? " evidencias " : " evidencia ")  +  '1' + " final").draw();
								
								limpiarControlesEditar();
							    $("#textoexitotrabajoeditar").html("Se actualizó los datos del participante");
								$('#modalexitotrabajoeditar').modal({
									show : true,
									backdrop : 'static',
									keyboard:false
								});
					        },
					        error: function (e) {
					            console.log("ERROR : ", e);
					        }
					    });						
					}					
				},
				error: function() {
					$("#modalimagencargando").modal('hide');
					alert("Exception al registrar");
			    }
			});	
			
			
		}
		else{
			$("#modalimagencargando").modal('hide');
			$("#textoinformaciontrabajoeditar").html("<div style='color:red' class='text-left'>"+mensajeValidacion+"</div>");
			$('#modalinformaciontrabajoeditar').modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
		}
	});		
	/*});*/
	
	$('#table_lpt_editar').DataTable().on("draw", function(row, data){
		
		var array_data = table_lpt_editar.data();
		var array_participanteid = $("#participanteid").val();
		array_participanteid = array_participanteid.split(',');
		
		var contador = 0;
		for(var i=0;i<array_indice.length;i++){				
			for(var j=0;j<array_participanteid.length-1;j++){ 
				if(array_indice[i] == array_participanteid[j]){
					table_lpt_editar.row(':eq('+i+')', { page: 'current' }).select();
				}
			}				
		}
	});
	
	
	$.fn.dataTable.ext.search.push(
   	    function( settings, data, dataIndex ) {
   	    	
   	    	if(settings.nTable.id !== 'table_lpt_editar'){
   	            return true;
   	        }
   	    	
   	    	var composicionmusical  = 0, cuento = 0, poesia = 0,  dibujopintura = 0 , ahorraragua = 0, modalidadindividual = 0 , modalidadgrupal = 0;
   	    	categoriatrabajo= $("#categoriatrabajoeditar").val();
   	    	modalidadtrabajo = $("#modalidadpostulaciontrabajoeditar").val();
   	    	
   	    	switch(categoriatrabajo){
   	    		case '1': composicionmusical = 1;  break;
   	    		case '2': cuento = 1; break;
   	    		case '3': poesia = 1;  break;
   	    		case '4': dibujopintura = 1; break;
   	    		case '5': ahorraragua = 1; break;
   	    	}
   	    	
   	    	switch(modalidadtrabajo){
	    		case '1': modalidadindividual = 1;  break;
	    		case '2': modalidadgrupal = 1; break;
	    	}
   	    	
   	    	if(!filtraCategoriaModalidad(composicionmusical, data[6]))
   	    		return false;
   	    	if(!filtraCategoriaModalidad(cuento, data[7]))
   	    		return false;
   	    	if(!filtraCategoriaModalidad(poesia, data[8]))
   	    		return false;
   	    	if(!filtraCategoriaModalidad(dibujopintura, data[9]))
   	    		return false;
   	    	if(!filtraCategoriaModalidad(ahorraragua, data[10]))
   	    		return false;   	    	
   	    	if(!filtraCategoriaModalidad(modalidadindividual, data[11]))
   	    		return false;
   	    	if(!filtraCategoriaModalidad(modalidadgrupal, data[12]))
   	    		return false;
   	    	array_indice.push(data[14])
   	    	return true;
   	    }
   	);
	
	function eliminarEvidenciaInicial(archivo,indice){
		for(var i=0;i<mis_evidencias_inicial_edit.length;i++){
			if(archivo == mis_evidencias_inicial_edit[i]){
				mis_evidencias_inicial_edit.splice(i,1);
				$("#evidenciastrabajoeditar").prop("disabled",false);
				mis_evidencias_eliminadas.push(archivo);
			}	
		}		
	}
	
	function eliminarEvidenciaEditar(archivo){
		console.log("archivo :" + archivo);
		for(var i =0;i<mis_evidencias_edit.length;i++){
			if(archivo == mis_evidencias_edit[i].name){
				mis_evidencias_edit.splice(i,1);
			}
		}
	}	
	
	function filtraCategoriaModalidad(campo1, campo2){
		if(campo1===0)
			return true;
		return campo1 == campo2;
	}
	
	function limpiarControlesEditar(){
		$("#categoriatrabajoeditar").val("0");
		$("#modalidadpostulaciontrabajoeditar").val("0");
		$("#titulotrabajoeditar").val("");
		$("#linkvideoeditar").val("");
		$("#conversacioneditar").prop("checked", false);
		$("#valoracionaguaeditar").prop("checked", false);
		$("#valoracionalcantarilladoeditar").prop("checked", false);
		$("#buenusoeditar").prop("checked", false);
		$("#importanciaeditar").prop("checked", false);
		$("#vinculoeditar").prop("checked", false);
		$("#carenciaseditar").prop("checked", false);
		$("#appaternodocentetrabajoeditar").val("");
		$("#apmaternodocentetrabajoeditar").val("");
		$("#nombredocentetrabajoeditar").val("");
		$("#tipodocumentodocentetrabajoeditar").val("0");
		$("#nrodocumentodocentetrabajoeditar").val("");
		$("#telefonodocentetrabajoeditar").val("");
		$("#correodocentetrabajoeditar").val("");
		$("#generodocentetrabajoeditar").val("0");
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
	
	function filterIntNroDocIdentidad(evt,input){
		
		if($("#tipodocumentodocentetrabajoeditar").val()==1){
		    var key = window.Event ? evt.which : evt.keyCode;    
		    var chark = String.fromCharCode(key);
		    var tempValue = input.value+chark;
		    if(key >= 48 && key <= 57){
		    	if($("#tipodocumentodocentetrabajoeditar").val() == 1){
					if($("#nrodocumentodocentetrabajoeditar").val().trim().length<8)
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
		else if($("#tipodocumentodocentetrabajoeditar").val()==2){
			if($("#nrodocumentodocentetrabajoeditar").val().trim().length<12)
				return true;
			return false;
		}
	}
	
	function validarCamposTrabajo(){
		
		arrayParticipante = new Array()
		array_nivel = new Array();
		arrayNombreParticipanteEditar = new Array();
		
		celdas_seleccionadas = table_lpt_editar.rows('.selected').data().length;
		mensajeValidacion = "";
		categoriatrabajo = $("#categoriatrabajoeditar").val();
		modalidadtrabajo = $("#modalidadpostulaciontrabajoeditar").val();
		titulotrabajo = $("#titulotrabajoeditar").val();
		linkvideotrabajo = $("#linkvideoeditar").val();
		apellidopaternotrabajo = $("#appaternodocentetrabajoeditar").val();	
		apellidomaternotrabajo = $("#apmaternodocentetrabajoeditar").val();
		nombretrabajo = $("#nombredocentetrabajoeditar").val();
		tipodoctrabajo  = $("#tipodocumentodocentetrabajoeditar").val();
		nrodoctrabajo  = $("#nrodocumentodocentetrabajoeditar").val();
		nrotelefonotrabajo  = $("#telefonodocentetrabajoeditar").val();
		correotrabajo  = $("#correodocentetrabajoeditar").val();
		generotrabajo  = $("#generodocentetrabajoeditar").val();
		
		conversacion = $("#conversacioneditar").is(":checked");
		valoracionagua = $("#valoracionaguaeditar").is(":checked") ;
		valoracionalcantarillado = $("#valoracionalcantarilladoeditar").is(":checked")
		buenuso = $("#buenusoeditar").is(":checked") ;
		importancia = $("#importanciaeditar").is(":checked");
		vinculo = $("#vinculoeditar").is(":checked");
		carencias = $("#carenciaseditar").is(":checked");
		
		if(categoriatrabajo == 0){
			mensajeValidacion = "Debe seleccionar categoria"+"<br>";
		}
		
		if(modalidadtrabajo == 0){
			mensajeValidacion += "Debe seleccionar modalidad"+"<br>";
		}
		else{
			if( modalidadtrabajo == 1 && celdas_seleccionadas != 1 ) {
				mensajeValidacion += "Debe seleccionar 1 celda de la tabla "+"<br>";
			}
			if( modalidadtrabajo == 2 && celdas_seleccionadas < 2 ) {
				mensajeValidacion += "Debe seleccionar mínimo 2 celdas de la tabla"+"<br>";
			}
		}
		
		if(modalidadtrabajo=="0"){
			mensajeValidacion += "Debe seleccionar modalidad"+"<br>";
		}
		
		if(titulotrabajo.trim()==""){
			mensajeValidacion += "Debe ingresar título de su trabajo final"+"<br>";
		}
		
		if(!$("#archivotrabajoeditar").is(':disabled') && archivotrabajoeditar.files.length==0){
			mensajeValidacion += "Debe subir archivo de trabajo final"+"<br>";
		}
		else{
			if(!$("#archivotrabajoeditar").is(':disabled')){
				var archivotrabajo_name = (archivotrabajoeditar.files[0]).name;
				var archivotrabajo_size = (archivotrabajoeditar.files[0]).size;
				if(archivotrabajo_size >40000000){
					mensajeValidacion += "El archivo " + archivotrabajo_name +" no debe superar los 20MB"+"<br>";
				}
				var ext = archivotrabajo_name.split('.').pop();
				ext = ext.toLowerCase();
				switch(ext){
					case 'jpg': break;
					case 'png': break;
					case 'jpeg': break;
					case 'pdf': break;
					case 'docx': break;
					case 'mp3': break;
					case 'mp4': break;
					default: mensajeValidacion += "El archivo " + archivotrabajo_name + " debe tener extensión jpg , png, pdf, word,mp3,mp4"+"<br>";	
				}
			}			
		}
		
		if( ((mis_evidencias_edit.length + mis_evidencias_inicial_edit.length) >10	) || ( (mis_evidencias_edit.length + mis_evidencias_inicial_edit.length) == 0)){
			mensajeValidacion += "Debe subir hasta 10 evidencias"+"<br>";
		}else{			
			for(var j=0;j<mis_evidencias_edit.length;j++){
				var evidenciastrabajo_name = mis_evidencias_edit[j].name;
				var evidenciastrabajo_size = mis_evidencias_edit[j].size;
				if(evidenciastrabajo_size >40000000){
					mensajeValidacion += "El evidencia "  + evidenciastrabajo_name + " no debe superar los 40MB"+"<br>";
				}
				var ext = evidenciastrabajo_name.split('.').pop();
				ext = ext.toLowerCase();
				switch(ext){
					case 'jpg': break;
					case 'png': break;
					case 'jpeg': break;
					case 'pdf': break;
					case 'docx': break;
					case 'mp3': break;
					case 'mp4': break;
					default: mensajeValidacion += "La evidencia " + evidenciastrabajo_name + " debe tener extensión jpg , png, pdf, word,mp3,mp4"+"<br>";	
				}
			}
		}
		
		var array = table_lpt_editar.rows(".selected").data();	
		var nombre="";
		for(i=0;i<array.length;i++){
			array_nivel.push(array[i].nivel);
			arrayParticipante.push({id : (array[i].id)});
			nombre = array[i].nombre + " " + array[i].appaterno + " " + array[i].apmaterno;
			arrayNombreParticipanteEditar.push(nombre);
		}
		
		let igual = true;
		for (let i = 0; i < array_nivel.length-1 ; i++) {
		    if (array_nivel[i] !== array_nivel[i + 1]) {
		      igual=false;
		      break;
		    }
		}
		
		if(!igual){
			mensajeValidacion += "Debe seleccionar participantes con el mismo nivel"+"<br>";
		}
		
		if( ! (conversacion || valoracionagua || valoracionalcantarillado || buenuso || importancia || vinculo  || carencias)){
			mensajeValidacion += "Debe seleccionar ejes temáticos"+"<br>";
		}
		
		if(apellidopaternotrabajo.trim()==""){
			mensajeValidacion += "Debe ingresar apellido paterno de docente"+"<br>";
		}
		
		if(apellidomaternotrabajo.trim()==""){
			mensajeValidacion += "Debe ingresar apellido materno de docente"+"<br>";
		}
		
		if(nombretrabajo.trim()==""){
			mensajeValidacion += "Debe ingresar nombre de docente"+"<br>";
		}
		
		if(tipodoctrabajo == "0"){
			mensajeValidacion += "Debe seleccionar tipo de documento de docente"+"<br>";
		}
		
		if(nrodoctrabajo.trim()==""){
			mensajeValidacion += "Debe ingresar número de documento de Docente"+"<br>";
		}
		
		if(correotrabajo.trim()==""){
			mensajeValidacion += "Debe ingresar correo electrónico de docente"+"<br>";
		}
		
		if(generotrabajo=="0"){
			mensajeValidacion += "Debe seleccionar genero de docente"+"<br>";
		}
		
		if(!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test(correotrabajo))){		
			mensajeValidacion += "Debe ingresar correctamente el correo electrónico de docente"+"<br>";
		}
		
		if(mensajeValidacion!=""){
			return false;
		}
		return true;
	}