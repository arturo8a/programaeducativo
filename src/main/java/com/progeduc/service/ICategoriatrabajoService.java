package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Categoriatrabajo;

public interface ICategoriatrabajoService extends ICRUD<Categoriatrabajo,Integer>{
	
	List<Categoriatrabajo> listar();

}
