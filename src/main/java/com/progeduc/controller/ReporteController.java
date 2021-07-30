package com.progeduc.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progeduc.dto.DocenteDto;
import com.progeduc.dto.ListaparticipantereporteDto;
import com.progeduc.model.Auspicio;
import com.progeduc.model.Participante;
import com.progeduc.model.UsuarioAlianza;
import com.progeduc.service.IDistritoService;
import com.progeduc.service.IDocentetutorService;
import com.progeduc.service.IOdsService;
import com.progeduc.service.IParticipanteService;
import com.progeduc.service.IUsuarioAlianzaService;

@RestController
@RequestMapping("")
public class ReporteController {

	@Autowired
	private IParticipanteService participanteServ;
	
	@Autowired
	private IOdsService odsserv;
	
	@Autowired
	private IDistritoService distServ;
	
	@Autowired
	private IDocentetutorService docentetutorServ;
	
	@Autowired
	private IUsuarioAlianzaService usuarioAlianzaServ;
	
	@GetMapping(value="/reporteparticipantesinscritos/{ods}/{anio}/{categoria}/{modalidad}/{nivel}")	
	public ResponseEntity<InputStreamResource> exportParticipantes(@PathVariable(value="ods") Integer ods,
			@PathVariable(value="anio") Integer anio,
			@PathVariable(name="categoria") Integer categoria,
			@PathVariable(name="modalidad") Integer modalidad,
			@PathVariable(name="nivel") Integer nivel) {
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		ByteArrayInputStream stream = reporteparticipantesinscritos(ods,anio,categoria,modalidad,nivel);
		
		HttpHeaders headers = new HttpHeaders();
		
		String fecha_archivo = dateFormat.format(date) + hourFormat.format(date);
		
		headers.add("Content-Disposition", "attachment; filename=Reportedocente_participantes_"+fecha_archivo+".xls");
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		
	}
	
