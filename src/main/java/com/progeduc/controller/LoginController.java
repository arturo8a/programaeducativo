package com.progeduc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.progeduc.model.Usuario;
import com.progeduc.model.UsuarioOds;
import com.progeduc.service.IUsuarioService;
import com.progeduc.service.IUsuarioodsService;

@Controller
@RequestMapping("")
//@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	IUsuarioService usuarioServ;
	
	@Autowired
	IUsuarioodsService usuarioodsServ;

	@RequestMapping(value="/login", method = RequestMethod.POST, produces="text/plain")	
    public @ResponseBody String loginUsuario(@RequestParam("usuario") String usuario, @RequestParam("password") String password,HttpSession ses){
    	
		try {
    		Usuario obj = usuarioServ.login(usuario, password);
        	if(obj==null) {
        		UsuarioOds uo = usuarioodsServ.login(usuario, password);
        		if(uo == null) {
        			return "-1";
        		}
        		ses.setAttribute("usuario", uo.getOds());
        		ses.setAttribute("perfil", uo.getOds());
        		ses.setAttribute("tipousuarioid", 0);
        		ses.setAttribute("odsid", uo.getOdsid());
        		return uo.getNombres();
        	}
        	else {
        		ses.setAttribute("usuario", obj.getUsuario());
        		ses.setAttribute("perfil", obj.getTipousuario().getDescripcion());
        		ses.setAttribute("odsid", 0);
        		ses.setAttribute("tipousuarioid", obj.getTipousuario().getId());
        		return obj.getNombre();
        	} 		
    	} catch (Exception ex) {
    		System.out.println("login: " + ex);
            return "error";
    	}
    	
    }
	
	@GetMapping("/cerrar_sesion")
	public String  cerrarsesion(@RequestParam(name="name",required=false,defaultValue="") String name, Model model,HttpSession ses) {
		ses.removeAttribute("usuario");
		ses.removeAttribute("perfil");
		ses.removeAttribute("flag");
		System.out.println("/cerrar_session :"+ses.getAttribute("usuario"));		
		return "login";
	}
	
	
	
}
