package com.progeduc.service;


import java.util.List;

import com.progeduc.interfac.AprobacionInscripciones;
import com.progeduc.interfac.DepartamentoDto;
import com.progeduc.model.UsuarioOds;

public interface IUsuarioOdsService extends ICRUD<UsuarioOds,Integer>{
	
	Integer guardar(Integer usuarioid, Integer odsid);
	List<AprobacionInscripciones> listarByUsuarioAprobacionInscripciones(Integer idusuario,Integer idods,Integer anio,String nombre,String estado);
	List<UsuarioOds> listarByUsuario(Integer usuarioid);
	List<DepartamentoDto> listarDepartamentoByUsuario(String usuario);
}
