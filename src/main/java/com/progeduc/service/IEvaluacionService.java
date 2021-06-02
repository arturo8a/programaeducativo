package com.progeduc.service;

import com.progeduc.dto.EvaluacionRubricaQuestionarioDto;
import com.progeduc.model.Evaluacion;

public interface IEvaluacionService extends ICRUD<Evaluacion,Integer>{
	
	Evaluacion saveEvalRubQuest(EvaluacionRubricaQuestionarioDto dto);
	
	int updateestado(Integer id, Integer estado);
	
	Evaluacion updateEvalRubQuest(EvaluacionRubricaQuestionarioDto dto);
}
