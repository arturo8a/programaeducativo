package com.progeduc.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.progeduc.model.QuestionarioRespuesta;

public interface IQuestionarioRespuestaService extends ICRUD<QuestionarioRespuesta,Integer>{
	
	//Integer guardar(Integer puntaje, String respuesta,Integer Questionario);
	
	Integer eliminar(@Param("id") Integer id);
	
	List<QuestionarioRespuesta> listarByTrabajo(Integer trabajoid);
	
	List<QuestionarioRespuesta> listarByQuestionario(Integer questionarioid);
}
