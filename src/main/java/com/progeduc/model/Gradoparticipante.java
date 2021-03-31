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
@Table(name="GRADOPARTICIPANTE")
public class Gradoparticipante {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="DESCRIPCION" ,length=150)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="nivelparticipanteid",nullable=true,foreignKey=@ForeignKey(name="FK_gradoest_nivelpart"))
	private Nivelparticipante nivelparticipante;

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

	public Nivelparticipante getNivelparticipante() {
		return nivelparticipante;
	}

	public void setNivelparticipante(Nivelparticipante nivelparticipante) {
		this.nivelparticipante = nivelparticipante;
	}
}
