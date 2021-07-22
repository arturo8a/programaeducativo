var data_ubigeo = "";
var arraysuministro = new Array();
var url_base = location.origin+"/";
var programaeducativoid;
var codigo_seleccionado = "";
var textoValidarControles = "";
var bandera=true;

var clickeable = false;
var focounico = false;

$(document).ready(function(){
	
	var fechaactual = new Date();
	
	$.ajax({
		type : "POST",
	    contentType : "application/json",
	    url : url_base + "pedesa/anioaperturado/"+fechaactual.getFullYear(),
	    dataType : 'json',
		success: function(respuesta) {
			console.log(respuesta);
			if(!respuesta){
				/*año no esta aperturado*/
				limpiarControles();
				desabilitarControles();
			}
			else{
				habilitarControles();
			}
		},
		error: function() {
			$("#modalimagencargando").modal('hide');
			$("#textoerror").html("Excepcion al ver Año actual");
			$('#modalerror').modal({
				show : true,
				backdrop : 'static',
				keyboard:false
			});
	    }
	});
	
	$("#codmod").val("");	
	limpiarControles();
	
	if(!focounico){
		$("#codmod").focus();
		focounico = true;
	}	
	
	$("#docprof").prop("disabled",true);
	$("#docdir").prop("disabled",true);
	//uncheckPostularConcurso();
	
	$("#divmsjSistemaEducativoFormAdd").css("display","none");
	
	$.ajax({
		url: url_base + "pedesa/departamentos",
		success: function(respuesta) {
			data_ubigeo  =  respuesta;
		},
		error: function() {
	        console.log("No se ha podido obtener la información");
	    }
	});
	
	$.ajax({
		url: url_base + "pedesa/listcegroupbycodmod",
		success: function(respuesta) {
			$( "#codmod" ).autocomplete({
		      source: respuesta,
		      select: function( event, ui ) {
		    	  //console.log("ui.item.value "+ ui.item.value);
		    	  codigo_seleccionado = ui.item.value;
		    	  buscarCodmod(ui.item.value);
		      }
		    });
		},
		error: function() {
	        console.log("No se ha podido obtener la información");
	    }
	});
	
	$("#departamentoid").on("change",function(){
		$("#provinciaid").html("");
		$("#distritoid").html("");
		$.ajax({
			url: url_base + "pedesa/provincias/bydepa/"+$("#departamentoid").val(),
			success: function(respuesta) {
				var contenido = "<option value=''>Seleccione</option>";
				for(var i=0;i<respuesta.length;i++){
					contenido = contenido + "<option value="+respuesta[i].id+">"+respuesta[i].descripcion+"</option>";
				}
				$("#provinciaid").html(contenido);
			},
			error: function() {
		        console.log("No se ha podido obtener la información");
		    }
		});
	});	
	
	$("#provinciaid").on("change",function(){
		$.ajax({
			url: url_base + "pedesa/distritos/byprovincia/"+$("#provinciaid").val(),
			success: function(respuesta) {
				var contenido = "<option value=''>Seleccione</option>";
				for(var i=0;i<respuesta.length;i++){
					contenido = contenido + "<option value="+respuesta[i].id+">"+respuesta[i].descripcion+"</option>";
				}
				$("#distritoid").html(contenido);
			},
			error: function() {
		        console.log("No se ha podido obtener la información");
		    }
		});
	});
	
	$("#tipodocdir").on("change",function(){
		
		if($("#tipodocdir").val()!=""){
			$("#docdir").prop('disabled', false);
			$("#docdir").val("");
			$("#docdir").focus();
		}
		else{
			$("#docdir").val("");
			$("#docdir").prop('disabled', true);
		}
	});
	
	$("#tipodocprof").on("change",function(){
		if($("#tipodocprof").val()!=""){
			console.log("2");
			$("#docprof").prop('disabled', false);
			$("#docprof").val("");
			$("#docprof").focus();
		}
		else{
			$("#docprof").val("");
			$("#docprof").prop('disabled', true);
		}
	});
	
	$("#codmod").focusout(function(){
		
		if($("#codmod").val().trim()!=""){
			var codmod = $("#codmod").val();
			if(codmod != codigo_seleccionado){
				buscarCodmod(codmod);
			}
		}
		else{
			limpiarControles();
		}
	});
	
	$("#nomie").focusout(function(){		
		if($("#nomie").val().trim()!=""){
			$("#nomie").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#tipoieid").focusout(function(){		
		if($("#tipoieid").val().trim()!=""){
			$("#tipoieid").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#departamentoid").focusout(function(){		
		if($("#departamentoid").val().trim()!=""){
			$("#departamentoid").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#provinciaid").focusout(function(){		
		if($("#provinciaid").val().trim()!=""){
			$("#provinciaid").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#distritoid").focusout(function(){		
		if($("#distritoid").val().trim()!=""){
			$("#distritoid").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#dirie").focusout(function(){		
		if($("#dirie").val().trim()!=""){
			$("#dirie").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#dre").focusout(function(){		
		if($("#dre").val().trim()!=""){
			$("#dre").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#ugel").focusout(function(){		
		if($("#ugel").val().trim()!=""){
			$("#ugel").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#telfie").focusout(function(){		
		if($("#telfie").val().trim()!=""){
			$("#telfie").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#mailie").focusout(function(){		
		if($("#mailie").val().trim()!=""){
			$("#mailie").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#facebook").focusout(function(){		
		if($("#facebook").val().trim()!=""){
			$("#facebook").css({"color":"black","border":"1px solid #ced4da"});
		}
	});	
	$("#lengua").focusout(function(){		
		if($("#lengua").val().trim()!=""){
			$("#lengua").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	
	
	
	$("input[name=ambradio]").click(function(){
		$("#lblambito").removeClass("group_checkbox_red");
		//$("#lblambito").addClass("group_checkbox_red");
	});
	
	$("input[name=modensradio]").click(function(){
		$("#lblmodens").removeClass("group_checkbox_red");
		//$("#lblambito").addClass("group_checkbox_red");
	});
	
	$("input[name=genradio]").click(function(){
		$("#lblgenero").removeClass("group_checkbox_red");
		//$("#lblambito").addClass("group_checkbox_red");
	});
	
	$("#turradio1").click(function(){
		$("#lblturno").removeClass("group_checkbox_red");
	});
	$("#turradio2").click(function(){
		$("#lblturno").removeClass("group_checkbox_red");
	});
	$("#turradio3").click(function(){
		$("#lblturno").removeClass("group_checkbox_red");
	});
	
	
	$("input[name=piscina]").click(function(){
		$("#lblPiscina").removeClass("group_checkbox_red");
		//$("#lblambito").addClass("group_checkbox_red");
	});
	
	/*$('input:radio[name=ambradio]:checked').val()==""*/	
	$("#proveedor").focusout(function(){		
		if($("#proveedor").val().trim()!=""){
			$("#proveedor").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#abastecimiento").focusout(function(){		
		if($("#abastecimiento").val().trim()!=""){
			$("#abastecimiento").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#tipodocdir").focusout(function(){		
		if($("#tipodocdir").val().trim()!=""){
			$("#tipodocdir").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#docdir").focusout(function(){		
		if($("#docdir").val().trim()!=""){
			$("#docdir").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#apedir").focusout(function(){		
		if($("#apedir").val().trim()!=""){
			$("#apedir").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#nomdir").focusout(function(){		
		if($("#nomdir").val().trim()!=""){
			$("#nomdir").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#telfdir").focusout(function(){		
		if($("#telfdir").val().trim()!=""){
			$("#telfdir").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#celdir").focusout(function(){		
		if($("#celdir").val().trim()!=""){
			$("#celdir").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#maildir").focusout(function(){		
		if($("#maildir").val().trim()!=""){
			$("#maildir").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#tipodocprof").focusout(function(){		
		if($("#tipodocprof").val().trim()!=""){
			$("#tipodocprof").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#docprof").focusout(function(){		
		if($("#docprof").val().trim()!=""){
			$("#docprof").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#apeprof").focusout(function(){		
		if($("#apeprof").val().trim()!=""){
			$("#apeprof").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#nomprof").focusout(function(){		
		if($("#nomprof").val().trim()!=""){
			$("#nomprof").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#telfprof").focusout(function(){		
		if($("#telfprof").val().trim()!=""){
			$("#telfprof").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#celprof").focusout(function(){		
		if($("#celprof").val().trim()!=""){
			$("#celprof").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	$("#mailprof").focusout(function(){		
		if($("#mailprof").val().trim()!=""){
			$("#mailprof").css({"color":"black","border":"1px solid #ced4da"});
		}
	});
	
	
	
	
	$("#btn_agregarsuministro").click(function(){
		var suministro = $("#suministro").val();
		agregar_suministro(suministro);
	});
	
	$("#btnregistrarprogeduc").click(function(){
		
		/*var msj = validarControles();
		if(msj!=""){
			alert("Debe ingresar "+msj);
		}
		else{
			$('#modalcargando').modal({
				backdrop: 'static'
			});
		}*/
		
		var validacion = validarControles();
		if(validacion){
			$('#modalcargando').modal({
				backdrop: 'static'
			});
		}
		else{
			$("#textoinformacion").html(textoValidarControles);
			$("#modalinformacion").modal("show");
		}
	});
	$("#docdir").keypress(function() {
		if($("#tipodocdir").val() == 1){
			if($("#tipodocdir").val().trim().length<=8)
				return true;
			return false;
		}
	});
	
	$("#btnconfirmarprogeduc").click(function(){
		
		if(!clickeable){
				clickeable = true;
		
				$("#modalcargando").modal('hide');
				$("#modalimagencargando").modal({
					show : true,
					backdrop : 'static',
					keyboard:false
				});
				
				/*registrar programa educativo*/
				
				var codmod = $("#codmod").val();
				var nomie = $("#nomie").val();
				var tipoieid = $("#tipoieid").val() 
				var distritoid = $("#distritoid").val();
				var dirie = $("#dirie").val();
				var dre = $("#dre").val();
				var ugel = $("#ugel").val();
				var telfie = $("#telfie").val();
				var mailie = $("#mailie").val();
				var facebook = $("#facebook").val();
				var lenguaid = $("#lengua").val();
				var ambitoid = $('input:radio[name=ambradio]:checked').val();
				var modensenianzaid = $('input:radio[name=modensradio]:checked').val();
				var generoid = $('input:radio[name=genradio]:checked').val();
				var proveedorid = $("#proveedor").val();		
				var abastecimiento = $("#abastecimiento").val();
				var piscinaid = $('input:radio[name=piscina]:checked').val();
				var tipodocidentdirid = $("#tipodocdir").val();
				var docdir = $("#docdir").val();
				var apedir = $("#apedir").val();
				var nomdir = $("#nomdir").val();
				var generodir = $("#generodir").val();
				var telfdir = $("#telfdir").val();
				var celdir = $("#celdir").val();
				var maildir = $("#maildir").val();
				var tipodocidentprofid = $("#tipodocprof").val();
				var docprof = $("#docprof").val();
				var apeprof = $("#apeprof").val();
				var nomprof = $("#nomprof").val();
				var generoprof = $("#generoprof").val();
				var telfprof = $("#telfprof").val();
				var celprof = $("#celprof").val();
				var mailprof = $("#mailprof").val();	
				
				var listSuministro = new Array();;
				for(var i=0;i<arraysuministro.length;i++){
					listSuministro.push({
						numero : arraysuministro[i]
					});
				}
				
				var progeduc = {
					codmod : codmod,
					nomie : nomie,
					tipoie : {
						id: tipoieid
					},
					distrito:{
						id: distritoid
					},
					dirie : dirie,
					dre : dre,
					ugel : ugel,
					telfie : telfie,
					mailie : mailie,
					facebook : facebook,
					lengua : {
						id : lenguaid
					},
					ambito : {
						id: ambitoid
					},
					modensenianza: {
						id: modensenianzaid
					},
					genero : {
						id: generoid,
					},
					piscina: {
						id : piscinaid
					},
					proveedor:{
						id : proveedorid
					},
					abastecimiento: abastecimiento,
					tipodocidentdir:{
						id: tipodocidentdirid
					},
					docdir : docdir,
					apedir : apedir,
					nomdir : nomdir,
					generodir : {
						id:generodir
					},
					telfdir : telfdir,
					celdir : celdir,
					maildir: maildir,			
					tipodocidentprof : {
						id: tipodocidentprofid
					},
					docprof : docprof,
					apeprof : apeprof,
					nomprof : nomprof,
					generoprof : {
						id:generoprof
					},
					telfprof : telfprof,
					celprof : celprof,
					mailprof : mailprof	,
					suministro: listSuministro,
					dep : $("#departamentoid").val(),
					prov : $("#provinciaid").val(),
					concurso: 0,
					estado : 'Pendiente'
				};
				
				var listNivel = new Array();
				
				if($("#nini").is(':checked')) {            
					var datai = {
						nrosecciones: $("#nseci").val(),
						nrodocentes: $("#ndoci").val(),
						nroalumnos: $("#nalui").val(),
						nrovarones: $("#nvari").val(),
						nromujeres: $("#nmuji").val(),
						tiponivel: {
							id :1
						}
					};
					listNivel.push(datai);
				}
				
				if($("#ninp").is(':checked')) {  
					var datap = {
							nrosecciones: $("#nsecp").val(),
							nrodocentes: $("#ndocp").val(),
							nroalumnos: $("#nalup").val(),
							nrovarones: $("#nvarp").val(),
							nromujeres: $("#nmujp").val(),
							tiponivel: {
								id: 2
							}
						};
					listNivel.push(datap);
				}
				
				if($("#nins").is(':checked')) {  
					
					var datas = {
							nrosecciones: $("#nsecs").val(),
							nrodocentes: $("#ndocs").val(),
							nroalumnos: $("#nalus").val(),
							nrovarones: $("#nvars").val(),
							nromujeres: $("#nmujs").val(),
							tiponivel: {
								id: 3
							}
						};
					listNivel.push(datas);
				}
				
				var listTurno = new Array();
				
				if($("#turradio1").is(':checked')) {  
					listTurno.push({
						id: 1
					});
				}
				if($("#turradio2").is(':checked')) {  
					listTurno.push({
						id: 2
					});
				}
				if($("#turradio3").is(':checked')) {  
					listTurno.push({
						id : 3
					});
				}
				
				var fechaactual = new Date();
				var anioactual = fechaactual. getFullYear();
					
				var obj = {
						progeduc : progeduc,
						listNivel : listNivel,
						listTurno : listTurno
					};
				
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : url_base + "pedesa/saveturnonivel",
					data : JSON.stringify(obj),
					dataType : 'json',
					success: function(respuesta) {
						if(respuesta){
							$("#modalimagencargando").modal('hide');
							$("#textoexito").html("Registro exitoso, pronto recibirá un correo de confirmación a la dirección electrónica registrada.");
							$('#modalexito').modal({
								show : true,
								backdrop : 'static',
								keyboard:false								
							});
							clickeable = false;
						}
						else{
							$("#modalimagencargando").modal('hide');
							$("#textoexito").html("Se registro correctamente en el Programa Educativo, pero hubo problemas al enviar las credenciales al correo institucional. Comuníquese con la Oficina de Tecnologías de Información de Sunass.");
							$("#codmod").val("");
							limpiarControles();
						}
					},
					error: function() {
						$("#modalimagencargando").modal('hide');
						alert("Error al Registrarse en el Programa Educativo. Comunicarse con la Oficina de Tecnología de Información de Sunass");
					}
				});		
		}
	});
	
	$("#btncancelarprogeduc").click(function(){		
		$("#modalcargando").modal('hide');
	});
	
	$("#btncerrarmensajeexito").click(function(){
		$("#modalexito").modal('hide');
		$("#codmod").val("");
		limpiarControles();
	});
	
	$("#btncerrarmensajeinformacion").click(function(){
		$("#modalinformacion").modal('hide');
	});
	
	$("#btncerrarmensajeerror").click(function(){
		$("#modalerror").modal('hide');
	});
	
	$("#nini").click(function(){
		$("#lblNivel").removeClass("group_checkbox_red");
		if(verificarCheck("#nini")){
			$("#nseci").prop( "disabled", false);
			$("#ndoci").prop( "disabled", false);
			//$("#nalui").prop( "disabled", false);
			$("#nvari").prop( "disabled", false);
			$("#nmuji").prop( "disabled", false);
			$("#nseci").focus();
		}
		else{
			$("#nseci").prop( "disabled", true);
			$("#ndoci").prop( "disabled", true);
			//$("#nalui").prop( "disabled", true);
			$("#nvari").prop( "disabled", true);
			$("#nmuji").prop( "disabled", true);
			
			$("#nseci").val(0);
			$("#ndoci").val(0);
			$("#nalui").val(0);
			$("#nvari").val(0);
			$("nmuji").val(0);
		}			
	});
	
	$("#ninp").click(function(){
		$("#lblNivel").removeClass("group_checkbox_red");
		if(verificarCheck("#ninp")){
			$("#nsecp").prop( "disabled", false);
			$("#ndocp").prop( "disabled", false);
			//$("#nalup").prop( "disabled", false);
			$("#nvarp").prop( "disabled", false);
			$("#nmujp").prop( "disabled", false);
			$("#nsecp").focus();
		}
		else{
			$("#nsecp").prop( "disabled", true);
			$("#ndocp").prop( "disabled", true);
			//$("#nalup").prop( "disabled", true);
			$("#nvarp").prop( "disabled", true);
			$("#nmujp").prop( "disabled", true);
			
			$("#nsecp").val(0);
			$("#ndocp").val(0);
			$("#nalup").val(0);
			$("#nvarp").val(0);
			$("nmujp").val(0);
		}		
	});
	
	$("#nins").click(function(){
		$("#lblNivel").removeClass("group_checkbox_red");
		if(verificarCheck("#nins")){
			$("#nsecs").prop( "disabled", false);
			$("#ndocs").prop( "disabled", false);
			//$("#nalus").prop( "disabled", false);
			$("#nvars").prop( "disabled", false);
			$("#nmujs").prop( "disabled", false);
			$("#nsecs").focus();
		}
		else{
			$("#nsecs").prop( "disabled", true);
			$("#ndocs").prop( "disabled", true);
			//$("#nalus").prop( "disabled", true);
			$("#nvars").prop( "disabled", true);
			$("#nmujs").prop( "disabled", true);
			
			$("#nsecs").val(0);
			$("#ndocs").val(0);
			$("#nalus").val(0);
			$("#nvars").val(0);
			$("nmujs").val(0);
		}
	});	
	
	$("#btnpostular").click(function(){
		
		$("#modalpostular").modal('hide');
		$("#modalimagencargando").modal({
			backdrop: 'static'
		});
		
		var contador=0;
		if($("#cat1").is(':checked')){
			contador +=1;
	    }
		if($("#cat2").is(':checked')){	
			contador +=1;
	    }
		if($("#cat3").is(':checked')){
			contador +=1;
	    }
		if($("#cat4").is(':checked')){
			contador +=1;
	    }
		if(contador==1){
			fncPostular(1);
		}
		else{
			alert("Debe seleccionar una categoria para Postular al Concurso");
		}
	});
	
	$("#btnsalirpostulacion").click(function(){
		fncPostular(0);		
	});
	
	$("#btnlimpiarformprogeduc").click(function(){
		var conf = confirm("¿Desea limpiar todos los datos del formulario?");
		if(conf){
			$("select, input").css({"color":"black","border":"1px solid #ced4da"});
			$("label").removeClass("group_checkbox_red");
			$("#codmod").val("");
			limpiarControles();
		}
	});
});

function uncheckPostularConcurso(){
    var cat1 = document.getElementById("cat1");
    var cat2 = document.getElementById("cat2");
    var cat3 = document.getElementById("cat3");
    var cat4 = document.getElementById("cat4");
    cat1.onclick = function(){ 
	   if(cat1.checked != false){ 
		   cat2.checked =null;
		   cat3.checked =null; 
		   cat4.checked =null; 
	   }
    } 
    cat2.onclick = function(){ 
	   if(cat2.checked != false){ 
		   cat1.checked =null;
		   cat3.checked =null; 
		   cat4.checked =null; 
	   }
    } 
    cat3.onclick = function(){ 
 	   if(cat3.checked != false){ 
 		   cat1.checked =null;
 		   cat2.checked =null; 
 		   cat4.checked =null; 
 	   }
     } 
     cat4.onclick = function(){ 
 	   if(cat4.checked != false){ 
 		   cat1.checked =null;
 		   cat2.checked =null; 
 		   cat3.checked =null; 
 	   }
     } 
}


function fncPostular(indicador){
	
	var valorConcurso = 0;
	
	var codmod = $("#codmod").val().trim();
	var nomie = $("#nomie").val();
	var tipoieid = $("#tipoieid").val() 
	var distritoid = $("#distritoid").val();
	var dirie = $("#dirie").val();
	var dre = $("#dre").val();
	var ugel = $("#ugel").val();
	var telfie = $("#telfie").val();
	var mailie = $("#mailie").val();
	var facebook = $("#facebook").val();
	var lenguaid = $("#lengua").val();
	var ambitoid = $('input:radio[name=ambradio]:checked').val();
	var modensenianzaid = $('input:radio[name=modensradio]:checked').val();
	var generoid = $('input:radio[name=genradio]:checked').val();
	var proveedorid = $("#proveedor").val();		
	var abastecimiento = $("#abastecimiento").val();
	var piscinaid = $('input:radio[name=piscina]:checked').val();
	var tipodocidentdirid = $("#tipodocdir").val();
	var docdir = $("#docdir").val();
	var apedir = $("#apedir").val();
	var nomdir = $("#nomdir").val();
	var telfdir = $("#telfdir").val();
	var celdir = $("#celdir").val();
	var maildir = $("#maildir").val();
	var tipodocidentprofid = $("#tipodocprof").val();
	var docprof = $("#docprof").val();
	var apeprof = $("#apeprof").val();
	var nomprof = $("#nomprof").val();
	var telfprof = $("#telfprof").val();
	var celprof = $("#celprof").val();
	var mailprof = $("#mailprof").val();	
	
	var listSuministro = new Array();;
	for(var i=0;i<arraysuministro.length;i++){
		listSuministro.push({
			numero : arraysuministro[i]
		});
	}
	
	var listCategoria = new Array();
	
	if($("#cat1").is(':checked')){
		valorConcurso = 1;
		listCategoria.push({
			numeroCat: 1
		});
    }
	if($("#cat2").is(':checked')){	
		valorConcurso = 1;
		listCategoria.push({
			numeroCat: 2
		});
    }
	if($("#cat3").is(':checked')){
		valorConcurso = 1;
		listCategoria.push({
			numeroCat: 3
		});
    }
	if($("#cat4").is(':checked')){
		valorConcurso = 1;
		listCategoria.push({
			numeroCat: 4
		});
    }
	
	var progeduc = {
		codmod : codmod,
		nomie : nomie,
		tipoie : {
			id: tipoieid
		},
		distrito:{
			id: distritoid
		},
		dirie : dirie,
		dre : dre,
		ugel : ugel,
		telfie : telfie,
		mailie : mailie,
		facebook : facebook,
		lengua : {
			id : lenguaid
		},
		ambito : {
			id: ambitoid
		},
		modensenianza: {
			id: modensenianzaid
		},
		genero : {
			id: generoid,
		},
		piscina: {
			id : piscinaid
		},
		proveedor:{
			id : proveedorid
		},
		abastecimiento: abastecimiento,
		tipodocidentdir:{
			id: tipodocidentdirid
		},
		docdir : docdir,
		apedir : apedir,
		nomdir : nomdir,
		telfdir : telfdir,
		celdir : celdir,
		maildir: maildir,			
		tipodocidentprof : {
			id: tipodocidentprofid
		},
		docprof : docprof,
		apeprof : apeprof,
		nomprof : nomprof,
		telfprof : telfprof,
		celprof : celprof,
		mailprof : mailprof	,
		suministro: listSuministro,
		categoria : listCategoria,
		dep : $("#departamentoid").val(),
		prov : $("#provinciaid").val(),
		concurso : valorConcurso
	};
	
	var listNivel = new Array();
	
	if($("#nini").is(':checked')) {            
		var datai = {
			nrosecciones: $("#nseci").val(),
			nrodocentes: $("#ndoci").val(),
			nroalumnos: $("#nalui").val(),
			nrovarones: $("#nvari").val(),
			nromujeres: $("#nmuji").val(),
			tiponivel: {
				id :1
			}
		};
		listNivel.push(datai);
    }
	
	if($("#ninp").is(':checked')) {  
		var datap = {
				nrosecciones: $("#nsecp").val(),
				nrodocentes: $("#ndocp").val(),
				nroalumnos: $("#nalup").val(),
				nrovarones: $("#nvarp").val(),
				nromujeres: $("#nmujp").val(),
				tiponivel: {
					id: 2
				}
			};
		listNivel.push(datap);
    }
	
	if($("#nins").is(':checked')) {  
		
		var datas = {
				nrosecciones: $("#nsecs").val(),
				nrodocentes: $("#ndocs").val(),
				nroalumnos: $("#nalus").val(),
				nrovarones: $("#nvars").val(),
				nromujeres: $("#nmujs").val(),
				tiponivel: {
					id: 3
				}
			};
		listNivel.push(datas);
    }
	
	var listTurno = new Array();
	
	if($("#turradio1").is(':checked')) {  
		listTurno.push({
			id: 1
		});
    }
	if($("#turradio2").is(':checked')) {  
		listTurno.push({
			id: 2
		});
    }
	if($("#turradio3").is(':checked')) {  
		listTurno.push({
			id : 3
		});
    }
	
	var obj = {
		progeduc : progeduc,
		listNivel : listNivel,
		listTurno : listTurno				
	};
	
	var bandera;
	
	var promesa1 = $.ajax({
		type : "POST",
	    contentType : "application/json",
	    url : url_base + "pedesa/saveturnonivel",
	    data : JSON.stringify(obj),
	    dataType : 'json',
		success: function(respuesta) {
			programaeducativoid = respuesta.id;
			if(programaeducativoid!=null){
				bandera=true;
			}
			else{
				bandera=false;
				console.log("Error al Registrarse en el Programa Educativo");
			}
		},
		error: function() {
			bandera=false;
	        console.log("No se ha podido obtener la información");
	    }
	});
	
	var fechaactual = new Date();
	var anioactual = fechaactual. getFullYear();
	promesa1.then(function(){
		if(bandera){
			
			var obj1  = {
				usuario : {
					usuario : $("#mailie").val().split("@")[0],
					password: "Sunass"+anioactual,
					nombre : $("#nomie").val(),
					email:$("#mailie").val(),
					dni:$("#docdir").val(),
					ciudad: $("#distritoid option:selected").text(),
					tipousuario: {
						id: 3
					},
					estado: 'HABILITADO',
					ods: {
						id: 0
					},
					ciclo: 0
				},
				emailbody: {
					email : $("#mailie").val(),
					content: "Su usuario es "+ $("#mailie").val().split("@")[0] + " y su contraseña es "+ "Sunass"+anioactual,
					subject: "credenciales de su cuenta de acceso al Sistema Educativo"
				}
			};
			
			$.ajax({
				type : "POST",
			    contentType : "application/json",
			    url : url_base + "pedesa/email/send",
			    data : JSON.stringify(obj1),
			    dataType : 'json',
				success: function(respuesta) {			
					if(respuesta){
						$("#modalimagencargando").modal('hide');
						$("#textoenviocorreo").html("Revise su bandeja de correo "+$("#mailie").val()+", se envió usuario y contraseña para el acceso al sistema del Programa Educativo");
						$('#modalmensajexito').modal({
							backdrop: 'static'
						});
					}
					else{
						$("#modalimagencargando").modal('hide');
						alert("Se registro correctamente en el Programa Educativo, pero hubo problemas al enviar las credenciales al correo institucional. Comuníquese con la Oficina de Tecnologías de Información de Sunass.");
						$("#codmod").val("");
						limpiarControles();
					}
				},
				error: function() {
					$("#modalimagencargando").modal('hide');
					alert("Se registro correctamente en el Programa Educativo, pero hubo problemas al enviar las credenciales al correo institucional. Comuníquese con la Oficina de Tecnologías de Información de Sunass.");
					$("#codmod").val("");
					limpiarControles();
			    }
			});
		}
		else{
			$("#modalimagencargando").modal('hide');
			alert("Error al Registrarse en el Programa Educativo. Comunicarse con la Oficina de Tecnología de Información de Sunass");
		}		
	});
}


function buscarCodmod(codmod){	
	
	var distritoid = "";
	$("select , input").css({"color":"black","border":"1px solid #ced4da"});
	$("select , input").css({"color":"black","border":"1px solid #ced4da"});
	$("select , input").css({"color":"black","border":"1px solid #ced4da"});
	$("select , input").css({"color":"black","border":"1px solid #ced4da"});
	
	$("#lblambito").removeClass("group_checkbox_red");
	$("#lblmodens").removeClass("group_checkbox_red");
	$("#lblgenero").removeClass("group_checkbox_red");
	
	$("#lblturno").removeClass("group_checkbox_red");
	$("#lblNivel").removeClass("group_checkbox_red");
	
	$("#lblPiscina").removeClass("group_checkbox_red");
	
	$.ajax({
		url: url_base + "pedesa/getcodmodbaa/"+codmod,
		success: function(respuesta) {
			//console.log('respuesta : '+JSON.stringify(respuesta));
			if(respuesta.id!=null){
				alert("Su Institución Educativa ya se encuentra registrada al Programa Educativo en este año");
				$("#codmod").val("");
				$("#codmod").focus();
				return false;
			}
			else{
				$.ajax({
					url: url_base + "pedesa/searchcodmod/"+codmod,
					success: function(respuesta) {
						JSON.stringify(respuesta);
						if(respuesta.progeduc!=null){					
							var progeduc = respuesta.progeduc;
							if(progeduc.id!=null){
								
								$("#nomie").val(progeduc.nomie);
								if(progeduc.tipoie !=null){
									if(progeduc.tipoie.id<=4){
										$("#tipoieid").val(progeduc.tipoie.id);
									}
									else{
										$("#tipoieid").val("");
									}
								}				
								
								$("#dirie").val(progeduc.dirie);
								$("#dre").val(progeduc.dre);
								$("#ugel").val(progeduc.ugel);
								$("#telfie").val(progeduc.telfie);
								$("#mailie").val(progeduc.mailie);
								$("#facebook").val(progeduc.facebook);
								if(progeduc.lengua != null){
									$("#lengua").val(progeduc.lengua.id);
								}				
								$("#abastecimiento").val(progeduc.abastecimiento);
								if(progeduc.ambito != null){
									$("#ambradio"+progeduc.ambito.id).prop("checked", true);
								}
								if(progeduc.modensenianza != null){
									$("#modensradio"+progeduc.modensenianza.id).prop("checked", true);
								}
								if(progeduc.genero != null){
									$("#genradio"+progeduc.genero.id).prop("checked", true);
								}
								if(progeduc.piscina != null){
									$("#piscina"+progeduc.piscina.id).prop("checked", true);
								}
								if(progeduc.proveedor != null){
									if((progeduc.proveedor.id)!=0 && (progeduc.proveedor.id)!=6 && (progeduc.proveedor.id)!=7){
										$("#proveedor").val(progeduc.proveedor.id);
									}
									else{
										$("#proveedor").val("");
									}									
								}				
								if(progeduc.tipodocidentdir != null){
									$("#docdir").prop("disabled",false);
									$("#tipodocdir").val(progeduc.tipodocidentdir.id);
								}				
								$("#docdir").val(progeduc.docdir);
								$("#apedir").val(progeduc.apedir);
								$("#nomdir").val(progeduc.nomdir);
								$("#telfdir").val(progeduc.telfdir);
								$("#celdir").val(progeduc.celdir);
								$("#maildir").val(progeduc.maildir);
								if(progeduc.tipodocidentprof != null){
									$("#docprof").prop("disabled",false);
									$("#tipodocprof").val(progeduc.tipodocidentprof.id);
								}				
								$("#docprof").val(progeduc.docprof);
								$("#apeprof").val(progeduc.apeprof);
								$("#nomprof").val(progeduc.nomprof);
								$("#telfprof").val(progeduc.telfprof);
								$("#celprof").val(progeduc.celprof);
								$("#mailprof").val(progeduc.mailprof);
								
								var data_departamento="";
								var data_provincia="";
								var data_distrito="";
								
								var departamentoid = 0;
								var provinciaid = 0;
								
								if((progeduc.distrito)!=null){
									
									distritoid = progeduc.distrito.id;
									console.log("data_ubigeo.length "+data_ubigeo.length);
									for(var i=0;i<data_ubigeo.length;i++){
										console.log("i "+i);
										data_departamento = data_ubigeo[i];	
										departamentoid = data_departamento.id;
										for(var j=0;j<data_departamento.provincias.length;j++){
											data_provincia = data_departamento.provincias[j];
											provinciaid = data_provincia.id;
											for(var k=0;k<data_provincia.distrito.length;k++){
												data_distrito = data_provincia.distrito[k];
												if(parseInt(data_distrito.id) === parseInt(distritoid)){
													k=data_provincia.distrito.length;
													j = data_departamento.provincias;
													i = data_ubigeo.length;
												}
											}
										}										
									}
									console.log("data_departamento: "+data_departamento);
									$("#departamentoid").val(departamentoid);	
									var contenido_provincia = "<option>Seleccione</option>";
									var lista_distrito="";
									for(var i=0;i<data_departamento.provincias.length;i++){
										data_provincia = data_departamento.provincias[i];
										if(provinciaid == data_provincia.id){
											lista_distrito=data_provincia.distrito;
										}
										contenido_provincia = contenido_provincia + "<option value="+data_provincia.id+">"+data_provincia.descripcion+"</option>";
									}			
									
									$("#provinciaid").html(contenido_provincia);
									$("#provinciaid").val(provinciaid);
									
									var contenido_distrito = "";
									for(var i= 0;i<lista_distrito.length;i++){
										contenido_distrito  = contenido_distrito + "<option value="+lista_distrito[i].id+">"+lista_distrito[i].descripcion+"</option>";
									}
									
									$("#distritoid").html(contenido_distrito);
									$("#distritoid").val(distritoid);
									
								}
								
								var objsuministro = progeduc.suministro;
								for(var i=0;i<objsuministro.length;i++){
									agregar_suministro(objsuministro[i].numero);
								}								
								var listTurno = respuesta.listTurno;								
								for(var i=0;i<listTurno.length;i++){
									$("#turradio"+listTurno[i].id).prop("checked", true);
								}								
								var listNivel = respuesta.listNivel;
								var tiponivel_id;
								var reg;
								var totalVarones = 0;
								var totalMujeres = 0;
								
								for(var i=0;i<listNivel.length;i++){
									reg = listNivel[i];	
									tiponivel_id=reg.tiponivel.id;									
									switch(tiponivel_id){
										case 1:
											$("#nini").prop("checked",true);
											$("#nseci").val(reg.nrosecciones);
											$("#ndoci").val(reg.nrodocentes);
											$("#nalui").val(reg.nroalumnos);
											$("#nvari").val(reg.nrovarones);
											$("#nmuji").val(reg.nromujeres);
											$("#nseci").prop( "disabled", false);
											$("#ndoci").prop( "disabled", false);
											//$("#nalui").prop( "disabled", false);
											$("#nvari").prop( "disabled", false);
											$("#nmuji").prop( "disabled", false);
											break;
										case 2:   
											$("#ninp").prop("checked",true);
											$("#nsecp").val(reg.nrosecciones);
											$("#ndocp").val(reg.nrodocentes);
											$("#nalup").val(reg.nroalumnos);
											$("#nvarp").val(reg.nrovarones);
											$("#nmujp").val(reg.nromujeres);
											$("#nsecp").prop( "disabled", false);
											$("#ndocp").prop( "disabled", false);
											//$("#nalup").prop( "disabled", false);
											$("#nvarp").prop( "disabled", false);
											$("#nmujp").prop( "disabled", false);
											break;
										case 3:   
											$("#nins").prop("checked",true);
											$("#nsecs").val(reg.nrosecciones);
											$("#ndocs").val(reg.nrodocentes);
											$("#nalus").val(reg.nroalumnos);
											$("#nvars").val(reg.nrovarones);
											$("#nmujs").val(reg.nromujeres);
											$("#nsecs").prop( "disabled", false);
											$("#ndocs").prop( "disabled", false);
											//$("#nalus").prop( "disabled", false);
											$("#nvars").prop( "disabled", false);
											$("#nmujs").prop( "disabled", false);
											break;
									}
									totalVarones += reg.nrovarones;
									totalMujeres += reg.nromujeres;
								}
								$("#lblTotalVarones").html("Total Varones "+totalVarones);
								$("#lblTotalMujeres").html("Total Mujeres "+totalMujeres);
								/*("#codmod").focus();*/
							}					
						}
						else{
							var confirmacion = confirm("Este Centro Educativo no existe en el Sistema. ¿Desea Limpiar los Campos del Formulario para registrarlo?");
							if(confirmacion == true){
								limpiarControles();
								$("#nomie").focus();
							}
							else{
								$("#nomie").focus();
							}
						}
									
					},
					error: function() {
				        console.log("No se ha podido obtener la información");
				    }				
				});				
			}
		}
	});
	
	
}

function validarCantidadDigitos(elemento,mensaje){
	if($("#"+elemento).val().trim().length>0 && $("#"+elemento).val().trim().length<8){
		$('#statusMsg').html("<div class='alert alert-warning alert-dismissible fade show' role='alert'>"+mensaje+"<button type='button' class='close' data-dismiss='alert' onclick='enfocarCampo("+'"'+elemento+'"'+")' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>");  
		$("#divmsjSistemaEducativoFormAdd").css("display","block");
		$('html, body').animate({scrollTop:0}, 'slow');
	}
}

function habilitarControles(){
	
	$("#codmod").prop("disabled", false);
	$("#nomie").prop("disabled", false);
	$("#tipoieid").prop("disabled", false);
	$("#departamentoid").prop("disabled", false);
	$("#provinciaid").prop("disabled", false);
	$("#distritoid").prop("disabled", false);
	$("#dirie").prop("disabled", false);
	$("#dre").prop("disabled", false);
	$("#ugel").prop("disabled", false);
	$("#telfie").prop("disabled", false);
	$("#mailie").prop("disabled", false);
	$("#facebook").prop("disabled", false);
	$("#lengua").prop("disabled", false);
	$("#ambradio1").prop("disabled", false);
	$("#ambradio2").prop("disabled", false);
	$("#ambradio3").prop("disabled", false);
	$("#modensradio1").prop("disabled", false);
	$("#modensradio2").prop("disabled", false);
	$("#modensradio3").prop("disabled", false);
	$("#genradio1").prop("disabled", false);
	$("#genradio2").prop("disabled", false);
	$("#genradio3").prop("disabled", false);
	$("#turradio1").prop("disabled", false);
	$("#turradio2").prop("disabled", false);
	$("#turradio3").prop("disabled", false);
	$("#lengua0").prop("disabled", false);
	$("#lengua1").prop("disabled", false);
	$("#nini").prop("disabled", false);
	$("#ninp").prop("disabled", false);
	$("#nins").prop("disabled", false);
	$("#nseci").prop("disabled", false);
	$("#nsecp").prop("disabled", false);
	$("#nsecs").prop("disabled", false);
	$("#ndoci").prop("disabled", false);
	$("#ndocp").prop("disabled", false);
	$("#ndocs").prop("disabled", false);
	$("#nalui").prop("disabled", false);
	$("#nalup").prop("disabled", false);
	$("#nalus").prop("disabled", false);
	$("#nvari").prop("disabled", false);
	$("#nvarp").prop("disabled", false);
	$("#nvars").prop("disabled", false);
	$("#nmuji").prop("disabled", false);
	$("#nmujp").prop("disabled", false);
	$("#nmujs").prop("disabled", false);
	
	$("#nseci").prop( "disabled", false );
	$("#nsecp").prop( "disabled", false );
	$("#nsecs").prop( "disabled", false );
	$("#ndoci").prop( "disabled", false );
	$("#ndocp").prop( "disabled", false );
	$("#ndocs").prop( "disabled", false );
	$("#nalui").prop( "disabled", false );
	$("#nalup").prop( "disabled", false );
	$("#nalus").prop( "disabled", false );
	$("#nvari").prop( "disabled", false );
	$("#nvarp").prop( "disabled", false );
	$("#nvars").prop( "disabled", false );
	$("#nmuji").prop( "disabled", false );
	$("#nmujp").prop( "disabled", false );
	$("#nmujs").prop( "disabled", false );
	
	
	$("#proveedor").prop("disabled", false);
	$("#suministro").prop("disabled", false);
	$("#abastecimiento").prop("disabled", false);
	$("#piscina0").prop("disabled",false);
	$("#piscina1").prop("disabled",false);
	$("#tipodocdir").prop("disabled", false);
	$("#docdir").prop("disabled", false);
	$("#docdir").prop("disabled",false);
	$("#apedir").prop("disabled", false);
	$("#nomdir").prop("disabled", false);
	$("#telfdir").prop("disabled", false);
	$("#celdir").prop("disabled", false);
	$("#maildir").prop("disabled", false);
	$("#tipodocprof").prop("disabled", false);
	$("#docprof").prop("disabled", false);
	$("#docprof").prop("disabled",false);
	$("#apeprof").prop("disabled", false);
	$("#nomprof").prop("disabled", false);
	$("#telfprof").prop("disabled", false);
	$("#celprof").prop("disabled", false);
	$("#mailprof").prop("disabled", false);	
	$("#btnlimpiarformprogeduc").prop("disabled",false);
	$("#btnregistrarprogeduc").prop("disabled",false);

}

function desabilitarControles(){
	
	$("#codmod").prop("disabled", true);
	$("#nomie").prop("disabled", true);
	$("#tipoieid").prop("disabled", true);
	$("#departamentoid").prop("disabled", true);
	$("#provinciaid").prop("disabled", true);
	$("#distritoid").prop("disabled", true);
	$("#dirie").prop("disabled", true);
	$("#dre").prop("disabled", true);
	$("#ugel").prop("disabled", true);
	$("#telfie").prop("disabled", true);
	$("#mailie").prop("disabled", true);
	$("#facebook").prop("disabled", true);
	$("#lengua").prop("disabled", true);
	$("#ambradio1").prop("disabled", true);
	$("#ambradio2").prop("disabled", true);
	$("#ambradio3").prop("disabled", true);
	$("#modensradio1").prop("disabled", true);
	$("#modensradio2").prop("disabled", true);
	$("#modensradio3").prop("disabled", true);
	$("#genradio1").prop("disabled", true);
	$("#genradio2").prop("disabled", true);
	$("#genradio3").prop("disabled", true);
	$("#turradio1").prop("disabled", true);
	$("#turradio2").prop("disabled", true);
	$("#turradio3").prop("disabled", true);
	$("#lengua0").prop("disabled", true);
	$("#lengua1").prop("disabled", true);
	$("#nini").prop("disabled", true);
	$("#ninp").prop("disabled", true);
	$("#nins").prop("disabled", true);
	$("#nseci").prop("disabled", true);
	$("#nsecp").prop("disabled", true);
	$("#nsecs").prop("disabled", true);
	$("#ndoci").prop("disabled", true);
	$("#ndocp").prop("disabled", true);
	$("#ndocs").prop("disabled", true);
	$("#nalui").prop("disabled", true);
	$("#nalup").prop("disabled", true);
	$("#nalus").prop("disabled", true);
	$("#nvari").prop("disabled", true);
	$("#nvarp").prop("disabled", true);
	$("#nvars").prop("disabled", true);
	$("#nmuji").prop("disabled", true);
	$("#nmujp").prop("disabled", true);
	$("#nmujs").prop("disabled", true);
	
	$("#nseci").prop( "disabled", true );
	$("#nsecp").prop( "disabled", true );
	$("#nsecs").prop( "disabled", true );
	$("#ndoci").prop( "disabled", true );
	$("#ndocp").prop( "disabled", true );
	$("#ndocs").prop( "disabled", true );
	$("#nalui").prop( "disabled", true );
	$("#nalup").prop( "disabled", true );
	$("#nalus").prop( "disabled", true );
	$("#nvari").prop( "disabled", true );
	$("#nvarp").prop( "disabled", true );
	$("#nvars").prop( "disabled", true );
	$("#nmuji").prop( "disabled", true );
	$("#nmujp").prop( "disabled", true );
	$("#nmujs").prop( "disabled", true );
	
	
	$("#proveedor").prop("disabled", true);
	$("#suministro").prop("disabled", true);
	$("#abastecimiento").prop("disabled", true);
	$("#piscina0").prop("disabled",true);
	$("#piscina1").prop("disabled",true);
	$("#tipodocdir").prop("disabled", true);
	$("#docdir").prop("disabled", true);
	$("#docdir").prop("disabled",true);
	$("#apedir").prop("disabled", true);
	$("#nomdir").prop("disabled", true);
	$("#telfdir").prop("disabled", true);
	$("#celdir").prop("disabled", true);
	$("#maildir").prop("disabled", true);
	$("#tipodocprof").prop("disabled", true);
	$("#docprof").prop("disabled", true);
	$("#docprof").prop("disabled",true);
	$("#apeprof").prop("disabled", true);
	$("#nomprof").prop("disabled", true);
	$("#telfprof").prop("disabled", true);
	$("#celprof").prop("disabled", true);
	$("#mailprof").prop("disabled", true);	
	$("#btnlimpiarformprogeduc").prop("disabled",true);
	$("#btnregistrarprogeduc").prop("disabled",true);

}

function limpiarControles(){
	
	//$("#codmod").val("");
	$("#nomie").val("");
	$("#tipoieid").val("");
	$("#departamentoid").val("");
	$("#provinciaid").val("");
	$("#distritoid").val("");
	$("#dirie").val("");
	$("#dre").val("");
	$("#ugel").val("");
	$("#telfie").val("");
	$("#mailie").val("");
	$("#facebook").val("");
	$("#lengua").val("");
	$("#ambradio1").prop("checked", false);
	$("#ambradio2").prop("checked", false);
	$("#ambradio3").prop("checked", false);
	$("#modensradio1").prop("checked", false);
	$("#modensradio2").prop("checked", false);
	$("#modensradio3").prop("checked", false);
	$("#genradio1").prop("checked", false);
	$("#genradio2").prop("checked", false);
	$("#genradio3").prop("checked", false);
	$("#turradio1").prop("checked", false);
	$("#turradio2").prop("checked", false);
	$("#turradio3").prop("checked", false);
	$("#lengua0").prop("checked", false);
	$("#lengua1").prop("checked", false);
	$("#nini").prop("checked", false);
	$("#ninp").prop("checked", false);
	$("#nins").prop("checked", false);
	$("#nseci").val("0");
	$("#nsecp").val("0");
	$("#nsecs").val("0");
	$("#ndoci").val("0");
	$("#ndocp").val("0");
	$("#ndocs").val("0");
	$("#nalui").val("0");
	$("#nalup").val("0");
	$("#nalus").val("0");
	$("#nvari").val("0");
	$("#nvarp").val("0");
	$("#nvars").val("0");
	$("#nmuji").val("0");
	$("#nmujp").val("0");
	$("#nmujs").val("0");
	
	$("#nseci").prop( "disabled", true );
	$("#nsecp").prop( "disabled", true );
	$("#nsecs").prop( "disabled", true );
	$("#ndoci").prop( "disabled", true );
	$("#ndocp").prop( "disabled", true );
	$("#ndocs").prop( "disabled", true );
	$("#nalui").prop( "disabled", true );
	$("#nalup").prop( "disabled", true );
	$("#nalus").prop( "disabled", true );
	$("#nvari").prop( "disabled", true );
	$("#nvarp").prop( "disabled", true );
	$("#nvars").prop( "disabled", true );
	$("#nmuji").prop( "disabled", true );
	$("#nmujp").prop( "disabled", true );
	$("#nmujs").prop( "disabled", true );
	
	
	$("#proveedor").val("");
	$("#suministro").val("");
	$("#agregarsuministro").html("");
	while(arraysuministro.length > 0){
		arraysuministro.pop(); 
	}
	$("#abastecimiento").val("");
	$("#piscina").prop("checked",false);
	$("#tipodocdir").val("");
	$("#docdir").val("");
	$("#docdir").prop("disabled",true);
	$("#apedir").val("");
	$("#nomdir").val("");
	$("#generodir").val("");
	$("#telfdir").val("");
	$("#celdir").val("");
	$("#maildir").val("");
	$("#tipodocprof").val("");
	$("#docprof").val("");
	$("#docprof").prop("disabled",true);
	$("#apeprof").val("");
	$("#nomprof").val("");
	$("#generoprof").val("");
	$("#telfprof").val("");
	$("#celprof").val("");
	$("#mailprof").val("");
	
	$("#provinciaid").html('<option value="">Seleccione</option>');
	$("#distritoid").html('<option value="">Seleccione</option>');
	
	$("#lblTotalVarones").html("");
	$("#lblTotalMujeres").html("");
	
	$("#piscina1").prop("checked",false);
	$("#piscina0").prop("checked",false);
}

function verificarCheck(item){
	return $(item).is(':checked');
}

function agregar_suministro(suministro){
	if(suministro.trim()!=''){
		var indicador = arraysuministro.indexOf(suministro);
		if(indicador === -1){
			arraysuministro.push(suministro.toString());
			$("#agregarsuministro").html($("#agregarsuministro").html() + "<div class='alert alert-warning alert-dismissible fade show' role='alert'>"+suministro+"<button type='button' class='close' data-dismiss='alert' onclick='eliminarsuministro("+'"'+suministro+'"'+")' aria-label='Close'><span aria-hidden='true' >&times;</span></button></div>");
			$("#suministro").val("");
			$("#suministro").focus();
		}
	}
}


function eliminarsuministro(item){
	
	var i = arraysuministro.indexOf(item);
	if ( i != -1 ) {
		arraysuministro.splice(i,1);
	}
}

/*Validar ingreso de número decimales*/
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


function filterCadena(evt,input){
	
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



function filterCadenaNombreApellido(evt,input){
	
	var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if((key >= 65 && key <= 90) || (key >= 97 && key <= 122) || (key ==32) ||  (key==225) || (key == 233) || (key==237) || (key==243) || (key==250)  ){    
        return true;
    }
    else{
        return false;
    }	
}


/*function validarEmail(elemento,mensaje){
	  if (!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test($("#"+elemento).val()))){
		  $('#statusMsg').html("<div class='alert alert-warning alert-dismissible fade show' role='alert'>"+mensaje+"<button type='button' class='close' data-dismiss='alert' onclick='enfocarCampo("+'"'+elemento+'"'+")' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>");  
		  $("#divmsjSistemaEducativoFormAdd").css("display","block");
		  $('html, body').animate({scrollTop:0}, 'slow');
	  }
	  else{
		  $('#statusMsg').html("");  
		  $("#divmsjSistemaEducativoFormAdd").css("display","hide");
	  }
}*/

/*function validarEmail(elemento){
	  if (!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test($("#"+elemento).val()))){
		  $('#statusMsg').html("<div class='alert alert-warning alert-dismissible fade show' role='alert'>El Correo es incorrecto<button type='button' class='close' data-dismiss='alert' onclick='enfocarCampo("+'"'+elemento+'"'+")' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>");  
		  $("#divmsjSistemaEducativoFormAdd").css("display","block");
		  $('html, body').animate({scrollTop:0}, 'slow');
	  }
	  else{
		  $('#statusMsg').html("");  
		  $("#divmsjSistemaEducativoFormAdd").css("display","hide");		  
	  }
}
*/
function enfocarCampo(campo){
	$("#"+campo).focus();
}

function validarControles(){
	var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
	textoValidarControles  = "";
	bandera=true;
	
	if($("#codmod").val().trim()==""){
		$("#codmod").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Código local I.E.</label><br>";
		bandera=false;
	}
	if($("#nomie").val().trim()==""){
		$("#nomie").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe ingresar Nombre de la IE</label><br>";
		bandera=false;
	}
	//console.log("$(#tipoieid).val().trim() "+($("#tipoieid").val()).trim());
	if($("#tipoieid").val()==""){
		$("#tipoieid").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Tipo I.E</label><br>";
		bandera=false;
	}
	if($("#departamentoid").val()==""){
		$("#departamentoid").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Departamento</label><br>"; 
		bandera=false;
	}
	if($("#provinciaid").val()==""){
		$("#provinciaid").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Provincia</label><br>";
		bandera=false;
	}
	if($("#distritoid").val()==""){
		$("#distritoid").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Distrito</label><br>";
		bandera=false;
	}
	if($("#dirie").val().trim()==""){
		$("#dirie").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Dirección</label><br>";
		bandera=false;
	}
	if($("#dre").val().trim()==""){
		$("#dre").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar DRE</label><br>"; 
		bandera=false;
	}
	if($("#ugel").val().trim()==""){
		$("#ugel").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar UGEL</label><br>"; 
		bandera=false;
	}
	if($("#telfie").val().trim()==""){
		$("#telfie").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar teléfono de institución educativa</label><br>"; 
		bandera=false;
	}
	if($("#facebook").val().trim()==""){
		$("#facebook").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar facebook de institución educativa</label><br>";
		bandera=false;
	}
	if($("#mailie").val().trim()==""){
		$("#mailie").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Correo institucional</label><br>";
		bandera=false;
	}
	if(!pattern.test($("#mailie").val())){		
		$("#mailie").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Correctamente el Correo Institucional</label><br>"; 
		bandera=false;  
	}
	if($("#lengua").val().trim()==""){
		$("#lengua").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Lengua</label><br>"; 
		bandera=false;		
	}
	if($('input:radio[name=ambradio]:checked').val()==undefined){
		//$('input:radio[name=ambradio]').css("color:red");
		$("#lblambito").addClass("group_checkbox_red");
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Ámbito</label><br>";
		bandera=false;
	}
	if($('input:radio[name=genradio]:checked').val()==undefined){		
		//$("#lblgenero").css("color:red");
		$("#lblgenero").addClass("group_checkbox_red");
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Género</label><br>";
		bandera=false;
	}
	//console.log("$('input:radio[name=modensradio]:checked').val()" + $('input:radio[name=modensradio]:checked').val());
	if($('input:radio[name=modensradio]:checked').val()==undefined){
		//$('input:radio[name=modensradio]').css("color:red");
		$("#lblmodens").addClass("group_checkbox_red");
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Modalidad de Enseñanza</label><br>"; 
		bandera=false;
	}
	if(!($("#turradio1").is(':checked') || $("#turradio2").is(':checked') || $("#turradio3").is(':checked'))){
		//$("#lblturno").css("color:red");
		$("#lblturno").addClass("group_checkbox_red");
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Turno</label><br>";
		bandera=false;
	}
	
	
	if(!($("#nini").is(':checked') || $("#ninp").is(':checked') || $("#nins").is(':checked'))){
		$("#lblNivel").addClass("group_checkbox_red");
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Nivel</label><br>"; 
		bandera=false;
	}
	
	
	if($("#proveedor").val()==""){
		$("#proveedor").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Proveedor</label><br>"; 
		bandera=false;
	}
	if($("#abastecimiento").val()==""){
		$("#abastecimiento").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Horas de abastecimiento de agua</label><br>"; 
		bandera=false;
	}
	if($('input:radio[name=piscina]:checked').val()==undefined){
		$("#lblPiscina").addClass("group_checkbox_red");
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Piscina</label><br>";
		bandera=false;
	}
	if($("#tipodocdir").val()==""){
		$("#tipodocdir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Tipo Documento Director</label><br>"; 
		bandera=false;
	}
	if($("#docdir").val()==""){
		$("#docdir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Documento de identidad del Director</label><br>";		
		bandera=false;
	}
	if($("#docdir").val().length<8){
		$("#docdir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>El Documento de identidad del Director debe tener 8 digitos</label><br>"; 
		bandera=false;
	}
	if($("#apedir").val()==""){
		$("#apedir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Apellidos del Director</label><br>";
		bandera=false;
	}
	if($("#nomdir").val()==""){
		$("#nomdir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Nombres del Director</label><br>";
		bandera=false;
	}
	if($("#generodir").val()==""){
		$("#generodir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Seleccionar el género del Director</label><br>";
		bandera=false;
	}
	if($("#telfdir").val()==""){
		$("#telfdir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Teléfono del Director</label><br>"; 
		bandera=false;
	}
	if($("#celdir").val().length<9){
		$("#celdir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>El Teléfono del Director debe tener 9 digitos</label><br>"; 
		bandera=false;
	}
	if($("#celdir").val().trim()==""){
		$("#celdir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Celular del Director</label><br>";
		bandera=false;
	}
	if($("#maildir").val()==""){		
		$("#maildir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Email del Director</label><br>"; 
		bandera=false; 	  
	}
	if(!pattern.test($("#maildir").val())){		
		$("#maildir").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Correctamente el Email del Director</label><br>"; 
		bandera=false;  	  
	}
	if($("#tipodocprof").val()==""){	
		$("#tipodocprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Seleccionar Tipo Documento del Profesor</label><br>";
		bandera=false;
	}
	if($("#docprof").val()==""){
		$("#docprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Documento de identidad del Profesor</label><br>";		
		bandera=false;
	}
	if($("#docprof").val().length<8){
		$("#docprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>El Documento de identidad del Profesor debe tener 8 digitos</label><br>"; 
		bandera=false;
	}
	if($("#apeprof").val().trim()==""){
		$("#apeprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Apellidos del Profesor</label><br>";
		bandera=false;
	}
	if($("#nomprof").val().trim()==""){
		$("#nomprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Nombres del Profesor</label><br>";
		bandera=false;
	}
	if($("#generoprof").val()==""){
		$("#generoprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Seleccionar el género del Profesor</label><br>";
		bandera=false;
	}
	if($("#telfprof").val().trim()==""){
		$("#telfprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Teléfono del Profesor</label><br>"; 
		bandera=false;
	}
	if($("#celprof").val().length<9){
		$("#celprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>El Celular del Profesor debe tener 9 digitos</label><br>";
		bandera=false;
	}	
	if($("#celprof").val().trim()==""){
		$("#celprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Celular del Profesor</label><br>";
		bandera=false;
	}	
	if($("#mailprof").val()==""){		
		$("#mailprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Email del Profesor</label><br>"; 
		bandera=false;    	  
	}
	if(!pattern.test($("#mailprof").val())){		
		$("#mailprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Correctamente el Email del Profesor</label><br>"; 
		bandera=false;    	  
	}
	return bandera;
}

function filterCorreo(evt,input){
	var key = window.Event ? evt.which : evt.keyCode; 
	if(key==225 || key == 233 || key==237 || key==243 || key==250 ){
		return false;
	}
	return true;
}

function filterIntNroDocIdentidadDirector(evt,input){
	if($("#tipodocdir").val()==1){
	    var key = window.Event ? evt.which : evt.keyCode;    
	    var chark = String.fromCharCode(key);
	    var tempValue = input.value+chark;
	    if(key >= 48 && key <= 57){
	    	if($("#tipodocdir").val() == 1){
				if($("#docdir").val().trim().length<8)
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
	else if($("#tipodocdir").val()==2){
		return true;
	}
}


function filterIntNroDocIdentidadProfesor(evt,input){
	
	if($("#tipodocprof").val()==1){
	    var key = window.Event ? evt.which : evt.keyCode;    
	    var chark = String.fromCharCode(key);
	    var tempValue = input.value+chark;
	    if(key >= 48 && key <= 57){
	    	if($("#tipodocprof").val() == 1){
				if($("#docprof").val().trim().length<8)
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
	else if($("#tipodocprof").val()==2){
		return true;
	}
}

function totalNivel(){
	
	var nvari = $("#nvari").val()==""?0:$("#nvari").val();
	var nmuji = $("#nmuji").val()==""?0:$("#nmuji").val();
	var nvarp = $("#nvarp").val()==""?0:$("#nvarp").val();
	var nmujp = $("#nmujp").val()==""?0:$("#nmujp").val();
	var nvars = $("#nvars").val()==""?0:$("#nvars").val();
	var nmujs = $("#nmujs").val()==""?0:$("#nmujs").val();
	
	var sumvar = parseInt(nvari) +  parseInt(nvarp) + parseInt(nvars);
	var summuj = parseInt(nmuji) + parseInt(nmujp) + parseInt(nmujs);
	
	$("#nalui").val(parseInt(nvari)+parseInt(nmuji));
	$("#nalup").val(parseInt(nvarp)+parseInt(nmujp));
	$("#nalus").val(parseInt(nvars)+parseInt(nmujs));
	
	$("#lblTotalVarones").html("Total Varones "+sumvar);
	$("#lblTotalMujeres").html("Total Mujeres "+summuj);	
}