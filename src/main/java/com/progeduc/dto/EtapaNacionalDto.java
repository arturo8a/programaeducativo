package com.progeduc.dto;

public class EtapaNacionalDto {
	
	String nivelDesc;
	Integer categoriaId;
	String categoriaDesc;
	String estado;
	
	public String getNivelDesc() {
		return nivelDesc;
	}
	public void setNivelDesc(String nivelDesc) {
		this.nivelDesc = nivelDesc;
	}
	public Integer getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCategoriaDesc() {
		return categoriaDesc;
	}
	public void setCategoriaDesc(String categoriaDesc) {
		this.categoriaDesc = categoriaDesc;
	}

}
