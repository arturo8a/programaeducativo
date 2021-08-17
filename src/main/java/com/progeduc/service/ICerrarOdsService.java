package com.progeduc.service;

import java.util.List;

import com.progeduc.model.CerrarOds;

public interface ICerrarOdsService {
	
	
	CerrarOds guardar(CerrarOds cerrarOds);
	
	List<CerrarOds> listCerrarOds();
	
}
