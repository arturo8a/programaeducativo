package com.progeduc.service;

import com.progeduc.model.Rubrica;

public interface IRubricaService extends ICRUD<Rubrica,Integer>{
	
	int updateestado(Integer id,Integer estado);
}
