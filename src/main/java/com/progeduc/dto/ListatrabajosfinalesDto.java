package com.progeduc.dto;

public class ListatrabajosfinalesDto {
	
	Integer id;
	String categoria;
	String titulo;
	String modalidad;
	String participantes;
	String archivos;
	Integer enviado;
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getParticipantes() {
		return participantes;
	}
	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}
	public String getArchivos() {
		return archivos;
	}
	public void setArchivos(String archivos) {
		this.archivos = archivos;
	}
	public Integer getEnviado() {
		return enviado;
	}
	public void setEnviado(Integer enviado) {
		this.enviado = enviado;
	}
	
	

}
