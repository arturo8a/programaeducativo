package com.progeduc.dto;

import java.util.List;

public class AsignarEvaluadoresDto {
	
	List<ClaveDto> trabajos_evaluados;
	List<ClaveDto> evaluadores;
	
	public List<ClaveDto> getTrabajos_evaluados() {
		return trabajos_evaluados;
	}
	public void setTrabajos_evaluados(List<ClaveDto> trabajos_evaluados) {
		this.trabajos_evaluados = trabajos_evaluados;
	}
	public List<ClaveDto> getEvaluadores() {
		return evaluadores;
	}
	public void setEvaluadores(List<ClaveDto> evaluadores) {
		this.evaluadores = evaluadores;
	}
	
}
