package com.progeduc.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.progeduc.componente.Ldap;
import com.progeduc.dto.AsignacionDto;
import com.progeduc.dto.AsignarEvaluadorDto;
import com.progeduc.dto.AsignarEvaluadoresDto;
import com.progeduc.dto.CategoriaNivelParticipacionByOds;
import com.progeduc.dto.ClaveValor;
import com.progeduc.dto.ConcursoDto;
import com.progeduc.dto.DataEtapaNacionalDto;
import com.progeduc.dto.DataTrabajosPermisos;
import com.progeduc.dto.DetalleEvaluacionReporteDto;
import com.progeduc.dto.DetalleUsuarioAlianzaEstrategica;
import com.progeduc.dto.EvaluacionDto;
import com.progeduc.dto.EvaluacionRubricaQuestionarioDto;
import com.progeduc.dto.EvaluadorDto;
import com.progeduc.dto.ListaDocente;
import com.progeduc.dto.ListaDocenteInscritos;
import com.progeduc.dto.ListaTrabajosFinalesPendientes;
import com.progeduc.dto.ListaparticipanteDto;
import com.progeduc.dto.ListaparticipantetrabajoDto;
import com.progeduc.dto.ListatrabajosfinalesDto;
import com.progeduc.dto.OdsFinalizarDto;
import com.progeduc.dto.ParticipanteVerDto;
import com.progeduc.dto.ResultadosGanadoresDto;
import com.progeduc.dto.TrabajoCategoriaModalidadAddDto;
import com.progeduc.dto.TrabajoCategoriaModalidadDto;
import com.progeduc.dto.TrabajofinalesEnviadoDto;
import com.progeduc.dto.TrabajosFinalesConcursoDto;
import com.progeduc.dto.TrabajosFinalizados;
import com.progeduc.dto.TrabajosfinalesParticipanteDto;
import com.progeduc.dto.UsuarioAlianzaDto;
import com.progeduc.dto.trabajoEvaluadoDto;
import com.progeduc.model.Aperturaranio;
import com.progeduc.model.Auspicio;
import com.progeduc.model.Categoriatrabajo;
import com.progeduc.model.CerrarEtapaNacional;
import com.progeduc.model.CerrarOds;
import com.progeduc.model.Docente;
import com.progeduc.model.Docentetutor;
import com.progeduc.model.Estadotrabajo;
import com.progeduc.model.Evaluacion;
import com.progeduc.model.EvaluacionResultado;
import com.progeduc.model.EvaluacionResultadoNacional;
import com.progeduc.model.Gradoparticipante;
import com.progeduc.model.Ods;
import com.progeduc.model.Participante;
import com.progeduc.model.Postulacionconcurso;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.TipoAuspicio;
import com.progeduc.model.Trabajosfinales;
import com.progeduc.model.TrabajosfinalesParticipante;
import com.progeduc.model.TrabajosfinalesUsuarioAlianza;
import com.progeduc.model.TrabajosfinalesUsuarioAlianzaNacional;
import com.progeduc.model.Usuario;
import com.progeduc.model.UsuarioAlianza;
import com.progeduc.model.UsuarioLdap;
import com.progeduc.model.UsuarioOds;
import com.progeduc.service.IAperturaranioService;
import com.progeduc.service.IAuspicioService;
import com.progeduc.service.ICategoriatrabajoService;
import com.progeduc.service.ICerrarNacionalService;
import com.progeduc.service.ICerrarOdsService;
import com.progeduc.service.IDistritoService;
import com.progeduc.service.IDocenteService;
import com.progeduc.service.IDocentetutorService;
import com.progeduc.service.IEvaluacionRespuestaNacionalService;
import com.progeduc.service.IEvaluacionRespuestaService;
import com.progeduc.service.IEvaluacionService;
import com.progeduc.service.IGeneroprofService;
import com.progeduc.service.IGradoparticipanteService;
import com.progeduc.service.IOdsService;
import com.progeduc.service.IParticipanteService;
import com.progeduc.service.IPostulacionconcursoService;
import com.progeduc.service.IProgramaeducativoService;
import com.progeduc.service.IQuestionarioRespuestaService;
import com.progeduc.service.ITipoAuspicioService;
import com.progeduc.service.ITrabajosfinalesParticipanteService;
import com.progeduc.service.ITrabajosfinalesService;
import com.progeduc.service.ITrabajosfinales_UsuarioAlianzaNacionalService;
import com.progeduc.service.ITrabajosfinales_UsuarioAlianzaService;
import com.progeduc.service.IUsuarioAlianzaService;
import com.progeduc.service.IUsuarioOdsService;
import com.progeduc.service.IUsuarioService;
import com.progeduc.service.impl.UploadFileService;

@RestController
@RequestMapping("")
public class ConcursoeducativoController {
	
	
	private static final Logger log = LoggerFactory.getLogger(ConcursoeducativoController.class);
	
	@Autowired
	IProgramaeducativoService progeducService;
	
	@Autowired
	IAperturaranioService aperturaranioService;
	
	@Autowired
	IParticipanteService participanteService;
	
	@Autowired
	IDocenteService docenteService;
	
	@Autowired
	private IPostulacionconcursoService postulacionconcursoServ;
	
	@Autowired
	private IGradoparticipanteService gradoparticipanteServ;
	
	@Autowired
	private UploadFileService uploadfile;
	
	@Autowired
	private IOdsService odsserv;
	
	@Autowired
	private IDistritoService distServ;
	
	@Autowired
	private ITrabajosfinalesService trabajosfinalesServ;
	
	@Autowired
	private ITrabajosfinalesParticipanteService trabajosfinalesparticipanteServ;
	
	@Autowired
	private IEvaluacionService evaluacionServ;	
	
	@Autowired
	IUsuarioOdsService usuarioodsServ;
	
	@Autowired
	private IUsuarioAlianzaService usuAlianzaserv;
	
	@Autowired
	private IAuspicioService auspicioServ;
	
	@Autowired
	private ITrabajosfinales_UsuarioAlianzaService trabajosFinales_UsuarioAlianzaServ;
	
	@Autowired
	IUsuarioService usuarioServ;
	
	@Autowired
	IEvaluacionService evaluacionService;
	
	@Autowired
	private IEvaluacionRespuestaService evaluacionRespuestaServ;
	
	@Autowired
	private ITipoAuspicioService tipoAuspicioServ;
	
	@Autowired
	private IDocentetutorService docentetutorService;
	
	@Autowired
	private ICerrarOdsService cerrarOdsServ;
	
	@Autowired
	private IEvaluacionRespuestaService evaluacionRepuestaServ;
	
	@Autowired
	private IQuestionarioRespuestaService questionarioRespuestaServ;
	
	@Autowired
	private IGeneroprofService generoprofserv;
	
	@Autowired
	IUsuarioOdsService usuarioodsService; 
	
	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
<<<<<<< HEAD
	private ITrabajosfinalesService trabajosFinalesServ;
=======
	private ICategoriatrabajoService categoriaSserv;
	
	@Autowired
	private ICerrarNacionalService cerrarNacionalSserv;
	
	@Autowired
	private ITrabajosfinales_UsuarioAlianzaNacionalService trabajosFinales_UsuarioAlianzaNacionalServ;
	
	@Autowired
	private IEvaluacionRespuestaNacionalService evaluacionRespuestaNacionalServ;
>>>>>>> a0041e797b274b58e2d5efc2267315702af947d9
	
	ListaparticipanteDto dto;	
	ListatrabajosfinalesDto dtotf;			
	ListaparticipantetrabajoDto ptdto;	
	ListaDocenteInscritos listadocentesinscritos;
	ListaTrabajosFinalesPendientes listaTrabajosFinalesPendientes;
	String miparticipante = "";	
	boolean banderaUpdate,banderaods;
	Mail mail;	
	String msj2;
	UsuarioLdap usuarioldap = null;
	String nivelparticipacion;
	Integer idnivelparticipacion, nro_evidencias;
	String estado, texto_evidencias;
	String registro_validar;
	String nivel_participante = "";
	Integer evaluadores_asignados = 0;
	String rol_entidad,entidad;
	String nombre_apellido_evaluador;
	float totalNota = 0.0f;
	Integer idTrab = 0;
	Integer rpta;
	Integer banderaParticipanteSubidoTrabajo;
	int contador_iguales;
	int resultado;
	private List<Integer> listaIdParticipante;
	private List<Integer> listaIdDto;
	Integer max_numeracion = 0;
	boolean verifica;
	boolean banderaOds;
	int estado_fuera_plazo=0;
	String ejesTematicos="";
	boolean bandera;
	String mi_ods,mi_modalidad,mi_estado,mi_categoria,mi_nivel_participacion,mi_nombreie,mi_puesto;
	Integer mi_anio;
	Integer nroEvaluadoresAsignados;
	int indice;
	DecimalFormat dosDecimales = new DecimalFormat("##.00");
	List<Float> puntaje;
	List<Ods> listaOds;
	boolean bandera_ods ,bandera_anio ,bandera_categoria,bandera_modalidad;
	String participantes,generoParticipante,peNivelParticipacion;
	
	@PostMapping(value="/registrarconcurso")
	public String registrarconcurso(@Valid @RequestBody Postulacionconcurso dto)  {
		
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		dto.setFecha_registro(ts);
		
		Postulacionconcurso pc = postulacionconcursoServ.registrar(dto);
		String respuesta="0";
		if(pc !=null) 
			respuesta = aperturaranioService.buscar(pc.getAnio()).getNombreconcurso();
		return respuesta;
	}
	
	public boolean verifivaExisteParticipanteTrabajo(Integer categoria, Integer modalidad, TrabajosfinalesParticipanteDto mi_dto,Integer idpe) {
		
		contador_iguales = 0;
		resultado = 0;
		verifica = true;
		Integer valor;
		if(modalidad == 1) {
		
			listaIdDto = new ArrayList<Integer>();
			mi_dto.getListaparticipante().forEach(obj->{
	 			listaIdDto.add(obj.getId());
	 		});
			valor =  listaIdDto.get(0);
			listaIdParticipante = new ArrayList<Integer>();
			trabajosfinalesServ.BuscarCategoriaModalidad(categoria, modalidad,idpe).forEach(tf->{
				trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(obj1->{					
					if(obj1.getParticipante().getId().equals(valor)) {
						verifica = false;
					}
				});
			});
		}
		else{
			listaIdDto = new ArrayList<Integer>();
			mi_dto.getListaparticipante().forEach(obj->{
	 			listaIdDto.add(obj.getId());
	 		});		 		
			listaIdParticipante = new ArrayList<Integer>();
			trabajosfinalesServ.BuscarCategoriaModalidad(categoria, modalidad,idpe).forEach(tf->{
				contador_iguales = 0;
				if(verifica) {
					trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(obj1->{
						for(int i=0;i<listaIdDto.size();i++) {
							if(listaIdDto.get(i).equals(obj1.getParticipante().getId())) {
								contador_iguales++;
								i = listaIdDto.size();
							}
						}						
					});
					if(contador_iguales == listaIdDto.size()){
						verifica = false;
					}		
				}
			});
		}
		return verifica;
	}
	
	@PostMapping(value = "/savetrabajosfinalesparticipante")
	public Integer savetrabajosfinalesparticipante(@Valid @RequestBody TrabajosfinalesParticipanteDto dto,HttpSession ses){ 
		
		rpta = 0;
		if( ses.getAttribute("usuario")==null)
			return -100;		
		Integer categoria_dto = dto.getTrabajosfinales().getCategoriatrabajo().getId();
		Integer modalidad_dto = dto.getTrabajosfinales().getModalidadtrabajo().getId();
		
		String codmod = ses.getAttribute("usuario").toString();		
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		dto.getTrabajosfinales().setFecha_registro(ts);
		dto.getTrabajosfinales().setAnio(ts.toLocalDateTime().getYear());		
		
		dto.getTrabajosfinales().setProgramaeducativo(pe);
		
		if(dto.getTrabajosfinales().getId() == null) { /*cuando registra*/
			if(! verifivaExisteParticipanteTrabajo(categoria_dto,modalidad_dto,dto,pe.getId())) {
				return -50;
			}
			max_numeracion = trabajosfinalesServ.maxNumeracion(pe.getId());
			if (max_numeracion == null)
				dto.getTrabajosfinales().setNumeracion(1);
			else
				dto.getTrabajosfinales().setNumeracion(max_numeracion + 1);
		}
		else { /*cuando actualiza*/
			dto.getTrabajosfinales().setNumeracion(trabajosfinalesServ.getNumeracion(dto.getTrabajosfinales().getId()));
		}
		Trabajosfinales tf = trabajosfinalesServ.saveTrabajofinaParticipante(dto);
		if( tf !=null) {
			resultado = tf.getId();
		}	
		else {
			resultado = 0;
		}
		return resultado;
	}
	
	@GetMapping(value="/listargradopornivel/{id}")
	public ResponseEntity<List<Gradoparticipante>> listargradopornivel(@PathVariable("id") Integer id){
		return new ResponseEntity<List<Gradoparticipante>>(gradoparticipanteServ.listargradopornivel(id),HttpStatus.OK);
	}
	
	@GetMapping(value="/eliminarparticipanteid/{id}")
	public Integer eliminarparticipanteid(@PathVariable("id") Integer id) {
		return participanteService.updateestado(id, 0);
	}
	
	@GetMapping(value="/eliminarevaluacionid/{id}")
	public Integer eliminarevaluacionid(@PathVariable("id") Integer id) {
		return evaluacionServ.updateestado(id, 0);
	}
	
	@GetMapping(value="/eliminartrabajoid/{id}")
	public Integer eliminartrabajoid(@PathVariable("id") Integer id,HttpSession ses) {
		if( ses.getAttribute("usuario")==null)
			return -100;
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		return trabajosfinalesServ.updateestado(id, 0,pe.getId());
	}
	
	@GetMapping(value="/enviartrabajoid/{id}")
	public Integer enviartrabajoid(@PathVariable("id") Integer id,HttpSession ses) {
		
		if( ses.getAttribute("usuario")==null)
			return -100;
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		return trabajosfinalesServ.updateenviado(id, 1,pe.getId());
	}
	
	@GetMapping(value="/eliminardocenteid/{id}")
	public Integer eliminardocenteid(@PathVariable("id") Integer id) {
		return docenteService.updateestado(id, 0);
	}
	
	@PostMapping(value="/registrarparticipante")
	public Integer registrarparticipante(@Valid @RequestBody Participante participante,HttpSession ses) {
		
		if( ses.getAttribute("usuario")==null)
			return -100;
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		
		int tipodocumentoid   = participante.getTipodocumentoestudiante().getId();
		String dnoDocumento = participante.getNrodocumentoestudiante();
		
		if(pe!=null) {
			participante.setProgramaeducativo(pe);
			Date date= new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			participante.setFecha_registro(ts);
			participante.setAnhio(ts.toLocalDateTime().getYear());
			
			if(participante.getId()==null) {
				if(participanteService.buscaTipoNroDocumento(tipodocumentoid, dnoDocumento).size()>0) {
					return -2;
				}
			}else {
				if(participanteService.buscaTipoNroDocumento(participante.getId(),tipodocumentoid, dnoDocumento).size()>0) {
					return -2;
				}
			}
			
			Participante p =  participanteService.registrar(participante);
			if(p!=null) {
				return p.getId();	
			}
			return 0;
		}
		return -1;
	}
	
	
	@PostMapping(value="/registrardocente")
	public ClaveValor registrardocente(@Valid @RequestBody Docente docente,HttpSession ses) {
		
		ClaveValor cl = new ClaveValor();
		if( ses.getAttribute("usuario")==null) {
			cl.setId(-100);
			cl.setValor("");
			return cl;
		}
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		
		if(pe!=null) {
			docente.setProgramaeducativo(pe);
			Date date= new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			docente.setFecha_registro(ts);
			docente.setAnhio(ts.toLocalDateTime().getYear());
			
			Docente d =  docenteService.registrar(docente);
			if(d!=null) {
				cl.setId(d.getId());
				Postulacionconcurso pc = postulacionconcursoServ.getByIdAnio(d.getProgramaeducativo().getId(), d.getProgramaeducativo().getAnhio());
				cl.setValor(pc!=null?"Si":"No");
				return cl;
			}
			cl.setId(0);
			cl.setValor("");
			return cl;
		}
		cl.setId(-1);
		cl.setValor("");
		return cl;		
	}
	
	@PostMapping(value="/registrarevaluacion")
	public Integer registrarevalrubquest(@Valid @RequestBody EvaluacionRubricaQuestionarioDto dto) {
		return evaluacionServ.saveEvalRubQuest(dto);
	}
	
	@PostMapping(value="/actualizarevaluacion")
	public Integer actualizarevaluacion(@Valid @RequestBody EvaluacionRubricaQuestionarioDto dto) {
		return evaluacionServ.updateEvalRubQuest(dto);
	}
	
	@PostMapping(value="/actualizardocente")
	public Integer actualizardocente(@Valid @RequestBody Docente docente,HttpSession ses) {
		
		if( ses.getAttribute("usuario")==null)
			return -100;
		
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		
		Docente midocente = new Docente();
		midocente.setId(docente.getId());
		midocente.setAnhio(docenteService.getByProgeduc(pe.getId()).getAnhio());
		midocente.setAppaterno(docente.getAppaterno());
		midocente.setApmaterno(docente.getApmaterno());
		midocente.setCorreoelectronico(docente.getCorreoelectronico());
		midocente.setEstado(1);
		midocente.setFecha_registro(docenteService.getByProgeduc(pe.getId()).getFecha_registro());
		midocente.setNombre(docente.getNombre());
		midocente.setNrodocumento(docente.getNrodocumento());
		midocente.setNrotelefono(docente.getNrotelefono());
		midocente.setGenero(docente.getGenero());
		midocente.setProgramaeducativo(pe);
		midocente.setTipodocumento(docente.getTipodocumento());
		docenteService.registrar(midocente);
		return midocente.getId();
	}
	
	@PostMapping(value="/registrarasignarevaluador")
	public AsignarEvaluadorDto registrarasignarevaluador(@Valid @RequestBody AsignarEvaluadorDto asignarevaluadordto, HttpSession ses) {
		
		return null;
	}
	
	@PostMapping(value="/subirarchivoparticipante")
	public Integer subirarchivoparticipante(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) {
		
		if(file.isEmpty())
			return 0;
		try {
			uploadfile.saveFile(file,id,"upload_participantes");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1; 
	}
	
	
	
	
	@PostMapping(value="/updateTrabajosFinalesEnviados")
	public Integer updateTrabajosFinalesEnviados(@Valid @RequestBody List<TrabajofinalesEnviadoDto> dto,HttpSession ses) {
		
		if( ses.getAttribute("usuario")==null)
			return -100;
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		banderaUpdate = true;		
		dto.forEach(dato->{
			rpta = trabajosfinalesServ.updateEnviados(1,dato.getId());
			if(rpta != 1)
				banderaUpdate = false;
		});
		if(!banderaUpdate)
			return 0;
		
		if(postulacionconcursoServ.updatefinalizarparticipaciontrabajo(pe.getId()) !=1)
			return 0;		
		
		/*enviar correo*/
		String msj = "<img src='./images/logo_login.PNG' style='width:400px' /><img src='./images/imagen1.PNG' style='width:400px' />";
		String msj1 = "<p>Estimado(a) docente,Por medio de la presente reciba a nombre de la Superindencia Nacionalde Servicios de Saneamiento nuestro Saludos de Bienvenida al “VIII Concurso Escolar Nacional de Buenas prácticas para el Ahorro del Agua Potable”, a través de este mensaje le hacemos llegar el siguiente cuadro con el consolidado de estudiantes inscritos y datos de sus trabajos finales presentados a esta edición del Concurso Escolar:</p>";
		msj2 = "<br><table><tr><td>Categoria</td><td>Modalidad</td><td>Título de trabajo</td><td>Participantes</td></tr>";
		String msj3 = "<p>Cabe resaltar que tanto las evidencias como trabajos finales subidos a través del Sistema del Concurso Escolar, serán evaluador según cronograma por nuestro Comité Evaluador. En caso que alguno de sus estudiantes haya alcanzado algún puesto en nuestro cuadro de mérito nos pondremos en contacto con usted a través del correo electrónico y números personales registrados por usted en el sistema. Aprovechamos esta oportunidad para agradecer su compromiso y dedicación demostrados a lo largo de todo este certamen, los cuales sin duda contribuyen a la formación de la una cultura valorativa de los servicios de agua y saneamiento entre sus estudiantes. Atentamente. PROGRAMA EDUCATIVO SUNASS</p>";
		
		trabajosfinalesServ.listarhabilitados(pe.getId()).forEach(obj->{			
			participantes = "";			
			msj2 += "<tr><td>"+ obj.getCategoriatrabajo().getDescripcion() + "</td>";
			msj2 += "<td>"+ obj.getModalidadtrabajo().getDescripcion() + "</td>";
			msj2 += "<td>"+ obj.getTitulotrabajo() + "</td>";
			
			trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj1->{
				participantes += obj1.getParticipante().getNombreestudiante() + " " + obj1.getParticipante().getAppaternoestudiante() + " " + obj1.getParticipante().getApmaternoestudiante() + "/";
			});
			msj2 += "<td>" + participantes + "</td></tr>";
		});
		msj2 += "</table>";
		
		mail = new Mail();
		if( mail.enviarCorreoTrabajosFinalesConcursoEscolar("Confirmación de registro de trabajos finales al Concurso Escolar",msj+msj1+msj2+msj3,pe.getMailie())) {
			return 1;
		}
		return 0;
	}
	
