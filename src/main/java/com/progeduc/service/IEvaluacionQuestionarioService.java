package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Evaluacionquestionario;

public interface IEvaluacionQuestionarioService extends ICRUD<Evaluacionquestionario,Integer>{
	
	Integer guardar(Integer evaluacionid, Integer questionarioid);
	
	List<Evaluacionquestionario> listarPorEvaluacionId(Integer id);
	
	List<Evaluacionquestionario> verificaExiste(Integer evaluacionid, Integer questionarioid);
	
}
