package com.progeduc.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progeduc.componente.Ldap;
import com.progeduc.dto.UsuarioDto;
import com.progeduc.dto.UsuarioOdsDto;
import com.progeduc.model.UsuarioLdap;
import com.progeduc.service.IUsuarioService;

@RestController
@RequestMapping("")
public class UsuarioController {
	
	@Autowired
	IUsuarioService usuarioService;

	UsuarioDto usuarioDto;
	
	@Autowired
	private IUsuarioService usuarioServ;
	
	int contador;
	
	@GetMapping("/listarusuarios")
	public ResponseEntity<List<UsuarioDto>> listarusuarios() throws Exception {
		
		List<UsuarioDto> lista =  new ArrayList<UsuarioDto>();		
		Ldap mildap = new Ldap();
		List<UsuarioLdap> listaldap = mildap.listarTodosUsuariosLDAP();
		usuarioService.listar().forEach(obj->{
			usuarioDto = new UsuarioDto();
			listaldap.forEach(obj1->{
				if(obj.getTipousuario().getId() == 1 || obj.getTipousuario().getId() == 2 || obj.getTipousuario().getId() == 11) {
					if(obj1.getCuenta().equals(obj.getUsuario())) {
						usuarioDto.setNombre(obj1.getNombre());
						usuarioDto.setApellido(obj1.getUsuario());
						usuarioDto.setPerfil(obj.getTipousuario().getDescripcion());
						usuarioDto.setUsuario(obj.getUsuario());
						usuarioDto.setCorreo(obj1.getCorreo());
						usuarioDto.setId(obj.getId());
						usuarioDto.setEstado(obj.getEstado());
						lista.add(usuarioDto);
					}
				}
			});
		});		
		return new ResponseEntity<List<UsuarioDto>>(lista, HttpStatus.OK);
	}
		
	@PostMapping(value="/registrarusuarioods")
	public Integer registrarusuarioods(@Valid @RequestBody UsuarioOdsDto dto) {
		
		if(usuarioServ.verificarexistenciausuario(dto.getUsuario().getUsuario()) == null) {
			
			Date date= new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			dto.getUsuario().setFecha_registro(ts);
			dto.getUsuario().setAnio(ts.toLocalDateTime().getYear());
			
			if(usuarioServ.saveUsuarioOds(dto)!=null)
				return 1;//registrado
			else
				return 0;//error al registrar usuario
		}
		return 2; //existe
		
	}
	
	@PostMapping(value="/actualizarusuarioods")
	public Integer actualizarusuarioods(@Valid @RequestBody UsuarioOdsDto dto) {
		
		if(usuarioServ.updateUsuarioOds(dto)!=null)
			return 1;//registrado
		else
			return 0;//error al registrar usuario
		
	}
	
	@GetMapping(value="/eliminarusuario/{usuario}")
	public Integer eliminarparticipanteid(@PathVariable("usuario") String usuario) {
		return usuarioServ.estadoEliminar(usuario);
	}
	
	@GetMapping(value="/activarusuario/{usuario}")
	public Integer activarusuario(@PathVariable("usuario") String usuario) {
		return usuarioServ.estadoActivar(usuario);
	}

}
