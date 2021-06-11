$(document).ready(function(){
    $("#btnbuscar").on("click",function(){
        var ods = $('#ods').val();
        var anio = $('#anio').val();
        var modalidad = $('#modalidad').val();
        var categoria = $('#categoria').val();
        var nivel = $('#nivel').val();
        var txtnombreIE = $('#txtnombreIE').val();
        
        
        
    });
    
    $('.dt-buttons').hide();
});

var table_ie = $('#table_ie').DataTable({
    dom: 'Bfrtip',
    "scrollX": true,
    "searching": true,
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
        "infoEmpty": "Mostrando 0 a 0 de 0 Entradas",
        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
        "infoPostFix": "",
        "thousands": ",",
        "lengthMenu": "Mostrar _MENU_ Entradas",
        "loadingRecords": "Cargando Registros...",
        "processing": "Procesando...",
        "search": "Buscar:",
        "zeroRecords": "No existen registros",//No existen registros
        "paginate": {
            "first": "Primero",
            "last": "Ultimo",
            "next": "Siguiente",
            "previous": "Anterior"
        }
    },
    'ajax' : {
        "url" : url_base + 'pedesa/listatrabpendientes',
        "type" : "GET",
        "dataSrc" : ""
    },
    'columns' : [
        { 'data' : null, "render": function (data, type, full, meta) { return meta.row + 1; }},
        { 'data' : 'anio'},
        { 'data' : 'codigotrabajo' },
        { 'data' : 'ods' },
        { 'data' : 'codigo_ie' },
        { 'data' : 'nombre_ie' },
        { 'data' : 'categoria' },                
        { 'data' : 'modalidad' },
        { 'data' : 'titulo_trabajo' },
        { 'data' : 'nivel'},
        { 'data' : 'ficha','searchable': false},
        { 'data' : 'evidencia','searchable': false},
        { 'data' : 'trabajo','searchable': false},
        { 'data' : 'permiso','searchable': false},
        { 'data' : 'accion','searchable': false}
    ],
    /*columnDefs: [
        {
            type: 'date-euro',"targets": 6,
                    "render": function(data, type, row, meta){
                            return moment(data).format('DD/MM/YYYY HH:mm');
                    }
            },
            { "visible": false, "targets": 11 },
            { "visible": false, "targets": 12 },
            { "visible": false, "targets": 13 },
            { "visible": false, "targets": 14 },
            { "visible": false, "targets": 15 },
            { "visible": false, "targets": 16 },
            { "visible": false, "targets": 17 },
            { "visible": false, "targets": 18 },
            { "visible": false, "targets": 19 },
            { "visible": false, "targets": 20 },
            { "visible": false, "targets": 21 },
            { "visible": false, "targets": 22 },
            { "visible": false, "targets": 23 },
            { "visible": false, "targets": 24 },
            { "visible": false, "targets": 25 },
            { "visible": false, "targets": 26 },
            { "visible": false, "targets": 27 },
            { "visible": false, "targets": 28 },
            { "visible": false, "targets": 29 },
            { "visible": false, "targets": 30 },
            { "visible": false, "targets": 31 },
            { "visible": false, "targets": 32 },
            { "visible": false, "targets": 33 },
            { "visible": false, "targets": 34 },
            { "visible": false, "targets": 35 },
            { "visible": false, "targets": 36 },
            { "visible": false, "targets": 37 },
            { "visible": false, "targets": 38 },
            { "visible": false, "targets": 39 },
            { "visible": false, "targets": 40 },
            { "visible": false, "targets": 41 },
            { "visible": false, "targets": 42 }
    ],*/
    "fnPreDrawCallback": function(oSettings) {
        console.log("inicio carga");
        $("#consulta_menu").prop("disabled",true);
        $("#btnbuscar").prop("disabled",true);
    },
    buttons: [
        {
            extend: 'excelHtml5',
            text:'<i class="far fa-file-excel"></i>',
            title: 'TRABAJOS FINALES ',
            messageTop: function(){
                return '[ Fecha desde: ' + $("#fecha_desde").val() + ']            [ Fecha hasta: '+ $("#fecha_hasta").val() + ']           [ Nombre: ' + $("#nomie").val() + ']           [ Departamento: ' + $("#departamento option:selected").text() + ']           [ Provincia: '+$("#provincia option:selected").text()+' ]          [ Distrito: '+$("#distrito option:selected").text()+']     [ Estado: '+$("#estado option:selected").text()+ ']';
            },
            filename : function(){
                var hoy = new Date();
                var dia = String(hoy.getDate());
                if(dia.length==1){
                        dia = "0"+dia;
                }
                var mes = String(hoy.getMonth()+1);
                if(mes.length==1){
                        mes = "0"+mes;
                }
                var hora = String(hoy.getHours());
                if(hora.length==1){
                        hora = "0"+hora;
                }
                var minuto = String(hoy.getMinutes());
                if(minuto.length==1){
                        minuto = "0"+minuto;
                }
                return "PE"+hoy.getFullYear()+'_'+dia+'-'+mes+'-'+hoy.getFullYear()+'_'+hora + minuto;
            }
        }
    ]
});
