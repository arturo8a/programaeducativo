package com.progeduc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="DISTRITO")
public class Distrito {
	
	@Id
	private Integer id;	
	
	@Column(name="DESCRIPCION" ,length=100)
	private String descripcion;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="provinciaid",nullable=false,foreignKey=@ForeignKey(name="fk_provincia_distrito"))
	private Provincia provincia;
	
	/*@ManyToOne
	@JoinColumn(name="odsid",nullable=false,foreignKey=@ForeignKey(name="fk_distrito_ods"))
	private Ods ods;*/
	
	@Column(name="ODSID")
	private Integer odsid;
	
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
	
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Integer getOdsid() {
		return odsid;
	}

	public void setOdsid(Integer odsid) {
		this.odsid = odsid;
	}	
	
}
