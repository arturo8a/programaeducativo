package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Evaluacionrubrica;

public interface IEvaluacionRubricaService extends ICRUD<Evaluacionrubrica,Integer>{
	
	Integer guardar(Integer evaluacionid, Integer rubricaid);
	
	List<Evaluacionrubrica> listarPorEvaluacionId(Integer id);
	
	List<Evaluacionrubrica> verificaExiste(Integer evaluacionid, Integer rubricaid);
}
