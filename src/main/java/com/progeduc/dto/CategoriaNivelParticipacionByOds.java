package com.progeduc.dto;

public class CategoriaNivelParticipacionByOds {
	
	private Integer categoriaId;
	private String nivelId;
	
	public CategoriaNivelParticipacionByOds(Integer categoriaId, String nivelId) {
		this.categoriaId = categoriaId;
		this.nivelId = nivelId;
	}
	public Integer getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getNivelId() {
		return nivelId;
	}
	public void setNivelId(String nivelId) {
		this.nivelId = nivelId;
	}

}
