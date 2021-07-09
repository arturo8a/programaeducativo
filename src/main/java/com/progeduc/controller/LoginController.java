package com.progeduc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.progeduc.componente.Ldap;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.Usuario;
import com.progeduc.model.UsuarioLdap;
import com.progeduc.model.Usuario_Ods;
import com.progeduc.service.IOdsService;
import com.progeduc.service.IProgramaeducativoService;
import com.progeduc.service.ITipousuarioService;
import com.progeduc.service.IUsuarioOdsService;
import com.progeduc.service.IUsuarioService;
import com.progeduc.service.IUsuario_odsService;

@Controller
@RequestMapping("")
//@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	IUsuarioService usuarioServ;
	
	@Autowired
	IUsuario_odsService usuario_odsServ;
	
	@Autowired
	IProgramaeducativoService progeducService;
	
	@Autowired
	private IOdsService odsserv;
	
	@Autowired
	private IUsuarioOdsService usuarioodsServ;
	
	@Autowired
	private ITipousuarioService tipousuarioServ;
	
	//@PostMapping(value="/loginUser")
	@RequestMapping(value="/loginUser", method = RequestMethod.POST, produces="text/plain")	
	public @ResponseBody String loginUser(@RequestParam("usuario") String usuario, @RequestParam("password") String password,HttpSession ses) throws Exception {
		
		if(usuario.equals("admin") && password.equals("Sunass2020")) {
			ses.setAttribute("usuario", "admin");/*adminpro*/
    		ses.setAttribute("perfil", "admin");
    		ses.setAttribute("tipousuarioid", 30);
    		return "admin";
		}
		
		Ldap mildap = new Ldap();
		Integer rpta = mildap.validarUsuario(usuario, password);
		
		if(rpta == 1){
			Usuario obj = usuarioServ.byUsuario(usuario);
			if(obj.getTipousuario().getId() == 1 || obj.getTipousuario().getId() == 2 || obj.getTipousuario().getId() == 11 || obj.getTipousuario().getId() == 12 ) {
				ses.setAttribute("usuario", obj.getUsuario());/*admin,espcialista ods, especialista du*/
        		ses.setAttribute("perfil", obj.getTipousuario().getDescripcion());
        		ses.setAttribute("tipousuarioid", obj.getTipousuario().getId());
        		return obj.getUsuario();
			}
		}
		else {
			Usuario obj = usuarioServ.login(usuario, password);
			if(obj == null) {
				Usuario_Ods uo = usuario_odsServ.login(usuario, password);
        		if(uo == null) {
        			return "-1";
        		}
        		ses.setAttribute("usuario", uo.getOds());/*ODS*/
        		ses.setAttribute("perfil", uo.getOds());
        		ses.setAttribute("tipousuarioid", 0);
        		ses.setAttribute("odsid", uo.getOdsid());
        		return uo.getNombres();
    		}
			Programaeducativo pe = progeducService.getCodmodByAnioActual(obj.getUsuario());
    		if(pe!=null) { /*IIEE*/
    			ses.setAttribute("usuario", obj.getUsuario());
        		ses.setAttribute("perfil", obj.getTipousuario().getDescripcion());
        		ses.setAttribute("tipousuarioid", obj.getTipousuario().getId());
        		return obj.getUsuario();
    		}
		}
		return "-2";
	}
	
	/*@RequestMapping(value="/login", method = RequestMethod.POST, produces="text/plain")	
    public @ResponseBody String loginUsuario(@RequestParam("usuario") String usuario, @RequestParam("password") String password,HttpSession ses){
    	
		try {
    		Usuario obj = usuarioServ.login(usuario, password);
    		if(obj==null) {
        		Usuario_Ods uo = usuario_odsServ.login(usuario, password);
        		if(uo == null) {
        			return "-1";
        		}
        		ses.setAttribute("usuario", uo.getOds());
        		ses.setAttribute("perfil", uo.getOds());
        		ses.setAttribute("tipousuarioid", 0);
        		return uo.getNombres();
        	}
        	else {
        		if(obj.getTipousuario().getId()==12) {
        			ses.setAttribute("usuario", obj.getUsuario());
            		ses.setAttribute("perfil", obj.getTipousuario().getDescripcion());
            		ses.setAttribute("tipousuarioid", obj.getTipousuario().getId());
            		return obj.getUsuario();
        		}
        		else {
        			Programaeducativo pe = progeducService.getCodmodByAnioActual(obj.getUsuario());
            		if(pe!=null) {
            			ses.setAttribute("usuario", obj.getUsuario());
                		ses.setAttribute("perfil", obj.getTipousuario().getDescripcion());
                		ses.setAttribute("tipousuarioid", obj.getTipousuario().getId());
                		return obj.getUsuario();
            		}
            		else {
            			return "-2";
            		}
        		}        		      		
        	}
    	} catch (Exception ex) {
    		System.out.println("login: " + ex);
            return "error";
    	}    	
    }*/
	
	@GetMapping("/cerrar_sesion")
	public String  cerrarsesion(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses) {
		ses.removeAttribute("usuario");
		ses.removeAttribute("perfil");
		ses.removeAttribute("flag");
		System.out.println("/cerrar_session :"+ses.getAttribute("usuario"));		
		return "login";
	}
	
	@GetMapping("/formnuevousuario")
	public String formnuevousuario(Model model) throws Exception {
		model.addAttribute("listaods",odsserv.listarAll());		
		Ldap mildap = new Ldap();
		List<UsuarioLdap> lista = mildap.listarTodosUsuariosLDAP();
		model.addAttribute("usuarios", lista);
		model.addAttribute("tipousuarios", tipousuarioServ.lista());
		return "formnuevousuario";
	}
	
	
	
}