	public ByteArrayInputStream reporteparticipantesinscritos(Integer ods,Integer anio,Integer categoria,Integer modalidad,Integer nivel)   {
		
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
				
		List<DocenteDto> listadocentedto = new ArrayList<DocenteDto>();
		
		docentetutorServ.listar().forEach(obj->{
			boolean banderaods = false;
			
			if(ods!=-1) {
				if(obj.getProgramaeducativo().getDistrito().getOdsid().equals(ods))
					banderaods  = true;
				else
					banderaods = false;					
			}
			else {
				banderaods = true;
			}
			
			if(banderaods){				
				DocenteDto dto = new DocenteDto();
				dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
				dto.setCodiie(obj.getProgramaeducativo().getCodmod());
				dto.setNomie(obj.getProgramaeducativo().getNomie());
				dto.setNombre(obj.getNombre());
				dto.setAppaterno(obj.getAppaterno());
				dto.setApmaterno(obj.getApmaterno());
				dto.setTipodocumento(obj.getTipodocumento().getDescripcion());
				dto.setNrodocumento(obj.getNrodocumento());
				dto.setGenero(obj.getGenero().getDescripcion());
				dto.setTelefono(obj.getNrotelefono());
				dto.setEmail(obj.getCorreoelectronico());
				dto.setCurso(obj.getCurso());
				dto.setTipodocente(obj.getResponsable().getDescripcion());
				listadocentedto.add(dto);
			}				
		});
		
		String [] columns = {"ODS","Código de IE","Nombre de IE","Nombres","Apellido paterno","Apellido materno","Tipo de documento","Nro de documento", "Género","Télefono","Email","Curso","Tipo de docente"};
		
		Sheet sheet = workbook.createSheet("Datos de docentes");
		Row row = sheet.createRow(0);
		for(int i=0;i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}
		
		int initRow = 1;
		for(DocenteDto dto : listadocentedto) {
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(dto.getOds());
			row.createCell(1).setCellValue(dto.getCodiie());
			row.createCell(2).setCellValue(dto.getNomie());
			row.createCell(3).setCellValue(dto.getNombre());
			row.createCell(4).setCellValue(dto.getAppaterno());
			row.createCell(5).setCellValue(dto.getApmaterno());
			row.createCell(6).setCellValue(dto.getTipodocumento());
			row.createCell(7).setCellValue(dto.getNrodocumento());
			row.createCell(8).setCellValue(dto.getGenero());
			row.createCell(9).setCellValue(dto.getTelefono());
			row.createCell(10).setCellValue(dto.getEmail());
			row.createCell(11).setCellValue(dto.getCurso());
			row.createCell(12).setCellValue(dto.getTipodocente());
			initRow++;
		}		
		
		List<Participante> listaParticipante = participanteServ.listarhabilitados();
		List<ListaparticipantereporteDto> listareporte = new ArrayList<ListaparticipantereporteDto>();
		
		listaParticipante.forEach(obj->{
			ListaparticipantereporteDto reporte  = new ListaparticipantereporteDto();
			
			boolean bandera_ods = false;
			boolean bandera_anio = false;
			boolean bandera_categoria = false;
			boolean bandera_modalidad = false;
			boolean bandera_nivel = false;
			
			if(ods!=-1) {
				if(obj.getProgramaeducativo().getDistrito().getOdsid().equals(ods))
					bandera_ods = true;
				else
					bandera_ods = false;					
			}
			else {
				bandera_ods = true;
			}
			/**/
			if(anio!=-1) {
				if(obj.getAnhio().equals(anio))
					bandera_anio = true;
				else
					bandera_anio = false;					
			}
			else {
				bandera_anio = true;
			}
			/**/
			if(categoria!=-1) {
				switch(categoria) {
					case 1: bandera_categoria = (obj.getCategoriacuento()==1 ? true : false ); break;
					case 2: bandera_categoria = (obj.getCategoriapoesia()==1 ? true : false ); break;
					case 3: bandera_categoria = (obj.getCategoriadibujopintura()==1 ? true : false ); break;
					case 4: bandera_categoria = (obj.getCategoriacomposicionmusical()==1 ? true : false ); break;
					case 5: bandera_categoria = (obj.getCategoriaahorroagua()==1 ? true : false ); break;
				}
			}
			else {
				bandera_categoria = true;
			}
			/**/
			if(modalidad!=-1) {
				switch(modalidad) {
					case 1: bandera_modalidad = (obj.getModalidadpostulacionindividual()==1 ? true : false ); break;
					case 2: bandera_modalidad = (obj.getModalidadpostulaciongrupal()==1 ? true : false ); break;
				}
			}
			else {
				bandera_modalidad = true;
			}
			
			/**/
			if(nivel!=-1) {
				System.out.println("nivel de la bd :" + obj.getGradoestudiante().getNivelgradopartid());
				if(obj.getGradoestudiante().getNivelgradopartid().equals(nivel))
					bandera_nivel = true;
				else
					bandera_nivel = false;
			}
			else {
				bandera_nivel = true;
			}			
			/**/
			if( bandera_ods && bandera_anio && bandera_categoria && bandera_modalidad && bandera_nivel) 			
			{
				
				reporte.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion() );
				reporte.setCodigoie(obj.getProgramaeducativo().getCodmod());
				reporte.setNomie(obj.getProgramaeducativo().getNomie());
				reporte.setNombreest(obj.getNombreestudiante());
				reporte.setAppaternoest(obj.getAppaternoestudiante());
				reporte.setApmaternoest(obj.getApmaternoestudiante());
				reporte.setFechanacimientoest(obj.getFechanacimientoestudiante().toString());
				reporte.setSeccionest(obj.getSeccion());
				reporte.setTipodocest(obj.getTipodocumentoestudiante().getDescripcion());
				reporte.setNrodocest(obj.getNrodocumentoestudiante());
				reporte.setGeneroest(obj.getGeneroestudiante().getDescripcion());
				reporte.setNivelest(obj.getGradoestudiante().getNivelparticipante().getDescripcion());
				reporte.setGradoest(obj.getGradoestudiante().getDescripcion());
				reporte.setNivelparticipacion(obj.getGradoestudiante().getNivelgradopartdesc());
				reporte.setModalidadest((obj.getModalidadpostulacionindividual()==1?"Individual/":"") + (obj.getModalidadpostulaciongrupal()==1?"grupal":""));
				reporte.setCategoriaest((obj.getCategoriacuento()==1?"Cuento/":"") +  (obj.getCategoriapoesia()==1?"Poesía/":"") +(obj.getCategoriadibujopintura()==1?"Dibujo o Pintura/":"") +(obj.getCategoriacomposicionmusical()==1?"Composición musical/":"") +(obj.getCategoriaahorroagua()==1?"Ahorro del agua en tu hogar":""));
				reporte.setNombreapoderado(obj.getNombrepmt());
				reporte.setAppaternoapoderado(obj.getAppaternopmt());
				reporte.setApmaternoapoderado(obj.getApmaternopmt());
				reporte.setNrotelfapoderado(obj.getNrotelefonopmt());
				reporte.setCorreoapoderado(obj.getCorreoelectronicopmt());
				reporte.setTipodocapoderado(obj.getTipodocumentopmt().getDescripcion());
				reporte.setNrodocapoderado(obj.getNrodocumentopmt());
				reporte.setParentescoapoderado(obj.getParentesco().getDescripcion());
				listareporte.add(reporte);	
			}					
		});
        
