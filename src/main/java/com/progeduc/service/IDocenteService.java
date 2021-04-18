package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Docente;

public interface IDocenteService extends ICRUD<Docente,Integer>{
	
	Docente getByProgeduc(Integer programaeducativoid);
	List<Docente> listByProgeduc(Integer programaeducativoid);
	int updateestado(Integer id, Integer estado);
	List<Docente> listarhabilitados(Integer programaeducativoid);
	List<Docente> listarTodoshabilitados();
}
