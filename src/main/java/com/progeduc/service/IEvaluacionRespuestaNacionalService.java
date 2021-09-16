package com.progeduc.service;

import java.util.List;

import com.progeduc.dto.rubricaPuntajeDto;
import com.progeduc.model.EvaluacionResultadoNacional;

public interface IEvaluacionRespuestaNacionalService extends ICRUD<EvaluacionResultadoNacional,Integer>{
	
	EvaluacionResultadoNacional getPorCodigoTrabajoAndCodigoPreginta(Integer idTrabajo, Integer idPregunta);
	
	List<EvaluacionResultadoNacional> getRespuestas(Integer idTrabajo);
	
	List<EvaluacionResultadoNacional> listaEvaluacionResultado(Integer idTrabajo, Integer idUsuario);
	/*Integer saveEvalRubQuest(EvaluacionRubricaQuestionarioDto dto);
	
	int updateestado(Integer id, Integer estado);
	
	Evaluacion updateEvalRubQuest(EvaluacionRubricaQuestionarioDto dto);
	
	Evaluacion getPorAnio(Integer anio);
	
	Evaluacion getPorAnioCategoriaNivelparticipacion(Integer anio,Integer idcategoria,Integer idnivelparticipacion);
	
	Evaluacion getPorAnioNivelparticipacionCategoria(Integer anio,Integer nivelparticipacion,Integer idcategoria);*/
	
	void borrarEvaluacionesPorTrabajo(Integer idTrabajo);
	
	List<rubricaPuntajeDto> listaRubricaPuntajeDto(Integer trabajoid);
}