		Collections.sort(listareporte);
		
		String [] columns1 = {"ODS","Código de IE","Nombre de IE","Nombres","Apellido paterno","Apellido materno","Fecha de nacimiento","Sección", "Tipo de documento","Nro de documento","Genero","Nivel","Grado",    "Nivel de participación", "Modalidad",    "Categorías","Nombres tutor","Apellido paterno tutor","Apellido materno tutor","telefono","Correo electronico", "Tipo de documento tutor","Nro de documento tutor","Parentesco"};
		Sheet sheet1 = workbook.createSheet("Datos de participantes");
		Row row1 = sheet1.createRow(0);
		for(int i=0;i<columns1.length;i++) {
			Cell cell = row1.createCell(i);
			cell.setCellValue(columns1[i]);
		}
		
		int initRow1 = 1;
		for(ListaparticipantereporteDto participante : listareporte) {
			row1 = sheet1.createRow(initRow1);
			row1.createCell(0).setCellValue(participante.getOds());
			row1.createCell(1).setCellValue(participante.getCodigoie());
			row1.createCell(2).setCellValue(participante.getNomie());
			row1.createCell(3).setCellValue(participante.getNombreest());
			row1.createCell(4).setCellValue(participante.getAppaternoest());
			row1.createCell(5).setCellValue(participante.getApmaternoest());
			row1.createCell(6).setCellValue(participante.getFechanacimientoest());
			row1.createCell(7).setCellValue(participante.getSeccionest());
			row1.createCell(8).setCellValue(participante.getTipodocest());
			row1.createCell(9).setCellValue(participante.getNrodocest());
			row1.createCell(10).setCellValue(participante.getGeneroest());
			row1.createCell(11).setCellValue(participante.getNivelest());
			row1.createCell(12).setCellValue(participante.getGradoest());
			row1.createCell(13).setCellValue(participante.getNivelparticipacion());
			row1.createCell(14).setCellValue(participante.getModalidadest());
			row1.createCell(15).setCellValue(participante.getCategoriaest());
			row1.createCell(16).setCellValue(participante.getNombreapoderado());
			row1.createCell(17).setCellValue(participante.getAppaternoapoderado());
			row1.createCell(18).setCellValue(participante.getApmaternoapoderado());
			row1.createCell(19).setCellValue(participante.getNrotelfapoderado());
			row1.createCell(20).setCellValue(participante.getCorreoapoderado());
			row1.createCell(21).setCellValue(participante.getTipodocapoderado());
			row1.createCell(22).setCellValue(participante.getNrodocapoderado());
			row1.createCell(23).setCellValue(participante.getParentescoapoderado());	
			initRow1 ++;
		}
		
