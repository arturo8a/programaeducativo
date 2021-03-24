package com.progeduc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ODS")
public class Ods {

	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="DESC_ODS",nullable=false,length=100)
	private String des_ods;
	
	@Column(name="DESCRIPCION",nullable=false,length=100)
	private String descripcion;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDes_ods() {
		return des_ods;
	}

	public void setDes_ods(String des_ods) {
		this.des_ods = des_ods;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
