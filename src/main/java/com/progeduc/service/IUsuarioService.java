package com.progeduc.service;


import com.progeduc.model.Usuario;

public interface IUsuarioService extends ICRUD<Usuario,Integer>{
	
	Usuario login(String usuario,String password);
	
	Usuario byUsuario(String usuario);
	
	Usuario verificarexistenciausuario(String correo);
	
	int updatecontrasenia(Integer id, String contrasenia);
}
