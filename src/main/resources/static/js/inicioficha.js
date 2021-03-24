var data_ubigeo = "";
var arraysuministro = new Array();
var url_base = location.origin+"/";
var programaeducativoid;
var codigo_seleccionado = "";
var textoValidarControles = "";
var bandera=true;

$(document).ready(function(){
	
	$("#btncancelarprogeduc").click(function(){		
		$("#modalcargando").modal('hide');
	});
	
	$("#btncerrarmensajeexito").click(function(){
		$("#modalexito").modal('hide');
	});
	
	$("#btncerrarmensajeinformacion").click(function(){
		$("#modalinformacion").modal('hide');
	});
	
	$("#btncerrarmensajeerror").click(function(){
		$("#modalerror").modal('hide');
	});
	
	$("#btn_agregarsuministro").click(function(){
		var suministro = $("#suministro").val();
		agregar_suministro(suministro);
	});
	
	$("#btncancelarprogeduc").click(function(){		
		$("#modalcargando").modal('hide');
	});
	
	$("#departamentoid").on("change",function(){
		$("#provinciaid").html("");
		$("#distritoid").html("");
		$.ajax({
			url: url_base + "programaeducativo/provincias/bydepa/"+$("#departamentoid").val(),
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
			url: url_base + "programaeducativo/distritos/byprovincia/"+$("#provinciaid").val(),
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
	
	$("#btnactualizarprogeduc").click(function(){		
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
	
	$("#btnmotivoobservacion").click(function(){
		
		$("#divmsj").css("visibility", "visible");
		
	});	
	
	$("#btnconfirmarprogeduc").click(function(){
	
		$('#agregarsuministro label').each(function(idx, el) {		    
		     arraysuministro.push($(el).html());
		});
		
		
		$("#modalcargando").modal('hide');
		$("#modalimagencargando").modal({
			show : true,
			backdrop : 'static',
			keyboard:false
		});
		
		
		
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
		var ambitoid = $("input[name='ambito.id']:checked").val();
		var modensenianzaid = $("input[name='modensenianza.id']:checked").val();
		var generoid = $("input[name='genero.id']:checked").val();
		var proveedorid = $("#proveedor").val();		
		var abastecimiento = $("#abastecimiento").val();
		var piscinaid =  $("input[name='piscina.id']:checked").val();
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
		
		
		
		var listSuministro = new Array();
		for(var i=0;i<arraysuministro.length;i++){
			listSuministro.push({
				numero : arraysuministro[i]
			});
		}
		
		var progeduc = {
			id : $("#hiddenid").val(),
			estado : 'Pendiente',
			anhio : $("#hiddenanio").val(),
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
			generodir:{
				id : generodir
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
			generoprof:{
				id : generoprof
			},
			telfprof : telfprof,
			celprof : celprof,
			mailprof : mailprof	,
			suministro: listSuministro,
			dep : $("#departamentoid").val(),
			prov : $("#provinciaid").val(),
			concurso: 0
		};
		
		var listNivel = new Array();
		
		if($("#nini").is(':checked')) {
			
			if($("#hiddenidi").val()>0){
				var datai = {
					id: $("#hiddenidi").val(),
					nrosecciones: $("#nseci").val(),
					nrodocentes: $("#ndoci").val(),
					nroalumnos: $("#nalui").val(),
					nrovarones: $("#nvari").val(),
					nromujeres: $("#nmuji").val(),
					tiponivel: {
						id :1
					}
				}
			}
			else{
				var datai = {
					nrosecciones: $("#nseci").val(),
					nrodocentes: $("#ndoci").val(),
					nroalumnos: $("#nalui").val(),
					nrovarones: $("#nvari").val(),
					nromujeres: $("#nmuji").val(),
					tiponivel: {
						id :1
					}
				}
			}
			listNivel.push(datai);
	    }
		
		if($("#ninp").is(':checked')) {  
		
			if($("#hiddenidp").val()>0){
				var datap = {
					id: $("#hiddenidp").val(),
					nrosecciones: $("#nsecp").val(),
					nrodocentes: $("#ndocp").val(),
					nroalumnos: $("#nalup").val(),
					nrovarones: $("#nvarp").val(),
					nromujeres: $("#nmujp").val(),
					tiponivel: {
						id: 2
					}
				};
			}
			else{
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
			}
			listNivel.push(datap);
	    }
		
		if($("#nins").is(':checked')) {  
			
			if($("#hiddenids").val()>0){
				var datas = {
					id : $("#hiddenids").val(),
					nrosecciones: $("#nsecs").val(),
					nrodocentes: $("#ndocs").val(),
					nroalumnos: $("#nalus").val(),
					nrovarones: $("#nvars").val(),
					nromujeres: $("#nmujs").val(),
					tiponivel: {
						id: 3
					}
				};
			}
			else{
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
			}
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
		
		$.ajax({
			type : "POST",
		    contentType : "application/json",
		    url : url_base + "programaeducativo/updateturnonivel",
		    data : JSON.stringify(obj),
		    dataType : 'json',
			success: function(respuesta) {
				if(respuesta){
					$("#modalimagencargando").modal('hide');
					$("#textoexito").html("Se Actualizaron los datos Exitosamente");
					$('#modalexito').modal({
						show : true,
						backdrop : 'static',
						keyboard:false
					});
				}
				else{
					$("#modalimagencargando").modal('hide');
					$("#textoerror").html("Error al actualizar datos");
					$('#modalerror').modal({
						show : true,
						backdrop : 'static',
						keyboard:false
					});
				}
			},
			error: function() {
				$("#modalimagencargando").modal('hide');
				alert("Exception al actualizar");
		    }
		});	
	});
	
	
});


function validarControles(){
	
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
	if(!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test($("#mailie").val()))){		
		$("#mailie").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Correctamente el Correo Institucional</label><br>"; 
		bandera=false;  
	}
	if($("#lengua").val().trim()==""){
		$("#lengua").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Lengua</label><br>"; 
		bandera=false;		
	}	
	
	
	
	if(! $("input[name='ambito.id']").is(':checked')){
		$("#lblambito").addClass("group_checkbox_red");
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Ámbito</label><br>";
		bandera=false;
	}
	if(! $("input[name='genero.id']").is(':checked')){		
		//$("#lblgenero").css("color:red");
		$("#lblgenero").addClass("group_checkbox_red");
		textoValidarControles  += "<label style='color:red'>Debe seleccionar Género</label><br>";
		bandera=false;
	}
	if(! $("input[name='modensenianza.id']").is(':checked')){
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
	if(! $("input[name='piscina.id']").is(':checked')){
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
	if(!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test($("#maildir").val()))){		
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
	if(!(/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test($("#mailprof").val()))){		
		$("#mailprof").css({"color":"red","border":"1px solid red"});
		textoValidarControles  += "<label style='color:red'>Debe Ingresar Correctamente el Email del Profesor</label><br>"; 
		bandera=false;    	  
	}
	return bandera;
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

