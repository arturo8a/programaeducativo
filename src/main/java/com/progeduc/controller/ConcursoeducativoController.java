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
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.progeduc.dto.ClaveValor;
import com.progeduc.dto.ConcursoDto;
import com.progeduc.dto.EvaluacionDto;
import com.progeduc.dto.EvaluacionRubricaQuestionarioDto;
import com.progeduc.dto.EvaluadorDto;
import com.progeduc.dto.ListaDocente;
import com.progeduc.dto.ListaDocenteInscritos;
import com.progeduc.dto.ListaparticipanteDto;
import com.progeduc.dto.ListaparticipantetrabajoDto;
import com.progeduc.dto.ListatrabajosfinalesDto;
import com.progeduc.dto.ParticipanteVerDto;
import com.progeduc.dto.TrabajofinalesEnviadoDto;
import com.progeduc.dto.TrabajosfinalesParticipanteDto;
import com.progeduc.dto.trabajoEvaluadoDto;
import com.progeduc.model.Aperturaranio;
import com.progeduc.model.Docente;
import com.progeduc.model.Evaluacion;
import com.progeduc.model.Gradoparticipante;
import com.progeduc.model.Ods;
import com.progeduc.model.Participante;
import com.progeduc.model.Postulacionconcurso;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.Trabajosfinales;
import com.progeduc.model.UsuarioLdap;
import com.progeduc.service.IAperturaranioService;
import com.progeduc.service.IDistritoService;
import com.progeduc.service.IDocenteService;
import com.progeduc.service.IEvaluacionService;
import com.progeduc.service.IGradoparticipanteService;
import com.progeduc.service.IOdsService;
import com.progeduc.service.IParticipanteService;
import com.progeduc.service.IPostulacionconcursoService;
import com.progeduc.service.IProgramaeducativoService;
import com.progeduc.service.ITrabajosfinalesParticipanteService;
import com.progeduc.service.ITrabajosfinalesService;
import com.progeduc.service.impl.UploadFileService;

@RestController
@RequestMapping("")
public class ConcursoeducativoController {
	
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
	
