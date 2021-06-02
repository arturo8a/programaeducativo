package com.progeduc.service;

import org.springframework.data.repository.query.Param;

import com.progeduc.model.Usuario_Ods;

public interface IUsuario_odsService extends ICRUD<Usuario_Ods, Integer> {
	
	Usuario_Ods byDepartamento(@Param("iddepartamento") Integer iddepartamento);	
	
	Usuario_Ods login(String usuario,String password);
	
	Usuario_Ods verificarexistenciacorreo(String correo);
	
	int updatecontrasenia(Integer id, String contrasenia);
}
