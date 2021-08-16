package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Postulacionconcurso;

public interface IPostulacionconcursoService extends ICRUD<Postulacionconcurso,Integer>{
	
	Postulacionconcurso getById(Integer programaeducativoid);
	Postulacionconcurso getByIdAnio(Integer programaeducativoid, Integer anio);
	int updatefinalizarparticipaciontrabajo(Integer id);
	List<Integer> aniosConcurso();
}
