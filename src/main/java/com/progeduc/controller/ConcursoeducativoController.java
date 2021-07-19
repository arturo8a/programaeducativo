package com.progeduc.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.progeduc.dto.ClaveValor;
import com.progeduc.dto.ConcursoDto;
import com.progeduc.dto.DetalleUsuarioAlianzaEstrategica;
import com.progeduc.dto.EvaluacionDto;
import com.progeduc.dto.EvaluacionRubricaQuestionarioDto;
import com.progeduc.dto.EvaluadorDto;
import com.progeduc.dto.ListaDocente;
import com.progeduc.dto.ListaDocenteInscritos;
import com.progeduc.dto.ListaInstitucionEducativa;
import com.progeduc.dto.ListaTrabajosFinalesPendientes;
import com.progeduc.dto.ListaparticipanteDto;
import com.progeduc.dto.ListaparticipantetrabajoDto;
import com.progeduc.dto.ListatrabajosfinalesDto;
import com.progeduc.dto.ParticipanteVerDto;
import com.progeduc.dto.TrabajofinalesEnviadoDto;
import com.progeduc.dto.TrabajosfinalesParticipanteDto;
import com.progeduc.dto.UsuarioAlianzaDto;
import com.progeduc.dto.trabajoEvaluadoDto;
import com.progeduc.model.Aperturaranio;
import com.progeduc.model.Auspicio;
import com.progeduc.model.Docente;
import com.progeduc.model.Evaluacion;
import com.progeduc.model.EvaluacionResultado;
import com.progeduc.model.Gradoparticipante;
import com.progeduc.model.Ods;
import com.progeduc.model.Participante;
import com.progeduc.model.Postulacionconcurso;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.Trabajosfinales;
import com.progeduc.model.TrabajosfinalesParticipante;
import com.progeduc.model.Usuario;
import com.progeduc.model.UsuarioAlianza;
import com.progeduc.model.UsuarioLdap;
import com.progeduc.service.IAperturaranioService;
import com.progeduc.service.IAuspicioService;
import com.progeduc.service.IDistritoService;
import com.progeduc.service.IDocenteService;
import com.progeduc.service.IEvaluacionRespuestaService;
import com.progeduc.service.IEvaluacionService;
import com.progeduc.service.IGradoparticipanteService;
import com.progeduc.service.IOdsService;
import com.progeduc.service.IParticipanteService;
import com.progeduc.service.IPostulacionconcursoService;
import com.progeduc.service.IProgramaeducativoService;
import com.progeduc.service.ITrabajosfinalesParticipanteService;
import com.progeduc.service.ITrabajosfinalesService;
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
	
	
	ListaparticipanteDto dto;	
	ListatrabajosfinalesDto dtotf;	
	ListaparticipantetrabajoDto ptdto;	
	ListaDocenteInscritos listadocentesinscritos;
	ListaTrabajosFinalesPendientes listaTrabajosFinalesPendientes;
	String miparticipante = "";	
	boolean banderaUpdate;	
	Mail mail;	
	String participantes, msj2;
	UsuarioLdap usuarioldap = null;
	String nivelparticipacion;
	Integer idnivelparticipacion, nro_evidencias;
	String estado, texto_evidencias;
	String registro_validar;
	String nivel_participante = "";
	Integer evaluadores_asignados = 0;
	String rol_entidad,entidad;
	String nombre_apellido_evaluador;
	Integer rpta;
	Double totalNota = 0.0;
	Integer idTrab = 0;
	
	
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
	
	@PostMapping(value = "/savetrabajosfinalesparticipante")
	public Integer savetrabajosfinalesparticipante(@Valid @RequestBody TrabajosfinalesParticipanteDto dto,HttpSession ses){
		
		Integer categoria_dto = dto.getTrabajosfinales().getCategoriatrabajo().getId();
		Integer modalidad_dto = dto.getTrabajosfinales().getModalidadtrabajo().getId();
		List<Integer> listaIdDto = new ArrayList<Integer>();
 		dto.getListaparticipante().forEach(obj->{
 			listaIdDto.add(obj.getId());
 		});
		List<Trabajosfinales> listaTrabajosFinales = trabajosfinalesServ.listarhabilitados();
		List<Integer> listaIdParticipante = new ArrayList<Integer>();
		listaTrabajosFinales.forEach(obj->{
			if(obj.getCategoriatrabajo().getId().equals(categoria_dto)	&& obj.getModalidadtrabajo().getId().equals(modalidad_dto)){
				trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj1->{
					listaIdParticipante.add(obj1.getParticipante().getId());
				});
			}
		});	
		
		if(listaIdDto.size() ==  listaIdParticipante.size()) {
			Collections.sort(listaIdDto);
			Collections.sort(listaIdParticipante);
			if(listaIdDto.equals(listaIdParticipante)) {
				return -1;
			}	
		}
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		dto.getTrabajosfinales().setFecha_registro(ts);
		dto.getTrabajosfinales().setAnio(ts.toLocalDateTime().getYear());
		
		String codmod = ses.getAttribute("usuario").toString();
		
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		dto.getTrabajosfinales().setProgramaeducativo(pe);
		
		Trabajosfinales tf = trabajosfinalesServ.saveTrabajofinaParticipante(dto);
		
		if( tf !=null) {
			return tf.getId();
		}		
		return 0;
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
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		return trabajosfinalesServ.updateestado(id, 0,pe.getId());
	}
	
	@GetMapping(value="/enviartrabajoid/{id}")
	public Integer enviartrabajoid(@PathVariable("id") Integer id,HttpSession ses) {
		
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
		
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		if(pe!=null) {
			participante.setProgramaeducativo(pe);
			Date date= new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			participante.setFecha_registro(ts);
			participante.setAnhio(ts.toLocalDateTime().getYear());
			
			Participante p =  participanteService.registrar(participante);
			if(p!=null)
				return p.getId();
			return 0;
		}
		return -1;
		
	}
	
	
	@PostMapping(value="/registrardocente")
	public ClaveValor registrardocente(@Valid @RequestBody Docente docente,HttpSession ses) {
		
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		ClaveValor cl = new ClaveValor();
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
	public Evaluacion actualizarevaluacion(@Valid @RequestBody EvaluacionRubricaQuestionarioDto dto) {
		return evaluacionServ.updateEvalRubQuest(dto);
	}
	
	@PostMapping(value="/actualizardocente")
	public Docente actualizardocente(@Valid @RequestBody Docente docente,HttpSession ses) {
		
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
		return midocente;
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
        				return "d"; /*esta fuera de las fechas - fecha desde cuarta y fecha hasta cuarta*/
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
						dto.setCodigo_trabajo(tf.getProgramaeducativo().getCodmod() + "-" + tf.getId().toString());
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
					dto.setCodigo_trabajo(tf.getProgramaeducativo().getCodmod() + "-" + tf.getId().toString());
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
							dto.setCodigo_trabajo(tf.getProgramaeducativo().getCodmod() + "-" + tf.getId().toString());
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
				EvaluadorDto dto = new EvaluadorDto();
				dto.setOds(ua.getOds().getDescripcion());
				
				rol_entidad = "";
				if(ua.getComitetecnico().equals("1")) {
					rol_entidad = "Comite Técnico/";
				}				
				if(ua.getComitetecnico().equals("1")) {
					rol_entidad += "Comite Evaluador/";
				}				
				if(ua.getComitetecnico().equals("1")) {
					rol_entidad += "Auspiciador/";
				}				
				if(ua.getComitetecnico().equals("1")) {
					rol_entidad += "Aliado/";
				}				
				if(rol_entidad.length()>0)
					rol_entidad = rol_entidad.substring(0, rol_entidad.length()-1);	
				dto.setRol_entidad(rol_entidad);
				dto.setEntidad(ua.getEntidad());
				dto.setApellido_paterno(ua.getApepatautoridad());
				dto.setApellido_materno(ua.getApematautoridad());
				dto.setNombres(ua.getNombresautoridad());
				dto.setTipo_documento(ua.getTipodocumento().getDescripcion());
				dto.setNro_documento(ua.getNumdocumento());
				dto.setId(ua.getId());
				lista.add(dto);
			});
		}
		else if (tipousuarioid==30){
			usuAlianzaserv.listar().forEach(ua->{
				EvaluadorDto dto = new EvaluadorDto();
				dto.setOds(ua.getOds().getDescripcion());
				rol_entidad = "";
				if(ua.getComitetecnico().equals("1")) {
					rol_entidad = "Comite Técnico/";
				}				
				if(ua.getComitetecnico().equals("1")) {
					rol_entidad += "Comite Evaluador/";
				}				
				if(ua.getComitetecnico().equals("1")) {
					rol_entidad += "Auspiciador/";
				}				
				if(ua.getComitetecnico().equals("1")) {
					rol_entidad += "Aliado/";
				}				
				if(rol_entidad.length()>0)
					rol_entidad = rol_entidad.substring(0, rol_entidad.length()-1);				
				dto.setRol_entidad(rol_entidad);
				dto.setEntidad(ua.getEntidad());
				dto.setApellido_paterno(ua.getApepatautoridad());
				dto.setApellido_materno(ua.getApematautoridad());
				dto.setNombres(ua.getNombresautoridad());
				dto.setTipo_documento(ua.getTipodocumento().getDescripcion());
				dto.setNro_documento(ua.getNumdocumento());
				dto.setId(ua.getId());
				lista.add(dto);
			});	
		}
		else {
			String usuario = ses.getAttribute("usuario").toString();
			Usuario user = usuarioServ.byUsuario(usuario);
			usuarioodsServ.listarByUsuario(user.getId()).forEach(usu->{				
				//List<UsuarioAlianza> listaUsu = usuAlianzaserv.listarByOds(usu.getOds().getId());
				List<UsuarioAlianza> listaUsu = usuAlianzaserv.listarByOds(10);
				listaUsu.forEach(ua->{
					EvaluadorDto dto = new EvaluadorDto();
					dto.setOds(ua.getOds().getDescripcion());
					rol_entidad = "";
					if(ua.getComitetecnico().equals("1")) {
						rol_entidad = "Comite Técnico/";
					}				
					if(ua.getComitetecnico().equals("1")) {
						rol_entidad += "Comite Evaluador/";
					}				
					if(ua.getComitetecnico().equals("1")) {
						rol_entidad += "Auspiciador/";
					}				
					if(ua.getComitetecnico().equals("1")) {
						rol_entidad += "Aliado/";
					}				
					if(rol_entidad.length()>0)
						rol_entidad = rol_entidad.substring(0, rol_entidad.length()-1);	
					dto.setRol_entidad(rol_entidad);
					dto.setEntidad(ua.getEntidad());
					dto.setApellido_paterno(ua.getApepatautoridad());
					dto.setApellido_materno(ua.getApematautoridad());
					dto.setNombres(ua.getNombresautoridad());
					dto.setTipo_documento(ua.getTipodocumento().getDescripcion());
					dto.setNro_documento(ua.getNumdocumento());
					dto.setId(ua.getId());
					lista.add(dto);
				});
			});	
		}		
		return new ResponseEntity<List<EvaluadorDto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listaconcurso")
	public ResponseEntity<List<ConcursoDto>> listarconcurso(HttpSession ses){
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());		
		List<ConcursoDto> listadto  = new ArrayList<ConcursoDto>();
		
		if(tipousuarioid == 0){			
			Object ob = ses.getAttribute("odsid");
			distServ.listByOdsid(Integer.parseInt(ob.toString())).forEach(obj->{				
				progeducService.listar(obj.getId()).forEach(pe->{
					List<Trabajosfinales> listaTrabajoFinales =  trabajosfinalesServ.listarHabilitadosEnviados(pe.getId());
					listaTrabajoFinales.forEach(obj2->{
						ConcursoDto dto = new ConcursoDto();
						dto.setId(obj2.getId());
						dto.setAnio(obj2.getAnio());
						dto.setCodigotrabajo(obj2.getProgramaeducativo().getCodmod() + "_" + obj2.getId());
						dto.setOds(odsserv.byOds(obj2.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
						dto.setCodigoie(obj2.getProgramaeducativo().getCodmod());
						dto.setNombreie(obj2.getProgramaeducativo().getNomie());
						dto.setCategoria(obj2.getCategoriatrabajo().getDescripcion());
						dto.setModalidad(obj2.getModalidadtrabajo().getDescripcion());
						dto.setTitulotrabajo(obj2.getTitulotrabajo());
						nivelparticipacion = "";
						trabajosfinalesparticipanteServ.listar(pe.getId()).forEach(obj3->{
							nivelparticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartdesc();
							idnivelparticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartid();
						});			
						dto.setNivelparticipacion(nivelparticipacion);
						dto.setEstado(obj2.getEstadotrabajo().getDescripcion());
						dto.setCalificacion(0);
						dto.setPuesto(0);
						listadto.add(dto);
					});
				});
			});		
		}
		else if (tipousuarioid==30){	
			
			progeducService.getListarHabilitadosAnioActual().forEach(pe->{
				List<Trabajosfinales> listaTrabajoFinales =  trabajosfinalesServ.listarHabilitadosEnviado(pe.getId());
				listaTrabajoFinales.forEach(obj->{
					System.out.println("trabajofinaleid :" +obj.getId());
					ConcursoDto dto = new ConcursoDto();
					dto.setId(obj.getId());
					dto.setAnio(obj.getAnio());
					dto.setCodigotrabajo(obj.getProgramaeducativo().getCodmod() + "_" + obj.getId());
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
					dto.setCalificacion(0);
					dto.setPuesto(0);
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
						List<Trabajosfinales> listaTrabajoFinales =  trabajosfinalesServ.listarhabilitados(pe.getId());
						listaTrabajoFinales.forEach(obj2->{
							ConcursoDto dto = new ConcursoDto();
							dto.setId(obj2.getId());
							dto.setAnio(obj2.getAnio());
							dto.setCodigotrabajo(obj2.getProgramaeducativo().getCodmod() + "_" + obj2.getId());
							dto.setOds(odsserv.byOds(obj2.getProgramaeducativo().getDistrito().getOdsid()).getDescripcion());
							dto.setCodigoie(obj2.getProgramaeducativo().getCodmod());
							dto.setNombreie(obj2.getProgramaeducativo().getNomie());
							dto.setCategoria(obj2.getCategoriatrabajo().getDescripcion());
							dto.setModalidad(obj2.getModalidadtrabajo().getDescripcion());
							dto.setTitulotrabajo(obj2.getTitulotrabajo());
							nivelparticipacion = "";
							trabajosfinalesparticipanteServ.listar(pe.getId()).forEach(obj3->{
								nivelparticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartdesc();
								idnivelparticipacion = obj3.getParticipante().getGradoestudiante().getNivelgradopartid();
							});
							dto.setNivelparticipacion(nivelparticipacion);
							dto.setEstado(obj2.getEstadotrabajo().getDescripcion());
							dto.setCalificacion(0);
							dto.setPuesto(0);
							listadto.add(dto);
						});
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
							if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
								rol_entidad += "Comite Evaluador/";
							}				
							if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
								rol_entidad += "Auspiciador/";
							}				
							if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
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
							dto.setCodigotrabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getId());
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
					if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
						rol_entidad += "Comite Evaluador/";
					}				
					if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
						rol_entidad += "Auspiciador/";
					}				
					if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
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
					dto.setCodigotrabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getId());
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
								if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
									rol_entidad += "Comite Evaluador/";
								}				
								if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
									rol_entidad += "Auspiciador/";
								}				
								if(tf_ua.getUsuarioalianza().getComitetecnico().equals("1")) {
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
								dto.setCodigotrabajo(tf.getProgramaeducativo().getCodmod() + "_" + tf.getId());
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
			dto.setCalificacion(0);
			dto.setPuesto(0);
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
		
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		
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
				dtotf.setEnviado(obj.getEnviado());
				
				lista.add(dtotf);
			});
		}		
		return new ResponseEntity<List<ListatrabajosfinalesDto>>(lista, HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/listaparticipantes_trabajo")
	public ResponseEntity<List<ListaparticipantetrabajoDto>> listaparticipantes_trabajo(HttpSession ses){
		
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		
		List<ListaparticipantetrabajoDto> lista = new ArrayList<ListaparticipantetrabajoDto>();
		List<Participante> listaParticipante = participanteService.listarhabilitados(pe.getId());
		if(listaParticipante!=null) {
			listaParticipante.forEach(obj->{
				
				ptdto =new ListaparticipantetrabajoDto();
				ptdto.setId(obj.getId());
				ptdto.setAppaterno(obj.getAppaternoestudiante());
				ptdto.setApmaterno(obj.getApmaternoestudiante());
				ptdto.setNombre(obj.getNombreestudiante());
				ptdto.setTipodocumento(obj.getTipodocumentoestudiante().getDescripcion());
				ptdto.setNrodocumento(obj.getNrodocumentoestudiante());
				ptdto.setComposicionmusical(obj.getCategoriacomposicionmusical());
				ptdto.setCuento(obj.getCategoriacuento());
				ptdto.setPoesia(obj.getCategoriapoesia());
				ptdto.setDibujopintura(obj.getCategoriadibujopintura());
				ptdto.setAhorraragua(obj.getCategoriaahorroagua());
				ptdto.setModalidadindividual(obj.getModalidadpostulacionindividual());
				ptdto.setModalidadgrupal(obj.getModalidadpostulaciongrupal());
				ptdto.setNivel(obj.getGradoestudiante().getNivelgradopartdesc());
				lista.add(ptdto);
			});
		}		
		return new ResponseEntity<List<ListaparticipantetrabajoDto>>(lista, HttpStatus.OK) ;
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
		System.out.println("tipousuarioid :" + tipousuarioid);	
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
		
		List<ListaTrabajosFinalesPendientes> lista = new ArrayList<ListaTrabajosFinalesPendientes>();
		List<Trabajosfinales> listaTrabajos = trabajosfinalesServ.listarTrabajosPendientes();
		listaTrabajos.forEach(data->{
			List<TrabajosfinalesParticipante> listaTrabajosParticipante = trabajosfinalesparticipanteServ.listar(data.getId());
			Participante participante = participanteService.ListarporId(listaTrabajosParticipante.get(0).getParticipante().getId());
			
			Evaluacion eval = evaluacionService.getPorAnioCategoriaNivelparticipacion(data.getAnio(), 
					data.getCategoriatrabajo().getId(), participante.getGradoestudiante().getNivelgradopartid());
			
			if(eval != null) {
				String strOds = "";
				if(data.getProgramaeducativo().getOds()!=null) {
					Ods ods = odsserv.byOds(data.getProgramaeducativo().getDistrito().getOdsid());
					strOds = ods.getDes_ods();
				}
				listaTrabajosFinalesPendientes = new ListaTrabajosFinalesPendientes();
				listaTrabajosFinalesPendientes.setAnio(data.getAnio());
				listaTrabajosFinalesPendientes.setCodigo(data.getId());
				listaTrabajosFinalesPendientes.setOds(strOds);
				listaTrabajosFinalesPendientes.setIiee(data.getProgramaeducativo().getCodmod());
				listaTrabajosFinalesPendientes.setCategoria(data.getCategoriatrabajo().getDescripcion());
				listaTrabajosFinalesPendientes.setModalidad(data.getModalidadtrabajo().getDescripcion());
				listaTrabajosFinalesPendientes.setTitulotrabajo(data.getTitulotrabajo());
				listaTrabajosFinalesPendientes.setNivelparticipacion(participante.getGradoestudiante().getNivelgradopartdesc());
				listaTrabajosFinalesPendientes.setFichatrabajo("ficha trabajo");
				listaTrabajosFinalesPendientes.setEvaluacion("ficha trabajo");
				listaTrabajosFinalesPendientes.setTrabajo("ficha trabajo");
				listaTrabajosFinalesPendientes.setFichatrabajo("ficha trabajo");
				lista.add(listaTrabajosFinalesPendientes);
			}
			
		});		
		return new ResponseEntity<List<ListaTrabajosFinalesPendientes>>(lista, HttpStatus.OK) ;
	}
	
	@GetMapping("/listatrabevaluados")
	public ResponseEntity<List<ListaTrabajosFinalesPendientes>>listTrabajosEvaluados(HttpSession ses){
		
		List<ListaTrabajosFinalesPendientes> lista = new ArrayList<ListaTrabajosFinalesPendientes>();
		List<Trabajosfinales> listaTrabajos = trabajosfinalesServ.listarTrabajosEvaluados();
		listaTrabajos.forEach(data->{
			List<TrabajosfinalesParticipante> listaTrabajosParticipante = trabajosfinalesparticipanteServ.listar(data.getId());
			Participante participante = participanteService.ListarporId(listaTrabajosParticipante.get(0).getParticipante().getId());
			
			Evaluacion eval = evaluacionService.getPorAnioCategoriaNivelparticipacion(data.getAnio(), 
					data.getCategoriatrabajo().getId(), participante.getGradoestudiante().getNivelgradopartid());
			
			if(eval != null) {
				String strOds = "";
				if(data.getProgramaeducativo().getOds()!=null) {
					Ods ods = odsserv.byOds(data.getProgramaeducativo().getDistrito().getOdsid());
					strOds = ods.getDes_ods();
				}
				listaTrabajosFinalesPendientes = new ListaTrabajosFinalesPendientes();
				listaTrabajosFinalesPendientes.setAnio(data.getAnio());
				listaTrabajosFinalesPendientes.setCodigo(data.getId());
				listaTrabajosFinalesPendientes.setOds(strOds);
				listaTrabajosFinalesPendientes.setIiee(data.getProgramaeducativo().getCodmod());
				listaTrabajosFinalesPendientes.setCategoria(data.getCategoriatrabajo().getDescripcion());
				listaTrabajosFinalesPendientes.setModalidad(data.getModalidadtrabajo().getDescripcion());
				listaTrabajosFinalesPendientes.setTitulotrabajo(data.getTitulotrabajo());
				listaTrabajosFinalesPendientes.setNivelparticipacion(participante.getGradoestudiante().getNivelgradopartdesc());
				listaTrabajosFinalesPendientes.setFichatrabajo("ficha trabajo");
				listaTrabajosFinalesPendientes.setEvaluacion("ficha trabajo");
				listaTrabajosFinalesPendientes.setTrabajo("ficha trabajo");
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
			usu.setCategoria(dto.getCategoria());
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
			usu.setTipodocumento(dto.getTipodocumento());
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
				usu.setNumoficio(dto.getNumoficio());
				usu.setFecha_oficio(dto.getFecha_oficio());
				if(!dto.getDocoficio().isEmpty()) {
					usu.setDocoficio(dto.getDocoficio());
				}
				
				
			}
			
			
			
			if(dto.getAuspicios().size() > 0) {
				List<Auspicio> listAus = dto.getAuspicios();
				for (Auspicio auspicio : listAus) {
					Auspicio aus = auspicioServ.ListarporId(auspicio.getId());
					if(aus != null) {
						aus.setTipodocumento(auspicio.getTipodocumento());
						aus.setCantidad(auspicio.getCantidad());
						aus.setDescripcion(auspicio.getDescripcion());
						aus.setMontounitario(auspicio.getMontounitario());
						aus.setMontototal(auspicio.getMontototal());
						aus.setUsuario_alianza(usu);
						aus.setFecha_registro(new Timestamp(new Date().getTime()));
						auspicioServ.registrar(aus);
					}else {
						aus = new Auspicio();
						aus.setTipodocumento(auspicio.getTipodocumento());
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
			usu.setCategoria(dto.getCategoria());
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
			usu.setTipodocumento(dto.getTipodocumento());
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
				usu.setNumoficio(dto.getNumoficio());
				usu.setFecha_oficio(dto.getFecha_oficio());
				usu.setDocoficio(dto.getDocoficio());
				/*enviar correo*/
				String msj = "<img src='./images/logo_login.PNG' style='width:400px' /><img src='./images/imagen1.PNG' style='width:400px' />";
				msj += "<p>Estimado(a), usted ha sido agregado como evaluador:</p>";
				msj += "<p>Usuario: "+dto.getUsuarioautoridad()+"</p>";
				msj += "<p>Contraseña: "+dto.getPasswordautoridad()+"</p>";				
				mail = new Mail();
				mail.enviarCorreoTrabajosFinalesConcursoEscolar("Credenciales de Usuario", msj, dto.getCorreoautoridad());
			}
			
			usu.setAuspicios(dto.getAuspicios());
			usu.setFecha_registro(new Timestamp(new Date().getTime()));
			usu.setEstado("1");
			UsuarioAlianza usu2 = usuAlianzaserv.registrar(usu);
			
			if(dto.getAuspicios().size() > 0) {
				List<Auspicio> listAus = dto.getAuspicios();
				for (Auspicio auspicio : listAus) {
					Auspicio aus = new Auspicio();
					aus.setTipodocumento(auspicio.getTipodocumento());
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
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());	
		List<DetalleUsuarioAlianzaEstrategica> lista = new ArrayList<DetalleUsuarioAlianzaEstrategica>();
		
		if(tipousuarioid == 0){			
			Object ob = ses.getAttribute("odsid");
			List<UsuarioAlianza> listaUsu = usuAlianzaserv.listarByOds(Integer.parseInt(ob.toString()));
			listaUsu.forEach(obj->{
				DetalleUsuarioAlianzaEstrategica dto = new DetalleUsuarioAlianzaEstrategica();
				dto.setId(obj.getId());
				dto.setAnio(obj.getAnio());
				dto.setOds(obj.getOds().getDescripcion());
				dto.setCategoria(obj.getCategoria().getDescripcion());
				dto.setEntidad(obj.getEntidad());
				dto.setComiteTecnico(obj.getComitetecnico());
				dto.setComiteEvaluador(obj.getComiteevaluador());
				dto.setAuspiciador(obj.getAuspiciador());
				dto.setAliado(obj.getAliado());
				dto.setEstado(obj.getEstado());
				lista.add(dto);
			});
		}
		else if (tipousuarioid==30){
			List<UsuarioAlianza> listaUsu= usuAlianzaserv.listar();	
			listaUsu.forEach(obj->{
				DetalleUsuarioAlianzaEstrategica dto = new DetalleUsuarioAlianzaEstrategica();
				dto.setId(obj.getId());
				dto.setAnio(obj.getAnio());
				dto.setOds(obj.getOds().getDescripcion());
				dto.setCategoria(obj.getCategoria().getDescripcion());
				dto.setEntidad(obj.getEntidad());
				dto.setComiteTecnico(obj.getComitetecnico());
				dto.setComiteEvaluador(obj.getComiteevaluador());
				dto.setAuspiciador(obj.getAuspiciador());
				dto.setAliado(obj.getAliado());
				dto.setEstado(obj.getEstado());
				lista.add(dto);
			});
		}
		else {
			String usuario = ses.getAttribute("usuario").toString();
			Usuario user = usuarioServ.byUsuario(usuario);
			usuarioodsServ.listarByUsuario(user.getId()).forEach(usu->{				
				System.out.println("usu.getOds().getId() :" + usu.getOds().getId());
				List<UsuarioAlianza> listaUsu = usuAlianzaserv.listarByOds(usu.getOds().getId());
				listaUsu.forEach(obj->{
					DetalleUsuarioAlianzaEstrategica dto = new DetalleUsuarioAlianzaEstrategica();
					dto.setId(obj.getId());
					dto.setAnio(obj.getAnio());
					dto.setOds(obj.getOds().getDescripcion());
					dto.setCategoria(obj.getCategoria().getDescripcion());
					dto.setEntidad(obj.getEntidad());
					dto.setComiteTecnico(obj.getComitetecnico());
					dto.setComiteEvaluador(obj.getComiteevaluador());
					dto.setAuspiciador(obj.getAuspiciador());
					dto.setAliado(obj.getAliado());
					dto.setEstado(obj.getEstado());
					lista.add(dto);
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
						trabajosFinales_UsuarioAlianzaServ.guardar(te.getId(),ev.getId());
						trabajosfinalesServ.updateEstadoTrabajo(te.getId(),2);
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
	public Integer registrarRespuestasEvaluacion(@Valid @RequestBody List<EvaluacionResultado> lista) {
		
		try {
			
			lista.forEach(obj->{
				EvaluacionResultado resultado = new EvaluacionResultado();
				resultado.setPreguntaid(obj.getPreguntaid());
				resultado.setRespuestaid(obj.getRespuestaid());
				resultado.setTrabajosfinales(obj.getTrabajosfinales());
				resultado.setTipo(obj.getTipo());
				resultado.setPuntaje(obj.getPuntaje());
				evaluacionRespuestaServ.registrar(resultado);
				
				totalNota += obj.getPuntaje();
				idTrab = obj.getTrabajosfinales().getId();
			});
				
			Trabajosfinales  trab =trabajosfinalesServ.ListarporId(idTrab);
			//trab.setNota();
			trab.setEstado(3);
			trabajosfinalesServ.modificar(trab);
			//trabajosfinalesServ.updateestado(trab.getId(), 3,trab.getProgramaeducativo().getId());

		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("ERROR: ",e);
			return 0;
		}
		
		return 1;
	}

	@GetMapping("/getRespuestas/{id}")
	public ResponseEntity<List<EvaluacionResultado>>getRespuestas(@PathVariable("id") Integer id){
		
		List<EvaluacionResultado> lista = evaluacionRespuestaServ.getRespuestas(id);
		return new ResponseEntity<List<EvaluacionResultado>>(lista, HttpStatus.OK);
	}
	
}
