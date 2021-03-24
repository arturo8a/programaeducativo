package com.progeduc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TIPODOCIDENTDIR")
public class Tipodocidentdir {
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="DESCRIPCION" ,length=50)
	private String descripcion;
	
	@Column(name="CODIGO" ,length=5)
	private String codigo;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