	@PostMapping(value="/subirtrabajoarchivoevidencia")
	public Integer subirtrabajoarchivoevidencia(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id, @RequestParam("files") ArrayList<MultipartFile> files) {
		
		if(file.isEmpty())
			return 0;
		try {
			uploadfile.saveFile(file,id,"upload_trabajos");
			for(int i=0;i<files.size();i++) {
				uploadfile.saveFile(files.get(i),id,"upload_evidencias");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1; 
	}
	
	@PostMapping(value="/subiractualizartrabajoarchivoevidencia")
	public Integer subiractualizartrabajoarchivoevidencia(@RequestParam(name="file",required=false) MultipartFile file,@RequestParam(name="id",required=false) Integer id, @RequestParam(name="files",required=false) ArrayList<MultipartFile> files,@RequestParam("array_evidencias_eliminadas") String array_evidencias_eliminadas) {
		
		try {
			if(file != null) {
				if(uploadfile.borrarArchivo(id,"upload_trabajos")) {
					uploadfile.saveFile(file,id,"upload_trabajos");
				}
			}
			
			String [] eliminados = array_evidencias_eliminadas.split(",");
			
			if(array_evidencias_eliminadas.length()>0) {
				for(int i=0;i<eliminados.length;i++) {
					System.out.println("obj :" + eliminados[i]);
					uploadfile.eliminarArchivoCreado(id, eliminados[i]);
				}
			}
			
			if(files!= null) {
				for(int i=0;i<files.size();i++) {
					uploadfile.saveFile(files.get(i),id,"upload_evidencias");
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1; 
	}
	
	@PostMapping(value="/actualizararchivoparticipante")
	public Integer actualizararchivoparticipante(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) {
		
		if(file.isEmpty())
			return 0;
		try {
			if(uploadfile.borrarArchivo(id,"upload_participantes")) {
				uploadfile.saveNuevoFile(file,"upload_participantes",id);
			}
			else {
				return 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1; 
	}
	
	@GetMapping("/participar_concurso")
	public String participar_concurso(HttpSession ses) throws ParseException {
		
		if( ses.getAttribute("usuario")==null)
			return "-100";
		
		Calendar fecha = Calendar.getInstance();
		Date date = Calendar.getInstance().getTime();
		DateFormat formato = new SimpleDateFormat("dd/MM/yy");
        String today = formato.format(date);
        Aperturaranio ap = aperturaranioService.buscar(fecha.get(Calendar.YEAR));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    	LocalDate fechaactual = LocalDate.parse(today, formatter);
        if(ap != null) {
        	String codmod = ses.getAttribute("usuario").toString();
        	Programaeducativo pe = progeducService.verificarEstadoAnio(codmod,fecha.get(Calendar.YEAR), "Aprobado");  
        	if(pe!=null) {
        		
        		Postulacionconcurso pc = postulacionconcursoServ.getById(pe.getId());
        		if(pc!=null) {
        			if(fechaactual.compareTo(ap.getCuartaetapadesde())>=0 && fechaactual.compareTo(ap.getCuartaetapahasta())<=0) {
        				return pe.getId().toString();//pe ah sido aprobado
        			}
        			else {
        				return pe.getId().toString();
        				//return "d"; /*esta fuera de las fechas - fecha desde cuarta y fecha hasta cuarta*/
        			}
        		}
        		else {
        			if(fechaactual.compareTo(ap.getSegundaetapadesde())>=0 && fechaactual.compareTo(ap.getSegundaetapahasta())<=0) {
        				return pe.getId().toString(); //pe ah sido aprobado
        			}
        			else {
        				return "b"; /*esta fuera de las fechas - fecha desde segunda y fecha hasta segunda*/
        			}
        		}
        		
        		//return pe.getId().toString();//pe ah sido aprobado
        		
        	}
        	else {
        		return "c";/*inscripcion al pe no ha sido aprobada*/
        	}
        }
		return "a";/*no esta aperturado el año*/
	}	
	
	@GetMapping(value = "/listaparticipantes")
	public ResponseEntity<List<ListaparticipanteDto>> listaparticipante(HttpSession ses){
		
		if( ses.getAttribute("usuario")==null) {
			ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://prometeo.sunass.gob.pe/pedesa")).build();
		}		
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);		
		List<ListaparticipanteDto> lista = new ArrayList<ListaparticipanteDto>();
		List<Participante> listaParticipante = participanteService.listarhabilitados(pe.getId());
		if(listaParticipante!=null) {
			listaParticipante.forEach(obj->{			
				String categoria = "";
				String modalidad = "";
				ListaparticipanteDto dto =new ListaparticipanteDto();
				dto.setId(obj.getId());
				dto.setAppaterno(obj.getAppaternoestudiante());
				dto.setApmaterno(obj.getApmaternoestudiante());
				dto.setNombre(obj.getNombreestudiante());
				dto.setTipodocumento(obj.getTipodocumentoestudiante().getDescripcion());
				dto.setNrodocumento(obj.getNrodocumentoestudiante());
				categoria = obj.getCategoriacuento()==1?"Cuento/":"";
				categoria += obj.getCategoriapoesia()==1?"Poesía/":"";
				categoria += obj.getCategoriadibujopintura()==1?"Dibujo o Pintura/":"";
				categoria += obj.getCategoriacomposicionmusical()==1?"Composición musical/":"";
				categoria += obj.getCategoriaahorroagua()==1?"Ahorro del agua en tu hogar/" : "";
				if(categoria.length()>0)
					categoria = categoria.substring(0,categoria.length()-1);				
				dto.setCategoria(categoria);
				modalidad = obj.getModalidadpostulacionindividual()==1?"Individual/":"";
				modalidad += obj.getModalidadpostulaciongrupal()==1?"Grupal/":"";				
				if(modalidad.length()>0)
					modalidad = modalidad.substring(0,modalidad.length()-1);
				dto.setModalidad(modalidad);
				lista.add(dto);
			});
		}		
		return new ResponseEntity<List<ListaparticipanteDto>>(lista, HttpStatus.OK) ;
	}
	
	@PostMapping(value = "/listaparticipantesbyanio")
	public ResponseEntity<List<ListaparticipanteDto>> listaparticipantesbyanio(@RequestParam("anio") Integer anio,HttpSession ses){
		
		if( ses.getAttribute("usuario")==null) {
			ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://prometeo.sunass.gob.pe/pedesa")).build();
		}		
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		List<ListaparticipanteDto> lista = new ArrayList<ListaparticipanteDto>();
		List<Participante> listaParticipante = participanteService.listarhabilitadosbyanio(pe.getId(),anio);
		if(listaParticipante!=null) {
			listaParticipante.forEach(obj->{			
				String categoria = "";
				String modalidad = "";
				ListaparticipanteDto dto =new ListaparticipanteDto();
				dto.setId(obj.getId());
				dto.setAppaterno(obj.getAppaternoestudiante());
				dto.setApmaterno(obj.getApmaternoestudiante());
				dto.setNombre(obj.getNombreestudiante());
				dto.setTipodocumento(obj.getTipodocumentoestudiante().getDescripcion());
				dto.setNrodocumento(obj.getNrodocumentoestudiante());
				categoria = obj.getCategoriacuento()==1?"Cuento/":"";
				categoria += obj.getCategoriapoesia()==1?"Poesía/":"";
				categoria += obj.getCategoriadibujopintura()==1?"Dibujo o Pintura/":"";
				categoria += obj.getCategoriacomposicionmusical()==1?"Composición musical/":"";
				categoria += obj.getCategoriaahorroagua()==1?"Ahorro del agua en tu hogar/" : "";
				if(categoria.length()>0)
					categoria = categoria.substring(0,categoria.length()-1);				
				dto.setCategoria(categoria);
				modalidad = obj.getModalidadpostulacionindividual()==1?"Individual/":"";
				modalidad += obj.getModalidadpostulaciongrupal()==1?"Grupal/":"";				
				if(modalidad.length()>0)
					modalidad = modalidad.substring(0,modalidad.length()-1);
				dto.setModalidad(modalidad);
				lista.add(dto);
			});
		}		
		return new ResponseEntity<List<ListaparticipanteDto>>(lista, HttpStatus.OK) ;
	}
	
	@PostMapping(value="/getdocentetutor")
	public Docentetutor getdocentetutor(@RequestParam("anio") Integer anio,Model model, HttpSession ses) {
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		Docentetutor docentetutor = docentetutorService.getByProgeducByAnio(pe.getId(),anio);
		return docentetutor;
	}
	
	@GetMapping("/listaTrabajoEvaluado")
	public ResponseEntity<List<trabajoEvaluadoDto>> listaTrabajoEvaluado(@RequestParam(name="name",required=false,defaultValue="") String name, Model model, HttpSession ses) {
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		List<trabajoEvaluadoDto> listadto = new ArrayList<trabajoEvaluadoDto>();		
		
		if(tipousuarioid == 0){			
			Object ob = ses.getAttribute("odsid");
			distServ.listByOdsid(Integer.parseInt(ob.toString())).forEach(dist->{
				progeducService.listar(dist.getId()).forEach(pe->{
					trabajosfinalesServ.listarHabilitadosEnviado(pe.getId()).forEach(tf->{							
						nivel_participante = "";
						evaluadores_asignados = 0;
						trabajoEvaluadoDto dto = new trabajoEvaluadoDto();
						dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
						dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
						trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(tfp->{
							nivel_participante  = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
						});
						dto.setNivel_participacion(nivel_participante);
						dto.setCodigoie(tf.getProgramaeducativo().getCodmod());
						dto.setNombreie(tf.getProgramaeducativo().getNomie());
						dto.setCodigo_trabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getNumeracion());
						dto.setModalidad(tf.getModalidadtrabajo().getDescripcion());
						dto.setTitulo(tf.getTitulotrabajo());				
						trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId()).forEach(tf_ua->{
							evaluadores_asignados += 1;
						});
						dto.setEvaluadores_asignados(evaluadores_asignados);
						dto.setTiene_evaluador_asignado(evaluadores_asignados>0? "Si" : "No");
						dto.setId(tf.getId());
						listadto.add(dto);
					});
				});
			});		
		}
		else if (tipousuarioid==30){
			
			progeducService.getListarHabilitadosAnioActual().forEach(pe->{
				trabajosfinalesServ.listarHabilitadosEnviado(pe.getId()).forEach(tf->{
					nivel_participante = "";
					evaluadores_asignados = 0;
					trabajoEvaluadoDto dto = new trabajoEvaluadoDto();
					dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
					dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
					trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(tfp->{
						nivel_participante  = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
					});
					dto.setNivel_participacion(nivel_participante);
					dto.setCodigoie(tf.getProgramaeducativo().getCodmod());
					dto.setNombreie(tf.getProgramaeducativo().getNomie());
					dto.setCodigo_trabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getNumeracion());
					dto.setModalidad(tf.getModalidadtrabajo().getDescripcion());
					dto.setTitulo(tf.getTitulotrabajo());				
					trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId()).forEach(tf_ua->{
						evaluadores_asignados += 1;
					});
					dto.setEvaluadores_asignados(evaluadores_asignados);
					dto.setTiene_evaluador_asignado(evaluadores_asignados>0? "Si" : "No");
					dto.setId(tf.getId());
					listadto.add(dto);
				});			
			});			
		}
		else {
			String usuario = ses.getAttribute("usuario").toString();
			Usuario user = usuarioServ.byUsuario(usuario);
			usuarioodsServ.listarByUsuario(user.getId()).forEach(obj->{
				distServ.listByOdsid(obj.getOds().getId()).forEach(dist->{					
					progeducService.listar(dist.getId()).forEach(pe->{
						trabajosfinalesServ.listarHabilitadosEnviado(pe.getId()).forEach(tf->{
							nivel_participante = "";
							evaluadores_asignados = 0;
							trabajoEvaluadoDto dto = new trabajoEvaluadoDto();
							dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
							dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
							trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(tfp->{
								nivel_participante  = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
							});
							dto.setNivel_participacion(nivel_participante);
							dto.setCodigoie(tf.getProgramaeducativo().getCodmod());
							dto.setNombreie(tf.getProgramaeducativo().getNomie());
							dto.setCodigo_trabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getNumeracion());
							dto.setModalidad(tf.getModalidadtrabajo().getDescripcion());
							dto.setTitulo(tf.getTitulotrabajo());				
							trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId()).forEach(tf_ua->{
								evaluadores_asignados += 1;
							});
							dto.setEvaluadores_asignados(evaluadores_asignados);
							dto.setTiene_evaluador_asignado(evaluadores_asignados>0? "Si" : "No");
							dto.setId(tf.getId());
							listadto.add(dto);
						});					
					});
				});
			});
		}
		return new ResponseEntity<List<trabajoEvaluadoDto>>(listadto, HttpStatus.OK);
	}
	
	@GetMapping("/listaevaluador")
	public ResponseEntity<List<EvaluadorDto>> listaEvaluador(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses) {
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());		
		List<EvaluadorDto> lista = new ArrayList<EvaluadorDto>();		
		
		if(tipousuarioid == 0){			
			Object ob = ses.getAttribute("odsid");			
			List<UsuarioAlianza> listaUsu = usuAlianzaserv.listarByOds(Integer.parseInt(ob.toString()));
			listaUsu.forEach(ua->{
				
				if(ua.getComiteevaluador().equals("1")) {
					if(ua.getEstado().equals("1")) {
						EvaluadorDto dto = new EvaluadorDto();
						dto.setOds(ua.getOds().getDescripcion());
						
						rol_entidad = "";
						if(ua.getComitetecnico().equals("1")) {
							rol_entidad = "Comite Técnico/";
						}				
						if(ua.getComiteevaluador().equals("1")) {
							rol_entidad += "Comite Evaluador/";
						}				
						if(ua.getAuspiciador().equals("1")) {
							rol_entidad += "Auspiciador/";
						}				
						if(ua.getAliado().equals("1")) {
							rol_entidad += "Aliado/";
						}				
						if(rol_entidad.length()>0)
							rol_entidad = rol_entidad.substring(0, rol_entidad.length()-1);	
						dto.setRol_entidad(rol_entidad);
						dto.setEntidad(ua.getEntidad());
						dto.setApellido_paterno(ua.getApepatautoridad());
						dto.setApellido_materno(ua.getApematautoridad());
						dto.setNombres(ua.getNombresautoridad());
						if(ua.getTipodocumento() != null) {
							dto.setTipo_documento(ua.getTipodocumento()!=null?ua.getTipodocumento().getDescripcion():"");
						}
						dto.setNro_documento(ua.getNumdocumento());
						dto.setId(ua.getId());
						lista.add(dto);
					}
				}
			});
		}
		else if (tipousuarioid==30){
			usuAlianzaserv.listar().forEach(ua->{
				EvaluadorDto dto = new EvaluadorDto();
				dto.setOds(ua.getOds().getDescripcion());
				rol_entidad = "";				
				if(ua.getComiteevaluador().equals("1")) {
					if(ua.getEstado().equals("1")) {
						if(ua.getComitetecnico().equals("1")) {
							rol_entidad = "Comite Técnico/";
						}				
						if(ua.getComiteevaluador().equals("1")) {
							rol_entidad += "Comite Evaluador/";
						}				
						if(ua.getAuspiciador().equals("1")) {
							rol_entidad += "Auspiciador/";
						}				
						if(ua.getAliado().equals("1")) {
							rol_entidad += "Aliado/";
						}				
						if(rol_entidad.length()>0)
							rol_entidad = rol_entidad.substring(0, rol_entidad.length()-1);				
						dto.setRol_entidad(rol_entidad);
						dto.setEntidad(ua.getEntidad());
						dto.setApellido_paterno(ua.getApepatautoridad());
						dto.setApellido_materno(ua.getApematautoridad());
						dto.setNombres(ua.getNombresautoridad());
						if(ua.getTipodocumento() != null) {
							dto.setTipo_documento(ua.getTipodocumento()!=null?ua.getTipodocumento().getDescripcion():"");
						}
						dto.setNro_documento(ua.getNumdocumento());
						dto.setId(ua.getId());
						lista.add(dto);
					}
				}				
			});	
		}
		else {
			String usuario = ses.getAttribute("usuario").toString();
			Usuario user = usuarioServ.byUsuario(usuario);
			usuarioodsServ.listarByUsuario(user.getId()).forEach(usu->{				
				List<UsuarioAlianza> listaUsu = usuAlianzaserv.listarByOds(usu.getOds().getId());
				//List<UsuarioAlianza> listaUsu = usuAlianzaserv.listarByOds(10);
				listaUsu.forEach(ua->{
					EvaluadorDto dto = new EvaluadorDto();
					dto.setOds(ua.getOds().getDescripcion());
					rol_entidad = "";					
					if(ua.getComiteevaluador().equals("1")) {
						if(ua.getEstado().equals("1")) {
							if(ua.getComitetecnico().equals("1")) {
								rol_entidad = "Comite Técnico/";
							}				
							if(ua.getComiteevaluador().equals("1")) {
								rol_entidad += "Comite Evaluador/";
							}				
							if(ua.getAuspiciador().equals("1")) {
								rol_entidad += "Auspiciador/";
							}				
							if(ua.getAliado().equals("1")) {
								rol_entidad += "Aliado/";
							}				
							if(rol_entidad.length()>0)
								rol_entidad = rol_entidad.substring(0, rol_entidad.length()-1);	
							dto.setRol_entidad(rol_entidad);
							dto.setEntidad(ua.getEntidad());
							dto.setApellido_paterno(ua.getApepatautoridad());
							dto.setApellido_materno(ua.getApematautoridad());
							dto.setNombres(ua.getNombresautoridad());
							dto.setTipo_documento(ua.getTipodocumento()!=null?ua.getTipodocumento().getDescripcion():"");
							dto.setNro_documento(ua.getNumdocumento());
							dto.setId(ua.getId());
							lista.add(dto);
						}						
					}
				});
			});	
		}		
		return new ResponseEntity<List<EvaluadorDto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listaconcurso")
	public ResponseEntity<List<ConcursoDto>> listarconcurso(HttpSession ses){
		
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
		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());		
		List<ConcursoDto> listadto  = new ArrayList<ConcursoDto>();
		
