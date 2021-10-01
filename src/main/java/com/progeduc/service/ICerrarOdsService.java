package com.progeduc.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.progeduc.model.CerrarOds;

public interface ICerrarOdsService {
	
	
	CerrarOds guardar(CerrarOds cerrarOds);
	
	List<CerrarOds> listCerrarOds();
	
	CerrarOds buscarPorOdsAnioactual(Integer odsid);
	
	List<CerrarOds>  listarPorAnio();
	
}
