package com.progeduc.dto;

public class CategoriaModalidadByOds {
	
	private Integer categoriaId;
	private Integer modalidadId;
	
	public Integer getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	public Integer getModalidadId() {
		return modalidadId;
	}
	public void setModalidadId(Integer modalidadId) {
		this.modalidadId = modalidadId;
	}
	public CategoriaModalidadByOds(Integer categoriaId, Integer modalidadId) {
		this.categoriaId = categoriaId;
		this.modalidadId = modalidadId;
	}
	
	

}
