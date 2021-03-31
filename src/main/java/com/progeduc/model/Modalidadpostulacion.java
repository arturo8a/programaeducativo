package com.progeduc.model;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Modalidadpostulacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="INDIVIDUAL")
	private Integer individual;
	
	@Column(name="GRUPAL",nullable=true)
	private Integer grupal;
	
	@ManyToOne
	@JoinColumn(name="PARTICIPANTEID",nullable=true,foreignKey=@ForeignKey(name="FK_modalidad_participante"))
	private Participante participante;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndividual() {
		return individual;
	}

	public void setIndividual(Integer individual) {
		this.individual = individual;
	}

	public Integer getGrupal() {
		return grupal;
	}

	public void setGrupal(Integer grupal) {
		this.grupal = grupal;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}	
}
