package com.progeduc.dto;

import java.util.List;

import com.progeduc.model.Evaluacion;
import com.progeduc.model.Questionario;
import com.progeduc.model.Rubrica;

public class EvaluacionRubricaQuestionarioDto {
	
	Evaluacion evaluacion;
	List<Rubrica> listarubrica;
	List<Questionario> listaquestionario;
	
	public Evaluacion getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}
	public List<Rubrica> getListarubrica() {
		return listarubrica;
	}
	public void setListarubica(List<Rubrica> listarubrica) {
		this.listarubrica = listarubrica;
	}
	public List<Questionario> getListaquestionario() {
		return listaquestionario;
	}
	public void setListaquestionario(List<Questionario> listaquestionario) {
		this.listaquestionario = listaquestionario;
	}

}
