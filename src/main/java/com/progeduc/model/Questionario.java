package com.progeduc.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="QUESTIONARIO")
public class Questionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="pregunta",nullable=true,length=250)
	private String pregunta;
	
	@OneToMany(mappedBy="questionario", cascade= {CascadeType.ALL}, orphanRemoval=true)
	private List<QuestionarioRespuesta> questionariorespuesta;
	
	@Column(name="ESTADO",nullable=true)
	private Integer estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public List<QuestionarioRespuesta> getQuestionariorespuesta() {
		return questionariorespuesta;
	}

	public void setQuestionariorespuesta(List<QuestionarioRespuesta> questionariorespuesta) {
		this.questionariorespuesta = questionariorespuesta;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}
