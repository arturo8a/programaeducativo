package com.progeduc.service;


import org.springframework.data.repository.query.Param;

import com.progeduc.dto.UsuarioOdsDto;
import com.progeduc.model.Usuario;

public interface IUsuarioService extends ICRUD<Usuario,Integer>{
	
	Usuario login(String usuario,String password);
	
	Usuario byUsuario(String usuario);
	
	Usuario verificarexistenciausuario(String correo);
	
	int updatecontrasenia(Integer id, String contrasenia);
	
	Usuario saveUsuarioOds(UsuarioOdsDto dto);
	
	Usuario updateUsuarioOds(UsuarioOdsDto dto);
	
	int estadoEliminar(String usuario);
	
	int estadoActivar(String usuario);
}
