package com.progeduc.dto;

public class AgregarRespuestaPreguntaDto {
	
	String respuesta;
	float puntaje;
	Integer cuestionarioid;
	
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public float getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(float puntaje) {
		this.puntaje = puntaje;
	}
	public Integer getCuestionarioid() {
		return cuestionarioid;
	}
	public void setCuestionarioid(Integer cuestionarioid) {
		this.cuestionarioid = cuestionarioid;
	}
	
	
	
}
