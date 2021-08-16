package com.progeduc.service;

import com.progeduc.model.Docentetutor;

public interface IDocentetutorService extends ICRUD<Docentetutor,Integer>{
	
	Docentetutor getByProgeduc(Integer programaeducativoid);
	Docentetutor getByProgeducByAnio(Integer programaeducativoid,Integer anio);
}
