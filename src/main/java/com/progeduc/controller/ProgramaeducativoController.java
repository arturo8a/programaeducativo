package com.progeduc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progeduc.dto.ActualizarContraseniaDto;
import com.progeduc.dto.ConsultapeDto;
import com.progeduc.dto.DatocorreoDto;
import com.progeduc.dto.FilterListAprobacionInscripcionDto;
import com.progeduc.dto.ListaCategoriaDto;
import com.progeduc.dto.ListaInstitucionEducativa;
import com.progeduc.dto.ParticipanteTrabajosfinalesDto;
import com.progeduc.dto.ProgeducDto;
import com.progeduc.dto.ProgeducTurnoNivelDto;
import com.progeduc.dto.ProgeducUpdateTurnoNivelDto;
import com.progeduc.dto.UpdateAprobarProgramaDto;
import com.progeduc.dto.UpdateObservarProgramaDto;
import com.progeduc.dto.Usuarioemail;
import com.progeduc.model.Aperturaranio;
import com.progeduc.model.Docentetutor;
import com.progeduc.model.Evaluacion;
import com.progeduc.model.Nivel;
import com.progeduc.model.Ods;
import com.progeduc.model.Participante;
import com.progeduc.model.ProgeducfiltroDto;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.ProgramaeducativoNivel;
import com.progeduc.model.ProgramaeducativoTurno;
import com.progeduc.model.Questionario;
import com.progeduc.model.Rubrica;
import com.progeduc.model.Trabajosfinales;
import com.progeduc.model.Turno;
import com.progeduc.model.Usuario;
import com.progeduc.service.IAperturaranioService;
import com.progeduc.service.ICategoriaService;
import com.progeduc.service.IDepartamentoService;
import com.progeduc.service.IDistritoService;
import com.progeduc.service.IDocenteService;
import com.progeduc.service.IDocentetutorService;
import com.progeduc.service.IEvaluacionQuestionarioService;
import com.progeduc.service.IEvaluacionRubricaService;
import com.progeduc.service.IEvaluacionService;
import com.progeduc.service.IGeneroprofService;
import com.progeduc.service.IOdsService;
import com.progeduc.service.IParticipanteService;
import com.progeduc.service.IPostulacionconcursoService;
import com.progeduc.service.IProgeducNivelService;
import com.progeduc.service.IProgeducTurnoService;
import com.progeduc.service.IProgramaeducativoService;
import com.progeduc.service.IProvinciaService;
import com.progeduc.service.ISuministroService;
import com.progeduc.service.ITipousuarioService;
import com.progeduc.service.ITrabajosfinalesParticipanteService;
import com.progeduc.service.ITrabajosfinalesService;
import com.progeduc.service.IUsuarioOdsService;
import com.progeduc.service.IUsuarioService;
import com.progeduc.service.IUsuario_odsService;
import com.progeduc.service.impl.UploadFileService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("")
//@RequestMapping("/programaeducativo")
//@CrossOrigin(origins = "*")
public class ProgramaeducativoController {
	
	@Autowired
	IProgramaeducativoService progeducService;
	
	@Autowired
	IProgeducNivelService progeducNivelService;
	
	@Autowired
	IProgeducTurnoService progeducTurnoService;	
	
	@Autowired
	private IPostulacionconcursoService postulacionconcursoServ;
	
	@Autowired
	ICategoriaService categoriaService;
	
	@Autowired
	IAperturaranioService aperturaranioService;
	
	@Autowired
	IUsuario_odsService usuarioodsService;
	
	@Autowired
	IUsuarioOdsService usuarioodsServ;
	
	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	private IDocentetutorService docentetutorserv;
	
	@Autowired
	private IDocenteService docenteserv;
	
	@Autowired
	private IOdsService odsserv;
	
	@Autowired
	private ITipousuarioService tipousuarioserv;
	
	@Autowired
	private IDepartamentoService depaServ;
	
	@Autowired
	private IProvinciaService provServ;
	
	@Autowired
	private IDistritoService distServ;
	
	@Autowired
	private IParticipanteService participanteServ;
	
	@Autowired
	private IEvaluacionService evaluacionServ;
	
	@Autowired
	private IEvaluacionRubricaService evaluacionrubricaServ;
	
	@Autowired
	private IEvaluacionQuestionarioService evaluacionquestionarioServ;
	
	@Autowired
	private ITrabajosfinalesService trabajosfinalesServ;
	
	@Autowired
	private ITrabajosfinalesParticipanteService trabajosfinalesparticipanteServ;
	
	@Autowired
	private IGeneroprofService generoprofserv;
	
	@Autowired
	private ISuministroService suministroServ;
	
	ProgeducTurnoNivelDto dto;
	List<Nivel> listNivel;
	List<Ods> listaOds;
	List<Turno> listTurno;
	ProgeducDto pedto;
	ListaInstitucionEducativa listaie;
	List<ProgeducDto> listProgeducDto;
	ProgramaeducativoTurno progeducTurno;
	boolean banderaOds;
	
	private String turno;
	private String nivel;
	private String suministro;
	Date fechaNueva=new Date();
	
	String idods;
	
	Usuarioemail usuarioEmail;
	
	@Autowired
	IUsuarioService usuarioServ;
	
	@Autowired
	private UploadFileService uploadfile;
	
	Mail mail ;	
	String ejestematicos;
	
	Calendar fecha;
	
