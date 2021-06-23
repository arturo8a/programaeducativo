$(document).ready(function(){
    $("#btnbuscar").on("click",function(){
        /*var ods = $('#ods').val();
        var anio = $('#anio').val();
        var modalidad = $('#modalidad').val();
        var categoria = $('#categoria').val();
        var nivel = $('#nivel').val();
        var txtnombreIE = $('#txtnombreIE').val();*/
        
        $("#btnbuscar").prop("disabled",true);
        
        setTimeout(() => {
			table_lista_aperturar_anio.draw();
		});
        
    });

    $(".registrarEvaliacion").on("click",function(){
		registarEvaliacionTrabajosPendientes($(this).attr('data-id'));
	});
    
    
    listar();
    
    $('.dt-buttons').hide();
});
console.log('------------> evaluar.js');

var listar = function (){
		
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
		        { 'data' : 'fichatrabajo' },
		        { 'data' : 'evaluacion' },
		        { 'data' : 'trabajo' },
		        { 'data' : 'permisos' },
		        { 'data' : 'codigo' ,
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
		obtener_data_form("#table_trabajos_pendientes tbody",table_lista_aperturar_anio);
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

var obtener_data_form = function(tbody,table){ console.log($(this).attr('data-id'));
		$(tbody).on("click","button.registrarEvaliacion",function(){
			registarEvaliacionTrabajosPendientes($(this).attr('data-id'));
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

