package com.progeduc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RUBRICA")
public class Rubrica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="rubrica",nullable=true)
	private String rubrica;
	
	@Column(name="criterio1",nullable=true)
	private String criterio1;
	
	@Column(name="descripcion1",nullable=true,length=250)
	private String descripcion1;
	
	@Column(name="puntaje1",nullable=true)
	private Integer puntaje1;
	
	@Column(name="criterio2",nullable=true)
	private String criterio2;
	
	@Column(name="descripcion2",nullable=true,length=250)
	private String descripcion2;
	
	@Column(name="puntaje2",nullable=true)
	private Integer puntaje2;
	
	@Column(name="criterio3",nullable=true)
	private String criterio3;
	
	@Column(name="descripcion3",nullable=true,length=250)
	private String descripcion3;
	
	@Column(name="puntaje3",nullable=true)
	private Integer puntaje3;
	
	@Column(name="criterio4",nullable=true)
	private String criterio4;
	
	@Column(name="descripcion4",nullable=true,length=250)
	private String descripcion4;
	
	@Column(name="puntaje4",nullable=true)
	private Integer puntaje4;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRubrica() {
		return rubrica;
	}

	public void setRubrica(String rubrica) {
		this.rubrica = rubrica;
	}

	public String getCriterio1() {
		return criterio1;
	}

	public void setCriterio1(String criterio1) {
		this.criterio1 = criterio1;
	}

	public String getDescripcion1() {
		return descripcion1;
	}

	public void setDescripcion1(String descripcion1) {
		this.descripcion1 = descripcion1;
	}

	public Integer getPuntaje1() {
		return puntaje1;
	}

	public void setPuntaje1(Integer puntaje1) {
		this.puntaje1 = puntaje1;
	}

	public String getCriterio2() {
		return criterio2;
	}

	public void setCriterio2(String criterio2) {
		this.criterio2 = criterio2;
	}

	public String getDescripcion2() {
		return descripcion2;
	}

	public void setDescripcion2(String descripcion2) {
		this.descripcion2 = descripcion2;
	}

	public Integer getPuntaje2() {
		return puntaje2;
	}

	public void setPuntaje2(Integer puntaje2) {
		this.puntaje2 = puntaje2;
	}

	public String getCriterio3() {
		return criterio3;
	}

	public void setCriterio3(String criterio3) {
		this.criterio3 = criterio3;
	}

	public String getDescripcion3() {
		return descripcion3;
	}

	public void setDescripcion3(String descripcion3) {
		this.descripcion3 = descripcion3;
	}

	public Integer getPuntaje3() {
		return puntaje3;
	}

	public void setPuntaje3(Integer puntaje3) {
		this.puntaje3 = puntaje3;
	}

	public String getCriterio4() {
		return criterio4;
	}

	public void setCriterio4(String criterio4) {
		this.criterio4 = criterio4;
	}

	public String getDescripcion4() {
		return descripcion4;
	}

	public void setDescripcion4(String descripcion4) {
		this.descripcion4 = descripcion4;
	}

	public Integer getPuntaje4() {
		return puntaje4;
	}

	public void setPuntaje4(Integer puntaje4) {
		this.puntaje4 = puntaje4;
	}
	
}
