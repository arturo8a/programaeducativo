const DATE_FORMAT = "YYYY-MM-DD";
var url_base = location.origin+"/";
	
$(document).ready(function(){
	
	$('#sidebarCollapse').on('click', function () {
		console.log("sidebarCollapse");
        $('#sidebar').toggleClass('active');
    });
	
	$("#btnCerrarSesion").click(function(){
		console.log("btnCerrarSesion");
		//window.location.href = url_base;
		$.ajax({
			url: url_base + "programaeducativo/cerrar_sesion",
			success: function(respuesta) {
				window.location.href = url_base;
			}
		});		
	});
	
	$("#btnbuscar").click(function(){
		alert("click boton");  
		/*table_ie.ajax.reload();
		table_ie.draw();*/
	});	
	
	$("#btnbuscar1").click(function(){
		alert("click boton1");  
		/*table_ie.ajax.reload();
		table_ie.draw();*/
	});	
	
	$("#fecha_desde").datepicker({
		locale: 'es-es',
        format: 'dd/mm/yyyy',
        uiLibrary: 'bootstrap4'
	});
	$("#fecha_hasta").datepicker({
		locale: 'es-es',
        format: 'dd/mm/yyyy',
        uiLibrary: 'bootstrap4'
	});
	
	var hoy = new Date();
	var nombre_archivo = "PE"+hoy.getFullYear()+'_'+hoy.getDate()+'-'+(hoy.getMonth()+1)+'-'+hoy.getFullYear()+'_'+hoy.getHours() + hoy.getMinutes();
	
	$.fn.dataTable.ext.search.push(
	    function( settings, data, dataIndex ) {
	    	
	    	var fecha_desde = $("#fecha_desde").val();	    	
	    	var fecha_hasta = $("#fecha_hasta").val();
	    	var nombre = $("#nomie").val();
	    	var departamento = $("#departamento option:selected").text();
	    	var provincia = $("#provincia option:selected").text();
	    	var distrito = $("#distrito option:selected").text();
	    	var estado = $("#estado option:selected").val();
	    	
	    	if(!filtraUbigeo(departamento,data[1]))
	    		return false;
	    	if(!filtraUbigeo(provincia,data[2]))
	    		return false;
	    	if(!filtraUbigeo(distrito,data[3]))
	    		return false;	    	
	    	if(!filtraNombre(nombre,data[4]))
	    		return false;
	    	if(!filtraFecha(fecha_desde,fecha_hasta,data[6]))
	    		return false;
	    	if(!filtraConcurso(estado,data[7]))
	    		return false;
	    	return true;
	    }
	);
	
	$("#departamento").on("change",function(){
		if($("#departamento").val()!="Todos"){
			$("#provincia").html("");
			$.ajax({
				url: url_base + "programaeducativo/provincias/bydepa/"+$("#departamento").val(),
				success: function(respuesta) {
					var contenido = "<option value='Todos'>Todos</option>";
					for(var i=0;i<respuesta.length;i++){
						contenido = contenido + "<option value="+respuesta[i].id+">"+respuesta[i].descripcion+"</option>";
					}
					$("#provincia").html(contenido);
				},
				error: function() {
			        console.log("No se ha podido obtener la información");
			    }
			});
		}
		else{
			$("#provincia").html("<option value='Todos'>Todos</option>");
		}
		$("#distrito").html("<option value='Todos'>Todos</option>");
	});	
	
	$("#provincia").on("change",function(){
		if($("#provincia").val()!="Todos"){
			$.ajax({
				url: url_base + "programaeducativo/distritos/byprovincia/"+$("#provincia").val(),
				success: function(respuesta) {
					var contenido = "<option value='Todos'>Todos</option>";
					for(var i=0;i<respuesta.length;i++){
						contenido = contenido + "<option value="+respuesta[i].id+">"+respuesta[i].descripcion+"</option>";
					}
					$("#distrito").html(contenido);
				},
				error: function() {
			        console.log("No se ha podido obtener la información");
			    }
			});
		}
		else{
			$("#distrito").html("<option value='Todos'>Todos</option>");
		}
		
	});
	
	var table_ie = $('#table_ie').DataTable({
        dom: 'Bfrtip',
        "scrollX": true,
        "destroy" : true ,
        lengthMenu: [
            [ 10, 15, 25, -1 ],
            [ '10 filas', '15 filas', '25 filas', 'Mostrar todas' ]
        ],
        "processing":true,
        language: {
            buttons: {
              pageLength: 'Mostrar'
            },
            "decimal": "",
            "emptyTable": "No hay información",
            "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
            "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
            "infoFiltered": "(Filtrado de _MAX_ total entradas)",
            "infoPostFix": "",
            "thousands": ",",
            "lengthMenu": "Mostrar _MENU_ Entradas",
            "loadingRecords": "..",
            "processing": "Procesando...",
            "search": "Buscar:",
            "zeroRecords": "Sin resultados encontrados",
            "paginate": {
                "first": "Primero",
                "last": "Ultimo",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        },
        'ajax' : {
            "url" : url_base + 'programaeducativo/programaseducativos/listaprogeduc',
            "type" : "GET",
            "dataSrc" : ""
        },
        'columns' : [
        	{ 
        		"data": null, "render": function (data, type, full, meta) { return meta.row + 1; }
        	},
        	{ 'data' : 'departamento' },
            { 'data' : 'provincia' },
            { 'data' : 'distrito' },
            { 'data' : 'insteduc' },
            { 'data' : 'codlocalie' },
            { 'data' : 'fecharegistro' },
            { 'data' : 'concurso'},
            { 'data' : 'nivel'},
            { 'data' : 'ambito'},
            { 'data' : 'id'},
            { 'data' : 'tipoieid'},
            { 'data' : 'dirie'},
            { 'data' : 'dre'},
            { 'data' : 'ugel'},
            { 'data' : 'telfie'},
            { 'data' : 'mailie'},
            { 'data' : 'facebook'},
            { 'data' : 'lengua'},
            { 'data' : 'genero'},
            { 'data' : 'turno'},
            { 'data' : 'provedor'},
            { 'data' : 'suministro'},
            { 'data' : 'hora_abastecimiento'},
            { 'data' : 'piscina'},
            { 'data' : 'tipodocdir'},
            { 'data' : 'nrodocidentdir'},
            { 'data' : 'apedir'},
            { 'data' : 'nomdir'},
            { 'data' : 'teldir'},
            { 'data' : 'celdir'},
            { 'data' : 'correodir'},
            { 'data' : 'tipodocprof'},
            { 'data' : 'nrodocidentprof'},
            { 'data' : 'apeprof'},
            { 'data' : 'nomprof'},
            { 'data' : 'telprof'},
            { 'data' : 'celprof'},
            { 'data' : 'correoprof'}
        ],
        colReorder: true,
        buttons: [
        	{
        		extend: 'excel',
                title: 'INSTITUCIONES EDUCATIVAS DEL PROGRAMA EDUCATIVO',
                messageTop: function(){
                	return '[ Fecha desde: ' + $("#fecha_desde").val() + ']            [ Fecha hasta: '+ $("#fecha_hasta").val() + ']           [ Nombre: ' + $("#nomie").val() + ']           [ Departamento: ' + $("#departamento option:selected").text() + ']           [ Provincia: '+$("#provincia option:selected").text()+' ]          [ Distrito: '+$("#distrito option:selected").text()+']     [ Estado: '+$("#estado option:selected").text()+ ']';
                },
                filename: nombre_archivo
        	}
        ]
    });
	
	/*table_ie.on('order.dt search.dt', function () {
		table_ie.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    }).draw();*/
	/*table_ie.on('order.dt search.dt', function () {
		table_ie.column(0, { search: 'applied', order: 'applied' }).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();*/
	
	table_ie.column(10).visible(false);
	table_ie.column(11).visible(false);
	table_ie.column(12).visible(false);
	table_ie.column(13).visible(false);
	table_ie.column(14).visible(false);
	table_ie.column(15).visible(false);
	table_ie.column(16).visible(false);
	table_ie.column(17).visible(false);
	table_ie.column(18).visible(false);
	table_ie.column(19).visible(false);
	table_ie.column(20).visible(false);
	table_ie.column(21).visible(false);
	table_ie.column(22).visible(false);
	table_ie.column(23).visible(false);
	table_ie.column(24).visible(false);
	table_ie.column(25).visible(false);
	table_ie.column(26).visible(false);
	table_ie.column(27).visible(false);
	table_ie.column(28).visible(false);
	table_ie.column(29).visible(false);
	table_ie.column(30).visible(false);
	table_ie.column(31).visible(false);
	table_ie.column(32).visible(false);
	table_ie.column(33).visible(false);
	table_ie.column(34).visible(false);
	table_ie.column(35).visible(false);
	table_ie.column(36).visible(false);
	table_ie.column(37).visible(false);
	table_ie.column(38).visible(false);
	
	/*table_ie.columns().every( function () {
        var that = this; 
        $('input', this.footer()).on('keyup change clear', function() {
            if ( that.search() !== this.value ) {
                that.search( this.value ).draw();
            }
        });
    });*/	
});

function filtraUbigeo(dato,campo){
	if(dato==="Todos")
		return true;
	return dato===campo;
}

function filtraNombre(nombre,campo){
	if(nombre.trim()==="")
		return true;
	var posicion = campo.indexOf(nombre); 
	if(posicion != -1)
		return  true;
	return false;
}


function filtraFecha(fecha_desde,fecha_hasta,campo){
	console.log("filtrafecha...");
	/*var momentUserRegisterDate = moment(new Date(campo), DATE_FORMAT);
	console.log("fecha registro : "+momentUserRegisterDate);
	if(fecha_desde==="" && fecha_hasta===""){
		return true;
	}
	if(fecha_desde!="" && fecha_hasta===""){
		fecha_desde = fecha_desde.split('-');
		fecha_desde = fecha_desde[2]+'-'+fecha_desde[1]+'-'+fecha_desde[0];		
	    let momentStartDateFilter = moment(new Date(fecha_desde), DATE_FORMAT);
	    return momentUserRegisterDate>=momentStartDateFilter;
	}
	else if(fecha_desde===""  && fecha_hasta!=""){
		fecha_hasta = fecha_hasta.split('-');
		fecha_hasta = fecha_hasta[2]+'-'+fecha_hasta[1]+'-'+fecha_hasta[0];		
	    let momentEndDateFilter = moment(new Date(fecha_hasta), DATE_FORMAT);
	    return momentUserRegisterDate<=momentEndDateFilter;
	}
	else{
		fecha_desde = fecha_desde.split('-');
		fecha_desde = fecha_desde[2]+'-'+fecha_desde[1]+'-'+fecha_desde[0];
		fecha_hasta = fecha_hasta.split('-');
		fecha_hasta = fecha_hasta[2]+'-'+fecha_hasta[1]+'-'+fecha_hasta[0];	
		let momentStartDateFilter = moment(new Date(fecha_desde), DATE_FORMAT);
	    let momentEndDateFilter = moment(new Date(fecha_hasta), DATE_FORMAT);
	    return momentUserRegisterDate>=momentStartDateFilter && momentUserRegisterDate<=momentEndDateFilter;
	}*/
} 

function filtraConcurso(dato,campo){
	if(dato==="Todos")
		return true;
	return dato===campo;	
}

function validarFecha(){
	
}



