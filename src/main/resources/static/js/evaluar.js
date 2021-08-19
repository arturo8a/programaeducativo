$(document).ready(function(){
    $("#btnbuscar").on("click",function(){
        
        $("#btnbuscar").prop("disabled",true);
        
        setTimeout(() => {
			table_lista_aperturar_anio.draw();
		});
        
    });
    
    $(".registrarEvaliacion").on("click",function(){
		registarEvaliacionTrabajosPendientes($(this).attr('data-id'));
	});

    $("#btnlimpiar").on("click",function(){
		$("#ods").val('Todos');
		$("#anio").val('Todos');
		$("#txtnombreIE").val('');
		$("#categoria").val('Todos');
		$("#nivel").val('Todos');
		$("#modalidad").val('Todos');
		table_lista_aperturar_anio.draw();
	});
    
    
    listar();
    
    $('.dt-buttons').hide();
});
console.log('------------> evaluar.js');

var listar = function (){ console.log('listar()');
		
		$("#table_trabajos_pendientes").dataTable().fnDestroy();
		table_lista_aperturar_anio = $('#table_trabajos_pendientes').DataTable({
			dom: '<B><"rs_concursos_educativos">frtip',
		    "scrollX": true,
		    "searching": true,
		    lengthMenu: [
		        [ 10, 15, 25, -1 ],
		        [ '10 filas', '15 filas', '25 filas', 'Mostrar todas' ]
		    ],
		    "processing":true,
		    language: {
		        "decimal": "",
		        "emptyTable": "No se encontraron registros",
		        "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
		        "infoEmpty": "Mostrando 0 a 0 de 0 Entradas",
		        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
		        "infoPostFix": "",
		        "thousands": ",",
		        "lengthMenu": "Mostrar _MENU_ Entradas",
		        "loadingRecords": "Cargando Registros...",
		        "processing": "Procesando...",
		        "search": "_INPUT_",
		        "searchPlaceholder": "Busqueda General",
		        "zeroRecords": "No existen registros",//No existen registros
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
		        { 'data' : 'trabajo' ,
	                   render: function(data, type) {
	                   		var x = '<img src="./images/iconos_nd/pdf1.svg" class="fichatrabajo" data-id="'+data+'" style="width:20px; cursor:pointer"/>';
                            return x;
                        }
                },
                { 'data' : 'trabajo' ,
	                   render: function(data, type) {
	                   		var x = '<img src="./images/svg/eye-solid.svg" class="evidencia" data-id="'+data+'" style="width:20px; cursor:pointer"/>';
                            return x;
                        }
                },
		        { 'data' : 'trabajo' ,
	                   render: function(data, type) {
	                   		var x = '<img src="./images/svg/eye-solid.svg" class="trabajo" data-id="'+data+'" style="width:20px; cursor:pointer"/>';
                            return x;
                        }
                },
		        { 'data' : 'trabajo', 
	                   render: function(data, type) {
	                   		var x = '<img src="./images/svg/eye-solid.svg" onclick="openModalTrabajosPermisos('+data+')" style="width:20px; cursor:pointer"/>';
                            return x;
                        }
		        },
		        { 'data' : 'trabajo' ,
	                   render: function(data, type) {
	                   		var x = "<button type='button' data-id='"+data+"' class='registrarEvaliacion btn btn-primary'>Evaluar</button>";
	                        /*switch (data) {
	                            case 0:
	                                x = "<button type='button' id='registrarEvaliacion' data-id='"+data+"' class='editar btn btn-primary'>Evaluar</button>";
	                                break;
	                            case 1:
	                            	x = "Evaluado";
	                                break;
                            }*/
                            return x;
                        }
               }
		    ],
		    buttons: []
		});
		$("div.rs_concursos_educativos").html("<strong>RESULTADOS DE CONCURSOS EDUCATIVOS</strong>");
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
		if(!filtraSelect(modalidad,data[6]))
			return false;
		if(!filtraSelect(categoria,data[5]))
			return false;
		if(!filtraSelect(nivel,data[8]))
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
	$(tbody).on("click","button.registrarEvaliacion",function(){ console.log('open modal registrar evaluacion');
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
			$("#modalimagencargando").modal('hide');
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
			var contenido = "<div class='row'><div class='col-xs-3 col-sm-3'><table style='width:100%'>";
			
			var evidencia_inicial;
			var ext_inicial;
			var mi_evi_inicial;
			var archivo_inicial;
			var contenido_inicial;		
						
			for(var i=0;i<evidencias.length;i++){
				if(i==0){
					evidencia_inicial = evidencias[0];
					ext_inicial = (evidencia_inicial.split('.')).pop();
					ext_inicial = ext_inicial.toLowerCase();
					mi_evi_inicial = JSON.stringify(evidencia_inicial);
					archivo_inicial = mi_evi_inicial.replace(/['"]+/g, '');
				}						
				var evidencia = evidencias[i];
				var ext = (evidencia.split('.')).pop();
				ext = ext.toLowerCase();
				var mi_evi = JSON.stringify(evidencia);
				var archivo = mi_evi.replace(/['"]+/g, '');
				contenido += "<tr><td style='border-bottom:1px solid #DDDDDD'><strong style='cursor:pointer' onclick='actualizaEvidenciaDiv("+'`'+id+'`'+","+'`'+archivo+'`'+"," +'`'+ext +'`'+")'>"+archivo+"</strong></td></tr>";						
			}
			
			contenido += "</table></div><div class='col-xs-9 col-sm-9'><table style='width:100%'><tr><td style='padding-bottom:10px'><div id='contenido_evidencia' style='width:100%'></div></td></tr></table></div></div>";
			$("#contenidoEvidencias").html(contenido);
			actualizaEvidenciaDiv(id,archivo_inicial,ext_inicial);
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
			var contenido = "<div style='width:100%'>";
			var ext = (archivo.split('.')).pop();
			ext = ext.toLowerCase();
			var mi_archivo = JSON.stringify(archivo);
			var archivo = mi_archivo.replace(/['"]+/g, '');
			var subcadena = id + "/" + archivo;
			switch(ext){
				case 'jpeg':
				case 'png':
				case 'jpg': 
					contenido += archivo + "<br><img src='../alfresco_programaeducativo/pedesa/upload_trabajos/"+subcadena+"'"+" width='70%' height='400px'/>";
					break;
				case 'pdf':
					contenido += archivo + "<br><iframe src='../alfresco_programaeducativo/pedesa/upload_trabajos/"+subcadena+"'"+" type='application/pdf' width='100%' height='600px'></<iframe>";
					break;
				case 'doc':
				case 'docs':
				case 'docx': 
					window.open('../alfresco_programaeducativo/pedesa/upload_trabajos/'+subcadena, '_blank');
					break;
				case 'mp4': 
					contenido += archivo + "<br><video src='../alfresco_programaeducativo/pedesa/upload_trabajos/"+subcadena+"'"+" width='100%' height='600px'></<video>";
					break;
			}					
			contenido += "</div>";
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
}

function actualizaEvidenciaDiv(id,archivo,extension){
			var subcadena  = id + "/" + archivo;
			switch(extension){
				case 'pdf' :  
					$("#contenido_evidencia").html("<iframe src='../alfresco_programaeducativo/pedesa/upload_evidencias/"+subcadena+"'"+" type='application/pdf' width='100%' height='600px'></<iframe>");
				break;
				case 'mp4':
					$("#contenido_evidencia").html("<video src='../alfresco_programaeducativo/pedesa/upload_evidencias/"+subcadena+"'"+" width='100%' height='600px' controls></<video>");
				break;
				case 'jpg':
				case 'png':
				case 'jpeg':
					$("#contenido_evidencia").html("<img class='img-fluid' src='../alfresco_programaeducativo/pedesa/upload_evidencias/"+subcadena+"'"+" width='100%' height='600px'/>");
					break;
				case 'mp3':
					$("#contenido_evidencia").html("<audio controls><source src='../alfresco_programaeducativo/pedesa/upload_evidencias/"+subcadena+"'"+" width='100%' height='600px' type='audio/mp3'>Tu navegador no soporta audio HTML5.</audio>");
				break;
				case 'doc':
				case 'docs':
				case 'docx':
					window.open('../alfresco_programaeducativo/pedesa/upload_evidencias/'+subcadena, '_blank');
				break;
			}
		}