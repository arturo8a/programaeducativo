package com.progeduc.service;


import java.util.List;

import com.progeduc.model.UsuarioOds;

public interface IUsuarioOdsService extends ICRUD<UsuarioOds,Integer>{
	
	Integer guardar(Integer usuarioid, Integer odsid);
	List<UsuarioOds> listarByUsuario(Integer usuarioid);
}