		if(tipousuarioid == 0){			
			Object ob = ses.getAttribute("odsid");
			distServ.listByOdsid(Integer.parseInt(ob.toString())).forEach(obj->{				
				progeducService.listar(obj.getId()).forEach(pe->{
					banderaods = false;
					listaOds.forEach(objOds->{
						if(objOds.getId().equals(pe.getDistrito().getOdsid())) {
							banderaods = true;
						}
					});
					if(banderaods) {
						List<Trabajosfinales> listaTrabajoFinales =  trabajosfinalesServ.listarHabilitadosEnviados(pe.getId());
						listaTrabajoFinales.forEach(obj2->{
							if(obj2.getEnviado()==1) {
								ConcursoDto dto = new ConcursoDto();
								dto.setId(obj2.getId());
								dto.setAnio(obj2.getAnio());
								dto.setCodigotrabajo(obj2.getProgramaeducativo().getCodmod() + "_" + obj2.getNumeracion());
								dto.setOds(odsserv.byOds(obj2.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
								dto.setCodigoie(obj2.getProgramaeducativo().getCodmod());
								dto.setNombreie(obj2.getProgramaeducativo().getNomie());
								dto.setCategoria(obj2.getCategoriatrabajo().getDescripcion());
								dto.setModalidad(obj2.getModalidadtrabajo().getDescripcion());
								dto.setTitulotrabajo(obj2.getTitulotrabajo());
								nivelparticipacion = "";
								trabajosfinalesparticipanteServ.listar(obj2.getId()).forEach(obj3->{
									nivelparticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartdesc();
									idnivelparticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartid();
								});			
								dto.setNivelparticipacion(nivelparticipacion);
								dto.setEstado(obj2.getEstadotrabajo().getDescripcion());
								dto.setCalificacion(obj2.getNota());
								dto.setPuesto(obj2.getPuesto());
								listadto.add(dto);
							}
						});
					}
				});
			});		
		}
		else if (tipousuarioid==30){	
			
			progeducService.getListarHabilitadosAnioActual().forEach(pe->{
				List<Trabajosfinales> listaTrabajoFinales =  trabajosfinalesServ.listarHabilitadosEnviado(pe.getId());
				listaTrabajoFinales.forEach(obj->{
					banderaods = false;
					listaOds.forEach(objOds->{
						if(objOds.getId().equals(obj.getProgramaeducativo().getDistrito().getOdsid())) {
							banderaods = true;
						}
					});
					if(banderaods) {
						ConcursoDto dto = new ConcursoDto();
						dto.setId(obj.getId());
						dto.setAnio(obj.getAnio());
						dto.setCodigotrabajo(obj.getProgramaeducativo().getCodmod() + "_" + obj.getNumeracion());
						dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
						dto.setCodigoie(obj.getProgramaeducativo().getCodmod());
						dto.setNombreie(obj.getProgramaeducativo().getNomie());
						dto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
						dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
						dto.setTitulotrabajo(obj.getTitulotrabajo());
						nivelparticipacion = "";
						trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj1->{
							nivelparticipacion = obj1.getParticipante().getGradoestudiante().getNivelgradopartdesc();
							idnivelparticipacion = obj1.getParticipante().getGradoestudiante().getNivelgradopartid();
						});			
						dto.setNivelparticipacion(nivelparticipacion);
						dto.setEstado(obj.getEstadotrabajo().getDescripcion());
						dto.setCalificacion(obj.getNota());
						dto.setPuesto(obj.getPuesto());
						listadto.add(dto);
					}
				});
			});
		}
		else {
			String usuario = ses.getAttribute("usuario").toString();
			Usuario user = usuarioServ.byUsuario(usuario);
			usuarioodsServ.listarByUsuario(user.getId()).forEach(obj->{
				distServ.listByOdsid(obj.getOds().getId()).forEach(dist->{
					progeducService.listar(dist.getId()).forEach(pe->{
						banderaods = false;
						listaOds.forEach(objOds->{
							if(objOds.getId().equals(pe.getDistrito().getOdsid())) {
								banderaods = true;
							}
						});
						if(banderaods) {
							List<Trabajosfinales> listaTrabajoFinales =  trabajosfinalesServ.listarHabilitadosEnviado(pe.getId());
							listaTrabajoFinales.forEach(obj2->{
								ConcursoDto dto = new ConcursoDto();
								dto.setId(obj2.getId());
								dto.setAnio(obj2.getAnio());
								dto.setCodigotrabajo(obj2.getProgramaeducativo().getCodmod() + "_" + obj2.getNumeracion());
								dto.setOds(odsserv.byOds(obj2.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
								dto.setCodigoie(obj2.getProgramaeducativo().getCodmod());
								dto.setNombreie(obj2.getProgramaeducativo().getNomie());
								dto.setCategoria(obj2.getCategoriatrabajo().getDescripcion());
								dto.setModalidad(obj2.getModalidadtrabajo().getDescripcion());
								dto.setTitulotrabajo(obj2.getTitulotrabajo());
								nivelparticipacion = "";
								trabajosfinalesparticipanteServ.listar(obj2.getId()).forEach(obj3->{
									nivelparticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartdesc();
									idnivelparticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartid();
								});
								dto.setNivelparticipacion(nivelparticipacion);
								dto.setEstado(obj2.getEstadotrabajo().getDescripcion());
								dto.setCalificacion(obj2.getNota());
								dto.setPuesto(obj2.getPuesto());
								listadto.add(dto);
							});
						}
					});
				});
			});
		}
		return new ResponseEntity<List<ConcursoDto>>(listadto, HttpStatus.OK) ;
	}
	
	@GetMapping(value="/listaasignacion")
	public ResponseEntity<List<AsignacionDto>> listaasignacion(HttpSession ses){
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		List<AsignacionDto> lista = new ArrayList<AsignacionDto>();	
		
		if(tipousuarioid == 0){			
			Object ob = ses.getAttribute("odsid");
			distServ.listByOdsid(Integer.parseInt(ob.toString())).forEach(obj->{
				progeducService.listar(obj.getId()).forEach(obj1->{
					trabajosfinalesServ.listarhabilitados(obj1.getId()).forEach(tf->{
						AsignacionDto dto = new AsignacionDto();
						rol_entidad = "";
						entidad = "";
						nombre_apellido_evaluador = "";
						trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId()).forEach(tf_ua->{
							nombre_apellido_evaluador +=	tf_ua.getUsuarioalianza().getNombresautoridad() +  " "+	tf_ua.getUsuarioalianza().getApepatautoridad() + " "+	tf_ua.getUsuarioalianza().getApematautoridad() + " / ";
							if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
								rol_entidad = "Comite Técnico/";
							}				
							if(tf_ua.getUsuarioalianza().getComiteevaluador().equals("1")) {
								rol_entidad += "Comite Evaluador/";
							}				
							if(tf_ua.getUsuarioalianza().getAuspiciador().equals("1")) {
								rol_entidad += "Auspiciador/";
							}				
							if(tf_ua.getUsuarioalianza().getAliado().equals("1")) {
								rol_entidad += "Aliador/";
							}
							if(rol_entidad.length()>0)
								rol_entidad = rol_entidad.substring(0, rol_entidad.length()-1);	
							entidad += tf_ua.getUsuarioalianza().getEntidad();
						});
						
						if(!entidad.equals("")) {
							dto.setRolentidad(rol_entidad);
							dto.setEntidad(entidad);
							
							dto.setAnio(tf.getAnio());
							dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
							dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
							dto.setCodigoiiee(tf.getProgramaeducativo().getCodmod());
							dto.setCodigotrabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getNumeracion());
							dto.setTituloiiee(tf.getTitulotrabajo());
							dto.setNombreiiee(tf.getProgramaeducativo().getNomie());
							dto.setModalidad(tf.getModalidadtrabajo().getDescripcion());
							
							nivelparticipacion  = "";
							trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(tfp->{
								nivelparticipacion = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
							});
							dto.setNivelparticipacion(nivelparticipacion);			
							
							dto.setEvaluadorasignado(nombre_apellido_evaluador);
							lista.add(dto);
						}
					});
				});
			});	
		}
		else if (tipousuarioid==30){
			trabajosfinalesServ.listarhabilitados().forEach(tf->{
				AsignacionDto dto = new AsignacionDto();
				rol_entidad = "";
				entidad = "";
				nombre_apellido_evaluador = "";
				trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId()).forEach(tf_ua->{
					nombre_apellido_evaluador +=	tf_ua.getUsuarioalianza().getNombresautoridad() +  " "+	tf_ua.getUsuarioalianza().getApepatautoridad() + " "+	tf_ua.getUsuarioalianza().getApematautoridad() + " / ";
					if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
						rol_entidad = "Comite Técnico/";
					}				
					if(tf_ua.getUsuarioalianza().getComiteevaluador().equals("1")) {
						rol_entidad += "Comite Evaluador/";
					}				
					if(tf_ua.getUsuarioalianza().getAuspiciador().equals("1")) {
						rol_entidad += "Auspiciador/";
					}				
					if(tf_ua.getUsuarioalianza().getAliado().equals("1")) {
						rol_entidad += "Aliado/";
					}
					if(rol_entidad.length()>0)
						rol_entidad = rol_entidad.substring(0, rol_entidad.length()-1);	
					entidad += tf_ua.getUsuarioalianza().getEntidad();
				});
				
				if(!entidad.equals("")) {
					dto.setRolentidad(rol_entidad);
					dto.setEntidad(entidad);
					
					dto.setAnio(tf.getAnio());
					dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
					dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
					dto.setCodigoiiee(tf.getProgramaeducativo().getCodmod());
					dto.setCodigotrabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getNumeracion());
					dto.setTituloiiee(tf.getTitulotrabajo());
					dto.setNombreiiee(tf.getProgramaeducativo().getNomie());
					dto.setModalidad(tf.getModalidadtrabajo().getDescripcion());
					
					nivelparticipacion  = "";
					trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(tfp->{
						nivelparticipacion = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
					});
					dto.setNivelparticipacion(nivelparticipacion);			
					
					dto.setEvaluadorasignado(nombre_apellido_evaluador);
					lista.add(dto);
				}			
			});
		}
		else {
			String usuario = ses.getAttribute("usuario").toString();
			Usuario user = usuarioServ.byUsuario(usuario);
			usuarioodsServ.listarByUsuario(user.getId()).forEach(usu->{		
				distServ.listByOdsid(usu.getOds().getId()).forEach(obj->{
					progeducService.listar(obj.getId()).forEach(obj1->{
						trabajosfinalesServ.listarhabilitados(obj1.getId()).forEach(tf->{
							AsignacionDto dto = new AsignacionDto();
							rol_entidad = "";
							entidad = "";
							nombre_apellido_evaluador = "";
							trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId()).forEach(tf_ua->{
								nombre_apellido_evaluador +=	tf_ua.getUsuarioalianza().getNombresautoridad() +  " "+	tf_ua.getUsuarioalianza().getApepatautoridad() + " "+	tf_ua.getUsuarioalianza().getApematautoridad() + " / ";
								if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
									rol_entidad = "Comite Técnico/";
								}				
								if(tf_ua.getUsuarioalianza().getComiteevaluador().equals("1")) {
									rol_entidad += "Comite Evaluador/";
								}				
								if(tf_ua.getUsuarioalianza().getAuspiciador().equals("1")) {
									rol_entidad += "Auspiciador/";
								}				
								if(tf_ua.getUsuarioalianza().getAliado().equals("1")) {
									rol_entidad += "Aliado/";
								}
								if(rol_entidad.length()>0)
									rol_entidad = rol_entidad.substring(0, rol_entidad.length()-1);	
								entidad += tf_ua.getUsuarioalianza().getEntidad();
							});
							
							if(!entidad.equals("")) {
								dto.setRolentidad(rol_entidad);
								dto.setEntidad(entidad);
								
								dto.setAnio(tf.getAnio());
								dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
								dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
								dto.setCodigoiiee(tf.getProgramaeducativo().getCodmod());
								dto.setCodigotrabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getNumeracion());
								dto.setTituloiiee(tf.getTitulotrabajo());
								dto.setNombreiiee(tf.getProgramaeducativo().getNomie());
								dto.setModalidad(tf.getModalidadtrabajo().getDescripcion());
								
								nivelparticipacion  = "";
								trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(tfp->{
									nivelparticipacion = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
								});
								dto.setNivelparticipacion(nivelparticipacion);			
								
								dto.setEvaluadorasignado(nombre_apellido_evaluador);
								lista.add(dto);
							}
						});
					});
				});	
			});			
		}
		return new ResponseEntity<List<AsignacionDto>>(lista, HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/listarTrabajosEvaluados")
	public ResponseEntity<List<ConcursoDto>> listarTrabajosEvaluados(HttpSession ses){
		
		List<ConcursoDto> listadto  = new ArrayList<ConcursoDto>();
		List<Trabajosfinales> listaTrabajoFinales =  trabajosfinalesServ.listarhabilitados();
		listaTrabajoFinales.forEach(obj->{
			ConcursoDto dto = new ConcursoDto();
			dto.setId(obj.getId());
			dto.setAnio(obj.getAnio());
			dto.setCodigotrabajo(obj.getProgramaeducativo().getId() + "_" + obj.getId());
			dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
			dto.setCodigoie(obj.getProgramaeducativo().getCodmod());
			dto.setNombreie(obj.getProgramaeducativo().getNomie());
			dto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
			dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
			dto.setTitulotrabajo(obj.getTitulotrabajo());
			dto.setNivelparticipacion(evaluacionServ.getPorAnio(obj.getAnio()).getNivelparticipacion().getDescripcion());
			dto.setEstado(evaluacionServ.getPorAnio(obj.getAnio()).getEstadoevaluacion().getDescripcion());
			dto.setCalificacion(obj.getNota());
			dto.setPuesto(obj.getPuesto());
			listadto.add(dto);
		});		
		return new ResponseEntity<List<ConcursoDto>>(listadto, HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/listaevaluaciones")
	public ResponseEntity<List<EvaluacionDto>> listaevaluaciones(HttpSession ses){
		
		List<EvaluacionDto> lista = new ArrayList<EvaluacionDto>();
		evaluacionServ.listar().forEach(obj->{
			if(obj.getEstado()==1) {
				EvaluacionDto midto = new EvaluacionDto();
				midto.setId(obj.getId());
				midto.setAnio(obj.getAnio());
				midto.setCategoriaevaluacion(obj.getCategoriaevaluacion().getDescripcion());
				midto.setNivelparticipacion(obj.getNivelparticipacion().getDescripcion());
				midto.setEstado(obj.getEstadoevaluacion().getDescripcion());
				midto.setEstadoevaluacion(obj.getEstadoevaluacion().getId());
				lista.add(midto);
			}
		});	
		return new ResponseEntity<List<EvaluacionDto>>(lista, HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/listatrabajosfinales")
	public ResponseEntity<List<ListatrabajosfinalesDto>> listatrabajosfinales(HttpSession ses){
		
		estado_fuera_plazo=0;		
		Calendar fecha = Calendar.getInstance();
		Date date = Calendar.getInstance().getTime();
		DateFormat formato = new SimpleDateFormat("dd/MM/yy");
        String today = formato.format(date);
        Aperturaranio ap = aperturaranioService.buscar(fecha.get(Calendar.YEAR));
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    	LocalDate fechaactual = LocalDate.parse(today, formatter);  
    	
    	String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);	    	
    	//Postulacionconcurso postconc = postulacionconcursoServ.getByIdAnio(pe.getId(), fecha.get(Calendar.YEAR));
    	if(fechaactual.compareTo(ap.getCuartaetapadesde())>=0 && fechaactual.compareTo(ap.getCuartaetapahasta())<=0)
    		estado_fuera_plazo = 1;
		
		List<ListatrabajosfinalesDto> lista = new ArrayList<ListatrabajosfinalesDto>();
		List<Trabajosfinales> listaTrabajosfinales =  trabajosfinalesServ.listarhabilitados(pe.getId());
		if(listaTrabajosfinales!=null) {
			listaTrabajosfinales.forEach(obj->{		
				
				String archivos = "";
				miparticipante = "";		
				dtotf =new ListatrabajosfinalesDto();
				dtotf.setId(obj.getId());				
				dtotf.setCategoria(obj.getCategoriatrabajo().getDescripcion());
				dtotf.setTitulo(obj.getTitulotrabajo());
				dtotf.setModalidad(obj.getModalidadtrabajo().getDescripcion());				
				trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj1->{
					miparticipante += obj1.getParticipante().getNombreestudiante() + " " + obj1.getParticipante().getAppaternoestudiante() + " " + obj1.getParticipante().getApmaternoestudiante() + "/";
				});
				if(miparticipante.length()>0)
					miparticipante = miparticipante.substring(0, miparticipante.length()-1);
				
				dtotf.setParticipantes(miparticipante);				
				nro_evidencias = uploadfile.nroArchivos(obj.getId(), "upload_evidencias");
				if(nro_evidencias == 1)
					texto_evidencias = " evidencia ";
				else
					texto_evidencias = " evidencias ";				
				archivos = nro_evidencias.toString() + texto_evidencias + uploadfile.nroArchivos(obj.getId(), "upload_trabajos").toString() + " final";				
				dtotf.setArchivos(archivos);
				if(estado_fuera_plazo == 1) {
					dtotf.setEnviado(obj.getEnviado());
				}else {
					dtotf.setEnviado(2);	
				}
				
							
				lista.add(dtotf);
			});
		}		
		return new ResponseEntity<List<ListatrabajosfinalesDto>>(lista, HttpStatus.OK) ;
	}
	
	
	
	
	@PostMapping(value = "/listatrabajosfinalesbyanio")
	public ResponseEntity<List<ListatrabajosfinalesDto>> listatrabajosfinalesbyanio(@RequestParam("anio") Integer anio,HttpSession ses){
		
		estado_fuera_plazo=0;		
		Calendar fecha = Calendar.getInstance();
		Date date = Calendar.getInstance().getTime();
		DateFormat formato = new SimpleDateFormat("dd/MM/yy");
        String today = formato.format(date);
        Aperturaranio ap = aperturaranioService.buscar(fecha.get(Calendar.YEAR));
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    	LocalDate fechaactual = LocalDate.parse(today, formatter);  
    	
    	String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);	    	
    	//Postulacionconcurso postconc = postulacionconcursoServ.getByIdAnio(pe.getId(), fecha.get(Calendar.YEAR));
    	if(fechaactual.compareTo(ap.getCuartaetapadesde())>=0 && fechaactual.compareTo(ap.getCuartaetapahasta())<=0)
    		estado_fuera_plazo = 1;
		
		List<ListatrabajosfinalesDto> lista = new ArrayList<ListatrabajosfinalesDto>();
		List<Trabajosfinales> listaTrabajosfinales =  trabajosfinalesServ.listarhabilitadosbyanio(pe.getId(),anio);
		if(listaTrabajosfinales!=null) {
			listaTrabajosfinales.forEach(obj->{		
				
				String archivos = "";
				miparticipante = "";		
				dtotf =new ListatrabajosfinalesDto();
				dtotf.setId(obj.getId());				
				dtotf.setCategoria(obj.getCategoriatrabajo().getDescripcion());
				dtotf.setTitulo(obj.getTitulotrabajo());
				dtotf.setModalidad(obj.getModalidadtrabajo().getDescripcion());				
				trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj1->{
					miparticipante += obj1.getParticipante().getNombreestudiante() + " " + obj1.getParticipante().getAppaternoestudiante() + " " + obj1.getParticipante().getApmaternoestudiante() + "/";
				});
				if(miparticipante.length()>0)
					miparticipante = miparticipante.substring(0, miparticipante.length()-1);
				
				dtotf.setParticipantes(miparticipante);				
				nro_evidencias = uploadfile.nroArchivos(obj.getId(), "upload_evidencias");
				if(nro_evidencias == 1)
					texto_evidencias = " evidencia ";
				else
					texto_evidencias = " evidencias ";				
				archivos = nro_evidencias.toString() + texto_evidencias + uploadfile.nroArchivos(obj.getId(), "upload_trabajos").toString() + " final";				
				dtotf.setArchivos(archivos);
				if(estado_fuera_plazo == 1) {
					dtotf.setEnviado(obj.getEnviado());	
				}else {
					dtotf.setEnviado(2);	
				}
				
							
				lista.add(dtotf);
			});
		}		
		return new ResponseEntity<List<ListatrabajosfinalesDto>>(lista, HttpStatus.OK) ;
	}
	
	
	
	@GetMapping("/listaparticipantes_trabajo")
	public ResponseEntity<List<ListaparticipantetrabajoDto>> listaparticipantes_trabajo(HttpSession ses){
		
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		List<ListaparticipantetrabajoDto> lista = new ArrayList<>();
		List<Participante> listaParticipante = participanteService.listarhabilitados(pe.getId());
		if(listaParticipante!=null) {
			listaParticipante.forEach(p->{
					ListaparticipantetrabajoDto ptdto =new ListaparticipantetrabajoDto();
					ptdto.setId(p.getId());
					ptdto.setAppaterno(p.getAppaternoestudiante());
					ptdto.setApmaterno(p.getApmaternoestudiante());
					ptdto.setNombre(p.getNombreestudiante());
					ptdto.setTipodocumento(p.getTipodocumentoestudiante().getDescripcion());
					ptdto.setNrodocumento(p.getNrodocumentoestudiante());
					ptdto.setComposicionmusical(p.getCategoriacomposicionmusical());
					ptdto.setCuento(p.getCategoriacuento());
					ptdto.setPoesia(p.getCategoriapoesia());
					ptdto.setDibujopintura(p.getCategoriadibujopintura());
					ptdto.setAhorraragua(p.getCategoriaahorroagua());
					ptdto.setModalidadindividual(p.getModalidadpostulacionindividual());
					ptdto.setModalidadgrupal(p.getModalidadpostulaciongrupal());
					ptdto.setNivel(p.getGradoestudiante().getNivelgradopartdesc());
					lista.add(ptdto);
			});
		}		
		return new ResponseEntity<List<ListaparticipantetrabajoDto>>(lista, HttpStatus.OK) ;
	}	
	
	@PostMapping(value="/verificaParticipanteSubidoTrabajo")
	public Integer verificaParticipanteSubidoTrabajo(@Valid @RequestBody TrabajoCategoriaModalidadDto dto) {
		List<TrabajosfinalesParticipante> listatfp = trabajosfinalesparticipanteServ.listarPorParticipante(dto.getId(),dto.getIdCategoria(),dto.getIdModalidad(),dto.getIdTrabajo());
		if(listatfp.size()>0)
			return 1;
		return 0;
	}
	
	@PostMapping(value="/verificaParticipanteSubidoTrabajoAdd")
	public Integer verificaParticipanteSubidoTrabajoAdd(@Valid @RequestBody TrabajoCategoriaModalidadAddDto dto) {
		List<TrabajosfinalesParticipante> listatfp = trabajosfinalesparticipanteServ.listarPorParticipante(dto.getId(),dto.getIdCategoria(),dto.getIdModalidad());
		if(listatfp.size()>0)
			return 1;
		return 0;
	}
	
	@GetMapping(value = "/listaparticipantesver_trabajo/{id}")
	public ResponseEntity<List<ParticipanteVerDto>> listaparticipantesver_trabajo(@PathVariable("id") Integer id){
		
		List<ParticipanteVerDto> lista = new ArrayList<ParticipanteVerDto>();
		trabajosfinalesparticipanteServ.listar(id).forEach(obj->{
			ParticipanteVerDto dto = new ParticipanteVerDto();
			dto.setApellidopaterno(obj.getParticipante().getAppaternoestudiante());
			dto.setApellidomaterno(obj.getParticipante().getApmaternoestudiante());
			dto.setNombres(obj.getParticipante().getNombreestudiante());
			dto.setTipodocumento(obj.getParticipante().getTipodocumentoestudiante().getDescripcion());
			dto.setNrodocumento(obj.getParticipante().getNrodocumentoestudiante());
			dto.setNivel(obj.getParticipante().getGradoestudiante().getNivelparticipante().getDescripcion());
			lista.add(dto);
		});
		return new ResponseEntity<List<ParticipanteVerDto>>(lista, HttpStatus.OK) ;
	}
	
	
	
	@GetMapping("/listadocentes")
	public ResponseEntity<List<ListaDocente>> listadocentes(HttpSession ses) {		
		
		List<ListaDocente> docente = new ArrayList<ListaDocente>();
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		docenteService.listarhabilitados(pe.getId()).forEach(obj->{
			ListaDocente ld = new ListaDocente();
			ld.setId(obj.getId());
			ld.setAppaterno(obj.getAppaterno());
			ld.setApmaterno(obj.getApmaterno());
			ld.setNombre(obj.getNombre());
			ld.setTipodocumento(obj.getTipodocumento().getDescripcion());
			ld.setNrodocumento(obj.getNrodocumento());
			ld.setNrotelefono(obj.getNrotelefono());
			ld.setCorreoelectronico(obj.getCorreoelectronico());
			ld.setFecha_registro(obj.getFecha_registro());
			Postulacionconcurso pc = postulacionconcursoServ.getByIdAnio(pe.getId(), obj.getAnhio());
			ld.setNomie(pc!=null?"Si":"No");
			docente.add(ld);
		});			
		return new ResponseEntity<List<ListaDocente>>(docente, HttpStatus.OK) ;
	}
	
	@GetMapping("/listadocentesinscritos")
	public ResponseEntity<List<ListaDocenteInscritos>> listadocentesinscritos(HttpSession ses) {
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		List<ListaDocenteInscritos> arrayie = new ArrayList<ListaDocenteInscritos>();
		if(tipousuarioid == 0){			
			Object ob = ses.getAttribute("odsid");
			distServ.listByOdsid(Integer.parseInt(ob.toString())).forEach(dist->{
				progeducService.listar(dist.getId()).forEach(pe->{
					docenteService.listarhabilitados(pe.getId()).forEach(obj->{
						listadocentesinscritos= new ListaDocenteInscritos();				
						listadocentesinscritos.setAnio(obj.getAnhio());				
						if(obj.getProgramaeducativo().getDistrito().getOdsid()!=null) {
							Ods ods = odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid());
							listadocentesinscritos.setOds(ods.getDescripcion());
						}
						else{
							listadocentesinscritos.setOds("");
						}
						listadocentesinscritos.setAnio(obj.getAnhio());
						listadocentesinscritos.setCodigo_ie(obj.getProgramaeducativo().getCodmod());
						listadocentesinscritos.setNombre_ie(obj.getProgramaeducativo().getNomie());
						Postulacionconcurso pc = postulacionconcursoServ.getByIdAnio(obj.getProgramaeducativo().getId(), obj.getAnhio());
						listadocentesinscritos.setInscrito_ie(pc!=null?"Si":"No");
						listadocentesinscritos.setDocente(obj.getNombre() + " " + obj.getAppaterno() + " " + obj.getApmaterno());
						listadocentesinscritos.setTipodocumento(obj.getTipodocumento().getDescripcion());
						listadocentesinscritos.setNrodocumento(obj.getNrodocumento());
						listadocentesinscritos.setCorreoelectronico(obj.getCorreoelectronico());
						listadocentesinscritos.setNrotelefono(obj.getNrotelefono());
						listadocentesinscritos.setGenero(obj.getGenero().getDescripcion());
						listadocentesinscritos.setFecha_registro(obj.getFecha_registro());
						arrayie.add(listadocentesinscritos);
					});
				});
			});		
		}
		else if (tipousuarioid==30){
			docenteService.listarTodoshabilitados().forEach(obj->{
				listadocentesinscritos= new ListaDocenteInscritos();				
				listadocentesinscritos.setAnio(obj.getAnhio());				
				if(obj.getProgramaeducativo().getDistrito().getOdsid()!=null) {
					Ods ods = odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid());
					listadocentesinscritos.setOds(ods.getDescripcion());
				}
				else{
					listadocentesinscritos.setOds("");
				}
				listadocentesinscritos.setAnio(obj.getAnhio());
				listadocentesinscritos.setCodigo_ie(obj.getProgramaeducativo().getCodmod());
				listadocentesinscritos.setNombre_ie(obj.getProgramaeducativo().getNomie());
				Postulacionconcurso pc = postulacionconcursoServ.getByIdAnio(obj.getProgramaeducativo().getId(), obj.getAnhio());
				listadocentesinscritos.setInscrito_ie(pc!=null?"Si":"No");
				listadocentesinscritos.setDocente(obj.getNombre() + " " + obj.getAppaterno() + " " + obj.getApmaterno());
				listadocentesinscritos.setTipodocumento(obj.getTipodocumento().getDescripcion());
				listadocentesinscritos.setNrodocumento(obj.getNrodocumento());
				listadocentesinscritos.setCorreoelectronico(obj.getCorreoelectronico());
				listadocentesinscritos.setNrotelefono(obj.getNrotelefono());
				listadocentesinscritos.setGenero(obj.getGenero().getDescripcion());
				listadocentesinscritos.setFecha_registro(obj.getFecha_registro());
				arrayie.add(listadocentesinscritos);
			});	
		}
		else {
			String usuario = ses.getAttribute("usuario").toString();
			Usuario user = usuarioServ.byUsuario(usuario);
			usuarioodsServ.listarByUsuario(user.getId()).forEach(obj1->{
				distServ.listByOdsid(obj1.getOds().getId()).forEach(dist->{
					progeducService.listar(dist.getId()).forEach(pe->{
						docenteService.listarhabilitados(pe.getId()).forEach(obj->{
							listadocentesinscritos= new ListaDocenteInscritos();				
							listadocentesinscritos.setAnio(obj.getAnhio());
							if(obj.getProgramaeducativo().getDistrito().getOdsid()!=null) {
								Ods ods = odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid());
								listadocentesinscritos.setOds(ods.getDescripcion());
							}
							else{
								listadocentesinscritos.setOds("");
							}
							listadocentesinscritos.setAnio(obj.getAnhio());
							listadocentesinscritos.setCodigo_ie(obj.getProgramaeducativo().getCodmod());
							listadocentesinscritos.setNombre_ie(obj.getProgramaeducativo().getNomie());
							Postulacionconcurso pc = postulacionconcursoServ.getByIdAnio(obj.getProgramaeducativo().getId(), obj.getAnhio());
							listadocentesinscritos.setInscrito_ie(pc!=null?"Si":"No");
							listadocentesinscritos.setDocente(obj.getNombre() + " " + obj.getAppaterno() + " " + obj.getApmaterno());
							listadocentesinscritos.setTipodocumento(obj.getTipodocumento().getDescripcion());
							listadocentesinscritos.setNrodocumento(obj.getNrodocumento());
							listadocentesinscritos.setCorreoelectronico(obj.getCorreoelectronico());
							listadocentesinscritos.setNrotelefono(obj.getNrotelefono());
							listadocentesinscritos.setGenero(obj.getGenero().getDescripcion());
							listadocentesinscritos.setFecha_registro(obj.getFecha_registro());
							arrayie.add(listadocentesinscritos);
						});
					});
				});
			});
		}
		return new ResponseEntity<List<ListaDocenteInscritos>>(arrayie, HttpStatus.OK);
	}
	
	@GetMapping(value = "/datosusuario/{usuario}")
	public ResponseEntity<UsuarioLdap> datosusuario(@PathVariable("usuario") String usuario , Model model) throws Exception{
		
		Ldap mildap = new Ldap();
		List<UsuarioLdap> lista = mildap.listarTodosUsuariosLDAP();
		lista.forEach(obj->{
			if(obj.getCuenta().equals( usuario))
				usuarioldap = obj;
		});
		return new ResponseEntity<UsuarioLdap>(usuarioldap, HttpStatus.OK) ;
	}
	
	@GetMapping("/listtrabajospendientes")
	public List<String> listTrabajosPendientes(){
		return  progeducService.listCentrosEducativosGroupbyCodmod();
	}
	
	@GetMapping("/listatrabpendientesasignados")
	public ResponseEntity<List<ListaTrabajosFinalesPendientes>>listTrabajosPendientesAsignados(HttpSession ses){
		Integer userAlianzaId = Integer.parseInt(ses.getAttribute("userId").toString());
		
		List<TrabajosfinalesUsuarioAlianza> listTrabAsignados = trabajosFinales_UsuarioAlianzaServ.listaTrabajosIdByUsuarioId(userAlianzaId);
		List<TrabajosfinalesUsuarioAlianzaNacional> listTrabAsignadosNacional = trabajosFinales_UsuarioAlianzaNacionalServ.listaTrabajosIdByUsuarioId(userAlianzaId);
		List<ListaTrabajosFinalesPendientes> lista = new ArrayList<ListaTrabajosFinalesPendientes>();

		for (TrabajosfinalesUsuarioAlianzaNacional trabajosfinalesUsuarioAlianzaNacional : listTrabAsignadosNacional) {
			TrabajosfinalesUsuarioAlianza tra = new TrabajosfinalesUsuarioAlianza();
			tra.setUsuarioalianza(trabajosfinalesUsuarioAlianzaNacional.getUsuarioalianza());
			tra.setTrabajosfinales(trabajosfinalesUsuarioAlianzaNacional.getTrabajosfinales());
			tra.setNota(trabajosfinalesUsuarioAlianzaNacional.getNota());
			listTrabAsignados.add(tra);
		}
		
		listTrabAsignados.forEach(data->{
			List<TrabajosfinalesParticipante> listaTrabajosParticipante = trabajosfinalesparticipanteServ.listar(data.getTrabajosfinales().getId());
			Participante participante = participanteService.ListarporId(listaTrabajosParticipante.get(0).getParticipante().getId());
			Evaluacion eval = evaluacionService.getPorAnioCategoriaNivelparticipacion(data.getTrabajosfinales().getAnio(), 
					data.getTrabajosfinales().getCategoriatrabajo().getId(), participante.getGradoestudiante().getNivelgradopartid());
			List<EvaluacionResultado> listEvaResultado = evaluacionRespuestaServ.listaEvaluacionResultado(data.getTrabajosfinales().getId(),userAlianzaId);
			List<EvaluacionResultadoNacional> listEvaResultadoN = evaluacionRespuestaNacionalServ.listaEvaluacionResultado(data.getTrabajosfinales().getId(),userAlianzaId);
			log.info(data.getTrabajosfinales().getId()+"-"+data.getTrabajosfinales().getEstadotrabajo().getId());
			if(eval != null && listEvaResultado.size() == 0 && listEvaResultadoN.size() == 0 && data.getTrabajosfinales().getEnviado() == 1 && 
					(
							(data.getTrabajosfinales().getEstadotrabajo().getId() == 2 || data.getTrabajosfinales().getEstadotrabajo().getId() == 21)
					 ||
					
							(data.getTrabajosfinales().getEstadonacional().getId() == 2 || data.getTrabajosfinales().getEstadonacional().getId() == 21)
					) 
				) {
				String strOds = "";
				listaTrabajosFinalesPendientes = new ListaTrabajosFinalesPendientes();
				listaTrabajosFinalesPendientes.setAnio(data.getTrabajosfinales().getAnio());
				listaTrabajosFinalesPendientes.setCodigo(data.getTrabajosfinales().getProgramaeducativo().getCodmod()+"_"+data.getTrabajosfinales().getNumeracion());
				listaTrabajosFinalesPendientes.setOds(odsserv.byOds(data.getTrabajosfinales().getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
				listaTrabajosFinalesPendientes.setIiee(data.getTrabajosfinales().getProgramaeducativo().getCodmod());
				listaTrabajosFinalesPendientes.setCategoria(data.getTrabajosfinales().getCategoriatrabajo().getDescripcion());
				listaTrabajosFinalesPendientes.setModalidad(data.getTrabajosfinales().getModalidadtrabajo().getDescripcion());
				listaTrabajosFinalesPendientes.setTitulotrabajo(data.getTrabajosfinales().getTitulotrabajo());
				listaTrabajosFinalesPendientes.setNivelparticipacion(participante.getGradoestudiante().getNivelgradopartdesc());
				listaTrabajosFinalesPendientes.setEvaluacion("ficha trabajo");
				listaTrabajosFinalesPendientes.setTrabajo(data.getTrabajosfinales().getId().toString());
				listaTrabajosFinalesPendientes.setFichatrabajo("ficha trabajo");
				lista.add(listaTrabajosFinalesPendientes);
			}
		});
		return new ResponseEntity<List<ListaTrabajosFinalesPendientes>>(lista, HttpStatus.OK) ;
	}
	
	@GetMapping("/listatrabevaluados")
	public ResponseEntity<List<ListaTrabajosFinalesPendientes>>listTrabajosEvaluados(HttpSession ses){
		Integer userAlianzaId = Integer.parseInt(ses.getAttribute("userId").toString());
		
		List<TrabajosfinalesUsuarioAlianza> listTrabAsignados = trabajosFinales_UsuarioAlianzaServ.listaTrabajosIdByUsuarioId(userAlianzaId);
		List<TrabajosfinalesUsuarioAlianzaNacional> listTrabAsignadosNacional = trabajosFinales_UsuarioAlianzaNacionalServ.listaTrabajosIdByUsuarioId(userAlianzaId);
		List<ListaTrabajosFinalesPendientes> lista = new ArrayList<ListaTrabajosFinalesPendientes>();
		
		for (TrabajosfinalesUsuarioAlianzaNacional trabajosfinalesUsuarioAlianzaNacional : listTrabAsignadosNacional) {
			TrabajosfinalesUsuarioAlianza tra = new TrabajosfinalesUsuarioAlianza();
			tra.setUsuarioalianza(trabajosfinalesUsuarioAlianzaNacional.getUsuarioalianza());
			tra.setTrabajosfinales(trabajosfinalesUsuarioAlianzaNacional.getTrabajosfinales());
			tra.setNota(trabajosfinalesUsuarioAlianzaNacional.getNota());
			listTrabAsignados.add(tra);
		}
		
		listTrabAsignados.forEach(data->{
			List<TrabajosfinalesParticipante> listaTrabajosParticipante = trabajosfinalesparticipanteServ.listar(data.getTrabajosfinales().getId());
			Participante participante = participanteService.ListarporId(listaTrabajosParticipante.get(0).getParticipante().getId());
			Evaluacion eval = evaluacionService.getPorAnioCategoriaNivelparticipacion(data.getTrabajosfinales().getAnio(), 
					data.getTrabajosfinales().getCategoriatrabajo().getId(), participante.getGradoestudiante().getNivelgradopartid());
			List<EvaluacionResultado> listEvaResultado = evaluacionRespuestaServ.listaEvaluacionResultado(data.getTrabajosfinales().getId(),userAlianzaId);
			List<EvaluacionResultadoNacional> listEvaResultadoN = evaluacionRespuestaNacionalServ.listaEvaluacionResultado(data.getTrabajosfinales().getId(),userAlianzaId);
			if(eval != null &&  (listEvaResultado.size() > 0  || listEvaResultadoN.size() > 0) && data.getTrabajosfinales().getEnviado() == 1) {
				System.out.println("eval: "+eval+" | listEvaResultado: "+data.getTrabajosfinales().getId());
				listaTrabajosFinalesPendientes = new ListaTrabajosFinalesPendientes();
				listaTrabajosFinalesPendientes.setAnio(data.getTrabajosfinales().getAnio());
				listaTrabajosFinalesPendientes.setCodigo(data.getTrabajosfinales().getProgramaeducativo().getCodmod()+"_"+data.getTrabajosfinales().getNumeracion());
				listaTrabajosFinalesPendientes.setOds(odsserv.byOds(data.getTrabajosfinales().getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
				listaTrabajosFinalesPendientes.setIiee(data.getTrabajosfinales().getProgramaeducativo().getCodmod());
				listaTrabajosFinalesPendientes.setCategoria(data.getTrabajosfinales().getCategoriatrabajo().getDescripcion());
				listaTrabajosFinalesPendientes.setModalidad(data.getTrabajosfinales().getModalidadtrabajo().getDescripcion());
				listaTrabajosFinalesPendientes.setTitulotrabajo(data.getTrabajosfinales().getTitulotrabajo());
				listaTrabajosFinalesPendientes.setNivelparticipacion(participante.getGradoestudiante().getNivelgradopartdesc());
				listaTrabajosFinalesPendientes.setEvaluacion("ficha trabajo");
				listaTrabajosFinalesPendientes.setTrabajo(data.getTrabajosfinales().getId().toString());
				listaTrabajosFinalesPendientes.setFichatrabajo("ficha trabajo");
				lista.add(listaTrabajosFinalesPendientes);
			}
		});	
		return new ResponseEntity<List<ListaTrabajosFinalesPendientes>>(lista, HttpStatus.OK) ;
	}
	
	@PostMapping(value="/saveusuarioalianza")
	public Integer registrarusuarioalianza(@Valid @RequestBody UsuarioAlianzaDto dto) {
		System.out.println(dto.getId());
		UsuarioAlianza usu = usuAlianzaserv.ListarporId(dto.getId());
		Calendar cal= Calendar.getInstance();
		int respuesta = 0;
		if(usu != null) {
			usu.setOds(dto.getOds());
			if(dto.getCategoria().getId() != 0) {
				usu.setCategoria(dto.getCategoria());
			}
			
			usu.setEntidad(dto.getEntidad());
			usu.setDireccion(dto.getDireccion());
			usu.setComitetecnico(dto.getComitetecnico());
			usu.setComiteevaluador(dto.getComiteevaluador());
			usu.setAuspiciador(dto.getAuspiciador());
			usu.setAliado(dto.getAliado());
			usu.setNumcontaccto(dto.getNumcontaccto());
			usu.setApepatcontacto(dto.getApepatcontacto());
			usu.setApematcontacto(dto.getApematcontacto());
			usu.setNombrecontacto(dto.getNombrecontacto());
			if(dto.getTipodocumento().getId() != 0) {
				usu.setTipodocumento(dto.getTipodocumento());
			}else {
				usu.setTipodocumento(null);
			}
			usu.setNumdocumento(dto.getNumdocumento());
			usu.setTelefonouno(dto.getTelefonouno());
			usu.setTelefonodos(dto.getTelefonodos());
			usu.setCorreocontato(dto.getCorreocontato());
			usu.setCargocontacto(dto.getCargocontacto());
			if(!dto.getApepatautoridad().equals("")) {
				usu.setApepatautoridad(dto.getApepatautoridad());
				usu.setApematautoridad(dto.getApematautoridad());
				usu.setNombresautoridad(dto.getNombresautoridad());
				usu.setCorreoautoridad(dto.getCorreoautoridad());
				usu.setCargoautoridad(dto.getCargoautoridad());
				usu.setUsuarioautoridad(dto.getUsuarioautoridad());
				usu.setPasswordautoridad(dto.getPasswordautoridad());
				usu.setEnviaroficio(dto.getEnviaroficio());
				if(dto.getEnviaroficio().equals("1")) {
					usu.setNumoficio(dto.getNumoficio());
					usu.setFecha_oficio(dto.getFecha_oficio());
					if(!dto.getDocoficio().isEmpty()) {
						usu.setDocoficio(dto.getDocoficio());
					}
				}
				
				/*enviar correo*/
				String msj = "";
				msj += "<p>Estimado(a), usted ha sido agregado como evaluador:</p>";
				msj += "<p>Usuario: "+dto.getUsuarioautoridad()+"</p>";
				msj += "<p>Contraseña: "+dto.getPasswordautoridad()+"</p>";				
				mail = new Mail();
				mail.enviarCorreoTrabajosFinalesConcursoEscolar("Credenciales de Usuario", msj, dto.getCorreoautoridad());
				
			}
			
			
			auspicioServ.eliminarAuspocioByUsuarioId(usu.getId());
			if(dto.getAuspicios().size() > 0) {
				List<Auspicio> listAus = dto.getAuspicios();
				for (Auspicio auspicio : listAus) {
					Auspicio aus = auspicioServ.ListarporId(auspicio.getId());
					if(aus != null) {
						//aus.setTipodocumento(auspicio.getTipodocumento());
						aus.setCantidad(auspicio.getCantidad());
						aus.setDescripcion(auspicio.getDescripcion());
						aus.setMontounitario(auspicio.getMontounitario());
						aus.setMontototal(auspicio.getMontototal());
						aus.setUsuario_alianza(usu);
						aus.setFecha_registro(new Timestamp(new Date().getTime()));
						auspicioServ.registrar(aus);
					}else {
						aus = new Auspicio();
						//aus.setTipodocumento(auspicio.getTipodocumento());
						aus.setCantidad(auspicio.getCantidad());
						aus.setDescripcion(auspicio.getDescripcion());
						aus.setMontounitario(auspicio.getMontounitario());
						aus.setMontototal(auspicio.getMontototal());
						aus.setUsuario_alianza(usu);
						aus.setFecha_registro(new Timestamp(new Date().getTime()));
						auspicioServ.registrar(aus);
					}
				}
			}
			
			usuAlianzaserv.modificar(usu);
			respuesta = usu.getId();
			
			
		}else {
			usu = new UsuarioAlianza();
			usu.setOds(dto.getOds());
			usu.setAnio(cal.get(Calendar.YEAR));
			if(dto.getCategoria().getId() != 0) {
				usu.setCategoria(dto.getCategoria());
			}
			usu.setEntidad(dto.getEntidad());
			usu.setDireccion(dto.getDireccion());
			usu.setComitetecnico(dto.getComitetecnico());
			usu.setComiteevaluador(dto.getComiteevaluador());
			usu.setAuspiciador(dto.getAuspiciador());
			usu.setAliado(dto.getAliado());
			usu.setNumcontaccto(dto.getNumcontaccto());
			usu.setApepatcontacto(dto.getApepatcontacto());
			usu.setApematcontacto(dto.getApematcontacto());
			usu.setNombrecontacto(dto.getNombrecontacto());
			if(dto.getTipodocumento().getId() != 0) {
				usu.setTipodocumento(dto.getTipodocumento());
			}else {
				usu.setTipodocumento(null);
			}
			usu.setNumdocumento(dto.getNumdocumento());
			usu.setTelefonouno(dto.getTelefonouno());
			usu.setTelefonodos(dto.getTelefonodos());
			usu.setCorreocontato(dto.getCorreocontato());
			usu.setCargocontacto(dto.getCargocontacto());
			
			if(!dto.getApepatautoridad().equals("")) {
				usu.setApepatautoridad(dto.getApepatautoridad());
				usu.setApematautoridad(dto.getApematautoridad());
				usu.setNombresautoridad(dto.getNombresautoridad());
				usu.setCorreoautoridad(dto.getCorreoautoridad());
				usu.setCargoautoridad(dto.getCargoautoridad());
				usu.setUsuarioautoridad(dto.getUsuarioautoridad());
				usu.setPasswordautoridad(dto.getPasswordautoridad());
				usu.setEnviaroficio(dto.getEnviaroficio());
				if(dto.getEnviaroficio().equals("1")) {
					usu.setNumoficio(dto.getNumoficio());
					usu.setFecha_oficio(dto.getFecha_oficio());
					usu.setDocoficio(dto.getDocoficio());
				}
				
				/*enviar correo*/
				String msj = "";
				msj += "<p>Estimado(a), usted ha sido agregado como evaluador:</p>";
				msj += "<p>Usuario: "+dto.getUsuarioautoridad()+"</p>";
				msj += "<p>Contraseña: "+dto.getPasswordautoridad()+"</p>";				
				mail = new Mail();
				mail.enviarCorreoTrabajosFinalesConcursoEscolar("Credenciales de Usuario", msj, dto.getCorreoautoridad());
			}
			
			usu.setAuspicios(dto.getAuspicios());
			usu.setFecha_registro(new Timestamp(new Date().getTime()));
			usu.setEstado("1");
			/*List<Auspicio> listAux = new ArrayList<Auspicio>();
			if(dto.getAuspicios().size() > 0) {
				listAux = dto.getAuspicios();
				usu.setAuspicios( new ArrayList<Auspicio>());
			}*/
			UsuarioAlianza usu2 = usuAlianzaserv.registrar(usu);
			
			/*usu2.setAuspicios(listAux);
			
			UsuarioAlianza usu3 = usuAlianzaserv.ListarporId(usu2.getId());
			usu3.setAuspicios(listAux);*/
			if(dto.getAuspicios().size() > 0) {
				List<Auspicio> listAus = dto.getAuspicios();
				for (Auspicio auspicio : listAus) {
					Auspicio aus = new Auspicio();
					//aus.setTipodocumento(auspicio.getTipodocumento());
					aus.setCantidad(auspicio.getCantidad());
					aus.setDescripcion(auspicio.getDescripcion());
					aus.setMontounitario(auspicio.getMontounitario());
					aus.setMontototal(auspicio.getMontototal());
					aus.setUsuario_alianza(usu2);
					aus.setFecha_registro(new Timestamp(new Date().getTime()));
					auspicioServ.registrar(aus);
				}
			}
			respuesta = usu2.getId();
		}
		return respuesta;
	}
	
	@GetMapping("/formactualizarusuario/{id}")
	public ResponseEntity<UsuarioAlianza> formactualizarusuario(@PathVariable("id") Integer id) {
		UsuarioAlianza usu = usuAlianzaserv.ListarporId(id);
		if(usu == null) {
			usu = new UsuarioAlianza();
		}else {
			if(usu.getFecha_oficio() != null) {
				usu.setFechadocformat(new SimpleDateFormat("dd/MM/yyyy").format(usu.getFecha_oficio()));
			}else {
				usu.setFechadocformat("");
			}
			usuAlianzaserv.registrar(usu);
		}
		
		return new ResponseEntity<UsuarioAlianza>(usu, HttpStatus.OK) ;
	}
	
	@GetMapping("/cambiarestado/{id}")
	public Integer editarEstado(@PathVariable("id") Integer id) {
		int result = 0;
		UsuarioAlianza usu = usuAlianzaserv.ListarporId(id);
		if(usu != null) {
			if(usu.getEstado().equals("1")) {
				usu.setEstado("0");
			}else {
				usu.setEstado("1");
			}
			usuAlianzaserv.registrar(usu);
			result = 1;
		}
		
		return result;
	}
	
	
	@GetMapping(value="/listaalianzaestrategica")
	public ResponseEntity<List<DetalleUsuarioAlianzaEstrategica>> listausuariosAlianza(HttpSession ses){
		
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
		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());	
		List<DetalleUsuarioAlianzaEstrategica> lista = new ArrayList<DetalleUsuarioAlianzaEstrategica>();
		
		if(tipousuarioid == 0){
			Object ob = ses.getAttribute("odsid");
			List<UsuarioAlianza> listaUsu = usuAlianzaserv.listarByOds(Integer.parseInt(ob.toString()));
			listaUsu.forEach(obj->{
				banderaOds = false;				
				listaOds.forEach(objOds->{
					if(objOds.getId().equals(obj.getOds().getId())) {
						banderaOds = true;
					}
				});
				if(banderaOds) {
					DetalleUsuarioAlianzaEstrategica dto = new DetalleUsuarioAlianzaEstrategica();
					dto.setId(obj.getId());
					dto.setAnio(obj.getAnio());
					dto.setOds(obj.getOds().getDescripcion());
					if(obj.getCategoria() != null)
					dto.setCategoria(obj.getCategoria().getDescripcion());
					dto.setEntidad(obj.getEntidad());
					dto.setComiteTecnico(obj.getComitetecnico());
					dto.setComiteEvaluador(obj.getComiteevaluador());
					dto.setAuspiciador(obj.getAuspiciador());
					dto.setAliado(obj.getAliado());
					dto.setEstado(obj.getEstado());				
					lista.add(dto);
				}
			});
		}
		else if (tipousuarioid==30){
			List<UsuarioAlianza> listaUsu= usuAlianzaserv.listar();	
			listaUsu.forEach(obj->{
				banderaOds = false;				
				listaOds.forEach(objOds->{
					if(objOds.getId().equals(obj.getOds().getId())) {
						banderaOds = true;
					}
				});
				if(banderaOds) {
					DetalleUsuarioAlianzaEstrategica dto = new DetalleUsuarioAlianzaEstrategica();
					dto.setId(obj.getId());
					dto.setAnio(obj.getAnio());
					dto.setOds(obj.getOds().getDescripcion());
					if(obj.getCategoria() != null)
					dto.setCategoria(obj.getCategoria().getDescripcion());
					dto.setEntidad(obj.getEntidad());
					dto.setComiteTecnico(obj.getComitetecnico());
					dto.setComiteEvaluador(obj.getComiteevaluador());
					dto.setAuspiciador(obj.getAuspiciador());
					dto.setAliado(obj.getAliado());
					dto.setEstado(obj.getEstado());
					lista.add(dto);
				}
			});
		}
		else {
			String usuario = ses.getAttribute("usuario").toString();
			Usuario user = usuarioServ.byUsuario(usuario);
			usuarioodsServ.listarByUsuario(user.getId()).forEach(usu->{				
				List<UsuarioAlianza> listaUsu = usuAlianzaserv.listarByOds(usu.getOds().getId());
				listaUsu.forEach(obj->{
					banderaOds = false;				
					listaOds.forEach(objOds->{
						if(objOds.getId().equals(obj.getOds().getId())) {
							banderaOds = true;
						}
					});
					if(banderaOds) {
						DetalleUsuarioAlianzaEstrategica dto = new DetalleUsuarioAlianzaEstrategica();
						dto.setId(obj.getId());
						dto.setAnio(obj.getAnio());
						dto.setOds(obj.getOds().getDescripcion());
						if(obj.getCategoria() != null)
						dto.setCategoria(obj.getCategoria().getDescripcion());
						dto.setEntidad(obj.getEntidad());
						dto.setComiteTecnico(obj.getComitetecnico());
						dto.setComiteEvaluador(obj.getComiteevaluador());
						dto.setAuspiciador(obj.getAuspiciador());
						dto.setAliado(obj.getAliado());
						dto.setEstado(obj.getEstado());
						lista.add(dto);
					}					
				});
			});			
		}
		return new ResponseEntity<List<DetalleUsuarioAlianzaEstrategica>>(lista, HttpStatus.OK) ;
	}
	
	@PostMapping(value="/subirarchivousuarios")
	public Integer subirarchivousuarios(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) {
		
		if(file.isEmpty())
			return 0;
		try {
			uploadfile.saveFile(file,id,"upload_usuarios_documentos");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1; 
	}
	
	@PostMapping(value="/saveasignarevaluador")
	public Integer saveasignarevaluador(@Valid @RequestBody AsignarEvaluadoresDto dto) {
		
		rpta=2;
		try {			
			dto.getTrabajos_evaluados().forEach(te->{
				dto.getEvaluadores().forEach(ev->{
					if(trabajosFinales_UsuarioAlianzaServ.buscar(te.getId(),ev.getId()) == null) {
						trabajosFinales_UsuarioAlianzaServ.guardar(te.getId(),ev.getId(),-1f);
						trabajosfinalesServ.updateEstadoTrabajo(te.getId(),2);
						
						Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(te.getId());
						trabajoFinal.setNota(0f);
						trabajosfinalesServ.modificar(trabajoFinal);
						
						rpta = 1;
					}
				});
			});
			return rpta;
		}
		catch(Exception exc) {
			return 0;
		}
	}
	
	@PostMapping(value="/registrarRespuestasEvaluacion")
	public Integer registrarRespuestasEvaluacion(@Valid @RequestBody List<EvaluacionResultado> lista,HttpSession ses) {
		
		try {
			
			Integer userAlianzaId = Integer.parseInt(ses.getAttribute("userId").toString());
			UsuarioAlianza usuAlianza = new UsuarioAlianza();
			usuAlianza.setId(userAlianzaId);
			
			Trabajosfinales  trabajof =trabajosfinalesServ.ListarporId(lista.get(0).getTrabajosfinales().getId());
			
			if(trabajof.getEstadonacional().getId() == 2) {
				totalNota = 0;
				lista.forEach(obj->{
					EvaluacionResultadoNacional resultado = new EvaluacionResultadoNacional();
					resultado.setPreguntaid(obj.getPreguntaid());
					resultado.setRespuestaid(obj.getRespuestaid());
					resultado.setTrabajosfinales(obj.getTrabajosfinales());
					resultado.setTipo(obj.getTipo());
					resultado.setPuntaje(obj.getPuntaje());
					resultado.setUsuario(usuAlianza);
					evaluacionRespuestaNacionalServ.registrar(resultado);
					
					totalNota += obj.getPuntaje();
					idTrab = obj.getTrabajosfinales().getId();
				});
				
				trabajosFinales_UsuarioAlianzaNacionalServ.actualizarNotaPorParticiante(idTrab, userAlianzaId, totalNota);

				if(trabajosFinales_UsuarioAlianzaNacionalServ.listarTrabajosFinalesSinNota(idTrab).size() == 0) {
					Trabajosfinales  trab =trabajosfinalesServ.ListarporId(idTrab);

					List<TrabajosfinalesUsuarioAlianzaNacional>  trabajosEvaluados = trabajosFinales_UsuarioAlianzaNacionalServ.listarByTrabajosfinalesId(idTrab);
					
					int numeroTrabajasEvaluados =  trabajosEvaluados.size();
					float sumaNotas = 0.0f;
					
					
					for (TrabajosfinalesUsuarioAlianzaNacional trabajosfinalesUsuarioAlianza : trabajosEvaluados) {
						sumaNotas += trabajosfinalesUsuarioAlianza.getNota();
					}
					
					trab.setNota_nacional(sumaNotas/numeroTrabajasEvaluados);
					trabajosfinalesServ.modificar(trab);
				}
			}else {
				totalNota = 0;
				lista.forEach(obj->{
					EvaluacionResultado resultado = new EvaluacionResultado();
					resultado.setPreguntaid(obj.getPreguntaid());
					resultado.setRespuestaid(obj.getRespuestaid());
					resultado.setTrabajosfinales(obj.getTrabajosfinales());
					resultado.setTipo(obj.getTipo());
					resultado.setPuntaje(obj.getPuntaje());
					resultado.setUsuario(usuAlianza);
					evaluacionRespuestaServ.registrar(resultado);
					
					totalNota += obj.getPuntaje();
					idTrab = obj.getTrabajosfinales().getId();
				});
				
				trabajosFinales_UsuarioAlianzaServ.actualizarNotaPorParticiante(idTrab, userAlianzaId, totalNota);

				if(trabajosFinales_UsuarioAlianzaServ.listarTrabajosFinalesSinNota(idTrab).size() == 0) {
					Trabajosfinales  trab =trabajosfinalesServ.ListarporId(idTrab);

					List<TrabajosfinalesUsuarioAlianza>  trabajosEvaluados = trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(idTrab);
					
					int numeroTrabajasEvaluados =  trabajosEvaluados.size();
					float sumaNotas = 0.0f;
					
					
					for (TrabajosfinalesUsuarioAlianza trabajosfinalesUsuarioAlianza : trabajosEvaluados) {
						sumaNotas += trabajosfinalesUsuarioAlianza.getNota();
					}
					
					trab.setNota(sumaNotas/numeroTrabajasEvaluados);
					trabajosfinalesServ.modificar(trab);
				}
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("ERROR: ",e);
			return 0;
		}
		
		return 1;
	}

	@GetMapping("/getRespuestas/{id}")
	public ResponseEntity<List<EvaluacionResultado>>getRespuestas(@PathVariable("id") Integer id,HttpSession ses){
		Integer userAlianzaId = Integer.parseInt(ses.getAttribute("userId").toString());
		List<EvaluacionResultado> lista = evaluacionRespuestaServ.listaEvaluacionResultado(id,userAlianzaId);
		return new ResponseEntity<List<EvaluacionResultado>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/getTipoAuspicio")
	public ResponseEntity<List<TipoAuspicio>>getRespuestas(HttpSession ses){
		List<TipoAuspicio> lista = tipoAuspicioServ.listar();
		return new ResponseEntity<List<TipoAuspicio>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/opneModalTrabajosPermisos/{id}")
	public ResponseEntity<List<DataTrabajosPermisos>>getTrabajoPermisos(@PathVariable("id") Integer id){
		
		List<TrabajosfinalesParticipante> listaTrabajosParticipante = trabajosfinalesparticipanteServ.listar(id);
		
		List<DataTrabajosPermisos> lista = new ArrayList<DataTrabajosPermisos>();
		
		int count = 1;
		
		for (TrabajosfinalesParticipante  dataTrabajosPermisos : listaTrabajosParticipante) {
			DataTrabajosPermisos participante = new DataTrabajosPermisos();
			participante.setIdParticiapnte(dataTrabajosPermisos.getParticipante().getId());
			participante.setNombreArchivo("Permiso"+count);
			count++;
			lista.add(participante);
		}

		return new ResponseEntity<List<DataTrabajosPermisos>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/consultarEtapaHabilitada")
	public ResponseEntity<OdsFinalizarDto> consultarEtapaHabilitada(HttpSession ses){
		OdsFinalizarDto odsRespuesta = new OdsFinalizarDto();
		String odsId = ses.getAttribute("odsid") != null ? ses.getAttribute("odsid").toString() : null;
		String tipousuarioid = ses.getAttribute("tipousuarioid") != null ? ses.getAttribute("tipousuarioid").toString() : null;
		String usuario = ses.getAttribute("usuario") != null ? ses.getAttribute("usuario").toString() : null;
		Calendar cal= Calendar.getInstance();
		int anio= cal.get(Calendar.YEAR);
		Aperturaranio apertura = aperturaranioService.buscar(anio);
		LocalDate dateActual = LocalDate.now();

		if(apertura.getQuintaetapadesde().isBefore(dateActual) || apertura.getQuintaetapadesde().isEqual(dateActual)) {
			odsRespuesta.setStatus(1);
			if(odsId != null && tipousuarioid != null && (tipousuarioid.equals("0")) ) {
				List<Ods> listOds = new ArrayList<>();

				if(tipousuarioid.equals("2")) {
					Optional<Ods> ods = odsserv.listarAll().stream().filter(o -> o.getId() == Integer.parseInt(odsId)).findFirst();
					listOds.add(ods.get());
					odsRespuesta.setListOds(listOds);
				}else {
					Optional<Ods> ods = odsserv.listarAll().stream().filter(o -> o.getId() == usuarioServ.byUsuario(ses.getAttribute("usuario").toString()).getOdsid()).findFirst();
					listOds.add(ods.get());
					odsRespuesta.setListOds(listOds);
				}
				
				
				List<CerrarOds> listCerrarOds = new ArrayList<>();
				Optional<CerrarOds> cerrarOds = cerrarOdsServ.listCerrarOds().stream().filter(o -> o.getOdsid().getId() == Integer.parseInt(odsId)).findFirst();
				if(!cerrarOds.isEmpty()) listCerrarOds.add(cerrarOds.get());
				odsRespuesta.setListCerrarOds(listCerrarOds);
			}else if(tipousuarioid != null && (tipousuarioid.equals("0") || tipousuarioid.equals("2") || tipousuarioid.equals("11")) ) {
				List<Ods> listOds = new ArrayList<>();
				Usuario usuarioLogin = usuarioServ.byUsuario(ses.getAttribute("usuario").toString());
				List<UsuarioOds> listUsuOds = usuarioodsServ.listarByUsuario(usuarioLogin.getId());
				for (UsuarioOds usuarioOds : listUsuOds) {
					listOds.add(usuarioOds.getOds());
				}
				odsRespuesta.setListOds(listOds);
				
				List<CerrarOds> listCerrarOds = new ArrayList<>();
				List<CerrarOds> listCerrarOdsTotales = cerrarOdsServ.listCerrarOds();
				if(!listCerrarOdsTotales.isEmpty()) {
					for (int i = 0; i < listCerrarOdsTotales.size(); i++) {
						for (int j = 0; j < listOds.size(); j++) {
							if(listOds.get(j).getId() == listCerrarOdsTotales.get(i).getOdsid().getId() ) {
								listCerrarOds.add(listCerrarOdsTotales.get(i));
							}
						}
					}
					
				}
				odsRespuesta.setListCerrarOds(listCerrarOds);
			}else {
				odsRespuesta.setListOds(odsserv.listarAll());
				odsRespuesta.setListCerrarOds(cerrarOdsServ.listCerrarOds());
			}
		}else {
			odsRespuesta.setStatus(0);
		}
		return new ResponseEntity<OdsFinalizarDto>(odsRespuesta, HttpStatus.OK);
	}
	
	@PostMapping(value="/cerrarOds")
	public Integer cerrarOds(@Valid @RequestBody List<Ods> listOds,HttpSession ses) {
		
		try {

			Calendar cal= Calendar.getInstance();
			int anio= cal.get(Calendar.YEAR);
			
			List<CerrarOds> lisCerrarOds = cerrarOdsServ.listCerrarOds();
			
			for (Ods ods : listOds) {
				CerrarOds cerrarOds = new CerrarOds();
				cerrarOds.setOdsid(ods);
				cerrarOds.setAnio(anio);
				cerrarOds.setEtapa(1);
				cerrarOds.setEstado(1);//finalizado
				
				for (CerrarOds cerrarOds2 : lisCerrarOds) {
					if(cerrarOds2.getOdsid().getId() == cerrarOds.getOdsid().getId()) {//si existe el registro actulizar
						cerrarOds = cerrarOds2;
						cerrarOds.setEstado(1);//finalizado
					}
				}
				
				cerrarOds = cerrarOdsServ.guardar(cerrarOds);
				
				/*lista de trabajos finales por ODS*/
				List<Trabajosfinales>  trabajoFinales = trabajosfinalesServ.listarTrabajosfinalesPorOds(ods.getId());
				for (Trabajosfinales trabajos : trabajoFinales) {
					Estadotrabajo estadoTrabajo = new  Estadotrabajo();
					estadoTrabajo.setId(3);
					trabajos.setEstadotrabajo(estadoTrabajo);
					trabajosfinalesServ.modificar(trabajos);
				}
				
				List<CerrarOds> listCerrarOds = cerrarOdsServ.listCerrarOds();//lista de ODS cerradas
				
				/*Listar Categorias X Nivel de Participacion de ODS*/
				//List<CategoriaModalidadByOds> listaCatModByOds = trabajosfinalesServ.listarCategoriaModalidadByOds(ods.getId());
				List<CategoriaNivelParticipacionByOds> listaCatNivByOds = trabajosfinalesServ.listarCategoriaNivelByOds(ods.getId());
				
				for (CategoriaNivelParticipacionByOds catModByODs : listaCatNivByOds) {
					log.info("CAREGORIAID: "+catModByODs.getCategoriaId() +" | NIVEL: "+catModByODs.getNivelId());
					/*Lista de trabajos Por Categoria y Nivel con Nota Promedio*/
					List<TrabajosFinalizados> listaTrab = trabajosfinalesServ.listaTrabajosFinalesConNotaPromedioPorCategoriaNivelOds(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId());
					/*Lista de empatados*/
					List<TrabajosFinalizados> listaTrabEmpatados = trabajosfinalesServ.listaTrabajosFinalesConNotaPromedioPorCategoriaNivelOdsEmpatados(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId());
					
					/*SECTION EMPATES*/
					if(listaTrabEmpatados.size() > 0) {
						float NotaPuesto1 = listaTrab.get(0).getNota(); //primera nota
						int puestoEmpate = 0;
						int cantidad = 0;
						/*BuscarEmpate*/
						for (TrabajosFinalizados trabajos : listaTrabEmpatados) {
							if(trabajos.getNota() == NotaPuesto1 ) {
								Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trabajos.getTrabajoId());
								/*trabajoFinal.setPuesto(1);
								trabajosfinalesServ.modificar(trabajoFinal);*/
								puestoEmpate = trabajoFinal.getPuesto();//PUESTO DE LOS EMPATADOS
								cantidad++;
							}
						}
						
						if(true) {//if(cantidad <= 1) {//cantidad de empatados <= 1 (NO HAY EMPATES NUEVOS)
							int indice = 0;
							float notaPuestoNuevo = 21;
							int puestoNuevo = 0;
							int cantidadEmpateNuevo = 0;
							int empates1 =0, empates2 =0;
							boolean puestoFinalizados = false;
							//int puestoDeEmpate = 100;
							for (TrabajosFinalizados trabajos : listaTrabEmpatados) {
								Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trabajos.getTrabajoId());
								log.info("TRABAJO EMPATADO FINALIZADOS: "+trabajos.getTrabajoId() + " | ODS: "+ ods.getId() +" | "+"CAREGORIAID: "+catModByODs.getCategoriaId() +" | NIVEL: "+catModByODs.getNivelId()+" | PUESTO: "+trabajoFinal.getPuesto()+indice+" | NOTA: "+trabajoFinal.getNota());
								log.info("+++++++++++++++++++++puestoNuevo: "+puestoNuevo +" - trabajoFinal.getPuesto():"+ trabajoFinal.getPuesto());
								if(trabajos.getNota() == notaPuestoNuevo) { log.info("EMPATE NUEVO");log.info("PUESTO: "+puestoNuevo);
									cantidadEmpateNuevo++;
									if(puestoFinalizados) {
										trabajoFinal.setPuesto(0);
									}else {
										trabajoFinal.setPuesto(puestoNuevo);
									}
	
									//puestoDeEmpate = puestoNuevo;
									cerrarOds.setEstado(2);//empate
									cerrarOdsServ.guardar(cerrarOds);
									indice=0;
									if(trabajoFinal.getPuesto() == 3) {
										indice=10;
									}
									if(trabajoFinal.getPuesto() == 1) {
										empates1++;log.info("empates1: "+empates1);
									}
									if(trabajoFinal.getPuesto() == 2) {
										empates2++;log.info("empates2: "+empates2);
									}
									
								}else if(trabajos.getNota() < notaPuestoNuevo || puestoNuevo != trabajoFinal.getPuesto()){ log.info("SIN EMPATE NUEVO");log.info("PUESTO: "+trabajoFinal.getPuesto()+"+"+ indice);log.info("Puestos distintos:  "+puestoNuevo +" != "+ trabajoFinal.getPuesto());
									if(trabajoFinal.getPuesto() == 3 && indice ==2) {
										indice=0;
									}
									if(puestoNuevo > trabajoFinal.getPuesto() && cantidadEmpateNuevo > 0) {
										trabajoFinal.setPuesto(0);
										puestoFinalizados = true;
									}else {
										if((puestoNuevo + indice) > 3) {
											trabajoFinal.setPuesto(0);
											puestoFinalizados = true;
										}else {
											if(puestoNuevo < trabajoFinal.getPuesto()) indice=0;
											else indice++;
											if(trabajoFinal.getPuesto() == 3) {
												trabajoFinal.setPuesto(trabajoFinal.getPuesto()); //asignar nuevo puesto
											}else {
												trabajoFinal.setPuesto(trabajoFinal.getPuesto() + indice+empates1); //asignar nuevo puesto
											}
											puestoNuevo = trabajoFinal.getPuesto();log.info("Nuevo puesto: "+puestoNuevo);
										}
										if(trabajoFinal.getPuesto() == 3) {
											indice=10;
										}
										//indice++;
										log.info("empates1: "+empates1);
										if(empates1 > 1) {
											trabajoFinal.setPuesto(0);
											puestoFinalizados = true;
										}log.info("empates2: "+empates2);
										if(empates2 > 1) {
											trabajoFinal.setPuesto(0);
											puestoFinalizados = true;
										}
										if(cantidadEmpateNuevo > 2) {
											trabajoFinal.setPuesto(0);
											puestoFinalizados = true;
										}
										
										if(empates1 == 1) {
											if(cantidadEmpateNuevo > 1) {
												trabajoFinal.setPuesto(0);
												puestoFinalizados = true;
											}
										}
										
										if(cantidadEmpateNuevo > 2) {
											trabajoFinal.setPuesto(0);
											puestoFinalizados = true;
										}
										/*if(puestoNuevo < trabajoFinal.getPuesto()) {
											trabajoFinal.setPuesto(0);
										}*/
									}
									log.info("********************PUESTO: "+puestoNuevo);
								}
								
								
								trabajosfinalesServ.modificar(trabajoFinal);
								notaPuestoNuevo = trabajos.getNota();
							}
							
							//cambias estado a los trabajos empatados
							//List<TrabajosFinalizados> listaPuesto= trabajosfinalesServ.listaTrabajosEmpatadosPorCatNivOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId(), puestoDeEmpate);
							List<TrabajosFinalizados> listaPuesto1= trabajosfinalesServ.listaTrabajosEmpatadosPorCatNivOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId(), 1);
							List<TrabajosFinalizados> listaPuesto2= trabajosfinalesServ.listaTrabajosEmpatadosPorCatNivOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId(), 2);
							List<TrabajosFinalizados> listaPuesto3= trabajosfinalesServ.listaTrabajosEmpatadosPorCatNivOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId(), 3);
							log.info("TOTAL PUESTO 1:"+listaPuesto1.size());
							log.info("TOTAL PUESTO 2:"+listaPuesto2.size());
							log.info("TOTAL PUESTO 3:"+listaPuesto3.size());
							for (TrabajosFinalizados trab : listaPuesto1) {
								if(listaPuesto1.size() > 1) { log.info("lista 1 mayor  a 1 ");
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getTrabajoId());//borrar las respuestas de las evaluaciones;
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getTrabajoId());//borrar asignacion de evaluadores
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
		
									trabajosfinalesServ.modificar(trabajoFinal);
								}
							}
							
							for (TrabajosFinalizados trab : listaPuesto2) {
								if(listaPuesto1.size() >= 3) { log.info("YA HAY MAS DE 3 en lista 1 ");
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									trabajoFinal.setPuesto(0);
									
									trabajosfinalesServ.modificar(trabajoFinal);
								}else if(listaPuesto2.size() > 1) { log.info("lista 2 mayor  a 1 ");
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getTrabajoId());//borrar las respuestas de las evaluaciones;
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getTrabajoId());//borrar asignacion de evaluadores
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
		
									trabajosfinalesServ.modificar(trabajoFinal);
								}
							}
							for (TrabajosFinalizados trab : listaPuesto3) {
								if((listaPuesto1.size() + listaPuesto2.size()) >= 3) { log.info("YA HAY MAS DE 3 en lista 1 + lista 2 ");
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									trabajoFinal.setPuesto(0);
									
									trabajosfinalesServ.modificar(trabajoFinal);
								}else if(listaPuesto3.size() > 1){ log.info("lista 3 mayor  a 1 ");
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getTrabajoId());//borrar las respuestas de las evaluaciones;
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getTrabajoId());//borrar asignacion de evaluadores
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
		
									trabajosfinalesServ.modificar(trabajoFinal);
								}
							}
						}else {//(HAY EMPATES NUEVOS)
							listaTrab = listaTrabEmpatados;
							/*Puesto 1*/
							NotaPuesto1 = listaTrab.get(0).getNota();
							cantidad = 0;
							/*BuscarEmpate*/
							for (TrabajosFinalizados trabajos : listaTrab) {
								if(trabajos.getNota() == NotaPuesto1 ) {
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trabajos.getTrabajoId());
									trabajoFinal.setPuesto(1);
									trabajosfinalesServ.modificar(trabajoFinal);
									cantidad++;
								}
							}
							if(cantidad > 1) {
								//List<Trabajosfinales> listaPuesto1= trabajosfinalesServ.listaTrabajosEmpatadosPorCatModOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getCategoriaId()ModalidadId(), ods.getId(),1);
								List<TrabajosFinalizados> listaPuesto1= trabajosfinalesServ.listaTrabajosEmpatadosPorCatNivOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId(), 1);
								for (TrabajosFinalizados trab : listaPuesto1) {
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getTrabajoId());//borrar las respuestas de las evaluaciones;
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getTrabajoId());//borrar asignacion de evaluadores
									
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
									trabajosfinalesServ.modificar(trabajoFinal);
									
								}
								cerrarOds.setEstado(2);//empate
								cerrarOdsServ.guardar(cerrarOds);
							}
							
							/*Puesto 2*/
							int cantidad2 = 0;
							if(listaTrab.size() > cantidad && cantidad == 1) {
								float NotaPuesto2 = listaTrab.get(cantidad).getNota();
								/*BuscarEmpate*/
								for (TrabajosFinalizados trabajos : listaTrab) {
									if(trabajos.getNota() == NotaPuesto2 ) {
										Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trabajos.getTrabajoId());
										trabajoFinal.setPuesto(2);
										trabajosfinalesServ.modificar(trabajoFinal);
										cantidad2++;
									}
								}
							}
							if(cantidad2 > 1 && cantidad == 1) {
								List<TrabajosFinalizados> listaPuesto2= trabajosfinalesServ.listaTrabajosEmpatadosPorCatNivOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId(),2);
								for (TrabajosFinalizados trab : listaPuesto2) {
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getTrabajoId());
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getTrabajoId());
									
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
									trabajosfinalesServ.modificar(trabajoFinal);
								}
								cerrarOds.setEstado(2);//empate
								cerrarOdsServ.guardar(cerrarOds);
							}
							
							/*Puesto 3*/
							int cantidad3 = 0;
							if(listaTrab.size() > cantidad+cantidad2 && (cantidad+cantidad2) == 2) {
								float NotaPuesto3 = listaTrab.get(cantidad+cantidad2).getNota();
								/*BuscarEmpate*/
								for (TrabajosFinalizados trabajos : listaTrab) {
									if(trabajos.getNota() == NotaPuesto3 ) {
										Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trabajos.getTrabajoId());
										trabajoFinal.setPuesto(3);
										trabajosfinalesServ.modificar(trabajoFinal);
										cantidad3++;
									}
								}
							}
							if(cantidad3 > 1 && (cantidad+cantidad2) == 2) {
								List<TrabajosFinalizados> listaPuesto3= trabajosfinalesServ.listaTrabajosEmpatadosPorCatNivOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId(),3);
								for (TrabajosFinalizados trab : listaPuesto3) {
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getTrabajoId());
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getTrabajoId());
									
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
									trabajosfinalesServ.modificar(trabajoFinal);
								}
								cerrarOds.setEstado(2);//empate
								cerrarOdsServ.guardar(cerrarOds);
							}
							
						}
							
							
							
							
							
							/***************************************************************/
							/*int indice = 0;
							int puestoEmpateNuevo = 1;
							int cantidadPuesto1 = 0,  cantidadPuesto2 =0, cantidadPuesto3 =0;
							float notaEmpateNuevo = 0;
							
							for (TrabajosFinalizados trabajos : listaTrabEmpatados) {
								if(trabajos.getNota() != notaEmpateNuevo ) {
									cantidadPuesto1++;
									notaEmpateNuevo = trabajos.getNota();
								}
							}
							for (TrabajosFinalizados trabajos : listaTrabEmpatados) {
								if(trabajos.getNota() != notaEmpateNuevo ) {
									cantidadPuesto2++;
									notaEmpateNuevo = trabajos.getNota();
								}
							}
							for (TrabajosFinalizados trabajos : listaTrabEmpatados) {
								if(trabajos.getNota() != notaEmpateNuevo ) {
									cantidadPuesto3++;
									notaEmpateNuevo = trabajos.getNota();
								}
							}
							
							for (TrabajosFinalizados trabajos : listaTrabEmpatados) {
								evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trabajos.getTrabajoId());//borrar las respuestas de las evaluaciones;
								trabajosFinales_UsuarioAlianzaServ.eliminar(trabajos.getTrabajoId());//borrar asignacion de evaluadores
		
								if(trabajos.getNota() == NotaPuesto1 ) {//trabajo iguales a la nota mayor
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trabajos.getTrabajoId());
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
									trabajoFinal.setPuesto(puestoEmpateNuevo);
									trabajosfinalesServ.modificar(trabajoFinal);
									//indice++;
									log.info("TRABAJO EMPATADO EMPATADOS: "+trabajos.getTrabajoId() + " | ODS: "+ ods.getId() +" | "+"CAREGORIAID: "+catModByODs.getCategoriaId() +" | NIVEL: "+catModByODs.getNivelId()+" | PUESTO: "+trabajoFinal.getPuesto()+indice+" | NOTA: "+trabajoFinal.getNota());
								}else {//Trabajos menores a la nota mayor
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trabajos.getTrabajoId());
									if((puestoEmpateNuevo + indice) > 3) {
										trabajoFinal.setPuesto(0);
									}else {
										trabajoFinal.setPuesto(puestoEmpateNuevo);
									}
									//trabajoFinal.setPuesto(0);
									trabajosfinalesServ.modificar(trabajoFinal);
									indice++;
									puestoEmpateNuevo = trabajoFinal.getPuesto();
									log.info("TRABAJO EMPATADO DESCARTADOS: "+trabajos.getTrabajoId() + " | ODS: "+ ods.getId() +" | "+"CAREGORIAID: "+catModByODs.getCategoriaId() +" | NIVEL: "+catModByODs.getNivelId()+" | PUESTO: "+trabajoFinal.getPuesto()+indice+" | NOTA: "+trabajoFinal.getNota());
								}
								NotaPuesto1 = trabajos.getNota();
								
							}
							cerrarOds.setEstado(2);//empate
							cerrarOdsServ.guardar(cerrarOds);
						}*/
						
					}
					else {
						/*Obtener Puesto*/
						if(listaTrab.size() > 0) {log.info("TOTAL DE TRABAJOS: "+listaTrab.size());
							/*Puesto 1*/
							float NotaPuesto1 = listaTrab.get(0).getNota();
							int cantidad = 0;
							/*BuscarEmpate*/log.info("BUSCAR EMPATES P1: "+listaTrab.size());
							for (TrabajosFinalizados trabajos : listaTrab) {
								if(trabajos.getNota() == NotaPuesto1 ) {
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trabajos.getTrabajoId());
									trabajoFinal.setPuesto(1);
									trabajosfinalesServ.modificar(trabajoFinal);
									cantidad++;
								}
							}log.info("FINALIZACION 1 - TOTAL EMPATES P1: "+cantidad);
							if(cantidad > 1) {
								//List<Trabajosfinales> listaPuesto1= trabajosfinalesServ.listaTrabajosEmpatadosPorCatModOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getCategoriaId()ModalidadId(), ods.getId(),1);
								List<TrabajosFinalizados> listaPuesto1= trabajosfinalesServ.listaTrabajosEmpatadosPorCatNivOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId(), 1);
								for (TrabajosFinalizados trab : listaPuesto1) {
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getTrabajoId());//borrar las respuestas de las evaluaciones;
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getTrabajoId());//borrar asignacion de evaluadores
									
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
									trabajoFinal.setEmpate(1);//empate si
									trabajoFinal.setNota_original(trabajoFinal.getNota());//nota original
									trabajosfinalesServ.modificar(trabajoFinal);
									log.info("TRABAJO EMPATADO F1: "+trab.getTrabajoId() + " | ODS: "+ ods.getId() +" | "+"CAREGORIAID: "+catModByODs.getCategoriaId() +" | NIVEL: "+catModByODs.getNivelId()+" | PUESTO: "+trabajoFinal.getPuesto()+indice+" | NOTA: "+trabajoFinal.getNota());
								}
								cerrarOds.setEstado(2);//empate
								cerrarOdsServ.guardar(cerrarOds);
							}
							
							/*Puesto 2*/
							int cantidad2 = 0;
							if(listaTrab.size() > cantidad && cantidad == 1) {
								float NotaPuesto2 = listaTrab.get(cantidad).getNota();
								/*BuscarEmpate*/
								for (TrabajosFinalizados trabajos : listaTrab) {
									if(trabajos.getNota() == NotaPuesto2 ) {
										Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trabajos.getTrabajoId());
										trabajoFinal.setPuesto(2);
										trabajosfinalesServ.modificar(trabajoFinal);
										cantidad2++;
									}
								}
							}log.info("FINALIZACION 1 - TOTAL EMPATES P2: "+cantidad2);
							if(cantidad2 > 1 && cantidad == 1) {
								List<TrabajosFinalizados> listaPuesto2= trabajosfinalesServ.listaTrabajosEmpatadosPorCatNivOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId(),2);
								for (TrabajosFinalizados trab : listaPuesto2) {
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getTrabajoId());
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getTrabajoId());
									
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
									trabajoFinal.setEmpate(1);//empate si
									trabajoFinal.setNota_original(trabajoFinal.getNota());//nota original
									trabajosfinalesServ.modificar(trabajoFinal);
									log.info("TRABAJO EMPATADO F1: "+trab.getTrabajoId() + " | ODS: "+ ods.getId() +" | "+"CAREGORIAID: "+catModByODs.getCategoriaId() +" | NIVEL: "+catModByODs.getNivelId()+" | PUESTO: "+trabajoFinal.getPuesto()+indice+" | NOTA: "+trabajoFinal.getNota());
								}
								cerrarOds.setEstado(2);//empate
								cerrarOdsServ.guardar(cerrarOds);
							}
							
							/*Puesto 3*/
							int cantidad3 = 0;
							if(listaTrab.size() > cantidad+cantidad2 && (cantidad+cantidad2) == 2) {
								float NotaPuesto3 = listaTrab.get(cantidad+cantidad2).getNota();
								/*BuscarEmpate*/
								for (TrabajosFinalizados trabajos : listaTrab) {
									if(trabajos.getNota() == NotaPuesto3 ) {
										Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trabajos.getTrabajoId());
										trabajoFinal.setPuesto(3);
										trabajosfinalesServ.modificar(trabajoFinal);
										cantidad3++;
									}
								}
							}log.info("FINALIZACION 1 - TOTAL EMPATES P3: "+cantidad3);
							if(cantidad3 > 1 && (cantidad+cantidad2) == 2) {
								List<TrabajosFinalizados> listaPuesto3= trabajosfinalesServ.listaTrabajosEmpatadosPorCatNivOdsPuesto(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId(),3);
								for (TrabajosFinalizados trab : listaPuesto3) {
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getTrabajoId());
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getTrabajoId());
									
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getTrabajoId());
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
									trabajoFinal.setEmpate(1);//empate si
									trabajoFinal.setNota_original(trabajoFinal.getNota());//nota original
									trabajosfinalesServ.modificar(trabajoFinal);
									log.info("TRABAJO EMPATADO F1: "+trab.getTrabajoId() + " | ODS: "+ ods.getId() +" | "+"CAREGORIAID: "+catModByODs.getCategoriaId() +" | NIVEL: "+catModByODs.getNivelId()+" | PUESTO: "+trabajoFinal.getPuesto()+indice+" | NOTA: "+trabajoFinal.getNota());
								}
								cerrarOds.setEstado(2);//empate
								cerrarOdsServ.guardar(cerrarOds);
							}
							
						}
					}
					
					
					
				}
				
				
				
				
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("ERROR: ",e);
			return 0;
		}
		
		return 1;
	}
	
	@GetMapping(value="/trabajosfinalesconcurso/{ods_reporte}/{anio_reporte}/{modalidad_reporte}/{estado_reporte}/{categoria_reporte}/{nivel_participacion_reporte}/{nombre_ie_reporte}/{puesto_reporte}")	
	public ResponseEntity<InputStreamResource> exportParticipantes(@PathVariable(value="ods_reporte") String ods,
			@PathVariable(value="anio_reporte") String anio,
			@PathVariable(name="modalidad_reporte") String modalidad,
			@PathVariable(name="estado_reporte") String estado,
			@PathVariable(name="categoria_reporte") String categoria,
			@PathVariable(name="nivel_participacion_reporte") String nivel_participacion,
			@PathVariable(name="nombre_ie_reporte") String nombreie,
			@PathVariable(name="puesto_reporte") String puesto, HttpSession ses
			) {
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		ByteArrayInputStream stream = reportetrabajosfinalesconcurso(ods,anio,modalidad,estado,categoria,nivel_participacion,nombreie,puesto,ses);
		
		HttpHeaders headers = new HttpHeaders();
		
		String fecha_archivo = dateFormat.format(date) + hourFormat.format(date);
		
		headers.add("Content-Disposition", "attachment; filename=trabajosfinalesconcurso_"+fecha_archivo+".csv");
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		
	}
	
	public ByteArrayInputStream reportetrabajosfinalesconcurso(String ods,String anio,String modalidad,String estado,String categoria,String nivel_participacion,String nombreie,String puesto,HttpSession ses)   {
		
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();		
		
		
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
				
		List<TrabajosFinalesConcursoDto> lista = new ArrayList<TrabajosFinalesConcursoDto>();		
		List<DetalleEvaluacionReporteDto> listaDerDto = new ArrayList<>();
		trabajosfinalesparticipanteServ.listarTodos().forEach(obj->{			
			if(obj.getTrabajosfinales().getEstado() == 1 && obj.getParticipante().getEstado()==1 && obj.getTrabajosfinales().getEnviado()==1) {				
				bandera = true;				
				banderaOds = false;
				mi_ods = odsserv.byOds(obj.getTrabajosfinales().getProgramaeducativo().getDistrito().getOdsid()).getDescripcion();
				mi_anio = obj.getTrabajosfinales().getAnio();
				mi_modalidad = obj.getTrabajosfinales().getModalidadtrabajo().getDescripcion();
				mi_estado = obj.getTrabajosfinales().getEstadotrabajo().getDescripcion();
				mi_categoria = obj.getTrabajosfinales().getCategoriatrabajo().getDescripcion();
				mi_nivel_participacion = obj.getParticipante().getGradoestudiante().getNivelgradopartdesc();
				mi_puesto = obj.getTrabajosfinales().getPuesto().toString();
				
				if(! ods.equals("Todos")) {
					if(mi_ods.toLowerCase().equals(ods.toLowerCase())) {
						banderaOds = true;
					}
				}else {
					listaOds.forEach(objOds->{
						if(objOds.getDescripcion().equals(mi_ods)) {
							banderaOds = true;
						}
					});
				}
				if(! anio.equals("Todos")) {
					if(mi_anio != Integer.parseInt(anio)){
						bandera = false;
					}
				}
				if(! modalidad.equals("Todos")) {
					if(! mi_modalidad.toLowerCase().equals(modalidad.toLowerCase())){
						bandera = false;
					}
				}
				if(! estado.equals("Todos")) {
					if(! mi_estado.toLowerCase().equals(estado.toLowerCase())){
						bandera = false;
					}
				}
				if(! categoria.equals("Todos")) {
					if(! mi_categoria.toLowerCase().equals(categoria.toLowerCase())){
						bandera = false;
					}
				}
				if(! puesto.equals("Todos")) {
					if(! mi_puesto.toLowerCase().equals(puesto.toLowerCase())){
						bandera = false;
					}
				}
				if(! nivel_participacion.equals("Todos")) {
					if(! mi_nivel_participacion.toLowerCase().equals(nivel_participacion.toLowerCase())){
						bandera = false;
					}
				}
				if(! nombreie.trim().equals("Todos")) {
					if(! obj.getTrabajosfinales().getProgramaeducativo().getNomie().toLowerCase().contains(nombreie.toLowerCase())){
						bandera = false;
					}
				}
				if(bandera && banderaOds) {
					TrabajosFinalesConcursoDto dto = new TrabajosFinalesConcursoDto();
					dto.setAnio(obj.getTrabajosfinales().getAnio());
					dto.setOds(odsserv.byOds(obj.getTrabajosfinales().getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
					dto.setCodigoie(obj.getTrabajosfinales().getProgramaeducativo().getCodmod());
					dto.setNombreie(obj.getTrabajosfinales().getProgramaeducativo().getNomie());
					dto.setRegion(obj.getTrabajosfinales().getProgramaeducativo().getDistrito().getProvincia().getDepartamento().getDescripcion());
					dto.setProvincia(obj.getTrabajosfinales().getProgramaeducativo().getDistrito().getProvincia().getDescripcion());
					dto.setDitrito(obj.getTrabajosfinales().getProgramaeducativo().getDistrito().getDescripcion());
					dto.setModalidad(mi_modalidad);
					dto.setAmbito(obj.getTrabajosfinales().getProgramaeducativo().getAmbito().getDescripcion());
					dto.setCodigoTrabajo(obj.getTrabajosfinales().getProgramaeducativo().getCodmod() + "_" + obj.getTrabajosfinales().getNumeracion().toString());
					dto.setEstadoTrabajo(obj.getTrabajosfinales().getEstadotrabajo().getDescripcion());
					dto.setTituloTrabajo(obj.getTrabajosfinales().getTitulotrabajo());
					dto.setLinkvideo(obj.getTrabajosfinales().getLinkvideo());
					dto.setModalidadTrabajo(obj.getTrabajosfinales().getModalidadtrabajo().getDescripcion());
					dto.setCategoriaTrabajo(obj.getTrabajosfinales().getCategoriatrabajo().getDescripcion());
					dto.setNivelParticipacion(obj.getParticipante().getGradoestudiante().getNivelgradopartdesc());
					ejesTematicos="";
					
					if(obj.getTrabajosfinales().getConversacion() == 1)
						ejesTematicos += "Conservación de las fuentes de agua/";
					if(obj.getTrabajosfinales().getValoracionagua() == 1)
						ejesTematicos += "Valoración de los servicios de agua potable/";
					if(obj.getTrabajosfinales().getValoracionalcantarillado() == 1)
						ejesTematicos += "Valoración del servicio de alcantarillado/";
					if(obj.getTrabajosfinales().getBuenuso() == 1)
						ejesTematicos += "Buen uso y reúso del agua potable/";
					if(obj.getTrabajosfinales().getImportancia() == 1)
						ejesTematicos += "Importancia de cerrar la brecha en saneamiento/";
					if(obj.getTrabajosfinales().getVinculo() == 1)
						ejesTematicos += "El vínculo estratégico entre el agua segura y la salud/";
					if(obj.getTrabajosfinales().getCarencias() == 1)
						ejesTematicos += "Las carencias que ponen en riesgo la vida/";
					if(obj.getTrabajosfinales().getRevaloracion()!=null) {
						if(obj.getTrabajosfinales().getRevaloracion()==1) {
							ejesTematicos += "Revaloración de las prácticas ancestrales para la seguridad hídrica";
						}
					}
					
					if(ejesTematicos.length()>0)
						ejesTematicos = ejesTematicos.substring(0, ejesTematicos.length()-1);
					
					
					dto.setEjesTematicos(ejesTematicos);
					dto.setNombreParticipante(obj.getParticipante().getNombreestudiante());
					dto.setApellidoPaternoParticipante(obj.getParticipante().getAppaternoestudiante());
					dto.setApellidoMaternoParticipante(obj.getParticipante().getApmaternoestudiante());
					dto.setTipoDocumentoParticipante(obj.getParticipante().getTipodocumentoestudiante().getDescripcion());
					dto.setNroDocumentoParticipante(obj.getParticipante().getNrodocumentoestudiante());
					dto.setFechaNacimientoParticipante(obj.getParticipante().getFechanacimientoestudiante().toString());
					
					dto.setGeneroParticipante(generoprofserv.ListarporId(obj.getParticipante().getGeneroestudiante().getId()).getDescripcion());
					
					dto.setSeccionParticipante(obj.getParticipante().getSeccion());
					dto.setNivelParticipante(obj.getParticipante().getGradoestudiante().getNivelparticipante().getDescripcion());
					dto.setGradoParticipante(obj.getParticipante().getGradoestudiante().getDescripcion());
					dto.setNombreTutor(obj.getParticipante().getNombrepmt());
					dto.setApellidoPaternoTutor(obj.getParticipante().getAppaternopmt());
					dto.setApellidoMaternoTutor(obj.getParticipante().getApmaternopmt());
					dto.setTipoDocumentoTutor(obj.getParticipante().getTipodocumentopmt().getDescripcion());
					dto.setNroDocumentoTutor(obj.getParticipante().getNrodocumentopmt());
					dto.setTelefonoTutor(obj.getParticipante().getNrotelefonopmt());
					dto.setCorreoTutor(obj.getParticipante().getCorreoelectronicopmt());
					dto.setParentescoTutor(obj.getParticipante().getParentesco().getDescripcion());
					dto.setNombreDocente(obj.getTrabajosfinales().getNombre());
					dto.setApellidoPaternoDocente(obj.getTrabajosfinales().getAppaterno());
					dto.setApellidoMaternoDocente(obj.getTrabajosfinales().getApmaterno());
					dto.setTipoDocumentoDocente(obj.getTrabajosfinales().getTipodocumento().getDescripcion());
					dto.setNroDocumentoDocente(obj.getTrabajosfinales().getNrodocumento());
					dto.setTelefonoDocente(obj.getTrabajosfinales().getTelefono());
					dto.setGeneroDocente(obj.getTrabajosfinales().getGenero().getDescripcion());
					dto.setCorreoDocente(obj.getTrabajosfinales().getCorreo());	
					dto.setNotaRegional(obj.getTrabajosfinales().getNota()!=null?dosDecimales.format(obj.getTrabajosfinales().getNota()):"");
					dto.setPuestoRegional(obj.getTrabajosfinales().getPuesto()==0?"":obj.getTrabajosfinales().getPuesto().toString());
					dto.setEmpate(obj.getTrabajosfinales().getEmpate()!=null?(obj.getTrabajosfinales().getEmpate()==0?"No":"Si"):"");
					dto.setNotaOriginal(obj.getTrabajosfinales().getNota_original()!=null?dosDecimales.format(obj.getTrabajosfinales().getNota_original()):"");
					dto.setNotaNacional("0.0");
					dto.setPuestoNacional("");
					lista.add(dto);					
					nroEvaluadoresAsignados = 0;
				}
			}			
		});
		
		
		trabajosfinalesServ.listarhabilitados().forEach(obj->{
			mi_ods = odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion();
			mi_anio = obj.getAnio();
			mi_modalidad = obj.getModalidadtrabajo().getDescripcion();
			mi_estado = obj.getEstadotrabajo().getDescripcion();
			mi_categoria = obj.getCategoriatrabajo().getDescripcion();
			mi_puesto = obj.getPuesto().toString();
			
			bandera = true;	
			banderaOds = false;
			
			if(obj.getEnviado()==1) {
				if(! ods.equals("Todos")) {
					if(mi_ods.toLowerCase().equals(ods.toLowerCase()))
						banderaOds = true;
				}else {
					listaOds.forEach(objOds->{
						if(objOds	.getDescripcion().equals(mi_ods)) {
							banderaOds = true;
						}
					});
				}
				
				if(! anio.equals("Todos")) {
					if(mi_anio != Integer.parseInt(anio)){
						bandera = false;
					}
				}
				if(! modalidad.equals("Todos")) {
					if(! mi_modalidad.toLowerCase().equals(modalidad.toLowerCase())){
						bandera = false;
					}
				}
				if(! puesto.equals("Todos")) {
					if(! mi_puesto.toLowerCase().equals(puesto.toLowerCase())){
						bandera = false;
					}
				}
				
				mi_nivel_participacion = "";
				trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj3->{
					mi_nivel_participacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartdesc();
				});	
				
				if(! nivel_participacion.equals("Todos")) {
					if(! mi_nivel_participacion.toLowerCase().equals(nivel_participacion.toLowerCase())){
						bandera = false;
					}
				}
				
				if(! puesto.equals("Todos")) {
					if(! mi_puesto.toLowerCase().equals(puesto.toLowerCase())){
						bandera = false;
					}
				}
				
				if(bandera && banderaOds) {
					nroEvaluadoresAsignados = 0;
					trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(obj.getId()).forEach(tf_ua->{
						nroEvaluadoresAsignados++;
					});				
					DetalleEvaluacionReporteDto derDto = new DetalleEvaluacionReporteDto();
					derDto.setAnio(obj.getAnio());
					derDto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
					derDto.setCodigoie(obj.getProgramaeducativo().getCodmod());
					derDto.setNombreie(obj.getProgramaeducativo().getNomie());
					derDto.setAmbito(obj.getProgramaeducativo().getAmbito().getDescripcion());
					derDto.setCodigoTrabajo(obj.getProgramaeducativo().getCodmod() + "_" + obj.getNumeracion().toString());
					derDto.setEstadoTrabajo(obj.getEstadotrabajo().getDescripcion());
					derDto.setTituloTrabajo(obj.getTitulotrabajo());
					derDto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
					derDto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
					derDto.setCantidadEvaluadoresAsignados(nroEvaluadoresAsignados);
					
					mi_nivel_participacion = "";
					trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(objn->{
						mi_nivel_participacion = objn.getParticipante().getGradoestudiante().getNivelgradopartdesc();
					});
					
					derDto.setNivelParticipacion(mi_nivel_participacion);
					
					derDto.setPregunta1("");
					derDto.setPregunta2("");
					derDto.setPregunta3("");
					derDto.setPregunta4("");
					derDto.setPregunta5("");
					derDto.setEresCebe("NO");
					
					puntaje  = new ArrayList<Float>();
					
					evaluacionRepuestaServ.listaRubricaPuntajeDto(obj.getId()).forEach(er->{
						puntaje.add(er.getPuntaje());
					});
					
					for(int i=0;i<puntaje.size();i++) {
						switch(i) {
							case 0 : derDto.setPregunta1(dosDecimales.format(puntaje.get(i))); break;
							case 1 : derDto.setPregunta2(dosDecimales.format(puntaje.get(i))); break;
							case 2 : derDto.setPregunta3(dosDecimales.format(puntaje.get(i))); break;
							case 3 : derDto.setPregunta4(dosDecimales.format(puntaje.get(i))); break;
							case 4 : derDto.setPregunta5(dosDecimales.format(puntaje.get(i))); break;
						}
					}	
					
					evaluacionRepuestaServ.getRespuestas(obj.getId()).forEach(objER->{
						if(objER.getTipo()==2) {
							questionarioRespuestaServ.listarByQuestionario(objER.getPreguntaid()).forEach(objQR->{
								if(objQR.getQuestionario().getPregunta().toLowerCase().contains("cebe")) {
									if(objQR.getRespuesta().toLowerCase().contains("si")) {
										derDto.setEresCebe("SI");
									}
								}
							});
						}
					});
					
					derDto.setNota(obj.getNota()!=null?obj.getNota().toString():"");
					derDto.setPuesto(obj.getPuesto()==0?"":obj.getPuesto().toString());
					listaDerDto.add(derDto);
				}
			}
		});
		
		String [] columns = {"AÑO","ODS","Codigo II.EE","NOMBRE II.EE","REGION","PROVINCIA","DISTRITO","MODALIDAD", "AMBITO","Código de trabajo","Estado de trabajo","Titulo de trabajo","Link de video","Modalidad","Categoria","Nivel de participación","Ejes temáticos","Nombres del participante","Apellido paterno","Apellido materno","Tipo de documento","Nro de documento","Fecha de nacimiento","Género","Seccion","Nivel","Grado","Nombres tutor","Apellido paterno tutor","Apellido materno tutor","Tipo de documento","Nro de documento tutor","telefono","correo electronico","Parentesco","Nombres del docente","Apellido paterno","Apellido materno","Tipo de documento","Nro de documento","Telefono","Género","Correo electrónico","Nota","Nota original","¿tuvo empate?","Puesto","Nota","Puesto"};
		
		Sheet sheet = workbook.createSheet("Registro de trabajos finales");
		Row row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("DATOS DE A II.EE");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));/*1era celda , ultima celda, 1era columna, ultima columna*/		
		
		row.createCell(9).setCellValue("DATOS DEL TRABAJO");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 9, 16));
		
		row.createCell(17).setCellValue("DATOS DE LOS PARTICIPANTES");
		sheet.addMergedRegion(new CellRangeAddress(0, 0,17, 26));
		
		row.createCell(27).setCellValue("DATOS DEL PADRE/MADRE O TUTOR");
		sheet.addMergedRegion(new CellRangeAddress(0, 0,27, 34));
		
		row.createCell(35).setCellValue("DATOS DEL DOCENTE");
		sheet.addMergedRegion(new CellRangeAddress(0, 0,35, 42));
		
		row.createCell(43).setCellValue("Concurso regional");
		sheet.addMergedRegion(new CellRangeAddress(0, 0,43, 46));
		
		row.createCell(47).setCellValue("Concurso nacional");
		sheet.addMergedRegion(new CellRangeAddress(0, 0,47, 48));
		
		row = sheet.createRow(1);
		for(int i=0;i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}
		
		int initRow = 2;
		for(TrabajosFinalesConcursoDto dto : lista) {
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(dto.getAnio());
			row.createCell(1).setCellValue(dto.getOds());
			row.createCell(2).setCellValue(dto.getCodigoie());
			row.createCell(3).setCellValue(dto.getNombreie());
			row.createCell(4).setCellValue(dto.getRegion());
			row.createCell(5).setCellValue(dto.getProvincia());
			row.createCell(6).setCellValue(dto.getDitrito());
			row.createCell(7).setCellValue(dto.getModalidad());
			row.createCell(8).setCellValue(dto.getAmbito());
			row.createCell(9).setCellValue(dto.getCodigoTrabajo());
			row.createCell(10).setCellValue(dto.getEstadoTrabajo());
			row.createCell(11).setCellValue(dto.getTituloTrabajo());
			row.createCell(12).setCellValue(dto.getLinkvideo());
			row.createCell(13).setCellValue(dto.getModalidadTrabajo());
			row.createCell(14).setCellValue(dto.getCategoriaTrabajo());
			row.createCell(15).setCellValue(dto.getNivelParticipacion());
			row.createCell(16).setCellValue(dto.getEjesTematicos());
			row.createCell(17).setCellValue(dto.getNombreParticipante());
			row.createCell(18).setCellValue(dto.getApellidoPaternoParticipante());
			row.createCell(19).setCellValue(dto.getApellidoMaternoParticipante());
			row.createCell(20).setCellValue(dto.getTipoDocumentoParticipante());
			row.createCell(21).setCellValue(dto.getNroDocumentoParticipante());
			row.createCell(22).setCellValue(dto.getFechaNacimientoParticipante());
			row.createCell(23).setCellValue(dto.getGeneroParticipante());
			row.createCell(24).setCellValue(dto.getSeccionParticipante());
			row.createCell(25).setCellValue(dto.getNivelParticipante());
			row.createCell(26).setCellValue(dto.getGradoParticipante());
			row.createCell(27).setCellValue(dto.getNombreTutor());
			row.createCell(28).setCellValue(dto.getApellidoPaternoTutor());
			row.createCell(29).setCellValue(dto.getApellidoMaternoTutor());
			row.createCell(30).setCellValue(dto.getTipoDocumentoTutor());
			row.createCell(31).setCellValue(dto.getNroDocumentoTutor());
			row.createCell(32).setCellValue(dto.getTelefonoTutor());
			row.createCell(33).setCellValue(dto.getCorreoTutor());
			row.createCell(34).setCellValue(dto.getParentescoTutor());
			row.createCell(35).setCellValue(dto.getNombreDocente());
			row.createCell(36).setCellValue(dto.getApellidoPaternoDocente());
			row.createCell(37).setCellValue(dto.getApellidoMaternoDocente());
			row.createCell(38).setCellValue(dto.getTipoDocumentoDocente());
			row.createCell(39).setCellValue(dto.getNroDocumentoDocente());
			row.createCell(40).setCellValue(dto.getTelefonoDocente());
			row.createCell(41).setCellValue(dto.getGeneroDocente());
			row.createCell(42).setCellValue(dto.getCorreoDocente());
			row.createCell(43).setCellValue(dto.getNotaRegional());
			row.createCell(44).setCellValue(dto.getNotaOriginal());
			row.createCell(45).setCellValue(dto.getEmpate());
			row.createCell(46).setCellValue(dto.getPuestoRegional());
			row.createCell(47).setCellValue(dto.getNotaNacional());
			row.createCell(48).setCellValue(dto.getPuestoNacional());
			initRow++;
		}
		
		
		String[] columnaDetalleEvaluacion = {"AÑO","ODS","CODIGO II.EE","NOMBRE II.EE","AMBITO","Código de trabajo","Estado de trabajo","Titulo de trabajo","Modalidad","Categoria","Nivel de participación","Cantidad de evaluadores asignados","Pregunta 1","Pregunta 2","Pregunta 3","Pregunta 4","Pregunta 5","Eres Cebe","Nota","Puesto"};
		Sheet hojaDetalleEvaluacion = workbook.createSheet("Detalle de la evaluación");
		
		Row row1DetalleEvaluacion = hojaDetalleEvaluacion.createRow(0);
		
		row1DetalleEvaluacion.createCell(0).setCellValue("");
		hojaDetalleEvaluacion.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));/*1era celda , ultima celda, 1era columna, ultima columna*/		
		
		row1DetalleEvaluacion.createCell(11).setCellValue("");
		
		row1DetalleEvaluacion.createCell(12).setCellValue("RUBRICA");
		hojaDetalleEvaluacion.addMergedRegion(new CellRangeAddress(0, 0, 12, 16));
		
		row1DetalleEvaluacion.createCell(17).setCellValue("CUESTIONARIO");
		
		row1DetalleEvaluacion = hojaDetalleEvaluacion.createRow(1);
		for(int i=0;i<columnaDetalleEvaluacion.length;i++) {
			Cell cell = row1DetalleEvaluacion.createCell(i);
			cell.setCellValue(columnaDetalleEvaluacion[i]);
		}
		
		initRow = 2;
		for(DetalleEvaluacionReporteDto dto : listaDerDto) {
			row1DetalleEvaluacion = hojaDetalleEvaluacion.createRow(initRow);
			row1DetalleEvaluacion.createCell(0).setCellValue(dto.getAnio());
			row1DetalleEvaluacion.createCell(1).setCellValue(dto.getOds());
			row1DetalleEvaluacion.createCell(2).setCellValue(dto.getCodigoie());
			row1DetalleEvaluacion.createCell(3).setCellValue(dto.getNombreie());
			row1DetalleEvaluacion.createCell(4).setCellValue(dto.getAmbito());
			row1DetalleEvaluacion.createCell(5).setCellValue(dto.getCodigoTrabajo());
			row1DetalleEvaluacion.createCell(6).setCellValue(dto.getEstadoTrabajo());
			row1DetalleEvaluacion.createCell(7).setCellValue(dto.getTituloTrabajo());
			row1DetalleEvaluacion.createCell(8).setCellValue(dto.getModalidad());
			row1DetalleEvaluacion.createCell(9).setCellValue(dto.getCategoria());
			row1DetalleEvaluacion.createCell(10).setCellValue(dto.getNivelParticipacion());		
			row1DetalleEvaluacion.createCell(11).setCellValue(dto.getCantidadEvaluadoresAsignados());
			row1DetalleEvaluacion.createCell(12).setCellValue(dto.getPregunta1());
			row1DetalleEvaluacion.createCell(13).setCellValue(dto.getPregunta2());
			row1DetalleEvaluacion.createCell(14).setCellValue(dto.getPregunta3());
			row1DetalleEvaluacion.createCell(15).setCellValue(dto.getPregunta4());
			row1DetalleEvaluacion.createCell(16).setCellValue(dto.getPregunta5());
			row1DetalleEvaluacion.createCell(17).setCellValue(dto.getEresCebe());
			row1DetalleEvaluacion.createCell(18).setCellValue(dto.getNota());
			row1DetalleEvaluacion.createCell(19).setCellValue(dto.getPuesto());
			initRow++;
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
			
			mi_ods = odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion();
			mi_anio = obj.getAnio();
			mi_modalidad = obj.getModalidadtrabajo().getDescripcion();
			mi_estado = obj.getEstadotrabajo().getDescripcion();
			mi_categoria = obj.getCategoriatrabajo().getDescripcion();
			mi_puesto = obj.getPuesto().toString();
			
			bandera = true;	
			banderaOds = false;
			
			if(obj.getEnviado()==1) {
				if(! ods.equals("Todos")) {
					if(mi_ods.toLowerCase().equals(ods.toLowerCase())) {
						banderaOds = true;
					}
				}else {
					listaOds.forEach(objOds->{
						if(objOds.getDescripcion().equals(mi_ods)) {
							banderaOds = true;
						}
					});
				}
				
				if(! anio.equals("Todos")) {
					if(mi_anio != Integer.parseInt(anio)){
						bandera = false;
					}
				}
				if(! modalidad.equals("Todos")) {
					if(! mi_modalidad.toLowerCase().equals(modalidad.toLowerCase())){
						bandera = false;
					}
				}
				if(! puesto.equals("Todos")) {
					if(! mi_puesto.toLowerCase().equals(puesto.toLowerCase())){
						bandera = false;
					}
				}
				
				peNivelParticipacion = "";
				if(! nivel_participacion.equals("Todos")) {
					List<Participante> listaParticipante = participanteService.listarhabilitados(obj.getProgramaeducativo().getId());
					listaParticipante.forEach(objParticipante->{
						if(objParticipante.getGradoestudiante().getDescripcion()==nivel_participacion) {
							bandera = true;
							peNivelParticipacion = objParticipante.getGradoestudiante().getNivelgradopartdesc();
						}
					});
				}
				
				if(! puesto.equals("Todos")) {
					if(! mi_puesto.toLowerCase().equals(puesto.toLowerCase())){
						bandera = false;
					}
				}
				
				if(bandera && banderaOds) {
					participantes = "";
					generoParticipante = "";
					ResultadosGanadoresDto dto = new ResultadosGanadoresDto();
					switch(obj.getPuesto()) {
						case  1 :  
							dto.setAnio(obj.getAnio());
							dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
							dto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
							if(peNivelParticipacion.equals("")) {
								trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj3->{
									peNivelParticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartdesc();
								});									
							}
							dto.setNivelParticipacion(peNivelParticipacion);
							dto.setPuesto("PRIMER PUESTO");
							dto.setCodigoIiee(obj.getProgramaeducativo().getCodmod());
							dto.setNombreIiee(obj.getProgramaeducativo().getNomie());
							dto.setAmbitoIiee(obj.getProgramaeducativo().getAmbito().getDescripcion());
							dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
							dto.setNombreTrabajo(obj.getNombre());
							trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(tfp->{
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
								trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj3->{
									peNivelParticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartdesc();
								});									
							}
							dto.setNivelParticipacion(peNivelParticipacion);
							dto.setPuesto("SEGUNDO PUESTO");
							dto.setCodigoIiee(obj.getProgramaeducativo().getCodmod());
							dto.setNombreIiee(obj.getProgramaeducativo().getNomie());
							dto.setAmbitoIiee(obj.getProgramaeducativo().getAmbito().getDescripcion());
							dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
							dto.setNombreTrabajo(obj.getNombre());
							trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(tfp->{
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
								trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj3->{
									peNivelParticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartdesc();
								});									
							}
							dto.setNivelParticipacion(peNivelParticipacion);
							dto.setPuesto("TERCER PUESTO");
							dto.setCodigoIiee(obj.getProgramaeducativo().getCodmod());
							dto.setNombreIiee(obj.getProgramaeducativo().getNomie());
							dto.setAmbitoIiee(obj.getProgramaeducativo().getAmbito().getDescripcion());
							dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
							dto.setNombreTrabajo(obj.getNombre());
							trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(tfp->{
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
	
	@GetMapping("/listaTrabajoEvaluadEmpate")
	public ResponseEntity<List<trabajoEvaluadoDto>> listaTrabajoEvaluadEmpate(@RequestParam(name="name",required=false,defaultValue="") String name, Model model, HttpSession ses) {
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		List<trabajoEvaluadoDto> listadto = new ArrayList<trabajoEvaluadoDto>();		
		
		if(tipousuarioid == 0){			
			Object ob = ses.getAttribute("odsid");
			distServ.listByOdsid(Integer.parseInt(ob.toString())).forEach(dist->{
				progeducService.listar(dist.getId()).forEach(pe->{
					trabajosfinalesServ.listaTrabajosEmpatadosPorODS(pe.getId()).forEach(tf->{							
						nivel_participante = "";
						evaluadores_asignados = 0;
						trabajoEvaluadoDto dto = new trabajoEvaluadoDto();
						dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
						dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
						trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(tfp->{
							nivel_participante  = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
						});
						dto.setNivel_participacion(nivel_participante);
						dto.setCodigoie(tf.getProgramaeducativo().getCodmod());
						dto.setNombreie(tf.getProgramaeducativo().getNomie());
						dto.setCodigo_trabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getNumeracion());
						dto.setModalidad(tf.getModalidadtrabajo().getDescripcion());
						dto.setTitulo(tf.getTitulotrabajo());				
						trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId()).forEach(tf_ua->{
							evaluadores_asignados += 1;
						});
						dto.setEvaluadores_asignados(evaluadores_asignados);
						dto.setTiene_evaluador_asignado(evaluadores_asignados>0? "Si" : "No");
						dto.setId(tf.getId());
						listadto.add(dto);
					});
				});
			});
		}
		else if (tipousuarioid==30){
			
			progeducService.getListarHabilitadosAnioActual().forEach(pe->{
				trabajosfinalesServ.listaTrabajosEmpatadosPorODS(pe.getId()).forEach(tf->{							
					nivel_participante = "";
					evaluadores_asignados = 0;
					trabajoEvaluadoDto dto = new trabajoEvaluadoDto();
					dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
					dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
					trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(tfp->{
						nivel_participante  = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
					});
					dto.setNivel_participacion(nivel_participante);
					dto.setCodigoie(tf.getProgramaeducativo().getCodmod());
					dto.setNombreie(tf.getProgramaeducativo().getNomie());
					dto.setCodigo_trabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getNumeracion());
					dto.setModalidad(tf.getModalidadtrabajo().getDescripcion());
					dto.setTitulo(tf.getTitulotrabajo());				
					trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId()).forEach(tf_ua->{
						evaluadores_asignados += 1;
					});
					dto.setEvaluadores_asignados(evaluadores_asignados);
					dto.setTiene_evaluador_asignado(evaluadores_asignados>0? "Si" : "No");
					dto.setId(tf.getId());
					listadto.add(dto);
				});
			});
		}
		else {
			String usuario = ses.getAttribute("usuario").toString();
			Usuario user = usuarioServ.byUsuario(usuario);
			usuarioodsServ.listarByUsuario(user.getId()).forEach(obj->{
				distServ.listByOdsid(obj.getOds().getId()).forEach(dist->{
					progeducService.listar(dist.getId()).forEach(pe->{
						trabajosfinalesServ.listaTrabajosEmpatadosPorODS(pe.getId()).forEach(tf->{							
							nivel_participante = "";
							evaluadores_asignados = 0;
							trabajoEvaluadoDto dto = new trabajoEvaluadoDto();
							dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
							dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
							trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(tfp->{
								nivel_participante  = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
							});
							dto.setNivel_participacion(nivel_participante);
							dto.setCodigoie(tf.getProgramaeducativo().getCodmod());
							dto.setNombreie(tf.getProgramaeducativo().getNomie());
							dto.setCodigo_trabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getNumeracion());
							dto.setModalidad(tf.getModalidadtrabajo().getDescripcion());
							dto.setTitulo(tf.getTitulotrabajo());				
							trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId()).forEach(tf_ua->{
								evaluadores_asignados += 1;
							});
							dto.setEvaluadores_asignados(evaluadores_asignados);
							dto.setTiene_evaluador_asignado(evaluadores_asignados>0? "Si" : "No");
							dto.setId(tf.getId());
							listadto.add(dto);
						});
					});
				});
			});
			
		}
		return new ResponseEntity<List<trabajoEvaluadoDto>>(listadto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listaconcursonacional")
	public ResponseEntity<List<ConcursoDto>> listaconcursonacional(HttpSession ses){
	
		List<ConcursoDto> listadto  = new ArrayList<ConcursoDto>();
		
		List<Trabajosfinales> listaTrabajoFinales =  trabajosfinalesServ.listarTrabajosConsursoNacional();
		listaTrabajoFinales.forEach(obj->{
			ConcursoDto dto = new ConcursoDto();
			dto.setId(obj.getId());
			dto.setAnio(obj.getAnio());
			dto.setCodigotrabajo(obj.getProgramaeducativo().getCodmod() + "_" + obj.getNumeracion());
			dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
			dto.setCodigoie(obj.getProgramaeducativo().getCodmod());
			dto.setNombreie(obj.getProgramaeducativo().getNomie());
			dto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
			dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
			dto.setTitulotrabajo(obj.getTitulotrabajo());
			nivelparticipacion = "";
			trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj1->{
				nivelparticipacion = obj1.getParticipante().getGradoestudiante().getNivelgradopartdesc();
				idnivelparticipacion = obj1.getParticipante().getGradoestudiante().getNivelgradopartid();
			});			
			dto.setNivelparticipacion(nivelparticipacion);
			dto.setEstado(obj.getEstadotrabajo().getDescripcion());
			dto.setCalificacion(obj.getNota());
			dto.setPuesto(obj.getPuesto());
			listadto.add(dto);
		});
		
		return new ResponseEntity<List<ConcursoDto>>(listadto, HttpStatus.OK) ;
	}
	
	@GetMapping("/consultarEtapaNacionalHabilitada")
	public ResponseEntity<DataEtapaNacionalDto> consultarEtapaNacionalHabilitada(HttpSession ses){
		DataEtapaNacionalDto respuesta = new DataEtapaNacionalDto();
		Calendar cal= Calendar.getInstance();
		int anio= cal.get(Calendar.YEAR);
		Aperturaranio apertura = aperturaranioService.buscar(anio);
		LocalDate dateActual = LocalDate.now();

		if(apertura.getQuintaetapadesde().isBefore(dateActual) || apertura.getQuintaetapadesde().isEqual(dateActual)) {
			respuesta.setStatus(1);
			
			//Lista de Niveles
			List<Gradoparticipante> listaNivel = gradoparticipanteServ.listarParaCierre();
			//Lista de Categorías
			List<Categoriatrabajo> listaCategoria = categoriaSserv.listar();
			//Lista de Empatadas
			List<CerrarEtapaNacional> listaEmpatadas =  cerrarNacionalSserv.listaNacionalEmpates();			
			
			respuesta.setListaNivel(listaNivel);
			respuesta.setListaCategoria(listaCategoria);
			respuesta.setListaCerrarEtapaNacional(listaEmpatadas);

		}else {
			respuesta.setStatus(0);
		}
		return new ResponseEntity<DataEtapaNacionalDto>(respuesta, HttpStatus.OK);
	}
	
	@GetMapping("/listaTrabajoEvaluadEmpateNacional")
	public ResponseEntity<List<trabajoEvaluadoDto>> listaTrabajoEvaluadEmpateNacional(@RequestParam(name="name",required=false,defaultValue="") String name, Model model, HttpSession ses) {
		
		List<trabajoEvaluadoDto> listadto  = new ArrayList<trabajoEvaluadoDto>();
		
		trabajosfinalesServ.listaTrabajoEvaluadEmpateNacional().forEach(tf->{							
			nivel_participante = "";
			evaluadores_asignados = 0;
			trabajoEvaluadoDto dto = new trabajoEvaluadoDto();
			dto.setOds(odsserv.byOds(tf.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
			dto.setCategoria(tf.getCategoriatrabajo().getDescripcion());
			trabajosfinalesparticipanteServ.listar(tf.getId()).forEach(tfp->{
				nivel_participante  = tfp.getParticipante().getGradoestudiante().getNivelgradopartdesc();
			});
			dto.setNivel_participacion(nivel_participante);
			dto.setCodigoie(tf.getProgramaeducativo().getCodmod());
			dto.setNombreie(tf.getProgramaeducativo().getNomie());
			dto.setCodigo_trabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getNumeracion());
			dto.setModalidad(tf.getModalidadtrabajo().getDescripcion());
			dto.setTitulo(tf.getTitulotrabajo());				
			trabajosFinales_UsuarioAlianzaServ.listarByTrabajosfinalesId(tf.getId()).forEach(tf_ua->{
				evaluadores_asignados += 1;
			});
			dto.setEvaluadores_asignados(evaluadores_asignados);
			dto.setTiene_evaluador_asignado(evaluadores_asignados>0? "Si" : "No");
			dto.setId(tf.getId());
			listadto.add(dto);
		});
		
		return new ResponseEntity<List<trabajoEvaluadoDto>>(listadto, HttpStatus.OK);
	}
	
	@PostMapping(value="/cerrarNacional")
	public Integer cerrarNacional(@Valid @RequestBody List<CerrarEtapaNacional> listCerrar,HttpSession ses) {
		
		try {

			Calendar cal= Calendar.getInstance();
			int anio= cal.get(Calendar.YEAR);
			
			List<CerrarEtapaNacional> listaCerrarNacionales = cerrarNacionalSserv.listar();
			
			for (CerrarEtapaNacional cerrarN : listCerrar) {
				CerrarEtapaNacional cerrarNacioanl = new CerrarEtapaNacional();
				cerrarNacioanl.setNivelDesc(cerrarN.getNivelDesc());
				cerrarNacioanl.setCategoriaId(cerrarN.getCategoriaId());
				cerrarNacioanl.setEstado(1);//finalizado
				
				for (CerrarEtapaNacional cerrarN2 : listaCerrarNacionales) {
					if(cerrarN2.getNivelDesc() == cerrarNacioanl.getNivelDesc() && cerrarN2.getCategoriaId() == cerrarNacioanl.getCategoriaId()) {//si existe el registro actulizar
						cerrarNacioanl = cerrarN2;
						cerrarNacioanl.setEstado(1);//finalizado
					}
				}
				
				cerrarNacioanl = cerrarNacionalSserv.registrar(cerrarNacioanl);
				
				/*lista de trabajos finales por Nivel y Categoria*/
				List<Trabajosfinales>  trabajoFinales = trabajosfinalesServ.listarTrabajosfinalesPorNivelCategoria(cerrarNacioanl.getCategoriaId(), cerrarNacioanl.getNivelDesc());
				for (Trabajosfinales trabajos : trabajoFinales) {
					Estadotrabajo estadoTrabajo = new  Estadotrabajo();
					estadoTrabajo.setId(3);
					trabajos.setEstadonacional(estadoTrabajo);
					trabajosfinalesServ.modificar(trabajos);
				}
	
				/*Listar Categorias X Nivel de Participacion*/

				//for (Trabajosfinales trabajos : trabajoFinales) {
					log.info("CAREGORIAID: "+cerrarNacioanl.getCategoriaId() +" | NIVEL: "+cerrarNacioanl.getNivelDesc());
					/*Lista de trabajos Por Categoria y Nivel con Nota Promedio*/
					//List<TrabajosFinalizados> listaTrab = trabajosfinalesServ.listaTrabajosFinalesConNotaPromedioPorCategoriaNivelOds(catModByODs.getCategoriaId(), catModByODs.getNivelId(), ods.getId());
					/*Lista de empatados*/
					List<Trabajosfinales> listaTrabEmpatados = trabajosfinalesServ.listarTrabajosfinalesPorNivelCategoriaEmpatadosConNota(cerrarNacioanl.getCategoriaId(), cerrarNacioanl.getNivelDesc());
					
					/*SECTION EMPATES*/
					if(listaTrabEmpatados.size() > 0) {
						float NotaPuesto1 = trabajoFinales.get(0).getNota(); //primera nota
						int puestoEmpate = 0;
						int cantidad = 0;
						/*BuscarEmpate*/
						for (Trabajosfinales trabajos : listaTrabEmpatados) {
							if(trabajos.getNota() == NotaPuesto1 ) {
								puestoEmpate = trabajos.getPuesto();//PUESTO DE LOS EMPATADOS
								cantidad++;
							}
						}
						
						if(true) {//if(cantidad <= 1) {//cantidad de empatados <= 1 (NO HAY EMPATES NUEVOS)
							int indice = 0;
							float notaPuestoNuevo = 21;
							int puestoNuevo = 0;
							int cantidadEmpateNuevo = 0;
							int empates1 =0, empates2 =0;
							boolean puestoFinalizados = false;
							//int puestoDeEmpate = 100;
							for (Trabajosfinales trabajos : listaTrabEmpatados) {
								Trabajosfinales  trabajoFinal = trabajos;
								log.info("TRABAJO EMPATADO FINALIZADOS: "+trabajos.getId() +" | "+"CAREGORIAID: "+cerrarNacioanl.getCategoriaId() +" | NIVEL: "+cerrarNacioanl.getNivelDesc()+" | PUESTO: "+trabajoFinal.getPuesto()+indice+" | NOTA: "+trabajoFinal.getNota());
								log.info("+++++++++++++++++++++puestoNuevo: "+puestoNuevo +" - trabajoFinal.getPuesto():"+ trabajoFinal.getPuesto());
								if(trabajos.getNota() == notaPuestoNuevo) { log.info("EMPATE NUEVO");log.info("PUESTO: "+puestoNuevo);
									cantidadEmpateNuevo++;
									if(puestoFinalizados) {
										trabajoFinal.setPuesto(0);
									}else {
										trabajoFinal.setPuesto(puestoNuevo);
									}
	
									//puestoDeEmpate = puestoNuevo;
									cerrarNacioanl.setEstado(2);//empate
									cerrarNacionalSserv.registrar(cerrarNacioanl);
									indice=0;
									if(trabajoFinal.getPuesto() == 3) {
										indice=10;
									}
									if(trabajoFinal.getPuesto() == 1) {
										empates1++;log.info("empates1: "+empates1);
									}
									if(trabajoFinal.getPuesto() == 2) {
										empates2++;log.info("empates2: "+empates2);
									}
									
								}else if(trabajos.getNota() < notaPuestoNuevo || puestoNuevo != trabajoFinal.getPuesto()){ log.info("SIN EMPATE NUEVO");log.info("PUESTO: "+trabajoFinal.getPuesto()+"+"+ indice);log.info("Puestos distintos:  "+puestoNuevo +" != "+ trabajoFinal.getPuesto());
									if(trabajoFinal.getPuesto() == 3 && indice ==2) {
										indice=0;
									}
									if(puestoNuevo > trabajoFinal.getPuesto() && cantidadEmpateNuevo > 0) {
										trabajoFinal.setPuesto(0);
										puestoFinalizados = true;
									}else {
										if((puestoNuevo + indice) > 3) {
											trabajoFinal.setPuesto(0);
											puestoFinalizados = true;
										}else {
											if(puestoNuevo < trabajoFinal.getPuesto()) indice=0;
											else indice++;
											if(trabajoFinal.getPuesto() == 3) {
												trabajoFinal.setPuesto(trabajoFinal.getPuesto()); //asignar nuevo puesto
											}else {
												trabajoFinal.setPuesto(trabajoFinal.getPuesto() + indice+empates1); //asignar nuevo puesto
											}
											puestoNuevo = trabajoFinal.getPuesto();log.info("Nuevo puesto: "+puestoNuevo);
										}
										if(trabajoFinal.getPuesto() == 3) {
											indice=10;
										}
										//indice++;
										log.info("empates1: "+empates1);
										if(empates1 > 1) {
											trabajoFinal.setPuesto(0);
											puestoFinalizados = true;
										}log.info("empates2: "+empates2);
										if(empates2 > 1) {
											trabajoFinal.setPuesto(0);
											puestoFinalizados = true;
										}
										if(cantidadEmpateNuevo > 2) {
											trabajoFinal.setPuesto(0);
											puestoFinalizados = true;
										}
										
										if(empates1 == 1) {
											if(cantidadEmpateNuevo > 1) {
												trabajoFinal.setPuesto(0);
												puestoFinalizados = true;
											}
										}
										
										if(cantidadEmpateNuevo > 2) {
											trabajoFinal.setPuesto(0);
											puestoFinalizados = true;
										}
										/*if(puestoNuevo < trabajoFinal.getPuesto()) {
											trabajoFinal.setPuesto(0);
										}*/
									}
									log.info("********************PUESTO: "+puestoNuevo);
								}
								
								
								trabajosfinalesServ.modificar(trabajoFinal);
								notaPuestoNuevo = trabajos.getNota();
							}
							
							//cambias estado a los trabajos empatados
							List<Trabajosfinales> listaPuesto1= trabajosfinalesServ.listaTrabajosEmpatadosNacionalPorCatNivPuesto(cerrarNacioanl.getCategoriaId(), cerrarNacioanl.getNivelDesc(), 1);
							List<Trabajosfinales> listaPuesto2= trabajosfinalesServ.listaTrabajosEmpatadosNacionalPorCatNivPuesto(cerrarNacioanl.getCategoriaId(), cerrarNacioanl.getNivelDesc(), 2);
							List<Trabajosfinales> listaPuesto3= trabajosfinalesServ.listaTrabajosEmpatadosNacionalPorCatNivPuesto(cerrarNacioanl.getCategoriaId(), cerrarNacioanl.getNivelDesc(), 3);
							log.info("TOTAL PUESTO 1:"+listaPuesto1.size());
							log.info("TOTAL PUESTO 2:"+listaPuesto2.size());
							log.info("TOTAL PUESTO 3:"+listaPuesto3.size());
							for (Trabajosfinales trab : listaPuesto1) {
								if(listaPuesto1.size() > 1) { log.info("lista 1 mayor  a 1 ");
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getId());//borrar las respuestas de las evaluaciones;
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getId());//borrar asignacion de evaluadores
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getId());
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
		
									trabajosfinalesServ.modificar(trabajoFinal);
								}
							}
							
							for (Trabajosfinales trab : listaPuesto2) {
								if(listaPuesto1.size() >= 3) { log.info("YA HAY MAS DE 3 en lista 1 ");
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getId());
									trabajoFinal.setPuesto(0);
									
									trabajosfinalesServ.modificar(trabajoFinal);
								}else if(listaPuesto2.size() > 1) { log.info("lista 2 mayor  a 1 ");
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getId());//borrar las respuestas de las evaluaciones;
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getId());//borrar asignacion de evaluadores
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getId());
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
		
									trabajosfinalesServ.modificar(trabajoFinal);
								}
							}
							for (Trabajosfinales trab : listaPuesto3) {
								if((listaPuesto1.size() + listaPuesto2.size()) >= 3) { log.info("YA HAY MAS DE 3 en lista 1 + lista 2 ");
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getId());
									trabajoFinal.setPuesto(0);
									
									trabajosfinalesServ.modificar(trabajoFinal);
								}else if(listaPuesto3.size() > 1){ log.info("lista 3 mayor  a 1 ");
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getId());//borrar las respuestas de las evaluaciones;
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getId());//borrar asignacion de evaluadores
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getId());
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
		
									trabajosfinalesServ.modificar(trabajoFinal);
								}
							}
						}
					}
					else {
						/*Obtener Puesto*/
						if(trabajoFinales.size() > 0) {log.info("TOTAL DE TRABAJOS: "+trabajoFinales.size());
							/*Puesto 1*/
							float NotaPuesto1 = trabajoFinales.get(0).getNota();
							int cantidad = 0;
							/*BuscarEmpate*/log.info("BUSCAR EMPATES P1: "+trabajoFinales.size());
							for (Trabajosfinales trabajos : trabajoFinales) {
								if(trabajos.getNota() == NotaPuesto1 ) {
									Trabajosfinales  trabajoFinal = trabajos;
									trabajoFinal.setPuesto(1);
									trabajosfinalesServ.modificar(trabajoFinal);
									cantidad++;
								}
							}log.info("FINALIZACION 1 - TOTAL EMPATES P1: "+cantidad);
							if(cantidad > 1) {
								trabajosfinalesServ.listaTrabajosEmpatadosNacionalPorCatNivPuesto(cerrarNacioanl.getCategoriaId(), cerrarNacioanl.getNivelDesc(), 1);
								List<Trabajosfinales> listaPuesto1= trabajosfinalesServ.listaTrabajosEmpatadosNacionalPorCatNivPuesto(cerrarNacioanl.getCategoriaId(), cerrarNacioanl.getNivelDesc(), 1);
								for (Trabajosfinales trab : listaPuesto1) {
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getId());//borrar las respuestas de las evaluaciones;
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getId());//borrar asignacion de evaluadores
									
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getId());
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
									trabajoFinal.setEmpate(1);//empate si
									trabajoFinal.setNota_original(trabajoFinal.getNota());//nota original
									trabajosfinalesServ.modificar(trabajoFinal);
									log.info("TRABAJO EMPATADO F1: "+trab.getId() +" | "+"CAREGORIAID: "+cerrarNacioanl.getCategoriaId() +" | NIVEL: "+cerrarNacioanl.getNivelDesc()+" | PUESTO: "+trabajoFinal.getPuesto()+indice+" | NOTA: "+trabajoFinal.getNota());
								}
								cerrarNacioanl.setEstado(2);//empate
								cerrarNacionalSserv.registrar(cerrarNacioanl);
							}
							
							/*Puesto 2*/
							int cantidad2 = 0;
							if(trabajoFinales.size() > cantidad && cantidad == 1) {
								float NotaPuesto2 = trabajoFinales.get(cantidad).getNota();
								/*BuscarEmpate*/
								for (Trabajosfinales trabajos : trabajoFinales) {
									if(trabajos.getNota() == NotaPuesto2 ) {
										Trabajosfinales  trabajoFinal = trabajos;
										trabajoFinal.setPuesto(2);
										trabajosfinalesServ.modificar(trabajoFinal);
										cantidad2++;
									}
								}
							}log.info("FINALIZACION 1 - TOTAL EMPATES P2: "+cantidad2);
							if(cantidad2 > 1 && cantidad == 1) {
								List<Trabajosfinales> listaPuesto2= trabajosfinalesServ.listaTrabajosEmpatadosNacionalPorCatNivPuesto(cerrarNacioanl.getCategoriaId(), cerrarNacioanl.getNivelDesc(), 2);
								for (Trabajosfinales trab : listaPuesto2) {
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getId());
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getId());
									
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getId());
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
									trabajoFinal.setEmpate(1);//empate si
									trabajoFinal.setNota_original(trabajoFinal.getNota());//nota original
									trabajosfinalesServ.modificar(trabajoFinal);
									log.info("TRABAJO EMPATADO F1: "+trab.getId() +" | "+"CAREGORIAID: "+cerrarNacioanl.getCategoriaId() +" | NIVEL: "+cerrarNacioanl.getNivelDesc()+" | PUESTO: "+trabajoFinal.getPuesto()+indice+" | NOTA: "+trabajoFinal.getNota());
								}
								cerrarNacioanl.setEstado(2);//empate
								cerrarNacionalSserv.registrar(cerrarNacioanl);
							}
							
							/*Puesto 3*/
							int cantidad3 = 0;
							if(trabajoFinales.size() > cantidad+cantidad2 && (cantidad+cantidad2) == 2) {
								float NotaPuesto3 = trabajoFinales.get(cantidad+cantidad2).getNota();
								/*BuscarEmpate*/
								for (Trabajosfinales trabajos : trabajoFinales) {
									if(trabajos.getNota() == NotaPuesto3 ) {
										Trabajosfinales  trabajoFinal = trabajos;
										trabajoFinal.setPuesto(3);
										trabajosfinalesServ.modificar(trabajoFinal);
										cantidad3++;
									}
								}
							}log.info("FINALIZACION 1 - TOTAL EMPATES P3: "+cantidad3);
							if(cantidad3 > 1 && (cantidad+cantidad2) == 2) {
								List<Trabajosfinales> listaPuesto3= trabajosfinalesServ.listaTrabajosEmpatadosNacionalPorCatNivPuesto(cerrarNacioanl.getCategoriaId(), cerrarNacioanl.getNivelDesc(), 3);
								for (Trabajosfinales trab : listaPuesto3) {
									evaluacionRespuestaServ.borrarEvaluacionesPorTrabajo(trab.getId());
									trabajosFinales_UsuarioAlianzaServ.eliminar(trab.getId());
									
									Estadotrabajo estadoTrabajo = new  Estadotrabajo();
									estadoTrabajo.setId(21);
									Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(trab.getId());
									trabajoFinal.setEstadotrabajo(estadoTrabajo);
									trabajoFinal.setEmpate(1);//empate si
									trabajoFinal.setNota_original(trabajoFinal.getNota());//nota original
									trabajosfinalesServ.modificar(trabajoFinal);
									log.info("TRABAJO EMPATADO F1: "+trab.getId() +" | "+"CAREGORIAID: "+cerrarNacioanl.getCategoriaId() +" | NIVEL: "+cerrarNacioanl.getNivelDesc()+" | PUESTO: "+trabajoFinal.getPuesto()+indice+" | NOTA: "+trabajoFinal.getNota());
								}
								cerrarNacioanl.setEstado(2);//empate
								cerrarNacionalSserv.registrar(cerrarNacioanl);
							}
							
						}
					}
					
					
					
				//}
				
				
				
				
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("ERROR: ",e);
			return 0;
		}
		
		return 1;
	}
	
	@GetMapping(value = "/listaconcursonacionalfinalizar")
	public ResponseEntity<List<ConcursoDto>> listaconcursonacionalfinalizar(HttpSession ses){
	
		List<ConcursoDto> listadto  = new ArrayList<ConcursoDto>();
		
		List<Trabajosfinales> listaTrabajoFinales =  trabajosfinalesServ.listarTrabajosConsursoNacional();
		listaTrabajoFinales.forEach(obj->{
			ConcursoDto dto = new ConcursoDto();
			dto.setId(obj.getId());
			dto.setAnio(obj.getAnio());
			dto.setCodigotrabajo(obj.getProgramaeducativo().getCodmod() + "_" + obj.getNumeracion());
			dto.setOds(odsserv.byOds(obj.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
			dto.setCodigoie(obj.getProgramaeducativo().getCodmod());
			dto.setNombreie(obj.getProgramaeducativo().getNomie());
			dto.setCategoria(obj.getCategoriatrabajo().getDescripcion());
			dto.setModalidad(obj.getModalidadtrabajo().getDescripcion());
			dto.setTitulotrabajo(obj.getTitulotrabajo());
			nivelparticipacion = "";
			trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj1->{
				nivelparticipacion = obj1.getParticipante().getGradoestudiante().getNivelgradopartdesc();
				idnivelparticipacion = obj1.getParticipante().getGradoestudiante().getNivelgradopartid();
			});			
			dto.setNivelparticipacion(nivelparticipacion);
			dto.setEstado(obj.getEstadotrabajo().getDescripcion());
			dto.setCalificacion(obj.getNota());
			dto.setPuesto(obj.getPuesto());
			listadto.add(dto);
		});
		
		return new ResponseEntity<List<ConcursoDto>>(listadto, HttpStatus.OK) ;
	}
	
	@PostMapping(value="/saveasignarevaluadorNacional")
	public Integer saveasignarevaluadorNacional(@Valid @RequestBody AsignarEvaluadoresDto dto) {
		
		rpta=2;
		try {			
			dto.getTrabajos_evaluados().forEach(te->{
				dto.getEvaluadores().forEach(ev->{
					if(trabajosFinales_UsuarioAlianzaNacionalServ.buscar(te.getId(),ev.getId()) == null) {
						trabajosFinales_UsuarioAlianzaNacionalServ.guardar(te.getId(),ev.getId(),-1f);
						trabajosfinalesServ.updateEstadoTrabajoNacional(te.getId(),2);
						
						Trabajosfinales  trabajoFinal = trabajosfinalesServ.ListarporId(te.getId());
						trabajoFinal.setNota(0f);
						trabajosfinalesServ.modificar(trabajoFinal);
						
						rpta = 1;
					}
				});
			});
			return rpta;
		}
		catch(Exception exc) {
			return 0;
		}
	}
}
