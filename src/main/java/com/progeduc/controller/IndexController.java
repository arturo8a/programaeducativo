package com.progeduc.controller;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.progeduc.model.Aperturaranio;
import com.progeduc.model.Docentetutor;
import com.progeduc.model.Postulacionconcurso;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.ProgramaeducativoNivel;
import com.progeduc.model.ProgramaeducativoTurno;
import com.progeduc.model.Suministro;
import com.progeduc.service.IAperturaranioService;
import com.progeduc.service.IDepartamentoService;
import com.progeduc.service.IDistritoService;
import com.progeduc.service.IDocentetutorService;
import com.progeduc.service.IGeneroService;
import com.progeduc.service.IGenerodirService;
import com.progeduc.service.IGeneroprofService;
import com.progeduc.service.IPostulacionconcursoService;
import com.progeduc.service.IProgeducNivelService;
import com.progeduc.service.IProgeducTurnoService;
import com.progeduc.service.IProgramaeducativoService;
import com.progeduc.service.IProvinciaService;
import com.progeduc.service.IResponsableregistroService;
import com.progeduc.service.LenguaService;
import com.progeduc.service.ProveedorService;
import com.progeduc.service.TipodocService;
import com.progeduc.service.TipoieService;

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
	IAperturaranioService aperturaranioService;
	
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
	public String nuevoregistroapertura() {
		
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
	public String contenido_consulta(@RequestParam(name="name",required=false,defaultValue="") String name, Model model) {
		model.addAttribute("departamento",depaServ.listar());
		return "contenido_consulta";
	}	
	
	@GetMapping("/programaconsulta")
	public String programaconsulta(@RequestParam(name="name",required=false,defaultValue="") String name, Model model) {
		model.addAttribute("departamento",depaServ.listar());
		//return "contenido_consulta";
		return "programaconsulta";
	}
	
	@GetMapping("/aperturar_anio")
	public String contenido_aperturar_anio(@RequestParam(name="name",required=false,defaultValue="") String name, Model model) {
		return "contenido_aperturar_anio";
	}
	
	@GetMapping("/menu")	
    public String menu_slidet(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses){
		if(ses.getAttribute("usuario")!=null) {
			if(Integer.parseInt(ses.getAttribute("tipousuarioid").toString()) == 3) { /*el usuario es la IE*/
				model.addAttribute("usuario", ses.getAttribute("usuario"));
				model.addAttribute("perfil", ses.getAttribute("perfil"));
				model.addAttribute("tipousuarioid", ses.getAttribute("tipousuarioid"));
				Calendar fecha = Calendar.getInstance();
				model.addAttribute("nombre_concurso",aperturaranioService.buscar(fecha.get(Calendar.YEAR)).getNombreconcurso());
				return "menu_concurso";
			}
			else { /*El admin u ODS*/
				model.addAttribute("usuario", ses.getAttribute("usuario"));
				model.addAttribute("perfil", ses.getAttribute("perfil"));
				model.addAttribute("tipousuarioid", ses.getAttribute("tipousuarioid"));
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
		return "opciones_concurso";
    }	
	
	@GetMapping("/fichainscripcion/{id}")
	public String fichainscripcion(@PathVariable("id") Integer id,  Model model) {
		
		model.addAttribute("idprogramaeducativo", id);
		model.addAttribute("codmod",progeducService.ListarporId(id).getCodmod());
		model.addAttribute("responsableregistro", responsableregistroserv.listar());
		Docentetutor docentetutor = docentetutorService.ListarporId(id);
		model.addAttribute("tipodoc",tipodocserv.findAll());
		model.addAttribute("genero",generoprofserv.listar());
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
			if(docentetutor!=null) {
				model.addAttribute("docentetutor", docentetutor);
				return "fichainscripcion_update";
			}
			return "fichainscripcion";
		}
		return "postular_concurso";
	}
}