		try {
			workbook.write(stream);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new ByteArrayInputStream(stream.toByteArray());
	}
	
	@GetMapping(value="/reporteAlianza/{ods}/{anio}/{rol}/{estado}")	
	public ResponseEntity<InputStreamResource> exportParticipantes(@PathVariable(value="ods") String ods,
			@PathVariable(value="anio") String anio,
			@PathVariable(value="rol") String rol,
			@PathVariable(value="estado") String estado, HttpSession ses) {
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());	
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		DateFormat dateFormatFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		String role6, role7, role8, role9;
		
		if(ods.equals("-1")) {
			ods = "";
		}
		if(anio.equals("-1")) {
			anio = "";
		}
		if(estado.equals("-1")) {
			estado = "";
		}
		if(rol.equals("-1")) {
			role6= "";
			role7= "";
			role8= "";
			role9= "";
		}else {
			if(rol.equals("6")) role6 = "1";
			else role6 = "";
				
			if(rol.equals("7")) role7 = "1";
			else role7 = "";
			
			if(rol.equals("8")) role8 = "1";
			else role8 = "";
			
			if(rol.equals("9")) role9 = "1";
			else role9 = "";
		}
		
		if(tipousuarioid == 0){
			String obsSesion = ses.getAttribute("odsid").toString();
			ods = obsSesion;
		}
		
		System.out.println(ods+anio+estado);
		List<UsuarioAlianza> listaUsu = usuarioAlianzaServ.listaUsuarioFiltro(ods, anio, estado, role6, role7, role8, role9);
		List<Auspicio> listaAuspicio = new ArrayList<>();
		
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
		
		
		String [] columns = {"Nº","AÑO","ODS","CATEGORIA","ENTIDAD","DIRECCION","COMITE TECNICO","COMITE EVALUADOR","AUSPICIADOR","ALIADO","ESTADO",
				"¿Cúantos contactos desea agregar?","Apellido paterno","Apellido materno","Nombres","Tipo de documento","Nro de documento","N° de telefono 1",
				"N° de telefono 2","Correo institucional","Cargo","Apellido paterno","Apellido materno","Nombres","Correo institucional","Cargo","Usuario",
				"Contraseña","Enviar oficio","Nro oficio","Fecha de oficio","Monto total"};
		
		Sheet sheet = workbook.createSheet("Alianza Estrategica");
		Row row = sheet.createRow(0);
		for(int i=0;i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}
		
