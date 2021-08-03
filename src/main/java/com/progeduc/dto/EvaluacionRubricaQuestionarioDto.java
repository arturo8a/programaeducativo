package com.progeduc.dto;

import java.util.List;

import com.progeduc.model.Evaluacion;
import com.progeduc.model.Questionario;
import com.progeduc.model.Rubrica;

public class EvaluacionRubricaQuestionarioDto {
	
	Evaluacion evaluacion;
	List<Rubrica> listarubrica;
	List<Questionario> listaquestionario;
	List<ClaveDto> eliminado_rubrica;
	List<ClaveDto> eliminado_questionario;
	
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
	public List<ClaveDto> getEliminado_rubrica() {
		return eliminado_rubrica;
	}
	public void setEliminado_rubrica(List<ClaveDto> eliminado_rubrica) {
		this.eliminado_rubrica = eliminado_rubrica;
	}
	public List<ClaveDto> getEliminado_questionario() {
		return eliminado_questionario;
	}
	public void setEliminado_questionario(List<ClaveDto> eliminado_questionario) {
		this.eliminado_questionario = eliminado_questionario;
	}
	public void setListarubrica(List<Rubrica> listarubrica) {
		this.listarubrica = listarubrica;
	}

}
