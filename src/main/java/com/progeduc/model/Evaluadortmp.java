package com.progeduc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EVALUADORTMP")
public class Evaluadortmp {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@Column(name="ANIO",nullable=true)
	private Integer anio;
	
	@ManyToOne
	@JoinColumn(name="odsid",nullable=true,foreignKey=@ForeignKey(name="FK_evaluador_ods"))
	private Ods ods;
	
	@ManyToOne
	@JoinColumn(name="idnivelparticipacion",nullable=true,foreignKey=@ForeignKey(name="FK_evaluador_nivelpart"))
	private  Nivelparticipacion nivelparticipacion;
	
	@ManyToOne
	@JoinColumn(name="idcategoria",nullable=true,foreignKey=@ForeignKey(name="FK_evaluador_categoria"))
	private Categoria categoria;
	
	@Column(name="CODMOD",nullable=true,length=70)
	private String codmod;
	
	@Column(name="EVALUADORASIGNADO",nullable=true)
	private Integer evaluadorasignado;
	
	@OneToMany(mappedBy="evaluadortmp", cascade = {CascadeType.ALL},orphanRemoval=true)
	private List<TrabajoEvaluadorTmp> trabajoevaluadortmp;
	
	@OneToMany(mappedBy="evaluadortmp", cascade = {CascadeType.ALL},orphanRemoval=true)
	private List<Evaluadorestmp> evaluadorestmp;

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

	public Ods getOds() {
		return ods;
	}

	public void setOds(Ods ods) {
		this.ods = ods;
	}

	public Nivelparticipacion getNivelparticipacion() {
		return nivelparticipacion;
	}

	public void setNivelparticipacion(Nivelparticipacion nivelparticipacion) {
		this.nivelparticipacion = nivelparticipacion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getCodmod() {
		return codmod;
	}

	public void setCodmod(String codmod) {
		this.codmod = codmod;
	}

	public Integer getEvaluadorasignado() {
		return evaluadorasignado;
	}

	public void setEvaluadorasignado(Integer evaluadorasignado) {
		this.evaluadorasignado = evaluadorasignado;
	}

	public List<TrabajoEvaluadorTmp> getTrabajoevaluadortmp() {
		return trabajoevaluadortmp;
	}

	public void setTrabajoevaluadortmp(List<TrabajoEvaluadorTmp> trabajoevaluadortmp) {
		this.trabajoevaluadortmp = trabajoevaluadortmp;
	}

	public List<Evaluadorestmp> getEvaluadorestmp() {
		return evaluadorestmp;
	}

	public void setEvaluadorestmp(List<Evaluadorestmp> evaluadorestmp) {
		this.evaluadorestmp = evaluadorestmp;
	}

}
