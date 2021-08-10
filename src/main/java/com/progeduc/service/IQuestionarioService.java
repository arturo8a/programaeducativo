package com.progeduc.service;


import com.progeduc.model.Questionario;

public interface IQuestionarioService extends ICRUD<Questionario,Integer>{
	
	int updateestado(Integer id,Integer estado);
}
