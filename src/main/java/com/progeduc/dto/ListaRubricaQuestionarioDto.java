package com.progeduc.dto;

import java.util.List;

import com.progeduc.model.Questionario;
import com.progeduc.model.Rubrica;

public class ListaRubricaQuestionarioDto {
	
	List<Rubrica> listarubrica;
	List<Questionario> listaquestionario;
	
	public List<Rubrica> getListarubrica() {
		return listarubrica;
	}
	public void setListarubrica(List<Rubrica> listarubrica) {
		this.listarubrica = listarubrica;
	}
	public List<Questionario> getListaquestionario() {
		return listaquestionario;
	}
	public void setListaquestionario(List<Questionario> listaquestionario) {
		this.listaquestionario = listaquestionario;
	}

}
