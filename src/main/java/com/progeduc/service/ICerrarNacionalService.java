package com.progeduc.service;

import java.util.List;

import com.progeduc.model.CerrarEtapaNacional;

public interface ICerrarNacionalService extends ICRUD<CerrarEtapaNacional,Integer>{
	
	List<CerrarEtapaNacional> listaNacionalEmpates();
	
	List<CerrarEtapaNacional> listar();
	
	public CerrarEtapaNacional registrar(CerrarEtapaNacional obj);
	
	public CerrarEtapaNacional modificar(CerrarEtapaNacional obj);

}
