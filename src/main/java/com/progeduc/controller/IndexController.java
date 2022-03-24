package com.progeduc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.progeduc.componente.Ldap;
import com.progeduc.dto.DetalleEvaluacionDto;
import com.progeduc.interfac.DepartamentoDto;
import com.progeduc.model.Aperturaranio;
import com.progeduc.model.Docente;
import com.progeduc.model.Docentetutor;
import com.progeduc.model.Evaluacion;
import com.progeduc.model.Modalidadtrabajo;
import com.progeduc.model.Ods;
import com.progeduc.model.Participante;
import com.progeduc.model.Postulacionconcurso;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.ProgramaeducativoNivel;
import com.progeduc.model.ProgramaeducativoTurno;
import com.progeduc.model.Questionario;
import com.progeduc.model.Rubrica;
import com.progeduc.model.Suministro;
import com.progeduc.model.Trabajosfinales;
import com.progeduc.model.TrabajosfinalesParticipante;
import com.progeduc.model.Usuario;
import com.progeduc.model.UsuarioLdap;
import com.progeduc.service.IAperturaranioService;
import com.progeduc.service.ICategoriaevaluacionService;
import com.progeduc.service.ICategoriatrabajoService;
import com.progeduc.service.ICerrarOdsService;
import com.progeduc.service.IDepartamentoService;
import com.progeduc.service.IDistritoService;
import com.progeduc.service.IDocenteService;
import com.progeduc.service.IDocentetutorService;
import com.progeduc.service.IEstadoevaluacionService;
import com.progeduc.service.IEvaluacionQuestionarioService;
import com.progeduc.service.IEvaluacionRubricaService;
import com.progeduc.service.IEvaluacionService;
import com.progeduc.service.IGeneroService;
import com.progeduc.service.IGenerodirService;
import com.progeduc.service.IGeneroprofService;
import com.progeduc.service.IGradoparticipanteService;
import com.progeduc.service.IModalidadtrabajoService;
import com.progeduc.service.INivelparticipacionService;
import com.progeduc.service.INivelparticipanteService;
import com.progeduc.service.IOdsService;
import com.progeduc.service.IParentescoService;
import com.progeduc.service.IParticipanteService;
import com.progeduc.service.IPostulacionconcursoService;
import com.progeduc.service.IProgeducNivelService;
import com.progeduc.service.IProgeducTurnoService;
import com.progeduc.service.IProgramaeducativoService;
import com.progeduc.service.IProvinciaService;
import com.progeduc.service.IResponsableregistroService;
import com.progeduc.service.ITipoAuspicioService;
import com.progeduc.service.ITipodocumentoService;
import com.progeduc.service.ITipousuarioService;
import com.progeduc.service.ITrabajosfinalesParticipanteService;
import com.progeduc.service.ITrabajosfinalesService;
import com.progeduc.service.ITrabajosfinales_UsuarioAlianzaNacionalService;
import com.progeduc.service.ITrabajosfinales_UsuarioAlianzaService;
import com.progeduc.service.IUsuarioAlianzaService;
import com.progeduc.service.IUsuarioOdsService;
import com.progeduc.service.IUsuarioService;
import com.progeduc.service.LenguaService;
import com.progeduc.service.ProveedorService;
import com.progeduc.service.TipodocService;
import com.progeduc.service.TipoieService;
import com.progeduc.service.impl.UploadFileService;

@Controller
@RequestMapping("")
public class IndexController {
	
	@Autowired
	private IProgramaeducativoService progeducService;
	
	@Autowired
	private IProgeducTurnoService progeducturnoService;
	
	@Autowired
	private IProgeducNivelService progeducnivelService;
	
	@Autowired
	private IDocentetutorService docentetutorService;
	
	@Autowired
	private IPostulacionconcursoService postulacionconcursoService;
	
	@Autowired
	private LenguaService lenguaserv;
	
	@Autowired
	private IGeneroService generoserv;
	
	@Autowired
	private IGenerodirService generodirserv;
	
	@Autowired
	private IResponsableregistroService responsableregistroserv;
	
	@Autowired
	private IGeneroprofService generoprofserv;
	
	@Autowired
	private TipoieService tipoieserv;
	
	@Autowired
	private IDepartamentoService depaServ;
	
	@Autowired
	private IProvinciaService provServ;
	
	@Autowired
	private IDistritoService distServ;
	
	@Autowired
	private ProveedorService proveedorserv;
	
	@Autowired
	private TipodocService tipodocserv;
	
	@Autowired
	private ITipodocumentoService tipodocumentoserv;
	
	@Autowired
	IAperturaranioService aperturaranioService;
	
	@Autowired
	INivelparticipanteService nivelparticipanteService;
	
	@Autowired
	IGradoparticipanteService gradoparticipanteService;
	
	@Autowired
	IParentescoService parentescoService;
	
	@Autowired
	private IOdsService odsserv;
	
	@Autowired
	IParticipanteService participanteService;
	
	@Autowired
	IDocenteService docenteService;
	
	@Autowired
	ICategoriatrabajoService categoriatrabajoService;
	
	@Autowired
	IModalidadtrabajoService modalidadtrabajoService;
	
	@Autowired
	ITrabajosfinalesService trabajosfinalesService;
	
	@Autowired
	ITrabajosfinalesParticipanteService trabajosfinalesparticipanteService;
	
	@Autowired
	INivelparticipacionService nivelparticipacionService;
	
	@Autowired
	ICategoriaevaluacionService categoriaevaluacionService;
	
	@Autowired
	IEstadoevaluacionService estadoevaluacionService;
	
	@Autowired
	IEvaluacionService evaluacionService;
	
	@Autowired
	private UploadFileService uploadfile;
	
	@Autowired
	private IEvaluacionRubricaService evaluacionrubricaServ;
	
	@Autowired
	private IEvaluacionQuestionarioService evaluacionquestionarioServ;
	
	@Autowired
	private ITipousuarioService tipousuarioServ;
	
	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	IUsuarioOdsService usuarioodsService; 
	
	@Autowired
	private IUsuarioAlianzaService usuAlianzaserv;
	
	@Autowired
	private ITipoAuspicioService tipoAuspicioServ;
	
	@Autowired
	private ICerrarOdsService cerrarOdsService;
	
	@Autowired
	private ITrabajosfinales_UsuarioAlianzaService trabfin_usuarioal;
	
	@Autowired
	private ITrabajosfinales_UsuarioAlianzaNacionalService trabfinal_usuario_nacional;
	
	String participanteid, usuario;
	Integer tipousuarioid,anioActual;
	
	int contador;
	String id_rubrica,id_questionario,ods,id_pregunta_respuesta,id_pr;
	String id_rubrica_edit,id_questionario_edit,ods_edit,id_questionario_pregunta_respuesta_edit,id_pr_edit;
	Integer odsid;
	String indices;
	String nota;
	boolean banderaBuscarPorOdsAnioactual;
	boolean banderaDepa;
	boolean idDepartamentoDiferente;
	Calendar fecha;
	
	@GetMapping("/inicio")
	public String inicio(@RequestParam(name="name",required=false,defaultValue="world") String name, Model model) {
		
		model.addAttribute("name",name);
		model.addAttribute("lengua",lenguaserv.findAll());
		model.addAttribute("tipoie",tipoieserv.findList());
		model.addAttribute("departamento",depaServ.listar());
		model.addAttribute("proveedor",proveedorserv.findAll());
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("generodir", generodirserv.listar());
		model.addAttribute("generoprof", generoprofserv.listar());
		return "inicio";
	}
	
	
	@GetMapping("/actualizarcontrasenia/us/{id}")
	public String actualizarcontraseniaus(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("tipo", "us");
		model.addAttribute("id", id);
		return "actualizarcontrasenia";
	}
	
	
	@GetMapping("/detalleverevaluacion/{id}")
	public String detalleverevaluacion(@PathVariable("id") Integer id, Model model) {
		
		nota = "";
		List<DetalleEvaluacionDto> lista = new ArrayList<DetalleEvaluacionDto>();
		
		trabfin_usuarioal.listarByTrabajosfinalesId(id).forEach(obj->{
			DetalleEvaluacionDto dto = new DetalleEvaluacionDto();
			dto.setNombre(obj.getUsuarioalianza().getNombresautoridad());
			dto.setAppaterno(obj.getUsuarioalianza().getApepatautoridad());
			dto.setApmaterno(obj.getUsuarioalianza().getApematautoridad());
			dto.setNota((obj.getNota()==null || obj.getNota()==-1.0)  ? "" : obj.getNota().toString());
			lista.add(dto);
		});
		model.addAttribute("total", trabajosfinalesService.ListarporId(id).getNota()!=null? trabajosfinalesService.ListarporId(id).getNota().toString():"");
		model.addAttribute("lista", lista);
		return "modal_detalleevaluacion";
	}
	