	@GetMapping("/searchid/{id}")
	public ResponseEntity<ProgeducTurnoNivelDto> searchId(@PathVariable("id") Integer id){
		
		listNivel = new ArrayList<Nivel>();
		listTurno = new ArrayList<Turno>();
		
		Programaeducativo obj = progeducService.ListarporId(id);
		
		progeducNivelService.listProgeducNivel(obj.getId()).forEach(obj1->{
			listNivel.add(obj1.getNivel());
		});
		
		progeducTurnoService.listProgeducTurno(obj.getId()).forEach(obj1->{
			listTurno.add(obj1.getTurno());
		});
		
		dto  = new ProgeducTurnoNivelDto();
		
		dto.setProgeduc(obj);
		dto.setListNivel(listNivel);
		dto.setListTurno(listTurno);
		
		return new ResponseEntity<ProgeducTurnoNivelDto>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/getcodmodbaa/{id}")
	public Programaeducativo searchCodmodBA(@PathVariable("id") String id){
		Programaeducativo obj = progeducService.getCodmodByAnioActual(id);
		if(obj==null) {
			return null;
		}
		return obj;
	}
	
	@GetMapping("/searchcodmod/{id}")
	public ResponseEntity<ProgeducTurnoNivelDto> searchCodmod(@PathVariable("id") String id){
		
		listNivel = new ArrayList<Nivel>();
		listTurno = new ArrayList<Turno>();
		
		Programaeducativo obj = progeducService.getCodmod(id);
		
		if(obj == null) {
			return null;
		}
		else {
			progeducNivelService.listProgeducNivel(obj.getId()).forEach(obj1->{
				listNivel.add(obj1.getNivel());
			});
			
			progeducTurnoService.listProgeducTurno(obj.getId()).forEach(obj1->{
				listTurno.add(obj1.getTurno());
			});
			
			dto  = new ProgeducTurnoNivelDto();
			
			dto.setProgeduc(obj);
			dto.setListNivel(listNivel);
			dto.setListTurno(listTurno);
			
			return new ResponseEntity<ProgeducTurnoNivelDto>(dto, HttpStatus.OK);
		}
	}
	
	@GetMapping("/listcegroupbycodmod")
	public List<String> listCEGroupbyCodmod(){
		return  progeducService.listCentrosEducativosGroupbyCodmod();
	}
	
	@GetMapping("/listaaperturaranio")
	public ResponseEntity<List<Aperturaranio>> listAperturaranio(){
		return new ResponseEntity<List<Aperturaranio>>(aperturaranioService.listar(), HttpStatus.OK) ;
	}	
	
	@PostMapping(value="/listaprogeduc")
	public ResponseEntity<List<ProgeducDto>> listaprogeduc(@Valid @RequestBody ConsultapeDto dto,HttpSession ses) {
		
		pedto = new ProgeducDto();
		String usuario = ses.getAttribute("usuario").toString();
		List<ProgeducDto> listProgeducDto = new ArrayList<ProgeducDto>();
		progeducService.listarConsultaPe(usuario, dto.getFechaDesde(), dto.getFechaHasta(), dto.getNombreie(), dto.getDepartamento(), dto.getProvincia(), dto.getDistrito(), dto.getInscritoce()).forEach(obj->{
			turno = "";
			nivel = "";
			suministro="";
			pedto = new ProgeducDto();
			pedto.setOds(obj.getOds());
			pedto.setDepartamento(obj.getDepartamento());
			pedto.setProvincia(obj.getProvincia());
			pedto.setDistrito(obj.getDistrito());
			pedto.setInsteduc(obj.getInsteduc());			
			pedto.setInscrito_ce(postulacionconcursoServ.getByIdAnio(obj.getId(), obj.getAnio())!=null?"Si":"No");					
			pedto.setFecharegistro(obj.getFecharegistro());
			pedto.setCodlocalie(obj.getCodlocalie());
			pedto.setEstado(null);
			pedto.setAmbito(obj.getAmbito());
			pedto.setModensenianza(obj.getModensenianza());
			pedto.setId(obj.getId());
			pedto.setTipoieid(obj.getTipoieid());
			pedto.setDirie(obj.getDirie());
			pedto.setDre(obj.getDre());
			pedto.setUgel(obj.getUgel());
			pedto.setTelfie(obj.getTelfie());
			pedto.setMailie(obj.getMailie());
			pedto.setFacebook(obj.getFacebook());
			pedto.setLengua(obj.getLengua());		
			pedto.setGenero(obj.getGenero());
			pedto.setProvedor(obj.getProvedor());			
			suministroServ.listarPorProgramaeducativo(obj.getId()).forEach(objS->{
				suministro += objS.getNumero() + " / ";
			});
			listTurno = new ArrayList<Turno>();
			progeducTurnoService.listProgeducTurno(obj.getId()).forEach(obj1->{
				turno += " " + obj1.getTurno().getDescripcion();
			});
			pedto.setTurno(turno);
			listNivel = new ArrayList<Nivel>();
			progeducNivelService.listProgeducNivel(obj.getId()).forEach(obj2->{
				nivel += " "+ obj2.getNivel().getTiponivel().getDescripcion() + "-"+(obj2.getNivel().getNrosecciones()!=null?obj2.getNivel().getNrosecciones():'0')+"-"+(obj2.getNivel().getNrodocentes()!=null?obj2.getNivel().getNrodocentes():'0')+"-" + (obj2.getNivel().getNroalumnos()!=null?obj2.getNivel().getNroalumnos():'0')+ "-"+(obj2.getNivel().getNrovarones()!=null?obj2.getNivel().getNrovarones():'0') + "-"+(obj2.getNivel().getNromujeres()!=null?obj2.getNivel().getNromujeres():'0' )+ "/";
			});
			pedto.setNivel(nivel);
			pedto.setSuministro(suministro);
			pedto.setHora_abastecimiento(obj.getHora_abastecimiento());
			pedto.setPiscina(obj.getPiscina());			
			pedto.setTipodocdir(obj.getTipodocdir());
			pedto.setNrodocidentdir(obj.getNrodocidentdir());
			pedto.setApedir(obj.getApedir());
			pedto.setNomdir(obj.getNomdir());
			pedto.setGenerodir(obj.getGenerodir());				
			pedto.setTeldir(obj.getTeldir());
			pedto.setCeldir(obj.getCeldir());
			pedto.setCorreodir(obj.getCorreodir());			
			pedto.setTipodocprof(obj.getTipodocprof());
			pedto.setNrodocidentprof(obj.getNrodocidentprof());
			pedto.setApeprof(obj.getApeprof());
			pedto.setNomprof(obj.getNomprof());
			pedto.setGeneroprof(obj.getGeneroprof());
			pedto.setTelprof(obj.getTelprof());
			pedto.setCelprof(obj.getCelprof());
			pedto.setCorreoprof(obj.getCorreoprof());				
			listProgeducDto.add(pedto);			
		});
		return new ResponseEntity<List<ProgeducDto>>(listProgeducDto, HttpStatus.OK) ;
	}
	
	
	@PostMapping(value="/listacolegiosinscritos")
	public ResponseEntity<List<ListaInstitucionEducativa>> listacolegiosinscritos(@Valid @RequestBody FilterListAprobacionInscripcionDto dto, HttpSession ses) {
		
		Integer tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		List<ListaInstitucionEducativa> arrayie = new ArrayList<ListaInstitucionEducativa>();
		
		if (tipousuarioid==30){
			progeducService.listarAprobacionInscripcion(dto.getIdods(),dto.getAnio(),dto.getNombreie(),dto.getEstado()).forEach(obj->{
				listaie= new ListaInstitucionEducativa();
				listaie.setOds(obj.getOds());
				listaie.setAnhio(obj.getAnhio());
				listaie.setNomie(obj.getNomie());
				listaie.setCodmod(obj.getCodmod());
				listaie.setEstado(obj.getEstado());
				listaie.setId(obj.getId());
				listaie.setMotivoobservacion(obj.getMotivoobservacion());
				arrayie.add(listaie);
			});
		}
		else{
			String usuario = ses.getAttribute("usuario").toString();
			Usuario user = usuarioServ.byUsuario(usuario);
			idods = "";
			usuarioodsServ.listarByUsuarioAprobacionInscripciones(user.getId(),dto.getIdods(),dto.getAnio(),dto.getNombreie(),dto.getEstado()).forEach(obj->{
				listaie= new ListaInstitucionEducativa();
				listaie.setOds(obj.getOds());
				listaie.setAnhio(obj.getAnhio());
				listaie.setNomie(obj.getNomie());
				listaie.setCodmod(obj.getCodmod());
				listaie.setEstado(obj.getEstado());
				listaie.setId(obj.getId());
				listaie.setMotivoobservacion(obj.getMotivoobservacion());
				arrayie.add(listaie);
			});
		}
		return new ResponseEntity<List<ListaInstitucionEducativa>>(arrayie, HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public Programaeducativo registrar(@Valid @RequestBody Programaeducativo pe){
		
		return progeducService.save(pe);
	}
	
	
	@GetMapping("/buscarMotivoPrograma/{id}")
	public String buscarMotivoPrograma(@PathVariable("id") Integer id){
		
		return progeducService.buscarMotivoPrograma(id);
	}
	
	@PostMapping(value = "/saveaperturaanio")
	public Integer saveaperturaanio(@Valid @RequestBody Aperturaranio pe){
		
		int anio = pe.getAnio();
		
		if(aperturaranioService.buscarSiExiste(anio)) {
			return 1; /*existe*/
		}
		else {
			if(aperturaranioService.registrar(pe)!=null) {
				return 2; /*lo registra*/
			}
			else {
				return 3; /*error al registrar*/
			}
		}
	}
	
	@PostMapping(value = "/updateaperturaanio")
	public Integer updateaperturaanio(@Valid @RequestBody Aperturaranio pe){
		
		if(aperturaranioService.registrar(pe)!=null) {
			return 2; /*lo registra*/
		}
		else {
			return 3; /*error al registrar*/
		}
	}
	
	@PostMapping(value = "/editaraperturaanio")
	public Integer editaraperturaanio(@Valid @RequestBody Aperturaranio pe){
		if(aperturaranioService.modificar(pe) != null) {
			return 2; /*se actualizo*/
		}
		else {
			return 3; /*error al actualizar*/
		}
	}
	
	
	@PostMapping(value= "/anioaperturado/{anio}")
	public boolean anioaperturado(@PathVariable("anio") Integer anio){
		
		return aperturaranioService.buscarSiExiste(anio);
	}	
	
	@PostMapping(value= "/updateestadoprogramaeducativo")
	public int updateestadoprogramaeducativo(@Valid @RequestBody UpdateObservarProgramaDto dto  ){
		
		int id = dto.getId();
		String estado = dto.getEstado();
		String motivoobservacion = dto.getMotivoObservacion();
		
		Programaeducativo pe = progeducService.ListarporId(id);
		String url = "http://prometeo.sunass.gob.pe/pedesa/inicioficha/" + pe.getId();
		
		if( progeducService.updateestado(id, estado,motivoobservacion) == 1) {
			String msj = "<p>Sistema del Programa Educativo Sunass</p><p>Estimado(a) docente, su registro ha sido observado debido a: " + motivoobservacion +" por favor vuelva a ingresar al formulario de registro para subsanar lo señalado, haciendo clic <a href='"+url+"'>Aquí</a> </p><p>Saludos</p>";
			mail = new Mail();
			if( mail.enviarCorreoIEobs(msj,pe.getMaildir())) {
				if(mail.enviarCorreoIEobs(msj,pe.getMailprof() )) {
					return 1;
				}
			}				
			return 0;
		}
		return 0;
	}
	
	@PostMapping(value= "/updateaprobarprogramaeducativo")
	public boolean updateaprobarprogramaeducativo(@Valid @RequestBody UpdateAprobarProgramaDto dto  ){
		
		int id = dto.getId();
		Programaeducativo pe = progeducService.ListarporId(id);
		String estado = dto.getEstado();
		if(progeducService.updateestadoaprobar(id, estado) == 1) {
			String codmod = pe.getCodmod();
			String dni = pe.getDocdir();
			Usuario us = usuarioService.byUsuario(codmod);
			if(us!=null) {
				dni = us.getPassword();
			}			
			if(usuarioService.byUsuario(codmod)==null) {						
				Usuario user = new Usuario(codmod,dni,tipousuarioserv.byTipousuario(3),1);
				usuarioService.registrar(user);
			}
			String mensaje = "<p>Sistema del Programa Educativo Sunass</p>Estimado (a) docente, le damos la bienvenida al Programa Educativo<br>'Aprendiendo a Usar Responsablemente el Agua Potable' de la Sunass. Si desea participar de nuestro Concurso Escolar confirme su inscripción <a href='http://prometeo.sunass.gob.pe/pedesa/'>Aquí</a>, ingresando el usuario y contraseña que le brindamos a continuación :<br><br>Usuario :"+ codmod+"<br>Contraseña :" + dni + "<br><br>Muchas gracias por su participación";                                        
			mail = new Mail();		
			if( mail.enviarCorreoIE(mensaje,pe.getMaildir())) {
				if( mail.enviarCorreoIE(mensaje,pe.getMailprof())) {					
					return true;
				}
			}	
		}
		return false;
	}	
	
	@PostMapping(value = "/saveturnonivel")
	public boolean registrarTurnoNivel(@Valid @RequestBody ProgeducTurnoNivelDto dto){
		
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		dto.getProgeduc().setFecha_registro(ts);
		dto.getProgeduc().setAnhio(ts.toLocalDateTime().getYear());	
		
		if(progeducService.saveProgeducTurnoNivel(dto) !=null) {
			mail = new Mail();
			if( mail.enviarCorreoRegistro(dto.getProgeduc().getMaildir())) {
				return mail.enviarCorreoRegistro(dto.getProgeduc().getMailprof());
			}
		}		
		return false;
	}
	
	@PostMapping(value = "/updateturnonivel")
	public boolean updateTurnoNivel(@Valid @RequestBody ProgeducUpdateTurnoNivelDto dto){
		
		int id = dto.getProgeduc().getId();
		int anio = progeducService.ListarporId(id).getAnhio();
		dto.getProgeduc().setAnhio(anio);
		
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		dto.getProgeduc().setFecha_registro(ts);
		
		if(progeducService.updateProgeducTurnoNivel(dto) !=null) {
			return true;
		}		
		return false;
	}
	
	@PostMapping(value="/savecategoria")
	public Integer registrarCategoria(@Valid @RequestBody ListaCategoriaDto dto){
		
		return categoriaService.guardar(dto);
	}
	
	@PostMapping(value="/actualizarcontraseniacorreo")
	public Integer actualizarcontraseniacorreo(@Valid @RequestBody ActualizarContraseniaDto dto){
		
		if(dto.getTipo()=="us") {
			return usuarioService.updatecontrasenia(dto.getId(), dto.getPassword());
		}
		else {
			return usuarioodsService.updatecontrasenia(dto.getId(), dto.getPassword());
		}
	}
	
	@PostMapping(value = "/filtro")
	public ResponseEntity<List<ProgeducDto>> listaprogeduc_filtro(@Valid @RequestBody ProgeducfiltroDto dto){
		
		String fecha_desde = dto.getFecha_desde().trim().isEmpty()?" ":dto.getFecha_desde();
		String fecha_hasta = dto.getFecha_hasta().trim().isEmpty()?" ":dto.getFecha_hasta();
		String nombreie = dto.getNombreie().trim().isEmpty()?" ":dto.getNombreie();
		Integer departamento = dto.getDepartamento();
		Integer provincia = dto.getProvincia();
		Integer distrito = dto.getDistrito();
		
		listProgeducDto = new ArrayList<ProgeducDto>();
		pedto = new ProgeducDto();
		progeducTurno = new ProgramaeducativoTurno();
		
		progeducService.listarfiltro(fecha_desde,fecha_hasta,nombreie,departamento,provincia,distrito).forEach(obj->{
			turno = "";
			nivel = "";
			suministro="";
				pedto = new ProgeducDto();
				pedto.setDepartamento((obj.getDistrito()!=null? (obj.getDistrito().getProvincia()!=null?(obj.getDistrito().getProvincia().getDepartamento()!=null?obj.getDistrito().getProvincia().getDepartamento().getDescripcion():""):""):""));
				pedto.setProvincia((obj.getDistrito()!=null? (obj.getDistrito().getProvincia()!=null?(obj.getDistrito().getProvincia().getDescripcion()):""):""));
				pedto.setDistrito((obj.getDistrito()!=null? (obj.getDistrito().getDescripcion()):""));
				pedto.setInsteduc(obj.getNomie());
				pedto.setFecharegistro(obj.getFecha_registro());
				pedto.setCodlocalie(obj.getCodmod());
				pedto.setEstado(null);
				pedto.setAmbito(obj.getAmbito()!=null?obj.getAmbito().getDescripcion():"");
				pedto.setId(obj.getId());
				pedto.setTipoieid(obj.getTipoie()!=null?obj.getTipoie().getDescripcion():"");
				pedto.setDirie(obj.getDirie());
				pedto.setDre(obj.getDre());
				pedto.setUgel(obj.getUgel());
				pedto.setTelfie(obj.getTelfie());
				pedto.setMailie(obj.getMailie());
				pedto.setFacebook(obj.getFacebook());
				pedto.setLengua(obj.getLengua()!=null?obj.getLengua().getDescripcion():"");		
				pedto.setGenero(obj.getGenero()!=null?obj.getGenero().getDescripcion():"");
				pedto.setProvedor(obj.getProveedor()!=null?obj.getProveedor().getDescripcion():"");
				obj.getSuministro().forEach(obj3->{
					suministro += obj3.getNumero() + " / ";
				});
				
				
				listTurno = new ArrayList<Turno>();
				progeducTurnoService.listProgeducTurno(pedto.getId()).forEach(obj1->{
					turno += " " + obj1.getTurno().getDescripcion();
				});
				pedto.setTurno(turno);
				
				listNivel = new ArrayList<Nivel>();
				
				progeducNivelService.listProgeducNivel(obj.getId()).forEach(obj2->{
					nivel += " "+ obj2.getNivel().getTiponivel().getDescripcion();
				});
				pedto.setNivel(nivel);
				pedto.setSuministro(suministro);
				pedto.setHora_abastecimiento(obj.getAbastecimiento());
				pedto.setPiscina(obj.getPiscina()!=null?obj.getPiscina().getDescripcion():"");
				pedto.setTipodocdir(obj.getTipodocidentdir()!=null?obj.getTipodocidentdir().getDescripcion():"");
				pedto.setNrodocidentdir(obj.getNomdir());
				pedto.setApedir(obj.getApedir());
				pedto.setNomdir(obj.getNomdir());
				pedto.setTeldir(obj.getTelfdir());
				pedto.setCeldir(obj.getCeldir());
				pedto.setCorreodir(obj.getMaildir());
				pedto.setTipodocprof(obj.getTipodocidentprof()!=null?obj.getTipodocidentprof().getDescripcion():"");
				pedto.setNrodocidentprof(obj.getNomprof());
				pedto.setApeprof(obj.getApeprof());
				pedto.setNomprof(obj.getNomprof());
				pedto.setTelprof(obj.getTelfprof());
				pedto.setCelprof(obj.getCelprof());
				pedto.setCorreoprof(obj.getMailprof());
				listProgeducDto.add(pedto);
		});
		return new ResponseEntity<List<ProgeducDto>>(listProgeducDto, HttpStatus.OK) ;
	}
	
	
	@GetMapping(value="/descargarpdf/{id}")
	public String descargarpdf(@PathVariable("id") Integer id, Model model) throws FileNotFoundException, JRException  {
		
		Programaeducativo pe = progeducService.ListarporId(id);
		return crearPdf(pe);
	}
	
	@GetMapping(value="/descargarparticipantepdf/{id}")
	public String descargarparticipantepdf(@PathVariable("id") Integer id, Model model) throws FileNotFoundException, JRException  {
		
		Participante part = participanteServ.ListarporId(id);
		return crearparticipantePdf(part);
	}
	
	@GetMapping(value="/descargarevaluacionpdf/{id}")
	public String descargarevaluacionpdf(@PathVariable("id") Integer id, Model model) throws FileNotFoundException, JRException  {
		
		Evaluacion eval = evaluacionServ.ListarporId(id);
		return crearEvaluacionPdf(eval);
	}
	
	@GetMapping(value="/descargarfichatrabajoconcursopdf/{id}")
	public String descargarFichaTrabajoConcursoPdf(@PathVariable("id") Integer id, Model model) throws FileNotFoundException, JRException  {
		
		//Evaluacion eval = evaluacionServ.ListarporId(id);
		Trabajosfinales trabajosFinales = trabajosfinalesServ.ListarporId(id);
		return crearFichaTrabajoConcursoPdf(trabajosFinales);
	}
	
	@GetMapping(value="/fichaautorizacionpdf/{id}")
	public String fichaautorizacionpdf(@PathVariable("id") Integer id, Model model) throws FileNotFoundException, JRException  {
		
		return "../alfresco_programaeducativo/pedesa/upload_participantes/"+ id.toString() + "/"+ uploadfile.buscarArchivo(id,"upload_participantes");
	}
	
	
	@PostMapping(value="/recuperarcorreo")
	public int recuperarcorreo(@Valid @RequestBody DatocorreoDto dto)  {
		
		Usuario obj = usuarioServ.verificarexistenciausuario(dto.getUsuario());
    	if(obj != null) {
    		mail = new Mail();
    		Programaeducativo pe = progeducService.getActualByCodmod(dto.getUsuario());
        	if(mail.enviarCorreoIE("Su contraseña es : " + obj.getPassword(), pe.getMaildir()))
        		if(mail.enviarCorreoIE("Su contraseña es : " + obj.getPassword(), pe.getMailprof()))
            		return 1;
    	}
    	return 0;
	}
	
	@PostMapping(value="/guardardocentetutor")
	public int guardardocentetutor(@Valid @RequestBody Docentetutor obj)  {
		
		if(docentetutorserv.registrar(obj)!=null)
			return 1;
		return 0;
		
	}	
	
	public String crearEvaluacionPdf(Evaluacion eval) throws FileNotFoundException, JRException {
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	
	 	File file = ResourceUtils.getFile("classpath:ficha_evaluacion.jrxml" );
	 	/*File file_sr_questionario_respuesta = ResourceUtils.getFile("classpath:sr_questionario_respuesta.jrxml" );*/
		JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
		/*JasperReport jr_questionario_respuesta = JasperCompileManager.compileReport(file_sr_questionario_respuesta.getAbsolutePath());*/
		
		Map parameters = new HashMap<String, Object>();
		
		parameters.put("anio", eval.getAnio());
		parameters.put("categoria", eval.getCategoriaevaluacion().getDescripcion());
		parameters.put("nivelparticipacion", eval.getNivelparticipacion().getDescripcion());
		parameters.put("estado", eval.getEstadoevaluacion().getDescripcion());
		/*parameters.put("sr_questionario_respuesta", jr_questionario_respuesta);*/
        
		List<Rubrica> listaRubrica = new ArrayList<Rubrica>();
        evaluacionrubricaServ.listarPorEvaluacionId(eval.getId()).forEach(obj->{
        	if(obj.getRubrica().getEstado() == 1) {
        		listaRubrica.add(obj.getRubrica());
        	}        	
        });
        
        List<Questionario> listaQuestionario = new ArrayList<Questionario>();
        evaluacionquestionarioServ.listarPorEvaluacionId(eval.getId()).forEach(obj->{
        	if(obj.getQuestionario().getEstado() == 1) {
        		listaQuestionario.add(obj.getQuestionario());
        	}
        });
        
        parameters.put("datasourcerubrica", listaRubrica);
        parameters.put("datasourcequestionario", listaQuestionario);
        //parameters.put("jr_questionario_respuesta" , jr_questionario_respuesta);
        
        //JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listaQuestionario);
        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, new JREmptyDataSource(1));
		//String path = "D:/Sunass/programa_educativo_desarrollo/programaeducativo/src/main/resources/reportes_evaluacion/";
        String path = "/opt/apache-tomcat-8.0.27/webapps/alfresco_programaeducativo/pedesa/reportes_evaluacion/";
		String archivo = eval.getId() + "_"+ dateFormat.format(date) + hourFormat.format(date);
		JasperExportManager.exportReportToPdfFile(jp,path + archivo + ".pdf");		
		//return "D:/Sunass/programa_educativo_desarrollo/programaeducativo/src/main/resources/reportes_evaluacion/"+archivo+".pdf";
		return "/alfresco_programaeducativo/pedesa/reportes_evaluacion/"+archivo+".pdf";
	}
	
	public String crearFichaTrabajoConcursoPdf(Trabajosfinales eval) throws FileNotFoundException, JRException {
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	
	 	File file = ResourceUtils.getFile("classpath:ficha_trabajofinales.jrxml");
		JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		Map parameters = new HashMap();
		
		parameters.put("categoria", eval.getCategoriatrabajo().getDescripcion());
		parameters.put("modalidad", eval.getModalidadtrabajo().getDescripcion());
		parameters.put("titulotrabajo", eval.getTitulotrabajo());
		parameters.put("linkvideo", eval.getLinkvideo()==null?"":eval.getLinkvideo());
		
		
		ejestematicos = ""; 
		if(eval.getConversacion() == 1)
			ejestematicos = "Conservación de las fuentes de agua/";
		if(eval.getValoracionagua() == 1)
			ejestematicos += "Valoración de los servicios de agua potable/";
		if(eval.getValoracionalcantarillado() == 1)
			ejestematicos += "Valoración del servicio de alcantarillado/";
		if(eval.getBuenuso() == 1)
			ejestematicos += "Buen uso y reúso del agua potable/";
		if(eval.getImportancia() == 1)
			ejestematicos += "Importancia de cerrar la brecha en saneamiento/";
		if(eval.getVinculo() == 1)
			ejestematicos += "El vínculo estratégico entre el agua segura y la salud/";
		if(eval.getCarencias() == 1)
			ejestematicos += "Las carencias que ponen en riesgo la vida/";
		if(eval.getRevaloracion()!=null) {
			if(eval.getRevaloracion()==1) {
				ejestematicos += "Revaloración de las prácticas ancestrales para la seguridad hídrica/";
			}
		}			
		
		if(ejestematicos.length()>0)
			ejestematicos = ejestematicos.substring(0, ejestematicos.length()-1);
		
		parameters.put("ejestematicos", ejestematicos);
		
		parameters.put("appaternodocente", eval.getAppaterno());
		parameters.put("apmaternodocente", eval.getApmaterno());
		parameters.put("nombredocente", eval.getNombre());
		parameters.put("tipodocumentodocente", eval.getTipodocumento().getDescripcion());
		parameters.put("nrodocumentodocente", eval.getNrodocumento());
		parameters.put("telefonodocente", eval.getTelefono());
		parameters.put("correodocente", eval.getCorreo());
		parameters.put("generodocente", eval.getGenero().getDescripcion());
		
		
		List<ParticipanteTrabajosfinalesDto> listadto  = new ArrayList<ParticipanteTrabajosfinalesDto>();
		
		trabajosfinalesparticipanteServ.listar(eval.getId()).forEach(obj->{
			
			ParticipanteTrabajosfinalesDto dto = new ParticipanteTrabajosfinalesDto();
			dto.setAppaternopart(obj.getParticipante().getAppaternoestudiante());
			dto.setApmaternopart(obj.getParticipante().getApmaternoestudiante());
			dto.setNombrepart(obj.getParticipante().getNombreestudiante());
			dto.setTipodocumentopart(obj.getParticipante().getTipodocumentoestudiante().getDescripcion());
			dto.setNrodocumentopart(obj.getParticipante().getNrodocumentoestudiante());
			dto.setNivelpart(obj.getParticipante().getGradoestudiante().getNivelgradopartdesc());
			listadto.add(dto);
		}); 
        
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listadto);
		JasperPrint jp = JasperFillManager.fillReport(jr, parameters, datasource);
		String path = "/opt/apache-tomcat-8.0.27/webapps/alfresco_programaeducativo/pedesa/reportes_trabajosfinales/";
		//String path = "D:/Sunass/programaeducativo/src/main/resources/reportes_trabajosfinales/";
		String archivo = eval.getId() + "_"+ dateFormat.format(date) + hourFormat.format(date);
		JasperExportManager.exportReportToPdfFile(jp,path + archivo + ".pdf");
		return "/alfresco_programaeducativo/pedesa/reportes_trabajosfinales/"+archivo+".pdf";			
		//return "D:/Sunass/programaeducativo/src/main/resources/reportes_trabajosfinales/"+archivo+".pdf";
	}
	
	
	public String crearparticipantePdf(Participante pe) throws FileNotFoundException, JRException {
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	
	 	File file = ResourceUtils.getFile("classpath:ficha_participante.jrxml" );
		JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
		
        Map parameters = new HashMap();
        
        String categorias="",modalidad="";
        if(pe.getCategoriaahorroagua()==1)
        	categorias = "ahorro de agua/";
        if(pe.getCategoriacomposicionmusical()==1)
        	categorias += "composición musical/";
        if(pe.getCategoriacuento()==1)
        	categorias += "cuento/";
        if(pe.getCategoriadibujopintura()==1)
        	categorias += "dibujo pintura/";
        if(pe.getCategoriapoesia() ==1)
        	categorias += "poesía/";
        if(categorias.length()>0)
        	categorias = categorias.substring(0,categorias.length()-1);	
        
        if(pe.getModalidadpostulacionindividual()==1)
        	modalidad = "individual/";
        if(pe.getModalidadpostulaciongrupal()==1)
        	modalidad += "grupal/";
        if(modalidad.length()>0)
        	modalidad = modalidad.substring(0,modalidad.length()-1);	
        
        parameters.put("appaternoestudiante", pe.getAppaternoestudiante()!=null ? pe.getAppaternoestudiante() : "");
        parameters.put("apmaternoestudiante", pe.getApmaternoestudiante()!= null ? pe.getApmaternoestudiante() : "");
        parameters.put("nombreestudiante",pe.getNombreestudiante()!=null? pe.getNombreestudiante() : "" );
        parameters.put("tipodocumentoestudiante",pe.getTipodocumentoestudiante()!=null ? pe.getTipodocumentoestudiante().getDescripcion() : "");
        parameters.put("nrodocumentoestudiante", pe.getNrodocumentoestudiante()!=null ? pe.getNrodocumentoestudiante() : "");
        parameters.put("fechanacimientoestudiante",pe.getFechanacimientoestudiante()!=null?pe.getFechanacimientoestudiante().toString():"");
        
        parameters.put("generoestudiante", pe.getGeneroestudiante()!=null ?	(generoprofserv.ListarporId(pe.getGeneroestudiante().getId()).getDescripcion()) : "");
        
        parameters.put("nivelestudiante", pe.getGradoestudiante()!=null ? pe.getGradoestudiante().getNivelparticipante().getDescripcion() : "");
        parameters.put("gradoestudiante", pe.getGradoestudiante()!=null ? pe.getGradoestudiante().getDescripcion() : "");
        parameters.put("seccion", pe.getSeccion()!=null ? pe.getSeccion():"");
        parameters.put("categorias",categorias);
        parameters.put("modalidad", modalidad);
        parameters.put("appaternopmt", pe.getAppaternopmt()!=null ? pe.getAppaternopmt():"");
        parameters.put("apmaternopmt", pe.getApmaternopmt()!=null? pe.getApmaternopmt() : "") ;
        parameters.put("nombrepmt", pe.getNombrepmt()!=null ? pe.getNombrepmt() :"");
        parameters.put("parentesco", pe.getParentesco()!=null ? (pe.getParentesco().getDescripcion()!=null? pe.getParentesco().getDescripcion(): "") :"");
        parameters.put("tipodocumentopmt", pe.getTipodocumentopmt()!=null? (pe.getTipodocumentopmt().getDescripcion()!=null?pe.getTipodocumentopmt().getDescripcion():"")  :"");
        parameters.put("nrodocumentopmt", pe.getNrodocumentopmt()!=null?pe.getNrodocumentopmt() :"");
        parameters.put("nrotelefonopmt", pe.getNrotelefonopmt()!=null? pe.getNrotelefonopmt() : "") ;
        parameters.put("correoelectronicopmt", pe.getCorreoelectronicopmt()!=null? pe.getCorreoelectronicopmt() : "") ;
        
		JasperPrint jp = JasperFillManager.fillReport(jr, parameters, new JREmptyDataSource(1));
		String path = "/opt/apache-tomcat-8.0.27/webapps/alfresco_programaeducativo/pedesa/reportes_participantes/";
		//String path = "D:/Edwin/ProyectosSunass/ProgEducativo/reportes/";
		String archivo = pe.getId() + "_"+ dateFormat.format(date) + hourFormat.format(date);
		JasperExportManager.exportReportToPdfFile(jp,path + archivo + ".pdf");
		return "/alfresco_programaeducativo/pedesa/reportes_participantes/"+archivo+".pdf";			
	}
	

	public String crearPdf(Programaeducativo pe) throws FileNotFoundException, JRException {
			
			Date date = new Date();
			DateFormat hourFormat = new SimpleDateFormat("HHmmss");
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		 	File file = ResourceUtils.getFile("classpath:pedesa.jrxml" );
			JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
			
	        Map parameters = new HashMap();	        
	        
	        parameters.put("codmod", pe.getCodmod()!=null ? pe.getCodmod() : "");
	        parameters.put("nomie", pe.getNomie()!= null ? pe.getNomie() : "");
	        parameters.put("tipoie",pe.getTipoie()!=null? pe.getTipoie().getDescripcion() : "" );
	        parameters.put("departamento", depaServ.ListarporId(pe.getDep())!=null ? depaServ.ListarporId(pe.getDep()).getDescripcion() : "");
	        parameters.put("provincia",provServ.getById(pe.getProv())!=null ? provServ.getById(pe.getProv()).getDescripcion() : "");
	        parameters.put("distrito",distServ.getById(pe.getDistrito().getId())!=null?distServ.getById(pe.getDistrito().getId()).getDescripcion():"");
	        parameters.put("direccion", pe.getDirie());
	        parameters.put("dre", pe.getDre());
	        parameters.put("ugel", pe.getUgel());
	        parameters.put("telefono",pe.getTelfie() );
	        parameters.put("correoinst", pe.getMailie());
	        parameters.put("linkface", pe.getFacebook());
	        parameters.put("lengua", pe.getLengua()!=null ? pe.getLengua().getDescripcion() : "") ;
	        parameters.put("ambitou", pe.getAmbito()!=null?( pe.getAmbito().getId()==1 ? pe.getAmbito().getDescripcion():""):"");
	        parameters.put("ambitor", pe.getAmbito()!=null?( pe.getAmbito().getId()==2 ? pe.getAmbito().getDescripcion():""):"");
	        parameters.put("ambitop", pe.getAmbito()!=null?( pe.getAmbito().getId()==3? pe.getAmbito().getDescripcion():""):"");
	        parameters.put("modalidadb", pe.getModensenianza()!=null?(pe.getModensenianza().getId()==1 ? pe.getModensenianza().getDescripcion():""):"");
	        parameters.put("modalidada", pe.getModensenianza()!=null?(pe.getModensenianza().getId()==2 ? pe.getModensenianza().getDescripcion():""):"");
	        parameters.put("modalidade", pe.getModensenianza()!=null?(pe.getModensenianza().getId()==3 ? pe.getModensenianza().getDescripcion():""):"");
	        parameters.put("generova", pe.getGenero()!=null? (pe.getGenero().getId() == 1 ? pe.getGenero().getDescripcion() : ""):"");
	        parameters.put("generomu", pe.getGenero()!=null? (pe.getGenero().getId() == 2 ? pe.getGenero().getDescripcion() : ""):"");
	        parameters.put("generomi", pe.getGenero()!=null? (pe.getGenero().getId() == 3 ? pe.getGenero().getDescripcion() : ""):"");
	        
	        List<ProgramaeducativoTurno> progeduc= progeducTurnoService.listProgeducTurno(pe.getId());		
			boolean manana=false,tarde=false,noche=false;
			String turnoma="",turnota="",turnono="";
			for(int i=0;i<progeduc.size();i++) {
				switch(progeduc.get(i).getTurno().getId()) {
					case 1: turnoma = "Mañana"; break;
					case 2: turnota = "Tarde"; break;
					case 3: turnono = "Noche"; break;
				}
			}
			parameters.put("turnoma", turnoma);
			parameters.put("turnota", turnota);
			parameters.put("turnono", turnono);
			
			
			List<ProgramaeducativoNivel> listpe= progeducNivelService.listProgeducNivel(pe.getId());
			boolean tninicial=false , tnprimaria=false, tnsecundaria=false;
			String niveli="Inicial"; int nisec=0,nidoc=0,nial=0,niva=0,nimuj=0;
			String nivelp="Primaria"; int npsec=0,npdoc=0,npal=0,npva=0,npmuj=0;
			String nivels="Secundaria"; int nssec=0,nsdoc=0,nsal=0,nsva=0,nsmuj=0;
			
			for(int j=0;j<listpe.size();j++) {
				switch(listpe.get(j).getNivel().getTiponivel().getId()) {
					case 1: 
						nisec=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrosecciones()!=null?listpe.get(j).getNivel().getNrosecciones():0):0;
						nidoc=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrodocentes()!=null?listpe.get(j).getNivel().getNrodocentes():0):0;
						nial=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNroalumnos()!=null?listpe.get(j).getNivel().getNroalumnos():0):0;
						niva=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrovarones()!=null?listpe.get(j).getNivel().getNrovarones():0):0;
						nimuj=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNromujeres()!=null?listpe.get(j).getNivel().getNromujeres():0):0;
					break;
					case 2: 
						npsec = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrosecciones()!=null?listpe.get(j).getNivel().getNrosecciones():0):0;
						npdoc = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrodocentes()!=null?listpe.get(j).getNivel().getNrodocentes():0):0;
						npal = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNroalumnos()!=null?listpe.get(j).getNivel().getNroalumnos():0):0;
						npva = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrovarones()!=null?listpe.get(j).getNivel().getNrovarones():0):0;
						npmuj = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNromujeres()!=null?listpe.get(j).getNivel().getNromujeres():0):0;
					break;
					case 3: 
						nssec = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrosecciones()!=null?listpe.get(j).getNivel().getNrosecciones():0):0;
						nsdoc = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrodocentes()!=null?listpe.get(j).getNivel().getNrodocentes():0):0;
						nsal = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNroalumnos()!=null?listpe.get(j).getNivel().getNroalumnos():0):0;
						nsva = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrovarones()!=null?listpe.get(j).getNivel().getNrovarones():0):0;
						nsmuj = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNromujeres()!=null?listpe.get(j).getNivel().getNromujeres():0):0;
					break;
				}
			}	
			
			parameters.put("niveli", niveli);
	        parameters.put("seccioni", nisec);
	        parameters.put("doci",nidoc );
	        parameters.put("alumi", nial);
	        parameters.put("vari",niva );
	        parameters.put("muji", nimuj);
	        
	        parameters.put("nivelp", nivelp);
	        parameters.put("seccionp", npsec);
	        parameters.put("docp", npdoc);
	        parameters.put("alump", npal);
	        parameters.put("varp", npva);
	        parameters.put("mujp", npmuj);
	        
	        parameters.put("nivels", nivels);
	        parameters.put("seccions", nssec);
	        parameters.put("docs", nsdoc);
	        parameters.put("alums", nsdoc);
	        parameters.put("vars", nsva);
	        parameters.put("mujs", nsmuj);
	        
	        parameters.put("provedorserv", pe.getProveedor()!=null?pe.getProveedor().getDescripcion():"");
	        parameters.put("horasabact", pe.getAbastecimiento()!=null?pe.getAbastecimiento():0);
	        parameters.put("piscina", pe.getPiscina()!=null?pe.getPiscina().getDescripcion():"");
	        parameters.put("dirtipodocident", pe.getTipodocidentdir()!= null? pe.getTipodocidentdir().getDescripcion() : "");
	        parameters.put("dirnrodocident", pe.getDocdir());
	        parameters.put("dirapellidos", pe.getApedir());
	        parameters.put("dirnombres", pe.getNomdir());
	        parameters.put("dirgenero", pe.getGenerodir()!=null?pe.getGenerodir().getDescripcion():"");
	        parameters.put("dirtelefono", pe.getTelfdir());
	        parameters.put("dircelular", pe.getCeldir());
	        parameters.put("dircorreo", pe.getMaildir());
	        parameters.put("proftipodocident", pe.getTipodocidentprof()!=null?pe.getTipodocidentprof().getDescripcion():"");
	        parameters.put("profnrodocident", pe.getDocprof());
	        parameters.put("profapellidos", pe.getApeprof());
	        parameters.put("profnombres", pe.getNomprof());
	        parameters.put("profgenero", pe.getGeneroprof()!=null?pe.getGeneroprof().getDescripcion():"");
	        parameters.put("proftelefono", pe.getTelfprof() );
	        parameters.put("profcelular", pe.getCelprof());
	        parameters.put("profcorreo", pe.getMailprof());
	        
	        JasperPrint jp = JasperFillManager.fillReport(jr, parameters, new JREmptyDataSource(1));
			String path = "/opt/apache-tomcat-8.0.27/webapps/alfresco_programaeducativo/pedesa/reportes/";
			//String path = "D:/Edwin/ProyectosSunass/ProgEducativo/reportes/";
			String archivo = pe.getCodmod() + "_"+ dateFormat.format(date) + hourFormat.format(date);
			JasperExportManager.exportReportToPdfFile(jp,path + archivo + ".pdf");			
			return "/alfresco_programaeducativo/pedesa/reportes/"+archivo+".pdf";			
	}
	
}
