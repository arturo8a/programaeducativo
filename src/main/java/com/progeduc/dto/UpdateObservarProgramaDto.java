package com.progeduc.dto;

public class UpdateObservarProgramaDto {
	
	Integer id;
	String estado;
	String motivoobservacion;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getMotivoObservacion() {
		return motivoobservacion;
	}
	
	public void setMotivoobservacion(String motivoobservacion) {
		this.motivoobservacion = motivoobservacion;
	}
}
