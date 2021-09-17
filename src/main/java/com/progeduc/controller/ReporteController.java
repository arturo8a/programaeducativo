package com.progeduc.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progeduc.dto.DetalleEvaluacionReporteDto;
import com.progeduc.dto.DocenteDto;
import com.progeduc.dto.IieeReporteDto;
import com.progeduc.dto.ListaparticipantereporteDto;
import com.progeduc.dto.NotasEvaluadorDto;
import com.progeduc.dto.ResultadosGanadoresDto;
import com.progeduc.dto.ResultadosRegionalesDto;
import com.progeduc.model.Auspicio;
import com.progeduc.model.Ods;
import com.progeduc.model.Participante;
import com.progeduc.model.Postulacionconcurso;
import com.progeduc.model.ProgramaeducativoNivel;
import com.progeduc.model.ProgramaeducativoTurno;
import com.progeduc.model.Trabajosfinales;
import com.progeduc.model.TrabajosfinalesParticipante;
import com.progeduc.model.TrabajosfinalesUsuarioAlianza;
import com.progeduc.model.UsuarioAlianza;
import com.progeduc.service.IDistritoService;
import com.progeduc.service.IDocentetutorService;
import com.progeduc.service.IEvaluacionRespuestaService;
import com.progeduc.service.IGeneroprofService;
import com.progeduc.service.IOdsService;
import com.progeduc.service.IParticipanteService;
import com.progeduc.service.IPostulacionconcursoService;
import com.progeduc.service.IProgeducNivelService;
import com.progeduc.service.IProgeducTurnoService;
import com.progeduc.service.IProgramaeducativoService;
import com.progeduc.service.IQuestionarioRespuestaService;
import com.progeduc.service.IRubricaService;
import com.progeduc.service.ITrabajosfinalesParticipanteService;
import com.progeduc.service.ITrabajosfinalesService;
import com.progeduc.service.ITrabajosfinales_UsuarioAlianzaService;
import com.progeduc.service.IUsuarioAlianzaService;
import com.progeduc.service.IUsuarioOdsService;
import com.progeduc.service.IUsuarioService;

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
	
	@Autowired
	private IPostulacionconcursoService potulacionConcursoServ;
	
	@Autowired
	private ITrabajosfinalesService trabajosFinalesServ;
	
	@Autowired
	private IProgramaeducativoService programaEducativoServ;
	
	@Autowired
	private ITrabajosfinalesParticipanteService trabajosFinalesParticipanteService;
	
	@Autowired
	private ITrabajosfinales_UsuarioAlianzaService trabajosFinales_UsuarioAlianzaServ;
	
	@Autowired
	private IProgeducNivelService peNivelServ;
	
	@Autowired
	private IProgeducTurnoService peTurnoServ;
	
	@Autowired
	private IEvaluacionRespuestaService evaluacionRepuestaServ;
	
	@Autowired
	private IQuestionarioRespuestaService questionarioRespuestaService;
	
	@Autowired
	private IRubricaService rubricaServ;
	
	@Autowired
	private IGeneroprofService generoprofserv;
	
	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	IUsuarioOdsService usuarioodsService; 
	
	List<Ods> listaOds;
	
	String mi_nivel_participacion, nivelSeccionDocenteAlumnoVaronMujer,peTurno, peSuministro,peParticipantes,peEjesTematicos,peNivelParticipacion,peGeneroParticipante,peModalidad,peCategorias,fecha_archivo;
	boolean bandera_ods,bandera_anio,bandera_categoria,bandera_modalidad,bandera_nivel,flag;
	DecimalFormat dosDecimales = new DecimalFormat("##.00");
	Float pregunta1,pregunta2,pregunta3,pregunta4,pregunta5;
	int contador1,contador2,contador3,contador4,contador5,contador,indice;
	List<Float> puntaje;
	Integer nroEvaluadoresAsignados;
	boolean bandera, banderaods, banderaOdsReporte;
	String participantes,generoParticipante;
	
	@GetMapping(value="/reporteparticipantesinscritos/{ods}/{anio}/{categoria}/{modalidad}/{nivel}")	
	public ResponseEntity<InputStreamResource> exportParticipantes(@PathVariable(value="ods") Integer ods,
			@PathVariable(value="anio") Integer anio,
			@PathVariable(name="categoria") Integer categoria,
			@PathVariable(name="modalidad") Integer modalidad,
			@PathVariable(name="nivel") Integer nivel,HttpSession ses) {
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		ByteArrayInputStream stream = reporteparticipantesinscritos(ods,anio,categoria,modalidad,nivel,ses);
		
		HttpHeaders headers = new HttpHeaders();
		
		fecha_archivo = dateFormat.format(date) + hourFormat.format(date);
		
		headers.add("Content-Disposition", "attachment; filename=Reportedocente_participantes_"+fecha_archivo+".csv");
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));		
	}
	
	@GetMapping(value="/reportenotasevaluador/{ods}/{anio}/{categoria}/{nivel}")	
	public ResponseEntity<InputStreamResource> reportenotasevaluador(@PathVariable(value="ods") Integer ods,
			@PathVariable(value="anio") Integer anio,
			@PathVariable(name="categoria") Integer categoria,
			@PathVariable(name="nivel") Integer nivel,HttpSession ses) {
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		ByteArrayInputStream stream = reporteexcelnotasevaluador(ods,anio,categoria,nivel,ses);
		
		HttpHeaders headers = new HttpHeaders();
		
		fecha_archivo = dateFormat.format(date) + hourFormat.format(date);
		
		headers.add("Content-Disposition", "attachment; filename=ReporteNotasEvaluador_"+fecha_archivo+".csv");
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}
	
	public ByteArrayInputStream reporteexcelnotasevaluador(Integer ods,Integer anio,Integer categoria,Integer nivel,HttpSession ses)   {
		
		listaOds = new ArrayList<>();
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		if(tipousuarioid.equals(2)) {
			String usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
		}
		else {
			listaOds = odsserv.listarAll();
		}
		
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
				
		List<NotasEvaluadorDto> lista = new ArrayList<NotasEvaluadorDto>();
		
		trabajosFinalesServ.listarhabilitados().forEach(tf->{	
			List<TrabajosfinalesUsuarioAlianza> listatfua = trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId());
			if(listatfua.size()==0) {
				banderaods = false;	
				bandera_anio = false;
				bandera_categoria = false;
				
				if(anio!=-1) {
					if(tf.getAnio().equals(anio))
						bandera_anio = true;
				}
				else {
					bandera_anio = true;
				}
				
				if(ods!=-1) {
					if(tf.getProgramaeducativo().getDistrito().getOdsid().equals(ods))
						banderaods  = true;
				}
				else {
					listaOds.forEach(objOds->{
						if(objOds.getId().equals(tf.getProgramaeducativo().getDistrito().getOdsid())) {
							banderaods = true;
						}
					});
				}	
				
				if(categoria!=-1) {
					if(tf.getCategoriatrabajo().getId().equals(categoria))
						bandera_categoria  = true;
				}
				else {
					bandera_categoria = true;
				}
				
				mi_nivel_participacion = "";
				if(nivel!=-1) {
					trabajosFinalesParticipanteService.listar(tf.getId()).forEach(tfp->{
						if(tfp.getParticipante().getGradoestudiante().getNivelgradopartid().equals(nivel)) {
							mi_nivel_participacion = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
							bandera_nivel = true;
						}
					});
				}
				else {
					bandera_nivel = true;
				}
				
				
				if(bandera_anio && banderaods && bandera_categoria && bandera_nivel && tf.getEnviado()==1){				
					NotasEvaluadorDto dto = new NotasEvaluadorDto();
					dto.setAnio(tf.getAnio());
					dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
					dto.setCodigoIe(tf.getProgramaeducativo().getCodmod());
					dto.setNombreIe(tf.getProgramaeducativo().getNomie());
					dto.setCodigoTrabajo(tf.getProgramaeducativo().getCodmod()+"_"+tf.getNumeracion());
					dto.setEstadoTrabajo(tf.getEstadotrabajo().getDescripcion());
					dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
					
					if(mi_nivel_participacion.equals("")) {
						trabajosFinalesParticipanteService.listar(tf.getId()).forEach(tfp->{
							mi_nivel_participacion = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
						});
					}
					
					dto.setNivelParticipacion(mi_nivel_participacion);
					dto.setEvaluador("-");
					dto.setCalificacion("");
					lista.add(dto);
				}				
			}
			else {
				listatfua.forEach(obj->{
					banderaods = false;	
					bandera_anio = false;
					bandera_categoria = false;
					
					if(anio!=-1) {
						if(obj.getTrabajosfinales().getAnio().equals(anio))
							bandera_anio = true;
					}
					else {
						bandera_anio = true;
					}
					
					if(ods!=-1) {
						if(obj.getTrabajosfinales().getProgramaeducativo().getDistrito().getOdsid().equals(ods))
							banderaods  = true;
					}
					else {
						listaOds.forEach(objOds->{
							if(objOds.getId().equals(tf.getProgramaeducativo().getDistrito().getOdsid())) {
								banderaods = true;
							}
						});
					}	
					
					if(categoria!=-1) {
						if(obj.getTrabajosfinales().getCategoriatrabajo().getId().equals(categoria))
							bandera_categoria  = true;
					}
					else {
						bandera_categoria = true;
					}
					
					mi_nivel_participacion = "";
					if(nivel!=-1) {
						trabajosFinalesParticipanteService.listar(obj.getTrabajosfinales().getId()).forEach(tfp->{
							if(tfp.getParticipante().getGradoestudiante().getNivelgradopartid().equals(nivel)) {
								mi_nivel_participacion = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
								bandera_nivel = true;
							}
						});
					}
					else {
						bandera_nivel = true;
					}
					
					
					if(bandera_anio && banderaods && bandera_categoria && bandera_nivel && obj.getTrabajosfinales().getEnviado()==1){				
						NotasEvaluadorDto dto = new NotasEvaluadorDto();
						dto.setAnio(obj.getTrabajosfinales().getAnio());
						dto.setOds(odsserv.byOds(obj.getTrabajosfinales().getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
						dto.setCodigoIe(obj.getTrabajosfinales().getProgramaeducativo().getCodmod());
						dto.setNombreIe(obj.getTrabajosfinales().getProgramaeducativo().getNomie());
						dto.setCodigoTrabajo(obj.getTrabajosfinales().getProgramaeducativo().getCodmod()+"_"+obj.getTrabajosfinales().getNumeracion());
						dto.setEstadoTrabajo(obj.getTrabajosfinales().getEstadotrabajo().getDescripcion());
						dto.setCategoria(obj.getTrabajosfinales().getCategoriatrabajo().getDescripcion());
						
						if(mi_nivel_participacion.equals("")) {
							trabajosFinalesParticipanteService.listar(obj.getTrabajosfinales().getId()).forEach(tfp->{
								mi_nivel_participacion = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
							});
						}
						
						dto.setNivelParticipacion(mi_nivel_participacion);
						dto.setEvaluador(obj.getUsuarioalianza().getNombresautoridad() + " " + obj.getUsuarioalianza().getApepatautoridad()+ " " + obj.getUsuarioalianza().getApematautoridad());
						dto.setCalificacion(obj.getNota()!=null?obj.getNota().toString():"");
						lista.add(dto);
					}				
				});	
			}		
		});		
		
		String [] columns = {"AÑO","ODS","CODIGO II.EE","NOMBRE II.EE","Código de trabajo","Estado de trabajo","Categoria","Nivel de participación","Evaluador","Calificación"};
		
		Sheet sheet = workbook.createSheet("Notas de evaluadores");
		Row row = sheet.createRow(0);		
		for(int i=0;i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}		
		int initRow = 1;
		for(NotasEvaluadorDto dto : lista) {
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(dto.getAnio());
			row.createCell(1).setCellValue(dto.getOds());
			row.createCell(2).setCellValue(dto.getCodigoIe());
			row.createCell(3).setCellValue(dto.getNombreIe());
			row.createCell(4).setCellValue(dto.getCodigoTrabajo());
			row.createCell(5).setCellValue(dto.getEstadoTrabajo());
			row.createCell(6).setCellValue(dto.getCategoria());
			row.createCell(7).setCellValue(dto.getNivelParticipacion());
			row.createCell(8).setCellValue(dto.getEvaluador());
			row.createCell(9).setCellValue(dto.getCalificacion());
			initRow++;			
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
	
	public ByteArrayInputStream reporteparticipantesinscritos(Integer ods,Integer anio,Integer categoria,Integer modalidad,Integer nivel,HttpSession ses)   {
		
		listaOds = new ArrayList<>();
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		
		if(tipousuarioid.equals(2)) {
			String usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
		}
		else {
			listaOds = odsserv.listarAll();
		}
		
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
				
		List<DocenteDto> listadocentedto = new ArrayList<DocenteDto>();
		
		docentetutorServ.listar().forEach(obj->{
			banderaods = false;
			bandera_anio=false;
			
			if(anio!=-1) {
				if(obj.getAnio().equals(anio))
					bandera_anio = true;
				else
					bandera_anio = false;
			}
			else {
				bandera_anio = true;
			}
			
			if(ods!=-1) {
				if(obj.getProgramaeducativo().getDistrito().getOdsid().equals(ods)) {
					banderaods  = true;
				}				
			}
			else {
				listaOds.forEach(objOds->{
					if(objOds.getId().equals(obj.getProgramaeducativo().getDistrito().getOdsid())) {
						banderaods = true;
					}
				});
			}			
			if(bandera_anio && banderaods){				
				DocenteDto dto = new DocenteDto();
				dto.setAnio(obj.getAnio());
				dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
				dto.setDepartamento(obj.getProgramaeducativo().getDistrito().getProvincia().getDepartamento().getDescripcion());
				dto.setProvincia(obj.getProgramaeducativo().getDistrito().getProvincia().getDescripcion());
				dto.setDistrito(obj.getProgramaeducativo().getDistrito().getDescripcion());
				dto.setNomie(obj.getProgramaeducativo().getNomie());
				dto.setCodigoie(obj.getProgramaeducativo().getCodmod());
				dto.setFechaRegistro(obj.getProgramaeducativo().getFecha_registro().toString());
				Postulacionconcurso pc = potulacionConcursoServ.getByIdAnio(obj.getProgramaeducativo().getId(), obj.getAnio());
				dto.setInscritoCe(pc!=null ? "Si":"No");
				List<ProgramaeducativoNivel> peNivel = peNivelServ.listProgeducNivel(obj.getProgramaeducativo().getId());
				nivelSeccionDocenteAlumnoVaronMujer = "";
				peNivel.forEach(objPenivel->{
					nivelSeccionDocenteAlumnoVaronMujer += objPenivel.getNivel().getTiponivel().getDescripcion() + "-"+objPenivel.getNivel().getNrosecciones()+"-"+objPenivel.getNivel().getNrodocentes()+"-"+objPenivel.getNivel().getNroalumnos()+"-"+objPenivel.getNivel().getNrovarones()+"-"+objPenivel.getNivel().getNromujeres()+ "/";
				});
				if(nivelSeccionDocenteAlumnoVaronMujer.length()>0)
					nivelSeccionDocenteAlumnoVaronMujer = nivelSeccionDocenteAlumnoVaronMujer.substring(0, nivelSeccionDocenteAlumnoVaronMujer.length()-1);
				dto.setNivelSeccionDocenteAlumnoVaronMujer(nivelSeccionDocenteAlumnoVaronMujer);
				dto.setAmbito(obj.getProgramaeducativo().getAmbito().getDescripcion());
				dto.setModalidadEnsenianza(obj.getProgramaeducativo().getModensenianza().getDescripcion());
				dto.setCodigoLocal(obj.getProgramaeducativo().getId().toString());
				dto.setTipoIe(obj.getProgramaeducativo().getTipoie().getDescripcion());
				dto.setDireccion(obj.getProgramaeducativo().getDirie());
				dto.setDre(obj.getProgramaeducativo().getDre());
				dto.setUgel(obj.getProgramaeducativo().getUgel());
				dto.setTelfIe(obj.getProgramaeducativo().getTelfie());
				dto.setEmailIe(obj.getProgramaeducativo().getMailie());
				dto.setFacebook(obj.getProgramaeducativo().getFacebook());
				dto.setLengua(obj.getProgramaeducativo().getLengua().getDescripcion());
				dto.setGeneroie(obj.getProgramaeducativo().getGenero().getDescripcion());				
				peTurno = "";
				List<ProgramaeducativoTurno> objPeTurno =	peTurnoServ.listProgeducTurno(obj.getProgramaeducativo().getId());
				objPeTurno.forEach(objTurno->{
					peTurno += objTurno.getTurno().getDescripcion() + " ";
				});				
				dto.setTurno(peTurno);				
				dto.setProveedorServicio(obj.getProgramaeducativo().getProveedor().getDescripcion());
				dto.setHorasAbastecimiento(obj.getProgramaeducativo().getAbastecimiento().toString());
				dto.setPiscina(obj.getProgramaeducativo().getPiscina().getDescripcion());
				dto.setTipoDocDirector(obj.getProgramaeducativo().getTipodocidentdir().getDescripcion());
				dto.setNroDocDirector(obj.getProgramaeducativo().getDocdir());
				dto.setApellidosDirector(obj.getProgramaeducativo().getApedir());
				dto.setNombresDirector(obj.getProgramaeducativo().getNomdir());
				dto.setGeneroDirector(obj.getProgramaeducativo().getGenerodir()!=null?obj.getProgramaeducativo().getGenerodir().getDescripcion():"");
				dto.setTelefonoDirector(obj.getProgramaeducativo().getTelfdir());
				dto.setCelularDirector(obj.getProgramaeducativo().getCeldir());
				dto.setCorreoDirector(obj.getProgramaeducativo().getMaildir());
				dto.setTipoDocProfesor(obj.getProgramaeducativo().getTipodocidentprof().getDescripcion());
				dto.setNroDocProfesor(obj.getProgramaeducativo().getDocprof());
				dto.setApellidosProfesor(obj.getProgramaeducativo().getApeprof());
				dto.setNombreProfesor(obj.getProgramaeducativo().getNomprof());
				dto.setGeneroProfesor(obj.getProgramaeducativo().getGeneroprof()!=null?obj.getProgramaeducativo().getGeneroprof().getDescripcion():"");
				dto.setTelefonoProfesor(obj.getProgramaeducativo().getTelfprof());
				dto.setCelularProfesor(obj.getProgramaeducativo().getCelprof());
				dto.setCorreoProfesor(obj.getProgramaeducativo().getMailprof());
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
		
		String [] columns = {"AÑO","ODS","DEPARTAMENTO","PROVINCIA","DISTRITO","INSTITUCIÓN EDUCATIVA","CÓDIGO LOCAL II.EE","FECHA REGISTRO","INSCRITO AL C.E","NIVEL-N°SECIONES-N°DOCENTES-N°ALUMNOS-N°VARONES-N°MUJERES","AMBITO","Modalidad de enseñanza", "Código local","Tipo II.EE","Dirección","DRE","UGEL","TELEF. II.EE","EMAIL II.EE","Facebook","Lengua","Clasifiación","Turno","Proveedor servicio","Horas abastecimiento","Piscina","Tipo doc. Director","N° Doc. Director","Apellidos director","Nombres director","Genero director","Telefono director","Celular director","Correo director","Tipo doc. profesor","N° Doc. profesor","Apellidos profesor","Nombres profesor","Genero profesor","Telefono profesor","Celular profesor","Correo profesor","Nombres","Apellido paterno","Apellido materno","Tipo de documento","Nro de documento","Género","Télefono","Email","Curso","Tipo de docente"};
		
		Sheet sheet = workbook.createSheet("Datos de docentes");
		Row row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("DATOS DE LA II.EE");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 41));/*1era celda , ultima celda, 1era columna, ultima columna*/		
		
		row.createCell(42).setCellValue("DATOS DEL DOCENTE INSCRITOS EN EL C.E");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 42, 52));
		
		row = sheet.createRow(1);
		for(int i=0;i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}
		
		int initRow = 2;
		for(DocenteDto dto : listadocentedto) {
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(dto.getAnio());
			row.createCell(1).setCellValue(dto.getOds());
			row.createCell(2).setCellValue(dto.getDepartamento());
			row.createCell(3).setCellValue(dto.getProvincia());
			row.createCell(4).setCellValue(dto.getDistrito());
			row.createCell(5).setCellValue(dto.getNomie());
			row.createCell(6).setCellValue(dto.getCodigoie());
			row.createCell(7).setCellValue(dto.getFechaRegistro());
			row.createCell(8).setCellValue(dto.getInscritoCe());
			row.createCell(9).setCellValue(dto.getNivelSeccionDocenteAlumnoVaronMujer());
			row.createCell(10).setCellValue(dto.getAmbito());
			row.createCell(11).setCellValue(dto.getModalidadEnsenianza());
			row.createCell(12).setCellValue(dto.getCodigoLocal());
			row.createCell(13).setCellValue(dto.getTipoIe());
			row.createCell(14).setCellValue(dto.getDireccion());
			row.createCell(15).setCellValue(dto.getDre());
			row.createCell(16).setCellValue(dto.getUgel());
			row.createCell(17).setCellValue(dto.getTelfIe());
			row.createCell(18).setCellValue(dto.getEmailIe());
			row.createCell(19).setCellValue(dto.getFacebook());
			row.createCell(20).setCellValue(dto.getLengua());
			row.createCell(21).setCellValue(dto.getGeneroie());
			row.createCell(22).setCellValue(dto.getTurno());
			row.createCell(23).setCellValue(dto.getProveedorServicio());
			row.createCell(24).setCellValue(dto.getHorasAbastecimiento());
			row.createCell(25).setCellValue(dto.getPiscina());
			row.createCell(26).setCellValue(dto.getTipoDocDirector());
			row.createCell(27).setCellValue(dto.getNroDocDirector());
			row.createCell(28).setCellValue(dto.getApellidosDirector());
			row.createCell(29).setCellValue(dto.getNombresDirector());
			row.createCell(30).setCellValue(dto.getGeneroDirector());
			row.createCell(31).setCellValue(dto.getTelefonoDirector());
			row.createCell(32).setCellValue(dto.getCelularDirector());
			row.createCell(33).setCellValue(dto.getCorreoDirector());
			row.createCell(34).setCellValue(dto.getTipoDocProfesor());
			row.createCell(35).setCellValue(dto.getNroDocProfesor());
			row.createCell(36).setCellValue(dto.getApellidosProfesor());
			row.createCell(37).setCellValue(dto.getNombreProfesor());
			row.createCell(38).setCellValue(dto.getGeneroProfesor());
			row.createCell(39).setCellValue(dto.getTelefonoProfesor());
			row.createCell(40).setCellValue(dto.getCelularProfesor());
			row.createCell(41).setCellValue(dto.getCorreoProfesor());
			row.createCell(42).setCellValue(dto.getNombre());
			row.createCell(43).setCellValue(dto.getAppaterno());
			row.createCell(44).setCellValue(dto.getApmaterno());
			row.createCell(45).setCellValue(dto.getTipodocumento());
			row.createCell(46).setCellValue(dto.getNrodocumento());
			row.createCell(47).setCellValue(dto.getGenero());
			row.createCell(48).setCellValue(dto.getTelefono());
			row.createCell(49).setCellValue(dto.getEmail());
			row.createCell(50).setCellValue(dto.getCurso());
			row.createCell(51).setCellValue(dto.getTipodocente());
			initRow++;			
		}		
		
		List<Participante> listaParticipante = participanteServ.listarhabilitados();
		List<ListaparticipantereporteDto> listareporte = new ArrayList<ListaparticipantereporteDto>();
		
		listaParticipante.forEach(obj->{
			ListaparticipantereporteDto reporte  = new ListaparticipantereporteDto();
			
			bandera_ods = false;
			boolean bandera_anio = false;
			boolean bandera_categoria = false;
			boolean bandera_modalidad = false;
			boolean bandera_nivel = false;
			
			if(ods!=-1) {
				if(obj.getProgramaeducativo().getDistrito().getOdsid().equals(ods)) {
					bandera_ods = true;
				}		
			}
			else {
				listaOds.forEach(objOds->{
					if(objOds.getId().equals(obj.getProgramaeducativo().getDistrito().getOdsid())) {
						bandera_ods = true;
					}
				});
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
			
			if(nivel!=-1) {
				if(obj.getGradoestudiante().getNivelgradopartid().equals(nivel))
					bandera_nivel = true;
				else
					bandera_nivel = false;
			}
			else {
				bandera_nivel = true;
			}			
			if( bandera_ods && bandera_anio && bandera_categoria && bandera_modalidad && bandera_nivel) 			
			{
				reporte.setAnio(obj.getAnhio());
				reporte.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion() );
				reporte.setDepartamento(obj.getProgramaeducativo().getDistrito().getProvincia().getDepartamento().getDescripcion());
				reporte.setProvincia(obj.getProgramaeducativo().getDistrito().getProvincia().getDescripcion());
				reporte.setDistrito(obj.getProgramaeducativo().getDistrito().getDescripcion());
				reporte.setNombreIe(obj.getProgramaeducativo().getNomie());
				reporte.setCodigoIe(obj.getProgramaeducativo().getCodmod());
				reporte.setFechaRegistro(obj.getProgramaeducativo().getFecha_registro().toString());
				Postulacionconcurso pc = potulacionConcursoServ.getByIdAnio(obj.getProgramaeducativo().getId(), anio);
				reporte.setInscritoCe(pc!=null?"Si":"No");
				List<ProgramaeducativoNivel> peNivel = peNivelServ.listProgeducNivel(obj.getProgramaeducativo().getId());
				nivelSeccionDocenteAlumnoVaronMujer = "";
				peNivel.forEach(objPenivel->{
					nivelSeccionDocenteAlumnoVaronMujer += objPenivel.getNivel().getTiponivel().getDescripcion() + "-"+objPenivel.getNivel().getNrosecciones()+"-"+objPenivel.getNivel().getNrodocentes()+"-"+objPenivel.getNivel().getNroalumnos()+"-"+objPenivel.getNivel().getNrovarones()+"-"+objPenivel.getNivel().getNromujeres()+ "/";
				});
				if(nivelSeccionDocenteAlumnoVaronMujer.length()>0)
					nivelSeccionDocenteAlumnoVaronMujer = nivelSeccionDocenteAlumnoVaronMujer.substring(0, nivelSeccionDocenteAlumnoVaronMujer.length()-1);
				reporte.setNivelSeccionDocenteAlumnoVaronMujer(nivelSeccionDocenteAlumnoVaronMujer);
				reporte.setAmbito(obj.getProgramaeducativo().getAmbito().getDescripcion());
				reporte.setModalidadEnsenianza(obj.getProgramaeducativo().getModensenianza().getDescripcion());
				reporte.setCodigoLocal(obj.getProgramaeducativo().getId().toString());
				reporte.setTipoIe(obj.getProgramaeducativo().getTipoie().getDescripcion());
				reporte.setDireccion(obj.getProgramaeducativo().getDirie());
				reporte.setDre(obj.getProgramaeducativo().getDre());
				reporte.setUgel(obj.getProgramaeducativo().getUgel());
				reporte.setTelfIe(obj.getProgramaeducativo().getTelfie());
				reporte.setEmailIe(obj.getProgramaeducativo().getMailie());
				reporte.setFacebook(obj.getProgramaeducativo().getFacebook());
				reporte.setLengua(obj.getProgramaeducativo().getLengua().getDescripcion());
				reporte.setGenero(obj.getProgramaeducativo().getGenero()!=null?obj.getProgramaeducativo().getGenero().getDescripcion():"");
				peTurno = "";
				List<ProgramaeducativoTurno> objPeTurno =	peTurnoServ.listProgeducTurno(obj.getProgramaeducativo().getId());
				objPeTurno.forEach(objTurno->{
					peTurno += objTurno.getTurno().getDescripcion() + " ";
				});
				reporte.setTurno(peTurno);
				reporte.setProveedorServicio(obj.getProgramaeducativo().getProveedor().getDescripcion());
				reporte.setHorasAbastecimiento(obj.getProgramaeducativo().getAbastecimiento());
				reporte.setPiscina(obj.getProgramaeducativo().getPiscina().getDescripcion());
				reporte.setTipoDocDirector(obj.getProgramaeducativo().getTipodocidentdir().getDescripcion());
				reporte.setNroDocDirector(obj.getProgramaeducativo().getDocdir());
				reporte.setApellidoDirector(obj.getProgramaeducativo().getApedir());
				reporte.setNombresDirector(obj.getProgramaeducativo().getNomdir());
				reporte.setGeneroDirector(obj.getProgramaeducativo().getGenerodir()!=null?obj.getProgramaeducativo().getGenerodir().getDescripcion():"");
				reporte.setTelefonoDirector(obj.getProgramaeducativo().getTelfdir());
				reporte.setCeularDirector(obj.getProgramaeducativo().getCeldir());
				reporte.setCorreoDirector(obj.getProgramaeducativo().getMaildir());
				reporte.setTipoDocProfesor(obj.getProgramaeducativo().getTipodocidentprof().getDescripcion());
				reporte.setNroDocProfesor(obj.getProgramaeducativo().getDocprof());
				reporte.setApellidosProfesor(obj.getProgramaeducativo().getApeprof());
				reporte.setNombresProfesor(obj.getProgramaeducativo().getNomprof());
				reporte.setGeneroProfesor(obj.getProgramaeducativo().getGeneroprof()!=null?obj.getProgramaeducativo().getGeneroprof().getDescripcion():"");
				reporte.setTelefonoProfesor(obj.getProgramaeducativo().getTelfprof());
				reporte.setCelularProfesor(obj.getProgramaeducativo().getCelprof());
				reporte.setCorreoProfesor(obj.getProgramaeducativo().getMailprof());reporte.setNombreest(obj.getNombreestudiante());
				reporte.setAppaternoest(obj.getAppaternoestudiante());
				reporte.setApmaternoest(obj.getApmaternoestudiante());
				reporte.setFechanacimientoest(obj.getFechanacimientoestudiante().toString());
				reporte.setSeccionest(obj.getSeccion());
				reporte.setTipodocest(obj.getTipodocumentoestudiante().getDescripcion());
				reporte.setNrodocest(obj.getNrodocumentoestudiante());
				
				reporte.setGeneroest(obj.getGeneroestudiante()!=null?  generoprofserv.ListarporId(obj.getGeneroestudiante().getId()).getDescripcion() :"");
				
				reporte.setNivelest(obj.getGradoestudiante().getNivelparticipante().getDescripcion());
				reporte.setGradoest(obj.getGradoestudiante().getDescripcion());
				reporte.setNivelparticipacion(obj.getGradoestudiante().getNivelgradopartdesc());
				peModalidad="";
				if(obj.getModalidadpostulacionindividual()==1)
					peModalidad += "Individual/";
				if(obj.getModalidadpostulaciongrupal()==1)
					peModalidad += "Grupal/";
				if(peModalidad.length()>0)
					peModalidad = peModalidad.substring(0, peModalidad.length()-1);
				reporte.setModalidadest(peModalidad);
				
				peCategorias="";
				if(obj.getCategoriacuento()==1)
					peCategorias += "Cuento/";
				if(obj.getCategoriapoesia()==1)
					peCategorias += "Poesía/";
				if(obj.getCategoriadibujopintura()==1)
					peCategorias += "Dibujo o Pintura/";
				if(obj.getCategoriacomposicionmusical()==1)
					peCategorias += "Composición musical/";
				if(obj.getCategoriaahorroagua()==1)
					peCategorias += "Ahorro del agua en tu hogar/";				
				if(peCategorias.length()>0)
					peCategorias = peCategorias.substring(0, peCategorias.length()-1);				
				reporte.setCategoriaest(peCategorias);
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
		
		String [] columns1 = {"AÑO","ODS","DEPARTAMENTO","PROVINCIA","DISTRITO","INSTITUCION EDUCATIVA","CODIGO LOCAL IIEE","FECHA REGISTRO","INSCRITO AL CE","NIVEL-N°Seciones-N°Docenes-N°Alumnos-N°Varones-N°Mujeres","Ambito","Modalidad enseñanza","Código local","Tipo II.EE","Dirección","DRE","UGEL","Teléfono II.EE","Email II.EE","Facebook","Lengua","Clasificación","Turno","Proveedor de servicio","Horas de abastecimiento","Piscina","Tipo doc. director","N° doc.Director","Apellidos de director","Nombre director","Genero director","Telefono director","Celular director","Correo director","Tipo doc. profesor","N° doc. profesor","Apellidos profesor","Nombres profesor","Genero profesor","Teléfono profesor","Celular profesor","Correo profesor","Nombres","Apellido paterno","Apellido materno","Fecha de nacimiento","Sección", "Tipo de documento","Nro de documento","Genero","Nivel","Grado",    "Nivel de participación", "Modalidad",    "Categorías","Nombres tutor","Apellido paterno tutor","Apellido materno tutor","telefono","Correo electronico", "Tipo de documento tutor","Nro de documento tutor","Parentesco"};
		Sheet sheet1 = workbook.createSheet("Datos de participantes");
		Row row1 = sheet1.createRow(0);
		
		row1.createCell(0).setCellValue("DATOS DE A II.EE");
		sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 41));/*1era celda , ultima celda, 1era columna, ultima columna*/		
		
		row1.createCell(42).setCellValue("DATOS DEL ESTUDIANTE INSCRITOS EN EL C.E");
		sheet1.addMergedRegion(new CellRangeAddress(0, 0, 42, 54));
		
		row1.createCell(55).setCellValue("DATOS DEL PADRE/MADRE O TUTOR");
		sheet1.addMergedRegion(new CellRangeAddress(0, 0, 55, 62));
		
		row1 = sheet1.createRow(1);
		for(int i=0;i<columns1.length;i++) {
			Cell cell = row1.createCell(i);
			cell.setCellValue(columns1[i]);
		}
		
		int initRow1 = 2;
		for(ListaparticipantereporteDto participante : listareporte) {
			row1 = sheet1.createRow(initRow1);
			row1.createCell(0).setCellValue(participante.getAnio());
			row1.createCell(1).setCellValue(participante.getOds());
			row1.createCell(2).setCellValue(participante.getDepartamento());
			row1.createCell(3).setCellValue(participante.getProvincia());
			row1.createCell(4).setCellValue(participante.getDistrito());
			row1.createCell(5).setCellValue(participante.getNombreIe());
			row1.createCell(6).setCellValue(participante.getCodigoIe());
			row1.createCell(7).setCellValue(participante.getFechaRegistro());
			row1.createCell(8).setCellValue(participante.getInscritoCe());
			row1.createCell(9).setCellValue(participante.getNivelSeccionDocenteAlumnoVaronMujer());
			row1.createCell(10).setCellValue(participante.getAmbito());
			row1.createCell(11).setCellValue(participante.getModalidadEnsenianza());
			row1.createCell(12).setCellValue(participante.getCodigoLocal());
			row1.createCell(13).setCellValue(participante.getTipoIe());
			row1.createCell(14).setCellValue(participante.getDireccion());
			row1.createCell(15).setCellValue(participante.getDre());
			row1.createCell(16).setCellValue(participante.getUgel());
			row1.createCell(17).setCellValue(participante.getTelfIe());
			row1.createCell(18).setCellValue(participante.getEmailIe());
			row1.createCell(19).setCellValue(participante.getFacebook());
			row1.createCell(20).setCellValue(participante.getLengua());
			row1.createCell(21).setCellValue(participante.getGenero());
			row1.createCell(22).setCellValue(participante.getTurno());
			row1.createCell(23).setCellValue(participante.getProveedorServicio());
			row1.createCell(24).setCellValue(participante.getHorasAbastecimiento());
			row1.createCell(25).setCellValue(participante.getPiscina());
			row1.createCell(26).setCellValue(participante.getTipoDocDirector());
			row1.createCell(27).setCellValue(participante.getNroDocDirector());
			row1.createCell(28).setCellValue(participante.getApellidoDirector());
			row1.createCell(29).setCellValue(participante.getNombresDirector());
			row1.createCell(30).setCellValue(participante.getGeneroDirector());
			row1.createCell(31).setCellValue(participante.getTelefonoDirector());
			row1.createCell(32).setCellValue(participante.getCeularDirector());
			row1.createCell(33).setCellValue(participante.getCorreoDirector());
			row1.createCell(34).setCellValue(participante.getTipoDocProfesor());
			row1.createCell(35).setCellValue(participante.getNroDocProfesor());
			row1.createCell(36).setCellValue(participante.getApellidosProfesor());
			row1.createCell(37).setCellValue(participante.getNombresProfesor());
			row1.createCell(38).setCellValue(participante.getGeneroProfesor());
			row1.createCell(39).setCellValue(participante.getTelefonoProfesor());
			row1.createCell(40).setCellValue(participante.getCelularProfesor());
			row1.createCell(41).setCellValue(participante.getCorreoProfesor());
			row1.createCell(42).setCellValue(participante.getNombreest());
			row1.createCell(43).setCellValue(participante.getAppaternoest());
			row1.createCell(44).setCellValue(participante.getApmaternoest());
			row1.createCell(45).setCellValue(participante.getFechanacimientoest());
			row1.createCell(46).setCellValue(participante.getSeccionest());
			row1.createCell(47).setCellValue(participante.getTipodocest());
			row1.createCell(48).setCellValue(participante.getNrodocest());
			row1.createCell(49).setCellValue(participante.getGeneroest());
			row1.createCell(50).setCellValue(participante.getNivelest());
			row1.createCell(51).setCellValue(participante.getGradoest());
			row1.createCell(52).setCellValue(participante.getNivelparticipacion());
			row1.createCell(53).setCellValue(participante.getModalidadest());
			row1.createCell(54).setCellValue(participante.getCategoriaest());
			row1.createCell(55).setCellValue(participante.getNombreapoderado());
			row1.createCell(56).setCellValue(participante.getAppaternoapoderado());
			row1.createCell(57).setCellValue(participante.getApmaternoapoderado());
			row1.createCell(58).setCellValue(participante.getNrotelfapoderado());
			row1.createCell(59).setCellValue(participante.getCorreoapoderado());
			row1.createCell(60).setCellValue(participante.getTipodocapoderado());
			row1.createCell(61).setCellValue(participante.getNrodocapoderado());
			row1.createCell(62).setCellValue(participante.getParentescoapoderado());	
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
	
	@GetMapping(value="/reportegeneral/{ods}/{anio}/{categoria}/{modalidad}/{nivel}")	
	public ResponseEntity<InputStreamResource> exportreportegeneral(@PathVariable(value="ods") Integer ods,
			@PathVariable(value="anio") Integer anio,
			@PathVariable(name="categoria") Integer categoria,
			@PathVariable(name="modalidad") Integer modalidad,
			@PathVariable(name="nivel") Integer nivel, HttpSession ses) {
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		ByteArrayInputStream stream = reportegeneral(ods,anio,categoria,modalidad,nivel,ses);
		
		HttpHeaders headers = new HttpHeaders();
		
		fecha_archivo = dateFormat.format(date) + hourFormat.format(date);
		
		headers.add("Content-Disposition", "attachment; filename=Reportegeneral_"+fecha_archivo+".csv");
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		
	}
	
	public ByteArrayInputStream reportegeneral(Integer ods,Integer anio,Integer categoria,Integer modalidad,Integer nivel,HttpSession ses)   {
		
		listaOds = new ArrayList<>();
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		if(tipousuarioid.equals(2)) {
			String usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
		}
		else {
			listaOds = odsserv.listarAll();
		}
		
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		List<IieeReporteDto> listaiiee = new ArrayList<IieeReporteDto>();
		contador=0;
		programaEducativoServ.listarTodos().forEach(pe->{
			bandera_ods = false;
			bandera_anio = false;
			bandera_categoria = false;
			bandera_modalidad = false;
			bandera_nivel = false;
			
			if(pe.getAnhio()!=null) {
			
					if(ods!=-1) {
						if(pe.getDistrito().getOdsid().equals(ods))
							bandera_ods = true;
					}
					else {
						listaOds.forEach(objOds->{
							if(objOds.getId().equals(pe.getDistrito().getOdsid())) {
								bandera_ods = true;
							}
						});
					}
					if(anio!=-1) {
						if(pe.getAnhio().equals(anio))
							bandera_anio = true;
						else
							bandera_anio = false;					
					}
					else {
						bandera_anio = true;
					}
					
					if( bandera_ods && bandera_anio &&  pe.getAnhio()!=null) 			
					{
						List<Trabajosfinales> objTrabajosFinales = trabajosFinalesServ.listarhabilitadosPE(pe.getId());
						if(objTrabajosFinales.size()==0) {
							
							bandera=true;
							
							if(categoria!=-1) {
								bandera = false;
							}
							
							if(modalidad!=-1) {
								bandera = false;
							}
							
							if(nivel!=-1) {
								bandera = false;
							}
							
							if(bandera){		
								IieeReporteDto iiee = new IieeReporteDto();
								iiee.setAnio(pe.getAnhio());
								iiee.setOds(odsserv.byOds(pe.getDistrito().getOdsid()).getDescripcion());
								iiee.setDepartamento(pe.getDistrito().getProvincia().getDepartamento().getDescripcion());
								iiee.setProvincia(pe.getDistrito().getProvincia().getDescripcion());
								iiee.setDistrito(pe.getDistrito().getDescripcion());
								iiee.setNombreIiee(pe.getNomie());
								iiee.setCodigoLocalIiee(pe.getCodmod());
								iiee.setFechaRegistro(pe.getFecha_registro()!=null?pe.getFecha_registro().toString():"");
								Postulacionconcurso pc = potulacionConcursoServ.getByIdAnio(pe.getId(), pe.getAnhio());
								iiee.setInscrioCe(pc!=null ? "Si":"No");
								List<ProgramaeducativoNivel> peNivel = peNivelServ.listProgeducNivel(pe.getId());
								nivelSeccionDocenteAlumnoVaronMujer = "";
								peNivel.forEach(objPenivel->{
									nivelSeccionDocenteAlumnoVaronMujer += objPenivel.getNivel().getTiponivel().getDescripcion() + "-"+objPenivel.getNivel().getNrosecciones()+"-"+objPenivel.getNivel().getNrodocentes()+"-"+objPenivel.getNivel().getNroalumnos()+"-"+objPenivel.getNivel().getNrovarones()+"-"+objPenivel.getNivel().getNromujeres()+ "/";
								});
								if(nivelSeccionDocenteAlumnoVaronMujer.length()>0)
									nivelSeccionDocenteAlumnoVaronMujer = nivelSeccionDocenteAlumnoVaronMujer.substring(0, nivelSeccionDocenteAlumnoVaronMujer.length()-1);
								iiee.setNivelSeccionDocenteAlumnoVaronMujer(nivelSeccionDocenteAlumnoVaronMujer);
								iiee.setAmbito(pe.getAmbito()!=null?pe.getAmbito().getDescripcion():"");
								iiee.setModalidadEnsenianza(pe.getModensenianza()!=null?pe.getModensenianza().getDescripcion():"");
								iiee.setCodigoLocal(pe.getId().toString());
								iiee.setTipoIiee(pe.getTipoie()!=null?pe.getTipoie().getDescripcion():"");
								iiee.setDireccion(pe.getDirie());
								iiee.setDre(pe.getDre());
								iiee.setUgel(pe.getUgel());
								iiee.setTelfIiee(pe.getTelfie());
								iiee.setEmailIiee(pe.getMailie());
								iiee.setFacebook(pe.getFacebook());
								iiee.setLengua(pe.getLengua()!=null?pe.getLengua().getDescripcion():"");
								iiee.setGenero(pe.getGenero()!=null?pe.getGenero().getDescripcion():"");
								peTurno = "";
								List<ProgramaeducativoTurno> objPeTurno =	peTurnoServ.listProgeducTurno(pe.getId());
								objPeTurno.forEach(objTurno->{
									peTurno += objTurno.getTurno().getDescripcion() + " ";
								});				
								iiee.setTurno(peTurno);
								iiee.setProveedorServicio(pe.getProveedor()!=null?pe.getProveedor().getDescripcion():"");
								peSuministro = "";
								pe.getSuministro().forEach(objSuministro->{
									peSuministro += objSuministro.getNumero().toString() + "/";
								});
								if(peSuministro.length()>0)
									peSuministro = peSuministro.substring(0, peSuministro.length()-1);
								iiee.setSuministro(peSuministro);
								iiee.setHorasAbastecimiento(pe.getAbastecimiento()!=null?pe.getAbastecimiento().toString():"");
								iiee.setPiscina(pe.getPiscina()!=null?pe.getPiscina().getDescripcion():"");
								iiee.setTipoDocDirector(pe.getTipodocidentdir()!=null?pe.getTipodocidentdir().getDescripcion():"");
								iiee.setNroDocDirector(pe.getDocdir());
								iiee.setApellidosDirector(pe.getApedir());
								iiee.setNombresDirector(pe.getNomdir());
								iiee.setGeneroDirector(pe.getGenerodir()!=null?pe.getGenerodir().getDescripcion():"");
								iiee.setTelefonoDirector(pe.getTelfdir());
								iiee.setCelularDirector(pe.getCeldir());
								iiee.setCorreoDirector(pe.getMaildir());
								iiee.setTipoDocProfesor(pe.getTipodocidentprof()!=null?pe.getTipodocidentprof().getDescripcion():"");
								iiee.setNroDocProfesor(pe.getDocprof());
								iiee.setApellidosProfesor(pe.getApeprof());
								iiee.setNombresProfesor(pe.getNomprof());
								iiee.setGeneroProfesor(pe.getGeneroprof()!=null?pe.getGeneroprof().getDescripcion():"");
								iiee.setTelefonoProfesor(pe.getTelfprof());
								iiee.setCelularProfesor(pe.getCelprof());
								iiee.setCorreoProfesor(pe.getMailprof());
								iiee.setCodigoTrabajo("-");
								iiee.setTituloTrabajo("-");
								iiee.setLinkVideo("-");
								iiee.setModalidadTrabajo("-");
								iiee.setCategoriaTrabajo("-");
								iiee.setNivelParticipacionTrabajo("-");
								iiee.setEjesTematicos("-");
								iiee.setNombreParticipante("-");
								iiee.setApellidoPaternoParticipante("-");
								iiee.setApellidoMaternoParticipante("-");
								iiee.setTipoDocumentoParticipante("-");
								iiee.setNroDocumentoParticipante("-");
								iiee.setFechaNacimientoParticipante("-");
								iiee.setGeneroParticipante("-");
								iiee.setSeccionParticipante("-");
								iiee.setNivelParticipante("-");
								iiee.setGradoParticipante("-");
								iiee.setNombreTutor("-");
								iiee.setApellidoPaternoTutor("-");
								iiee.setApellidoMaternoTutor("-");
								iiee.setTipoDocumentoTutor("-");
								iiee.setNroDocumentoTutor("-");
								iiee.setTelefonoTutor("-");
								iiee.setCorreoTutor("-");
								iiee.setParentesco("-");
								iiee.setNombreDocente("-");
								iiee.setApellidoPaternoDocente("-");
								iiee.setApellidoMaternoDocente("-");
								iiee.setTipoDocumentoDocente("-");
								iiee.setNrodocumentoDocente("-");
								iiee.setTelefonoDocente("-");
								iiee.setGeneroDocente("-");
								iiee.setCorreoDocente("-");
								iiee.setNotaRegional("0.0");
								iiee.setPuestoRegional("");
								iiee.setNotaNacional("0.0");
								iiee.setPuestoNacional("");
								listaiiee.add(iiee);
							}
						}
						else {
							objTrabajosFinales.forEach(tf->{
								List<TrabajosfinalesParticipante> objtfp = trabajosFinalesParticipanteService.listar(tf.getId());
									objtfp.forEach(tfp->{
										bandera_ods = false;
										bandera_anio = false;
										bandera_categoria = false;
										bandera_modalidad = false;
										bandera_nivel = false;
										if(ods!=-1) {
											if(tfp.getTrabajosfinales().getProgramaeducativo().getDistrito().getOdsid().equals(ods))
												bandera_ods = true;
										}
										else {
											listaOds.forEach(objOds->{
												if(objOds.getId().equals(tfp.getTrabajosfinales().getProgramaeducativo().getDistrito().getOdsid())) {
													bandera_ods = true;
												}
											});
										}
										if(categoria!=-1) {
											if(categoria.equals(tfp.getTrabajosfinales().getCategoriatrabajo().getId()))
												bandera_categoria = true;
										}
										else {
											bandera_categoria = true;
										}
										
										if(modalidad!=-1) {
											if(modalidad.equals(tfp.getTrabajosfinales().getModalidadtrabajo().getId()))
												bandera_modalidad = true;
										}
										else {
											bandera_modalidad = true;
										}
										
										if(nivel!=-1) {
											if(tfp.getParticipante().getGradoestudiante().getNivelgradopartid().equals(nivel))
												bandera_nivel = true;
										}
										else {
											bandera_nivel = true;
										}	
										
										if(bandera_categoria && bandera_modalidad && bandera_nivel && bandera_ods && tfp.getTrabajosfinales().getEnviado()==1) {		
											IieeReporteDto iiee = new IieeReporteDto();
											iiee.setAnio(pe.getAnhio());
											iiee.setOds(odsserv.byOds(pe.getDistrito().getOdsid()).getDescripcion());
											iiee.setDepartamento(pe.getDistrito().getProvincia().getDepartamento().getDescripcion());
											iiee.setProvincia(pe.getDistrito().getProvincia().getDescripcion());
											iiee.setDistrito(pe.getDistrito().getDescripcion());
											iiee.setNombreIiee(pe.getNomie());
											iiee.setCodigoLocalIiee(pe.getCodmod());
											iiee.setFechaRegistro(pe.getFecha_registro()!=null?pe.getFecha_registro().toString():"");
											Postulacionconcurso pc = potulacionConcursoServ.getByIdAnio(pe.getId(), pe.getAnhio());
											iiee.setInscrioCe(pc!=null ? "Si":"No");
											List<ProgramaeducativoNivel> peNivel = peNivelServ.listProgeducNivel(pe.getId());
											nivelSeccionDocenteAlumnoVaronMujer = "";
											peNivel.forEach(objPenivel->{
												nivelSeccionDocenteAlumnoVaronMujer += objPenivel.getNivel().getTiponivel().getDescripcion() + "-"+objPenivel.getNivel().getNrosecciones()+"-"+objPenivel.getNivel().getNrodocentes()+"-"+objPenivel.getNivel().getNroalumnos()+"-"+objPenivel.getNivel().getNrovarones()+"-"+objPenivel.getNivel().getNromujeres()+ "/";
											});
											if(nivelSeccionDocenteAlumnoVaronMujer.length()>0)
												nivelSeccionDocenteAlumnoVaronMujer = nivelSeccionDocenteAlumnoVaronMujer.substring(0, nivelSeccionDocenteAlumnoVaronMujer.length()-1);
											iiee.setNivelSeccionDocenteAlumnoVaronMujer(nivelSeccionDocenteAlumnoVaronMujer);
											iiee.setAmbito(pe.getAmbito()!=null?pe.getAmbito().getDescripcion():"");
											iiee.setModalidadEnsenianza(pe.getModensenianza()!=null?pe.getModensenianza().getDescripcion():"");
											iiee.setCodigoLocal(pe.getId().toString());
											iiee.setTipoIiee(pe.getTipoie()!=null?pe.getTipoie().getDescripcion():"");
											iiee.setDireccion(pe.getDirie());
											iiee.setDre(pe.getDre());
											iiee.setUgel(pe.getUgel());
											iiee.setTelfIiee(pe.getTelfie());
											iiee.setEmailIiee(pe.getMailie());
											iiee.setFacebook(pe.getFacebook());
											iiee.setLengua(pe.getLengua()!=null?pe.getLengua().getDescripcion():"");
											iiee.setGenero(pe.getGenero()!=null?pe.getGenero().getDescripcion():"");
											peTurno = "";
											List<ProgramaeducativoTurno> objPeTurno =	peTurnoServ.listProgeducTurno(pe.getId());
											objPeTurno.forEach(objTurno->{
												peTurno += objTurno.getTurno().getDescripcion() + " ";
											});				
											iiee.setTurno(peTurno);
											iiee.setProveedorServicio(pe.getProveedor()!=null?pe.getProveedor().getDescripcion():"");
											peSuministro = "";
											pe.getSuministro().forEach(objSuministro->{
												peSuministro += objSuministro.getNumero().toString() + "/";
											});
											if(peSuministro.length()>0)
												peSuministro = peSuministro.substring(0, peSuministro.length()-1);
											iiee.setSuministro(peSuministro);
											iiee.setHorasAbastecimiento(pe.getAbastecimiento()!=null?pe.getAbastecimiento().toString():"");
											iiee.setPiscina(pe.getPiscina()!=null?pe.getPiscina().getDescripcion():"");
											iiee.setTipoDocDirector(pe.getTipodocidentdir()!=null?pe.getTipodocidentdir().getDescripcion():"");
											iiee.setNroDocDirector(pe.getDocdir());
											iiee.setApellidosDirector(pe.getApedir());
											iiee.setNombresDirector(pe.getNomdir());
											iiee.setGeneroDirector(pe.getGenerodir()!=null?pe.getGenerodir().getDescripcion():"");
											iiee.setTelefonoDirector(pe.getTelfdir());
											iiee.setCelularDirector(pe.getCeldir());
											iiee.setCorreoDirector(pe.getMaildir());
											iiee.setTipoDocProfesor(pe.getTipodocidentprof()!=null?pe.getTipodocidentprof().getDescripcion():"");
											iiee.setNroDocProfesor(pe.getDocprof());
											iiee.setApellidosProfesor(pe.getApeprof());
											iiee.setNombresProfesor(pe.getNomprof());
											iiee.setGeneroProfesor(pe.getGeneroprof()!=null?pe.getGeneroprof().getDescripcion():"");
											iiee.setTelefonoProfesor(pe.getTelfprof());
											iiee.setCelularProfesor(pe.getCelprof());
											iiee.setCorreoProfesor(pe.getMailprof());
											iiee.setCodigoTrabajo(tf.getProgramaeducativo().getCodmod()+"_"+tf.getNumeracion());
											iiee.setEstadoTrabajo(tf.getEstadotrabajo().getDescripcion());
											iiee.setTituloTrabajo(tf.getTitulotrabajo());
											iiee.setLinkVideo(tf.getLinkvideo());
											iiee.setModalidadTrabajo(tf.getModalidadtrabajo()!=null?tf.getModalidadtrabajo().getDescripcion():"");
											iiee.setCategoriaTrabajo(tf.getCategoriatrabajo()!=null?tf.getCategoriatrabajo().getDescripcion():"");
											iiee.setNivelParticipacionTrabajo(tfp.getParticipante()!=null?tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc():"");
											
											peEjesTematicos = "";
											if(tf.getConversacion()==1) {
												peEjesTematicos += "Conservación de las fuentes de agua/";
											}
											if(tf.getValoracionagua()==1) {
												peEjesTematicos += "Valoración de los servicios de agua potable/";
											}
											if(tf.getValoracionalcantarillado()==1) {
												peEjesTematicos += "Valoración del servicio de alcantarillado/";
											}
											if(tf.getBuenuso()==1) {
												peEjesTematicos += "Buen uso y reúso del agua potable/";
											}
											if(tf.getImportancia()==1) {
												peEjesTematicos += "Importancia de cerrar la brecha en saneamiento/";
											}
											if(tf.getVinculo()==1) {
												peEjesTematicos += "El vínculo estratégico entre el agua seura y la salud/";
											}
											if(tf.getCarencias()==1) {
												peEjesTematicos += "Las carencias que ponen en riesgo la vida/";
											}
											if(tf.getRevaloracion()!=null) {
												if(tf.getRevaloracion()==1) {
													peEjesTematicos += "Revaloración de las practicas ancetrales para la seguridad hídrica/";
												}
											}
											if(peEjesTematicos.length()>0)
												peEjesTematicos = peEjesTematicos.substring(0, peEjesTematicos.length()-1);
											iiee.setEjesTematicos(peEjesTematicos);
											iiee.setNombreParticipante(tfp.getParticipante()!=null?tfp.getParticipante().getNombreestudiante():"");
											iiee.setApellidoPaternoParticipante(tfp.getParticipante()!=null?tfp.getParticipante().getAppaternoestudiante():"");
											iiee.setApellidoMaternoParticipante(tfp.getParticipante()!=null?tfp.getParticipante().getApmaternoestudiante():"");
											iiee.setTipoDocumentoParticipante(tfp.getParticipante()!=null?tfp.getParticipante().getTipodocumentoestudiante().getDescripcion():"");
											iiee.setNroDocumentoParticipante(tfp.getParticipante()!=null?tfp.getParticipante().getNrodocumentoestudiante():"");
											iiee.setFechaNacimientoParticipante(tfp.getParticipante()!=null?tfp.getParticipante().getFechanacimientoestudiante().toString():"");
											
											iiee.setGeneroParticipante(tfp.getParticipante()!=null?generoprofserv.ListarporId(tfp.getParticipante().getGeneroestudiante().getId()).getDescripcion():"");
											
											iiee.setSeccionParticipante(tfp.getParticipante()!=null?tfp.getParticipante().getSeccion():"");
											iiee.setNivelParticipante(tfp.getParticipante()!=null?tfp.getParticipante().getGradoestudiante().getNivelparticipante().getDescripcion():"");
											iiee.setGradoParticipante(tfp.getParticipante()!=null?tfp.getParticipante().getGradoestudiante().getDescripcion():"");
											iiee.setNombreTutor(tfp.getParticipante()!=null?tfp.getParticipante().getNombrepmt():"");
											iiee.setApellidoPaternoTutor(tfp.getParticipante()!=null?tfp.getParticipante().getAppaternopmt():"");
											iiee.setApellidoMaternoTutor(tfp.getParticipante()!=null?tfp.getParticipante().getAppaternopmt():"");
											iiee.setTipoDocumentoTutor(tfp.getParticipante()!=null?tfp.getParticipante().getTipodocumentopmt().getDescripcion():"");
											iiee.setNroDocumentoTutor(tfp.getParticipante()!=null?tfp.getParticipante().getNrodocumentopmt():"");
											iiee.setTelefonoTutor(tfp.getParticipante()!=null?tfp.getParticipante().getNrotelefonopmt():"");
											iiee.setCorreoTutor(tfp.getParticipante()!=null?tfp.getParticipante().getCorreoelectronicopmt():"");
											iiee.setParentesco(tfp.getParticipante()!=null?tfp.getParticipante().getParentesco().getDescripcion():"");
											iiee.setNombreDocente(tf.getNombre());
											iiee.setApellidoPaternoDocente(tf.getAppaterno());
											iiee.setApellidoMaternoDocente(tf.getApmaterno());
											iiee.setTipoDocumentoDocente(tf.getTipodocumento()!=null?tf.getTipodocumento().getDescripcion():"");
											iiee.setNrodocumentoDocente(tf.getNrodocumento());
											iiee.setTelefonoDocente(tf.getTelefono());
											iiee.setGeneroDocente(tf.getGenero()!=null?tf.getGenero().getDescripcion():"");
											iiee.setCorreoDocente(tf.getCorreo());
											iiee.setNotaRegional(tf.getNota()!=null?dosDecimales.format(tf.getNota()):"");
											iiee.setPuestoRegional(tf.getPuesto()==0?"":tf.getPuesto().toString());
											iiee.setNotaNacional("0.0");
											iiee.setPuestoNacional("");
											listaiiee.add(iiee);
										}
									});
							});
						}
					}
			}
		});
		
		String [] columns = {"AÑO","ODS","DEPARTAMENTO","PROVINCIA","DISTRITO","INSTITUCIÓN EDUCATIVA","CÓDIGO LOCAL II.EE","FECHA REGISTRO","INSCRITO AL C.E","NIVEL-N°SECIONES-N°DOCENTES-N°ALUMNOS-N°VARONES-N°MUJERES","AMBITO","Modalidad de enseñanza", "Código local","Tipo II.EE","Dirección","DRE","UGEL","TELEF. II.EE","EMAIL II.EE","Facebook","Lengua","Clasificación","Turno","Proveedor servicio","Horas abastecimiento","Piscina","Tipo doc. Director","N° Doc. Director","Apellidos director","Nombres director","Genero director","Telefono director","Celular director","Correo director","Tipo doc. profesor","N° Doc. profesor","Apellidos profesor","Nombres profesor","Genero profesor","Telefono profesor","Celular profesor","Correo profesor","Código de trabajo","Estado de trabajo","Título de trabajo","Link video","Modalidad","Categoria","Nivel de participación","Ejes tématicos","Nombres del participante","Apellido paterno","Apellido materno","Tipo de documento","Nro de documento","Fecha de nacimiento","Género","Sección","Nivel","Grado","Nombres de tutor","Apellido paterno tutor","Apellido materno tutor","Tipo de documento","Nro de documento","telefono","correo electronico","Parentesco","Nombre del docente","Apellido paterno","Apellido materno","Tipo de documento","Nro de documento","Telefono","Género","Correo electrónico","Nota","Puesto","Nota","Puesto"};
		
		Sheet sheet = workbook.createSheet("Datos de la II.EE");
		Row row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("DATOS DE LA II.EE");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 41));/*1era celda , ultima celda, 1era columna, ultima columna*/		
		
		row.createCell(42).setCellValue("DATOS DEL TRABAJO");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 42, 49));
		
		row.createCell(50).setCellValue("DATOS DE LOS PARTICIPANTES");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 50, 59));
		
		row.createCell(60).setCellValue("DATOS DL PADRE/MADRE O TUTOR");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 60, 67));
		
		row.createCell(68).setCellValue("DATOS DEL DOCENTE");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 68, 75));
		
		row.createCell(76).setCellValue("CONCURSO REGIONAL");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 76, 77));
		
		row.createCell(78).setCellValue("CONCURSO NACIONAL");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 78, 79));
		
		row = sheet.createRow(1);
		for(int i=0;i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}
		
		int initRow = 2;
		for(IieeReporteDto dto : listaiiee) {
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(dto.getAnio());
			row.createCell(1).setCellValue(dto.getOds());
			row.createCell(2).setCellValue(dto.getDepartamento());
			row.createCell(3).setCellValue(dto.getProvincia());
			row.createCell(4).setCellValue(dto.getDistrito());
			row.createCell(5).setCellValue(dto.getNombreIiee());
			row.createCell(6).setCellValue(dto.getCodigoLocalIiee());
			row.createCell(7).setCellValue(dto.getFechaRegistro());
			row.createCell(8).setCellValue(dto.getInscrioCe());
			row.createCell(9).setCellValue(dto.getNivelSeccionDocenteAlumnoVaronMujer());
			row.createCell(10).setCellValue(dto.getAmbito());
			row.createCell(11).setCellValue(dto.getModalidadEnsenianza());
			row.createCell(12).setCellValue(dto.getCodigoLocal());
			row.createCell(13).setCellValue(dto.getTipoIiee());
			row.createCell(14).setCellValue(dto.getDireccion());
			row.createCell(15).setCellValue(dto.getDre());
			row.createCell(16).setCellValue(dto.getUgel());
			row.createCell(17).setCellValue(dto.getTelfIiee());
			row.createCell(18).setCellValue(dto.getEmailIiee());
			row.createCell(19).setCellValue(dto.getFacebook());
			row.createCell(20).setCellValue(dto.getLengua());
			row.createCell(21).setCellValue(dto.getGenero());
			row.createCell(22).setCellValue(dto.getTurno());
			row.createCell(23).setCellValue(dto.getProveedorServicio());
			row.createCell(24).setCellValue(dto.getHorasAbastecimiento());
			row.createCell(25).setCellValue(dto.getPiscina());
			row.createCell(26).setCellValue(dto.getTipoDocDirector());
			row.createCell(27).setCellValue(dto.getNroDocDirector());
			row.createCell(28).setCellValue(dto.getApellidosDirector());
			row.createCell(29).setCellValue(dto.getNombresDirector());
			row.createCell(30).setCellValue(dto.getGeneroDirector());
			row.createCell(31).setCellValue(dto.getTelefonoDirector());
			row.createCell(32).setCellValue(dto.getCelularDirector());
			row.createCell(33).setCellValue(dto.getCorreoDirector());
			row.createCell(34).setCellValue(dto.getTipoDocProfesor());
			row.createCell(35).setCellValue(dto.getNroDocProfesor());
			row.createCell(36).setCellValue(dto.getApellidosProfesor());
			row.createCell(37).setCellValue(dto.getNombresProfesor());
			row.createCell(38).setCellValue(dto.getGeneroProfesor());
			row.createCell(39).setCellValue(dto.getTelefonoProfesor());
			row.createCell(40).setCellValue(dto.getCelularProfesor());
			row.createCell(41).setCellValue(dto.getCorreoProfesor());
			row.createCell(42).setCellValue(dto.getCodigoTrabajo());
			row.createCell(43).setCellValue(dto.getEstadoTrabajo());
			row.createCell(44).setCellValue(dto.getTituloTrabajo());
			row.createCell(45).setCellValue(dto.getLinkVideo());
			row.createCell(46).setCellValue(dto.getModalidadTrabajo());
			row.createCell(47).setCellValue(dto.getCategoriaTrabajo());
			row.createCell(48).setCellValue(dto.getNivelParticipacionTrabajo());
			row.createCell(49).setCellValue(dto.getEjesTematicos());
			row.createCell(50).setCellValue(dto.getNombreParticipante());
			row.createCell(51).setCellValue(dto.getApellidoPaternoParticipante());
			row.createCell(52).setCellValue(dto.getApellidoMaternoParticipante());
			row.createCell(53).setCellValue(dto.getTipoDocumentoParticipante());
			row.createCell(54).setCellValue(dto.getNroDocumentoParticipante());
			row.createCell(55).setCellValue(dto.getFechaNacimientoParticipante());
			row.createCell(56).setCellValue(dto.getGeneroParticipante());
			row.createCell(57).setCellValue(dto.getSeccionParticipante());
			row.createCell(58).setCellValue(dto.getNivelParticipante());
			row.createCell(59).setCellValue(dto.getGradoParticipante());
			row.createCell(60).setCellValue(dto.getNombreTutor());
			row.createCell(61).setCellValue(dto.getApellidoPaternoTutor());
			row.createCell(62).setCellValue(dto.getApellidoMaternoTutor());
			row.createCell(63).setCellValue(dto.getTipoDocumentoTutor());
			row.createCell(64).setCellValue(dto.getNroDocumentoTutor());
			row.createCell(65).setCellValue(dto.getTelefonoTutor());
			row.createCell(66).setCellValue(dto.getCorreoTutor());
			row.createCell(67).setCellValue(dto.getParentesco());
			row.createCell(68).setCellValue(dto.getNombreDocente());
			row.createCell(69).setCellValue(dto.getApellidoPaternoDocente());
			row.createCell(70).setCellValue(dto.getApellidoMaternoDocente());
			row.createCell(71).setCellValue(dto.getTipoDocumentoDocente());
			row.createCell(72).setCellValue(dto.getNrodocumentoDocente());
			row.createCell(73).setCellValue(dto.getTelefonoDocente());
			row.createCell(74).setCellValue(dto.getGeneroDocente());
			row.createCell(75).setCellValue(dto.getCorreoDocente());
			row.createCell(76).setCellValue(dto.getNotaRegional());
			row.createCell(77).setCellValue(dto.getPuestoRegional());
			row.createCell(78).setCellValue(dto.getNotaNacional());
			row.createCell(79).setCellValue(dto.getPuestoNacional());
			initRow++;
		}		
		
		List<DetalleEvaluacionReporteDto> lista = new ArrayList<>();		
		trabajosFinalesServ.listarhabilitados().forEach(obj->{			
			bandera_ods = false;
			bandera_anio = false;
			bandera_categoria = false;
			bandera_modalidad = false;
			bandera_nivel = false;
			
			if(ods!=-1) {
				if(obj.getProgramaeducativo().getDistrito().getOdsid().equals(ods))
					bandera_ods = true;
			}
			else {
				listaOds.forEach(objOds->{
					if(objOds.getId().equals(obj.getProgramaeducativo().getDistrito().getOdsid())) {
						bandera_ods = true;
					}
				});
			}
			/**/
			if(anio!=-1) {
				if(obj.getProgramaeducativo().getAnhio().equals(anio))
					bandera_anio = true;
				else
					bandera_anio = false;					
			}
			else {
				bandera_anio = true;
			}
			/**/
			if(categoria!=-1) {
				if(obj.getCategoriatrabajo().getId().equals(categoria))
					bandera_categoria = true;
				else
					bandera_categoria = false;
			}
			else {
				bandera_categoria = true;
			}
			
			if(modalidad!=-1) {
				if(obj.getModalidadtrabajo().getId().equals(modalidad))
					bandera_modalidad = true;
				else
					bandera_modalidad = false;
			}
			else {
				bandera_modalidad = true;
			}
			mi_nivel_participacion = "";
			if(nivel!=-1) {				
				trabajosFinalesParticipanteService.listar(obj.getId()).forEach(objn->{
					if(objn.getParticipante().getGradoestudiante().getNivelgradopartid().equals(nivel)) {
						mi_nivel_participacion = objn.getParticipante().getGradoestudiante().getNivelgradopartdesc();
						bandera_nivel = true;
					}						
				});
			}
			else {
				bandera_nivel = true;
			}
			
			if( bandera_ods && bandera_anio && bandera_categoria && bandera_modalidad && bandera_nivel && obj.getEnviado()==1) 			
			{
				DetalleEvaluacionReporteDto dto  = new DetalleEvaluacionReporteDto();
				dto.setAnio(obj.getProgramaeducativo().getAnhio());
				dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion() );
				dto.setCodigoie(obj.getProgramaeducativo().getCodmod());
				dto.setNombreie(obj.getProgramaeducativo().getNomie());
				dto.setAmbito(obj.getProgramaeducativo().getAmbito().getDescripcion());
				dto.setCodigoTrabajo(obj.getProgramaeducativo().getCodmod()+"_"+obj.getNumeracion());
				dto.setTituloTrabajo(obj.getTitulotrabajo());
				dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
				dto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
				
				nroEvaluadoresAsignados = 0;
				trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(obj.getId()).forEach(tf_ua->{
					nroEvaluadoresAsignados++;
				});
				
				dto.setCantidadEvaluadoresAsignados(nroEvaluadoresAsignados);
				
				dto.setPregunta1("");
				dto.setPregunta2("");
				dto.setPregunta3("");
				dto.setPregunta4("");
				dto.setPregunta5("");
				dto.setEresCebe("NO");
				
				if(mi_nivel_participacion.equals("")) {
					trabajosFinalesParticipanteService.listar(obj.getId()).forEach(objn->{
						mi_nivel_participacion = objn.getParticipante().getGradoestudiante().getNivelgradopartdesc();
					});
				}
				
				dto.setNivelParticipacion(mi_nivel_participacion);
				puntaje  = new ArrayList<Float>();
				
				evaluacionRepuestaServ.listaRubricaPuntajeDto(obj.getId()).forEach(er->{
					puntaje.add(er.getPuntaje());
				});
				
				for(int i=0;i<puntaje.size();i++) {
					switch(i) {
						case 0 : dto.setPregunta1(dosDecimales.format(puntaje.get(i))); break;
						case 1 : dto.setPregunta2(dosDecimales.format(puntaje.get(i))); break;
						case 2 : dto.setPregunta3(dosDecimales.format(puntaje.get(i))); break;
						case 3 : dto.setPregunta4(dosDecimales.format(puntaje.get(i))); break;
						case 4 : dto.setPregunta5(dosDecimales.format(puntaje.get(i))); break;
					}
				}		
				
				evaluacionRepuestaServ.getRespuestas(obj.getId()).forEach(objER->{
					if(objER.getTipo()==2) {
						questionarioRespuestaService.listarByQuestionario(objER.getPreguntaid()).forEach(objQR->{
							if(objQR.getQuestionario().getPregunta().toLowerCase().contains("cebe")) {
								if(objQR.getRespuesta().toLowerCase().contains("si")) {
									dto.setEresCebe("SI");
								}
							}
						});
					}
				});				
				
				dto.setNota(obj.getNota()!=null?obj.getNota().toString():"");
				dto.setPuesto(obj.getPuesto().toString());
				lista.add(dto);
			}					
		});
        
		String [] columns1 = {"AÑO","ODS","CODIGO II.EE","NOMBRE II.EE","AMBITO","Código de trabajo","Titulo de trabajo","Modalidad","Categoria","Nivel de participacion","Cantidad de evaluadores","Pregunta 1","Pregunta 2","Pregunta 3","Pregunta 4","Pregunta 5","Eres CEBE","Nota","Puesto"};
		Sheet sheet1 = workbook.createSheet("Detalle de la evaluacion Region");
		Row row1 = sheet1.createRow(0);
		
		row1.createCell(0).setCellValue("DATOS DE A II.EE");
		sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));/*1era celda , ultima celda, 1era columna, ultima columna*/		
		
		row1.createCell(10).setCellValue("Rubrica");
		sheet1.addMergedRegion(new CellRangeAddress(0, 0, 10, 15));
		
		row1.createCell(16).setCellValue("Cuestionario");
		
		row1 = sheet1.createRow(1);
		for(int i=0;i<columns1.length;i++) {
			Cell cell = row1.createCell(i);
			cell.setCellValue(columns1[i]);
		}
		
		int initRow1 = 2;
		for(DetalleEvaluacionReporteDto dto : lista) {
			row1 = sheet1.createRow(initRow1);
			row1.createCell(0).setCellValue(dto.getAnio());
			row1.createCell(1).setCellValue(dto.getOds());
			row1.createCell(2).setCellValue(dto.getCodigoie());
			row1.createCell(3).setCellValue(dto.getNombreie());
			row1.createCell(4).setCellValue(dto.getAmbito());
			row1.createCell(5).setCellValue(dto.getCodigoTrabajo());
			row1.createCell(6).setCellValue(dto.getTituloTrabajo());
			row1.createCell(7).setCellValue(dto.getModalidad());
			row1.createCell(8).setCellValue(dto.getCategoria());
			row1.createCell(9).setCellValue(dto.getNivelParticipacion());
			row1.createCell(10).setCellValue(dto.getCantidadEvaluadoresAsignados());
			row1.createCell(11).setCellValue(dto.getPregunta1());
			row1.createCell(12).setCellValue(dto.getPregunta2());
			row1.createCell(13).setCellValue(dto.getPregunta3());
			row1.createCell(14).setCellValue(dto.getPregunta4());
			row1.createCell(15).setCellValue(dto.getPregunta5());
			row1.createCell(16).setCellValue(dto.getEresCebe());
			row1.createCell(17).setCellValue(dto.getNota());	
			row1.createCell(18).setCellValue(dto.getPuesto());	
			initRow1 ++;
		}
		
		List<ResultadosRegionalesDto> listarr = new ArrayList<>();		
		trabajosFinalesServ.listarhabilitados().forEach(obj->{
			bandera_ods = false;
			bandera_anio = false;
			bandera_categoria = false;
			bandera_modalidad = false;
			bandera_nivel = false;
			
			if(ods!=-1) {
				if(obj.getProgramaeducativo().getDistrito().getOdsid().equals(ods))
					bandera_ods = true;
			}
			else {
				listaOds.forEach(objOds->{
					if(objOds.getId().equals(obj.getProgramaeducativo().getDistrito().getOdsid())) {
						banderaods = true;
					}
				});
			}
			/**/
			if(anio!=-1) {
				if(obj.getProgramaeducativo().getAnhio().equals(anio))
					bandera_anio = true;
				else
					bandera_anio = false;					
			}
			else {
				bandera_anio = true;
			}
			/**/
			if(categoria!=-1) {
				if(categoria == obj.getCategoriatrabajo().getId())
					bandera_categoria=true;
				else
					bandera_categoria=false;
			}
			else {
				bandera_categoria = true;
			}
			/**/
			if(modalidad!=-1) {
				if(modalidad == obj.getModalidadtrabajo().getId())
					bandera_modalidad = true;
				else
					bandera_modalidad = false;
			}
			else {
				bandera_modalidad = true;
			}
			
			peNivelParticipacion="";
			if(nivel!=-1) {
				List<Participante> listaParticipante = participanteServ.listarhabilitados(obj.getProgramaeducativo().getId());
				listaParticipante.forEach(objParticipante->{
					if(objParticipante.getGradoestudiante().getNivelgradopartid()==nivel) {
						bandera_nivel = true;
						peNivelParticipacion = objParticipante.getGradoestudiante().getNivelgradopartdesc();
					}
				});
			}
			else {
				bandera_nivel = true;
			}			
			
			if( bandera_ods && bandera_anio && bandera_categoria && bandera_modalidad && bandera_nivel && obj.getEnviado()==1) 			
			{
				ResultadosRegionalesDto rrdto = new ResultadosRegionalesDto();
				rrdto.setAnio(obj.getAnio());
				rrdto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
				rrdto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
				rrdto.setNivelParticipacion(peNivelParticipacion);
				rrdto.setPuesto(obj.getPuesto().toString());
				rrdto.setCodigoIiee(obj.getProgramaeducativo().getCodmod());
				rrdto.setNombreIiee(obj.getProgramaeducativo().getNomie());
				rrdto.setAmbito(obj.getProgramaeducativo().getAmbito().getDescripcion());
				rrdto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
				rrdto.setNombreTrabajo(obj.getNombre());
				
				peParticipantes = "";
				peGeneroParticipante = "";
				trabajosFinalesParticipanteService.listar(obj.getId()).forEach(objTF->{
					peParticipantes += objTF.getParticipante().getNombreestudiante() + " "+objTF.getParticipante().getAppaternoestudiante()+ " " + objTF.getParticipante().getApmaternoestudiante()+",";
					peGeneroParticipante += objTF.getParticipante().getGeneroestudiante().getDescripcion()+",";
				});
				if(peParticipantes.length()>0) {
					peParticipantes = peParticipantes.substring(0,peParticipantes.length()-1);
					peGeneroParticipante = peGeneroParticipante.substring(0, peGeneroParticipante.length()-1);
				}	
				rrdto.setParticipante(peParticipantes);
				rrdto.setGenero(peGeneroParticipante);
				rrdto.setNotaFinal(obj.getNota()!=null?obj.getNota().toString():"");
				listarr.add(rrdto);
			}			
		});
		
		String [] columns2 = {"AÑO","ODS","CATEGORIA","NIVEL DE PARTICIPACION","PUESTO","CODIGO DE II.EE","NOMBRE DE II.EE","AMBITO DE II.EE","MODALIDAD","NOMBRE DE TRABAJO","PARTICIPANTES","GENERO","NOTA FINAL"};
		Sheet sheet2 = workbook.createSheet("Resultados regionales");
		Row row2 = sheet2.createRow(0);
		
		row2.createCell(0).setCellValue("GANADORES");
		sheet2.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));/*1era celda , ultima celda, 1era columna, ultima columna*/		
		
		row2 = sheet2.createRow(1);
		for(int i=0;i<columns2.length;i++) {
			Cell cell = row2.createCell(i);
			cell.setCellValue(columns2[i]);
		}
		
		int initRow2 = 2;
		for(ResultadosRegionalesDto dto : listarr) {
			row2 = sheet2.createRow(initRow2);
			row2.createCell(0).setCellValue(dto.getAnio());
			row2.createCell(1).setCellValue(dto.getOds());
			row2.createCell(2).setCellValue(dto.getCategoria());
			row2.createCell(3).setCellValue(dto.getNivelParticipacion());
			row2.createCell(4).setCellValue(dto.getPuesto());
			row2.createCell(5).setCellValue(dto.getCodigoIiee());
			row2.createCell(6).setCellValue(dto.getNombreIiee());
			row2.createCell(7).setCellValue(dto.getAmbito());
			row2.createCell(8).setCellValue(dto.getModalidad());
			row2.createCell(9).setCellValue(dto.getNombreTrabajo());
			row2.createCell(10).setCellValue(dto.getParticipante());
			row2.createCell(11).setCellValue(dto.getGenero());
			row2.createCell(12).setCellValue(dto.getNotaFinal());
			initRow2 ++;
		}
		
		String[] ColumnaResultados = {"AÑO","ODS","CATEGORIA","NIVEL DE PARTICIPACIÓN","PUESTO","CODIGO DE II.EE","NOMBRE DE II.EE","AMBITO DE II.EE","MODALIDAD","NOMBRE DEL TRABAJO","PARTICIPANTES","GENERO","NOTA FINAL","DOCENTE","CELULAR DOCENTE"};
		Sheet hojaResultados = workbook.createSheet("Resultados");
		
		Row row1Resultados = hojaResultados.createRow(0);
		
		row1Resultados.createCell(0).setCellValue("");
		hojaResultados.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));/*1era celda , ultima celda, 1era columna, ultima columna*/		
		
		row1Resultados.createCell(4).setCellValue("GANADORES");
		hojaResultados.addMergedRegion(new CellRangeAddress(0, 0, 4, 14));
		
		row1Resultados = hojaResultados.createRow(1);
		for(int i=0;i<ColumnaResultados.length;i++) {
			Cell cell = row1Resultados.createCell(i);
			cell.setCellValue(ColumnaResultados[i]);
		}
		
		List<ResultadosGanadoresDto> listaResultadosGanadores  =new ArrayList<ResultadosGanadoresDto>();		
		
		trabajosFinalesServ.listarhabilitados().forEach(obj->{
			
			if(obj.getEnviado()==1) {
				
				bandera_ods = false;
				bandera_anio = false;	
				bandera_categoria=false;
				bandera_modalidad = false;
				bandera_nivel = false;
				
				if(ods!=-1) {
					if(obj.getProgramaeducativo().getDistrito().getOdsid().equals(ods))
						bandera_ods = true;
				}
				else {
					listaOds.forEach(objOds->{
						if(objOds.getId().equals(obj.getProgramaeducativo().getDistrito().getOdsid()))
							bandera_ods = true;
					});
				}
				
				if(anio!=-1) {
					if(obj.getProgramaeducativo().getAnhio().equals(anio))
						bandera_anio = true;
				}
				else {
					bandera_anio = true;
				}
				
				if(categoria!=-1) {
					if(categoria == obj.getCategoriatrabajo().getId())
						bandera_categoria=true;
				}
				else {
					bandera_categoria = true;
				}
				
				if(modalidad!=-1) {
					if(modalidad == obj.getModalidadtrabajo().getId())
						bandera_modalidad = true;
				}
				else {
					bandera_modalidad = true;
				}
				
				peNivelParticipacion="";
				if(nivel!=-1) {
					List<Participante> listaParticipante = participanteServ.listarhabilitados(obj.getProgramaeducativo().getId());
					listaParticipante.forEach(objParticipante->{
						if(objParticipante.getGradoestudiante().getNivelgradopartid()==nivel) {
							bandera_nivel = true;
							peNivelParticipacion = objParticipante.getGradoestudiante().getNivelgradopartdesc();
						}
					});
				}
				else {
					bandera_nivel = true;
				}			
				
				if( bandera_ods && bandera_anio && bandera_categoria && bandera_modalidad && bandera_nivel) 			
				{
					participantes = "";
					generoParticipante = "";
					ResultadosGanadoresDto dto = new ResultadosGanadoresDto();
					switch(obj.getPuesto()) {
						case  1 :  
							dto.setAnio(obj.getAnio());
							dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
							dto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
							if(peNivelParticipacion.equals("")) {
								trabajosFinalesParticipanteService.listar(obj.getId()).forEach(objTF->{
									peNivelParticipacion = objTF.getParticipante().getGradoestudiante().getNivelgradopartdesc();
								});
							}		
							dto.setNivelParticipacion(peNivelParticipacion);
							dto.setPuesto("PRIMER PUESTO");
							dto.setCodigoIiee(obj.getProgramaeducativo().getCodmod());
							dto.setNombreIiee(obj.getProgramaeducativo().getNomie());
							dto.setAmbitoIiee(obj.getProgramaeducativo().getAmbito().getDescripcion());
							dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
							dto.setNombreTrabajo(obj.getNombre());
							trabajosFinalesParticipanteService.listar(obj.getId()).forEach(tfp->{
								participantes += tfp.getParticipante().getNombreestudiante() + " " + tfp.getParticipante().getAppaternoestudiante()+ " " + tfp.getParticipante().getApmaternoestudiante() + ",";
								generoParticipante += tfp.getParticipante().getGeneroestudiante().getDescripcion() + ",";
							});
							if(participantes.length()>0) {
								participantes = participantes.substring(0, participantes.length()-1);
								generoParticipante  = generoParticipante.substring(0, generoParticipante.length()-1);
							}							
							dto.setParticipantes(participantes);
							dto.setGenero(generoParticipante);
							dto.setNotaFinal(obj.getNota());
							dto.setDocente(obj.getNombre() + " "  + obj.getAppaterno() + " " + obj.getApmaterno());
							dto.setCelularDocente(obj.getTelefono());	
							listaResultadosGanadores.add(dto);
							break;
						case  2 :  
							dto.setAnio(obj.getAnio());
							dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
							dto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
							if(peNivelParticipacion.equals("")) {
								trabajosFinalesParticipanteService.listar(obj.getId()).forEach(objTF->{
									peNivelParticipacion = objTF.getParticipante().getGradoestudiante().getNivelgradopartdesc();
								});
							}		
							dto.setNivelParticipacion(peNivelParticipacion);
							dto.setPuesto("SEGUNDO PUESTO");
							dto.setCodigoIiee(obj.getProgramaeducativo().getCodmod());
							dto.setNombreIiee(obj.getProgramaeducativo().getNomie());
							dto.setAmbitoIiee(obj.getProgramaeducativo().getAmbito().getDescripcion());
							dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
							dto.setNombreTrabajo(obj.getNombre());
							trabajosFinalesParticipanteService.listar(obj.getId()).forEach(tfp->{
								participantes += tfp.getParticipante().getNombreestudiante() + " " + tfp.getParticipante().getAppaternoestudiante()+ " " + tfp.getParticipante().getApmaternoestudiante() + ",";
								generoParticipante += tfp.getParticipante().getGeneroestudiante().getDescripcion() + ",";
							});
							if(participantes.length()>0) {
								participantes = participantes.substring(0, participantes.length()-1);
								generoParticipante  = generoParticipante.substring(0, generoParticipante.length()-1);
							}							
							dto.setParticipantes(participantes);
							dto.setGenero(generoParticipante);
							dto.setNotaFinal(obj.getNota());
							dto.setDocente(obj.getNombre() + " "  + obj.getAppaterno() + " " + obj.getApmaterno());
							dto.setCelularDocente(obj.getTelefono());
							listaResultadosGanadores.add(dto);
							break;
						case  3 :  
							dto.setAnio(obj.getAnio());
							dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
							dto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
							if(peNivelParticipacion.equals("")) {
								trabajosFinalesParticipanteService.listar(obj.getId()).forEach(objTF->{
									peNivelParticipacion = objTF.getParticipante().getGradoestudiante().getNivelgradopartdesc();
								});
							}		
							dto.setNivelParticipacion(peNivelParticipacion);
							dto.setPuesto("TERCER PUESTO");
							dto.setCodigoIiee(obj.getProgramaeducativo().getCodmod());
							dto.setNombreIiee(obj.getProgramaeducativo().getNomie());
							dto.setAmbitoIiee(obj.getProgramaeducativo().getAmbito().getDescripcion());
							dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
							dto.setNombreTrabajo(obj.getNombre());
							trabajosFinalesParticipanteService.listar(obj.getId()).forEach(tfp->{
								participantes += tfp.getParticipante().getNombreestudiante() + " " + tfp.getParticipante().getAppaternoestudiante()+ " " + tfp.getParticipante().getApmaternoestudiante() + ",";
								generoParticipante += tfp.getParticipante().getGeneroestudiante().getDescripcion() + ",";
							});
							if(participantes.length()>0) {
								participantes = participantes.substring(0, participantes.length()-1);
								generoParticipante  = generoParticipante.substring(0, generoParticipante.length()-1);
							}							
							dto.setParticipantes(participantes);
							dto.setGenero(generoParticipante);
							dto.setNotaFinal(obj.getNota());
							dto.setDocente(obj.getNombre() + " "  + obj.getAppaterno() + " " + obj.getApmaterno());
							dto.setCelularDocente(obj.getTelefono());
							listaResultadosGanadores.add(dto);
							break;
					}
				}
			}
		});
		
		int initRow3 = 2;
		for(ResultadosGanadoresDto dto : listaResultadosGanadores) {
			row1Resultados = hojaResultados.createRow(initRow3);
			row1Resultados.createCell(0).setCellValue(dto.getAnio());
			row1Resultados.createCell(1).setCellValue(dto.getOds());
			row1Resultados.createCell(2).setCellValue(dto.getCategoria());
			row1Resultados.createCell(3).setCellValue(dto.getNivelParticipacion());
			row1Resultados.createCell(4).setCellValue(dto.getPuesto());
			row1Resultados.createCell(5).setCellValue(dto.getCodigoIiee());
			row1Resultados.createCell(6).setCellValue(dto.getNombreIiee());
			row1Resultados.createCell(7).setCellValue(dto.getAmbitoIiee());
			row1Resultados.createCell(8).setCellValue(dto.getModalidad());
			row1Resultados.createCell(9).setCellValue(dto.getNombreTrabajo());
			row1Resultados.createCell(10).setCellValue(dto.getParticipantes());
			row1Resultados.createCell(11).setCellValue(dto.getGenero());
			row1Resultados.createCell(12).setCellValue(dto.getNotaFinal());
			row1Resultados.createCell(12).setCellValue(dto.getDocente());
			row1Resultados.createCell(12).setCellValue(dto.getCelularDocente());
			initRow3 ++;
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
		
		listaOds = new ArrayList<>();
		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		if(tipousuarioid.equals(2)) {
			String usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
		}
		else {
			listaOds = odsserv.listarAll();
		}
		
		String role6, role7, role8, role9;
		String strRol = "";
		
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
			strRol = "Todos";
		}else {
			if(rol.equals("6")) { role6 = "1"; strRol = "Comite Técnico";}
			else role6 = "";
				
			if(rol.equals("7")) {role7 = "1"; strRol = "Comite Evaluador";}
			else role7 = "";
			
			if(rol.equals("8")) {role8 = "1"; strRol = "Auspiciador";}
			else role8 = "";
			
			if(rol.equals("9")) {role9 = "1"; strRol = "Aliado";}
			else role9 = "";
		}
		
		if(tipousuarioid == 0){
			String obsSesion = ses.getAttribute("odsid").toString();
			ods = obsSesion;
		}
		
		List<UsuarioAlianza> listaUsu =	new ArrayList<UsuarioAlianza>();
		usuarioAlianzaServ.listaUsuarioFiltro(ods, anio, estado, role6, role7, role8, role9).forEach(objua->{
			bandera_ods = false;
			listaOds.forEach(objOds->{
				if(objOds.getId().equals(objua.getOds().getId())) {
					bandera_ods = true;
				}
			});
			if(bandera_ods) {
				listaUsu.add(objua);
			}
		});
		List<Auspicio> listaAuspicio = new ArrayList<>();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
		
		/* Get access to HSSFCellStyle */
		HSSFCellStyle my_style = workbook.createCellStyle();
		HSSFFont my_font = workbook.createFont();
        my_font.setBold(true);
        my_style.setFont(my_font);
        
        /* Get access to HSSFCellStyle */
		HSSFCellStyle my_style_Titulo = workbook.createCellStyle();
		my_style_Titulo.setAlignment(HorizontalAlignment.CENTER);
		
		HSSFCellStyle my_style_Cabecera1 = workbook.createCellStyle();
		HSSFFont my_font2 = workbook.createFont();
        my_font2.setBold(true);
        my_style_Cabecera1.setFont(my_font2);
        my_style_Cabecera1.setAlignment(HorizontalAlignment.CENTER);
        

		String [] columns = {"Nº","AÑO","ODS","CATEGORIA","ENTIDAD","DIRECCION","COMITE TECNICO","COMITE EVALUADOR","AUSPICIADOR","ALIADO","ESTADO",
				"¿Cúantos contactos desea agregar?","Apellido paterno","Apellido materno","Nombres","Tipo de documento","Nro de documento","N° de telefono 1",
				"N° de telefono 2","Correo institucional","Cargo","Apellido paterno","Apellido materno","Nombres","Correo institucional","Cargo","Usuario",
				"Contraseña","Enviar oficio","Nro oficio","Fecha de oficio","Monto total"};
		
		Sheet sheet = workbook.createSheet("Alianza Estrategica");
		
		/*Linea 1*/
		Row rowline1 = sheet.createRow(0);
		Cell cell1 = rowline1.createCell(0);
		cell1.setCellValue("ProgramaEducativo");
		cell1.setCellStyle(my_style_Titulo);
		
		//rowline1.createCell(0).setCellValue("Reporte Alianza Estratégica");
		
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,columns.length-1));
		
		/*Linea 1*/
		Row rowline2 = sheet.createRow(1);
		
		String strOds = "[ODS: "+(ods.equals("") ? "Todos" :  odsserv.byOds(Integer.parseInt(ods)).getDescripcion() )+"]";
		String strAnio= "[Año de inscripción: "+(anio.equals("") ? "Todos" :  anio )+"]";
		strRol = "[Rol: "+strRol+"]";
		String strEstado = "[Estado: "+(estado.equals("") ? "Todos" :  (estado.equals("1") ? "Activo" : "Inactivo"))+"]";
		
		Cell cell2 = rowline2.createCell(1);
		cell2.setCellValue(strOds+"            "+strAnio+"            "+strRol+"            "+strEstado);
		cell2.setCellStyle(my_style_Titulo);
		
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,columns.length-1));
		
		/*linea 2*/
		Row rowline3 = sheet.createRow(2);
		Cell cell31 = rowline3.createCell(11);
		cell31.setCellValue("DATOS DEL CONTACTO");
		cell31.setCellStyle(my_style_Cabecera1);
		
		sheet.addMergedRegion(new CellRangeAddress(2,2,11,20));
		
		Cell cell32 = rowline3.createCell(21);
		cell32.setCellValue("Datos de la Autoridad/Representante");
		cell32.setCellStyle(my_style_Cabecera1);
		
		sheet.addMergedRegion(new CellRangeAddress(2,2,21,30));
		
		Cell cell33 = rowline3.createCell(31);
		cell33.setCellValue("Datos del auspicio");
		cell33.setCellStyle(my_style_Cabecera1);
		
		//sheet.addMergedRegion(new CellRangeAddress(2,2,31,31));
		
		
		/*Contenido de la lista - Cabecera*/
		Row row = sheet.createRow(3);
		for(int i=0;i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(my_style);
		}
		
		int initRow = 4;
		
		for (UsuarioAlianza obj : listaUsu) {
			int i = 0;
			
			row = sheet.createRow(initRow);
			row.createCell(i++).setCellValue(initRow-2); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getAnio()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getOds().getDescripcion()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getCategoria()!=null?obj.getCategoria().getDescripcion():""); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getEntidad()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getDireccion()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getComitetecnico().equals("1") ? "SI" : "NO"); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getComiteevaluador().equals("1") ? "SI" : "NO"); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getAuspiciador().equals("1") ? "SI" : "NO"); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getAliado().equals("1") ? "SI" : "NO"); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getEstado().equals("1") ? "ACTIVO" : "INACTIVO"); sheet.autoSizeColumn(i);
			
			row.createCell(i++).setCellValue(obj.getNumcontaccto()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getApepatcontacto()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getApematcontacto()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getNombrecontacto()); sheet.autoSizeColumn(i);
			if(obj.getTipodocumento() != null) {
				row.createCell(i++).setCellValue(obj.getTipodocumento().getDescripcion()); sheet.autoSizeColumn(i);
			}else {
				row.createCell(i++).setCellValue(""); sheet.autoSizeColumn(i);
			}
			
			row.createCell(i++).setCellValue(obj.getNumdocumento()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getTelefonouno()); sheet.autoSizeColumn(i);
			
			row.createCell(i++).setCellValue(obj.getTelefonodos()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getCorreocontato()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getCargocontacto()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getApepatautoridad()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getApematautoridad()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getNombresautoridad()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getCorreoautoridad()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getCargoautoridad()); sheet.autoSizeColumn(i);
			row.createCell(i++).setCellValue(obj.getUsuarioautoridad()); sheet.autoSizeColumn(i);
			
			row.createCell(i++).setCellValue(obj.getPasswordautoridad()); sheet.autoSizeColumn(i);
			if(obj.getEnviaroficio() != null) {
				row.createCell(i++).setCellValue(obj.getEnviaroficio().equals("1") ? "SI" : "NO"); sheet.autoSizeColumn(i);
			}else {
				row.createCell(i++).setCellValue(""); sheet.autoSizeColumn(i);
			}
			
			row.createCell(i++).setCellValue(obj.getNumoficio()); sheet.autoSizeColumn(i);
			if(obj.getFecha_oficio() != null) {
				row.createCell(i++).setCellValue(dateFormatFecha.format(obj.getFecha_oficio())); sheet.autoSizeColumn(i);
			}else {
				row.createCell(i++).setCellValue(""); sheet.autoSizeColumn(i);
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
		
		
		String [] columns1 = {"ODS","N°","Descripcion","Cantidad","Monto unitario","Monto total"};
		Sheet sheet1 = workbook.createSheet("Auspicios");
		Row row1 = sheet1.createRow(0);
		for(int i=0;i<columns1.length;i++) {
			Cell cell = row1.createCell(i);
			cell.setCellValue(columns1[i]);
			cell.setCellStyle(my_style);
		}
		
		int initRow1 = 1;
		for(Auspicio auspicio : listaAuspicio) {
			int i = 0;
			
			row1 = sheet1.createRow(initRow1);
			row1.createCell(i++).setCellValue(auspicio.getOds());
			row1.createCell(i++).setCellValue(initRow1);
			row1.createCell(i++).setCellValue(auspicio.getDescripcion());
			//row1.createCell(i++).setCellValue(""/*auspicio.getTipodocumento().getDescripcion()*/);
			row1.createCell(i++).setCellValue(auspicio.getCantidad());
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
		
		fecha_archivo = dateFormat.format(date) + hourFormat.format(date);
		
		headers.add("Content-Disposition", "attachment; filename=Lista_alianzaestrategica_"+fecha_archivo+".csv");
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		
	}
	
}