	@GetMapping("/detalleverevaluacionnacional/{id}")
	public String detalleverevaluacionnacional(@PathVariable("id") Integer id, Model model) {
		
		nota = "";
		List<DetalleEvaluacionDto> lista = new ArrayList<DetalleEvaluacionDto>();
		
		trabfinal_usuario_nacional.listarByTrabajosfinalesId(id).forEach(obj->{
			DetalleEvaluacionDto dto = new DetalleEvaluacionDto();
			dto.setNombre(obj.getUsuarioalianza().getNombresautoridad());
			dto.setAppaterno(obj.getUsuarioalianza().getApepatautoridad());
			dto.setApmaterno(obj.getUsuarioalianza().getApematautoridad());
			dto.setNota((obj.getNota()==null || obj.getNota()==-1.0)  ? "" : obj.getNota().toString());
			lista.add(dto);		
			nota = obj.getTrabajosfinales().getNota_nacional() == null  ? "" : obj.getTrabajosfinales().getNota_nacional().toString();
		});
		model.addAttribute("total", nota);
		model.addAttribute("lista", lista);
		return "modal_detalleevaluacion";
	}
	
	@GetMapping("/formeditartrabajos/{id}")
	public String formeditartrabajos(@PathVariable("id") Integer id, Model model) {
		
		List<Modalidadtrabajo> listaModalidad = new ArrayList<>();
		
		Trabajosfinales tf  = trabajosfinalesService.ListarporId(id);
		model.addAttribute("tf", tf);
		model.addAttribute("hd_categoria", (tf.getCategoriatrabajo().getId()== 1 || tf.getCategoriatrabajo().getId() == 3) ? false : true);
		model.addAttribute("categoriatrabajo",categoriatrabajoService.listar());
		
		if(tf.getCategoriatrabajo().getId()==1) {
			listaModalidad.add(modalidadtrabajoService.ListarporId(1));
			listaModalidad.add(modalidadtrabajoService.ListarporId(2));
		}	
		else {
			listaModalidad.add(modalidadtrabajoService.ListarporId(1));
		}
		model.addAttribute("modalidadtrabajo",listaModalidad);
		
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());		
		
		model.addAttribute("chconversacion", tf.getConversacion()==1 ? true : false);
		model.addAttribute("chvaloracionagua", tf.getValoracionagua()==1 ? true : false);
		model.addAttribute("chvaloracionalcantarillado", tf.getValoracionalcantarillado() == 1 ? true : false);
		model.addAttribute("chbuenuso", tf.getBuenuso()==1 ? true : false);
		model.addAttribute("chimportancia", tf.getImportancia()==1 ? true : false);
		model.addAttribute("chvinculo", tf.getVinculo()==1 ? true : false);
		model.addAttribute("chcarencias", tf.getCarencias()==1 ? true : false);
		model.addAttribute("chrevaloracion", tf.getRevaloracion()==1 ? true : false);
		
		participanteid = "";
		trabajosfinalesparticipanteService.listar(id).forEach(obj->{
			participanteid += (obj.getParticipante().getId()).toString() + ",";
		});
		
		model.addAttribute("participanteid", participanteid);
		
		model.addAttribute("nombretrabajos",uploadfile.buscarArchivo(id, "upload_trabajos"));
		model.addAttribute("nombreevidencias",uploadfile.buscarEvidencias(id, "upload_evidencias"));
		
		contador = 1;
		uploadfile.buscarEvidencias(id, "upload_evidencias").forEach(obj->{
			model.addAttribute("evidencia" + contador,obj);
			contador ++;
		});
		System.out.println("contador : " + contador);
		
