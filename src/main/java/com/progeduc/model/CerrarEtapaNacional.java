package com.progeduc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CERRAR_NACIONAL")
public class CerrarEtapaNacional {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="categoria_Id")
	private Integer categoriaId;
	
	@Column(name="nivel")
	private String nivelDesc;
	
	@Column(name="estado")
	private Integer estado;
	
	@Column(name="anio")
	private Integer anio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNivelDesc() {
		return nivelDesc;
	}

	public void setNivelDesc(String nivelDesc) {
		this.nivelDesc = nivelDesc;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	

}