		int initRow = 1;
		for (UsuarioAlianza obj : listaUsu) {
			int i = 0;
			
			row = sheet.createRow(initRow);
			row.createCell(i++).setCellValue(initRow);
			row.createCell(i++).setCellValue(obj.getAnio());
			row.createCell(i++).setCellValue(obj.getOds().getDescripcion());
			row.createCell(i++).setCellValue(obj.getCategoria().getDescripcion());
			row.createCell(i++).setCellValue(obj.getEntidad());
			row.createCell(i++).setCellValue(obj.getDireccion());
			row.createCell(i++).setCellValue(obj.getComitetecnico().equals("1") ? "SI" : "NO");
			row.createCell(i++).setCellValue(obj.getComiteevaluador().equals("1") ? "SI" : "NO");
			row.createCell(i++).setCellValue(obj.getAuspiciador().equals("1") ? "SI" : "NO");
			row.createCell(i++).setCellValue(obj.getAliado().equals("1") ? "SI" : "NO");
			row.createCell(i++).setCellValue(obj.getEstado().equals("1") ? "ACTIVO" : "INACTIVO");
			
			row.createCell(i++).setCellValue(obj.getNumcontaccto());
			row.createCell(i++).setCellValue(obj.getApepatcontacto());
			row.createCell(i++).setCellValue(obj.getApematcontacto());
			row.createCell(i++).setCellValue(obj.getNombrecontacto());
			row.createCell(i++).setCellValue(obj.getTipodocumento().getDescripcion());
			row.createCell(i++).setCellValue(obj.getNumdocumento());
			row.createCell(i++).setCellValue(obj.getTelefonouno());
			
			row.createCell(i++).setCellValue(obj.getTelefonodos());
			row.createCell(i++).setCellValue(obj.getCorreocontato());
			row.createCell(i++).setCellValue(obj.getCargocontacto());
			row.createCell(i++).setCellValue(obj.getApepatautoridad());
			row.createCell(i++).setCellValue(obj.getApematautoridad());
			row.createCell(i++).setCellValue(obj.getNombresautoridad());
			row.createCell(i++).setCellValue(obj.getCorreoautoridad());
			row.createCell(i++).setCellValue(obj.getCargoautoridad());
			row.createCell(i++).setCellValue(obj.getUsuarioautoridad());
			
			row.createCell(i++).setCellValue(obj.getPasswordautoridad());
			if(obj.getEnviaroficio() != null) {
				row.createCell(i++).setCellValue(obj.getEnviaroficio().equals("1") ? "SI" : "NO");
			}else {
				row.createCell(i++).setCellValue("");
			}
			
			row.createCell(i++).setCellValue(obj.getNumoficio());
			if(obj.getFecha_oficio() != null) {
				row.createCell(i++).setCellValue(dateFormatFecha.format(obj.getFecha_oficio()));
			}else {
				row.createCell(i++).setCellValue("");
			}
			
			List<Auspicio> listAus = obj.getAuspicios();
			float montoTotal = 0;
			for (Auspicio asu : listAus) {
				montoTotal += asu.getMontototal();
				asu.setOds(obj.getOds().getDescripcion());
				listaAuspicio.add(asu);
			}
			
			row.createCell(i++).setCellValue(montoTotal);
			
			initRow++;
		}
		
		
		String [] columns1 = {"ODS","N°","Tipo","Cantidad","Descripcion","Monto unitario","Monto total"};
		Sheet sheet1 = workbook.createSheet("Auspicios");
		Row row1 = sheet1.createRow(0);
		for(int i=0;i<columns1.length;i++) {
			Cell cell = row1.createCell(i);
			cell.setCellValue(columns1[i]);
		}
		
		int initRow1 = 1;
		for(Auspicio auspicio : listaAuspicio) {
			int i = 0;
			
			row1 = sheet1.createRow(initRow1);
			row1.createCell(i++).setCellValue(auspicio.getOds());
			row1.createCell(i++).setCellValue(initRow1);
			row1.createCell(i++).setCellValue(auspicio.getTipodocumento().getDescripcion());
			row1.createCell(i++).setCellValue(auspicio.getCantidad());
			row1.createCell(i++).setCellValue(auspicio.getDescripcion());
			row1.createCell(i++).setCellValue(auspicio.getMontounitario());
			row1.createCell(i++).setCellValue(auspicio.getMontototal());
			initRow1 ++;
		}
		
		try {
			workbook.write(streamOut);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ByteArrayInputStream stream = new ByteArrayInputStream(streamOut.toByteArray());
		
		
		HttpHeaders headers = new HttpHeaders();
		
		String fecha_archivo = dateFormat.format(date) + hourFormat.format(date);
		
		headers.add("Content-Disposition", "attachment; filename=Lista_alianzaestrategica_"+fecha_archivo+".xls");
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		
	}
	
}
