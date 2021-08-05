package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Ods;

public interface IOdsService {
	
	Ods byOds(int id);
	
	List<Ods> listarAll();
	
	List<Ods> listarOdsDeTrabajosEvaluados();
}