	ListaparticipanteDto dto;	
	ListatrabajosfinalesDto dtotf;	
	ListaparticipantetrabajoDto ptdto;	
	ListaDocenteInscritos listadocentesinscritos;	
	String miparticipante = "";	
	boolean banderaUpdate;	
	Mail mail;	
	String participantes, msj2;
	UsuarioLdap usuarioldap = null;
	String nivelparticipacion;
	Integer idnivelparticipacion;
	String estado;
	
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
			if(trabajosfinalesServ.updateenviado(dato.getId(), 1,pe.getId()) != 1)
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
				uploadfile.saveNuevoFile(file,id);
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
        if(ap != null) {
        	String codmod = ses.getAttribute("usuario").toString();
        	Programaeducativo pe = progeducService.verificarEstadoAnio(codmod,fecha.get(Calendar.YEAR), "Aprobado");  
        	if(pe!=null) {
        		//return pe.getId().toString();//pe ah sido aprobado
        		Postulacionconcurso pc = postulacionconcursoServ.getById(pe.getId());
        		if(pc!=null) {
        			return pe.getId().toString(); //ya se encuentra registrado en el concurso
        		}
        		else {
        			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                	LocalDate fechaactual = LocalDate.parse(today, formatter);
        			if(fechaactual.compareTo(ap.getSegundaetapadesde())>=0 && fechaactual.compareTo(ap.getCuartaetapahasta())<=0) {
        				return pe.getId().toString();
        			}
        			else {
        				return "b"; /*esta fuera de las fechas - fecha desde segunda y fecha hasta cuarta*/
        			}
        		}
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
				dto =new ListaparticipanteDto();
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
	public ResponseEntity<List<trabajoEvaluadoDto>> listaTrabajoEvaluado(@RequestParam(name="name",required=false,defaultValue="") String name, Model model) {
		
		List<trabajoEvaluadoDto> lista = new ArrayList<trabajoEvaluadoDto>();		
		for(int i=1;i<=8;i++) {
			trabajoEvaluadoDto dto = new trabajoEvaluadoDto();		
			dto.setOds("Piura");
			dto.setCategoria("Canto");
			dto.setNivel_participacion("Primer Nivel");
			dto.setCodigoie("234444");
			dto.setNombreie("Los laureles");
			dto.setCodigo_trabajo("234444-01");
			dto.setModalidad("Grupal");
			dto.setTitulo("Gotita de amor");
			dto.setEvaluadores_asignados(i);
			dto.setId(i);
			lista.add(dto);
		}
		return new ResponseEntity<List<trabajoEvaluadoDto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listaevaluador")
	public ResponseEntity<List<EvaluadorDto>> listaEvaluador(@RequestParam(name="name",required=false,defaultValue="") String name, Model model) {
		
		List<EvaluadorDto> lista = new ArrayList<EvaluadorDto>();		
		
		EvaluadorDto dto = new EvaluadorDto();	
		
		dto.setOds("Piura");
		dto.setRol_entidad("Ministerio");
		dto.setEntidad("Ministerio de justicia");
		dto.setApellido_paterno("Lopez");
		dto.setApellido_materno("Perez");
		dto.setNombres("Juan");
		dto.setTipo_documento("DNI");
		dto.setNro_documento("12345678");
		dto.setId(1);
		lista.add(dto);
		
		dto.setOds("Piura");
		dto.setRol_entidad("Ministerio");
		dto.setEntidad("Ministerio de justicia");
		dto.setApellido_paterno("Lopez");
		dto.setApellido_materno("Perez");
		dto.setNombres("Maria");
		dto.setTipo_documento("CE");
		dto.setNro_documento("123456789A");
		dto.setId(2);
		lista.add(dto);
		
		dto.setOds("Piura");
		dto.setRol_entidad("Ministerio");
		dto.setEntidad("Ministerio de justicia");
		dto.setApellido_paterno("Lopez");
		dto.setApellido_materno("Perez");
		dto.setNombres("Laura");
		dto.setTipo_documento("DNI");
		dto.setNro_documento("12349865");
		dto.setId(3);
		lista.add(dto);
		
		dto.setOds("Piura");
		dto.setRol_entidad("Ministerio");
		dto.setEntidad("Ministerio de justicia");
		dto.setApellido_paterno("Lopez");
		dto.setApellido_materno("Perez");
		dto.setNombres("Juan");
		dto.setTipo_documento("DNI");
		dto.setNro_documento("12345678");
		dto.setId(4);
		lista.add(dto);
		
		dto.setOds("Piura");
		dto.setRol_entidad("Ministerio");
		dto.setEntidad("Ministerio de justicia");
		dto.setApellido_paterno("Lopez");
		dto.setApellido_materno("Perez");
		dto.setNombres("Juan");
		dto.setTipo_documento("DNI");
		dto.setNro_documento("12345678");
		dto.setId(5);
		lista.add(dto);
		
		return new ResponseEntity<List<EvaluadorDto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listaconcurso")
	public ResponseEntity<List<ConcursoDto>> listarconcurso(HttpSession ses){
		
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
			nivelparticipacion = "";
			trabajosfinalesparticipanteServ.listar(obj.getId()).forEach(obj1->{
				nivelparticipacion = obj1.getParticipante().getGradoestudiante().getNivelgradopartdesc();
				idnivelparticipacion = obj1.getParticipante().getGradoestudiante().getNivelgradopartid();
			});
			dto.setNivelparticipacion(nivelparticipacion);
			//dto.setEstado(evaluacionServ.getPorAnio(obj.getAnio()).getEstadoevaluacion().getDescripcion());
			dto.setEstado(evaluacionServ.getPorAnioNivelparticipacion(obj.getAnio(),idnivelparticipacion)!=null?evaluacionServ.getPorAnioNivelparticipacion(obj.getAnio(),idnivelparticipacion).getEstadoevaluacion().getDescripcion():"");
			dto.setCalificacion(0);
			dto.setPuesto(0);
			listadto.add(dto);
		});		
		return new ResponseEntity<List<ConcursoDto>>(listadto, HttpStatus.OK) ;
	}
	
	@GetMapping(value="/listaasignacion")
	public ResponseEntity<List<AsignacionDto>> listaasignacion(HttpSession ses){
		
		List<AsignacionDto> lista = new ArrayList<AsignacionDto>();
		/*AsignacionDto dto = new AsignacionDto();
		dto.setAnio(0);
		dto.setCategoria("");
		dto.setCodigoiiee("");
		dto.setCodigotrabajo("");
		dto.setEntidad("");
		dto.setEvaluadorasignado("");
		dto.setModalidad("");
		dto.setNivelparticipacion("");
		dto.setNombreiiee("");
		dto.setOds("");
		dto.setRolentidad("");
		lista.add(dto);*/
		AsignacionDto dto = new AsignacionDto();
		lista.add(dto);
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
			EvaluacionDto midto = new EvaluacionDto();
			midto.setId(obj.getId());
			midto.setAnio(obj.getAnio());
			midto.setCategoriaevaluacion(obj.getCategoriaevaluacion().getDescripcion());
			midto.setNivelparticipacion(obj.getNivelparticipacion().getDescripcion());
			midto.setEstado(obj.getEstadoevaluacion().getDescripcion());
			lista.add(midto);
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
					//System.out.println(obj1.getParticipante().getNombreestudiante() + " " + obj1.getParticipante().getAppaternoestudiante() + " " + obj1.getParticipante().getApmaternoestudiante());
				});
				
				dtotf.setParticipantes(miparticipante);
				
				archivos = uploadfile.nroArchivos(obj.getId(), "upload_evidencias").toString() + " evidencias " + uploadfile.nroArchivos(obj.getId(), "upload_trabajos").toString() + " final";
				
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
		
		List<ListaDocenteInscritos> arrayie = new ArrayList<ListaDocenteInscritos>();
		//Object ob = ses.getAttribute("odsid");
		//if(Integer.parseInt(ob.toString()) == 0) {
		if(true) {
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
			//distServ.listByOdsid(Integer.parseInt(ob.toString())).forEach(distrito->{
			distServ.listByOdsid(19).forEach(distrito->{
				progeducService.listar(distrito.getId()).forEach(pe->{					
					docenteService.listarhabilitados(pe.getId()).forEach(docente->{
						listadocentesinscritos= new ListaDocenteInscritos();
						
						listadocentesinscritos.setAnio(docente.getAnhio());
						Ods ods = odsserv.byOds(distrito.getOdsid());
						listadocentesinscritos.setOds(ods.getDes_ods());
						listadocentesinscritos.setCodigo_ie(docente.getProgramaeducativo().getCodmod());
						listadocentesinscritos.setNombre_ie(docente.getProgramaeducativo().getNomie());
						Postulacionconcurso pc = postulacionconcursoServ.getByIdAnio(docente.getProgramaeducativo().getId(), docente.getAnhio());
						listadocentesinscritos.setInscrito_ie(pc!=null?"Si":"No");
						listadocentesinscritos.setDocente(docente.getNombre() + " " + docente.getAppaterno() + " " + docente.getApmaterno());
						listadocentesinscritos.setTipodocumento(docente.getTipodocumento().getDescripcion());
						listadocentesinscritos.setNrodocumento(docente.getNrodocumento());
						listadocentesinscritos.setCorreoelectronico(docente.getCorreoelectronico());
						listadocentesinscritos.setNrotelefono(docente.getNrotelefono());
						listadocentesinscritos.setGenero(docente.getGenero().getDescripcion());
						listadocentesinscritos.setFecha_registro(docente.getFecha_registro());
						arrayie.add(listadocentesinscritos);
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
}
