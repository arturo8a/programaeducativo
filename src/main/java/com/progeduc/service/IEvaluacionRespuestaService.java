package com.progeduc.service;

import java.util.List;

import com.progeduc.dto.EvaluacionRubricaQuestionarioDto;
import com.progeduc.model.Evaluacion;
import com.progeduc.model.EvaluacionResultado;

public interface IEvaluacionRespuestaService extends ICRUD<EvaluacionResultado,Integer>{
	
	EvaluacionResultado getPorCodigoTrabajoAndCodigoPreginta(Integer idTrabajo, Integer idPregunta);
	
	List<EvaluacionResultado> getRespuestas(Integer idTrabajo);
	
	List<EvaluacionResultado> listaEvaluacionResultado(Integer idTrabajo, Integer idUsuario);
	/*Integer saveEvalRubQuest(EvaluacionRubricaQuestionarioDto dto);
	
	int updateestado(Integer id, Integer estado);
	
	Evaluacion updateEvalRubQuest(EvaluacionRubricaQuestionarioDto dto);
	
	Evaluacion getPorAnio(Integer anio);
	
	Evaluacion getPorAnioCategoriaNivelparticipacion(Integer anio,Integer idcategoria,Integer idnivelparticipacion);
	
	Evaluacion getPorAnioNivelparticipacionCategoria(Integer anio,Integer nivelparticipacion,Integer idcategoria);*/
	
	void borrarEvaluacionesPorTrabajo(Integer idTrabajo);
}
