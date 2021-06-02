package com.progeduc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EVALUACION")
public class Evaluacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="ANIO")
	private Integer anio;
	
	@ManyToOne
	@JoinColumn(name="idcategoriaevaluacion",nullable=true,foreignKey=@ForeignKey(name="FK_evaluacion_cateval"))
	private Categoriaevaluacion categoriaevaluacion;
	
	@ManyToOne
	@JoinColumn(name="idnivelparticipacion",nullable=true,foreignKey=@ForeignKey(name="FK_evaluacion_nivelpart"))
	private Nivelparticipacion nivelparticipacion;
	
	@ManyToOne
	@JoinColumn(name="idestadoevaluacion",nullable=true,foreignKey=@ForeignKey(name="FK_evaluacion_estadoeval"))
	private Estadoevaluacion estadoevaluacion;
	
	@Column(name="ESTADO")
	private Integer estado;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Categoriaevaluacion getCategoriaevaluacion() {
		return categoriaevaluacion;
	}

	public void setCategoriaevaluacion(Categoriaevaluacion categoriaevaluacion) {
		this.categoriaevaluacion = categoriaevaluacion;
	}

	public Nivelparticipacion getNivelparticipacion() {
		return nivelparticipacion;
	}

	public void setNivelparticipacion(Nivelparticipacion nivelparticipacion) {
		this.nivelparticipacion = nivelparticipacion;
	}

	public Estadoevaluacion getEstadoevaluacion() {
		return estadoevaluacion;
	}

	public void setEstadoevaluacion(Estadoevaluacion estadoevaluacion) {
		this.estadoevaluacion = estadoevaluacion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
}
