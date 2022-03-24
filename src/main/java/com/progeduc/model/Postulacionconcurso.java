package com.progeduc.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="POSTULACIONCONCURSO")
public class Postulacionconcurso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="categoria1")
	private Integer categoria1;
	
	@Column(name="categoria2")
	private Integer categoria2;
	
	@Column(name="categoria3")
	private Integer categoria3;
	
	@Column(name="categoria4")
	private Integer categoria4;
	
	@Column(name="categoria5")
	private Integer categoria5;
	
	@Column(name="FINALIZARPARTICIPACIONTRABAJO",nullable=true)
	private Integer finalizarparticipaciontrabajo;
	
	@Column(name="FINALIZARPARTDOCENTETRABAJO",nullable=true)
	private Integer finalizarparticipaciondocentetrabajo;
	
	@ManyToOne
	@JoinColumn(name="programaeducativoid",nullable=false,foreignKey=@ForeignKey(name="FK_programaeducativo_id"))
	private Programaeducativo progeduc;
	
	@Column(name="anio")
	private Integer anio;
	
	@JsonIgnore
	@Column(name="FECHA_REGISTRO",nullable=true)
	private Timestamp fecha_registro;	
	
	public Timestamp getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Postulacionconcurso() {
		
	}
	
	public Integer getCategoria1() {
		return categoria1;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCategoria1(Integer categoria1) {
		this.categoria1 = categoria1;
	}
	
	public Integer getCategoria2() {
		return categoria2;
	}
	
	public void setCategoria2(Integer categoria2) {
		this.categoria2 = categoria2;
	}
	
	public Integer getCategoria3() {
		return categoria3;
	}
	
	public void setCategoria3(Integer categoria3) {
		this.categoria3 = categoria3;
	}
	
	public Integer getCategoria4() {
		return categoria4;
	}
	
	public void setCategoria4(Integer categoria4) {
		this.categoria4 = categoria4;
	}

	public Programaeducativo getProgeduc() {
		return progeduc;
	}

	public void setProgeduc(Programaeducativo progeduc) {
		this.progeduc = progeduc;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getCategoria5() {
		return categoria5;
	}

	public void setCategoria5(Integer categoria5) {
		this.categoria5 = categoria5;
	}

	public Integer getFinalizarparticipaciontrabajo() {
		return finalizarparticipaciontrabajo;
	}

	public void setFinalizarparticipaciontrabajo(Integer finalizarparticipaciontrabajo) {
		this.finalizarparticipaciontrabajo = finalizarparticipaciontrabajo;
	}

	public Integer getFinalizarparticipaciondocentetrabajo() {
		return finalizarparticipaciondocentetrabajo;
	}

	public void setFinalizarparticipaciondocentetrabajo(Integer finalizarparticipaciondocentetrabajo) {
		this.finalizarparticipaciondocentetrabajo = finalizarparticipaciondocentetrabajo;
	}
	
}
