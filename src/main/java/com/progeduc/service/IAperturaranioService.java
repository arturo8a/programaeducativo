package com.progeduc.service;


import com.progeduc.model.Aperturaranio;

public interface IAperturaranioService extends ICRUD<Aperturaranio,Integer>{
	
	boolean buscarSiExiste(Integer anio);
	
	Aperturaranio buscar(Integer anio);
	
	Aperturaranio buscarsicumpleconcurso(String fecha);
}
