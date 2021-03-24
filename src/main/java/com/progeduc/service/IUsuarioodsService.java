package com.progeduc.service;

import org.springframework.data.repository.query.Param;

import com.progeduc.model.UsuarioOds;

public interface IUsuarioodsService extends ICRUD<UsuarioOds, Integer> {
	
	UsuarioOds byDepartamento(@Param("iddepartamento") Integer iddepartamento);	
	
	UsuarioOds login(String usuario,String password);
	
	UsuarioOds verificarexistenciacorreo(String correo);
	
	int updatecontrasenia(Integer id, String contrasenia);
}
