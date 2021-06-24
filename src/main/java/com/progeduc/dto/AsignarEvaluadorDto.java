package com.progeduc.dto;

import java.util.List;

import com.progeduc.model.Evaluadortmp;

public class AsignarEvaluadorDto {
	
	Evaluadortmp evaluadortmp;
	List<Integer> trabajosevaluados;
	List<Integer> evaluadores;
	
	public Evaluadortmp getEvaluadortmp() {
		return evaluadortmp;
	}
	public void setEvaluadortmp(Evaluadortmp evaluadortmp) {
		this.evaluadortmp = evaluadortmp;
	}
	public List<Integer> getTrabajosevaluados() {
		return trabajosevaluados;
	}
	public void setTrabajosevaluados(List<Integer> trabajosevaluados) {
		this.trabajosevaluados = trabajosevaluados;
	}
	public List<Integer> getEvaluadores() {
		return evaluadores;
	}
	public void setEvaluadores(List<Integer> evaluadores) {
		this.evaluadores = evaluadores;
	}
	
	
}