		model.addAttribute("nroevidencias",uploadfile.buscarEvidencias(id, "upload_evidencias").size());
		return "formeditartrabajo";
	}	
	
	@GetMapping("/formvertrabajos/{id}")
	public String formvertrabajos(@PathVariable("id") Integer id, Model model) {
		
		Trabajosfinales tf  = trabajosfinalesService.ListarporId(id);
		model.addAttribute("tf", tf);
		model.addAttribute("categoriatrabajo",categoriatrabajoService.listar());
		model.addAttribute("modalidadtrabajo",modalidadtrabajoService.listar());
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());
		
		
		model.addAttribute("chconversacion", tf.getConversacion()==1 ? true : false);
		model.addAttribute("chvaloracionagua", tf.getValoracionagua()==1 ? true : false);
		model.addAttribute("chvaloracionalcantarillado", tf.getValoracionalcantarillado() == 1 ? true : false);
		model.addAttribute("chbuenuso", tf.getBuenuso()==1 ? true : false);
		model.addAttribute("chimportancia", tf.getImportancia()==1 ? true : false);
		model.addAttribute("chvinculo", tf.getVinculo()==1 ? true : false);
		model.addAttribute("chcarencias", tf.getCarencias()==1 ? true : false);
		model.addAttribute("chrevaloracion", tf.getRevaloracion()!=null?(tf.getRevaloracion()==1 ? true:false):false);
		
		participanteid = "";
		trabajosfinalesparticipanteService.listar(id).forEach(obj->{
			participanteid += (obj.getParticipante().getId()).toString() + ",";
		});
		
		model.addAttribute("participanteid", participanteid);
		
		model.addAttribute("nombretrabajos",uploadfile.buscarArchivo(id, "upload_trabajos"));
		model.addAttribute("nombreevidencias",uploadfile.buscarEvidencias(id, "upload_evidencias"));
		
		contador = 1;
		uploadfile.buscarEvidencias(id, "upload_evidencias").forEach(obj->{
			model.addAttribute("evidencia" + contador,obj);
			contador ++;
		});
		
		model.addAttribute("nroevidencias",uploadfile.buscarEvidencias(id, "upload_evidencias").size());
		return "formvertrabajoconsulta";
	}
	
	@GetMapping("/actualizarcontrasenia/uo/{id}")
	public String actualizarcontraseniauo(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("tipo", "uo");
		model.addAttribute("id", id);
		return "actualizarcontrasenia";
	}
	
	
	@GetMapping(value= "/editarviewaperturaanio/{id}")
	public String editarviewaperturaanio(@PathVariable("id") Integer id, Model model){
		
		Aperturaranio ap = aperturaranioService.ListarporId(id);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		model.addAttribute("id", ap.getId());
		model.addAttribute("anio",ap.getAnio());
		model.addAttribute("nombreconcurso", ap.getNombreconcurso());
		model.addAttribute("primeraetapadesde", ap.getPrimeraetapadesde().format(formatter).toString());
		model.addAttribute("primeraetapahasta", ap.getPrimeraetapahasta().format(formatter).toString());
		model.addAttribute("segundaetapadesde", ap.getSegundaetapadesde().format(formatter).toString());
		model.addAttribute("segundaetapahasta", ap.getSegundaetapahasta().format(formatter).toString());
		model.addAttribute("terceraetapadesde", ap.getTerceraetapadesde().format(formatter).toString());
		model.addAttribute("terceraetapahasta", ap.getTerceraetapahasta().format(formatter).toString());
		model.addAttribute("cuartaetapadesde", ap.getCuartaetapadesde().format(formatter).toString());
		model.addAttribute("cuartaetapahasta", ap.getCuartaetapahasta().format(formatter).toString());
		model.addAttribute("quintaetapadesde", ap.getQuintaetapadesde().format(formatter).toString());
		model.addAttribute("quintaetapahasta", ap.getQuintaetapahasta().format(formatter).toString());
		model.addAttribute("sextaetapadesde", ap.getSextaetapadesde().format(formatter).toString());
		model.addAttribute("sextaetapahasta", ap.getSextaetapahasta().format(formatter).toString());
		model.addAttribute("septimaetapadesde", ap.getseptimaetapadesde().format(formatter).toString());
		model.addAttribute("septimaetapahasta", ap.getSeptimaetapahasta().format(formatter).toString());		
		return "editarviewaperturaanio";
	}
	
	
	@GetMapping("/recuperarcontrasenia")
	public String recuperarcontrasenia() {
		
		return "recuperarcontrasenia";
	}
	
	
	@GetMapping("/nuevoregistroapertura")
	public String nuevoregistroapertura(Model model) {
		
		List<Integer> listaAnio = new ArrayList<Integer>();
		Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        for(int i = anio;i<anio+5;i++) {
        	listaAnio.add(i);
        }	
        model.addAttribute("anios",listaAnio);
		return "nuevoregistroapertura";
	}
	
	
	@GetMapping("/inicioficha/{id}")
	public String inicio(@PathVariable Integer id, Model model) {		
		
		Programaeducativo obj = progeducService.ListarporId(id);
		model.addAttribute("programaeducativo",obj);
		
		
		model.addAttribute("lengua",lenguaserv.findAll());
		model.addAttribute("tipoie",tipoieserv.findList());
		model.addAttribute("departamento",depaServ.listar());
		model.addAttribute("proveedor",proveedorserv.findAll());
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("generodir", generodirserv.listar());
		model.addAttribute("generoprof", generoprofserv.listar());
		
		model.addAttribute("provincia",provServ.listProvinciaByDepa(obj.getDep()));
		model.addAttribute("distrito",distServ.listByProvincia(obj.getProv()) );
		
		List<ProgramaeducativoTurno> progeduc= progeducturnoService.listProgeducTurno(id);		
		boolean manana=false,tarde=false,noche=false;
		for(int i=0;i<progeduc.size();i++) {
			switch(progeduc.get(i).getTurno().getId()) {
				case 1: manana = true; break;
				case 2: tarde = true ; break;
				case 3: noche = true ; break;
			}
		}
		
		model.addAttribute("manana",manana);
		model.addAttribute("tarde",tarde);
		model.addAttribute("noche",noche);
		
		List<ProgramaeducativoNivel> listpe= progeducnivelService.listProgeducNivel(id);
		boolean tninicial=false , tnprimaria=false, tnsecundaria=false;
		int idi=0,nisec=0,nidoc=0,nial=0,niva=0,nimuj=0;
		int idp=0,npsec=0,npdoc=0,npal=0,npva=0,npmuj=0;
		int ids=0,nssec=0,nsdoc=0,nsal=0,nsva=0,nsmuj=0;
		
		for(int j=0;j<listpe.size();j++) {
			switch(listpe.get(j).getNivel().getTiponivel().getId()) {
				case 1: 
					tninicial=true;
					idi = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getId()!=null?listpe.get(j).getNivel().getId():0):0;
					nisec=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrosecciones()!=null?listpe.get(j).getNivel().getNrosecciones():0):0;
					nidoc=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrodocentes()!=null?listpe.get(j).getNivel().getNrodocentes():0):0;
					nial=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNroalumnos()!=null?listpe.get(j).getNivel().getNroalumnos():0):0;
					niva=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrovarones()!=null?listpe.get(j).getNivel().getNrovarones():0):0;
					nimuj=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNromujeres()!=null?listpe.get(j).getNivel().getNromujeres():0):0;
				break;
				case 2: 
					tnprimaria=true;
					idp = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getId()!=null?listpe.get(j).getNivel().getId():0):0;
					npsec=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrosecciones()!=null?listpe.get(j).getNivel().getNrosecciones():0):0;
					npdoc=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrodocentes()!=null?listpe.get(j).getNivel().getNrodocentes():0):0;
					npal=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNroalumnos()!=null?listpe.get(j).getNivel().getNroalumnos():0):0;
					npva=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrovarones()!=null?listpe.get(j).getNivel().getNrovarones():0):0;
					npmuj=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNromujeres()!=null?listpe.get(j).getNivel().getNromujeres():0):0;
				break;
				case 3: 
					tnsecundaria=true;
					ids = listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getId()!=null?listpe.get(j).getNivel().getId():0):0;
					nssec=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrosecciones()!=null?listpe.get(j).getNivel().getNrosecciones():0):0;
					nsdoc=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrodocentes()!=null?listpe.get(j).getNivel().getNrodocentes():0):0;
					nsal=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNroalumnos()!=null?listpe.get(j).getNivel().getNroalumnos():0):0;
					nsva=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNrovarones()!=null?listpe.get(j).getNivel().getNrovarones():0):0;
					nsmuj=listpe.get(j).getNivel()!=null?(listpe.get(j).getNivel().getNromujeres()!=null?listpe.get(j).getNivel().getNromujeres():0):0;
				break;
			}
		}
		
		model.addAttribute("tninicial",tninicial);
		model.addAttribute("idi",idi);
		model.addAttribute("nisec",nisec);
		model.addAttribute("nidoc",nidoc);
		model.addAttribute("nial",nial);
		model.addAttribute("niva",niva);
		model.addAttribute("nimuj",nimuj);
		model.addAttribute("tnprimaria",tnprimaria);
		model.addAttribute("idp",idp);
		model.addAttribute("npsec",npsec);
		model.addAttribute("npdoc",npdoc);
		model.addAttribute("npal",npal);
		model.addAttribute("npva",npva);
		model.addAttribute("npmuj",npmuj);
		model.addAttribute("tnsecundaria",tnsecundaria);
		model.addAttribute("ids",ids);
		model.addAttribute("nssec",nssec);
		model.addAttribute("nsdoc",nsdoc);
		model.addAttribute("nsal",nsal);
		model.addAttribute("nsva",nsva);
		model.addAttribute("nsmuj",nsmuj);
		
		List<Suministro> listsum = obj.getSuministro();
		model.addAttribute("listasuministro",listsum);
		return "inicioficha";
	}
	
	@GetMapping("/")
	public String login(@RequestParam(name="name",required=false,defaultValue="") String name, Model model) {
		
		return "login";
	}
	
	@GetMapping("/contenidoconsulta")
	public String contenido_consulta(Model model,HttpSession ses) {
		
		/*if(ses.getAttribute("usuario")==null) {
			ses.removeAttribute("usuario");
			ses.removeAttribute("perfil");
			ses.removeAttribute("flag");
			return "session_cerrada";
		}*/
		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());		
		if(tipousuarioid.equals(2)) {
			usuario = ses.getAttribute("usuario").toString();			
			model.addAttribute("departamento",usuarioodsService.listarDepartamentoByUsuario(usuario));
		}else {
			model.addAttribute("departamento",depaServ.listar());
		}
		fecha = Calendar.getInstance();
		model.addAttribute("anioActual",fecha.get(Calendar.YEAR));
		return "contenido_consulta";
	}	
	
	@GetMapping("/programaconsulta")
	public String programaconsulta(Model model,HttpSession ses) {
		
		List<Ods> listaOds = new ArrayList<>();		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		if(tipousuarioid.equals(2)) {
			usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
			model.addAttribute("ods", listaOds);
		}
		else {
			model.addAttribute("ods",odsserv.listarAll());
		}
		model.addAttribute("departamento",depaServ.listar());
		fecha = Calendar.getInstance();
        List<Integer> lista = new ArrayList<Integer>();
        anioActual = fecha.get(Calendar.YEAR);
        for(int i = anioActual-4;i<=anioActual;i++) {
                lista.add(i);
        }
        model.addAttribute("anioActual", anioActual);
        model.addAttribute("anios",lista);
		return "programaconsulta";
	}
	
	@GetMapping("/docenteconsulta")
	public String docenteconsulta(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses) {
		
		List<Ods> listaOds = new ArrayList<>();		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		if(tipousuarioid.equals(2)) {
			usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
			model.addAttribute("ods", listaOds);
		}
		else {
			model.addAttribute("ods",odsserv.listarAll());
		}
		Calendar fecha = Calendar.getInstance();
		List<Integer> lista = new ArrayList<Integer>();
		int anio = fecha.get(Calendar.YEAR);
		for(int i = anio-5;i<=anio;i++) {
			lista.add(i);
		}
		model.addAttribute("anios", lista);
		return "docenteconsulta";
	}
	
	@GetMapping("/listaformconcurso")
	public String listaconcurso(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses) {		
		
		List<Ods> listaOds = new ArrayList<>();		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		if(tipousuarioid.equals(2)) {
			usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
			model.addAttribute("ods", listaOds);
		}
		else {
			model.addAttribute("ods",odsserv.listarAll());
		}
		
		Calendar fecha = Calendar.getInstance();
        List<Integer> lista = new ArrayList<Integer>();
        int anio = fecha.get(Calendar.YEAR);
        for(int i = anio-5;i<=anio;i++) {
                lista.add(i);
        }
        
        Aperturaranio apertura = aperturaranioService.buscar(anio);
		LocalDate dateActual = LocalDate.now();
		if(apertura.getQuintaetapadesde().isBefore(dateActual) || apertura.getQuintaetapadesde().isEqual(dateActual)) {
			model.addAttribute("showFinalizar",1);
		}else {
			model.addAttribute("showFinalizar",0);
		}
        
        
        model.addAttribute("anios", lista);
        model.addAttribute("modalidadtrabajo",modalidadtrabajoService.listar());
        model.addAttribute("listaestadoevaluacion", estadoevaluacionService.listar());
        model.addAttribute("categoriatrabajo",categoriaevaluacionService.listar());//categoriatrabajoService.listar());
        model.addAttribute("nivelparticipacion",nivelparticipacionService.listar());
		return "listaformconcurso";
	}
	
	@GetMapping("/formasignarevaluador")
	public String formasignarevaluador(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses) {
		
		List<Ods> listaOds = new ArrayList<>();		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		if(tipousuarioid.equals(2)) {
			usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
			model.addAttribute("ods", listaOds);
			odsid = usuarioService.byUsuario(ses.getAttribute("usuario").toString()).getOdsid();
			
		}
		else {
			model.addAttribute("ods",odsserv.listarAll());
			odsid = 0;
		}	
		model.addAttribute("odsid",odsid);
		model.addAttribute("nivelparticipacion",nivelparticipacionService.listar());
		model.addAttribute("categoriatrabajo",categoriaevaluacionService.listar());
		Calendar fecha = Calendar.getInstance();
        model.addAttribute("anio", fecha.get(Calendar.YEAR));
        model.addAttribute("colegios", progeducService.listCentrosEducativosGroupbyNomie());
		return "formasignarevaluador";
	}
	
	@GetMapping("/trabajospendientes")
	public String trabajospendientes(@RequestParam(name="name",required=false,defaultValue="") String name, Model model) {
            Calendar fecha = Calendar.getInstance();
            model.addAttribute("anio",fecha.get(Calendar.YEAR));
            List<Integer> lista = new ArrayList<Integer>();
            int anio = fecha.get(Calendar.YEAR);
            for(int i = anio-5;i<=anio;i++) {
                  lista.add(i);
            }		
            model.addAttribute("ods",odsserv.listarAll());
            model.addAttribute("anios", lista);
            model.addAttribute("departamento",depaServ.listar());
            model.addAttribute("categoriatrabajo",categoriatrabajoService.listar());//categoriatrabajoService.listar());
            model.addAttribute("modalidadtrabajo",modalidadtrabajoService.listar());
            model.addAttribute("nivelparticipacion",nivelparticipacionService.listar());
            return "trabajospendientes";
	}
    
    @GetMapping("/trabajosevaluados")
	public String trabajosevaluados(@RequestParam(name="name",required=false,defaultValue="") String name, Model model) {
            Calendar fecha = Calendar.getInstance();
            model.addAttribute("anio",fecha.get(Calendar.YEAR));
            List<Integer> lista = new ArrayList<Integer>();
            int anio = fecha.get(Calendar.YEAR);
            for(int i = anio-5;i<=anio;i++) {
                    lista.add(i);
            }
		
            model.addAttribute("ods",odsserv.listarAll());
            model.addAttribute("anios", lista);
            model.addAttribute("departamento",depaServ.listar());
            model.addAttribute("categoriatrabajo",categoriatrabajoService.listar());//categoriatrabajoService.listar());
            model.addAttribute("modalidadtrabajo",modalidadtrabajoService.listar());
            model.addAttribute("nivelparticipacion",nivelparticipacionService.listar());
            return "trabajosevaluados";
	}
	
	@GetMapping("/listaformrevaluaciones")
	public String rubricaevaluacionconsulta(@RequestParam(name="name",required=false,defaultValue="") String name, Model model) {
		Calendar fecha = Calendar.getInstance();
		model.addAttribute("anio",fecha.get(Calendar.YEAR));
		List<Integer> lista = new ArrayList<Integer>();
		int anio = fecha.get(Calendar.YEAR);
		for(int i = anio-5;i<=anio;i++) {
			lista.add(i);
		}
		model.addAttribute("listanivelparticipacion",nivelparticipacionService.listar());
		model.addAttribute("anios", lista);
		model.addAttribute("listacategoriaevaluacion", categoriaevaluacionService.listar());
		model.addAttribute("listaestadoevaluacion", estadoevaluacionService.listar());
		return "listaevaluaciones";
	}
	
	
	@GetMapping("/formcrearevaluacion")
	public String formcrearevaluacion(Model model) {
		Calendar fecha = Calendar.getInstance();
		model.addAttribute("listanivelparticipacion",nivelparticipacionService.listar());
		model.addAttribute("anio",fecha.get(Calendar.YEAR));
		model.addAttribute("listacategoriaevaluacion", categoriaevaluacionService.listar());
		return "formcrearevaluacion";
	}
	
	@GetMapping("/reportes")
	public String reportes(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses) {
		
		List<Ods> listaOds = new ArrayList<>();		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		if(tipousuarioid.equals(2)) {
			usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
			model.addAttribute("ods", listaOds);
		}
		else {
			model.addAttribute("ods",odsserv.listarAll());
		}		
		List<Integer> lista = new ArrayList<Integer>();
		int anio = fecha.get(Calendar.YEAR);
		for(int i = anio-5;i<=anio;i++) {
			lista.add(i);
		}
		model.addAttribute("anios", lista);
		return "reportes";
	}
	
	@GetMapping("/formregistrarrubrica")
	public String formregistrarrubrica(Model model) {
		return "formregistrarrubrica";
	}
	
	@GetMapping("/aperturar_anio")
	public String contenido_aperturar_anio(@RequestParam(name="name",required=false,defaultValue="") String name, Model model) {
		Calendar fecha = Calendar.getInstance();
		List<Integer> lista = new ArrayList<Integer>();
		int anio = fecha.get(Calendar.YEAR);
		for(int i = anio;i<=anio+4;i++) {
			System.out.println("año : " + i);
			lista.add(i);
		}
		model.addAttribute("anios", lista);
		return "contenido_aperturar_anio";
	}
	
	@GetMapping("/consulta_usuarios")
	public String consulta_usuarios(Model model) {
		Calendar fecha = Calendar.getInstance();
		model.addAttribute("anio",fecha.get(Calendar.YEAR));
		List<Integer> lista = new ArrayList<Integer>();
		int anio = fecha.get(Calendar.YEAR);
		for(int i = anio-5;i<=anio;i++) {
			lista.add(i);
		}
		model.addAttribute("anios", lista);
		model.addAttribute("ods",odsserv.listarAll());		
		model.addAttribute("tipousuarios", tipousuarioServ.lista());
		
		return "consulta_usuarios";
	}
	
	@GetMapping("/menu")	
    public String menu_slidet(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses){
		if(ses.getAttribute("usuario")!=null) {
			Calendar fecha = Calendar.getInstance();
			if(Integer.parseInt(ses.getAttribute("tipousuarioid").toString()) == 3) { /*el usuario es la IE*/
				model.addAttribute("usuario", ses.getAttribute("usuario"));
				model.addAttribute("perfil", ses.getAttribute("perfil"));
				model.addAttribute("tipousuarioid", ses.getAttribute("tipousuarioid"));
				model.addAttribute("nombre_concurso",aperturaranioService.buscar(fecha.get(Calendar.YEAR)).getNombreconcurso());
				model.addAttribute("cargainicialtabla", 1);
				model.addAttribute("cargainicialtablatrabajos", 1);
				return "menu_concurso";
			}else if(Integer.parseInt(ses.getAttribute("tipousuarioid").toString()) == 5) {
				model.addAttribute("usuario", ses.getAttribute("usuario"));
				model.addAttribute("perfil", ses.getAttribute("perfil"));
				model.addAttribute("tipousuarioid", ses.getAttribute("tipousuarioid"));
				return "menu_evaluadores";
			}
			else {
				model.addAttribute("usuario", ses.getAttribute("usuario"));
				model.addAttribute("perfil", ses.getAttribute("perfil"));
				model.addAttribute("tipousuarioid", ses.getAttribute("tipousuarioid"));
				model.addAttribute("anioActual",fecha.get(Calendar.YEAR));
				return "menu_slidet";
			}
		}
		return "login";
    }	
	
	@GetMapping("/menu_concurso")	
    public String menu_concurso(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses){
		model.addAttribute("usuario", ses.getAttribute("usuario"));
		model.addAttribute("perfil", ses.getAttribute("perfil"));
		model.addAttribute("tipousuarioid", ses.getAttribute("tipousuarioid"));
		Calendar fecha = Calendar.getInstance();
		model.addAttribute("nombre_concurso",aperturaranioService.buscar(fecha.get(Calendar.YEAR)).getNombreconcurso());
		model.addAttribute("cargainicialtabla", 1);
		model.addAttribute("cargainicialtablatrabajos", 1);
		return "opciones_concurso";
    }	
	
	@GetMapping("/fichainscripcion/{id}")
	public String fichainscripcion(@PathVariable("id") Integer id,  Model model,HttpSession ses) {
		
		model.addAttribute("idprogramaeducativo", id);
		model.addAttribute("codmod",progeducService.ListarporId(id).getCodmod());
		model.addAttribute("responsableregistro", responsableregistroserv.listar());
		Docentetutor docentetutor = docentetutorService.ListarporId(id);
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());
		model.addAttribute("nivel",nivelparticipanteService.listar());
		model.addAttribute("parentesco",parentescoService.listar());
		
		int activar_trabajos_finales = 0;		
		Calendar fecha = Calendar.getInstance();
		Date date = Calendar.getInstance().getTime();
		DateFormat formato = new SimpleDateFormat("dd/MM/yy");
        String today = formato.format(date);
        Aperturaranio ap = aperturaranioService.buscar(fecha.get(Calendar.YEAR));
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    	LocalDate fechaactual = LocalDate.parse(today, formatter);
    	//LocalDate fechaactual = LocalDate.parse("20/04/21", formatter);
    	
    	String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
    	
    	Postulacionconcurso postconc = postulacionconcursoService.getByIdAnio(pe.getId(), fecha.get(Calendar.YEAR));	    	
    	model.addAttribute("finalizaparticipaciontrabajo",postconc.getFinalizarparticipaciontrabajo());
    	
        if(fechaactual.compareTo(ap.getCuartaetapadesde())>=0 && fechaactual.compareTo(ap.getCuartaetapahasta())<=0)
        	activar_trabajos_finales = 1;
        model.addAttribute("activar_trabajos_finales",activar_trabajos_finales);
		
		if(docentetutor!=null) {
			model.addAttribute("docentetutor", docentetutor);
			return "fichainscripcion_update";
		}
		return "fichainscripcion";
	}
	
	@GetMapping("/postular_concurso/{id}")
	public String postular_concurso(@PathVariable("id") Integer id,  Model model,HttpSession ses) {
		
		model.addAttribute("id",id);
		Postulacionconcurso pc = postulacionconcursoService.getById(id);
		if( pc!= null) {
			model.addAttribute("codmod",progeducService.ListarporId(id).getCodmod());						
			Docentetutor docentetutor = docentetutorService.getByProgeduc(id);
			model.addAttribute("responsableregistro", responsableregistroserv.listar());
			model.addAttribute("tipodoc",tipodocserv.findAll());
			model.addAttribute("genero",generoprofserv.listar());
			model.addAttribute("nivel",nivelparticipanteService.listar());
			model.addAttribute("parentesco",parentescoService.listar());
			int activar_trabajos_finales = 0;		
			Calendar fecha = Calendar.getInstance();
			Date date = Calendar.getInstance().getTime();
			DateFormat formato = new SimpleDateFormat("dd/MM/yy");
	        String today = formato.format(date);
	        Aperturaranio ap = aperturaranioService.buscar(fecha.get(Calendar.YEAR));
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
	    	LocalDate fechaactual = LocalDate.parse(today, formatter);
	    	//LocalDate fechaactual = LocalDate.parse("20/04/21", formatter);	    	
	    	String codmod = ses.getAttribute("usuario").toString();
			Programaeducativo pe = progeducService.getActualByCodmod(codmod);	    	
	    	Postulacionconcurso postconc = postulacionconcursoService.getByIdAnio(pe.getId(), fecha.get(Calendar.YEAR));	    	
	    	
	    	Integer odsid = pe.getDistrito().getOdsid();
	    	if(cerrarOdsService.buscarPorOdsAnioactual(odsid)!=null) {
	    		banderaBuscarPorOdsAnioactual = true;
	    	}
	    	else {
	    		banderaBuscarPorOdsAnioactual = false;
	    	}		
	    			
	    	//if((postconc.getFinalizarparticipaciontrabajo() == 1) || banderaBuscarPorOdsAnioactual) 
	    	if(banderaBuscarPorOdsAnioactual)
	    		model.addAttribute("finalizaparticipaciontrabajo",1);
	    	else
	    		model.addAttribute("finalizaparticipaciontrabajo",0);	   
	    	
	        if(fechaactual.compareTo(ap.getCuartaetapadesde())>=0 && fechaactual.compareTo(ap.getCuartaetapahasta())<=0)
	        	activar_trabajos_finales = 1;
	        
	        model.addAttribute("activar_trabajos_finales",activar_trabajos_finales);
	        model.addAttribute("cuarta_etapa_desde", ap.getCuartaetapadesde());
	        model.addAttribute("cuarta_etapa_hasta", ap.getCuartaetapahasta());
			if(docentetutor!=null) {
				model.addAttribute("docentetutor", docentetutor);
				return "fichainscripcion_update";
			}
			return "fichainscripcion";
		}
		return "postular_concurso";
	}
	
	@GetMapping("/formregistrarparticipante")
	public String formregistrarparticipante( Model model) {
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());
		model.addAttribute("nivel",nivelparticipanteService.listar());
		model.addAttribute("parentesco",parentescoService.listar());
		return "formregistrarparticipante";
	}
	
	@GetMapping("/formregistrarparticipantedocente")
	public String formregistrarparticipantedocente( Model model) {
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());
		model.addAttribute("nivel",nivelparticipanteService.listar());
		model.addAttribute("parentesco",parentescoService.listar());
		return "formregistrarparticipantedocente";
	}
	
	@GetMapping("/formagregartrabajo")
	public String formagregartrabajo( Model model) {
		model.addAttribute("categoriatrabajo",categoriatrabajoService.listar());
		model.addAttribute("modalidadtrabajo",modalidadtrabajoService.listar());
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());
		return "formagregartrabajo";
	}
	
	@GetMapping("/listarparticipante")
	public String listarparticipante( Model model) {
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());
		model.addAttribute("nivel",nivelparticipanteService.listar());
		model.addAttribute("parentesco",parentescoService.listar());
		return "listarparticipante";
	}
	
	@GetMapping("/editarviewparticipanteid/{id}")
	public String editarviewparticipanteid(@PathVariable("id") Integer id,  Model model,HttpSession ses) {
		
		Participante pa = participanteService.ListarporId(id);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		model.addAttribute("fechanacimiento", pa.getFechanacimientoestudiante().format(formatter).toString());
		model.addAttribute("participante", pa);
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());
		model.addAttribute("nivel",nivelparticipanteService.listar());
		model.addAttribute("grado",gradoparticipanteService.listargradopornivel(pa.getGradoestudiante().getNivelparticipante().getId()));
		model.addAttribute("parentesco",parentescoService.listar());
		model.addAttribute("nombrearchivo",uploadfile.buscarArchivo(pa.getId(),"upload_participantes"));
		return "formeditarparticipante";
	}
	
	@GetMapping("/editarviewparticipantedocenteid/{id}")
	public String editarviewparticipantedocenteid(@PathVariable("id") Integer id,  Model model,HttpSession ses) {
		
		Participante pa = participanteService.ListarporId(id);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		model.addAttribute("fechanacimiento", pa.getFechanacimientoestudiante().format(formatter).toString());
		model.addAttribute("participante", pa);
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());
		model.addAttribute("nivel",nivelparticipanteService.listar());
		model.addAttribute("grado",gradoparticipanteService.listargradopornivel(pa.getGradoestudiante().getNivelparticipante().getId()));
		model.addAttribute("parentesco",parentescoService.listar());
		model.addAttribute("nombrearchivo",uploadfile.buscarArchivo(pa.getId(),"upload_participantes"));
		return "formeditarparticipantedocente";
	}
	
	@GetMapping("/editarviewusuarioid/{miusuario}")
	public String editarviewusuarioid(@PathVariable("miusuario") String miusuario,  Model model,HttpSession ses) throws Exception {
		
		Usuario usuario = usuarioService.byUsuario(miusuario);
		model.addAttribute("usuario", usuario);
		model.addAttribute("id", usuario.getId());
		ods = "";
		usuarioodsService.listarByUsuario(usuario.getId()).forEach(obj->{
			ods += obj.getOds().getId().toString() + ",";
		});		
		model.addAttribute("ods",ods);
		model.addAttribute("listaods",odsserv.listarAll());		
		Ldap mildap = new Ldap();
		List<UsuarioLdap> lista = mildap.listarTodosUsuariosLDAP();
		lista.forEach(obj1->{
			if(obj1.getCuenta().equals(usuario.getUsuario())) {
				model.addAttribute("datosusuario",obj1);
			}
		});
		model.addAttribute("usuarios", lista);
		model.addAttribute("tipousuarios", tipousuarioServ.lista());
		return "formeditarusuario";
	}
	
	@GetMapping("/editarviewevaluacionid/{id}")
	public String editarviewevaluacionid(@PathVariable("id") Integer id,  Model model,HttpSession ses) {
		
		id_rubrica_edit = "";
		id_questionario_edit ="";
		id_pr_edit="";
		id_questionario_pregunta_respuesta_edit="";
		
		Integer mayor=0;
		indices ="";
		
		Integer mayor_rubrica=0;		
		List<Integer> idRubrica_max = new ArrayList<Integer>();
		Evaluacion eval = evaluacionService.ListarporId(id);
		model.addAttribute("evaluacion_edit", eval);		
		List<Rubrica> listaRubrica = new ArrayList<Rubrica>();
        evaluacionrubricaServ.listarPorEvaluacionId(eval.getId()).forEach(obj->{
        	if(obj.getRubrica().getEstado() == 1) {
        		indices += obj.getRubrica().getId().toString() + "-";
            	idRubrica_max.add(obj.getRubrica().getId());
            	listaRubrica.add(obj.getRubrica());
            	id_rubrica_edit += obj.getRubrica().getId().toString() + "-";
        	}        	
        });
        
        for(int i=0;i<idRubrica_max.size();i++) {
        	if(idRubrica_max.get(i) > mayor_rubrica) {
        		mayor_rubrica = idRubrica_max.get(i);
        	}
        }
        
        Integer mayor_cuestionario=0;
        List<Integer> idQuestionario_max = new ArrayList<Integer>();
        List<Questionario> listaQuestionario = new ArrayList<Questionario>();
        evaluacionquestionarioServ.listarPorEvaluacionId(eval.getId()).forEach(obj->{
        	
        	if(obj.getQuestionario().getEstado() == 1) {
        		indices += obj.getQuestionario().getId().toString() + "/";
            	idQuestionario_max.add(obj.getQuestionario().getId());
            	listaQuestionario.add(obj.getQuestionario());
            	id_pr = "";
            	obj.getQuestionario().getQuestionariorespuesta().forEach(pr->{
            		id_pr += pr.getId() + "-";
            		indices += pr.getId() + "*";
            	});
            	indices = indices.substring(0,indices.length()-1);
            	indices += "-";
            	if(id_pr!="")
            		id_questionario_pregunta_respuesta_edit += obj.getQuestionario().getId().toString() + "*" + (id_pr.substring(0,id_pr.length()-1)) + "/";
        	}
        });
        
        if(indices.length()>0)
        	indices= indices.substring(0,indices.length()-1);
        else
        	indices = "";
        
        for(int i=0;i<idQuestionario_max.size();i++) {
        	if(idQuestionario_max.get(i) > mayor_cuestionario) {
        		mayor_cuestionario = idQuestionario_max.get(i);
        	}
        }
        
        if(mayor_rubrica>mayor_cuestionario) {
        	mayor = mayor_rubrica;
        }
        else {
        	mayor = mayor_cuestionario;
        }
        model.addAttribute("indices", indices); 
        model.addAttribute("id_evaluacion_edit", eval.getId()); 
        model.addAttribute("id_rubrica_edit", id_rubrica_edit);
        model.addAttribute("id_questionario_edit", id_questionario);
        model.addAttribute("id_questionario_pregunta_respuesta_edit", id_questionario_pregunta_respuesta_edit);
        
        model.addAttribute("max", mayor);
        
        Calendar fecha = Calendar.getInstance();
		model.addAttribute("anio",fecha.get(Calendar.YEAR));		
        model.addAttribute("listaquestionario_edit", listaQuestionario);
		model.addAttribute("listarubrica_edit", listaRubrica);
        model.addAttribute("listanivelparticipacion_edit",nivelparticipacionService.listar());
		model.addAttribute("listacategoriaevaluacion_edit", categoriaevaluacionService.listar());
		return "formeditarevaluacion";
	}
	
	@GetMapping("/verviewevaluacionid/{id}")
	public String verviewevaluacionid(@PathVariable("id") Integer id,  Model model,HttpSession ses) {
		
		id_rubrica = "";
		id_questionario ="";
		id_pr="";
		id_pregunta_respuesta="";
		
		Evaluacion eval = evaluacionService.ListarporId(id);
		model.addAttribute("evaluacion", eval);
		
		List<Rubrica> listaRubrica = new ArrayList<Rubrica>();
        evaluacionrubricaServ.listarPorEvaluacionId(eval.getId()).forEach(obj->{
        	if(obj.getRubrica().getEstado()==1) {
        		listaRubrica.add(obj.getRubrica());
	        	id_rubrica += obj.getRubrica().getId().toString() + "-";
        	}
        });
        
        List<Questionario> listaQuestionario = new ArrayList<Questionario>();
        evaluacionquestionarioServ.listarPorEvaluacionId(eval.getId()).forEach(obj->{
        	if(obj.getQuestionario().getEstado()==1) {
        		listaQuestionario.add(obj.getQuestionario());
            	id_pr = "";
            	obj.getQuestionario().getQuestionariorespuesta().forEach(pr->{
            		id_pr += pr.getId() + "-";
            	});
            	if(id_pr!="") {
            		id_pregunta_respuesta += "(" + obj.getQuestionario().getId().toString() + "*" + (id_pr.substring(0,id_pr.length()-1)) + ")";
            	}
        	}  	
        });
       
        model.addAttribute("id_evaluacion", eval.getId()); 
        model.addAttribute("id_rubrica", id_rubrica);
        model.addAttribute("id_questionario", id_questionario);
        model.addAttribute("id_pregunta_respuesta", id_pregunta_respuesta);
        
        Calendar fecha = Calendar.getInstance();
		model.addAttribute("anio",fecha.get(Calendar.YEAR));		
        model.addAttribute("listaquestionario", listaQuestionario);
		model.addAttribute("listarubrica", listaRubrica);
        model.addAttribute("listanivelparticipacion",nivelparticipacionService.listar());
		model.addAttribute("listacategoriaevaluacion", categoriaevaluacionService.listar());
		return "formverevaluacion";
	}
	
	@GetMapping("/editarviewdocenteid/{id}")
	public String editarviewdocenteid(@PathVariable("id") Integer id,  Model model,HttpSession ses) {
		
		Docente docente = docenteService.ListarporId(id);
		model.addAttribute("docente", docente);
		model.addAttribute("tipodoc",tipodocumentoserv.listar());
		model.addAttribute("genero",generoprofserv.listar());
		return "formeditardocente";
	}
	
	@GetMapping("/consulta_concurso")
	public String consulta_concurso() {		
		return "consulta_concurso";
	}
	
	@GetMapping("/consulta_concursoeducativo")
	public String consulta_concursoeducativo(HttpSession ses,Model model) {	
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getPenultimoAnioByCodmod(codmod);
		
		if(pe==null)
			return "ConsultaSinDataIe";
		
		model.addAttribute("idprogramaeducativo", pe.getId());
		model.addAttribute("codmod",pe.getCodmod());
		model.addAttribute("responsableregistro", responsableregistroserv.listar());
		
		Calendar fecha = Calendar.getInstance();
		Docentetutor docentetutor = docentetutorService.getByProgeducByAnio(pe.getId(),fecha.get(Calendar.YEAR)-1);
		if(docentetutor!=null) {
			model.addAttribute("docentetutor_id", docentetutor.getId());
			model.addAttribute("docentetutor_anio", docentetutor.getAnio());
			model.addAttribute("docentetutor_appaterno", docentetutor.getAppaterno());
			model.addAttribute("docentetutor_apmaterno", docentetutor.getApmaterno());
			model.addAttribute("docentetutor_nombre", docentetutor.getNombre());
			model.addAttribute("docentetutor_tipodocumento", docentetutor.getTipodocumento().getDescripcion());
			model.addAttribute("docentetutor_nrodocumento", docentetutor.getNrodocumento());
			model.addAttribute("docentetutor_genero", docentetutor.getGenero().getDescripcion());
			model.addAttribute("docentetutor_nrotelefono", docentetutor.getNrotelefono());
			model.addAttribute("docentetutor_correoelectronico", docentetutor.getCorreoelectronico());
			model.addAttribute("docentetutor_responsable", docentetutor.getResponsable());
			model.addAttribute("docentetutor_curso", docentetutor.getCurso());
		}
		else {
			model.addAttribute("docentetutor_id", "");
			model.addAttribute("docentetutor_anio", "");
			model.addAttribute("docentetutor_appaterno", "");
			model.addAttribute("docentetutor_apmaterno", "");
			model.addAttribute("docentetutor_nombre", "");
			model.addAttribute("docentetutor_tipodocumento", "");
			model.addAttribute("docentetutor_nrodocumento", "");
			model.addAttribute("docentetutor_genero", "");
			model.addAttribute("docentetutor_nrotelefono", "");
			model.addAttribute("docentetutor_correoelectronico", "");
			model.addAttribute("docentetutor_responsable", "");
			model.addAttribute("docentetutor_curso", "");
		}
		model.addAttribute("docentetutor", docentetutor);
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());
		model.addAttribute("nivel",nivelparticipanteService.listar());
		model.addAttribute("parentesco",parentescoService.listar());
		
		model.addAttribute("anios", postulacionconcursoService.aniosConcurso(codmod));
    	/*List<Integer> anios = new ArrayList<Integer>();
    	anios.add(2019);
    	anios.add(2020);
    	anios.add(2021);
    	model.addAttribute("anios", anios);	*/
    	model.addAttribute("ultimo_anio", fecha.get(Calendar.YEAR)-1);
		return "consulta_concursoeducativo";
	}	
	
	@GetMapping("/formragregardocente")
	public String formragregarparticipante(Model model) {
		model.addAttribute("tipodoc",tipodocumentoserv.listar());
		model.addAttribute("genero",generoprofserv.listar());
		return "formregistrardocente";
	}
	
	@GetMapping("/formregistrarevaluacion/{id}")
	public String formregistrarevaluacion(@PathVariable("id") Integer id,  Model model,HttpSession ses) {
		id_rubrica = "";
		id_questionario ="";
		
		Trabajosfinales trabajosfinales = trabajosfinalesService.ListarporId(id);
		List<TrabajosfinalesParticipante> listaTrabajosParticipante = trabajosfinalesparticipanteService.listar(trabajosfinales.getId());
		Participante participante = participanteService.ListarporId(listaTrabajosParticipante.get(0).getParticipante().getId());

		Evaluacion eval = evaluacionService.getPorAnioCategoriaNivelparticipacion(trabajosfinales.getAnio(), 
				trabajosfinales.getCategoriatrabajo().getId(), participante.getGradoestudiante().getNivelparticipacion().getId());
		model.addAttribute("evaluacion", eval);
		
		List<Rubrica> listaRubrica = new ArrayList<Rubrica>();
        evaluacionrubricaServ.listarPorEvaluacionId(eval.getId()).forEach(obj->{
        	if(obj.getRubrica().getEstado().equals(1)) {
        		listaRubrica.add(obj.getRubrica());
            	id_rubrica += obj.getRubrica().getId().toString() + "-";
        	}
        });
        
        List<Questionario> listaQuestionario = new ArrayList<Questionario>();
        evaluacionquestionarioServ.listarPorEvaluacionId(eval.getId()).forEach(obj->{
        	if(obj.getQuestionario().getEstado().equals(1)) {
        		listaQuestionario.add(obj.getQuestionario());
            	id_questionario += obj.getQuestionario().getId().toString() + "-";
        	}
        });    
        
        model.addAttribute("id_rubrica", id_rubrica);
        model.addAttribute("id_questionario", id_questionario);
        model.addAttribute("id_evaluacion", eval.getId());
        model.addAttribute("id_trabajofinal", trabajosfinales.getId());
        
        List<Integer> listanro = new ArrayList<Integer>();
        listanro.add(1);
        listanro.add(2);
        listanro.add(3);
        listanro.add(4);
        listanro.add(5);
        model.addAttribute("listanro", listanro);
        
        
        Calendar fecha = Calendar.getInstance();
		model.addAttribute("anio",fecha.get(Calendar.YEAR));
		List<Integer> lista = new ArrayList<Integer>();
		int anio = fecha.get(Calendar.YEAR);
		for(int i = anio-5;i<=anio;i++) {
			lista.add(i);
		}

		/*List<TrabajosfinalesParticipante> listaTrabajosParticipante = trabajosfinalesparticipanteService.listar(trabajosfinales.getId());
		Participante participante = participanteService.ListarporId(listaTrabajosParticipante.get(0).getParticipante().getId());*/
		
		model.addAttribute("nombretrabajo", trabajosfinales.getTitulotrabajo());
		model.addAttribute("categoria", trabajosfinales.getCategoriatrabajo().getDescripcion());
		model.addAttribute("nivel", participante.getGradoestudiante().getNivelgradopartdesc());
		model.addAttribute("modalidad", trabajosfinales.getModalidadtrabajo().getDescripcion());
		
        model.addAttribute("listarubrica", listaRubrica);
        model.addAttribute("listaquestionario", listaQuestionario);
        
        model.addAttribute("listanivelparticipacion",nivelparticipacionService.listar());
		model.addAttribute("anios", lista);
		model.addAttribute("listacategoriaevaluacion", categoriaevaluacionService.listar());
		return "formregistrarevaluacion";
	}
	
	@GetMapping("/formmostrarevaluacion/{id}")
	public String formmostrarevaluacion(@PathVariable("id") Integer id,  Model model,HttpSession ses) {
		id_rubrica = "";
		id_questionario ="";
		
		Trabajosfinales trabajosfinales = trabajosfinalesService.ListarporId(id);
		List<TrabajosfinalesParticipante> listaTrabajosParticipante = trabajosfinalesparticipanteService.listar(trabajosfinales.getId());
		Participante participante = participanteService.ListarporId(listaTrabajosParticipante.get(0).getParticipante().getId());

		Evaluacion eval = evaluacionService.getPorAnioCategoriaNivelparticipacion(trabajosfinales.getAnio(), 
				trabajosfinales.getCategoriatrabajo().getId(), participante.getGradoestudiante().getNivelparticipacion().getId());
		model.addAttribute("evaluacion", eval);
		
		List<Rubrica> listaRubrica = new ArrayList<Rubrica>();
        evaluacionrubricaServ.listarPorEvaluacionId(eval.getId()).forEach(obj->{
        	if(obj.getRubrica().getEstado()==1) {
        		listaRubrica.add(obj.getRubrica());
            	id_rubrica += obj.getRubrica().getId().toString() + "-";
        	}
        });
        
        List<Questionario> listaQuestionario = new ArrayList<Questionario>();
        evaluacionquestionarioServ.listarPorEvaluacionId(eval.getId()).forEach(obj->{
        	if(obj.getQuestionario().getEstado()==1) {
        		listaQuestionario.add(obj.getQuestionario());
            	id_questionario += obj.getQuestionario().getId().toString() + "-";
        	}
        });    
        
        model.addAttribute("id_rubrica", id_rubrica);
        model.addAttribute("id_questionario", id_questionario);
        model.addAttribute("id_evaluacion", eval.getId());
        model.addAttribute("id_trabajofinal", trabajosfinales.getId());
        
        List<Integer> listanro = new ArrayList<Integer>();
        listanro.add(1);
        listanro.add(2);
        listanro.add(3);
        listanro.add(4);
        listanro.add(5);
        model.addAttribute("listanro", listanro);
        
        
        Calendar fecha = Calendar.getInstance();
		model.addAttribute("anio",fecha.get(Calendar.YEAR));
		List<Integer> lista = new ArrayList<Integer>();
		int anio = fecha.get(Calendar.YEAR);
		for(int i = anio-5;i<=anio;i++) {
			lista.add(i);
		}

		/*List<TrabajosfinalesParticipante> listaTrabajosParticipante = trabajosfinalesparticipanteService.listar(trabajosfinales.getId());
		Participante participante = participanteService.ListarporId(listaTrabajosParticipante.get(0).getParticipante().getId());*/
		
		model.addAttribute("nombretrabajo", trabajosfinales.getTitulotrabajo());
		model.addAttribute("categoria", trabajosfinales.getCategoriatrabajo().getDescripcion());
		model.addAttribute("nivel", participante.getGradoestudiante().getNivelgradopartdesc());
		model.addAttribute("modalidad", trabajosfinales.getModalidadtrabajo().getDescripcion());
		
        model.addAttribute("listarubrica", listaRubrica);
        model.addAttribute("listaquestionario", listaQuestionario);
        
        model.addAttribute("listanivelparticipacion",nivelparticipacionService.listar());
		model.addAttribute("anios", lista);
		model.addAttribute("listacategoriaevaluacion", categoriaevaluacionService.listar());
		return "formMostraEvaluacion";
	}
	
	@GetMapping("/formregistrarusuario")
	public String formregistrarusuario(Model model,HttpSession ses) {
		
		List<Ods> listaOds = new ArrayList<>();		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		if(tipousuarioid.equals(2)) {
			usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
			model.addAttribute("odsregusu", listaOds);
		}
		else {
			model.addAttribute("odsregusu",odsserv.listarAll());
		}		
		//model.addAttribute("odsregusu",odsserv.listarAll());
		model.addAttribute("categoriaregusu",categoriaevaluacionService.listar());
		model.addAttribute("idAlianzaEstrategica","0");
		model.addAttribute("tipodoc",tipodocumentoserv.listar());
		model.addAttribute("tipodocaus",tipoAuspicioServ.listar());
		return "formregistrarusuario";
	}
	
	@GetMapping("/formeditarusuario/{id}")
	public String formeditarusuario(@PathVariable("id") Integer id,Model model,HttpSession ses) {
		model.addAttribute("odsregusu",odsserv.listarAll());
		model.addAttribute("categoriaregusu",categoriaevaluacionService.listar());
		model.addAttribute("idAlianzaEstrategica",id);
		model.addAttribute("tipodoc",tipodocumentoserv.listar());
		model.addAttribute("tipodocaus",tipoAuspicioServ.listar());
		return "formregistrarusuario";
	}
	
	@GetMapping("/formCerrarAsignarEvaluador")
	public String formCerrarAsignarEvaluador(Model model,HttpSession ses) {
		
		if(ses.getAttribute("tipousuarioid").toString().equals("1") || ses.getAttribute("tipousuarioid").toString().equals("11") || ses.getAttribute("tipousuarioid").toString().equals("12") || ses.getAttribute("tipousuarioid").toString().equals("30")) {
			odsid = 0;
		}
		else if(ses.getAttribute("tipousuarioid").toString().equals("2")) {
			
			odsid = usuarioService.byUsuario(ses.getAttribute("usuario").toString()).getOdsid();
			//odsid = 18;
		}		
		
		model.addAttribute("odsid",odsid);
		//model.addAttribute("ods",odsserv.listarOdsEmpatadas());//ods empatadas
		model.addAttribute("ods",odsserv.listarAll());
		model.addAttribute("nivelparticipacion",nivelparticipacionService.listar());
		model.addAttribute("categoriatrabajo",categoriaevaluacionService.listar());
		Calendar fecha = Calendar.getInstance();
        model.addAttribute("anio", fecha.get(Calendar.YEAR));
        model.addAttribute("colegios", progeducService.listCentrosEducativosGroupbyNomie());
		return "formCerrarAsignarEvaluador";
	}
	
	@GetMapping("/formCerrarNacionalAsignarEvaluador")
	public String formCerrarNacionalAsignarEvaluador(Model model,HttpSession ses) {
		
		if(ses.getAttribute("tipousuarioid").toString().equals("1") || ses.getAttribute("tipousuarioid").toString().equals("11") || ses.getAttribute("tipousuarioid").toString().equals("12") || ses.getAttribute("tipousuarioid").toString().equals("30")) {
			odsid = 0;
		}
		else if(ses.getAttribute("tipousuarioid").toString().equals("2")) {
			
			odsid = usuarioService.byUsuario(ses.getAttribute("usuario").toString()).getOdsid();
			//odsid = 18;
		}		
		
		model.addAttribute("odsid",odsid);
		//model.addAttribute("ods",odsserv.listarOdsEmpatadas());//ods empatadas
		model.addAttribute("ods",odsserv.listarAll());
		model.addAttribute("nivelparticipacion",nivelparticipacionService.listar());
		model.addAttribute("categoriatrabajo",categoriaevaluacionService.listar());
		Calendar fecha = Calendar.getInstance();
        model.addAttribute("anio", fecha.get(Calendar.YEAR));
        model.addAttribute("colegios", progeducService.listCentrosEducativosGroupbyNomie());
		return "formCerrarNacionalAsignarEvaluador";
	}
	
	@GetMapping("/formCerrarNacionalAsignarEmpatesEvaluador")
	public String formCerrarNacionalAsignarEmpatesEvaluador(Model model,HttpSession ses) {
		
		if(ses.getAttribute("tipousuarioid").toString().equals("1") || ses.getAttribute("tipousuarioid").toString().equals("11") || ses.getAttribute("tipousuarioid").toString().equals("12") || ses.getAttribute("tipousuarioid").toString().equals("30")) {
			odsid = 0;
		}
		else if(ses.getAttribute("tipousuarioid").toString().equals("2")) {
			
			odsid = usuarioService.byUsuario(ses.getAttribute("usuario").toString()).getOdsid();
			//odsid = 18;
		}		
		
		model.addAttribute("odsid",odsid);
		//model.addAttribute("ods",odsserv.listarOdsEmpatadas());//ods empatadas
		model.addAttribute("ods",odsserv.listarAll());
		model.addAttribute("nivelparticipacion",nivelparticipacionService.listar());
		model.addAttribute("categoriatrabajo",categoriaevaluacionService.listar());
		Calendar fecha = Calendar.getInstance();
        model.addAttribute("anio", fecha.get(Calendar.YEAR));
        model.addAttribute("colegios", progeducService.listCentrosEducativosGroupbyNomie());
		return "formCerrarNacionalAsignarEmpatesEvaluador";
	}
	
	@GetMapping("/consultahistorica")
	public String consultahistorica(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses) {		
		
		List<Ods> listaOds = new ArrayList<>();		
		tipousuarioid = Integer.parseInt(ses.getAttribute("tipousuarioid").toString());
		if(tipousuarioid.equals(2)) {
			usuario = ses.getAttribute("usuario").toString();
			usuarioodsService.listarByUsuario(usuarioService.byUsuario(usuario).getId()).forEach(obj->{
				listaOds.add(obj.getOds());
			});
			model.addAttribute("ods", listaOds);
		}
		else {
			model.addAttribute("ods",odsserv.listarAll());
		}
		
		Calendar fecha = Calendar.getInstance();
        List<Integer> lista = new ArrayList<Integer>();
        int anio = fecha.get(Calendar.YEAR);
        for(int i = anio-5;i<=anio;i++) {
                lista.add(i);
        }
        
        Aperturaranio apertura = aperturaranioService.buscar(anio);
		LocalDate dateActual = LocalDate.now();
		//if(apertura.getQuintaetapadesde().isAfter(dateActual)) {
		if(apertura.getQuintaetapadesde().isBefore(dateActual) || apertura.getQuintaetapadesde().isEqual(dateActual)) {
			model.addAttribute("showFinalizar",1);
		}else {
			model.addAttribute("showFinalizar",0);
		}
        
        
        model.addAttribute("anios", lista);
        model.addAttribute("modalidadtrabajo",modalidadtrabajoService.listar());
        model.addAttribute("listaestadoevaluacion", estadoevaluacionService.listar());
        model.addAttribute("categoriatrabajo",categoriaevaluacionService.listar());//categoriatrabajoService.listar());
        model.addAttribute("nivelparticipacion",nivelparticipacionService.listar());
		return "consulta_historica";
	}
	
}

