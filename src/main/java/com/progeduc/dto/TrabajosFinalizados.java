package com.progeduc.dto;

public class TrabajosFinalizados {
	
	private Integer trabajoId;
	private Integer odsId;
	private Integer categoriaId;
	private String nivelId;
	private float nota;

	public TrabajosFinalizados(Integer trabajoId, Integer odsId, Integer categoriaId, String nivelId, float nota) {
		this.trabajoId = trabajoId;
		this.odsId = odsId;
		this.categoriaId = categoriaId;
		this.nivelId = nivelId;
		this.nota = nota;
	}

	public Integer getTrabajoId() {
		return trabajoId;
	}

	public void setTrabajoId(Integer trabajoId) {
		this.trabajoId = trabajoId;
	}

	public Integer getOdsId() {
		return odsId;
	}

	public void setOdsId(Integer odsId) {
		this.odsId = odsId;
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

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}
	
	
	
}
